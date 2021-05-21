
package com.chdor.schema_registry.schemas.management.json.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaDefault;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaDescription;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle;


/**
 * TVShow JSON Schema proper annotations POJO
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "date", "characters" })

@JsonSchemaTitle("TVShow")
@JsonSchemaDescription("Descriptive card of series of the year 1970- 1980")
public class TVShowPojo {

	@JsonProperty("name")
	@JsonSchemaDefault("Man from Atlantis")
	@JsonSchemaDescription("Serie's name")
	private String name;

	@JsonProperty("date")
	@JsonSchemaDefault(value = "1977")
	@JsonSchemaDescription("Serie's release year")
	private Integer date;
    
    @JsonProperty("characters")
	@JsonSchemaDescription("Characters in the Serie")
    private List<CharacterPojo> characters;
    
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
	public List<CharacterPojo> getCharacters() {
		return characters;
	}
	public void setCharacters(List<CharacterPojo> characters) {
		this.characters = characters;
	}


    
}
