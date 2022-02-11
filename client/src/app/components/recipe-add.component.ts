import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Recipe } from '../models';
import { RecipeService } from '../services/recipe.service';

@Component({
  selector: 'app-recipe-add',
  templateUrl: './recipe-add.component.html',
  styleUrls: ['./recipe-add.component.css']
})
export class RecipeAddComponent implements OnInit {

  form!: FormGroup
  recipe!: Recipe
  lineItemsArray!: FormArray
  ingredient!: FormControl
  tempList:string[] =[]
  ingredientInput: string=''
  

  constructor(private fb: FormBuilder, private recipeSvc: RecipeService , private router: Router) { }

  ngOnInit(): void {
    // this.form.reset()
    this.form=this.createForm()
  }

  createForm(): FormGroup{
    return this.fb.group(
      {
      title: this.fb.control('',[Validators.required, Validators.minLength(3)]),
      image: this.fb.control('',[Validators.required]),
      instruction: this.fb.control('',[Validators.required, Validators.minLength(3)]),
      ingredients: this.createLineItems(this.tempList)
      }
    )
  }

  private createLineItems(li: string[] = []): FormArray {
    const lis = this.fb.array([], [ Validators.minLength(1) ])
    for (let l of li)
      lis.push(this.createLineItem(l))
    return lis
  }

  private createLineItem(li: string): FormControl {
    return this.fb.control('')
  }

  addLineItem(ingredient: string) {
    if(ingredient!==''){
      this.tempList.push(ingredient)
      console.info("FormArray updated with ",ingredient)
      console.info("templist: ", this.tempList)
      this.createLineItem(ingredient)
    }
  
  }
  deleteLineItem(i: number) {
    console.info("deleting item at index ",i)
    this.tempList.splice(i,1)
  }

  addRecipe() {
    if (!this.isValid()) {
      alert('Your form is not complete.')
      return
    }
    let recipe = this.form.value as Recipe
    recipe.ingredients=this.tempList
    console.info(recipe)
    this.recipeSvc.postRecipe(recipe)
    .then(result=>{
      const info = result 
      console.info("Yahoo! POST Created with 201 code via JSON object. Message >>>>>",info)
      this.router.navigate(['/'])

    })
    .catch(error=>{
      const errorMsg  = error.error
      console.warn("GG bro u sucks. Error 404 Bad request despite via JSON object : ",errorMsg)
    })

  
  }

  isValid() {
    return this.form.valid && (this.tempList.length > 0)
  }

}
