package project.parser;

import java.util.ArrayList;

public class ClassObject {

	private ArrayList<String> imports;
	private ArrayList<MethodObject> methods;
	private ArrayList<String> invokedClasses;
	private int numberOfLines;
	private String className;
	private String simpleName;
	private String packageName;
	
	public ClassObject(){
		imports = new ArrayList<String>();
		methods = new ArrayList<MethodObject>();
		invokedClasses = new ArrayList<String>();
	}
	public ClassObject(ArrayList<String> imports, ArrayList<MethodObject> methods, int numberOfLines, String className){
		this.imports = imports;
		this.methods = methods;
		this.numberOfLines = numberOfLines;
		this.setClassName(className);
	}

	public void setPackage(String str){
		this.packageName = str;
	}
	public String getPackage(){
		return this.packageName;
	}
	public void addImports (String str){
		this.imports.add(str);
	}
	public ArrayList<String> getImports() {
		return imports;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassName() {
		return className;
	}
	public void setSimpleName(String name){
		this.simpleName = name;
	}
	public String getSimpleName(){
		return simpleName;
	}
	public void addMethod(MethodObject mobj){
		this.methods.add(mobj);
	}
	public ArrayList<MethodObject> getMethods() {
		return methods;
	}
	public void addInvokedClass(String className){
		this.invokedClasses.add(className);
	}
	public ArrayList<String> getInvokedClasses(){
		return invokedClasses;
	}
	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}
	public int getNumberOfLines() {
		return numberOfLines;
	}
}
