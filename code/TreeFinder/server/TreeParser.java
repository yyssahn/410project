package ca.ubc.cpsc310.project.TreeFinder.server;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import ca.ubc.cpsc310.project.TreeFinder.client.Tree;

public class TreeParser extends DefaultHandler{
	private Tree tree;
	private ArrayList<Tree> reg;
	private StringBuffer accumulator;
	
	private final String treeTypesArray[] = {"Alder", "Apple", "Arborvitae", "Ash", "Beech", 
			"Birch", "Catalpa", "Cedar", "Cherry", "Cypress", "Dogwood", "Elm", "Filbert", 
			"Fir", "Hawthorn", "Holly", "Honeylocust", "Hornbeam", "Horsechestnut", 
			"Ironwood", "Japanese", "Linden", "Locust", "Magnolia", "Maple", "Oak", "Pear", "Pine",
			"Plane tree", "Plum", "Snowbell", "Spruce", "Sweetgum", "Tuliptree", "Walnut"};
	
	private static final PersistenceManagerFactory pmfInstance = PMF.getPMF();
	
	public TreeParser(){
		reg = new ArrayList<Tree>();
	}
	
	public void startDocument(){
		System.out.print("Start document mother fucker! \n");
		accumulator = new StringBuffer();
	}
	
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts){		
		if(qName.toLowerCase().equals("streettree")){
			tree = new Tree(atts.getValue("TreeID"));
			reg.add(tree);
		}
		
		accumulator.setLength(0);
	}
	
	public void characters(char[] temp, int start, int length){
		accumulator.append(temp, start, length);
	}
	
	public void endElement(String uri, String localName, String qName){		
		if(qName.toLowerCase().equals("commonname")){
			String name = accumulator.toString().trim();
			this.tree.setCommonName(name);
			
			for(String t : treeTypesArray){
				if(name.toLowerCase().contains(t.toLowerCase())){
					this.tree.setTreeType(t);
					break;
				}
			}
		}
		else if(qName.toLowerCase().equals("heightrangeid"))
			this.tree.setHeightRange(accumulator.toString().trim());
		else if(qName.toLowerCase().equals("diameter")){
			this.tree.setDiameter(accumulator.toString().trim());
			
			double diameter = Double.parseDouble(accumulator.toString().trim());
			if(diameter >= 0.0 && diameter < 10.0)
				this.tree.setDiameterID("0");
			else if(diameter >= 10.0 && diameter < 20.0)
				this.tree.setDiameterID("1");
			else if(diameter >= 20.0 && diameter < 30.0)
				this.tree.setDiameterID("2");
			else if(diameter >= 30.0 && diameter < 40.0)
				this.tree.setDiameterID("3");
			else if(diameter >= 40.0 && diameter < 50.0)
				this.tree.setDiameterID("4");
			else if(diameter >= 50.0 && diameter < 60.0)
				this.tree.setDiameterID("5");
		}
		else if(qName.toLowerCase().equals("onstreetblock"))
			this.tree.setOnStreetBlock(accumulator.toString().trim());
		else if(qName.toLowerCase().equals("stdstreet"))
			this.tree.setStdStreet(accumulator.toString().trim());
		else if(qName.toLowerCase().equals("onstreet"))
			this.tree.setOnStreet(accumulator.toString().trim());
	
		accumulator.setLength(0);
	}
	
	public void endDocument(){			
		PersistenceManager pm = getPersistenceManager();
		
	    try {
	      pm.makePersistentAll(reg);
	    } finally {
	      pm.close();
	    }
	}
	
	private PersistenceManager getPersistenceManager() {
		return pmfInstance.getPersistenceManager();
	}
}