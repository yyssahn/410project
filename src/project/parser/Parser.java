package project.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

import java.io.BufferedReader;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;


import project.translator.ClassTranslator;
import project.translator.ClassTranslatorImpl;
import japa.parser.JavaParser;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;

public class Parser {
	private static ArrayList<ClassObject> classes;
	private static ClassObject cobj;
	private static MethodObject mobj;
	private static CompilationUnit cu;

	public static void parse(String str){
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		cu = (CompilationUnit) parser.createAST(null);
		cu.accept(new ASTVisitor(){
			
			public boolean visit(ClassInstanceCreation node){
				return true;	
			}
			public boolean visit(PackageDeclaration node){
				cobj.setPackage(node.getName().toString());
				return true;
			}
			public boolean visit(ImportDeclaration node){
				cobj.addImports(node.getName().toString());
				return true;
			}
			public boolean visit(MethodDeclaration node){
				int length = 1 + cu.getLineNumber(node.getLength() + node.getStartPosition()) - cu.getLineNumber(node.getStartPosition());
				mobj = new MethodObject();
				mobj.setName(node.getName().toString());
				mobj.setNumberOfLines(length);
				cobj.addMethod(mobj);
				return true;
			}
			public boolean visit(MethodInvocation node){
				System.out.println(cobj.getClassName());
				System.out.println(node.getName().toString());
//				MethodObject mobj = new MethodObject();
//				mobj.setName(node.getName().toString());
//				
//				mobj.setStartLine(cu.getLineNumber(node.getStartPosition()));
//				cobj.addInvocation(mobj);
				return true;
			}
		});
	}
	
	//input with string dirpath
	public static void parseFilesInDir(String str) throws IOException{
		classes = new ArrayList<ClassObject>();
		File root = new File(str);
		File[] files = root.listFiles ( );
		iterateFiles(files);
	}
	
	public static void iterateFiles(File[] files) throws IOException{
	    for (File file : files) {
	        if (file.isDirectory()) {
	            iterateFiles(file.listFiles()); // Calls same method again.
	        } else {
	        	String filepath = file.getAbsolutePath();
	        	cobj = new ClassObject();
	        	cobj.setClassName(filepath);
	        	parse(readFileToString(filepath));
	        	classes.add(cobj);
	        }
	    }
	}
	
	//read file content into a string
	public static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
 
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
 
		reader.close();
 
		return  fileData.toString();	
	}
	
	// To do
	public int[][] findrelation(ArrayList<ClassObject> clist){
		int[][] relation = new int[clist.size()][clist.size()];
		
		return relation;
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
		}
	}
}
