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
	private static ArrayList<MethodObject> methods;
	private static ArrayList<String> imports;
	private static ArrayList<ClassObject> classes;
	private static ClassObject classObject;

	//Mike: Rename it and use ParserTest?
	public static void main(String[] args) throws Exception {
		classes = new ArrayList<ClassObject>();
		
		
		//Mike: simple loop added to account several files
		//Parserception        
		for (String file : args)
		{
			methods = new ArrayList<MethodObject>();
			imports = new ArrayList<String>();
			 // creates an input stream for the file to be parsed
			FileInputStream in = new FileInputStream(file);

			CompilationUnit cu;
			try {
				// parse the file
	//			cu = JavaParser.parse(in);
			} finally {
				in.close();
			}

			//String className = "Dummy name"; // Doesn't seem to be a method for getting class name.  Have to implement.
			String className = file.substring(file.lastIndexOf('/'));	//Mike: Good enough for now
			System.out.println(className);
		//	List<ImportDeclaration> declarationList = cu.getImports();
		//	PackageDeclaration Package = cu.getPackage();
//			String importName;
//			for(int i=0; i<declarationList.size(); i++){
//				importName = declarationList.get(i).getName().toString();
//				imports.add(importName);
//			}
//			System.out.println(Package.getName().toString());
//			// Visit the methods and extract info
//			new MethodVisitor().visit(cu, null);
//
//			int numberOfLines = cu.getEndLine();

//			classObject = new ClassObject(imports, methods, numberOfLines, className);

			//Testing
			System.out.println("Lines in class: " + classObject.getNumberOfLines() + "\n");

			ArrayList<String> testImports = classObject.getImports();
			System.out.println("Printing imports:");
			for(int i=0; i<testImports.size(); i++){
				System.out.println("   " + testImports.get(i));
			}

			ArrayList<MethodObject> testMethods = classObject.getMethods();
			System.out.println("Printing method objects");
			for(int i=0; i<testMethods.size(); i++){
				System.out.println("   Method name: " + testMethods.get(i).getName());
				System.out.println("   Method size: " + testMethods.get(i).getNumberOfLines() + " lines \n");
			}

			classes.add(classObject);
		}
		
        ClassTranslator test = new ClassTranslatorImpl();
		test.translateClass(classes);
    }
	public static ClassObject parse(String str){
		final ClassObject cobj;
		cobj = new ClassObject();
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		cu.accept(new ASTVisitor(){

			public boolean visit(MethodDeclaration node){
				MethodObject mobj = new MethodObject();
				mobj.setName(node.getName().toString());
				int length = 1 + cu.getLineNumber(node.getLength() + node.getStartPosition())-cu.getLineNumber(node.getStartPosition());
				mobj.setNumberOfLines(length);
				mobj.setStartLine(cu.getLineNumber(node.getStartPosition()));
				mobj.setEndLine(cu.getLineNumber(node.getLength() + node.getStartPosition()));
				cobj.addMethod(mobj);
				return true;
			}
			public boolean visit(MethodInvocation node){
				MethodObject mobj = new MethodObject();
				mobj.setName(node.getName().toString());
				
				mobj.setStartLine(cu.getLineNumber(node.getStartPosition()));
				cobj.addInvocation(mobj);
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

			
			
		});
		return cobj;
	}
	//loop directory to get file list
	public static ArrayList<ClassObject> ParseFilesInDir() throws IOException{
		File dirs = new File(".");
		String dirPath = dirs.getCanonicalPath() + File.separator+"code"+File.separator + "TreeFinder" + File.separator;
		ArrayList<ClassObject> ClassList = new ArrayList<ClassObject>();
		
		File root = new File(dirPath);
		//System.out.println(rootDir.listFiles());
		File[] files = root.listFiles ( );
		String filePath = null;
		 for (File f : files ) {
			 File[] filetwo = f.listFiles();
			 for (File g : filetwo){
				 filePath= g.getAbsolutePath();
				 if (g.isFile()){
					 ClassObject cobj = parse(readFileToString(filePath));
					 ClassList.add(cobj);
				 }
			 }
			 
		 }
		 return ClassList;
	}
	//read file content into a string
		public static String readFileToString(String filePath) throws IOException {
			StringBuilder fileData = new StringBuilder(1000);
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
	 
			char[] buf = new char[10];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				System.out.println(numRead);
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
				buf = new char[1024];
			}
	 
			reader.close();
	 
			return  fileData.toString();	
		}
	
}
