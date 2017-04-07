package veiw;




import model.Car;
import controller.BDFromClass;
import controller.Conector;


public class Main {

	public static void main(String[] args){
		
		BDFromClass bd = new BDFromClass(Car.class);			// Car.class - class to save
		bd.setPathToFile("C:\\Users\\Анічка\\Desktop\\BD\\"); 	// path to save files
		Conector<Car> conn = new Conector<>(bd);
		conn.conect();

		


	}

}
