package project.test;

import java.io.IOException;
import java.util.ArrayList;

import project.parser.ClassObject;
import project.parser.MethodObject;
import project.parser.Parser;

public class ParserTest {
	private static ArrayList<ClassObject> classes;
	public static void main(String[] args) throws Exception {
		Parser.parseFilesInDir("src");
		classes = Parser.getClasses();
	//	printClasses();
		testrelation();
	}
	
	public static void printClasses(){
		for(ClassObject c : classes){
			System.out.println(c.getClassName());
			System.out.println(c.getSimpleName());
			System.out.println("  " + c.getNumberOfLines());
			for(String s : c.getImports())
				System.out.println("  " + s);
			for(MethodObject m : c.getMethods()){
				System.out.println("  " + m.getName());
				System.out.println("    " + m.getNumberOfLines());
			}
			for(String s : c.getInvokedMethod())
				System.out.println("        " + s);
		}
	}
	
	public static void testrelation(){
		int[][] relation = new int[classes.size()][classes.size()];
		System.out.println("\n  Testing relation \n\n");
		relation = Parser.findrelation(classes);
		int i,j;
		for(i = 0; i<classes.size();i++){
			for(j=0; j<classes.size();j++){
				System.out.println("relation[" + i + "][" +  j + "] :"  + relation[i][j]);
				
			}
		}
		
		
	}
}
