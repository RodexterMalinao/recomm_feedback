//package com.pccw.recommendation.feedback.model;
//
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.model.rest.RestBindingMode;
//import org.apache.camel.model.rest.RestParamType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//
//import com.pccw.recommendation.feedback.service.RecomFeedbackService;
//import com.pccw.recommendation.feedback.service.RecomFeedbackServiceImpl;
//
//@Component
//public class RecomFeedbackRoute extends RouteBuilder {
//
//	@Autowired
//	DataSource dataSource;
//
//	public DataSource getDataSource() {
//		return dataSource;
//	}
//
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
//
//	RecomFeedbackService recomFeedbackService = new RecomFeedbackServiceImpl();
//
//	@Override
//	public void configure() {
//		restConfiguration().component("servlet").bindingMode(RestBindingMode.json);
//
//		rest("/retrieve").get().outType(RecomFeedback.class).to("direct:select");
//
//		rest("/insert").post().param().name("feedbackHistory").type(RestParamType.query).required(true).endParam()
//				.produces(MediaType.APPLICATION_JSON_VALUE).type(RecomFeedback.class).route().to("direct:setHeader")
//				.routeId("postRecomFeedbackRoute").log("--- binded ${body} ---").choice()
//				.when(header("feedbackHistory").isEqualTo("T")).multicast().to("direct:insert", "direct:getReturnId")
//				.endChoice().otherwise().multicast().to("direct:insert", "direct:getReturnId");
//
//		from("direct:select").setBody(constant("select * from recommendation_feedback")).to("jdbc:dataSource")
//				.process(new Processor() {
//					public void process(Exchange xchg) throws Exception {
//						log.info("Param Value = " + xchg.getIn().getHeader("feedbackHistory"));
//						xchg.getIn().getHeader("Value");
//						List<RecomFeedback> recomFeedbackList = recomFeedbackService.retrieveRecomFeedback(xchg);
//						xchg.getIn().setBody(recomFeedbackList);
//					}
//				});
//
//		from("direct:setHeader").process(new Processor() {
//			public void process(Exchange xchg) throws Exception {
//				RecomFeedback recomFeedback = xchg.getIn().getBody(RecomFeedback.class);
//				xchg.getIn().setHeader("clubId", recomFeedback.getClubId());
//				xchg.getIn().setHeader("mobileNumber", recomFeedback.getMobileNumber());
//				xchg.getIn().setHeader("FSA", recomFeedback.getFsa());
//				xchg.getIn().setHeader("serviceNumber", recomFeedback.getServiceNumber());
//				xchg.getIn().setHeader("customerNumber", recomFeedback.getCustomerNumber());
//			}
//		});
//
//		from("direct:insert").process(new Processor() {
//			public void process(Exchange xchg) throws Exception {
//				recomFeedbackService.insertRecomFeedback(xchg);
//			}
//		}).to("jdbc:dataSource");
//
//		from("direct:getReturnId").process(new Processor() {
//			public void process(Exchange xchg) throws Exception {
//				recomFeedbackService.returnId(xchg);
//			}
//		}).to("jdbc:dataSource").process(new Processor() {
//			public void process(Exchange xchg) throws Exception {
//				String feedbackId = xchg.getIn().getBody(String.class).replaceAll("\\D+", "");
//				xchg.getIn().setHeader("feedbackId", feedbackId);
//			}
//		});
//
//		from("direct:returnWithFeedbackHistory").process(new Processor() {
//			public void process(Exchange xchg) throws Exception {
//				recomFeedbackService.returnRecomFeedbackListByCust(xchg);
//			}
//		}).to("jdbc:dataSource").to("direct:printResponseMessage");
//
//		from("direct:printResponseMessage").process(new Processor() {
//			public void process(Exchange xchg) throws Exception {
//				recomFeedbackService.printResponseMessage(xchg);
//			}
//		});
//	}
//}
//
////rest("/insert").post().produces(MediaType.APPLICATION_JSON_VALUE).type(RecomFeedback.class).route()
////.routeId("postRecomFeedbackRoute").log("--- binded ${body} ---").to("direct:insert")
////.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201)).setBody(constant(null));
//
////from("direct:talk").process(exchange -> {
////RecomFeedback p = new RecomFeedback();
////p.setFirstname("Bennet");
////p.setLastname("Schulz");
////exchange.getIn().setBody(p);
////});
//
////rest("/cafe/menu").description("Recommendation Feedback")
////.get("/items").description("Returns all menu items").outType(MenuItem[].class)
////    .responseMessage().code(200).message("All of the menu items").endResponseMessage()
////    .to("bean:menuService?method=getMenuItems")
////.get("/items/{id}").description("Returns menu item with matching id").outType(MenuItem.class)
////    .param().name("id").type(RestParamType.path).description("The id of the item").dataType("int").endParam()
////    .responseMessage().code(200).message("The requested menu item").endResponseMessage()
////    .responseMessage().code(404).message("Menu item not found").endResponseMessage()
////    .to("bean:menuService?method=getMenuItem(${header.id})")
////.post("/items").description("Creates a new menu item").type(MenuItem.class)
////    .param().name("body").type(RestParamType.body).description("The item to create").endParam()
////    .responseMessage().code(201).message("Successfully created menu item").endResponseMessage()
////    .responseMessage().code(400).message("Invalid menu item").endResponseMessage()
////    .route().to("bean:menuService?method=createMenuItem").setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201)).endRest()
