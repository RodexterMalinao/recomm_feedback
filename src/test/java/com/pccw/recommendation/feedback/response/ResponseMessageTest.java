package com.pccw.recommendation.feedback.response;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.javacrumbs.jsonunit.core.internal.JsonUtils;

public class ResponseMessageTest {

	@Test
	public void ResponseMessage() {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setMessage("Test message");
		responseMessage.setStatus(200);
		Data data = new Data();
		data.setFeedbackId("1");
		List<RecomFeedbackHistory> list = new ArrayList<RecomFeedbackHistory>();
		list.add(postSample1());
		data.setHistory(list);
		responseMessage.setData(data);
		System.out.print(JsonUtils.convertToJson(responseMessage, ""));
	}

	private RecomFeedbackHistory postSample1() {
		RecomFeedbackHistory recomFeedbackHistory = new RecomFeedbackHistory();

		recomFeedbackHistory.setFeedbackDttm("2021-01-04 01:50:04");
		recomFeedbackHistory.setFeedbackSystem("Test");
		recomFeedbackHistory.setRecommendationSourceSystem("Test");
		recomFeedbackHistory.setRecommendedOffer("Test");
		recomFeedbackHistory.setFeedbackType("Test");
		recomFeedbackHistory.setFeedbackReason("Test");
		recomFeedbackHistory.setProductLines("Test");
		recomFeedbackHistory.setClubId("Test");
		recomFeedbackHistory.setParentCustNum("Test");
		recomFeedbackHistory.setLineLevelKey("Test");
		recomFeedbackHistory.setLineLevelValue("Test");
		recomFeedbackHistory.setCustomerNumber("Test");
		recomFeedbackHistory.setStaffId("Test");
		recomFeedbackHistory.setStaffName("Test");
		recomFeedbackHistory.setTeamId("Test");
		recomFeedbackHistory.setTeamName("Test");
		recomFeedbackHistory.setChannelCode("Test");
		recomFeedbackHistory.setChannelName("Test");

		return recomFeedbackHistory;
	}

}
