package project.test;

import java.util.ArrayList;

import project.parser.ClassObject;
import project.parser.MethodObject;
import project.translator.ClassTranslatorImpl;

public class VideoTestGood {

	public static void main (String args []){
		ClassTranslatorImpl myCT = new ClassTranslatorImpl();
		ArrayList<ClassObject> toPass = new ArrayList<ClassObject>();
		
		{
		ClassObject a = new ClassObject();
		a.setSimpleName("one.java");
		a.setNumberOfLines(120);
		a.setPackage("main.fake.client");
		for (int i = 0; i < 6; i++)
			a.addImports("dummyImport");
		for (int i = 0; i < 10; i++)
			a.addMethod(new MethodObject("dummyMethod", 5));
		toPass.add(a);
		}
		
		{
			ClassObject a = new ClassObject();
			a.setSimpleName("two.java");
			a.setNumberOfLines(60);
			a.setPackage("main.fake.client");
			for (int i = 0; i < 4; i++)
				a.addImports("dummyImport");
			for (int i = 0; i < 8; i++)
				a.addMethod(new MethodObject("dummyMethod", 5));
			toPass.add(a);
			}
		
		{
			ClassObject a = new ClassObject();
			a.setSimpleName("three.java");
			a.setNumberOfLines(40);
			a.setPackage("main.fake.client");
			for (int i = 0; i < 4; i++)
				a.addImports("dummyImport");
			for (int i = 0; i < 3; i++)
				a.addMethod(new MethodObject("dummyMethod", 5));
			toPass.add(a);
			}
		
		{
			ClassObject a = new ClassObject();
			a.setSimpleName("one.java");
			a.setNumberOfLines(90);
			a.setPackage("main.fake.server");
			for (int i = 0; i < 3; i++)
				a.addImports("dummyImport");
			for (int i = 0; i < 7; i++)
				a.addMethod(new MethodObject("dummyMethod", 5));
			toPass.add(a);
			}
		
		
		
		{
			ClassObject a = new ClassObject();
			a.setSimpleName("two.java");
			a.setNumberOfLines(45);
			a.setPackage("main.fake.server");
			for (int i = 0; i < 2; i++)
				a.addImports("dummyImport");
			for (int i = 0; i < 4; i++)
				a.addMethod(new MethodObject("dummyMethod", 5));
			toPass.add(a);
			}
		
		int [][] relations = 	{{0, 2, 3, 5, 0},
								{1, 0, 1, 0, 0},
								{0, 0, 0, 0, 0},
								{2, 0, 0, 0, 4},
								{0, 0, 0, 3, 0}	};
		
		myCT.translateClass(toPass,relations);
		}
}
