package service;

import java.lang.reflect.Field;
import java.util.Comparator;

public class PrimitiveComparator<T> {
	Comparator<T> getComparator(String name,Class<?> type){
		Comparator<T> compar = new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				try {
					Field f1 = o1.getClass().getDeclaredField(name);
					Field f2 = o2.getClass().getDeclaredField(name);
					f1.setAccessible(true);
					f2.setAccessible(true);
					if(type.equals(String.class)){
						if(f1.get(o1).toString().compareTo(f2.get(o2).toString()) > 0){
							return 1;
						}else if(f1.get(o1).toString().compareTo(f2.get(o2).toString()) < 0){
							return -1;
						}
						return 0;
					}else if(type.equals(int.class)){
						if(f1.getInt(o1) > f2.getInt(o2)){
							return 1;
						}else if(f1.getInt(o1) < f2.getInt(o2)){
							return -1;
						}
						return 0;
					}else if(type.equals(double.class)){
						if(f1.getDouble(o1) > f2.getDouble(o2)){
							return 1;
						}else if(f1.getDouble(o1) < f2.getDouble(o2)){
							return -1;
						}
						return 0;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return 0;
			}
		};
		return compar;
	}
	
}
