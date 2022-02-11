import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Recipe } from '../models';
import { RecipeService } from '../services/recipe.service';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {

  id!: string
  recipe!: Recipe

  constructor(private ActivatedRoute: ActivatedRoute,
              private router: Router,
              private recipeSvc: RecipeService) { }

  ngOnInit(): void {
    this.id=this.ActivatedRoute.snapshot.params['recipeId']
    console.info(this.id)
    this.recipeSvc.getRecipe(this.id)
    .then(result=>{
      this.recipe=result
      console.info(this.recipe)
    })
    .catch(error=>{
      console.info(error.error)
    })
  }

}
