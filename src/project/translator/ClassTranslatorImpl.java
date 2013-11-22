package project.translator;
import java.util.ArrayList;

import project.parser.*;
import project.flowerVisualizer.*;

public class ClassTranslatorImpl implements ClassTranslator{
	
	final float MAXSTEMHEIGHT = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height / 3;
	final int MINSTEMHEIGHT = 0;
	final int MINRADIUS = 5;
	@Override
	public void translateClass(ArrayList<ClassObject> classes, int[][] relationships) {
		
		ArrayList<Flower> flowerList = new ArrayList<Flower>();
		int topStemHeight = 0;
		
		for(int i = 0; i < classes.size(); i++){
			if(classes.get(i).getNumberOfLines() > topStemHeight){
				topStemHeight = classes.get(i).getNumberOfLines();
			}
		}
		System.out.println(topStemHeight);

		float stemHeightFactor = MAXSTEMHEIGHT / topStemHeight;
		
		for(int i = 0; i < classes.size(); i++){
			float scaleFactor = (float) classes.get(i).getNumberOfLines() / (float) topStemHeight;
			
			int stemHeight = Math.max(MINSTEMHEIGHT, (int) (classes.get(i).getNumberOfLines() * stemHeightFactor));
			int numberOfPetals = classes.get(i).getMethods().size();
			
//			int coreSize = (int) Math.max(MINRADIUS, (50 * scaleFactor));
//			int petalRadius = (int) Math.max(MINRADIUS, (50 * scaleFactor));
			
			int coreSize = 50;
			int petalRadius = 50;
			
			int numberOfRoots = classes.get(i).getImports().size();
			boolean hasLeaves = true;
			if(scaleFactor < 0.25){
				hasLeaves = false;
			}
			String className = classes.get(i).getClassName();
//			System.out.println(stemHeight + " | " +  numberOfPetals + " | " + coreSize + " | " +  petalRadius + " | " +  numberOfRoots + " | " +  hasLeaves + " | " + className);
			Flower newFlower = new Flower(0, 0, stemHeight, numberOfPetals, coreSize, petalRadius, numberOfRoots, hasLeaves, className);
			newFlower.setScaleFactor(scaleFactor * 1.2f);
			flowerList.add(newFlower);
		}
		
		ArrayList<FlowerRelation> relationList = translateRelationships(relationships);
		FlowerVisualizer visualization = new FlowerVisualizerImpl();
		visualization.drawFlowers(flowerList, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height, relationList);
		visualization.requestScale(40, 80, (int) MAXSTEMHEIGHT, Integer.toString((int) topStemHeight/2), Integer.toString(topStemHeight));
	}
	
	public ArrayList<FlowerRelation> translateRelationships(int[][] relationships){
		ArrayList<FlowerRelation> relationList = new ArrayList<FlowerRelation>();
		for(int i = 0; i < relationships.length; i++){
			for(int j = 0; j < relationships[i].length; j++){
				if(relationships[i][j] != 0){
					relationList.add(new FlowerRelation(i, j, relationships[i][j]));
				}
			}
		}
		return relationList;
	}

}
