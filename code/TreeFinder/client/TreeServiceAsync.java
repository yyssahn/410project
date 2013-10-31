package ca.ubc.cpsc310.project.TreeFinder.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TreeServiceAsync {
	public void getTreeList(String query, AsyncCallback<List<Tree>> async);
}
