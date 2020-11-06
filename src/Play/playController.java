package Play;
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


public class playController implements Initializable{
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
    
    @FXML
    public void easyLevel(ActionEvent event) throws Exception{
    	 
         
    	File f = new File("./src/images/easy_recipes");
        Random r = new Random();
        String[] fileList = f.list();
        int index = r.nextInt(fileList.length);
        try {
//           PlayingGamePanelController(fileList[index], "easy");

        	Stage appStage;
            Parent root;

            String link = "./PlayingGamePanel.fxml";
//            String linkCSS = "../CSS_Files/menu.css";

            appStage = (Stage) easyLevel.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource(link));
            Scene scene = new Scene(root);
//            scene.getStylesheets().add(getClass().getResource(linkCSS).toExternalForm());
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    

    }
    
    @FXML
    public void mediumLevel(ActionEvent event) throws Exception{
    	 
         
    	File f = new File("./src/images/medium_recipes");
        Random r = new Random();
        String[] fileList = f.list();
        int index = r.nextInt(fileList.length);
        try {

//         PlayingGamePanelController(fileList[index], "medium");

        	Stage appStage;
            Parent root;

            String link = "./PlayingGamePanel.fxml";
//            String linkCSS = "../CSS_Files/menu.css";

            appStage = (Stage) easyLevel.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource(link));
            Scene scene = new Scene(root);
//            scene.getStylesheets().add(getClass().getResource(linkCSS).toExternalForm());
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
 

    }
    
    @FXML
    public void hardLevel(ActionEvent event) throws Exception{
    	 
         
    	File f = new File("./src/images/hard_recipes");
        Random r = new Random();
        String[] fileList = f.list();
        int index = r.nextInt(fileList.length);
        try {
        	 
//        	PlayingGamePanelController(fileList[index], "hard");


        	Stage appStage;
            Parent root;

            String link = "./PlayingGamePanel.fxml";
//            String linkCSS = "../CSS_Files/menu.css";

            appStage = (Stage) easyLevel.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource(link));
            Scene scene = new Scene(root);
//            scene.getStylesheets().add(getClass().getResource(linkCSS).toExternalForm());
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    
    }
    
    


//	private  ImageView realDish;
//	private ImageView kitchenToolImage; 
//	private TabPane listFoods; 
//	private VBox kitchenTools; 
//	String item = "None";



//	public void PlayingGamePanelController(String recipeName, String difficulty) throws IOException {
//    	String recipePicked = recipeName;
//    	
//    	File file = new File("./src/images/final dish_full/"+recipeName.replace(".txt", ".png"));  
//		Image image = new Image(file.toURI().toString());
//		realDish.setImage(image);
//	}

    @Override
    public void initialize(URL url, ResourceBundle rb){
    	
    }
}

