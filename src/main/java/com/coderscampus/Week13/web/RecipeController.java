package com.coderscampus.Week13.web;

import com.coderscampus.Week13.service.FileService;
import com.coderscampus.Week13.service.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecipeController {

	@Autowired
	private FileService fileService;

	@GetMapping("/gluten-free")
	public List<Recipe> getGlutenFreeRecipes() {
		List<Recipe> glutenFreeRecipes = new ArrayList<>();
		for (Recipe recipe : fileService.getRecipes()) {
			if (recipe.getGlutenFree()) {
				glutenFreeRecipes.add(recipe);
			}
		}
		return glutenFreeRecipes;
	}

	@GetMapping("/vegan")
	public List<Recipe> getVeganRecipes() {
		List<Recipe> veganRecipes = new ArrayList<>();
		for (Recipe recipe : fileService.getRecipes()) {
			if (recipe.getVegan()) {
				veganRecipes.add(recipe);
			}
		}
		return veganRecipes;
	}

	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> getVeganAndGlutenFreeRecipes() {
		List<Recipe> veganAndGlutenFreeRecipes = new ArrayList<>();
		for (Recipe recipe : fileService.getRecipes()) {
			if (recipe.getVegan() && recipe.getGlutenFree()) {
				veganAndGlutenFreeRecipes.add(recipe);
			}
		}
		return veganAndGlutenFreeRecipes;
	}

	@GetMapping("/vegetarian")
	public List<Recipe> getVegetarianRecipes() {
		List<Recipe> vegetarianRecipes = new ArrayList<>();
		for (Recipe recipe : fileService.getRecipes()) {
			if (recipe.getVegetarian()) {
				vegetarianRecipes.add(recipe);
			}
		}
		return vegetarianRecipes;
	}

	@GetMapping("/all-recipes")
	public List<Recipe> getAllRecipes() {
		return fileService.getRecipes();
	}
}