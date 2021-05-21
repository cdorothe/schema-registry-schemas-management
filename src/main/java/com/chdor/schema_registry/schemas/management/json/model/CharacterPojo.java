
package com.chdor.schema_registry.schemas.management.json.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaDefault;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaDescription;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle;


/**
 * Character JSON Schema proper annotations POJO
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
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

	@JsonSchemaDefault("Mark")
	@JsonSchemaDescription("Character's firstname")
    @JsonProperty("firstname")
    private String firstName;

	@JsonSchemaDefault("Harris")
	@JsonSchemaDescription("Character's lastname")
	@JsonProperty("lastname")
    private String lastName;
   
	@JsonSchemaDefault("30")
	@JsonSchemaDescription("Character's age")
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
