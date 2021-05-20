
package com.chdor.schema_registry.schemas.management.json.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaBool;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaDefault;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaDescription;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaExamples;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaFormat;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInt;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaOptions;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaOptions.Item;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaString;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle;


/**
 * TVSeriesActor
 * <p>
 * TV Show Actor info
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "date", "characters" })

@JsonSchemaTitle("TVShow")
//@JsonSchemaFormat("string")

@JsonSchemaInject(bools = { @JsonSchemaBool(path = "flag/myFlag/value", value = true) }, ints = {
		@JsonSchemaInt(path = "count/Nb Characters Count/value", value = 18) }, strings = {
				@JsonSchemaString(path = "anecdote/A Serie anecdote/value", value = "cancelled") })

@JsonSchemaOptions(items = { @Item(name = "self contained", value = "true") })

public class TVShowPojoAnnoted {

	@JsonSchemaInject(bools = { @JsonSchemaBool(path = "myflag/boolean/type", value = true) }, ints = {
			@JsonSchemaInt(path = "myInteger/a integer/type", value = 1458) }, strings = {
					@JsonSchemaString(path = "patternProperties/^[a-zA-Z0-9]+/type", value = "string") })

	@JsonProperty("name")
	@JsonSchemaExamples("An example: Man from Atlantis")
	@JsonSchemaDescription("The name of Serie")
	private String name;

	@JsonProperty("date")
	@JsonSchemaDefault("1972")
	@JsonSchemaFormat("yyyy")
	private Integer date;
    
    @JsonProperty("characters")
    private List<CharacterPojoAnnoted> characters;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public List<CharacterPojoAnnoted> getCharacters() {
		return characters;
	}
	public void setCharacters(List<CharacterPojoAnnoted> characters) {
		this.characters = characters;
	}


    
}
