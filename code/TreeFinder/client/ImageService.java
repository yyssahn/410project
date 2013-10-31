package ca.ubc.cpsc310.project.TreeFinder.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("images")
public interface ImageService extends RemoteService{


	public  String getBlobstoreUploadUrl();

	public  ImageBlob get(String key);

	public void deleteImage(String key);
	
	public java.util.LinkedList<ImageBlob> getRecentlyUploaded();
}
