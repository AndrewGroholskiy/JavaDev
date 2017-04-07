package service;

import java.util.List;

public class OutputDataInConsole {
	private int[] biggestSize;
	
	
	
	public void coolOut(List<String> lines, String[] fieldName){
		biggestSize = new int[fieldName.length];         // ����� � ����� ������ ���������� �������� ������� �������
		
		for (int i = 0; i < fieldName.length; i++) {			//�������� ������� ������� ������������ �������� ����
			biggestSize[i] = fieldName[i].length();
		}
		
		findBiggestElement(lines, fieldName);
		
		int width = fieldName.length+1;						//��������� ������ �������� + ������� �������� + 1 ���� , ��
		for (int i = 0; i < biggestSize.length; i++){		//����� � ���� ����� �� �������� ���� |.
			width+=biggestSize[i];
		}
		
		printLine(width);
		
		System.out.println();								//����� �����
		System.out.print("|");
		
		for (int i = 0; i < biggestSize.length; i++) {    	//���� ����� �������
		int center = 0;
		if(biggestSize[i] -  fieldName[i].length() >=2){			//���� ������ ���������� ���� � ���� ����� > 2 
			center = (biggestSize[i] -  fieldName[i].length())/2;	//�� ����� �������� �� ������
		}
		for (int j = 0; j < center; j++) {							// �������� ������� ����� �����
			System.out.print(" ");
		}
			System.out.print(fieldName[i]);							//�������� ��������
			for (int j = 0; j < biggestSize[i] - fieldName[i].length() - center; j++) {
				System.out.print(" ");										//�������� ������� ���� ����
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
		for (int i = 0; i < fieldName.length; i++) {						//�������� �������� � �����
			int begin = l.indexOf(fieldName[i]) + fieldName[i].length()+1;
			
			if(i ==  fieldName.length-1){
				data = l.substring(begin, l.length());
			}else{				
				data = l.substring(begin, l.indexOf(",", begin));			
			}

			System.out.print("|"+data);											//���� ��������
			for (int j = 0; j < (biggestSize[cell] - data.length()); j++) {
				System.out.print(" ");											// ���� ������ ���� �������� ����� ������� ����������
			}
			cell++;
		}
	}
	
	private void findBiggestElement(List<String> lines, String[] fieldName){
		int cell = 0;
		for (int i = 0; i < fieldName.length; i++) {			//����������� �� ����� � ������ �������� �������� �������
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
		for (int i = 0; i < width; i++) {					//�������� ������������� �� ��� ������ �����
			System.out.print("-");
		}
	}
	
}
