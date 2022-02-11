import { Component, OnInit } from '@angular/core';
import { Recipe } from '../models';
import { RecipeService } from '../services/recipe.service';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {

  recipes!: Recipe[]

  constructor(private recipeSvc: RecipeService) { }

  ngOnInit(): void {

    this.recipeSvc.getAllRecipes()
    .then(result=>{
      this.recipes=result
      console.info(this.recipes)
    })
  }

}
