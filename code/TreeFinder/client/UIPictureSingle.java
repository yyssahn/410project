package ca.ubc.cpsc310.project.TreeFinder.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class UIPictureSingle {
	private VerticalPanel myPanel;
	private HorizontalPanel adminPanel;
	private String filename;
	private boolean adminRights = false;
	Image image;
	ImageBlob imageblob;
	
	
	public Widget asWidget(){
		return myPanel;
	}
	
	/**
	 * A constructor that accepts a URL parameter.
	 * @param filename The URL of an image to be created.
	 */
	public UIPictureSingle(String filename){
		this.filename = filename;
		
		myPanel = new VerticalPanel();
		myPanel.setStyleName("imageSingle");
		myPanel.setBorderWidth(0);
		
		// adminPanel
		adminPanel = new HorizontalPanel();
		adminPanel.setSpacing(2);
		if (adminRights)
			myPanel.add(adminPanel);
		adminPanel.setSize("100%", "30");
		
		Label nameLabel; 
		if (filename.equals(""))
			nameLabel = new Label("No name");
		else
			nameLabel = new Label(filename);
		
		nameLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		adminPanel.add(nameLabel);
		adminPanel.setCellVerticalAlignment(nameLabel, HasVerticalAlignment.ALIGN_MIDDLE);
		
		Image deleteImage = new Image("Graphics/cross25.png");
		deleteImage.setStyleName("imageButton");
		deleteImage.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				myPanel.getElement().setAttribute("cursor", "progress");
				// TODO Make a delete RPC-call on filename?
				myPanel.removeFromParent();
				}});
		
		adminPanel.add(deleteImage);
		adminPanel.setCellVerticalAlignment(deleteImage, HasVerticalAlignment.ALIGN_MIDDLE);
		adminPanel.setCellHorizontalAlignment(deleteImage, HasHorizontalAlignment.ALIGN_RIGHT);
		deleteImage.setSize("20", "20");
		
		// The main image
		image = new Image(filename);
		image.setAltText("A CUTE TREE");
		myPanel.add(image);
		image.setSize("100%", "80%");
		
		// Facebook HTML
		HTMLPanel facebookPanel = makeFacebookPanel(filename);
		facebookPanel.setStyleName("facebook-comment");
		myPanel.add(facebookPanel);
		
		//ScrollPanel facebookWrapper = new ScrollPanel(facebookPanel);
		//facebookWrapper.setSize("100%", "150px");
		//myPanel.add(facebookWrapper);
		myPanel.setCellHorizontalAlignment(facebookPanel, HasHorizontalAlignment.ALIGN_CENTER);
		facebookPanel.setSize("99%", "100%");
	}
	
	/** A default constructor.
	 * @wbp.parser.constructor **/
	public UIPictureSingle(){
		this("Images/1.png");
	}
	
	/**
	 * A method for changing whether the user has administrator rights - hides and shows the admin panel.
	 * @param adminRights
	 */
	public void setAdminRights(boolean adminRights) {
		if (this.adminRights!=adminRights)
		{ //need to toggle
			this.adminRights = adminRights;
			if (adminRights)
				myPanel.insert(adminPanel, 0);
			else
				adminPanel.removeFromParent();
		}
	}

	/** 
	 * Creates an HTML Panel with the comments for an image.
	 * @param filename	The URL of an image.
	 * */
	private static HTMLPanel makeFacebookPanel(String filename){
		HTMLPanel facebookPanel = new HTMLPanel(
				"<head></head>" +
						"<body>" +
						"<div id=\"fb-root\"></div>" +
						"<div align=\"right\" class=\"fb-comments\" data-href=\""
						+filename+"\" " +
						"data-width=\"400\" data-num-posts=\"2\"></div></div>" +
				"</body>"
				);
		testfunction();
		return facebookPanel;
	}
	
	/** A native JavaScript function that facebook requires.
	 * **/
	private native static void testfunction() /*-{
	  (function(d, s, id) {
  		var js, fjs = d.getElementsByTagName(s)[0];
  		if (d.getElementById(id)) return;
  		js = d.createElement(s); js.id = id;
  		js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=348106908627533";
  		fjs.parentNode.insertBefore(js, fjs);
		}($wnd.document, 'script', 'facebook-jssdk'));
	}-*/;

	
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
