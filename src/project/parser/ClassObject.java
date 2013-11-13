package project.parser;

import java.util.ArrayList;

public class ClassObject {

	private ArrayList<String> imports;
	private ArrayList<MethodObject> methods;
	private int numberOfLines;
	private String className;
	private String PackageName;
	
	public ClassObject(ArrayList<String> imports, ArrayList<MethodObject> methods, int numberOfLines, String className){
		this.imports = imports;
		this.methods = methods;
		this.numberOfLines = numberOfLines;
		this.setClassName(className);
	}

	public ClassObject(){
		
	}
	public void addImports (String str){
		this.imports.add(str);
		
	}
	public void setPackage(String str){
		this.PackageName=str;
	}
	public ArrayList<String> getImports() {
		return imports;
	}
	public void setImports(ArrayList<String> imports) {
		this.imports = imports;
	}
	public void addMethod(MethodObject mobj){
		this.methods.add(mobj);
	}
	
	public void addInvocation(MethodObject mobj){
		for (MethodObject m : this.methods){
			if (m.getStartNumber()<= mobj.getStartNumber() && mobj.getStartNumber()<=m.getEndNumber()){
				m.addInvocation(mobj);
			}
			
		}
		
		
	}
	
	public ArrayList<MethodObject> getMethods() {
		return methods;
	}
	
	public void setMethods(ArrayList<MethodObject> methods) {
		this.methods = methods;
	}
	
	public int getNumberOfLines() {
		return numberOfLines;
	}
	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
