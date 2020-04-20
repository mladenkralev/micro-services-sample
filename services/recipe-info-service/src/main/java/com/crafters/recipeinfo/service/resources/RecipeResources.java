package com.crafters.recipeinfo.service.resources;

import com.crafters.recipeinfo.service.model.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeResources {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{recipeId}")
    public Recipe getCatalog(@PathVariable("recipeId") String recipeId) throws IOException, XMLStreamException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        headers.add("Accept", "*/*");

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://www.beerxml.com/recipes.xml", HttpMethod.GET, requestEntity, String.class);

        JSONObject xmlJSONObj = XML.toJSONObject(Objects.requireNonNull(responseEntity.getBody()));
        JSONArray recipes = (JSONArray) ((JSONObject) xmlJSONObj.get("RECIPES")).get("RECIPE");

        List<Recipe> returningList = new ArrayList();
        recipes.forEach(it -> {
            JSONObject currentObject = (JSONObject) it;
            returningList.add(new Recipe((String) currentObject.get("NAME"), (String) currentObject.get("TYPE")));
        });

        Optional<Recipe> recipe = returningList.stream().filter(it -> it.getName().equals(recipeId)).findFirst();
        if(recipe.isPresent()) {
            return recipe.get();
        } else {
            return new Recipe("Default", "None");
        }
    }
}
