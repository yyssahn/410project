

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.Toolkit;
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
		mainWindowFrame.setBackground(new Color(236, 240, 241));
		mainWindowFrame.add(new Label("Hallo"));
		//mainWindowFrame.setLayout(new FlowLayout());
		mainWindowFrame.setLayout(new BorderLayout());
		
		mainWindowFrame.setTitle("It is Robotanism! Your code is filled with flowers~");
		Image myIcon = Toolkit.getDefaultToolkit().getImage("assets/icon0.png");
		mainWindowFrame.setIconImage(myIcon);
		
		ScrollPane myScrollPane = new ScrollPane();
		Panel myPanel = new Panel();
		//Debug
		for (Flower currentFlower : flowerList)
			//mainWindowFrame.add(new FlowerComponent(currentFlower));
			myPanel.add(new FlowerComponent(currentFlower));
		
		//Need this to close the windows and the program
		mainWindowFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
		
		myScrollPane.add(myPanel);
		mainWindowFrame.add(myScrollPane);
	
		
		mainWindowFrame.setVisible(true);
	}

	@Override
	public void drawFlowers(ArrayList<Flower> flowerList) {
		drawFlowers(flowerList, WIDTH, HEIGHT);		
	}

	@Override
	public void drawFlowers(ArrayList<Flower> flowerList, int width,
			int height, ArrayList<FlowerRelation> relationList) {
		//Do not care about the relationList for now, but thanks!
		drawFlowers(flowerList, width, height);		
	}

}
