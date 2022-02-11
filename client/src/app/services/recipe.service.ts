import { HttpClient } from '@angular/common/http';
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
      this.http.get<Recipe[]>('http://localhost:8080/api/recipes')
    )
  } 
}
