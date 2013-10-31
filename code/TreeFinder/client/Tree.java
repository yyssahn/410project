package ca.ubc.cpsc310.project.TreeFinder.client;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@SuppressWarnings("serial")
@PersistenceCapable
public class Tree implements Serializable{
	@Persistent
	@PrimaryKey
	private String treeID;
	@Persistent
	private String commonName;
	@Persistent
	private String heightRangeID;
	@Persistent
	private String diameterID;
	@Persistent
	private String diameter;
	@Persistent
	private String onStreetBlock;
	@Persistent
	private String stdStreet;
	@Persistent
	private String onStreet;
	@Persistent
	private String treeType;
	
	public Tree(){
		
	}
	
	public Tree(String treeID){
		this.treeID = treeID;
	}
	
	public String getTreeID(){
		return this.treeID;
	}

	public String getCommonName(){
		return this.commonName;
	}
	
	public String getDiameterID(){
		return this.diameterID;
	}
	public String getDiameter(){
		return this.diameter;
	}
	
	public String getHeightRange(){
		return this.heightRangeID;
	}
	
	public String getStdStreet(){
		return this.stdStreet;
	}
	
	public String getOnStreet(){
		return this.onStreet;
	}
	
	public String getOnStreetBlock(){
		return this.onStreetBlock;
	}
	
	public String getTreeType(){
		return this.treeType;
	}

	public void setCommonName(String name){
		this.commonName = name;
	}
	
	public void setOnStreet(String str){
		this.onStreet = str;
	}
	
	public void setStdStreet(String str){
		this.stdStreet = str;
	}
	
	public void setDiameterID(String i){
		this.diameterID = i;
	}
	
	public void setDiameter(String i){
		this.diameter = i;
	}
	
	public void setHeightRange(String i){
		this.heightRangeID = i;
	}
	
	public void setOnStreetBlock(String i){
		this.onStreetBlock = i;
	}
	

	public void setTreeType(String t){
		this.treeType = t;
	}

	public String toString(){
		return this.commonName + " " + this.treeID + "address: " + this.getOnStreetBlock()+  " " +
	 this.getStdStreet();
		

	}
}
