package application.Recipes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


public class recipesController implements Initializable{
    @FXML private Button backButton ; // back button to go back the main menu
    @FXML private VBox recipes, info_recipes; //VBox to contain the list choice of recipe and recipes info
    @FXML private TextArea label; //to display the recipes info
    @FXML private ImageView recipe_image; 
    
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
    
    private ImageView displayImage = new ImageView();

    
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
        ArrayList<String> recipeName = new ArrayList<String>(); // do this array to get the name of recipe and use for the image of list view
        
        
        //
        for (String r : recipe_types) {
            f = new File("./src/application/images/" + r);

            paths = f.list(); 
            for (String path : paths) 
            	{
            		recipesList.add(path.split(".txt")[0]);
            		recipeName.add(path.split(".txt")[0]); // add name of recipes to the list

            	}

        }
      
        
        //Create a list view with text. 
        ListView<String> list = new ListView<>(recipesList);
        
        //Custom the list view  with image and text
        list.setCellFactory(param -> new ListCell<String>() {
        	private ImageView displayImage = new ImageView(); // create the image view
        	@Override
        	public void updateItem(String nameRecipe, boolean empty) {
        	super.updateItem(nameRecipe, empty);
        	if (empty) {
        	setText(null);
        	setGraphic(null);
        	} else {
        		 for (String path : recipeName) 
                  	{
             			 if (nameRecipe.equals(path))
             			{             				 
		        			 File file = new File("./src/application/images/final dish/" + path+".png");  
		        			 Image image = new Image(file.toURI().toString());
		             		 displayImage.setImage(image);              				 
		             		 setText(nameRecipe); 
		             		 setGraphic(displayImage); 
           			}
                  	}
        	}	
        	}

        });
      
        	

        recipes.getChildren().add(list); 

        
        //Action after choosing a recipe in list view
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			/**
			 * the function to print the content of recipe
			 * @param event
			 */
            @Override
            public void handle(MouseEvent event) {
                label.clear(); // to clear the info after clicking another choice
                label.setDisable(true); // to make the textarea disable for user adding or typing. 
                
                //Print the big image on the side textarea
            	File file = new File("./src/application/images/final_dish_full/" + list.getSelectionModel().getSelectedItem()+".png");  
		        Image image = new Image(file.toURI().toString());
				recipe_image.setImage(image);
//				System.out.println("./src/images/final dish/" + list.getSelectionModel().getSelectedItem()+"/png"); 
               
				//Print the info of the picked recipe
            	for(String r: recipe_types) 
				{
					try {
						BufferedReader br = new BufferedReader(new FileReader("./src/application/images/"+r+"/"+list.getSelectionModel().getSelectedItem()+".txt"));
						String line = null;  
						while ((line = br.readLine()) != null)  
						{  
							//Scan the txt to "Procedure" for print out the content after this
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

