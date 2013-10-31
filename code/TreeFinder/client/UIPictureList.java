package ca.ubc.cpsc310.project.TreeFinder.client;

import java.util.LinkedList;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UIPictureList {
private ScrollPanel scrollingPanel;
private VerticalPanel[] verticals;
private int numberOfColumns = 2;
private LinkedList<UIPictureSingle> listOfPictures = new LinkedList<UIPictureSingle>();
private boolean adminRights = false;
	
//TODO: Causes an imbalance for large lists with comments.

	public Widget asWidget(){
		return scrollingPanel;
	}
	
	/**
	 * The default constructor.
	 * @param numberOfColumns The number of columns that are needed.
	 */
	public UIPictureList(int numberOfColumns){
		if (numberOfColumns<0)
			numberOfColumns = 2;
		this.numberOfColumns = numberOfColumns;
		
		initializeViewPanels();
	
		//addRandomStuff();	//Debug
	}

	
	/**
	 * A method for changing whether the user has administrator rights.
	 * @param adminRights
	 */
	public void setAdminRights(boolean adminRights) {
		if (this.adminRights!=adminRights)
		{ //need to toggle
			this.adminRights = adminRights;
			for (UIPictureSingle current: listOfPictures)
				current.setAdminRights(adminRights);
		}	
	}
	
	/**
	 * Creates the panels.
	 */
	private void initializeViewPanels() {
		HorizontalPanel myHorizontalPanel = new HorizontalPanel();
		myHorizontalPanel.setSpacing(2);
		scrollingPanel = new ScrollPanel(myHorizontalPanel);
		
		//Creating vertical panels
		verticals = new VerticalPanel[numberOfColumns];
		for (int i = 0; i < numberOfColumns; i++){
			verticals[i] = new VerticalPanel();
			verticals[i].setSpacing(2);
			//verticals[i].setSize("50%", "100%");
			myHorizontalPanel.add(verticals[i]);
		}
	}
	
	 /**
	 *  Creates and adds a picture to the leftmost top of a Panel by URL.
	 *  Should be useful for adding an uploaded picture.
	 * @param filename The URL of the picture.
	 * @return UIPictureSingle created.
	 */
	public UIPictureSingle addImageFirst(String filename)
	{
		UIPictureSingle a = new UIPictureSingle(filename);
		listOfPictures.add(a);
		verticals[0].insert(a.asWidget(), 0);
		a.setAdminRights(true);
		return a;
	}
	
	/**
	 * Creates and adds a picture to the end of a Panel by URL.
	 * @param filename The URL of the picture.
	 * @return UIPictureSingle created.
	 */
	public UIPictureSingle addImage(String filename)
	{
		UIPictureSingle a = new UIPictureSingle(filename);
		listOfPictures.add(a);
		verticals[(listOfPictures.size()-1)%numberOfColumns].add(a.asWidget());
		a.setAdminRights(adminRights);
		return a;
	}
	
	/**
	 * Creates images according to a list of names and adds them to the panel. 
	 * @param filename The list of filenames.
	 * @return The list of the UIPictureSingles created.
	 */
	public UIPictureSingle[] addImages(String filename[])
	{
		UIPictureSingle[] a = new UIPictureSingle [filename.length];
		for (int i = 0; i<filename.length; i++) {
			a[i] = new UIPictureSingle(filename[i]);
			listOfPictures.add(a[i]);
			verticals[listOfPictures.size()%numberOfColumns].add(a[i].asWidget());
			a[i].setAdminRights(adminRights);}
		return a;
	}
	
	/**
	 * Adds some random pictures - for debug.
	 */
	public void addRandomStuff(){
		for (int i = 0; i < 6; i++){
			UIPictureSingle a = addImage("Images/"+(i%2+1)+".png");
			if (i%2==0)
				a.setAdminRights(true);
		}
	}
	
	/**
	 * Creates new panels instead of the old ones.
	 */
	public void recreateThePanels(){
		initializeViewPanels();
		for (int i = 0; i < listOfPictures.size();i++)
			verticals[i%numberOfColumns].add(listOfPictures.get(i).asWidget());
	}
}
