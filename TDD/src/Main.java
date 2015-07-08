import java.io.IOException;

import com.javacore.controllers.ElevatorController;
import com.javacore.controllers.PassengerController;
import com.javacore.utils.Properties;

/** Runner class  */
public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
	  /** Get main properties and organize passengers  */
         		  new Properties();
         	      PassengerController passControl = new PassengerController();
         	      passControl.organizePassengers();
         	      new ElevatorController(passControl).start();
	}
}
