//package com.pccw.recommendation.feedback.model;
//
//public class Backup extends RouteBuilder {
//
//		@Autowired
//		DataSource dataSource;
//
//		public DataSource getDataSource() {
//			return dataSource;
//		}
//
//		public void setDataSource(DataSource dataSource) {
//			this.dataSource = dataSource;
//		}
//
//		RecomFeedbackService recomFeedbackService = new RecomFeedbackServiceImpl();
//
//		@Override
//		public void configure() {
//			restConfiguration().component("servlet").bindingMode(RestBindingMode.json);
//
//			rest("/retrieve").get().outType(RecomFeedback.class).to("direct:select");
//
////			rest("/insert").post().produces(MediaType.APPLICATION_JSON_VALUE).type(RecomFeedback.class).route()
////					.routeId("postRecomFeedbackRoute").log("--- binded ${body} ---").to("direct:insert")
////					.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201)).setBody(constant(null));
//
//			rest("/insert").post().produces(MediaType.APPLICATION_JSON_VALUE).type(RecomFeedback.class).route()
//					.routeId("postRecomFeedbackRoute").log("--- binded ${body} ---").to("direct:insert")
//					.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
//
//			from("direct:select").setBody(constant("select * from recommendation_feedback")).to("jdbc:dataSource")
//					.process(new Processor() {
//						public void process(Exchange xchg) throws Exception {
//							List<RecomFeedback> recomFeedbackList = recomFeedbackService.getRecomFeedback(xchg);
//							xchg.getIn().setBody(recomFeedbackList);
//						}
//					});
//
//			from("direct:returnId").to("jdbc:dataSource").process(new Processor() {
//				public void process(Exchange xchg) throws Exception {
//					recomFeedbackService.returnId(xchg);
//				}
//			});
//
//			from("direct:insert").process(new Processor() {
//				public void process(Exchange xchg) throws Exception {
//					recomFeedbackService.insertRecomFeedback(xchg);
//				}
//			}).to("jdbc:dataSource").to("direct:returnId").setBody(simple("Test"));
//
//		}
//	}
//
//	//from("direct:talk").process(exchange -> {
//	//RecomFeedback p = new RecomFeedback();
//	//p.setFirstname("Bennet");
//	//p.setLastname("Schulz");
//	//exchange.getIn().setBody(p);
//	//});
//
//}
