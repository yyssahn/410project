package project.test;

import java.util.ArrayList;

import project.flowerVisualizer.Flower;
import project.flowerVisualizer.FlowerComposite;
import project.flowerVisualizer.FlowerRelation;
import project.flowerVisualizer.FlowerVisualizer;
import project.flowerVisualizer.FlowerVisualizerImpl;
import project.flowerVisualizer.Palette;

public class VideoExample {
	public static void main (String args [])
	{
		FlowerComposite left = new FlowerComposite();
		left.setName("left.");
		left.setPrimaryColor(Palette.setTransparency(Palette.PETER_RIVER, 100));
		for (int i = 0; i<5; i++)
			left.add(new Flower(0, 0, 25, 5, 5, 5, 2, true, "Tiny.java"));
		left.add(new Flower(0, 0, 400, 100, 50, 50, 500, true, "ElGiganto.java"));
		for (int i = 0; i<5; i++)
			left.add(new Flower(0, 0, 25, 5, 5, 5, 2, true, "Tiny.java"));
		
		
		FlowerComposite right = new FlowerComposite();
		right.setName("right.");
		right.setPrimaryColor(Palette.setTransparency(Palette.PETER_RIVER, 100));
		right.add(new Flower(0, 0, 25, 5, 50, 50, 2, true, "One.java"));
		right.add(new Flower(0, 0, 25, 5, 50, 50, 2, true, "Two.java"));
		right.add(new Flower(0, 0, 25, 5, 50, 50, 2, true, "Three.java"));
		
		FlowerComposite big = new FlowerComposite();
		big.setName("cpsc410.project.");
		big.setPrimaryColor(Palette.setTransparency(Palette.AMETHYST, 100));
		big.add(left);
		big.add(right);
		
		FlowerVisualizer myFV = new FlowerVisualizerImpl();
		
		ArrayList<FlowerRelation> relations = new ArrayList<FlowerRelation>();
		
		myFV.drawFlowers(big, 1200, 800, relations);
		for (int i = 0; i<11; i++)
			for (int j = i+1; j<11; j++)
				{
				relations.add(new FlowerRelation(i, j, 1));
				relations.add(new FlowerRelation(j, i, 1));
				}
		relations.add(new FlowerRelation(11,12,5));
		relations.add(new FlowerRelation(12,13,5));
		
		myFV.requestScale(40, 60, 400+50, "300", "999 999 999");
	}

}
