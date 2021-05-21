package com.chdor.schema_registry.schemas.management.avro.model;

import org.apache.avro.reflect.AvroDefault;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.AvroSchema;

@AvroDoc("A tiny Series character identity card")
public class CharacterAvroPojoAnnoted {
	
	@AvroSchema(value = "\"string\"")
	@AvroDoc(value = "First Name of the Character")
	@AvroDefault("\"Mark\"")
	private String firstName = null;
	
	@AvroDoc(value = "Last Name of the Character")
	@AvroDefault("\"Harris\"")
	private String lastName = null;

	@AvroDoc(value = "Character Age")
	@AvroDefault(value = "30")
	private Integer age = null;


    // Getters & Setters

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	
}