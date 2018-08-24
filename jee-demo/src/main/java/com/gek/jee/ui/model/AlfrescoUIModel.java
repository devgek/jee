package com.gek.jee.ui.model;

import java.util.ArrayList;
import java.util.List;

import com.gek.jee.to.DocumentTO;


@UIModel
public class AlfrescoUIModel {
	private String documentName = "susi";
	private List<DocumentTO> documentList = new ArrayList<DocumentTO>();
	private String folderPath = "/sites/kahrersoftware/documentlibrary/bilder";

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public List<DocumentTO> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<DocumentTO> documentList) {
		this.documentList = documentList;
	}
}
