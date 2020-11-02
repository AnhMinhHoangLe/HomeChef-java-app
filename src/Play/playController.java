package Play;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    


    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}

