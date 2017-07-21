package com.myapps.campaigns.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapps.campaigns.service.CampaignService;
import com.myapps.campaigns.web.models.Campaign;

@RestController
@RequestMapping("/ad")
public class CampaingController {

	private static final Logger logger = LoggerFactory.getLogger(CampaingController.class);
	
	@Autowired
	private CampaignService campaignService;
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.POST)
	public void createCampaign(@Valid @RequestBody Campaign campaign, HttpServletRequest request){
		System.out.println("POST");
		logger.debug("Create Campaing for partnerId:" + campaign.getPartnerId());
		campaignService.addCampaign(campaign);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, value = "/{partnerId}")
	public Campaign getCampaign(@PathVariable String partnerId, HttpServletRequest request ){
		logger.debug("Get Campaing for partnerId:" + partnerId);
		Campaign cp = campaignService.getCampaign(partnerId);
		return cp;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET)
	public List<Campaign> getAllCampaign(HttpServletRequest request ){
		logger.debug("Get All Campaings ");
		return campaignService.getCampaigns();

	}
}
