package Menu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FoodListRenderer{

	String[] folders = new String[] {"raw", "rice_wheat", "sauce", "vegetables", "kitchen tools"};

	public FoodListRenderer() 
	{
		super();
	}
	
	public FoodListRenderer(String[] folders) 
	{
		this.folders = folders.clone();
	}

	//Custom class to display images along with names in JList
	public ImageView getListCellRendererComponent(Object value) throws FileNotFoundException 
	{

		
		String param = (String) value; 
	
	
		String name = param.split("\\.")[0]; // seperate value of name
		param = name+".png";
	
		
		String temp = null;
		for (String title : folders) 
		{
			File folder = new File("./src/images/" + title);
			String[] values = folder.list();
			
			for(String s: values) {
				if(s.equals(param)) 
				{
					temp = title;
					break;
				}
			}
			if(temp != null) break;
		}
		
		String img = null;
		img = "./src/images/" + temp + "/" + param;	
        FileInputStream input = new FileInputStream(img);
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

		return imageView; 
	}
}
