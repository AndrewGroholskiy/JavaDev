package service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Reder {

	private File file;
	
	public Reder(File file) {
		this.file = file;
	}

	public List<String> bufferedRead(){
		List<String> persons = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = br.readLine();
			while(line != null){
				persons.add(line);
				line = br.readLine();
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return persons;
	}
	
	public List<String> read(){
		List<String> list = new ArrayList<String>();
		StringBuilder line = new StringBuilder();
 		try (FileReader reader = new FileReader(file)){				
			int i;
			do{
				i = reader.read();
				if(i!=-1){
					if(i !='\n'){
						if(i != '\r')
						line.append(String.valueOf((char)i));
					}else{
						list.add(line.toString());
						line = new StringBuilder();
					}
				}		
			}
			while(i !=-1);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public File getFile() {
		return file;
	}
	
}
