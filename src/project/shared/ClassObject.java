package project.shared;
import japa.parser.ast.ImportDeclaration;

import java.util.ArrayList;



public class ClassObject {
	private String name;
	private String packageName;
	private ArrayList<String> imports;
	private ArrayList<MethodObject> methods;
	private int numberOfLines;
	
	
	public ClassObject(String name, String pName, ArrayList<String> imports, ArrayList<MethodObject> methods, int numberOfLines){
		this.name = name;
		this.packageName = pName;
		this.imports = imports;
		this.methods = methods;
		this.numberOfLines = numberOfLines;
	}
	

	public ClassObject(){
		
	}
	
	
	public ArrayList<String> getImports() {
		return imports;
	}
	public void addImport(ImportDeclaration imports){
		this.imports.add(imports.getName().toString());
		
	}
	public void setPackageName(String name){
		this.packageName= name;
		
	}
	public void setClass(String name){
		this.name= name;
		
	}
	public void setImports(ArrayList<String> imports) {
		this.imports = imports;
	}
	
	public ArrayList<MethodObject> getMethods() {
		return methods;
	}
	public void addMethods(MethodObject method){
		this.methods.add(method);
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
	
	
}
