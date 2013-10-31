package ca.ubc.cpsc310.project.TreeFinder.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.TabLayoutPanel;

public abstract class DisplayTreePanel {

	public DisplayTreePanel(TabLayoutPanel targetPanel) {
		super();
	}

	public abstract void refreshList(List<Tree> treelist);

}