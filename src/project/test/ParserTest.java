package project.test;

import java.io.IOException;
import java.util.ArrayList;

import project.parser.ClassObject;
import project.parser.MethodObject;
import project.parser.Parser;

public class ParserTest {
	private static ArrayList<ClassObject> classes;
	public static void main(String[] args) throws Exception {
		Parser.parseFilesInDir("code/TreeFinder");
		classes = Parser.getClasses();
		printClasses();
	}
	
	public static void printClasses(){
		for(ClassObject c : classes){
			System.out.println(c.getClassName());
			System.out.println("  " + c.getNumberOfLines());
			for(String s : c.getImports())
				System.out.println("  " + s);
			for(MethodObject m : c.getMethods()){
				System.out.println("  " + m.getName());
				System.out.println("    " + m.getNumberOfLines());
			}
			for(String s : c.getInvokedClasses())
				System.out.println("        " + s);
		}
	}
}
