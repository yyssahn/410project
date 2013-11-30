package project.test;
import project.parser.Parser;

public class MainTest {
	public static void main(String[] args) throws Exception {		
		Parser.parseCodebase("src");
		Parser.parseCodebase("code");
	}
}
