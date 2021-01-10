package com.pccw.recommendation.feedback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Service;

import com.pccw.recommendation.feedback.helper.RecomFeedbackHelper;
import com.pccw.recommendation.feedback.model.RecomFeedback;
import com.pccw.recommendation.feedback.model.RecomFeedbackPost;
import com.pccw.recommendation.feedback.response.Data;
import com.pccw.recommendation.feedback.response.RecomFeedbackHistory;
import com.pccw.recommendation.feedback.response.ResponseMessage;
import com.pccw.recommendation.feedback.util.DataTypeUtil;
import com.pccw.recommendation.feedback.util.ErrorResponseUtil;

import net.javacrumbs.jsonunit.core.internal.JsonUtils;

@Service("recomFeedbackService")
public class RecomFeedbackServiceImpl extends RecomFeedbackHelper implements RecomFeedbackService {

	@Override
	public List<RecomFeedback> retrieveRecomFeedback(Exchange xchg) {
		@SuppressWarnings("unchecked")
		ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) xchg.getIn().getBody();
		List<RecomFeedback> recomFeedbacks = new ArrayList<RecomFeedback>();

		for (Map<String, String> data : dataList) {
			RecomFeedback recomFeedback = new RecomFeedback();
			recomFeedback.setFeedbackId(DataTypeUtil.stringAsInteger(data.get(F_FEEDBACK_ID)));
			recomFeedback.setFeedbackDttm(String.valueOf(data.get(F_FEEDBACK_DTTM)));
			recomFeedback.setFeedbackSystem(data.get(F_FEEDBACK_SYSTEM));
			recomFeedback.setRecommendationSourceSystem(data.get(F_RECOMMENDATION_SOURCE_SYSTEM));
			recomFeedback.setRecommendedOffer(data.get(F_RECOMMENDED_OFFER));
			recomFeedback.setFeedbackType(data.get(F_FEEDBACK_TYPE));
			recomFeedback.setFeedbackReason(data.get(F_FEEDBACK_REASON));
			recomFeedback.setProductLines(data.get(F_PRODUCT_LINES));
			recomFeedback.setClubId(data.get(F_CLUB_ID));
			recomFeedback.setParentCustNum(data.get(F_PARENT_CUST_NUM));
			recomFeedback.setLineLevelKey(data.get(F_LINE_LEVEL_KEY));
			recomFeedback.setLineLevelValue(data.get(F_LINE_LEVEL_VALUE));
			recomFeedback.setCustomerNumber(data.get(F_CUSTOMER_NUMBER));
			recomFeedback.setStaffId(data.get(F_STAFF_ID));
			recomFeedback.setStaffName(data.get(F_STAFF_NAME));
			recomFeedback.setTeamId(data.get(F_TEAM_ID));
			recomFeedback.setTeamName(data.get(F_TEAM_NAME));
			recomFeedback.setChannelCode(data.get(F_CHANNEL_CODE));
			recomFeedback.setChannelName(data.get(F_CHANNEL_NAME));
			recomFeedback.setEnabledFlag(data.get(F_ENABLED_FLAG));
			recomFeedbacks.add(recomFeedback);
		}

		return recomFeedbacks;
	}

	@Override
	public void selectRecomFeedback(Exchange xchg) {

		String columns = F_FEEDBACK_ID + "," + " TO_CHAR(" + F_FEEDBACK_DTTM + ", '" + DATE_FORMAT + "') "
				+ F_FEEDBACK_DTTM + "," + F_FEEDBACK_SYSTEM + "," + F_RECOMMENDATION_SOURCE_SYSTEM + ","
				+ F_RECOMMENDED_OFFER + "," + F_FEEDBACK_TYPE + "," + F_FEEDBACK_REASON + "," + F_PRODUCT_LINES + ","
				+ F_CLUB_ID + "," + F_PARENT_CUST_NUM + "," + F_LINE_LEVEL_KEY + "," + F_LINE_LEVEL_VALUE + ","
				+ F_CUSTOMER_NUMBER + "," + F_STAFF_ID + "," + F_STAFF_NAME + "," + F_TEAM_ID + "," + F_TEAM_NAME + ","
				+ F_CHANNEL_CODE + "," + F_CHANNEL_NAME + "," + F_ENABLED_FLAG;

		String criteria = F_PARENT_CUST_NUM + " = " + "'" + xchg.getIn().getHeader("parentCustNum") + "'" + " and "
				+ F_PRODUCT_LINES + " = " + "'" + xchg.getIn().getHeader("productLines") + "'" + " and "
				+ F_ENABLED_FLAG + " = " + "'" + ENABLE_FLAG + "'";

		String query = selectStatement(columns, T_RECOM_FB, criteria);

		System.out.println("query : " + query);

		xchg.getIn().setBody(query);

	}

	@Override
	public void insertRecomFeedback(Exchange xchg) {

		RecomFeedbackPost recomFeedbackPost = xchg.getIn().getBody(RecomFeedbackPost.class);
		RecomFeedback recomFeedback = new RecomFeedback(recomFeedbackPost);

		String columns = F_FEEDBACK_ID + "," + F_FEEDBACK_DTTM + "," + F_FEEDBACK_SYSTEM + ","
				+ F_RECOMMENDATION_SOURCE_SYSTEM + "," + F_RECOMMENDED_OFFER + "," + F_FEEDBACK_TYPE + ","
				+ F_FEEDBACK_REASON + "," + F_PRODUCT_LINES + "," + F_CLUB_ID + "," + F_PARENT_CUST_NUM + ","
				+ F_LINE_LEVEL_KEY + "," + F_LINE_LEVEL_VALUE + "," + F_CUSTOMER_NUMBER + "," + F_STAFF_ID + ","
				+ F_STAFF_NAME + "," + F_TEAM_ID + "," + F_TEAM_NAME + "," + F_CHANNEL_CODE + "," + F_CHANNEL_NAME;

		String values = "feedback_id_seq.nextval, CURRENT_TIMESTAMP," + "'" + recomFeedback.getFeedbackSystem() + "','"
				+ recomFeedback.getRecommendationSourceSystem() + "','" + recomFeedback.getRecommendedOffer() + "','"
				+ recomFeedback.getFeedbackType() + "','" + recomFeedback.getFeedbackReason() + "','"
				+ recomFeedback.getProductLines() + "','" + recomFeedback.getClubId() + "','"
				+ recomFeedback.getParentCustNum() + "','" + recomFeedback.getLineLevelKey() + "','"
				+ recomFeedback.getLineLevelValue() + "','" + recomFeedback.getCustomerNumber() + "','"
				+ recomFeedback.getStaffId() + "','" + recomFeedback.getStaffName() + "','" + recomFeedback.getTeamId()
				+ "','" + recomFeedback.getTeamName() + "','" + recomFeedback.getChannelCode() + "','"
				+ recomFeedback.getChannelName() + "'";

		String query = insertStatement(columns, T_RECOM_FB, values);

		System.out.println("query : " + query);

		xchg.getIn().setBody(query);
	}

	@Override
	public void returnId(Exchange xchg) {
		RecomFeedbackPost recomFeedbackPost = xchg.getIn().getBody(RecomFeedbackPost.class);
		RecomFeedback recomFeedback = new RecomFeedback(recomFeedbackPost);

		String criteria = F_CLUB_ID + " = " + "'" + recomFeedback.getClubId() + "'" + " and " + F_CUSTOMER_NUMBER + " ="
				+ "'" + recomFeedback.getCustomerNumber() + "'" + " and " + F_FEEDBACK_SYSTEM + " =" + "'"
				+ recomFeedback.getFeedbackSystem() + "'" + " and " + F_RECOMMENDATION_SOURCE_SYSTEM + " =" + "'"
				+ recomFeedback.getRecommendationSourceSystem() + "'" + " and " + F_RECOMMENDED_OFFER + " =" + "'"
				+ recomFeedback.getRecommendedOffer() + "'" + " and " + F_FEEDBACK_TYPE + " =" + "'"
				+ recomFeedback.getFeedbackType() + "'" + " and " + F_FEEDBACK_REASON + " =" + "'"
				+ recomFeedback.getFeedbackReason() + "'" + " and " + F_PRODUCT_LINES + " =" + "'"
				+ recomFeedback.getProductLines() + "'" + " and " + F_STAFF_ID + " =" + "'" + recomFeedback.getStaffId()
				+ "'" + " and " + F_STAFF_NAME + " =" + "'" + recomFeedback.getStaffName() + "'" + " and " + F_TEAM_ID
				+ " =" + "'" + recomFeedback.getTeamId() + "'" + " and " + F_TEAM_NAME + " =" + "'"
				+ recomFeedback.getTeamName() + "'" + " and " + F_CHANNEL_CODE + " =" + "'"
				+ recomFeedback.getChannelCode() + "'" + " and " + F_CHANNEL_NAME + " =" + "'"
				+ recomFeedback.getChannelName() + "' ORDER BY " + F_FEEDBACK_ID + " DESC";

		String query = selectStatement(F_FEEDBACK_ID, T_RECOM_FB, criteria);

		String queryRow1 = selectStatement(F_FEEDBACK_ID, "(" + query + ")", "ROWNUM = 1");

		System.out.println("select query : " + queryRow1);
		xchg.getIn().setBody(queryRow1);
	}

	@Override
	public void printSuccessResponseMessage(Exchange xchg) {

		ResponseMessage responseMessage = new ResponseMessage();
		Data data = new Data();

		String value = xchg.getIn().getHeader("feedbackHistory", String.class);
		String feedbackId = xchg.getIn().getHeader("feedbackId", String.class);

		if (value != null) {
			switch (value) {
			case "T":
				List<RecomFeedbackHistory> recomFeedbacks = new ArrayList<RecomFeedbackHistory>();
				recomFeedbacks = getRecomFeedbackList(xchg);
				responseMessage.setStatus(200);
				responseMessage.setSuccess(true);
				responseMessage.setMessage("Insert record with feedback history");
				data.setFeedbackId(feedbackId);
				data.setHistory(recomFeedbacks);
				responseMessage.setData(data);
				break;
			default:
				responseMessage.setStatus(200);
				responseMessage.setSuccess(true);
				responseMessage.setMessage("Insert record without feedback history");
				data.setFeedbackId(feedbackId);
				responseMessage.setData(data);
				xchg.getIn().setBody("INSERTED WITHOUT FEEDBACK HISTORY : " + xchg.getIn().getBody());
				break;
			}
		}

		xchg.getIn().setBody(responseMessage);
	}

	@Override
	public void returnRecomFeedbackListByCust(Exchange xchg) {
		String parentCustNum = String.valueOf(xchg.getIn().getHeader("parentCustNum").toString());
		String productLines = String.valueOf(xchg.getIn().getHeader("productLines").toString());

		String columns = "TO_CHAR(" + F_FEEDBACK_DTTM + ", '" + DATE_FORMAT + "') " + F_FEEDBACK_DTTM + ", "
				+ F_FEEDBACK_SYSTEM + ", " + F_RECOMMENDATION_SOURCE_SYSTEM + ", " + F_RECOMMENDED_OFFER + ", "
				+ F_FEEDBACK_TYPE + ", " + F_FEEDBACK_REASON + ", " + F_PRODUCT_LINES + ", " + F_CLUB_ID + ","
				+ F_PARENT_CUST_NUM + ", " + F_LINE_LEVEL_KEY + ", " + F_LINE_LEVEL_VALUE + ", " + F_CUSTOMER_NUMBER
				+ ", " + F_STAFF_ID + ", " + F_STAFF_NAME + ", " + F_TEAM_ID + ", " + F_TEAM_NAME + ", "
				+ F_CHANNEL_CODE + ", " + F_CHANNEL_NAME + ", " + F_ENABLED_FLAG;

		String criteria = F_PARENT_CUST_NUM + " = " + "'" + parentCustNum + "'" + " and " + F_PRODUCT_LINES + " = "
				+ "'" + productLines + "'" + " and " + F_ENABLED_FLAG + " = " + "'" + ENABLE_FLAG + "'" + " ORDER BY "
				+ F_FEEDBACK_ID + " ASC";

		String query = selectStatement(columns, T_RECOM_FB, criteria);

		System.out.println("query : " + query);
		xchg.getIn().setBody(query);
	}

	private List<RecomFeedbackHistory> getRecomFeedbackList(Exchange xchg) {
		@SuppressWarnings("unchecked")
		ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) xchg.getIn().getBody();
		List<RecomFeedbackHistory> recomFeedbacks = new ArrayList<RecomFeedbackHistory>();
		for (Map<String, String> data : dataList) {

			RecomFeedbackHistory recomFeedback = new RecomFeedbackHistory();

			recomFeedback.setFeedbackDttm(data.get(F_FEEDBACK_DTTM));
			recomFeedback.setFeedbackSystem(data.get(F_FEEDBACK_SYSTEM));
			recomFeedback.setRecommendationSourceSystem(data.get(F_RECOMMENDATION_SOURCE_SYSTEM));
			recomFeedback.setRecommendedOffer(data.get(F_RECOMMENDED_OFFER));
			recomFeedback.setFeedbackType(data.get(F_FEEDBACK_TYPE));
			recomFeedback.setFeedbackReason(data.get(F_FEEDBACK_REASON));
			recomFeedback.setProductLines(data.get(F_PRODUCT_LINES));
			recomFeedback.setClubId(data.get(F_CLUB_ID));
			recomFeedback.setParentCustNum(data.get(F_PARENT_CUST_NUM));
			recomFeedback.setLineLevelKey(data.get(F_LINE_LEVEL_KEY));
			recomFeedback.setLineLevelValue(data.get(F_LINE_LEVEL_VALUE));
			recomFeedback.setCustomerNumber(data.get(F_CUSTOMER_NUMBER));
			recomFeedback.setStaffId(data.get(F_STAFF_ID));
			recomFeedback.setStaffName(data.get(F_STAFF_NAME));
			recomFeedback.setTeamId(data.get(F_TEAM_ID));
			recomFeedback.setTeamName(data.get(F_TEAM_NAME));
			recomFeedback.setChannelCode(data.get(F_CHANNEL_CODE));
			recomFeedback.setChannelName(data.get(F_CHANNEL_NAME));
			recomFeedback.setEnabledFlag(data.get(F_ENABLED_FLAG));

			recomFeedbacks.add(recomFeedback);
		}

		return recomFeedbacks;
	}

	@Override
	public boolean isRequestValid(Exchange xchg) {

		RecomFeedbackPost recomFeedbackPost = xchg.getIn().getBody(RecomFeedbackPost.class);
		RecomFeedback recomFeedback = new RecomFeedback(recomFeedbackPost);

		return isMandatoryValid(recomFeedback);
	}

	@Override
	public void printErrorResponseMessage(Exchange xchg) {
		ResponseMessage responseMessage = new ResponseMessage();
		RecomFeedbackPost recomFeedbackPost = xchg.getIn().getBody(RecomFeedbackPost.class);
		RecomFeedback recomFeedback = new RecomFeedback(recomFeedbackPost);

		responseMessage.setStatus(ErrorResponseUtil.STATUS_CODE_MANDATORY);
		responseMessage.setSuccess(false);
		responseMessage.setMessage(ErrorResponseUtil.responseCodes.get(ErrorResponseUtil.STATUS_CODE_MANDATORY)
				+ returnMessageForMandatoryValidation(recomFeedback));
		xchg.getOut().setBody(JsonUtils.convertToJson(responseMessage, "Error"));
	}

}
