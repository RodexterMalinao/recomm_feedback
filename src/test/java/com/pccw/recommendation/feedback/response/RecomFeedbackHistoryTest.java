package com.pccw.recommendation.feedback.response;

public class RecomFeedbackHistoryTest {

	private String feedbackDttm;
	private String feedbackSystem;
	private String recommendationSourceSystem;
	private String recommendedOffer;
	private String feedbackType;
	private String feedbackReason;
	private String productLines;
	private String clubId;
	private String parentCustNum;
	private String lineLevelKey;
	private String lineLevelValue;
	private String customerNumber;
	private String staffId;
	private String staffName;
	private String teamId;
	private String teamName;
	private String channelCode;
	private String channelName;
	private String enabledFlag;

	public String getFeedbackDttm() {
		return feedbackDttm;
	}

	public void setFeedbackDttm(String feedbackDttm) {
		this.feedbackDttm = feedbackDttm;
	}

	public String getFeedbackSystem() {
		return feedbackSystem;
	}

	public void setFeedbackSystem(String feedbackSystem) {
		this.feedbackSystem = feedbackSystem;
	}

	public String getRecommendationSourceSystem() {
		return recommendationSourceSystem;
	}

	public void setRecommendationSourceSystem(String recommendationSourceSystem) {
		this.recommendationSourceSystem = recommendationSourceSystem;
	}

	public String getRecommendedOffer() {
		return recommendedOffer;
	}

	public void setRecommendedOffer(String recommendedOffer) {
		this.recommendedOffer = recommendedOffer;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getFeedbackReason() {
		return feedbackReason;
	}

	public void setFeedbackReason(String feedbackReason) {
		this.feedbackReason = feedbackReason;
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getProductLines() {
		return productLines;
	}

	public void setProductLines(String productLines) {
		this.productLines = productLines;
	}

	public String getParentCustNum() {
		return parentCustNum;
	}

	public void setParentCustNum(String parentCustNum) {
		this.parentCustNum = parentCustNum;
	}

	public String getLineLevelKey() {
		return lineLevelKey;
	}

	public void setLineLevelKey(String lineLevelKey) {
		this.lineLevelKey = lineLevelKey;
	}

	public String getLineLevelValue() {
		return lineLevelValue;
	}

	public void setLineLevelValue(String lineLevelValue) {
		this.lineLevelValue = lineLevelValue;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	@Override
	public String toString() {
		return "RecomFeedbackHistory [feedbackDttm=" + feedbackDttm + ", feedbackSystem=" + feedbackSystem
				+ ", recommendationSourceSystem=" + recommendationSourceSystem + ", recommendedOffer="
				+ recommendedOffer + ", feedbackType=" + feedbackType + ", feedbackReason=" + feedbackReason
				+ ", productLines=" + productLines + ", clubId=" + clubId + ", parentCustNum=" + parentCustNum
				+ ", lineLevelKey=" + lineLevelKey + ", lineLevelValue=" + lineLevelValue + ", customerNumber="
				+ customerNumber + ", staffId=" + staffId + ", staffName=" + staffName + ", teamId=" + teamId
				+ ", teamName=" + teamName + ", channelCode=" + channelCode + ", channelName=" + channelName
				+ ", enabledFlag=" + enabledFlag + "]";
	}

}