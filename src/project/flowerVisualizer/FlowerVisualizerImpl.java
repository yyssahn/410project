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
	
	FlowerPanel myPanel;

	//A helper method not to have duplicate code
	private void drawFlowers(FlowerComposite flowerTree, ArrayList<Flower> flowerList, int width, int height,
			ArrayList<FlowerRelation> relationList)
	{
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
		myPanel = new FlowerPanel();

		
		//The only section of the code that type-dependent
		if (flowerTree!=null)
			myPanel.add(flowerTree.makeUIWrap());
		else if (flowerList!=null)
			for (Flower currentFlower : flowerList)
				myPanel.add(currentFlower.makeUIWrap());
		
		myPanel.addRelations(relationList);
		myPanel.init();


		myScrollPane.add(myPanel);
		mainWindowFrame.add(myScrollPane);
		
		mainWindowFrame.setVisible(true);	
	}
	
	///The followings are the implementation of the interface that call each other in order to end up in the helper.
	@Override
	public void drawFlowers(FlowerComposite flowerTree, int width, int height,
			ArrayList<FlowerRelation> relationList) {
		drawFlowers(flowerTree, null, width, height, relationList);	
	}
	
	
	@Override
	public void drawFlowers(ArrayList<Flower> flowerList, int width,
			int height, ArrayList<FlowerRelation> relationList) {
			drawFlowers(null, flowerList, width, height, relationList);
	}
	
	@Override
	public void requestScale(int x, int y, int height, String middleText, String upperText){
		if (myPanel!=null)
			myPanel.prepareScale(x, y, height, middleText, upperText);
	}
	
	/**
	 * Changes how high the relation arrows are drawn - specific to this implementation.
	 * @param height - the maximum height above the highest flower in the relation that the arc reaches for neighboring flowers; 
	 * if there are k flowers in between, the the arc has (k + 1)*height instead.
	 */
	public void setRelationArcCoefficient(int height)
	{
		myPanel.setRelationArcCoefficient(height);
	}
}
