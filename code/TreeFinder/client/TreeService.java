package ca.ubc.cpsc310.project.TreeFinder.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("trees")
public interface TreeService extends RemoteService{
	public List<Tree> getTreeList(String query);
}
