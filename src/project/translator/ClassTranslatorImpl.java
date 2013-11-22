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
		
		Random rand = new Random();

		FlowerComposite packages = new FlowerComposite();
//		packages.setName(classes.get(0).getPackage().split("\\.")[0]);
		packages.setName(classes.get(0).getPackage().substring(0,classes.get(0).getPackage().lastIndexOf(".")));
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		packages.setPrimaryColor(new Color(r, g, b, 0.5f));
		
		FlowerComposite newPackage = new FlowerComposite();
		newPackage.setName("");
		
		for(int i = 0; i < classes.size(); i++){
//			String newPackageName = classes.get(i).getPackage().split("\\.")[1];
			System.out.println(classes.get(i).getClassName());

			String newPackageName = classes.get(i).getPackage().substring(classes.get(i).getPackage().lastIndexOf(".")+1,classes.get(i).getPackage().length());

			if(!(newPackage.getName().equals(newPackageName))){
				if(newPackage.getName() != ""){
					packages.add(newPackage);
				}
				newPackage = new FlowerComposite();
				newPackage.setName(newPackageName);

				r = rand.nextFloat();
				g = rand.nextFloat();
				b = rand.nextFloat();
				
				newPackage.setPrimaryColor(new Color(r, g, b, 0.5f));
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
			newPackage.add(newFlower);
		}
		packages.add(newPackage);
		
		ArrayList<FlowerRelation> relationList = translateRelationships(relationships);
		
		FlowerVisualizer visualization = new FlowerVisualizerImpl();
		visualization.drawFlowers(packages, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height, relationList);
		visualization.requestScale(40, 80, (int) MAXSTEMHEIGHT, Integer.toString((int) topStemHeight/2), Integer.toString(topStemHeight));
		((FlowerVisualizerImpl) visualization).setRelationArcCoefficient(20);
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
