package application.Play;

public final class GameLevel {
	  private static GameLevel INSTANCE = null;
	  
	  private String level;
	  private String recipe;
	  
	  public static GameLevel getInstance() {
		if (INSTANCE == null) 
			INSTANCE = new GameLevel(); 
		
		return INSTANCE; 
	  }
	  
	  public void set(String level, String recipe) {
	    this.level = level;
	    this.recipe = recipe;
	  }
	  
	  public String getLevel() {
	    return this.level;
	  }
	  
	  public String getRecipe() {
	    return this.recipe;
	  }
}
