
package com.chdor.schema_registry.schemas.management.json.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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

public class TVShowPojo {

	@JsonProperty("name")
	private String name;

	@JsonProperty("date")
	private Integer date;
    
    @JsonProperty("characters")
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
