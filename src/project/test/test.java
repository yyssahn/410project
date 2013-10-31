package project.test;


import java.awt.Frame;
import java.util.ArrayList;

import project.flowerVisualizer.Flower;
import project.flowerVisualizer.FlowerVisualizer;
import project.flowerVisualizer.FlowerVisualizerImpl;


public class test {

	public static Frame one;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Flower myFirstFlower = new Flower(0, 400, 200, 10, 50, 50, 6, true, "Flower.java");
		Flower mySecondFlower = new Flower(0, 400, 100, 10, 25, 25, 15, true, "BlahblahblahFlower.java");
		myFirstFlower.setnumberOfRoots(6);
		ArrayList<Flower> toPass = new ArrayList<Flower>();
		toPass.add(myFirstFlower);
		for (int i = 0; i< 10; i++)
		{
			toPass.add(myFirstFlower);
			toPass.add(mySecondFlower);
		}
		
		FlowerVisualizer myFV = new FlowerVisualizerImpl();
		myFV.drawFlowers(toPass);
	}

}