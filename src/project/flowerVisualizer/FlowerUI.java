package project.flowerVisualizer;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;


/**
 * A UI wrapper class for Flower.
 * @author Mike
 *
 */
public class FlowerUI extends FlowerUIComponent{
	private Flower flowerData;
	public Flower getFlowerData() {
		return flowerData;
	}


	public static final int DEFAULTROOTSIZE = 60;
	private int rootSize;
	public Point connectionCenter = new Point();
	Font f = new Font("Monospaced", Font.BOLD, 12);


	@Override
	public FlowerUIComponent wrapAround(FlowerComponent wrapped) {
		Flower flowerData = (Flower) wrapped;
		if (flowerData == null)
			return null;
		this.flowerData = flowerData;
		return this;
	}
	
	
	public void paintReuseGraphics(Graphics g, int x, int y){
		g.translate(x, y);
		paint(g);
		
		//get the point of connection to other flowers 	//Thank you once again, CPSC 314!
		connectionCenter.setLocation(((Graphics2D) g).getTransform().getTranslateX() + width/2,
				((Graphics2D) g).getTransform().getTranslateY() + width/2);
		g.translate(-x, -y);
	}
	
	//Sophisticated code for drawing Flowers
	public void paint(Graphics g){
		//Simple error check.
		if (flowerData == null)
		{	
			System.out.println("Trying to draw an empty Flower");
			return;
		}

		
		//Stem
			//Need to change the size of a stroke
				int stemWidth = Math.max(3, (int) (10*flowerData.getScaleFactor()));
				((Graphics2D) g).setStroke(new BasicStroke(stemWidth));
		g.setColor(Palette.NEPHRITIS);
		g.drawArc(width/2-(int)(20*flowerData.getScaleFactor()), width/2, (int) (40*flowerData.getScaleFactor()), 
				width/2+flowerData.getStemHeight(), 270, 170);

		//Leaves
		if (flowerData.hasLeaves())
		{
			g.fillArc(width/2+(int)(16*flowerData.getScaleFactor()), width+flowerData.getStemHeight()/10, 
					(int) (flowerData.getScaleFactor()*80), (int) (flowerData.getScaleFactor()*40), 45, 180);
			g.fillArc(width/2+(int)(16*flowerData.getScaleFactor())-((int) (flowerData.getScaleFactor()*73)), width+flowerData.getStemHeight()/4, 
					(int) (flowerData.getScaleFactor()*80), (int) (flowerData.getScaleFactor()*40), -45, 180);
		}
		
		//Head
			//Petals
				g.setColor(Palette.SUN_FLOWER);
				if (flowerData.getNumberOfPetals() == 1)
				{
					g.fillOval(0, 0, width-1, width-1);
				}
				else
				{
					int n = flowerData.getNumberOfPetals();
					int[] x = new int[2*n+1];
					int[] y = new int[2*n+1];
					for (int k = 0; k < x.length; k++)
					{
						x[k] = width / 2;
						y[k] = width / 2;
					}
					
					for (int k = 0; k < 2*n; k++)
					{
						int w = (k%2 != 0) ? width/2 : flowerData.getCoreRadius();
						x[k] += w*Math.cos(Math.PI/2 + Math.PI/n*k);
						y[k] += w*Math.sin(Math.PI/2 + Math.PI/n*k);
						
					}
					g.fillPolygon(x, y, 2 * n);					
				}
				
		//Core
		g.setColor(flowerData.getPrimaryColor());
		g.fillOval(flowerData.getPetalRadius(), flowerData.getPetalRadius(), 
				flowerData.getCoreRadius()*2,flowerData.getCoreRadius()*2);
			
		//Makes a hat to the flower - yay!
		//g.fillArc(0, 0, 100, 50, 45, 180);
		
		//Roots
		g.setColor(Palette.SUN_FLOWER);
		if (flowerData.hasRoots()) 
			if (flowerData.getNumberOfRoots() > 1)
			{	//Several roots - drawing a polygon.
				int nImports = flowerData.getNumberOfRoots();
				int[] x = new int[2 * nImports];
				int[] y = new int[2 * nImports];
				int xzero = width / 2;
				int yzero = height - DEFAULTROOTSIZE - 3;
				x[0] = xzero;
				y[0] = yzero;
				//Spiky parts of the root
				for (int k = 0; k < 2 * nImports; k = k + 2) {
					double alpha = Math.PI / 2 / nImports + k / 2 * Math.PI/ nImports;
					x[k + 1] = xzero + (int) ((rootSize - Math.random() * 10 + 5) 
							* Math.cos(alpha));
					y[k + 1] = yzero + (int) ((rootSize - Math.random() * 10 + 5) 
							* Math.sin(alpha));
				}
				//Internal parts of the root
				for (int k = 2; k < 2 * nImports; k = k + 2) {
					double alpha = k / 2 * Math.PI / nImports;
					x[k] = xzero + (int) ((rootSize - Math.random() * 10 + 5) / 2.2 
							* Math.cos(alpha));
					y[k] = yzero + (int) ((rootSize - Math.random() * 10 + 5) / 2.2 
							* Math.sin(alpha));
				}
				g.fillPolygon(x, y, 2 * nImports);
			}
			else { //One root -> carrot-like triangle.
				int[] x = new int[3];
				int[] y = new int[3];

				x[0] = (int) (width / 2 - Math.min(4,10*flowerData.getScaleFactor()));
				y[0] = height - DEFAULTROOTSIZE - 3;
				x[1] = (int) (width / 2 + Math.min(4,10*flowerData.getScaleFactor()));
				y[1] = height - DEFAULTROOTSIZE - 3;
				x[2] = (int) (width / 2 - Math.random() * 10 + 5);
				y[2] = (int) (height - DEFAULTROOTSIZE - 3 + rootSize - Math.random() * 10 + 5);

				g.fillPolygon(x, y, 3);
			}
		
		//TODO: Deal with long Strings?
		//Printing the class name
		g.setColor(Color.DARK_GRAY);
		g.setFont(f);
		g.drawString(flowerData.getName(), 0, 10);
	}


	@Override
	public ArrayList<FlowerUI> getFlowerUIList() {
		ArrayList<FlowerUI> toReturn = new ArrayList<FlowerUI>();
		toReturn.add(this);
		return toReturn;
	}


	@Override
	public void init() {
		int totalRadius = flowerData.getCoreRadius() + flowerData.getPetalRadius();
		width = 2*totalRadius;
		rootSize = (int) (flowerData.getScaleFactor() * (flowerData.hasRoots() ? 1 : 0) * DEFAULTROOTSIZE);
		//To align the flowers by roots, DEFAULTROOTSIZE is used as a measure.
		height = width + flowerData.getStemHeight() + DEFAULTROOTSIZE;
	}

}