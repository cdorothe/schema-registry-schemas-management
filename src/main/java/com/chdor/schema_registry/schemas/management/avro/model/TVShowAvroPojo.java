package com.chdor.schema_registry.schemas.management.avro.model;

import java.util.List;

import org.apache.avro.reflect.AvroDoc;

@AvroDoc(value = "Descriptive card of series of the year 1970- 1980")
public class TVShowAvroPojo {
		
		@AvroDoc(value = "Name of the Serie")
		private String name = null;
		
		@AvroDoc(value = "Release Date of the Serie")
		private String date = null;

		@AvroDoc(value = "A tiny Serie character identity card")
		private List<CharacterAvroPojo> characters = null;

		// Getters & *Setters
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public List<CharacterAvroPojo> getCharacters() {
			return characters;
		}

		public void setCharacters(List<CharacterAvroPojo> characters) {
			this.characters = characters;
		}

		
		
}
