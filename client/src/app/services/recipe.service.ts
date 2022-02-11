import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Recipe } from '../models';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  recipe!: Recipe
  recipes!: Recipe[]

  constructor(private http: HttpClient) { }

  getAllRecipes() : Promise <Recipe[]>{


    return lastValueFrom(
      this.http.get<Recipe[]>('/api/recipes')
    )
  } 

  getRecipe(id:string): Promise<Recipe>{
    return lastValueFrom(
      this.http.get<Recipe>('/api/recipe/'+id)
    )
  }

  postRecipe(recipe:Recipe): Promise<string>{
    
    const url = ' /api/recipe'
    
    const headers = new HttpHeaders()
    .set('content-type','application/json')
    .set('Access-Control-Allow-Origin','*');


    return lastValueFrom(
      this.http.post<string>(url,recipe,{headers})
    )
  }
}
