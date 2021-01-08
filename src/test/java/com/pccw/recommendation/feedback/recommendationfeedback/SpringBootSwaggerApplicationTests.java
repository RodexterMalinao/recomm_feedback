package com.pccw.recommendation.feedback.recommendationfeedback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import com.pccw.recommendation.feedback.model.RecomFeedback;
import com.pccw.recommendation.feedback.model.RecomFeedbackPost;
import com.pccw.recommendation.feedback.service.RecomFeedbackServiceImpl;
import com.pccw.recommendation.feedback.service.RecomFeedbackService;

//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringBootSwaggerApplicationTests {

	@Mock
	Exchange xchg;

	@Test
	public void contextLoads() {
		RecomFeedback recomFeedback = new RecomFeedback();
		recomFeedback.setChannelCode("test");
		assertEquals("test", recomFeedback.getChannelCode(), recomFeedback.getChannelCode());
	}

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

	@Test
	public void testToString() {
		RecomFeedbackPost post = postSample1(); // you didn't supply the object, so I guessed
		String expected = "RecomFeedbackPost [feedbackSystem=Test, recommendationSourceSystem=, recommendedOffer=, feedbackType=, feedbackReason=, productLines=, clubId=, parentCustNum=, lineLevelKey=, lineLevelValue=, customerNumber=, staffId=, staffName=, teamId=, teamName=, channelCode=, channelName=]"; // put
		// here
		Assert.assertEquals(expected, post.toString());
	}

}
//import static org.mockito.Mockito.*;
//
//public class MockitoTest  {
//
//    @Mock
//    MyDatabase databaseMock; 
//
//    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 
//
//    @Test
//    public void testQuery()  {
//        ClassToTest t  = new ClassToTest(databaseMock); 
//        boolean check = t.query("* from t"); 
//        assertTrue(check); 
//        verify(databaseMock).query("* from t"); 
//    }
//}