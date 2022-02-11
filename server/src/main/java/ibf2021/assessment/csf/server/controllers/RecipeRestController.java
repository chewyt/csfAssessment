package ibf2021.assessment.csf.server.controllers;


/* Write your request hander in this file */

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.assessment.csf.server.models.*;
import ibf2021.assessment.csf.server.services.RecipeService;
// import ibf2021.assessment.csf.server.services;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/api/recipes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeRestController{

    private final Logger logger = Logger.getLogger(RecipeRestController.class.getName());
    private Optional<Recipe> optRecipe;
    private Recipe recipe;
    
    @Autowired
    private RecipeService svc;

    @GetMapping
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
    
    // @PostMapping(consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    // public ResponseEntity<String> postRegister(@RequestBody MultiValueMap<String, String> form){

    //     String name = form.getFirst("name");
    //     logger.info("Name from POST >>>>> %s".formatted(name));

    //     if (!name.trim().toLowerCase().equals("justin bieber")) {
    //        //return 201 code
    //        final JsonObject resp = Json.createObjectBuilder()
    //             .add("message", "inserted")
    //             .build();
    //         return ResponseEntity.status(HttpStatus.CREATED).body(resp.toString());
            
    //     } else {
            
    //         final JsonObject resp = Json.createObjectBuilder()
    //              .add("message", "error not bad your name is justin bieber")
    //              .build();
    //          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp.toString());
    //     }

    // }
    // @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> postRegisterJson(@RequestBody Registration registration){

    //     String name = registration.getName();
    //     logger.info("Name from POST(JSON) >>>>> %s".formatted(name));

    //     if (!name.trim().toLowerCase().equals("justin bieber")) {
    //        //return 201 code
    //         //run some service for updating repo or stored value in spring
    //         //svc.update(newTodos);

    //        final JsonObject resp = Json.createObjectBuilder()
    //             .add("message", "inserted from JSON")
    //             .build();
    //         return ResponseEntity.status(HttpStatus.CREATED).body(resp.toString());
            
    //     } else {
            
    //         final JsonObject resp = Json.createObjectBuilder()
    //              .add("message", "error not bad your name is justin bieber by JSON")
    //              .build();
    //          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp.toString());
    //     }

    // }

    // @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<String> postReminders(@RequestBody String payload) {
	// 	try {
	// 		this.svc.update(payload);
	// 		JsonObject ok = Json.createObjectBuilder()
	// 			.add("message", "Update on %s".formatted(new Date()))
	// 			.build();
	// 		return ResponseEntity.status(HttpStatus.CREATED).body(ok.toString());
	// 	} catch (IOException ex) {
	// 		JsonObject err = Json.createObjectBuilder()
	// 			.add("error", ex.getMessage())
	// 			.build();
	// 		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.toString());
	// 	}
	// }

    
}
