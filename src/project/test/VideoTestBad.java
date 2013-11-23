package project.test;

import java.util.ArrayList;

import project.parser.ClassObject;
import project.parser.MethodObject;
import project.translator.ClassTranslatorImpl;

public class VideoTestBad {

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
			a.setNumberOfLines(20);
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
			a.setSimpleName("four.java");
			a.setNumberOfLines(35);
			a.setPackage("main.fake.client");
			for (int i = 0; i < 1; i++)
				a.addImports("dummyImport");
			for (int i = 0; i < 1; i++)
				a.addMethod(new MethodObject("dummyMethod", 5));
			toPass.add(a);
			}
		
		
		
		{
			ClassObject a = new ClassObject();
			a.setSimpleName("one.java");
			a.setNumberOfLines(130);
			a.setPackage("main.fake.server");
			for (int i = 0; i < 11; i++)
				a.addImports("dummyImport");
			for (int i = 0; i < 7; i++)
				a.addMethod(new MethodObject("dummyMethod", 5));
			toPass.add(a);
			}
		
		int [][] relations = {	{0,0,3,5,4},
								{0,0,0,0,0},
								{5,0,0,0,10},
								{0,0,8,0,0},
								{0,0,0,0,0}	};
		
		myCT.translateClass(toPass,relations);
		}
}
