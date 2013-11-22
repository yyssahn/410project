package project.test;

import java.io.IOException;
import java.util.ArrayList;

import project.parser.ClassObject;
import project.parser.MethodObject;
import project.parser.Parser;

public class ParserTest {
	private static ArrayList<ClassObject> classes;
	private static Parser parser;
	public static void main(String[] args) throws Exception {
		Parser.parseFilesInDir("code");
		classes = Parser.getClasses();
//		printClasses();
		testrelation();
	}
	
	public static void printClasses(){
		for(ClassObject c : classes){
			System.out.println(c.getClassName());
//			System.out.println(c.getSimpleName());
//			System.out.println("  " + c.getNumberOfLines());
			for(String s: c.getInvokedClasses())
				System.out.println("invoked classes : " + s);
			for(String s : c.getImports())
				System.out.println("imports include : " + s);
//			for(MethodObject m : c.getMethods()){
//				System.out.println("  " + m.getName());
//				System.out.println("    " + m.getNumberOfLines());
//			}
//			for(String s : c.getInvokedMethod())
//				System.out.println("        " + s);
			for(String s : c.getSimpleImport())
				System.out.println("simple imports : "+ s);
		};
	}
	
	public static void testrelation(){
		int[][] relation = new int[classes.size()][classes.size()];
		System.out.println("\n  Testing relation \n\n");
		relation = Parser.findrelation(classes);
		int i,j;
		for(i = 0; i<classes.size();i++){
			for(j=0; j<classes.size();j++){
//				System.out.println("Class i : " + classes.get(i).getSimpleName() + " : Class j : " + classes.get(j).getSimpleName());
//				System.out.println("relation[" + i + "][" +  j + "] :"  + relation[i][j]);
				System.out.println("relation " + classes.get(i).getSimpleName() + "-" + classes.get(j).getSimpleName() + ":" + relation[i][j]);
			}
		}
		
		
	}
}
