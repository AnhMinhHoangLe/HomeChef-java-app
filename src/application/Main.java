package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * 
 * @author Anh Le, Huan Tran, Anh Le
 *
 */
public class Main extends Application {
	/**
	 * Start application
	 */
    @Override
    public void start(Stage stage) throws Exception{
    	//add FXML file to main menu page
        Parent root = FXMLLoader.load(getClass().getResource("./Menu/sample.fxml"));

        //Create the size of main menu Page
        Scene scene = new Scene(root,1600,900);
        //Add CSS file to main menu page in the CSS_Files folder
        scene.getStylesheets().add(getClass().getResource("./CSS_Files/menu.css").toExternalForm());

        stage.setTitle("HOMECHEF");

        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Launch application
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}




