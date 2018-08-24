package com.gek.jee.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;

import com.gek.jee.cmis.OpenCmisSessionHolder;

@Named
public class AlfrescoClientService {
	@Inject
	private OpenCmisSessionHolder sessionHolder;
	
	public Document getDocumentById(String id) {
		Session s = sessionHolder.getSession();
		OperationContext oc = s.createOperationContext();	
		Document doc = (Document) s.getObject(id, oc);
		return doc;
	}
	
	public Folder getFolderByPath(String path) {
		CmisObject o = getObjectByPath(path);
		if (o.getBaseTypeId().equals(BaseTypeId.CMIS_FOLDER)) {
			return (Folder)o;
		}
		else {
			return null;
		}
	}
	
	public Document addDocument(String parentPath, InputStream docInputStream, String docName, String contentType) {
		Folder parent = getFolderByPath(parentPath);
		
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		properties.put(PropertyIds.NAME, docName);

		ContentStream contentStream = new ContentStreamImpl(docName, null, contentType, docInputStream);

		// create a major version
		Document newDoc = parent.createDocument(properties, contentStream, VersioningState.MAJOR);	
		return newDoc;
	}
	
	public CmisObject getObjectByPath(String path) {
		Session s = sessionHolder.getSession();
		OperationContext oc = s.createOperationContext();	
		CmisObject o = s.getObjectByPath(path, oc);
		return o;
	}
	
	public ItemIterable<CmisObject> getDocumentsInFolder(String path) {
		CmisObject o = getObjectByPath(path);
		if (o.getBaseTypeId().equals(BaseTypeId.CMIS_FOLDER)) {
			Folder folder = (Folder)o;
			return folder.getChildren();
		}
		else {
			return null;
		}
	}
	
	public void connectTest() {
		Session s = sessionHolder.getSession();
		
		Folder root = s.getRootFolder();

		ItemIterable<CmisObject> pl = root.getChildren();
		
		traceCollection(pl);
	}

	private void traceCollection(ItemIterable<CmisObject> pl) {
		for (CmisObject o : pl) {
			if (o.getBaseType().getDisplayName().equals("Folder")) {
				Folder folder = (Folder)o;
				System.out.println(folder.getPath() + "[" + o.getId() + "]");
				traceCollection(folder.getChildren());
			}
			else {
				System.out.println("---" + o.getName() + "[" + o.getId() + "]" + o.getBaseType().getDisplayName());
			}
		}
	}
}
