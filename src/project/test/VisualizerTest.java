package project.test;


import java.awt.Frame;
import java.util.ArrayList;

import project.flowerVisualizer.Flower;
import project.flowerVisualizer.FlowerRelation;
import project.flowerVisualizer.FlowerVisualizer;
import project.flowerVisualizer.FlowerVisualizerImpl;
import project.flowerVisualizer.Palette;


public class VisualizerTest {

	public static Frame one;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Flower myFirstFlower = new Flower(0, 400, 200, 10, 50, 50, 6, true, "Flower.java");
		Flower mySecondFlower = new Flower(0, 400, 100, 10, 25, 25, 15, true, "BlahblahblahFlower.java");
		myFirstFlower.setnumberOfRoots(6);
		mySecondFlower.setScaleFactor(2);
		
		ArrayList<Flower> toPass = new ArrayList<Flower>();
		toPass.add(myFirstFlower);
		for (int i = 0; i< 10; i++)
		{
			toPass.add(myFirstFlower);
			toPass.add(mySecondFlower);
		}
		
		//Changes the color of the Flower
		toPass.get(4).setPrimaryColor(Palette.CARROT);
		
		ArrayList<FlowerRelation> relations = new ArrayList<FlowerRelation>();
		relations.add(new FlowerRelation(0, 1, 5));
		relations.add(new FlowerRelation(0, 2, 4));
		relations.add(new FlowerRelation(3, 2, 1));
		
		FlowerVisualizer myFV = new FlowerVisualizerImpl();
		myFV.drawFlowers(toPass, 640, 480, relations);
	}

}