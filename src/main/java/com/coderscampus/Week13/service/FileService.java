package com.coderscampus.Week13.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	private List<Recipe> recipes;

	@Autowired
	public FileService() throws IOException {
		this.recipes = new ArrayList<>();
		loadRecipes();
	}

	public List<Recipe> loadRecipes() throws IOException {
		// Load the CSV file from the classpath
		ClassPathResource resource = new ClassPathResource("recipe.txt");
		InputStreamReader reader = new InputStreamReader(resource.getInputStream());

		// Parse the CSV file using Apache Commons CSV
		CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',').withEscape('\\')
				.withHeader("Cooking Minutes", "Dairy Free", "Gluten Free", "Instructions", "Preparation Minutes",
						"Price Per Serving", "Ready In Minutes", "Servings", "Spoonacular Score", "Title", "Vegan",
						"Vegetarian")
				.withSkipHeaderRecord().withIgnoreSurroundingSpaces();

	    try {
	        // Convert each CSV record into a Recipe object and add it to the list
	        CSVParser parser = new CSVParser(reader, csvFormat);
	        for (CSVRecord record : parser) {
	            Recipe recipe = new Recipe();
	            recipe.setCookingMinutes(Integer.parseInt(record.get("Cooking Minutes")));
	            recipe.setDairyFree(Boolean.parseBoolean(record.get("Dairy Free")));
	            recipe.setGlutenFree(Boolean.parseBoolean(record.get("Gluten Free")));
	            recipe.setInstructions(record.get("Instructions"));
	            recipe.setPreparationMinutes(Double.parseDouble(record.get("Preparation Minutes")));
	            recipe.setPricePerServing(Double.parseDouble(record.get("Price Per Serving")));
	            recipe.setReadyInMinutes(Integer.parseInt(record.get("Ready In Minutes")));
	            recipe.setServings(Integer.parseInt(record.get("Servings")));
	            recipe.setSpoonacularScore(Double.parseDouble(record.get("Spoonacular Score")));
	            recipe.setTitle(record.get("Title"));
	            recipe.setVegan(Boolean.parseBoolean(record.get("Vegan")));
	            recipe.setVegetarian(Boolean.parseBoolean(record.get("Vegetarian")));
	            recipes.add(recipe);
	        }

	        // Close the CSV parser and the reader
	        parser.close();
	        reader.close();
	    } catch (IOException e) {
	        // Throw the caught exception
	        throw e;
	    }

	    return recipes;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

}
