

import java.awt.Frame;
import java.util.ArrayList;


public class test {

	public static Frame one;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Flower myFirstFlower = new Flower(0, 400, 200, 10, 100, 100, true, true, "Flower.java");
		ArrayList<Flower> toPass = new ArrayList<Flower>();
		toPass.add(myFirstFlower);
		for (int i = 0; i< 10; i++)
			toPass.add(myFirstFlower);

		FlowerVisualizer myFV = new FlowerVisualizerImpl();
		myFV.drawFlowers(toPass);
	}

}