package ca.ubc.cpsc310.project.TreeFinder.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListUI extends DisplayTreePanel {
	int numrow;
	VerticalPanel panel = new VerticalPanel();
	FlexTable flextable = new FlexTable();
	
	public void OnModuleLoad(){
		refreshList(null);
	}
	
	public ListUI(TabLayoutPanel targetPanel){
		super(targetPanel);
		ScrollPanel myScrollPanel = new ScrollPanel(flextable);
		targetPanel.add(myScrollPanel, "Tree List"); 

	}
	
	public void createlist(ArrayList<Tree> treelist){
		numrow = 0;
		for (Tree t: treelist)
			numrow++;
		refreshList(treelist);

	}
		
	@Override
	public void refreshList(List<Tree> treelist) {		
		int i = 1;
		flextable.removeAllRows();
		flextable.setText(0, 0, "id");
		flextable.setText(0, 1, "name");
		flextable.setText(0, 2, "height");
		flextable.setText(0, 3, "diameter");
		flextable.setText(0, 4, "stdstreet");
		flextable.setText(0, 5, "onstreet");
		flextable.setText(0, 6, "onstreetblock");
		if (treelist.size() != 0){
			for (Tree t: treelist){
				flextable.setText(i, 0, t.getTreeID());
				flextable.setText(i, 1, t.getCommonName());
				flextable.setText(i, 2, t.getHeightRange());
				flextable.setText(i, 3, t.getDiameter());
				flextable.setText(i, 4, t.getStdStreet());
				flextable.setText(i, 5, t.getOnStreet());
				flextable.setText(i, 6, t.getOnStreetBlock());
				flextable.getCellFormatter().addStyleName(i, 0, "watchListNumericColumn");
				flextable.getCellFormatter().addStyleName(i, 1, "watchListColumn");
				flextable.getCellFormatter().addStyleName(i, 2, "watchListNumericColumn");
				flextable.getCellFormatter().addStyleName(i, 3, "watchListNumericColumn");
				flextable.getCellFormatter().addStyleName(i, 4, "watchListColumn");
				flextable.getCellFormatter().addStyleName(i, 5, "watchListColumn");
				flextable.getCellFormatter().addStyleName(i, 6, "watchListNumericColumn");
				i++;
			}
		}
		flextable.getRowFormatter().addStyleName(0, "watchListHeader");
	    flextable.addStyleName("watchList");
	}
}
