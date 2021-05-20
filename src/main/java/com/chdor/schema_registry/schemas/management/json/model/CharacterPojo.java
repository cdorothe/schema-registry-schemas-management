
package com.chdor.schema_registry.schemas.management.json.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaDescription;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle;


/**
 * TVSeriesActor
 * <p>
 * TV Show Actor info
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "firstname",
    "lastname",
    "age"
})

@JsonSchemaTitle("Character")
@JsonSchemaDescription("A tiny Series character identity card")
public class CharacterPojo {

    @JsonProperty("firstname")
    private String firstName;

	@JsonProperty("lastname")
    private String lastName;
    @JsonProperty("age")
    private Integer age;
    
	@JsonGetter("firstname")
    public String getFirstName() {
		return firstName;
	}
	@JsonSetter("firstname")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@JsonGetter("lastname")
	public String getLastName() {
		return lastName;
	}
	@JsonSetter("lastname")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@JsonGetter("age")
	public Integer getAge() {
		return age;
	}
	@JsonSetter("age")
	public void setAge(Integer age) {
		this.age = age;
	}
}
