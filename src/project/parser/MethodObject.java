package project.parser;

public class MethodObject {

	private String name;
	private int numberOfLines;
	
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
}
