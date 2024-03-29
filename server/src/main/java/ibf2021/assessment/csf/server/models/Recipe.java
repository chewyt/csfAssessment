package ibf2021.assessment.csf.server.models;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

/*
 * IMPORTANT: Do not change this class
 */

public class Recipe {
	private String id;
	private String title;
	private String image;
	private String instruction;
	private List<String> ingredients = new LinkedList<>();

	public Recipe() { 
		id = UUID.randomUUID().toString().substring(0, 8);
	}

	public String getId() { return this.id;}
	public void setId(String id) { this.id = id; }

	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }

	public String getImage() { return this.image; }
	public void setImage(String image) { this.image = image; }

	public String getInstruction() { return this.instruction; }
	public void setInstruction(String instruction) { this.instruction = instruction; }

	public List<String> getIngredients() { return this.ingredients; }
	public void addIngredient(String ingredient) { this.ingredients.add(ingredient); }

	@Override
	public String toString() {
		return "Id: %s, Title: %s".formatted(this.id, this.title);
	}

	public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", id)
                .add("title", title)
                .add("image", image)
                .add("instruction", instruction)
                .add("ingredients", IngredientstoJsonArray())
                .build();
    }
	public JsonObject toJsonSummary() {
        return Json.createObjectBuilder()
                .add("id", id)
                .add("title", title)
                .build();
    }

	
	
	public JsonArray IngredientstoJsonArray(){
		

		JsonArrayBuilder ingredients = Json.createArrayBuilder();
            this.ingredients.forEach(ingredients::add);
		
			return ingredients.build();

		
	}

}

