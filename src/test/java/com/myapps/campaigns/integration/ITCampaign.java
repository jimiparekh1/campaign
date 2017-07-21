package com.myapps.campaigns.integration;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.myapps.campaigns.Application;
import com.myapps.campaigns.utils.JsonUtils;
import com.myapps.campaigns.web.models.Campaign;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:${server.localport}")
public class ITCampaign {

	@Test
    public void addAndGetCampaignSunny(){
		Campaign campaign = new Campaign();
		String partnerId = "p1";
		campaign.setPartnerId(partnerId);
		campaign.setDuration(100);
		campaign.setAdContent("First Ad");
		String requestBody = JsonUtils.asJson(campaign);
		//Add a campaign
		given()
        	.contentType("application/json").body(requestBody).log().all()
        	.expect().statusCode(204)
        	.when()
        	.post("/ad");
		
		//Received the campaign to verify it was added properly
		Campaign received = given()
	        	.contentType("application/json").log().all()
	        	.expect().statusCode(200)
	        	.when()
	        	.get("/ad/p1").as(Campaign.class);
		
		assertNotNull(received);
		assertTrue(received.partnerId.equals(partnerId));
		
	}
	
	@Test
	public void missingPartnerIdAdd(){
		Campaign campaign = new Campaign();
		campaign.setDuration(100);
		campaign.setAdContent("First Ad");
		String requestBody = JsonUtils.asJson(campaign);
		// Try to Add a campaign and verify that 404 is throw
		given()
        	.contentType("application/json").body(requestBody).log().all()
        	.expect().statusCode(400)
        	.when()
        	.post("/ad");
	}
	
	@Test
	public void duplicatePartnerIdAdd(){
		Campaign campaign = new Campaign();
		String partnerId = "p1";
		campaign.setPartnerId(partnerId);
		campaign.setDuration(100);
		campaign.setAdContent("First Ad");
		String requestBody = JsonUtils.asJson(campaign);
		
		//Add a campaign
		given()
        	.contentType("application/json").body(requestBody).log().all()
        	.expect().statusCode(204)
        	.when()
        	.post("/ad");
		
		//Received the campaign to verify it was added properly
		Campaign received = given()
	        	.contentType("application/json").log().all()
	        	.expect().statusCode(200)
	        	.when()
	        	.get("/ad/p1").as(Campaign.class);
		
		assertNotNull(received);
		assertTrue(received.partnerId.equals(partnerId));
		
		//Add record for same partner Id
		campaign.setPartnerId(partnerId);
		campaign.setDuration(100);
		campaign.setAdContent("Second Ad");
		requestBody = JsonUtils.asJson(campaign);
		
		//Add a campaign
		given()
        	.contentType("application/json").body(requestBody).log().all()
        	.expect().statusCode(409)
        	.when()
        	.post("/ad");
		
	}
	
	@Test
	public void noAddForPartnerIdGet(){
				given()
			        	.contentType("application/json").log().all()
			        	.expect().statusCode(404)
			        	.when()
			        	.get("/ad/p1");
		
	}
	
	@Test
	public void getAllAdEmpty(){
		
		Campaign[] received = given()
	        	.contentType("application/json").log().all()
	        	.expect().statusCode(200)
	        	.when()
	        	.get("/ad").as(Campaign[].class);
		
		assertNotNull(received);
		assertTrue(received.length == 0);
		
	}
	
	@Test
	public void getAllAd(){
		Campaign campaign = new Campaign();
		campaign.setPartnerId("p1");
		campaign.setDuration(100);
		campaign.setAdContent("First Ad");
		String requestBody = JsonUtils.asJson(campaign);
		//Add first campaign
		given()
        	.contentType("application/json").body(requestBody).log().all()
        	.expect().statusCode(204)
        	.when()
        	.post("/ad");
		
		campaign.setPartnerId("p2");
		campaign.setAdContent("Second Ad");
		requestBody = JsonUtils.asJson(campaign);
		
		//Add second campaign
		given()
    	.contentType("application/json").body(requestBody).log().all()
    	.expect().statusCode(204)
    	.when()
    	.post("/ad");
		
		Campaign[] received = given()
	        	.contentType("application/json").log().all()
	        	.expect().statusCode(200)
	        	.when()
	        	.get("/ad").as(Campaign[].class);
		
		assertNotNull(received);
		assertTrue(received.length == 2);
		
	}
}
