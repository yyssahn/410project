package project.flowerVisualizer;


import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;


@SuppressWarnings("serial")
public class FlowerUIComponent extends Canvas{
	private Flower flowerData;
	public int totalWidth;
	private int totalHeight;
	private final int DEFAULTROOTSIZE = 60;
	private int rootSize;
	public Point connectionCenter = new Point();
	Font f = new Font("Monospaced", Font.BOLD, 12);


	public FlowerUIComponent(Flower flowerData) {
		if (flowerData == null)
			return;
		this.flowerData = flowerData;
		int totalRadius = flowerData.getCoreRadius() + flowerData.getPetalRadius();
		totalWidth = 2*totalRadius;
		rootSize = (flowerData.hasRoots() ? 1 : 0) * DEFAULTROOTSIZE;
		totalHeight = totalWidth + flowerData.getStemHeight() + rootSize;
		this.setSize(totalWidth, totalHeight);
		this.setVisible(false);
	}
	
	
	public void paintReuseGraphics(Graphics g, int x, int y){
		g.translate(x, y);
		paint(g);
		
		//get the point of connection to other flowers
		connectionCenter.setLocation(x+totalWidth/2, y+totalWidth/2);
		g.translate(-x, -y);
	}
	
	//Do not look here, I am a bit ashamed =(
	@Override
	public void paint(Graphics g){
		if (flowerData == null)
		{	
			System.out.println("Trying to draw an empty Flower");
			return;
		}
		//this.setSize(totalWidth, totalHeight);
		
		//Need to change the size of a stroke
		((Graphics2D) g).setStroke(new BasicStroke(7));
		
		//Stem
		g.setColor(new Color(39, 174, 96));
		g.drawArc(totalWidth/2-20, totalWidth/2, 40, totalWidth/2+flowerData.getStemHeight(), 270, 170);

		//Leaves
		if (flowerData.hasLeaves())
		{
			g.fillArc(totalWidth/2+16, totalWidth+flowerData.getStemHeight()/10, 80, 40, 45, 180);
			g.fillArc(totalWidth/2+16-73, totalWidth+flowerData.getStemHeight()/4, 80, 40, -45, 180);
		}
		
		//Head
			//Petals
				g.setColor(new Color(241, 196, 15));
				if (flowerData.getNumberOfPetals() == 1)
				{
					g.fillOval(0, 0, totalWidth-1, totalWidth-1);
				}
				else
				{
					int n = flowerData.getNumberOfPetals();
					int[] x = new int[2*n+1];
					int[] y = new int[2*n+1];
					for (int k = 0; k < x.length; k++)
					{
						x[k] = totalWidth / 2;
						y[k] = totalWidth / 2;
					}
					
					for (int k = 0; k < 2*n; k++)
					{
						int w = (k%2 != 0) ? totalWidth/2 : flowerData.getCoreRadius();
						x[k] += w*Math.cos(Math.PI/2 + Math.PI/n*k);
						y[k] += w*Math.sin(Math.PI/2 + Math.PI/n*k);
						
					}
					g.fillPolygon(x, y, 2 * n);					
				}
				
			//Core
		//g.setColor(new Color(231, 76, 60));
		g.setColor(flowerData.getPrimaryColor());
		g.fillOval(flowerData.getPetalRadius(), flowerData.getPetalRadius(), 
				flowerData.getCoreRadius()*2,flowerData.getCoreRadius()*2);
			
		//Makes a hat to the flower - yay!
		//g.fillArc(0, 0, 100, 50, 45, 180);
		
		//Roots
		if (flowerData.hasRoots()) {
			g.setColor(new Color(241, 196, 15));
			int nImports = flowerData.getNumberOfRoots();
			int[] x = new int[2 * nImports];
			int[] y = new int[2 * nImports];
			int xzero = totalWidth / 2;
			int yzero = totalHeight - rootSize - 3;
			x[0] = xzero;
			y[0] = yzero;
			for (int k = 0; k < 2 * nImports; k = k + 2) {
				double alpha = Math.PI / 2 / nImports + k / 2 * Math.PI/ nImports;
				x[k + 1] = xzero + (int) ((DEFAULTROOTSIZE - Math.random() * 10 + 5) 
								* Math.cos(alpha));
				y[k + 1] = yzero + (int) ((DEFAULTROOTSIZE - Math.random() * 10 + 5) 
								* Math.sin(alpha));
			}
			for (int k = 2; k < 2 * nImports; k = k + 2) {
				double alpha = k / 2 * Math.PI / nImports;
				x[k] = xzero + (int) ((DEFAULTROOTSIZE - Math.random() * 10 + 5) / 2.2 
						* Math.cos(alpha));
				y[k] = yzero + (int) ((DEFAULTROOTSIZE - Math.random() * 10 + 5) / 2.2 
						* Math.sin(alpha));
			}
			g.fillPolygon(x, y, 2 * nImports);
		}
		
		//TODO: Deal with long Strings?
		//Printing the class name
		g.setColor(Color.DARK_GRAY);
		g.setFont(f);
		g.drawString(flowerData.getName(), 0, 10);
	}


}