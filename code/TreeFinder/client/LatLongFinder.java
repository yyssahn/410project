package ca.ubc.cpsc310.project.TreeFinder.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MarkerClickHandler;
//import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.overlay.Marker;


public class LatLongFinder implements LatLngCallback {
	 final Tree tree;
	static List<Marker> markerList = new ArrayList<Marker>();
	static Marker marker;
//	private static List<Tree> treeList = new ArrayList<Tree>();
	private  Marker marker2;
	private  LatLng point;



	public LatLongFinder(Tree tree2){
		tree = tree2;
		point = null;
	
	}
	@Override
	public void onFailure() {
		System.out.println("Failed: Tree" + tree + " not found");
		List<Tree> singleTreeList = new ArrayList<Tree>();
		singleTreeList.add(tree);
		Map.getDoubleList().add(singleTreeList);
		
		
	}
	@Override
	public void onSuccess(LatLng point2) {
		// TODO Auto-generated method stub
		point = point2;
		  marker2 = makeMarker(point2);
	//	  MapWidget map = Map.map;
	//	  System.out.println("Marker: " + marker2);
			System.out.println("Success: Tree" + tree + " " + point2);
	
			marker2.addMarkerClickHandler(new MarkerClickHandler() {     
				public void onClick(MarkerClickEvent event) {     
					Map.map.getInfoWindow().open(marker2,  new InfoWindowContent(tree.getCommonName()+ tree.getTreeID()));   
				} 
			});
		  Map.map.addOverlay(marker2);

//			addPointMap(marker2);

	}	
	

	
			 
	public Marker makeMarker(LatLng point){
		final Marker marker =  new Marker(point);
		return marker;
	}
	
	
	public  LatLng getPoint(){
		return point;
	}
	
/*	public static List<Marker> getMarkerList(){
		return markerList;
	}*/
	
	public  Marker getMarker(){
		return marker2;
	}
}

	
/*	public static Marker getLats(String address){
	Geocoder geocoder;
//	if (treeList != null){
		geocoder = new Geocoder();
		
//		LatLngCallback treePoint = new LatLngCallback() {
//			(LatLongFinder.onFailure(), LatLongFinder.onSuccess(point));}

//		LatLongFinder treepoint = new LatLongFinder();
		LatLngCallback treePoint = new LatLngCallback() { 
			public void onFailure() {  
				System.out.println("Address " + tree.getOnStreetBlock() + tree.getOnStreet() + "not found");
			}
			public void onSuccess(LatLng point) { 
				System.out.println("Point: " + point);
//				map.setCenter(point, 13);     
				  marker2 = new Marker(point);   



				marker.addMarkerClickHandler(new MarkerClickHandler() {     
					public void onClick(MarkerClickEvent event) {     
						map.getInfoWindow().open(marker,  new InfoWindowContent(tree.getCommonName()+ tree.getTreeID()));   
					} 
				});
				
//				latlngList.add(point);
//				System.out.println("LatLng first list: " + latlngList);
				markerList.add(marker);

//				System.out.println("LatLng first list: " + latlngList);
	

			} 
		};
		geocoder.getLatLng(address , treePoint);
		return marker2;
	}

//	}
}*/


