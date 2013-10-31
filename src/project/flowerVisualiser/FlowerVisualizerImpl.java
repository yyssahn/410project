package project.flowerVisualiser;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class FlowerVisualizerImpl implements FlowerVisualizer{

	final int WIDTH = 640;
	final int HEIGHT = 480;
	
	@Override
	public void drawFlowers(ArrayList<Flower> flowerList, int width, int height) {
		Frame mainWindowFrame = new Frame();
		mainWindowFrame.setSize(640, 480);
		mainWindowFrame.add(new Label("Hallo"));
		
		//Debug
		//Flower myFirstFlower = new Flower(0, 400, 200, 5, 100, 50, true, false, "Blah");
		for (Flower currentFlower : flowerList)
			mainWindowFrame.add(new FlowerComponent(currentFlower));
		
		//Need this to close the program
		mainWindowFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
		
		mainWindowFrame.setVisible(true);
	}

	@Override
	public void drawFlowers(ArrayList<Flower> flowerList) {
		drawFlowers(flowerList, WIDTH, HEIGHT);		
	}

}
