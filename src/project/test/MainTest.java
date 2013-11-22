package project.test;
import java.awt.Frame;
import java.util.ArrayList;

import project.parser.ClassObject;
import project.parser.MethodObject;
import project.parser.Parser;

public class MainTest {

	public static Frame frameOne;
	private static ArrayList<ClassObject> classes;

	public static void main(String[] args) throws Exception {
		String [] fileList = {"code/TreeFinder/server/TreeParser.java", 
				"code/TreeFinder/server/TreeServiceImpl.java", 
				"code/TreeFinder/server/imagedao.java", 
				"code/TreeFinder/server/PMF.java", 
				"code/TreeFinder/server/XMLParser.java", 
				"code/TreeFinder/server/TreeServiceImpl.java", 
				"code/TreeFinder/server/imagedao.java", 
				"code/TreeFinder/server/PMF.java", 
				"code/TreeFinder/server/XMLParser.java", 
				"code/TreeFinder/server/TreeServiceImpl.java", 
				"code/TreeFinder/server/imagedao.java", 
				"code/TreeFinder/server/PMF.java", 
				"code/TreeFinder/server/XMLParser.java", 
				"code/TreeFinder/server/TreeServiceImpl.java", 
				"code/TreeFinder/server/imagedao.java", 
				"code/TreeFinder/server/PMF.java", 
				"code/TreeFinder/server/XMLParser.java"};
//		Parser.main(fileList);
		
		Parser.parseFilesInDir("src");
		classes = Parser.getClasses();
	}
}
