package project.parser;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import project.translator.MethodObject;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;

public class Parser {
	private static ArrayList<MethodObject> methods;
	private static ArrayList<String> imports;
	private static ClassObject classObject;

	public static void main(String[] args) throws Exception {
		methods = new ArrayList<MethodObject>();
		imports = new ArrayList<String>();
		//Parserception 
		String file = "code/TreeFinder/server/TreeParser.java";
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream(file);

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }

        List<ImportDeclaration> declarationList = cu.getImports();
        String importName;
        for(int i=0; i<declarationList.size(); i++){
        	importName = declarationList.get(i).getName().toString();
        	imports.add(importName);
        }
        
        // Visit the methods and extract info
        new MethodVisitor().visit(cu, null);
        
        int numberOfLines = cu.getEndLine();
        
        classObject = new ClassObject(imports, methods, numberOfLines);
        
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
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    private static class MethodVisitor extends VoidVisitorAdapter<Object> {
    	MethodObject newMethod;

        @Override
        public void visit(MethodDeclaration n, Object arg) {            
            String methodName = n.getName();
            int methodSize = (n.getEndLine() - n.getBeginLine() + 1);
            newMethod = new MethodObject(methodName, methodSize);
            
            methods.add(newMethod);
        }
    }
}
