import { v4 as uuidv4 } from 'uuid';

export interface Recipe extends RecipeSummary {
    image: string
    instruction: string
    ingredients: string[]  
}

export interface RecipeSummary {
	id?: string
	title: string
}

export interface Guard{
    evaluate():boolean
}