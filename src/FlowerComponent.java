

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


@SuppressWarnings("serial")
public class FlowerComponent extends Canvas{
	private Flower flowerData;
	private int totalWidth;
	private int totalHeight;
	private final int DEFAULTROOTSIZE = 60;
	private int rootSize;


	public FlowerComponent(Flower flowerData) {
		if (flowerData == null)
			return;
		this.flowerData = flowerData;
		totalWidth = flowerData.getCoreSize() + flowerData.getPetalSize();
		rootSize = (flowerData.isHasRoots() ? 1 : 0) * DEFAULTROOTSIZE;
		totalHeight = totalWidth + flowerData.getStemHeight() + rootSize;
		this.setSize(totalWidth, totalHeight);
	}

	//Do not look here, I am a bit ashamed =(
	public void paint(Graphics g){
		if (flowerData == null)
		{	
			System.out.print("Trying to draw an empty Flower");
			return;
		}

		this.setSize(totalWidth, totalHeight);
		
		//Need to change the size of a stroke
		Graphics2D g2d = ((Graphics2D) g);
		g2d.setStroke(new BasicStroke(5));
		
		//Head
			//Petals
				g.setColor(new Color(241, 196, 15));
				g.fillOval(0, 0, totalWidth-1, totalWidth-1);
			//Core
		g.setColor(new Color(231, 76, 60));
		g.fillOval(flowerData.getPetalSize()/2, flowerData.getPetalSize()/2, 
				flowerData.getCoreSize(),flowerData.getCoreSize());
			

		//Stem
		g.setColor(new Color(39, 174, 96));
		g.drawArc(totalWidth/2-20, totalWidth, 40, flowerData.getStemHeight(), 270, 170);

		//Roots
		if (flowerData.isHasRoots()) {
			g.setColor(new Color(241, 196, 15));
			int nImports = flowerData.getnOfRoots();
			int[] x = new int[2 * nImports];
			int[] y = new int[2 * nImports];
			int xzero = totalWidth / 2;
			int yzero = totalHeight - rootSize - 3;
			x[0] = xzero;
			y[0] = yzero;
			for (int k = 0; k < 2 * nImports; k = k + 2) {
				double alpha = Math.PI / 2 / nImports + k / 2 * Math.PI
						/ nImports;
				x[k + 1] = xzero
						+ (int) ((DEFAULTROOTSIZE - Math.random() * 10 + 5) * Math
								.cos(alpha));
				y[k + 1] = yzero
						+ (int) ((DEFAULTROOTSIZE - Math.random() * 10 + 5) * Math
								.sin(alpha));
			}
			for (int k = 2; k < 2 * nImports; k = k + 2) {
				double alpha = k / 2 * Math.PI / nImports;
				x[k] = xzero
						+ (int) ((DEFAULTROOTSIZE - Math.random() * 10 + 5) / 2.2 * Math
								.cos(alpha));
				y[k] = yzero
						+ (int) ((DEFAULTROOTSIZE - Math.random() * 10 + 5) / 2.2 * Math
								.sin(alpha));
			}
			g.fillPolygon(x, y, 2 * nImports);
		}
		//Printing the class name
		g.setColor(Color.DARK_GRAY);
		Font f = new Font("Monospaced", Font.BOLD, 12);
		g.setFont(f);
		g.drawString(flowerData.getClassName(), 0, 10);
	}

}