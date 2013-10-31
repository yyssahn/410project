package ca.ubc.cpsc310.project.TreeFinder.client;

import java.util.List;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Admin extends DisplayTreePanel{
	
	private VerticalPanel verticalPanel;
	  private  Anchor signOutLink = new Anchor("Log Out");
	  private  VerticalPanel loginPanel = new VerticalPanel();

	
	public void OnModuleLoad(){
		refreshList(null);
	}
	//The constructor
	public Admin(TabLayoutPanel targetPanel){
		super(targetPanel);
		//		this.targetPanel = targetPanel;

		verticalPanel = new VerticalPanel();
		Label welcome = new Label("Welcome to the Admin Page");
		verticalPanel.add(welcome);
		
		LoginInfo loginInfo = TreeFinder.getLoginInfo();
		String logout = loginInfo.getLogoutUrl();
		signOutLink.setHref(logout);
	    loginPanel.add(signOutLink);
	    verticalPanel.add(loginPanel);

		targetPanel.add(verticalPanel, "Admin");
	}


	
	@Override
	public void refreshList(List<Tree> treelist) {
		// TODO Auto-generated method stub
		
	}
	

}
