import java.awt.Component;
import java.awt.Graphics;


@SuppressWarnings("serial")
public class FlowerComponent extends Component{
	private Flower flowerData;

	public FlowerComponent(Flower flowerData) {
		this.flowerData = flowerData;
	}

	//Do not look here, I am a bit ashamed =(
	public void paint(Graphics g){
		int totalWidth = flowerData.getCoreSize() + flowerData.getPetalSize();
		int rootSize = (flowerData.isHasRoots() ? 1 : 0) * 40;
		int totalHeight = totalWidth + flowerData.getStemHeight() + rootSize;
		this.setSize(totalWidth, totalHeight);
		
		//Head
		g.drawOval(0, 0, totalWidth-1, totalWidth-1);
		g.drawOval(flowerData.getPetalSize()/2, flowerData.getPetalSize()/2, 
				flowerData.getCoreSize(),flowerData.getCoreSize());
		
		//Stem
		g.drawArc(totalWidth/2, totalWidth, 40, flowerData.getStemHeight(), 270, 180);
		
		//Roots
		g.draw3DRect(totalWidth/2-40, totalHeight-rootSize, 80, rootSize-1, false);
	}

}