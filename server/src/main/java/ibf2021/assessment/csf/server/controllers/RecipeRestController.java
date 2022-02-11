package ibf2021.assessment.csf.server.controllers;


/* Write your request hander in this file */


import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.assessment.csf.server.models.*;
import ibf2021.assessment.csf.server.services.RecipeService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/api/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeRestController{

    private final Logger logger = Logger.getLogger(RecipeRestController.class.getName());
    private Optional<Recipe> optRecipe;
    private Recipe recipe;
    
    @Autowired
    private RecipeService svc;

    @GetMapping(path = "/{id}")
	public ResponseEntity<String> getRecipebyId (@PathVariable String id) {
		
        optRecipe = svc.getRecipeById(id);
        
        if (optRecipe.isPresent()) {
            logger.info("Cache hit for %s".formatted(id));
            recipe=optRecipe.get();

            return ResponseEntity.status(HttpStatus.OK).body(recipe.toJson().toString());
        }
        else{
            
            final JsonObject resp = Json.createObjectBuilder()
                         .add("message", "Recipe ID is non-existent")
                         .build();
                     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp.toString());
        }      
	}
       
    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postRecipen(@RequestBody Recipe recipe){

        String id = recipe.getId();
        logger.info("ID from POST(JSON) >>>>> %s".formatted(id));

        svc.addRecipe(recipe);
        logger.info(recipe.toString());
        
        final JsonObject resp = Json.createObjectBuilder()
             .add("message", "inserted to Java Map")
             .build();
         return ResponseEntity.status(HttpStatus.CREATED).body(resp.toString());
        
    }   
}
