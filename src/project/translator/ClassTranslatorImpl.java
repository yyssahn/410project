package project.translator;
import java.util.ArrayList;

import project.parser.*;
import project.flowerVisualizer.*;

public class ClassTranslatorImpl implements ClassTranslator{
	
	final float MAXSTEMHEIGHT = (float) 150.0;
	final int MINSTEMHEIGHT = 50;
	
	@Override
	public void translateClass(ArrayList<ClassObject> classes) {
		
		ArrayList<Flower> flowerList = new ArrayList<Flower>();
		int topStemHeight = 0;
		
		for(int i = 0; i < classes.size(); i++){
			if(classes.get(i).getNumberOfLines() > topStemHeight){
				topStemHeight = classes.get(i).getNumberOfLines();
			}
		}

		float stemHeightFactor = MAXSTEMHEIGHT / topStemHeight;
		
		for(int i = 0; i < classes.size(); i++){
			int xpos = 0;
			int ypos = 0;
			int stemHeight = (int) Math.max(MINSTEMHEIGHT, (int) (classes.get(i).getNumberOfLines() * stemHeightFactor));
			int numberOfPetals = classes.get(i).getMethods().size();
			int coreSize = 40;
			int petalRadius = 40;
			int numberOfRoots = classes.get(i).getImports().size();
			boolean hasLeaves = true;
			String className = classes.get(i).getClassName();
						
			Flower newFlower = new Flower(xpos, ypos, stemHeight, numberOfPetals, coreSize, petalRadius, numberOfRoots, hasLeaves, className);
			flowerList.add(newFlower);
		}
		
		ArrayList<FlowerRelation> relationList = new ArrayList<FlowerRelation>();
		FlowerVisualizer visualization = new FlowerVisualizerImpl();
		visualization.drawFlowers(flowerList, 640, 480, relationList);
	}

}
