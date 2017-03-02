package ua.service.implementation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import ua.service.FileWriter;


@Service
public class FileWriterImpl implements  FileWriter{

	@Override
	public String write(HttpServletRequest request) throws FileUploadException, IOException {
		 
		 ServletFileUpload upload = new ServletFileUpload();
		  FileItemIterator iter = upload.getItemIterator(request);  
		  while (iter.hasNext())
	      {
	          FileItemStream item = iter.next();
	          String name = item.getFieldName();
	          InputStream stream = item.openStream();


	              System.out.println("File field " + name + " with file name "
	                      + item.getName() + " detected.");
	              // Process the input stream
	              File pathToHome = new File(System.getProperty("catalina.home"));
	  			File pathToFolder = new File(pathToHome, "images" + File.separator + "item");
	  			
	  			if(!pathToFolder.exists()){
					pathToFolder.mkdirs();
				} 
	  		File f = new File(pathToFolder, item.getName());

	          FileOutputStream fout= new FileOutputStream(f);
	          BufferedOutputStream bout= new BufferedOutputStream (fout);
	          BufferedInputStream bin= new BufferedInputStream(stream);

	          int byte_;
	          while ((byte_=bin.read()) != -1){
	               bout.write(byte_);
	          }
	          bout.close();
	          bin.close();
	      }
		return null;      		 	  
		
		
	}

}
