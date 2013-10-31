package ca.ubc.cpsc310.project.TreeFinder.client;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;


@SuppressWarnings("serial")
@PersistenceCapable
public class ImageBlob implements Serializable{
	public static final String SERVING_URL = "servingUrl";
	public static final String OWNER_ID = "ownerId";
	String key;
	String servingUrl;
	String ownerId;
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getServingUrl() {
		return servingUrl;
	}

	public void setServingUrl(String servingUrl) {
		this.servingUrl = servingUrl;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}



}
