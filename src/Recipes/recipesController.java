package Recipes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class recipesController implements Initializable{
    @FXML private Button backButton ; // back button to go back the main menu
    @FXML private VBox recipes, info_recipes; //VBox to contain the list choice of recipe and recipes info
    @FXML private TextArea label; //to display the recipes info
    
    
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
    
    
    /**
     * Create a list of list view to pick a recipe. 
     * After the choice, it will display the order and steps to cook the picked recipe
     */
    private void recipesController() {
    	String[] recipe_types = new String[]{"easy_recipes", "medium_recipes", "hard_recipes"};

        //Get list of recipes 
    	 ObservableList<String> recipesList =FXCollections.observableArrayList();
        File f = null; 
        String[] paths;

        for (String r : recipe_types) {
            f = new File("./src/images/" + r);
            paths = f.list(); 
            for (String path : paths) 
            	{
            		recipesList.add(path.split(".txt")[0]);
            	}

        }
        
        //create a list view and add list of recipes into it. 
        ListView<String> list = new ListView<>(recipesList);
        list.setCellFactory(TextFieldListCell.forListView());
        recipes.getChildren().add(list); 
        
        //Action after choosing a recipe in list view
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                label.clear(); // to clear the info after clicking another choice

               //Print the info of the picked recipe
            	for(String r: recipe_types) 
				{
					try {
						BufferedReader br = new BufferedReader(new FileReader("./src/images/"+r+"/"+list.getSelectionModel().getSelectedItem()+".txt"));
						String line = null;  
						while ((line = br.readLine()) != null)  
						{  
							if(line.equals("Procedure:")) 
							{
								String tempLine = null;
								//To print the info by applying "append" method
								while ((tempLine = br.readLine()).trim().length() > 0)
									label.appendText(tempLine +"\n");
									break;
							}
						} 
					}
					catch(Exception exc) 
					{
						continue;
					}
				}
                

			}
        });
    }
    
	 
	@Override
    public void initialize(URL url, ResourceBundle rb){
		recipesController(); 
    }

}

