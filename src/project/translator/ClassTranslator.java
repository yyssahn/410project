package project.translator;

import java.util.ArrayList;

import project.flowerVisualizer.FlowerRelation;
import project.parser.ClassObject;

public interface ClassTranslator {
	
	public void translateClass(ArrayList<ClassObject> classes, int[][] relationships);

	public ArrayList<FlowerRelation> translateRelationships(int[][] relationships);
}
