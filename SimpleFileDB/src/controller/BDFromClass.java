package controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class BDFromClass {

	private Class<?> clazz;
	private final Scanner sc = new Scanner(System.in);
	private File file;
	private String pathToFile ="";
	
	public BDFromClass(Class<?> clazz) {
		this.clazz = clazz;
		file = new File(pathToFile+clazz.getName() + ".txt");
		if(!file.exists()){
			try {file.createNewFile();} 
			catch (IOException e) {e.printStackTrace();}
		}	
	}
	
	//повертаЇ л≥ст ≥мен пол≥в
	public List<String> getFieldsNane(){
		List<String> nameList = new ArrayList<String>();
		for (int i = 0; i < clazz.getDeclaredFields().length; i++)
			nameList.add(clazz.getDeclaredFields()[i].getName());
		return nameList;
	}
	
	
	public List<Field> getFilds(){
		return new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
	}
	
	// вит€гуЇ з класу clazz типи даних вс≥х пол≥в ≥ додаЇ в л≥ст 
	public List<Class<?>> getFildsType(){
		List<Class<?>> fieldClassType = new ArrayList<Class<?>>();
		Field[] f = clazz.getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			fieldClassType.add(f[i].getType());
		}
		return fieldClassType;
	}	
	
	// просить користувача весте вс≥ пол€ в консоль
	public StringBuilder enterClassFild(){
		StringBuilder line = new StringBuilder();
		for (int i = 1; i < clazz.getDeclaredFields().length; i++) {
			System.out.println("Enter " + clazz.getDeclaredFields()[i].getName() + " " + " of " + clazz.getSimpleName()+ "(" +clazz.getDeclaredFields()[i].getType() +")");
			if(i != clazz.getDeclaredFields().length-1)
			line.append(clazz.getDeclaredFields()[i].getName() + " " + sc.next() + ", ");
			else{
				line.append(clazz.getDeclaredFields()[i].getName() + " " + sc.next());
			}
		}
		return line;
	}
	
	//етворюЇ екземпл€р класу
	public Object parseToObject(String ... ar) throws InstantiationException, IllegalAccessException{
		Object ob = null;
		try {
			Field f[] = clazz.getDeclaredFields();		// пол€ класу
			Class<?> cl[] = getArryaType(f);			// типи параметр≥в конструктора int.class, String.class
			Object[] obArray = getObArray(ar);			//аргументи конструктора
			ob =  clazz.getDeclaredConstructor(cl).newInstance(obArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ob;
	}
	
	//повертаЇ масив тип≥в пол≥в
	private Class<?>[] getArryaType(Field...f){
		Class<?>[] clazz = new Class[f.length];
		for (int i = 0; i < f.length; i++) {
			clazz[i] = f[i].getType();
		}
		return clazz;
	}
	
	//повертаЇ масив обЇкт≥в прим≥тивних тип≥в ≥ стр≥чки
	public Object[] getObArray(String[] ar){
		Field[] f = clazz.getDeclaredFields();
		Object[] obArray = new Object[f.length];
		for (int i = 0; i < f.length; i++) {
			if(f[i].getType().equals(int.class)){
				obArray[i] = new Integer(ar[i]);
			}else if(f[i].getType().equals(String.class)){
				obArray[i] = new String(ar[i]);
			}else if(f[i].getType().equals(float.class)){
				obArray[i] = new Float(ar[i]);
			}else if(f[i].getType().equals(double.class)){
				obArray[i] = new Double(ar[i]);
			}else if(f[i].getType().equals(long.class)){
				obArray[i] = new Long(ar[i]);
			}else if(f[i].getType().equals(char.class)){
				obArray[i] = new Character(ar[i].charAt(0));
			}else if(f[i].getType().equals(byte.class)){
				obArray[i] = new Byte(ar[i]);
			}else if(f[i].getType().equals(boolean.class)){
				obArray[i] = new Boolean(ar[i]);
			}else if(f[i].getType().equals(short.class)){
				obArray[i] = new Short(ar[i]);
			}		
		}
		return obArray;
	}
	
	public Class<?> getClazz(){
		return clazz;	
	}

	public File getFile() {
		return file;
	}
	public String getClasName(){
		return clazz.getSimpleName();
	}
	
	public int fieldPosition(){
		int i = 1;
		for (Field f : getFilds()) {
			System.out.println(i + " to sort by " + f.getName());
			i++;
		}
		System.out.println(0+ " to get back ");
		int fildId = sc.nextInt();
		return fildId;
	}
	
	
	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
		File path = new File(pathToFile);
		if(!path.exists()) path.mkdirs();
		this.file = new File(pathToFile+clazz.getName() + ".txt");
		if(!file.exists()){
			try {file.createNewFile();} 
			catch (IOException e) {e.printStackTrace();}
		}	
	}

	public String[] getFieldName(){
		return getFieldsNane().toArray(new String[getFieldsNane().size()]);
		
	}
	
	public int countOfields(){
		return clazz.getDeclaredFields().length;
	}
}
