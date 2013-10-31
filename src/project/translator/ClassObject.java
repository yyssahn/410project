package project.translator;
import java.util.ArrayList;


public class ClassObject {

	private ArrayList<String> imports;
	private ArrayList<MethodObject> methods;
	private int numberOfLines;
	private String className;
	
	public ClassObject(ArrayList<String> imports, ArrayList<MethodObject> methods, int numberOfLines, String className){
		this.imports = imports;
		this.methods = methods;
		this.numberOfLines = numberOfLines;
		this.setClassName(className);
	}
	
	public ArrayList<String> getImports() {
		return imports;
	}
	public void setImports(ArrayList<String> imports) {
		this.imports = imports;
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
