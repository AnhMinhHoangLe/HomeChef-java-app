package application.Menu;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller implements Initializable{
	
	//Declare the present buttons on menu page
    @FXML private Button playMenuButton, helpMenuButton, recipesMenuButton ;
    
    /**
     * Create a menu function to change page
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    public void Menu(ActionEvent event) throws Exception{
        	 Stage appStage;
        	 Parent root; 
        	 
        	 String link = ""; 
        	 String linkCSS = ""; 
        	 
             if(event.getSource()==playMenuButton)
             {
                 appStage = (Stage) playMenuButton.getScene().getWindow();
                 link = "../Play/play.fxml";
                 linkCSS="../CSS_Files/play.css";
             }
             else if(event.getSource()==helpMenuButton)
             {
                 appStage =(Stage) helpMenuButton.getScene().getWindow();
                 link = "../Help/help.fxml";
                 linkCSS="../CSS_Files/help.css";
             }
             else
             {
                appStage =(Stage) recipesMenuButton.getScene().getWindow();
                link = "../Recipes/recipes.fxml";
                linkCSS="../CSS_Files/recipes.css";
             }

            root = FXMLLoader.load(getClass().getResource(link));
            Scene scene = new Scene(root, 1600, 900);
            scene.getStylesheets().add(getClass().getResource(linkCSS).toExternalForm());
            appStage.setScene(scene);
            appStage.show();
        }

        /**
         * Exit the game function
         * 
         * @param event
         */
        public void exitMenu(ActionEvent event){
        	Platform.exit();
            System.exit(0);
        }


    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}




