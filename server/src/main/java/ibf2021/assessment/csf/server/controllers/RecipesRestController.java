package ibf2021.assessment.csf.server.controllers;

/* Write your request hander in this file */

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.assessment.csf.server.models.*;
import ibf2021.assessment.csf.server.services.RecipeService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/api/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipesRestController {

    private final Logger logger = Logger.getLogger(RecipesRestController.class.getName());
    private List<Recipe> recipes = new LinkedList<>();
    
    @Autowired
    private RecipeService svc;

    @GetMapping
	public ResponseEntity<String> getAllRecipes() {
		
        recipes = svc.getAllRecipes();
        List<JsonObject> jRecipes = recipes.stream()
				.map(r -> r.toJsonSummary())
				.toList();
        
        return ResponseEntity.ok(Json.createArrayBuilder(jRecipes).build().toString());
	}
    
}
