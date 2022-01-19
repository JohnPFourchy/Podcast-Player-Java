import javafx.application.Application;
import view.ViewUIMain;

/**
 * The main class of the Podcast Manager application that loads the view
 * to allow the user to see and interact with the application.
 * 
 * @author Akli Amrous
 * @author Rhys Davis
 * @author Joey Enslen
 * @author John Fourchy
 */
public class PodcastMain {

	/**
	 * Loads the GUI.
	 * 
	 * @param args - the programs arguments, ignored for this application
	 */
	public static void main(String[] args) {
		Application.launch(ViewUIMain.class, args);
	}

}
