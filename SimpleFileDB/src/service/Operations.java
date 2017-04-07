package service;

import java.util.List;


public interface Operations<T> {

	void save(List<String> lines, String line);
	
	boolean delete(List<String> lines,int id);
	
	void deleteAll();
	
	T findById(List<String> lines,int id);
	
	boolean updateById(List<String> lines, int id,String line);
	
	List<String> getAll();
	
	void sortBy(String fieldName, Class<?> fieldType, String ordering);
	
	
	
	
}
