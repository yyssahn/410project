package project.test;

import java.util.ArrayList;

import project.parser.ClassObject;
import project.parser.Parser;

public class ParserTest {
	private static ArrayList<ClassObject> classes;
	public static void main(String[] args) throws Exception {
		Parser.parseFilesInDir("src");
		classes = Parser.getClasses();
		printClasses();
	}
	
	public static void printClasses(){
		for(ClassObject c : classes){
			System.out.println(c.getClassName());
			System.out.println("  " + c.getNumberOfLines());
			for(String s: c.getInvokedClasses())
				System.out.println("invoked classes : " + s);
			for(String s : c.getImports())
				System.out.println("imports include : " + s);
			for(String s : c.getSimpleImport())
				System.out.println("simple imports : "+ s);
		};
	}
}
