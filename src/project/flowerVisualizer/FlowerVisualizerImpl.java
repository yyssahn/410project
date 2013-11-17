package project.flowerVisualizer;


import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class FlowerVisualizerImpl implements FlowerVisualizer{

	final int DEFAULT_WIDTH = 640;
	final int DEFAULT_HEIGHT = 480;

	@Override
	public void drawFlowers(FlowerComposite flowerTree, int width, int height,
			ArrayList<FlowerRelation> relationList) {
		
		Frame mainWindowFrame = new Frame();
		mainWindowFrame.setSize(width, height);
		mainWindowFrame.setBackground(Palette.CLOUDS);
		mainWindowFrame.add(new Label("Hallo"));
		mainWindowFrame.setLayout(new BorderLayout());

		mainWindowFrame.setTitle("It is Robotanism! Your code is filled with flowers~");
		Image myIcon = Toolkit.getDefaultToolkit().getImage("assets/icon0.png");
		mainWindowFrame.setIconImage(myIcon);
		
		//Need this to close the windows and the program
		mainWindowFrame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});

		ScrollPane myScrollPane = new ScrollPane();
		FlowerPanel myPanel = new FlowerPanel();

		myPanel.add(flowerTree.makeUIWrap());			

		myPanel.addRelations(relationList);
		myPanel.init();

		myScrollPane.add(myPanel);
		mainWindowFrame.add(myScrollPane);

		mainWindowFrame.setVisible(true);	
	}
	
	
	@Override
	public void drawFlowers(ArrayList<Flower> flowerList, int width,
			int height, ArrayList<FlowerRelation> relationList) {
		Frame mainWindowFrame = new Frame();
		mainWindowFrame.setSize(width, height);
		mainWindowFrame.setBackground(Palette.CLOUDS);
		mainWindowFrame.add(new Label("Hallo"));
		mainWindowFrame.setLayout(new BorderLayout());

		mainWindowFrame.setTitle("It is Robotanism! Your code is filled with flowers~");
		Image myIcon = Toolkit.getDefaultToolkit().getImage("assets/icon0.png");
		mainWindowFrame.setIconImage(myIcon);
		
		//Need this to close the windows and the program
		mainWindowFrame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});

		ScrollPane myScrollPane = new ScrollPane();
		FlowerPanel myPanel = new FlowerPanel();

		for (Flower currentFlower : flowerList)
		{
			myPanel.add(currentFlower.makeUIWrap());
		}
			

		myPanel.addRelations(relationList);
		myPanel.init();


		myScrollPane.add(myPanel);
		mainWindowFrame.add(myScrollPane);

		mainWindowFrame.setVisible(true);		
	}

	@Override
	public void drawFlowers(ArrayList<Flower> flowerList, int width, int height) {
		drawFlowers(flowerList, width, height, new ArrayList<FlowerRelation>());
	}

	@Override
	public void drawFlowers(ArrayList<Flower> flowerList) {
		drawFlowers(flowerList, DEFAULT_WIDTH, DEFAULT_HEIGHT);		
	}

}
