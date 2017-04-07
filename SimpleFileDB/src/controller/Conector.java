package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import service.OperationImpl;
import service.OutputDataInConsole;

public class Conector<T> {

	private Scanner sc = new Scanner(System.in);
	private OperationImpl<T> oper;
	private BDFromClass bd;
	private OutputDataInConsole coolPrintInconsole;
	
	public Conector(BDFromClass bd) {
		oper = new OperationImpl<T>(bd);
		this.bd = bd;
		coolPrintInconsole = new OutputDataInConsole();
	}
	
	
	public void conect(){
		
		boolean conect = true;
		
		while(conect){
			System.out.println("1 to add new " + bd.getClasName());
			System.out.println("2 to delete "+  bd.getClasName());
			System.out.println("3 to get "+ bd.getClasName());
			System.out.println("4 to get All "+ bd.getClasName() + "'s");
			System.out.println("5 to delete all " + bd.getClasName() + "'s");
			System.out.println("6 to update " + bd.getClasName());
			System.out.println("7 sort by:");
			System.out.println("0 to exit");
			
			String choice = sc.next();
			
			switch(choice){
			case "1":
				String p = bd.enterClassFild().toString();
				oper.save(oper.getR().bufferedRead(),p);								// add object to fiel
				break;
			case "2":
				System.out.println("Enter id");
				int idtoDel = sc.nextInt();
				oper.delete(oper.getR().bufferedRead(),idtoDel);						// delete obj from file
				break;
			case "3":
				System.out.println("Enter id");											// return object by T type by id
				int personId = sc.nextInt();
				T t = oper.findById(oper.getR().bufferedRead(),personId);
				if(t != null){
					List<T> tList = new ArrayList<T>();
					tList.add(t);
					coolPrintInconsole.coolOut(oper.convertFormTtoString(tList), bd.getFieldName());
				}else{
					System.out.println("Not found");
				}
				break;
			case "4":
				coolPrintInconsole.coolOut(oper.getAll(), bd.getFieldName());  // show all records in file
				break;
			case "5":	
				oper.deleteAll();											// delete all records from file
				break;
			case "6":
				System.out.println("Enter id");
				int idToUpdate = sc.nextInt();
				String data = bd.enterClassFild().toString();
				
				if(oper.updateById(oper.getR().bufferedRead(),idToUpdate,data)){	//update obj 
					System.out.println("update succsses");
				}else{
					System.out.println("fail to update");
				}
				break;
			case "7":
				
				boolean subMenu = true;
				
				while(subMenu){
					int nestedChoese = bd.fieldPosition();
					if(nestedChoese > 0 && nestedChoese <= bd.countOfields()){
						
						boolean subMenu2 = true;
						
						while(subMenu2){
							System.out.println("1 to ascending order");
							System.out.println("2 to deascending order");
							System.out.println("0 to get beck");
							int order = sc.nextInt();
							if(order > 2){
								System.out.println("out fo renge");
							}else if(order == 0){
								subMenu2 = false;
							}else{
								String fName = bd.getFieldsNane().get(nestedChoese-1);
								Class<?> clazz = bd.getFilds().get(nestedChoese-1).getType();
								String ordering = order == 1 ? "ascending order" : "deascending order";
								System.out.println("sorted by "+ fName + " " + ordering);	
								oper.sortBy(fName, clazz, ordering);								
							}
						}
					}else if(nestedChoese == 0){
						subMenu = false;
					}else{
						System.out.println("Out of range");
					}
				}
							
				break;
			case "0":
				sc.close();
				conect = false;
				break;
			default:
			System.out.println("wrong command");
			}
			
		}
	}	
}
