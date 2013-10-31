package ca.ubc.cpsc310.project.TreeFinder.server;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import ca.ubc.cpsc310.project.TreeFinder.client.Tree;
import ca.ubc.cpsc310.project.TreeFinder.client.TreeService;


import com.google.appengine.api.datastore.PreparedQuery;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@SuppressWarnings("serial")
public class TreeServiceImpl extends RemoteServiceServlet implements TreeService{	
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(TreeServiceImpl.class.getName());
	private static final PersistenceManagerFactory pmfInstance = PMF.getPMF();

	@SuppressWarnings("unchecked")
	public List<Tree> getTreeList(String query) {
		PersistenceManager pm = getPersistenceManager();
		List<Tree> trees = new ArrayList<Tree>();
		try{
			String test = "SELECT FROM Tree WHERE treeID == '589'";
			Query t = pm.newQuery(test);
			t.setClass(Tree.class);
			t.setUnique(true);
			Tree testTree = (Tree) t.execute();
			
			if(testTree == null)
				XMLParser.parseXML();
			
			Query q = pm.newQuery(query);
			q.setClass(Tree.class);
			q.getFetchPlan().setFetchSize(500);
			List<Tree> result = (List<Tree>) q.execute();
			
			for(Tree tree : result)
				trees.add(tree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			pm.close();
		}
		return trees;
	}

	private PersistenceManager getPersistenceManager() {
		return pmfInstance.getPersistenceManager();
	}
}



