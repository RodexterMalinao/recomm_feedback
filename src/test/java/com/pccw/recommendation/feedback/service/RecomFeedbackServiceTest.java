package com.pccw.recommendation.feedback.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import com.pccw.recommendation.feedback.helper.RecomFeedbackHelper;
import com.pccw.recommendation.feedback.model.RecomFeedbackPost;

public class RecomFeedbackServiceTest extends RecomFeedbackHelper {

	@Mock
	Exchange xchg;

	@Test
	public void RecomFeedbackService() {
		CamelContext ctx = new DefaultCamelContext();
		Exchange ex = new DefaultExchange(ctx);
		RecomFeedbackService service = new RecomFeedbackServiceImpl();

		RecomFeedbackPost recomFeedbackPost1 = postSample1();
		ex.getIn().setBody(recomFeedbackPost1);

		assertFalse(null, service.isRequestValid(ex));

		RecomFeedbackPost recomFeedbackPost2 = postSample2();
		ex.getIn().setBody(recomFeedbackPost2);

		assertTrue(null, service.isRequestValid(ex));

		ex.getIn().setBody(recomFeedbackPost1);
		service.insertRecomFeedback(ex);

		ex.getIn().setBody(recomFeedbackPost1);
		service.printErrorResponseMessage(ex);

		service.selectRecomFeedback(ex);

		ex.getIn().setHeader("parentCustNum", "test");
		ex.getIn().setHeader("productLines", "test");
		service.returnRecomFeedbackListByCust(ex);
	}

	@Test
	public void retrieveRecomFeedbackTest() {
		CamelContext ctx = new DefaultCamelContext();
		Exchange ex = new DefaultExchange(ctx);
		RecomFeedbackService service = new RecomFeedbackServiceImpl();

		ArrayList<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		dataList.add(feedbackSample1());
		dataList.add(feedbackSample1());
		ex.getIn().setBody(dataList);

		service.retrieveRecomFeedback(ex);
	}

	private RecomFeedbackPost postSample1() {
		RecomFeedbackPost recomFeedbackPost = new RecomFeedbackPost();

		recomFeedbackPost.setFeedbackSystem("Test");
		recomFeedbackPost.setRecommendationSourceSystem("");
		recomFeedbackPost.setRecommendedOffer("");
		recomFeedbackPost.setFeedbackType("");
		recomFeedbackPost.setFeedbackReason("");
		recomFeedbackPost.setProductLines("");
		recomFeedbackPost.setClubId("");
		recomFeedbackPost.setParentCustNum("");
		recomFeedbackPost.setLineLevelKey("");
		recomFeedbackPost.setLineLevelValue("");
		recomFeedbackPost.setCustomerNumber("");
		recomFeedbackPost.setStaffId("");
		recomFeedbackPost.setStaffName("");
		recomFeedbackPost.setTeamId("");
		recomFeedbackPost.setTeamName("");
		recomFeedbackPost.setChannelCode("");
		recomFeedbackPost.setChannelName("");

		return recomFeedbackPost;
	}

	private RecomFeedbackPost postSample2() {
		RecomFeedbackPost recomFeedbackPost = new RecomFeedbackPost();

		recomFeedbackPost.setFeedbackSystem("Test");
		recomFeedbackPost.setRecommendationSourceSystem("Test");
		recomFeedbackPost.setRecommendedOffer("Test");
		recomFeedbackPost.setFeedbackType("Test");
		recomFeedbackPost.setFeedbackReason("Test");
		recomFeedbackPost.setProductLines("Test");
		recomFeedbackPost.setClubId("Test");
		recomFeedbackPost.setParentCustNum("Test");
		recomFeedbackPost.setLineLevelKey("Test");
		recomFeedbackPost.setLineLevelValue("Test");
		recomFeedbackPost.setCustomerNumber("Test");
		recomFeedbackPost.setStaffId("Test");
		recomFeedbackPost.setStaffName("Test");
		recomFeedbackPost.setTeamId("Test");
		recomFeedbackPost.setTeamName("Test");
		recomFeedbackPost.setChannelCode("Test");
		recomFeedbackPost.setChannelName("Test");

		return recomFeedbackPost;
	}

	@SuppressWarnings("deprecation")
	private Map<String, Object> feedbackSample1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(F_FEEDBACK_ID, "1");
		map.put(F_FEEDBACK_DTTM, new Date("2021/01/01 12:12:12"));
		map.put(F_FEEDBACK_SYSTEM, "test");
		map.put(F_RECOMMENDATION_SOURCE_SYSTEM, "test");
		map.put(F_RECOMMENDED_OFFER, "test");
		map.put(F_FEEDBACK_TYPE, "test");
		map.put(F_FEEDBACK_REASON, "test");
		map.put(F_PRODUCT_LINES, "test");
		map.put(F_CLUB_ID, "test");
		map.put(F_PARENT_CUST_NUM, "test");
		map.put(F_LINE_LEVEL_KEY, "test");
		map.put(F_LINE_LEVEL_VALUE, "test");
		map.put(F_CUSTOMER_NUMBER, "test");
		map.put(F_STAFF_ID, "test");
		map.put(F_STAFF_NAME, "test");
		map.put(F_TEAM_ID, "test");
		map.put(F_TEAM_NAME, "test");
		map.put(F_CHANNEL_CODE, "test");
		map.put(F_CHANNEL_NAME, "test");
		map.put(F_ENABLED_FLAG, "Y");
		return map;
	}

	@Test
	public void testToString() {
		RecomFeedbackPost post = postSample1(); // you didn't supply the object, so I guessed
		String expected = "RecomFeedbackPost [feedbackSystem=Test, recommendationSourceSystem=, recommendedOffer=, feedbackType=, feedbackReason=, productLines=, clubId=, parentCustNum=, lineLevelKey=, lineLevelValue=, customerNumber=, staffId=, staffName=, teamId=, teamName=, channelCode=, channelName=]"; // put
		// here
		Assert.assertEquals(expected, post.toString());
	}

}