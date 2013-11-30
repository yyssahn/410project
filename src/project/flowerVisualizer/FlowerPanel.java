package project.flowerVisualizer;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class FlowerPanel extends Canvas {

	private int flowersWidth = 0;
	private int flowersHeight = 0;
	private int offset = 0;
	private int miny = 10;
	
	//How curvy is the arcs between the flowers.
	private int relationArcCoefficient = 40;
	

	private ArrayList<FlowerUIComponent> components = new ArrayList<FlowerUIComponent>();
	private ArrayList<FlowerUI> flowers;
	private ArrayList<FlowerRelation> relations;
	
	private int scaleX, scaleY, scaleHeight;
	private String scaleMiddleString, scaleUpperString;
	
	/**
	 * Prepares a nice scale to show the number of lines of code involved.
	 * @param x - x coordinate of the bottom of the scale.
	 * @param y - y coordinate of the bottom. (Measured from the bottom of the panel.)
	 * @param height - height from the bottom of the scale to the top.
	 * @param middleText - text to be displayed at the middle of the scale.
	 * @param upperText - text to be displayed at the top of the scale.
	 */
	public void prepareScale(int x, int y, int height, String middleText, String upperText){
		scaleX = x;
		scaleY = y;
		scaleHeight = height;
		scaleMiddleString = middleText;
		scaleUpperString = upperText;
	}
	
	private static final long serialVersionUID = -4877282940745566348L;
	
	public FlowerPanel() {
		super();
		//Scrolling functionality
		this.addMouseWheelListener(new MouseWheelListener() {		
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				double scalingFactor = 1 + 0.10*Math.abs(e.getWheelRotation());
				//Reversing if it is a negative scaling
				if (e.getWheelRotation() < 0)
					scalingFactor = 1/scalingFactor;
				
				for (FlowerUI current:flowers)
						current.getFlowerData().setScaleFactor((float) scalingFactor);
					
				//For the future - scaling of the height meter.
				//((FlowerPanel) e.getComponent()).scaleHeight *= scalingFactor;
				
				((FlowerPanel) e.getComponent()).init();
				((FlowerPanel) e.getComponent()).repaint();
			}
		});
	}
	
	/**Required to be executed after all the FlowerComponents are added.
	 * Sets the sizes correctly.
	 */	
	public void init()
	{
		flowersHeight = 200;
		flowers =  new ArrayList<FlowerUI>();
		for (FlowerUIComponent current:components)
			{
			current.init();
			flowersWidth+=current.getWidth();
			if (current.getHeight() > flowersHeight)
				flowersHeight = current.getHeight();
			flowers.addAll(current.getFlowerUIList());
			}
		this.setPreferredSize(new Dimension(flowersWidth, flowersHeight));
	}
	
	/**
	 * @param relationList - list of relations between the flowers
	 */
	public void addRelations(ArrayList<FlowerRelation> relationList){
		this.relations = relationList;
	};

	
	
	public void paint(Graphics g)
	{		
//		this.setBackground(Palette.CLOUDS);
		g.setColor(Palette.CLOUDS);
		g.fillRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		
		g.setColor(Palette.SUN_FLOWER);
		int sunsize = 300;
		g.fillOval(-sunsize/2, -sunsize/2, sunsize, sunsize);
		
		offset = Math.max((getWidth() - flowersWidth)/2, 5);
						
		for (FlowerUIComponent current: components)
		{
			current.paintReuseGraphics(g, offset, Math.max(getHeight()-current.getHeight(), miny));
			offset+=current.getWidth();
		}

		if (relations!=null)
			for (FlowerRelation current: relations)
				drawRelation(current, g);
		
		drawScale(g);
		
		g.dispose();
		g.finalize();
	}
	
	private void drawRelation(FlowerRelation relation, Graphics g)
	{
		int leftIndex = Math.min(relation.fromFlower,relation.toFlower);
		int rightIndex = Math.max(relation.fromFlower,relation.toFlower);
		
		Point left = flowers.get(leftIndex).connectionCenter;
		Point right = flowers.get(rightIndex).connectionCenter;
		
		int maxheight = Math.min(left.y, right.y) - (rightIndex-leftIndex) * relationArcCoefficient;
		
		//Lifting the right relations not to have bidirectional coincide.
		if (relation.fromFlower>relation.toFlower)
			maxheight += relationArcCoefficient/4;
		
		((Graphics2D) g).setStroke(new BasicStroke(relation.connectionWidth));
		g.setColor(relation.getPrimaryColor());
		g.drawArc(left.x, maxheight, right.x-left.x, (left.y-maxheight)*2, 90, 90);
		g.drawArc(left.x, maxheight, right.x-left.x, (right.y-maxheight)*2, 0, 90);
		
		
		AffineTransform tempTrans = ((Graphics2D) g).getTransform();
		((Graphics2D) g).setStroke(new BasicStroke(relation.connectionWidth*5/6));
		//Drawing arrows
		double yt = 25;
		if (rightIndex == relation.toFlower)
		{
			//Drawing arrows on the right

			//Calculating the angle from the triangle
			//Obtaining local linarization of the oval	
			double xt =  (right.x-left.x)*Math.sqrt(1- Math.pow((yt/((right.y-maxheight)*2)), 2));
			double xtt = (right.x-left.x)-xt;
			double hypo = Point.distance(xtt, yt, 0, 0);
			double theta = Math.acos((yt)/hypo);

			g.translate(right.x, right.y);
			((Graphics2D) g).rotate(-Math.PI/2-theta);			
		}
		else
		{
			//Calculating the angle from the triangle
			//Obtaining local linarization of the oval	
			double xt =  (right.x-left.x)*Math.sqrt(1- Math.pow((yt/((left.y-maxheight)*2)), 2));
			double xtt = (right.x-left.x)-xt;
			double hypo = Point.distance(xtt, yt, 0, 0);
			double theta = Math.acos((yt)/hypo);

			g.translate(left.x, left.y);
			((Graphics2D) g).rotate(-Math.PI/2+theta);
		}
		g.drawLine(0, 0, 12, 5);
		g.drawLine(0, 0, 12, -5);
		((Graphics2D) g).setTransform(tempTrans);
	}

	
	public void drawScale(Graphics g){
		//Check for scale not set
		if (scaleHeight == 0 )
			return;
		
		g.setColor(Palette.setTransparency(Palette.MIDNIGHT_BLUE, 200));
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.drawLine(scaleX, this.getHeight() - scaleY, scaleX, this.getHeight()-scaleY-scaleHeight);
		
		g.drawString(scaleUpperString, scaleX-20, this.getHeight() - scaleY - scaleHeight-g.getFont().getSize());
		
		g.drawString(scaleMiddleString, scaleX-20, this.getHeight() - scaleY - (scaleHeight+g.getFont().getSize())/2);
	}
	
	public void add(FlowerUIComponent toAdd){
		components.add(toAdd);
	}
	
	public int getRelationArcCoefficient() {
		return relationArcCoefficient;
	}

	public void setRelationArcCoefficient(int relationArcCoefficient) {
		this.relationArcCoefficient = relationArcCoefficient;
	}
}