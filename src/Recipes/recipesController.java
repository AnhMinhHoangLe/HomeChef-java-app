package Recipes;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
import java.net.URL;
//import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;


public class recipesController implements Initializable{
    @FXML private Button backButton ;
    @FXML private TitledPane recipes, info_recipes;

    //Back button
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
//
//    @FXML
//    public void recipes(Stage stage) throws Exception {
//        String[] recipe_types = new String[]{"easy_recipes", "medium_recipes", "hard_recipes"};
//
//        //Get list of recipes
//        ArrayList<String> recipes = new ArrayList<String>();
//        for (String r : recipe_types) {
//            File f = new File("../images/" + r);
//            for (String s : f.list()) recipes.add(s.split(".txt")[0]);
//        }
//
//    }
//


    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}

