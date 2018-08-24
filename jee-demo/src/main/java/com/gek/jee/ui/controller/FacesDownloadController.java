package com.gek.jee.ui.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

@UIController
public class FacesDownloadController {
	private static final int DEFAULT_BUFFER_SIZE = 1024; 
	
	public void downloadFile(InputStream is, String fileName, String mimeType) throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();  

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // Prepare streams.
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open streams.
            input = new BufferedInputStream(is, DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            // Write file contents to response.
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        }
        finally {
        	output.close();
        	input.close();
       }

        context.responseComplete();
	}
}
