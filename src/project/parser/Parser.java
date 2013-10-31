package project.parser;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import project.shared.ClassObject;
import project.shared.MethodObject;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;

public class Parser {	
	private static ClassObject classObject;
	
	public Parser(){
		
		
	}
	
	
	public static ClassObject parse(String[] args) throws Exception {
		ArrayList<MethodObject> methods = new ArrayList<MethodObject>();
		ArrayList<String> imports = new ArrayList<String>();
		//Parserception 
		String file = "code/TreeFinder/server/TreeParser.java";
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream(file);
        ClassObject cobj = new ClassObject();
        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        cobj.setClass(file);
        List<ImportDeclaration> importList = cu.getImports();
        for(int i=0; i<importList.size(); i++){
        
        	cobj.addImport(importList.get(i));
        }
        
        // Visit the methods and extract info
        new MethodVisitor(cobj).visit(cu, null);
        cobj.setNumberOfLines(cu.getEndLine());
        cobj.setPackageName(cu.getPackage().getName().toString());
        return cobj;
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    private static class MethodVisitor extends VoidVisitorAdapter<Object> {
    	ClassObject classObj;
    	MethodObject newMethod;

    	public MethodVisitor (ClassObject cobj){
    		this.classObj=cobj;
    	}
        @Override
        public void visit(MethodDeclaration n, Object arg) {            
            String methodName = n.getName();
            int methodSize = (n.getEndLine() - n.getBeginLine() + 1);
            newMethod = new MethodObject(methodName, methodSize);
           this.classObj.addMethods(newMethod);
        }
    }
}
