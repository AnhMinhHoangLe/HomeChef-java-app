package Play;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PlayingGame implements Initializable{    
	
	public PlayingGame loaderController ;

   private static ImageView realDish;
   private ImageView kitchenToolImage; 
   private TabPane listFoods; 
   private VBox kitchenTools; 
   String item = "None";



   public static void playingGamePanel(String recipeName, String statusDifficulty) throws IOException {
   	
   		String url = "./src/images/final dish_full/"+ recipeName.replace(".txt", ".png"); 
   		File file = new File(url);  
   		Image image = new Image(file.toURI().toString());
   		realDish.setImage(image);
   	}
 


    public void initialize(URL url, ResourceBundle rb ){
    }
}


