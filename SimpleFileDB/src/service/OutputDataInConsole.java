package service;

import java.util.List;

public class OutputDataInConsole {
	private int[] biggestSize;
	
	
	
	public void coolOut(List<String> lines, String[] fieldName){
		biggestSize = new int[fieldName.length];         // масив в якому будуть зберіратись найбільші довжини значень
		
		for (int i = 0; i < fieldName.length; i++) {			//початкові довжини значень присвоюються довжиним полів
			biggestSize[i] = fieldName[i].length();
		}
		
		findBiggestElement(lines, fieldName);
		
		int width = fieldName.length+1;						//визначеня ширини таблички + кількість елементів + 1 тому , що
		for (int i = 0; i < biggestSize.length; i++){		//перед і після назви чи значення буде |.
			width+=biggestSize[i];
		}
		
		printLine(width);
		
		System.out.println();								//новий рядок
		System.out.print("|");
		
		for (int i = 0; i < biggestSize.length; i++) {    	//вивід шапки таблиці
		int center = 0;
		if(biggestSize[i] -  fieldName[i].length() >=2){			//якщо різниця найбільшого поля і його назви > 2 
			center = (biggestSize[i] -  fieldName[i].length())/2;	//то назву виводимо по центру
		}
		for (int j = 0; j < center; j++) {							// виводимо відступи перед полем
			System.out.print(" ");
		}
			System.out.print(fieldName[i]);							//виводимо значення
			for (int j = 0; j < biggestSize[i] - fieldName[i].length() - center; j++) {
				System.out.print(" ");										//виводимо відступи після поля
			}
			System.out.print("|");
		}
		System.out.println();
		
		printLine(width);
		
		System.out.println();
		
		for (String l : lines) {								
			
			getDataFromLine(l, fieldName);
			
			System.out.print("|");
			System.out.println();
			
			printLine(width);
			System.out.println();
		}
	}
	
	private void getDataFromLine(String l, String[] fieldName){
		int cell = 0;
		String data;
		for (int i = 0; i < fieldName.length; i++) {						//витягуємо значення зі стічок
			int begin = l.indexOf(fieldName[i]) + fieldName[i].length()+1;
			
			if(i ==  fieldName.length-1){
				data = l.substring(begin, l.length());
			}else{				
				data = l.substring(begin, l.indexOf(",", begin));			
			}

			System.out.print("|"+data);											//вивід значення
			for (int j = 0; j < (biggestSize[cell] - data.length()); j++) {
				System.out.print(" ");											// вивід пробілів якщо значення менше довжини найбільшого
			}
			cell++;
		}
	}
	
	private void findBiggestElement(List<String> lines, String[] fieldName){
		int cell = 0;
		for (int i = 0; i < fieldName.length; i++) {			//перегортуємо всі рядки і шукаємо значення найбільшої довжини
			for (String l : lines) {
				int begin = l.indexOf(fieldName[i]) + fieldName[i].length()+1;
				int size;
				if(i ==  fieldName.length-1){
					size = l.substring(begin, l.length()-1).length();
				}else{				
					size = l.substring(begin, l.indexOf(",", begin)).length();			
				}
				if(biggestSize[cell] < size){
					biggestSize[cell] = size;
				}
			}
			cell++;
		}
	}
	
	private void printLine(int width){
		for (int i = 0; i < width; i++) {					//виводимо горизонтально на всю ширину риски
			System.out.print("-");
		}
	}
	
}
