package com.myapps.campaigns.service;

import java.util.List;

import com.myapps.campaigns.web.models.Campaign;

public interface CampaignService {
	public void addCampaign(Campaign campaign);
	
	public Campaign getCampaign(String partnerId);
	
	public List<Campaign> getCampaigns();

}
