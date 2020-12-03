package application.Play;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class playController implements Initializable {
    @FXML private Button backButton, easyLevel, mediumLevel, hardLevel ;

	/**
	* Create a function to "BACK" button to go back to main menu
	* 
	* @param event
	* @throws Exception
	*/
	@FXML
	public void backToMenu(ActionEvent event) throws Exception {
		Stage appStage;
		Parent root;
	
	    String link = "../Menu/sample.fxml";
	    String linkCSS = "../CSS_Files/menu.css";
	
	    appStage = (Stage) backButton.getScene().getWindow();
	
	    root = FXMLLoader.load(getClass().getResource(link));
	    Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource(linkCSS).toExternalForm());
	    appStage.setScene(scene);
	    appStage.show();
	}
	
	/*
	 * To get the result of difficulty level after clicking one of three buttons.
	 * @param event
	 * @throws Exception
	 */
    @FXML
    public void difficultyLevel(ActionEvent event) throws Exception{
		Stage appStage;
		GameLevel gameLevel = GameLevel.getInstance();
		Parent root; 
		String link = ""; 
		String linkCSS = ""; 
		
		
		File f; 
		int index;
		String[] fileList; 
		String statusDifficulty; 

		//Easy level, medium level, hard level
		if(event.getSource()== easyLevel)
		{
			 f = new File("./src/application/images/easy_recipes");
		     appStage = (Stage) easyLevel.getScene().getWindow();
		     statusDifficulty = "easy"; 
		     
		}
		else if(event.getSource()== mediumLevel)
		{
		     appStage = (Stage) mediumLevel.getScene().getWindow();
			 f = new File("./src/application/images/medium_recipes");
		     statusDifficulty = "medium"; 
		
		}
		else
		{
			f = new File("./src/application/images/hard_recipes");
		    appStage = (Stage) hardLevel.getScene().getWindow();
		    statusDifficulty = "hard"; 
		
		}
			// to pick the random order of recipes in picked difficulty level
			Random r = new Random(); 
			fileList = f.list();
			index = r.nextInt(fileList.length); 
			
			gameLevel.set(statusDifficulty, fileList[index]);
			
			//Change Stage to gaming
			link = "./PlayingGamePanel.fxml";
			linkCSS = "../CSS_Files/playingGamePanel.css";
			
			root = FXMLLoader.load(getClass().getResource(link));
			Scene scene = new Scene(root, 1600, 900);
			scene.getStylesheets().add(getClass().getResource(linkCSS).toExternalForm());
			
			appStage.setScene(scene);
			appStage.show();
			
        }
  



    @Override
    public void initialize(URL url, ResourceBundle rb ){
    	
    }
}

