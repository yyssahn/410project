package project.translator;
import java.util.ArrayList;
import project.parser.*;
import project.flowerVisualizer.*;

public class ClassTranslatorImpl implements ClassTranslator{

	@Override
	public void translateClass(ArrayList<ClassObject> classes) {
		
		ArrayList<Flower> flowerList = new ArrayList<Flower>();
		int maxStemHeight = 0;
		for(int i = 0; i < classes.size(); i++){
			if(classes.get(i).getNumberOfLines() > maxStemHeight){
				maxStemHeight = classes.get(i).getNumberOfLines();
			}
		}
		
		for(int i = 0; i < classes.size(); i++){
			int xpos = 0;
			int ypos = 0;
			int stemHeight = classes.get(i).getNumberOfLines();
			int numberOfPetals = classes.get(i).getMethods().size();
			int coreSize = 50;
			int petalRadius = 100;
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
