package com.myapps.campaigns.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.myapps.campaigns.exceptions.ResourceConflictException;
import com.myapps.campaigns.exceptions.ResourceNotFoundException;
import com.myapps.campaigns.service.CampaignService;
import com.myapps.campaigns.web.models.Campaign;

@Service
public class CampaignServiceImpl implements CampaignService {

	private static final Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);
	private Map<String,Campaign> campaignMap = new ConcurrentHashMap<>();
	
	@Override
	public void addCampaign(Campaign campaign) {
		Campaign cp = campaignMap.get(campaign.getPartnerId());
		if( cp == null || cp.getExpiringAt() < System.currentTimeMillis()){
			campaign.setExpiringAt(System.currentTimeMillis() + campaign.getDuration() * 1000);
			campaignMap.put(campaign.getPartnerId(), campaign);
			logger.debug("Campaign successfully added for partnerID:" + campaign.getPartnerId());
		}else{
			logger.debug("Campaign exists for partnerID:" + campaign.getPartnerId());
			throw new ResourceConflictException("Campaign with parner id: " + 
						campaign.getPartnerId() +" already exists.");
		}
	}

	@Override
	public Campaign getCampaign(String partnerId) {
		Campaign cp = campaignMap.get(partnerId);
		if(cp == null || cp.getExpiringAt() < System.currentTimeMillis()){
			logger.debug("No active campaign found for partnerID:" + partnerId);
			throw new ResourceNotFoundException("No active for parnerId:" + partnerId +" found");
		}
		return cp;
	}

	@Override
	public List<Campaign> getCampaigns() {
		List<Campaign> campaigns = new ArrayList<>(campaignMap.values());
		return campaigns;
	}

}
