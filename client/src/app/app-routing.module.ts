import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipeAddComponent } from './components/recipe-add.component';
import { RecipeDetailComponent } from './components/recipe-detail.component';
import { RecipeListComponent } from './components/recipe-list.component';

const routes: Routes = [
  {path:'', component: RecipeListComponent},
  {path:'recipe/:recipeId', component: RecipeDetailComponent},
  {path:'add', component: RecipeAddComponent},
  { path: '**', redirectTo: '/', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{ useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
