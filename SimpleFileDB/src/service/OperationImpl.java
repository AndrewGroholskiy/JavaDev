package service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import controller.BDFromClass;



public class OperationImpl<T> implements Operations<T>{
	

	private Reder r;
	private Writer<T> w;
	private BDFromClass bd;

	
	public OperationImpl(BDFromClass bd) {
		this.bd = bd;
		r = new Reder(bd.getFile());
		w = new Writer<T>(bd.getFile());
		}

	@Override
	public void save(List<String> lines,String p) {
		String last;
		int id;
		if(lines.size() > 0){
			last = lines.get(lines.size()-1);
			id= Integer.valueOf(last.substring(3, last.indexOf(","))) + 1;
		}else{
			id = 1;
		}
		lines.add("id "+id +", " + p);
		
		w.bufferdWriter(lines);
	}

	@Deprecated
	public void delete2(int id) {
		List<T> ob = getAllPerson(r.read());
		Field f;
		for (int i = 0; i < ob.size(); i++) {
			try {
				f = ob.get(i).getClass().getDeclaredField("id");
				f.setAccessible(true);
				if(f.getInt(ob.get(i)) == id){	
					ob.remove(i);
				}else{
					f.setAccessible(false);
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		w.writeList(ob);
	}

	@Deprecated
	public T findById2(int id) {
		List<T> ob = getAllPerson(r.bufferedRead());
		Field f;
		for (T o : ob) {
			try {
				f = o.getClass().getDeclaredField("id");
				f.setAccessible(true);
				if(f.getInt(o) == id){	
					f.setAccessible(false);
					return o;
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return null;
	}

	@Deprecated
	public boolean updateById2(T t1) {
		List<T> ob = getAllPerson(r.read());	
		Field f;
		Field t1Field;
		for (int i = 0; i < ob.size(); i++) {
			try {
				t1Field = t1.getClass().getDeclaredField("id");
				t1Field.setAccessible(true);
					f = ob.get(i).getClass().getDeclaredField("id");
					f.setAccessible(true);
					if(f.getInt(ob.get(i)) == t1Field.getInt(t1)){							
						ob.set(i, t1);					
						t1Field.setAccessible(false);
						f.setAccessible(false);
						w.writeList(ob);
						return true;
					}
					t1Field.setAccessible(false);
					f.setAccessible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
	@SuppressWarnings("unchecked")
	public List<T> getAllPerson(List<String> strList) {
		List<T> ob = new ArrayList<T>();
		for (String s : strList) {		
			try {
				ob.add((T)bd.parseToObject(getT(s)));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return ob;
	}


	@SuppressWarnings("unchecked")
	public T getAllPerson(String strList) {
		T ob = null;
			try {
				ob = (T) bd.parseToObject(getT(strList));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return ob;
	}
	// приймає стрічку представленя рядка з файлу і повертає стрічкомий масив значень 
	private String[] getT(String s){
		List<String> fieldName = bd.getFieldsNane();
		String[] arg = new String[fieldName.size()];
		int size;
		int begin;
		int end;
		for (int i = 0; i < fieldName.size(); i++) {
			size = fieldName.get(i).length();
			begin = s.indexOf(fieldName.get(i)) + size + 1;	
			
			if(s.indexOf(",", begin) > 0) {
				end = s.indexOf(",", begin);
			}
			else {
				end = s.length();				
			}
		arg[i] = s.substring(begin, end);
		}
	return arg;
	}

	@Override
	public void sortBy(String name, Class<?> type, String ordering) {
		List<T> sorted = getAllPerson(r.bufferedRead());
		if(ordering.equals("ascending order")){
			sorted.sort(new PrimitiveComparator<T>().getComparator(name, type));			
		}else{
			sorted.sort(new PrimitiveComparator<T>().getComparator(name, type).reversed());
		}
		
		new OutputDataInConsole().coolOut(convertFormTtoString(sorted), bd.getFieldName());
	}

	
	public List<String> convertFormTtoString(List<T> tList){
		List<String> lines = new ArrayList<String>();	
		StringBuilder sb;
		Field[] field ;
		for (T t : tList) {
				sb = new StringBuilder();
				field = t.getClass().getDeclaredFields();
				for (int i = 0; i < field.length; i++) {
					try{
						field[i].setAccessible(true);
						if(i < field.length - 1)
						sb.append(field[i].getName() +" " + field[i].get(t) + ", ");
						else
						sb.append(field[i].getName() +" " + field[i].get(t));	
						field[i].setAccessible(false);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				lines.add(sb.toString());
		}
		return lines;
	}
	
	@Override
	public void deleteAll() {
		File f = r.getFile();
		f.delete();
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<String> getAll() {
		return r.read();
	}

	public BDFromClass getBd() {
		return bd;
	}

	public Reder getR() {
		return r;
	}

	@Override
	public T findById(List<String> s,int id){
		String str;
		int ids;
		for (int i = 0; i < s.size(); i++) {
			str = s.get(i);
			ids = Integer.valueOf(str.substring(3, str.indexOf(",")));
			if(ids == id){
				return  getAllPerson(str);
			}
		}
		return null;
	}
	@Override
	public boolean updateById(List<String> s,int id, String newLine){
		String str;
		int ids;
		for (int i = 0; i < s.size(); i++) {
			str = s.get(i);
			ids = Integer.valueOf(str.substring(3, str.indexOf(",")));
			if(ids == id){
				s.set(i, "id " + id + ", " + newLine);
				w.bufferdWriter(s);
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean delete(List<String> s,int id){
		String str;
		int ids;
		for (int i = 0; i < s.size(); i++) {
			str = s.get(i);
			ids = Integer.valueOf(str.substring(3, str.indexOf(",")));
			if(ids == id){
				s.remove(i);
				w.bufferdWriter(s);
				return true;
			}
		}
		return false;
	}
}
