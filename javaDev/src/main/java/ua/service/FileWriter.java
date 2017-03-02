package ua.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;

public interface FileWriter {
	
	String write(HttpServletRequest request) throws FileUploadException, IOException;
}
