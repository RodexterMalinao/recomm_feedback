package com.pccw.recommendation.feedback.util;

import java.util.HashMap;

public final class ErrorResponseUtil {

	public static final HashMap<Integer, String> responseCodes = new HashMap<>();

	public static final int STATUS_CODE_MANDATORY = 480;
	public static final int STATUS_CODE_FEEDBACK_REASON = 481;
	public static final int STATUS_CODE_CLUB_ID = 482;
	public static final int STATUS_CODE_LINE_LEVEL_KEY = 483;
	public static final int STATUS_CODE_LINE_LEVEL_VALUE = 484;
	public static final int STATUS_CODE_PARENT_CUST_NUM = 485;

	static {
		responseCodes.put(STATUS_CODE_MANDATORY, "The following fields are required : ");
		responseCodes.put(STATUS_CODE_FEEDBACK_REASON, " feedback_reason is not null if feedback_type is “REJECT” ");
		responseCodes.put(STATUS_CODE_CLUB_ID, " club_id is not null if product_lines is “CLUB” ");
		responseCodes.put(STATUS_CODE_LINE_LEVEL_KEY, " line_level_key is not null if product_lines <> “CLUB” ");
		responseCodes.put(STATUS_CODE_LINE_LEVEL_VALUE, " line_level_value is not null if product_lines <> “CLUB” ");
		responseCodes.put(STATUS_CODE_PARENT_CUST_NUM, " parent_cust_num is not null if product_lines <> “CLUB” ");
//		responseCodes.put(3, "thirty");
//		responseCodes.put(4, "forty");
//		responseCodes.put(5, "fifty");
//		responseCodes.put(6, "sixty");
//		responseCodes.put(7, "seventy");
//		responseCodes.put(8, "eighty");
//		responseCodes.put(9, "ninety");
	}

}