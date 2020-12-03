package application.Play;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayingGame implements Initializable{    
	@FXML protected Button backButton, hintButton, kitchenToolImage;
	@FXML protected TabPane listFoods; //TabPane to contain the list choice of recipes info
	@FXML protected Tab raw, rice_wheat, vegetables, sauce, kitchen_tools; //TabPane to contain the list choice of recipes info
	@FXML protected VBox selectedItemsBox; //VBox to contain the list of selected items
	@FXML protected ImageView realDish; 

	private boolean maxedOut = false;
	private Tab SelectedTab = raw;
	private String TabName = "raw";
	private int hintCounter = 0;
	private ArrayList<String> recipeList = new ArrayList<String>();
	private ArrayList<String> recipeSelected = new ArrayList<String>();
	public PlayingGame loaderController ;
	
	private ObservableList<String> itemNames = FXCollections.observableArrayList();
	private ListView<String> listView = new ListView<String>(itemNames);
	

	GameLevel gameLevel = GameLevel.getInstance();
	
	/**
	* Create a function to "BACK" button to go back to main menu
	* 
	* @throws Exception
	*/
	@FXML
	public void backToMenu() throws Exception {
		Stage appStage;
		Parent root;
		
	    String link = "../Play/play.fxml";
	    String linkCSS="../CSS_Files/play.css";
	    
	    appStage = (Stage) backButton.getScene().getWindow();
	
	    root = FXMLLoader.load(getClass().getResource(link));
	    Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource(linkCSS).toExternalForm());
	    appStage.setScene(scene);
	    appStage.show();
	}
	
	/**
	* Create a function to "Hint" button to display hint dialog
	* 
	* @throws Exception
	*/
	@FXML
	public void showHint() throws Exception {
		Alert alert = null;
		if (hintCounter < 3) {
			// to pick the random order of recipes 
			Random r = new Random();
			int index = r.nextInt(recipeList.size());
	        String item = recipeList.get(index);
	        
	        alert = new Alert(AlertType.NONE, item, ButtonType.OK);
			alert.setTitle("You have hint(s) left: "+(2-hintCounter)+", here ya go");
			
			hintCounter++;
		}else {
			alert = new Alert(AlertType.NONE, "Sorry no more hint for you.", ButtonType.OK);
			alert.setTitle("Ops!");
		}
		alert.showAndWait();
	}

	/**
	* Main playing game panel (needs to be initialized)
	* 
	*/
	public void playingGamePanel() {
		String level = gameLevel.getLevel();
		String recipe = gameLevel.getRecipe();
		
		//load tab data
		loadTab();
        
        //display the real dish image
    	File file1 = new File("./src/images/final_dish_full/" +recipe.split(".txt")[0]+".png");  
        Image image1 = new Image(file1.toURI().toString());
        realDish.setImage(image1);
        
        //display the pot in the center
    	File file2 = new File("./src/application/images/real_dish/pot.png");  
        Image image2 = new Image(file2.toURI().toString());
        ImageView view = new ImageView(image2);
        view.setFitHeight(340);
        view.setPreserveRatio(true);
        kitchenToolImage.setGraphic(view);
        
		//Load the recipe data
		try {
			BufferedReader br = new BufferedReader(new FileReader("./src/application/images/"+level+"_recipes/"+recipe));
			String line = null;  
			while ((line = br.readLine()) != null)  
			{  
				//Scan the txt to "Game Order" for print out the content after this
				if(line.equals("Game Order:")) 
				{
					String tempLine = null;
				
					
					while ((tempLine = br.readLine()).trim().length() > 0) {
						//add recipe items into the recipe list
						recipeList.add(tempLine);
					}
					break;
				}
			} 
		}
		catch(Exception exc) 
		{
		}
		
		//event listeners for tabpane
		listFoods.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			loadTab();
		});
		
	}
	
	/**
	* Create a function to load tab data
	* 
	*/
	public void loadTab() {
    	String Tabx = listFoods.getSelectionModel().getSelectedItem().getText();
    	switch(Tabx) {
    		case "Rice/White": SelectedTab = rice_wheat; TabName = "rice_wheat"; break;
    		case "Vegetables": SelectedTab = vegetables; TabName = "vegetables"; break;
    		case "Sauce": SelectedTab = sauce; TabName = "sauce"; break;
    		case "Kitchen Tools": SelectedTab = kitchen_tools; TabName = "kitchen_tools"; break;
    		default: SelectedTab = raw;  TabName = "raw";
    			
    	}
    	
    	//create an empty vbox to add items
        VBox vbox = new VBox();
        vbox.setId(listFoods.getSelectionModel().getSelectedItem().toString());

        //list of recipes 
		ObservableList<String> itemsList = FXCollections.observableArrayList();
	
		//load all items from the selected folder into the vbox
	    File f = null; 
	    String[] paths;
	    ArrayList<String> itemsName = new ArrayList<String>(); // do this array to get the name of recipe and use for the image of list view
	    
	    f = new File("./src/application/images/"+TabName);
	
	    paths = f.list(); 

	    for (String path : paths) 
	    {
	    	itemsList.add(path.split(".txt")[0]);
	    	itemsName.add(path.split(".txt")[0]); // add name of recipes to the list
	    }
	  
	    //create a list view with text. 
	    ListView<String> list = new ListView<>(itemsList);

	    //custom the list view  with image and text
	    list.setCellFactory(param -> new ListCell<String>() {
	    	private ImageView displayImage = new ImageView(); // create the image view
			
	    	@Override
	    	public void updateItem(String nameRecipe, boolean empty) {
	        	super.updateItem(nameRecipe, empty);
	        	if (empty) {
		        	setText(null);
		        	setGraphic(null);
	        	} else {
	        		for (String path : itemsName) 
	              	{
	         			 if (nameRecipe.equals(path))
	         			 {
	         				 //load individual items into "list"
		        			 File file = new File("./src/application/images/"+TabName+"/" + path);
		        			 Image image = new Image(file.toURI().toString());
		             		 displayImage.setImage(image);
		             		 setText(nameRecipe.split(".png")[0]);
		             		 setGraphic(displayImage); 
	         			 }
	              	}
	        	}
	    	}
	    });
	    
	    //load list items into vbox
	    vbox.getChildren().add(list); 
	    
        //Action after choosing a recipe in list view
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			/**
			 * the function send data to the selected items panel
			 * @param event
			 */
            @Override
            public void handle(MouseEvent event) {
            	if (recipeList.size() > recipeSelected.size()) 
            		addSelectedItem(TabName+"/"+list.getSelectionModel().getSelectedItem());
            	
            	else if (recipeList.size() <= recipeSelected.size()) {
            		maxedOut = true;
            		GameComplete();
            	}
            }
        });
        
        //load vbox and items into the selected tab
        SelectedTab.setContent(vbox);
	}
	
	/**
	* Create a function to track game completion result.
	* Items selected are store into the recipeSelected arraylist then compare it with the recipeList to validate the selection
	* if result matches, then the player win, if not the player can try again.
	*/
	public void GameComplete() {
		Alert alert = null;
		
		//sort both arraylist
		Collections.sort(recipeList);
		Collections.sort(recipeSelected);
		
		//compare both arraylist to validate the selections and the recipe list
        if (recipeList.equals(recipeSelected)) {
        	alert = new Alert(AlertType.NONE, "You Win!", ButtonType.OK);
            alert.setTitle("Yay!");
            
        }else {
        	//player selected more than the required ingredients
        	if (maxedOut)
        		alert = new Alert(AlertType.NONE, "Required amount of ingredients reached!\nTry Again?", ButtonType.YES, ButtonType.NO);
        	
        	//player failed to pick the correct ingredients
        	else
        		alert = new Alert(AlertType.NONE, "Sorry incorrect ingredients!\nTry Again?", ButtonType.YES, ButtonType.NO);

            alert.setTitle("Ops!");
        }
		alert.showAndWait();
		
		//yes button let player try again
		if (alert.getResult() == ButtonType.YES) {
			selectedItemsBox.getChildren().clear();
			recipeSelected.clear();
			itemNames.clear();
			
		//all other buttons return player to difficulty selection
		}else {
			try {
				backToMenu();
			}
			catch(Exception exc)  
			{
			}
		}
	}
	
	/**
	* Create a function to add item into the selected panel
	* 
	* @param String itemName
	*/
	public void addSelectedItem(String itemName) {
		itemNames.add(itemName);
 		recipeSelected.add(itemName.split("/")[1].split(".png")[0]);
    	
 		//custom the list view  with image and text
    	listView.setCellFactory(param -> new ListCell<String>() {
	    	private ImageView displayImage = new ImageView(); // create the image view
			
	    	@Override
	    	public void updateItem(String nameRecipe, boolean empty) {
	        	super.updateItem(nameRecipe, empty);
	        	if (empty) {
		        	setText(null);
		        	setGraphic(null);
	        	} else {
	        		 //load individual items into "listView"
        			 File file = new File("./src/application/images/"+nameRecipe.split(".png")[0]+".png");
        			 Image image = new Image(file.toURI().toString());
             		 displayImage.setImage(image);
             		 setText(nameRecipe.split("/")[1].split(".png")[0]);
             		 setGraphic(displayImage); 
	        	}
	    	}
    	});
    	
    	//clear items selected panel then rewrite with all items
    	selectedItemsBox.getChildren().clear();
    	selectedItemsBox.getChildren().add(listView);
	}
	
	/**
	* initialize the game
	* 
	* @param URL url, ResourceBundle rb 
	*/
    public void initialize(URL url, ResourceBundle rb ){
    	playingGamePanel();
    }
}

