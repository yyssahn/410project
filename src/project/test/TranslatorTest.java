package project.test;
import java.awt.Frame;
import java.util.ArrayList;

import project.parser.MethodObject;
import project.parser.ClassObject;
import project.translator.ClassTranslator;
import project.translator.ClassTranslatorImpl;


public class TranslatorTest {

	public static Frame one;
	
	public static void main(String[] args) {
		
		ArrayList<ClassObject> classes = new ArrayList<ClassObject>();
		
		for(int i = 0; i < 1; i ++){
			ArrayList<String> imports = new ArrayList<String>();
			imports.add("test1");
			imports.add("test2");
			ArrayList<MethodObject> methods = new ArrayList<MethodObject>();
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
			classes.add(new ClassObject(imports, methods, 10, "test.java"));
		}
		for(int i = 0; i < 1; i ++){
			ArrayList<String> imports = new ArrayList<String>();
			imports.add("test1");
			imports.add("test2");
			ArrayList<MethodObject> methods = new ArrayList<MethodObject>();
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
			classes.add(new ClassObject(imports, methods, 15, "test.java"));
		}
		for(int i = 0; i < 1; i ++){
			ArrayList<String> imports = new ArrayList<String>();
			imports.add("test1");
			imports.add("test2");
			ArrayList<MethodObject> methods = new ArrayList<MethodObject>();
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
			classes.add(new ClassObject(imports, methods, 5000, "test.java"));
		}
		for(int i = 0; i < 1; i ++){
			ArrayList<String> imports = new ArrayList<String>();
			imports.add("test1");
			imports.add("test2");
			ArrayList<MethodObject> methods = new ArrayList<MethodObject>();
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
			classes.add(new ClassObject(imports, methods, 20, "test.java"));
		}
		
		for(int i = 0; i < 1; i ++){
			ArrayList<String> imports = new ArrayList<String>();
			imports.add("test1");
			imports.add("test2");
			ArrayList<MethodObject> methods = new ArrayList<MethodObject>();
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
				methods.add(new MethodObject("test", 232));
				methods.add(new MethodObject("anothertest", 434));
				methods.add(new MethodObject("fake", 2));
			classes.add(new ClassObject(imports, methods, 10000, "fake.java"));
		}
		
		ClassTranslator test = new ClassTranslatorImpl();
		test.translateClass(classes);
		
	}

}
