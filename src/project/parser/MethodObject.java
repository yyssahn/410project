package project.parser;

import java.util.ArrayList;

public class MethodObject {

	private String name;
	private int numberOfLines;
	private int startline;
	private int endline;
	private ArrayList<MethodObject> invocations;
	
	public MethodObject(){
		invocations = new ArrayList<MethodObject>();
	}
	public MethodObject(String name, int numberOfLines){
		this.name = name;
		this.numberOfLines = numberOfLines;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setNumberOfLines(int numberOfLines){
		this.numberOfLines = numberOfLines;
	}
	public int getNumberOfLines(){
		return numberOfLines;
	}
	public void addInvocation(MethodObject mobj){
		this.invocations.add(mobj);
	}
	public ArrayList<MethodObject> getInvocations(){
		return this.invocations;
	}
}
