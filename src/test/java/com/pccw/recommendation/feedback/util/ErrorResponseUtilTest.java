package com.pccw.recommendation.feedback.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class ErrorResponseUtilTest {

	@Test
	public void contextLoads() {

		String value = ErrorResponseUtil.responseCodes.get(ErrorResponseUtil.STATUS_CODE_MANDATORY);

		assertEquals("The following fields are required : ", value);
	}

}