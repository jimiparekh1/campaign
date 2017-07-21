package com.myapps.campaigns.web.models;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Campaign {

	@NotNull(message = "partnerId can not be null")
	public String partnerId;
	public int duration;
	public String adContent;
	
	@JsonIgnore
	public long expiringAt;
	

	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAdContent() {
		return adContent;
	}
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}
	@JsonProperty("expiringAt")
	public long getExpiringAt() {
		return expiringAt;
	}
	@JsonIgnore
	public void setExpiringAt(long expiringAt) {
		this.expiringAt = expiringAt;
	}
	
	
}
