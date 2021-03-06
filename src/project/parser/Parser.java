package project.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;

import java.io.BufferedReader;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import project.translator.ClassTranslator;
import project.translator.ClassTranslatorImpl;

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
		cobj.setNumberOfLines(cu.getLineNumber(str.length()-1));
		cu.accept(new ASTVisitor(){
			
			public boolean visit(ClassInstanceCreation node){
				cobj.addInvokedClass(node.getType().toString());
				return true;	
			}
			public boolean visit(PackageDeclaration node){
				cobj.setPackage(node.getName().toString());
				return true;
			}
			public boolean visit(ImportDeclaration node){
				cobj.addImports(node.getName().toString());
		       	String[] temp = node.getName().toString().split("\\\\");
	        	String[] simpleimport = temp[temp.length - 1].split("\\.");
	        	cobj.addSimpleImport(simpleimport[0]);
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
				Expression exp = node.getExpression();
				String str;
				if(exp != null){
					str = exp.toString();
					if(Character.isUpperCase(str.charAt(0)))
						cobj.addInvokedClass(str);
				}
				
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
	
	public static void parseCodebase(String str) throws IOException{
		parseFilesInDir(str);
		int[][] relationships = findrelation(classes);
		ClassTranslator translator = new ClassTranslatorImpl();
		translator.translateClass(classes, relationships);
	}
	
	public static void iterateFiles(File[] files) throws IOException{
	    for (File file : files) {
	        if (file.isDirectory()) {
	            iterateFiles(file.listFiles()); // Calls same method again.
	        } else if (file.getName().endsWith(".java")){
	        	String filepath = file.getAbsolutePath();
	        	String[] temp;
	        	if(System.getProperty("os.name").startsWith("Windows"))
	        		temp = filepath.split("\\\\");
	        	else
		        	temp = filepath.split("/");
	        	String[] simpleName = temp[temp.length - 1].split("\\.");
	        	cobj = new ClassObject();
	        	cobj.setSimpleName(simpleName[0]);
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
	
	public static ArrayList<ClassObject> getClasses(){
		return classes;
	}
	
	// returns relations in a metrics form
	// n
	public static int[][] findrelation(ArrayList<ClassObject> clist){
		int[][] returnrelation = new int[clist.size()][clist.size()];
		int k,h;
		// puts zeroes in relation metrics
		for (k = 0 ; k<clist.size(); k++){
			for (h = 0 ; h < clist.size(); h++)
				returnrelation[k][h]= 0;		
		}		
		//fill in metrics
		for (int i=0; i<clist.size(); i++){
			ArrayList<String> invokedClasses = clist.get(i).getInvokedClasses();
			for(String s : invokedClasses){
				for(int j=0; j<clist.size(); j++){
					if(s.equals(clist.get(j).getSimpleName()))
						returnrelation[i][j]++;
				}
			}
		}
		
		return returnrelation;
	}
}
