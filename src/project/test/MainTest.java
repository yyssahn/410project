package project.test;
import java.awt.Frame;
import java.util.ArrayList;

import project.parser.ClassObject;
import project.parser.MethodObject;
import project.parser.Parser;

public class MainTest {
	public static void main(String[] args) throws Exception {		
		Parser.parseCodebase("code");
	}
}
