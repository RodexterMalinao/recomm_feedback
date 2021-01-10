package com.pccw.recommendation.feedback.App;

import java.util.List;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.pccw.recommendation.feedback.model.RecomFeedback;
import com.pccw.recommendation.feedback.model.RecomFeedbackPost;
import com.pccw.recommendation.feedback.service.RecomFeedbackService;
import com.pccw.recommendation.feedback.service.RecomFeedbackServiceImpl;

@Component
public class RecomFeedbackRoute extends RouteBuilder {

	@Autowired
	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	RecomFeedbackService recomFeedbackService = new RecomFeedbackServiceImpl();

	@Override
	public void configure() {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

		rest("/retrieve").get().param().name("productLines").type(RestParamType.query).required(true).endParam().param()
				.name("parentCustNum").type(RestParamType.query).required(false).endParam().param().name("clubId")
				.type(RestParamType.query).required(false).endParam().outType(RecomFeedback.class).to("direct:select");

		rest("/insert").post().param().name("feedbackHistory").type(RestParamType.query).required(true).endParam()
				.produces(MediaType.APPLICATION_JSON_VALUE).type(RecomFeedbackPost.class).route().to("direct:setHeader")
				.routeId("postRecomFeedbackRoute").log("--- binded ${body} ---").to("direct:validate");

		/** Select the record */
		from("direct:select").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				recomFeedbackService.selectRecomFeedback(xchg);
			}
		}).to("jdbc:dataSource").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				List<RecomFeedback> recomFeedbackList = recomFeedbackService.retrieveRecomFeedback(xchg);
				xchg.getIn().setBody(recomFeedbackList);
			}
		});

		from("direct:setHeader").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				RecomFeedbackPost recomFeedback = xchg.getIn().getBody(RecomFeedbackPost.class);
				xchg.getIn().setHeader("parentCustNum", recomFeedback.getParentCustNum());
				xchg.getIn().setHeader("productLines", recomFeedback.getProductLines());
//				xchg.getIn().setHeader("clubId", recomFeedback.getClubId());
			}
		});

		/** Validate request */
		from("direct:validate").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				xchg.getIn().setHeader("isValid", recomFeedbackService.isRequestValid(xchg));
//				log.info("body : " + xchg.getIn().getBody());
//				log.info("validate");
			}
		}).choice().when(header("isValid")).to("direct:insertValidated").otherwise()
				.to("direct:printErrorResponseMessage");

		from("direct:insertValidated").multicast().to("direct:insert", "direct:getReturnId");

		/** Insert the record */
		from("direct:insert").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				recomFeedbackService.insertRecomFeedback(xchg);
			}
		}).to("jdbc:dataSource");

		/** Get return id */
		from("direct:getReturnId").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
//				log.info("isValid : " + xchg.getIn().getHeader("isValid"));
				recomFeedbackService.returnId(xchg);
			}
		}).to("jdbc:dataSource").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				String feedbackId = xchg.getIn().getBody(String.class).replaceAll("\\D+", "");
				xchg.getIn().setHeader("feedbackId", feedbackId);
				log.info("feedbackId : " + xchg.getIn().getHeader("feedbackId"));
			}
		}).choice().when(header("feedbackHistory").isEqualTo("T")).to("direct:getFeedbackHistory").endChoice()
				.otherwise().to("direct:printSuccessResponseMessage");

		/** Get feedback history */
		from("direct:getFeedbackHistory").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				recomFeedbackService.returnRecomFeedbackListByCust(xchg);
//				log.info("direct:getFeedbackHistory : ok ");
			}
		}).to("jdbc:dataSource").to("direct:printSuccessResponseMessage");

		/** Print success response message */
		from("direct:printSuccessResponseMessage").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
//				log.info("feedbackId : " + xchg.getIn().getHeader("feedbackId"));
				recomFeedbackService.printSuccessResponseMessage(xchg);
			}
		});

		/** Print error response message */
		from("direct:printErrorResponseMessage").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
				recomFeedbackService.printErrorResponseMessage(xchg);
				xchg.getOut().setHeader("CamelHttpResponseCode", xchg.getIn().getHeader("error-code"));
			}
		});// .setHeader(Exchange.HTTP_RESPONSE_CODE,
			// constant(ErrorResponseUtil.STATUS_CODE_MANDATORY));
	}
}
