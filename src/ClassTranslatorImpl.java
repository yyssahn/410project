import java.util.ArrayList;

public class ClassTranslatorImpl implements ClassTranslator{

	@Override
	public void translateClass(ArrayList<ClassObject> classes) {
		ArrayList<Flower> flowerList = new ArrayList<Flower>();
		
		for(int i = 0; i < classes.size(); i++){
			int numberOfImports = classes.get(i).getImports().size();
			int numberOfMethods = classes.get(i).getMethods().size();
			int numberOfLines = classes.get(i).getNumberOfLines();
			String className = classes.get(i).getClassName();
			
//Flower(int xpos, int ypos, int stemHeight, int numberOfPetals, int coreSize, int petalRadius, int numberOfRoots, boolean hasLeaves, String className) 
			
			Flower newFlower = new Flower(0, 0, numberOfLines, numberOfMethods, 50, 100, numberOfImports, true, className);
			flowerList.add(newFlower);
		}
		
		ArrayList<FlowerRelation> relationList = new ArrayList<FlowerRelation>();
		FlowerVisualizer visualization = new FlowerVisualizerImpl();
		visualization.drawFlowers(flowerList, 640, 480, relationList);
	}

}
