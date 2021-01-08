package com.pccw.recommendation.feedback.response;

import java.util.List;

public class DataTest {
	private String feedbackId;
	private List<RecomFeedbackHistory> history;

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public List<RecomFeedbackHistory> getHistory() {
		return history;
	}

	public void setHistory(List<RecomFeedbackHistory> history) {
		this.history = history;
	}

}
