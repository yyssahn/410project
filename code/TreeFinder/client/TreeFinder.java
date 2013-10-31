package ca.ubc.cpsc310.project.TreeFinder.client;

import java.util.LinkedList;
import java.util.List;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Image;

/**
 *Entry point classes define <code>onModuleLoad()</code>.
 */
public class TreeFinder implements EntryPoint {
	private  VerticalPanel UI_MenuPanel;
	//List boxes for selected data
	private ListBox heightListBox, diameterListBox, typesListBox;
	//The boxes with order selected
	private ListBox sortingComboBox;
	private ListBox orderComboBox;
	//Button for sending query
	private Button sendButton;
	//Button for admin login
	private Button adminButton;
	//The list of all the Tree Tabs
	private FormPanel form;
	//"Image" tab
	private UIPictureList pictureListTab;
	// for uploading,
	private LinkedList<DisplayTreePanel> tabDisplayPanels= new LinkedList<DisplayTreePanel>();

	private final String treeTypesArray[] = {"Alder", "Apple", "Arborvitae", "Ash", "Beech", 
			"Birch", "Catalpa", "Cedar", "Cherry", "Cypress", "Dogwood", "Elm", "Filbert", 
			"Fir", "Hawthorn", "Holly", "Honeylocust", "Hornbeam", "Horsechestnut", 
			"Ironwood", "Japanese", "Linden", "Locust", "Magnolia", "Maple", "Oak", "Pear", "Pine",
			"Plane tree", "Plum", "Snowbell", "Spruce", "Sweetgum", "Tuliptree", "Walnut"};
	
	private String query;
	
	  private static  LoginInfo loginInfo = null;
	  private  VerticalPanel loginPanel = new VerticalPanel();
	  private Label loginLabel = new Label("Please sign in to your Google Account to access the TreeFinder admin page.");
	  private Anchor signInLink = new Anchor("Sign In");
	  private  Anchor signOutLink = new Anchor("Log Out");
	  
	  private TabLayoutPanel UI_TabPanelLayout;


	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Tree service.
	 */	
	private final TreeServiceAsync treeService = GWT.create(TreeService.class);
	private final ImageServiceAsync imageService = GWT.create(ImageService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		UI_TabPanelLayout = new TabLayoutPanel(1.5, Unit.EM);
		
		FlowPanel myFlowPanel;

		DockLayoutPanel UI_MainDockPanelLayout = new DockLayoutPanel(Unit.PX);
		UI_MainDockPanelLayout.setStyleName("MainDockLayoutPanel");
		RootLayoutPanel.get().add(UI_MainDockPanelLayout);
		//p.addWest(new HTML("navigation"), 128);
		UI_MenuPanel = new VerticalPanel();

		UI_MainDockPanelLayout.addWest(UI_MenuPanel, 320.0);


		//p.addNorth(new HTML("list"), 384);
		//p.add(new HTML("details"));
		//tabPanelLayout.add(p, "High-Tech");

		myFlowPanel = new FlowPanel();
		myFlowPanel.add(new Label("I am a cool list!!!!!"));
		//		HTML ListHtml = new HTML();
		//		ListHtml.setHTML("ListHtml.html");
		//		myFlowPanel.add(new HTML("ListHtml.html"));
		//		myFlowPanel.add(ListHtml);
		//		UI_TabPanelLayout.add(myFlowPanel, "The List");

		createTreeSelectionMenu(UI_MenuPanel, new SendHandler());		
		//myFlowPanel = new FlowPanel();
		//myFlowPanel.add(new Label("I am a cool map!!!!!"));
		//tabPanelLayout.add(myFlowPanel, "The Map");

//createTreeSelectionMenu(UI_MenuPanel, new MyHandler());
Map.getPanel(UI_MenuPanel);
		


		VerticalPanel UploadPanel = new VerticalPanel();
		UploadPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		//UI_MenuPanel.add(UploadPanel);
		UploadPanel.setWidth("315px");

		Label lblUploadACute = new Label("Upload a cute Tree Picture!");
		lblUploadACute.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		UploadPanel.add(lblUploadACute);

		final FileUpload fileUpload = new FileUpload();
		fileUpload.setName("fileUpload");
		UploadPanel.add(fileUpload);
		form = new FormPanel();
		form.setWidget(UploadPanel);
		//form.setEncoding(FormPanel.ENCODING_MULTIPART);
		//form.setMethod(FormPanel.METHOD_POST);
		startNewBlobstoreSession();
		fileUpload.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				System.out.println(fileUpload.getFilename());
				com.google.gwt.user.client.Window.alert(("Yay! You chose a file! Magic-magic-magic!!!!\n"+
						fileUpload.getFilename()));
				//startNewBlobstoreSession();
				form.submit();
				
				
			}});
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event){
				form.reset();
				startNewBlobstoreSession();
				System.out.println("The event results are " + event.getResults());
				com.google.gwt.user.client.Window.alert("The event results are " + event.getResults());
				String key = event.getResults();
				//key = "ahFjcHNjMzEwdGVhbXRyZWVtaXIPCxIJSW1hZ2VCbG9iGGMM";	//Hardcoded test
				imageService.get(key, new AsyncCallback<ImageBlob>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						System.out.println("Naaaaayy!!! =(");
						com.google.gwt.user.client.Window.alert("Naaaaaayyy!! =(");
					}

					@Override
					public void onSuccess(ImageBlob result) {
						System.out.println(result==null);
						System.out.println("new " + result.servingUrl + ", yay!!!");
						com.google.gwt.user.client.Window.alert("new " + result.servingUrl + ", yay!!!");
						pictureListTab.addImageFirst(result.servingUrl);
						//UIPictureSingle uip = new UIPictureSingle(result.servingUrl);
						form.reset();
						//uip.imageblob = result;
						//uip.image.setUrl(result.getServingUrl());
					}
					
				});
			}
		});
		
		//form.add(UploadPanel);
		UI_MenuPanel.add(form);
		

		fileUpload.setWidth("95%");


		DockLayoutPanel UI_ContentPanel = new DockLayoutPanel(Unit.EM);
		UI_MainDockPanelLayout.add(UI_ContentPanel);
		UI_ContentPanel.setSize("100%", "100%");
		//UI_ContentPanel.setSize("100%", "100%");

		DockLayoutPanel UI_FaceBookFooter = new DockLayoutPanel(Unit.PX);
		UI_ContentPanel.addSouth(UI_FaceBookFooter, 5.0);
		UI_FaceBookFooter.setSize("100%", "100%");

		//ScrollPanel faceBookLike = new ScrollPanel("flike.html");
		Frame fframe = new Frame("flike.html");
		UI_FaceBookFooter.addEast(fframe, 400.0);
		fframe.setSize("100%", "100%");
		fframe.getElement().setAttribute("scrolling", "no");
		fframe.getElement().setAttribute("frameBorder", "0");
		fframe.getElement().setAttribute("text-align", "right");

		//TabLayoutPanel UI_TabPanelLayout = new TabLayoutPanel(1.5, Unit.EM);
		UI_TabPanelLayout.setSize("100%", "100%");
		UI_ContentPanel.add(UI_TabPanelLayout);

		//Loading the map and the list tabs...
		tabDisplayPanels.add(new Map(UI_TabPanelLayout));
		tabDisplayPanels.add(new ListUI(UI_TabPanelLayout));

		Frame frame = new Frame("facebook.html");
		frame.setStyleName("facebook-like");
		frame.setHeight("100%");
		frame.setWidth("100%");
		//HTMLPanel myPanel = new HTMLPanel("facebook.html");

		UI_TabPanelLayout.add(frame,"Facebook");

		pictureListTab = new UIPictureList(2);
		UI_TabPanelLayout.add(pictureListTab.asWidget(),"Images");

		//Loading images from the datastore
		imageService.getRecentlyUploaded(new AsyncCallback<LinkedList<ImageBlob>>(){
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Loading old pictures failed.");
				com.google.gwt.user.client.Window.alert("Loading old pictures failed.");
			}

			@Override
			public void onSuccess(LinkedList<ImageBlob> result) {
				for (ImageBlob current: result)
					pictureListTab.addImage(current.servingUrl);
			}
		});
		
		
		//Hiding the loading message...
		DOM.setInnerHTML(RootPanel.get("Loading-Message").getElement(), "");
		
		
	}


	@SuppressWarnings("deprecation")
	private void createTreeSelectionMenu(VerticalPanel parentPanel, 
			SendHandler sendButtonHandler) {
		
		Image logoImage = new Image("Graphics/logo.png");
		UI_MenuPanel.add(logoImage);
		logoImage.setWidth("321");

		VerticalPanel FilterPanel = new VerticalPanel();
		UI_MenuPanel.add(FilterPanel);

		Label FilterLabel = new Label("Select Filter...");
		FilterLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		FilterPanel.add(FilterLabel);
		HorizontalPanel FilterSelection = new HorizontalPanel();
		FilterPanel.add(FilterSelection);

		VerticalPanel HeightSelectionPanel = new VerticalPanel();
		HeightSelectionPanel.setSpacing(2);
		HeightSelectionPanel.setBorderWidth(5);
		FilterSelection.add(HeightSelectionPanel);

		Label HeightLabel = new Label("Height Ranges");
		HeightSelectionPanel.add(HeightLabel);

		heightListBox = new ListBox();
		heightListBox.setMultipleSelect(true);
		HeightSelectionPanel.add(heightListBox);
		heightListBox.addItem("All");
		heightListBox.addItem("0-10\tft.");
		heightListBox.addItem("10-20\tft.");
		heightListBox.addItem("20-30\tft.");
		heightListBox.addItem("30-40\tft.");
		heightListBox.addItem("40-50\tft.");
		heightListBox.addItem("50-60\tft.");
		heightListBox.addItem("60-70\tft.");
		heightListBox.addItem("70-80\tft.");
		heightListBox.addItem("80-90\tft.");
		heightListBox.addItem("90-100\tft.");
		heightListBox.addItem(">100\tft.");
		heightListBox.setName("Height Range");
		heightListBox.setSize("89px", "179px");
		heightListBox.setVisibleItemCount(5);
		heightListBox.setSelectedIndex(0);

		VerticalPanel DiameterSelectionPanel = new VerticalPanel();
		DiameterSelectionPanel.setSpacing(2);
		DiameterSelectionPanel.setBorderWidth(5);
		FilterSelection.add(DiameterSelectionPanel);

		Label DiameterLabel = new Label("Diameter");
		DiameterSelectionPanel.add(DiameterLabel);

		diameterListBox = new ListBox();
		diameterListBox.setMultipleSelect(true);
		DiameterSelectionPanel.add(diameterListBox);
		diameterListBox.addItem("All");
		diameterListBox.addItem("0-10\tin.");
		diameterListBox.addItem("10-20\tin.");
		diameterListBox.addItem("20-30\tin.");
		diameterListBox.addItem("30-40\tin.");
		diameterListBox.addItem("40-50\tin.");
		diameterListBox.addItem("50-60\tin.");
		diameterListBox.setName("Diameter");
		diameterListBox.setSize("89px", "179px");
		diameterListBox.setVisibleItemCount(5);
		diameterListBox.setSelectedIndex(0);

		VerticalPanel TypesPanel = new VerticalPanel();
		TypesPanel.setSpacing(2);
		TypesPanel.setBorderWidth(5);
		FilterSelection.add(TypesPanel);

		Label TypesLabel = new Label("Tree Types");
		TypesPanel.add(TypesLabel);

		typesListBox = new ListBox();
		typesListBox.setMultipleSelect(true);
		TypesPanel.add(typesListBox);
		typesListBox.setName("Height Range");
		typesListBox.setSize("89px", "179px");
		typesListBox.setVisibleItemCount(5);
		typesListBox.addItem("All");
		typesListBox.setSelectedIndex(0);
		//Adding the contend from the list of available tree types
		for (String treeTypeString:treeTypesArray)
			typesListBox.addItem(treeTypeString);	

		HorizontalPanel SortingPanel = new HorizontalPanel();
		SortingPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		SortingPanel.setSpacing(2);
		UI_MenuPanel.add(SortingPanel);
		UI_MenuPanel.setCellHorizontalAlignment(SortingPanel, HasHorizontalAlignment.ALIGN_CENTER);

		Label SortingLabel = new Label("Select the list sorting parameter:");
		SortingPanel.add(SortingLabel);

		sortingComboBox = new ListBox();
		sortingComboBox.addItem("Tree Types");
		sortingComboBox.addItem("Height Ranges");
		sortingComboBox.addItem("Diameter");
		sortingComboBox.setSelectedIndex(0);
		SortingPanel.add(sortingComboBox);

		HorizontalPanel OrderPanel = new HorizontalPanel();
		OrderPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		OrderPanel.setSpacing(2);
		UI_MenuPanel.add(OrderPanel);
		UI_MenuPanel.setCellHorizontalAlignment(OrderPanel, HasHorizontalAlignment.ALIGN_CENTER);
		
		Label OrderLabel = new Label("Select the order direction:");
		OrderPanel.add(OrderLabel);
		
		orderComboBox = new ListBox();
		orderComboBox.addItem("Ascending");
		orderComboBox.addItem("Descending");
		orderComboBox.setSelectedIndex(0);
		OrderPanel.add(orderComboBox);
		
		HorizontalPanel SendButtonPanel = new HorizontalPanel();
		SendButtonPanel.setSpacing(2);
		SendButtonPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		SendButtonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		UI_MenuPanel.add(SendButtonPanel);
		UI_MenuPanel.setCellHorizontalAlignment(SendButtonPanel, HasHorizontalAlignment.ALIGN_RIGHT);

		sendButton = new Button("Hallo", sendButtonHandler);
		sendButton.setText("Send");
		SendButtonPanel.add(sendButton);
		SendButtonPanel.setCellHorizontalAlignment(sendButton, HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel adminButtonPanel = new HorizontalPanel();
		adminButtonPanel.setSpacing(2);
		adminButtonPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		adminButtonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		UI_MenuPanel.add(adminButtonPanel);
		UI_MenuPanel.setCellHorizontalAlignment(adminButtonPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		
		AdminHandler adminButtonHandler = new AdminHandler();
		adminButton = new Button("admin", adminButtonHandler);
		adminButton.setText("Admin");
		adminButtonPanel.add(adminButton);
		adminButtonPanel.setCellHorizontalAlignment(adminButton, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	public VerticalPanel getPanel(){
		return UI_MenuPanel;
	}


	public void filterOptions(){
		//Checking heights
		LinkedList<String> heightRanges = new LinkedList<String>();
		for (int i=0; i<heightListBox.getItemCount(); i++){
			if (heightListBox.isItemSelected(i))
				heightRanges.add(heightListBox.getValue(i));
		}
		//Checking diameters
		LinkedList<String> diameters = new LinkedList<String>();
		for (int i=0; i<diameterListBox.getItemCount(); i++){
			if (diameterListBox.isItemSelected(i))
				diameters.add(diameterListBox.getValue(i));
		}
		//Checking tree types - the index in the ListBox is the same as in treeTypesArray.
		LinkedList<String> types = new LinkedList<String>();
		for (int i=0; i<typesListBox.getItemCount(); i++){
			if (typesListBox.isItemSelected(i))
				types.add(typesListBox.getValue(i));
		}

		query = "SELECT FROM Tree WHERE ";
		
		//Add heightRanges to query
		for(String range : heightRanges){
			if(range.equals("All"))
				break;
			else{
				if(heightRanges.size() == 1)
					query += "(heightRangeID == '" + String.valueOf(range.charAt(0)) + "') ";
				else{
					if(heightRanges.getFirst().equals(range))
						query += "(heightRangeID == '" + String.valueOf(range.charAt(0)) + "' || ";
					else if(heightRanges.getLast().equals(range))
						query += "heightRangeID == '" + String.valueOf(range.charAt(0)) + "') ";
					else
						query += "heightRangeID == '" + String.valueOf(range.charAt(0)) + "' || ";
				}
			}
		}
		//Add diameters to query
		if(!heightRanges.getFirst().equals("All") && !diameters.getFirst().equals("All"))
			query += "&& ";
		for(String diameter : diameters){
			if(diameter.equals("All"))
				break;
			else{
				if(diameters.size() == 1)
					query += "(diameterID == '" + String.valueOf(diameter.charAt(0)) + "') ";
				else{
					if(diameters.getFirst().equals(diameter))
						query += "(diameterID == '" + String.valueOf(diameter.charAt(0)) + "' || ";
					else if(diameters.getLast().equals(diameter))
						query += "diameterID == '" + String.valueOf(diameter.charAt(0)) + "') ";
					else
						query += "diameterID == '" + String.valueOf(diameter.charAt(0)) + "' || ";
				}
			}
		}
		//Add commonNames to query
		if( (!heightRanges.getFirst().equals("All") || !diameters.getFirst().equals("All")) && !types.getFirst().equals("All"))
			query += "&& ";
		for(String type : types){
			if(type.equals("All"))
				break;
			else{
				if(types.size() == 1)
					query += "(treeType == '" + type + "')";
				else{
					if(types.getFirst().equals(type))
						query += "(treeType == '" + type + "' || ";
					else if(types.getLast().equals(type))
						query += "treeType == '" + type + "')";
					else
						query += "treeType == '" + type + "' || ";
				}
			}
		}
		
		if(heightRanges.getFirst().equals("All") && diameters.getFirst().equals("All") && types.getFirst().equals("All"))
			query = "SELECT FROM Tree";
	}

	public void orderOptions(){
		String selected = "";
		for(int i=0; i<sortingComboBox.getItemCount(); i++)
			if (sortingComboBox.isItemSelected(i))
				selected = sortingComboBox.getValue(i);
		
		if(selected.equals("Tree Types"))
			query += " ORDER BY commonName ";
		else if(selected.equals("Height Ranges"))
			query += " ORDER BY heightRangeID ";
		else if(selected.equals("Diameter"))
			query += " ORDER BY diameter ";
		
		for(int i=0; i<orderComboBox.getItemCount(); i++)
			if(orderComboBox.isItemSelected(i))
				selected = orderComboBox.getValue(i);
		
		if(selected.equals("Ascending"))
			query += "ASCENDING";
		else if(selected.equals("Descending"))
			query += "DESCENDING";
	}

	private void refreshAllTabs(List<Tree> newTreeList){
		for (DisplayTreePanel tab: tabDisplayPanels){
			tab.refreshList(newTreeList);
		}

	}

	// Methods for waiting animation
	private void setWait(){
		RootLayoutPanel.get().getElement().getStyle().setCursor(Cursor.WAIT);
		sendButton.getElement().getStyle().setCursor(Cursor.WAIT);
	}
	private void setUnwait(){
		RootLayoutPanel.get().getElement().getStyle().setCursor(Cursor.AUTO);
		sendButton.getElement().getStyle().setCursor(Cursor.POINTER);
	}



	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);

		UI_MenuPanel.add(loginPanel);
		UI_MenuPanel.setCellHorizontalAlignment(loginPanel, HasHorizontalAlignment.ALIGN_LEFT);

	}


	// Returns loginInfo
	public static LoginInfo getLoginInfo(){
		return loginInfo;
	}
	  


	//========================================================================================================
	private class SendHandler implements ClickHandler, KeyUpHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			filterOptions();
			orderOptions();
			sendRequest();
		}

		/**
		 * Fired when the user types in the nameField.
		 */
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				filterOptions();
				orderOptions();
				sendRequest();
			}
		}

		public void sendRequest(){
			setWait();
			System.out.println(query);
			treeService.getTreeList(query, new AsyncCallback<List<Tree>>(){
				public void onFailure(Throwable error){
					System.out.println("Error mother fucker!");
					setUnwait();
				}
				public void onSuccess(List<Tree> trees){
					refreshAllTabs(trees);
					setUnwait();
				}
			});
		}
	}	
	//========================================================================================================
//	private class AdminHandler implements ClickHandler {
//
//		/**
//		 * Fired when the user clicks on the adminButton.
//		 */
//		public void onClick(ClickEvent event) {
//		    // Check login status using login service.
//		    LoginServiceAsync loginService = GWT.create(LoginService.class);
//		    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
//		      public void onFailure(Throwable error) {
//		      }
//
//		      public void onSuccess(LoginInfo result) {
//		    	  loginInfo = result;
//		    	  if(loginInfo.isLoggedIn()) {
//		    		  // Checks if user is an Admin
//		    		  if(loginInfo.isAdmin() == true){
//		    		  tabDisplayPanels.add(new Admin(UI_TabPanelLayout));
//		    		  com.google.gwt.user.client.Window.alert(("You are now logged in as Admin"));
//		    	  }
//
//
//	private void startNewBlobstoreSession() {
//		imageService.getBlobstoreUploadUrl(new AsyncCallback<String>() {
//		    		  // If user is not an admin
//		    	  else  {
//		    		  signOutLink.setHref(loginInfo.getLogoutUrl());
//		    		    loginPanel.add(signOutLink);
//		    		    UI_MenuPanel.add(loginPanel);
//		    		    UI_MenuPanel.setCellHorizontalAlignment(loginPanel, HasHorizontalAlignment.ALIGN_LEFT);
//		    		  com.google.gwt.user.client.Window.alert(("Sorry you are not an Admin"));
//		    	  }
//
//		        } else {
//		            loadLogin();
//		        }}
//		      });
//		}
//		
//	}
//	
//	
//	//==================================================================================================================================
//			
//		    
//			@UiHandler("uploadButton")
//			void onSubmit(ClickEvent e) {
//				form.submit();
//			}
//
//		    
//		    @Override
//			public void onSuccess(String result) {
//				form.setAction(result);
//				form.setEncoding(FormPanel.ENCODING_MULTIPART);
//				form.setMethod(FormPanel.METHOD_POST);
//				System.out.println(result + " was successful.");
//			//	uploadButton.setText("Upload");
//			//	uploadButton.setEnabled(true);
//
//			}
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//	}

	private class AdminHandler implements ClickHandler {

		/**
		 * Fired when the user clicks on the adminButton.
		 */
		public void onClick(ClickEvent event) {
		    // Check login status using login service.
		    LoginServiceAsync loginService = GWT.create(LoginService.class);
		    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
		      public void onFailure(Throwable error) {
		    	  System.out.println("Naaaay! Login RPC failed!!!");
		      }

		      public void onSuccess(LoginInfo result) {
		    	  loginInfo = result;
		    	  System.out.println("Yay! Login RPC succeed!!! " + loginInfo.isLoggedIn());
		    	  if(loginInfo.isLoggedIn()) {
		    		  // Checks if user is an Admin
		    		  if(loginInfo.isAdmin() == true){
		    			  tabDisplayPanels.add(new Admin(UI_TabPanelLayout));
		    			  pictureListTab.setAdminRights(true);
		    			  com.google.gwt.user.client.Window.alert(("You are now logged in as Admin"));
		    	  }
		    		  // If user is not an admin
		    	  else  {
		    		  signOutLink.setHref(loginInfo.getLogoutUrl());
		    		    loginPanel.add(signOutLink);
		    		    UI_MenuPanel.add(loginPanel);
		    		    UI_MenuPanel.setCellHorizontalAlignment(loginPanel, HasHorizontalAlignment.ALIGN_LEFT);
		    		  com.google.gwt.user.client.Window.alert(("Sorry you are not an Admin"));
		    	  }

		        } else {
		            loadLogin();
		        }}
		      });
		}

	}
	
	
	@UiHandler("uploadButton")
	void onSubmit(ClickEvent e) {
		form.submit();
	}

	private void startNewBlobstoreSession() {
		imageService.getBlobstoreUploadUrl(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				form.setAction(result);
				form.setEncoding(FormPanel.ENCODING_MULTIPART);
				form.setMethod(FormPanel.METHOD_POST);
				System.out.println(result + " was successful.");
			//	uploadButton.setText("Upload");
			//	uploadButton.setEnabled(true);

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
}
