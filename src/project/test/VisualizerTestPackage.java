package project.test;


import java.awt.Frame;
import java.util.ArrayList;

import project.flowerVisualizer.Flower;
import project.flowerVisualizer.FlowerComposite;
import project.flowerVisualizer.FlowerRelation;
import project.flowerVisualizer.FlowerVisualizer;
import project.flowerVisualizer.FlowerVisualizerImpl;
import project.flowerVisualizer.Palette;


public class VisualizerTestPackage {

	public static Frame one;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Flower myFirstFlower = new Flower(0, 400, 200, 10, 50, 50, 6, true, "Flower.java");
		Flower mySecondFlower = new Flower(0, 400, 100, 10, 25, 25, 15, true, "BlahblahblahFlower.java");
		myFirstFlower.setnumberOfRoots(6);
		
		FlowerComposite one = new FlowerComposite();
		one.add(myFirstFlower);
		one.add(mySecondFlower);
		one.setName("Hallo");
		one.setPrimaryColor(Palette.setTransparency(Palette.PETER_RIVER, 100));
		
		FlowerComposite two = new FlowerComposite();
		two.add(one);
		two.add(one);
		two.add(mySecondFlower);
		two.add(myFirstFlower);
		two.setPrimaryColor(Palette.setTransparency(Palette.ALIZARIN, 100));
		two.setName("Big set of Flowers!");
		
		ArrayList<FlowerRelation> relations = new ArrayList<FlowerRelation>();
		relations.add(new FlowerRelation(0, 1, 5));
		relations.add(new FlowerRelation(0, 2, 4));
		relations.add(new FlowerRelation(3, 2, 1));
		
		FlowerVisualizer myFV = new FlowerVisualizerImpl();
	
		myFV.drawFlowers(two, 640, 480, relations);
		myFV.requestScale(40, 80, 350, "Middle", "Top");
	}

}