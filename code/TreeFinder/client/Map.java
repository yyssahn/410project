package ca.ubc.cpsc310.project.TreeFinder.client;


import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
//import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
//import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
//import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
//import com.google.gwt.maps.client.overlay.Marker;
//import com.google.gwt.maps.client.overlay.Overlay;

//import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import ca.ubc.cpsc310.project.TreeFinder.client.LatLongFinder;
/* * Entry point classes define <code>onModuleLoad()</code>. */


public class Map extends DisplayTreePanel implements EntryPoint { 

	private List<Tree> treeList = new ArrayList<Tree>();
	private static Tree tree = null;
	//DockLayoutPanel panelPointer;
	public  static MapWidget map;
	private TabLayoutPanel targetPanel;
//	private List<Marker> markerList = new ArrayList<Marker>();
	//private List<LatLng> latlngList = new ArrayList<LatLng>();
//	private Marker marker;
	private static VerticalPanel vertPanel;
	private final static List<List<Tree>> twoList = new ArrayList<List<Tree>>();


	// GWT module entry point method. 
	public void onModuleLoad() {  
		Maps.loadMapsApi("AIzaSyCxhDHekiUR0DI6vV5YFiGOPKnzh7kW65U", "2", false, new Runnable() { 
			public void run() {  
				buildUi();  
			}  
		}); 

	}  
	
	//The constructor
	public Map(TabLayoutPanel targetPanel){
		super(targetPanel);
		this.targetPanel = targetPanel;
		Maps.loadMapsApi("AIzaSyCxhDHekiUR0DI6vV5YFiGOPKnzh7kW65U", "2", false, new Runnable() { 
			public void run() {  
				buildUi();
		//		map.checkResizeAndCenter();
	
			}  
		}); 


		// Makes the refresh button
	mapButton(vertPanel, new ClickHandler(){public void onClick(ClickEvent event) {
		map.checkResizeAndCenter();
	}});
	}
	
	public static void getPanel(VerticalPanel panel){
		vertPanel = panel;
	}
	
	@Override
	public void refreshList(List<Tree> treelist){
		map.checkResizeAndCenter();
		treeList = treelist;
//		System.out.println(treeList);
		map.clearOverlays();
		plotPoints();
//		System.out.println("latlnglist: " + latlngList);
		map.checkResizeAndCenter();

	//	mapPoints(latlngList);
	//	mapMarkers(markerList);
	}
	
	public void buildUi() {   
		// Open a map centered on Vancouver, BC  
		LatLng westPointGrey = LatLng.newInstance(49.265892, -123.199716);  
		map = new MapWidget(westPointGrey, 10);    
		map.setSize("100%", "100%");    
		// Add some controls for the zoom level 
		map.addControl(new LargeMapControl());   
		map.checkResizeAndCenter();
		targetPanel.add(map, "The Map");
	//	targetPanel.addSelectionHandler(handler);
		map.setZoomLevel(15);
	}
		
	
	public void plotPoints(){
		//	map.clearOverlays();
		final Geocoder geocoder;
		if (treeList != null){
			geocoder = new Geocoder();


	//		final List<List<Tree>> twoList = new ArrayList<List<Tree>>();

			for (int i =0; i< treeList.size(); i++){
				int num = 0;
				if (i+5 <= treeList.size())
					num = i+5;
				else  num = treeList.size();
				List<Tree> newList = treeList.subList(i, num);
				twoList.add(newList);
				i = i+4;

			}

			System.out.println("twoList: " + twoList);

				Timer t = new Timer() {
					@Override
					public void run() {
						for (int i = 0; i < twoList.size(); i++){
							final List<Tree> newTreeList = twoList.get(i);
						//	System.out.println("twoList: " + twoList);
						for (int k = 0; k < newTreeList.size(); k++){
							tree = newTreeList.get(k);
							//	System.out.println("Tree: " + tree);
							final String address = tree.getOnStreetBlock() + " " + tree.getStdStreet() +" Vancouver BC";
							//	System.out.println("Address: " + address);

							geocoder.getLatLng(address,  new LatLongFinder(tree));	
						}
						twoList.remove(i);
					}}};
					t.scheduleRepeating(4000);
			}		
	}
	
	public final  MapWidget getMap(){
		return map;
	}
	
	public static Tree getTree(){
		return tree;
	}
	
	
	public static List<List<Tree>> getDoubleList(){
		return twoList;
	}
/*	public final void addPointMap(Marker marker){
		map.addOverlay(marker);
	}*/
	
/*	public void mapPoints(List<LatLng> points){
		
		for (int i = 0; i < latlngList.size(); i++){
			 final Marker marker = new Marker(points.get(i));   
			
				map.addOverlay(marker);    

				marker.addMarkerClickHandler(new MarkerClickHandler() {     
					public void onClick(MarkerClickEvent event) {     
						map.getInfoWindow().open(marker,  new InfoWindowContent(tree.getCommonName()+ tree.treeID));   
					}
		});
		}
	
	}*/
	
	/*public void mapMarkers(List<Marker> points){
		
		for (int i = 0; i < latlngList.size(); i++){
			final Marker  marker =  points.get(i);   
				map.addOverlay(marker);    

				marker.addMarkerClickHandler(new MarkerClickHandler() {     
					public void onClick(MarkerClickEvent event) {     
						map.getInfoWindow().open(marker,  new InfoWindowContent(tree.getCommonName()+ tree.treeID));   
					}
		});
		}
	
	}*/

 
	public void mapButton(VerticalPanel parentPanel, ClickHandler refreshButtonHandler){
		
		VerticalPanel UI_MenuPanel = parentPanel;
		
		HorizontalPanel RefreshButtonPanel = new HorizontalPanel();
		RefreshButtonPanel.setSpacing(2);
		RefreshButtonPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
		RefreshButtonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		UI_MenuPanel.add(RefreshButtonPanel);
		UI_MenuPanel.setCellHorizontalAlignment(RefreshButtonPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		
		Button refreshButton = new Button("Hallo");
		refreshButton.addClickHandler(refreshButtonHandler);
		refreshButton.setText("Refresh");
		RefreshButtonPanel.add(refreshButton);
		RefreshButtonPanel.setCellHorizontalAlignment(refreshButton, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
/*	public static void newPanel2(VerticalPanel uI_MenuPanel) {
		 vertPanel = uI_MenuPanel;
		
	}*/

	class MyHandler2 implements ClickHandler, KeyUpHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			map.checkResizeAndCenter();
		}

		@Override
		public void onKeyUp(KeyUpEvent event) {
			// TODO Auto-generated method stub
		}
		
	}

}
