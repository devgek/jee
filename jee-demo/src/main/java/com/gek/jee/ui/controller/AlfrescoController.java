package com.gek.jee.ui.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.primefaces.event.FileUploadEvent;

import com.gek.jee.service.AlfrescoClientService;
import com.gek.jee.to.DocumentTO;
import com.gek.jee.ui.model.AlfrescoUIModel;

@UIController
public class AlfrescoController{
	@Inject
	AlfrescoUIModel uiModel;
	@Inject
	AlfrescoClientService alfrescoClientService;
	@Inject
	FacesDownloadController downloadController;
	
	public void getDocumentNames() {
//		alfrescoClientService.connectTest();
//		CmisObject o = alfrescoClientService.getObjectByPath("/Sites/kahrersoftware/documentLibrary/Bilder/512x512ptime.png");
		uiModel.getDocumentList().clear();
		ItemIterable<CmisObject> items = alfrescoClientService.getDocumentsInFolder(uiModel.getFolderPath());
		if (items != null) {
			for (CmisObject item : items) {
				DocumentTO doc = new DocumentTO();
				doc.setId(item.getId());
				doc.setName(item.getName());
				doc.setDownloadable(item.getBaseTypeId().equals(BaseTypeId.CMIS_DOCUMENT));
				uiModel.getDocumentList().add(doc);
			}
		}
		else {
			uiModel.setDocumentName("no documents");
		}
	}
	
	public void download(String documentId) {
		System.out.println("Download id " + documentId);
		Document doc = alfrescoClientService.getDocumentById(documentId);
		System.out.println("Mime-Type " + doc.getContentStream().getMimeType());
		
		try {
			downloadController.downloadFile(doc.getContentStream().getStream(), doc.getContentStreamFileName(), doc.getContentStreamMimeType());
		} catch (IOException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error while downloading " + doc.getContentStreamFileName()));
		}
		
		System.out.println("after download");
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = null;
		if (event.getFile().getSize() > 0) {
			try {
				Document doc = alfrescoClientService.addDocument(uiModel.getFolderPath(), event.getFile().getInputstream(), event.getFile().getFileName(), event.getFile().getContentType());
			} catch (IOException e) {
		        message = new FacesMessage("File ", event.getFile().getFileName() + " could not be saved in repository.");
			}
	        message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
	        //add document to alfresco repository
		}
		else {
	        message = new FacesMessage("Not Succesful", event.getFile().getFileName() + " is not uploaded.");
		}
		
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
