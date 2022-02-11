import { Component, OnInit } from '@angular/core';
import { Recipe, RecipeSummary } from '../models';
import { RecipeService } from '../services/recipe.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipes!: RecipeSummary[]

  constructor(private recipeSvc: RecipeService) { }

  ngOnInit(): void {

    this.recipeSvc.getAllRecipes()
    .then(result=>{
      this.recipes=result
      console.info("ngOnInit")
      console.info(this.recipes)
    })
  }

}
