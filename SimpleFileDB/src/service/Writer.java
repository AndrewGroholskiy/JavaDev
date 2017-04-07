package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.List;


public class Writer<T> {

	private File file;

	public Writer(File file) {
		this.file = file;
	}
	
	
	public boolean bufferdWriter(List<String> lines){	
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (String string : lines) 
				bw.write(string + "\r\n");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			return true;		
	}
	
	public boolean bufferdWriterList(List<T> person){
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
			StringBuilder sb;
			Field[] field ;
			for (T p : person) {
				sb = new StringBuilder();
				field = p.getClass().getDeclaredFields();
				for (int i = 0; i < field.length; i++) {
					field[i].setAccessible(true);
					if(i < field.length - 1)
					sb.append(field[i].getName() +" " + field[i].get(p) + ", ");
					else
					sb.append(field[i].getName() +" " + field[i].get(p));	
					field[i].setAccessible(false);
				}
				writer.write(sb + "\r\n");				
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	
	public boolean writeList(List<T> list){
		try (FileWriter writer = new FileWriter(file)){
			StringBuilder sb;
			Field[] field;
			for (T p : list) {
				sb = new StringBuilder();
				field = p.getClass().getDeclaredFields();
				for (int i = 0; i < field.length; i++) {
					field[i].setAccessible(true);
					if(i < field.length - 1)
					sb.append(field[i].getName() +" " + field[i].get(p) + ", ");
					else
					sb.append(field[i].getName() +" " + field[i].get(p));	
					field[i].setAccessible(false);
				}
				writer.write(sb + "\r\n");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
}
