package project.translator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import project.parser.*;
import project.flowerVisualizer.*;

public class ClassTranslatorImpl implements ClassTranslator{
	
	final float MAXSTEMHEIGHT = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height / 3;
	final int MINSTEMHEIGHT = 0;
	final int MINRADIUS = 5;
	@Override
	public void translateClass(ArrayList<ClassObject> classes, int[][] relationships) {
		
		int topStemHeight = 0;
		
		for(int i = 0; i < classes.size(); i++){
			if(classes.get(i).getNumberOfLines() > topStemHeight){
				topStemHeight = classes.get(i).getNumberOfLines();
			}
		}

		float stemHeightFactor = MAXSTEMHEIGHT / topStemHeight;
		
//		ArrayList<Flower> flowerList = new ArrayList<Flower>();
		ArrayList<FlowerComposite> packageList = new ArrayList<FlowerComposite>();
		int packageNumber = 0;
		
		for(int i = 0; i < classes.size(); i++){
			String packageName = classes.get(i).getPackage();
			
			if((packageList.size() == 0) || (packageName != packageList.get(packageList.size()-1).getName())){
				packageList.add(new FlowerComposite());
				packageList.get(packageList.size()-1).setName(packageName);
				
				Random rand = new Random();
				
				float r = rand.nextFloat();
				float g = rand.nextFloat();
				float b = rand.nextFloat();
				
				packageList.get(packageList.size()-1).setPrimaryColor(new Color(r, g, b));
			}
			
			float scaleFactor = (float) classes.get(i).getNumberOfLines() / (float) topStemHeight;
			
			int stemHeight = Math.max(MINSTEMHEIGHT, (int) (classes.get(i).getNumberOfLines() * stemHeightFactor));
			int numberOfPetals = classes.get(i).getMethods().size();
						
			int coreSize = 50;
			int petalRadius = 50;
			
			int numberOfRoots = classes.get(i).getImports().size();
			boolean hasLeaves = true;
			if(scaleFactor < 0.25){
				hasLeaves = false;
			}
			String className = classes.get(i).getSimpleName();
			Flower newFlower = new Flower(0, 0, stemHeight, numberOfPetals, coreSize, petalRadius, numberOfRoots, hasLeaves, className);
			newFlower.setScaleFactor(scaleFactor * 1.2f);
			packageList.get(packageList.size()-1).add(newFlower);
		}
		
		ArrayList<FlowerRelation> relationList = translateRelationships(relationships);
		
		FlowerVisualizer visualization = new FlowerVisualizerImpl();
//		for(int i = 0; i < packageList.size(); i++){
			visualization.drawFlowers(packageList.get(0), java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height, relationList);
//		}
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
