package project.test;

import java.io.IOException;

import project.parser.Parser;

public class ParserTest {
	public static void main(String[] args) throws IOException{
		Parser.parseFilesInDir("code/TreeFinder");
		Parser.printClasses();
	}
}
