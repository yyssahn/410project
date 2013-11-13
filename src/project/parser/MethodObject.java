package project.parser;

import java.util.ArrayList;

public class MethodObject {

	private String name;
	private int numberOfLines;
	private int startline;
	private int endline;
	private ArrayList<MethodObject> invocations;
	

	public MethodObject(String name, int numberOfLines){
		this.name = name;
		this.numberOfLines = numberOfLines;
	}
	public MethodObject(){
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setStartLine(int line){
		this.startline=line;
	}
	public void setEndLine(int line){
		this.endline=line;
	}
	
	public String getName(){
		return name;
	}
	
	public void setNumberOfLines(int numberOfLines){
		this.numberOfLines = numberOfLines;
	}
	public int getStartNumber(){
		return this.startline;
	}
	public int getEndNumber(){
		return this.endline;
	}
	
	public int getNumberOfLines(){
		return numberOfLines;
	}
	public ArrayList<MethodObject> getInvocations(){
		return this.invocations;
	}
	public void addInvocation(MethodObject mobj){
		this.invocations.add(mobj);
	}
	
}
