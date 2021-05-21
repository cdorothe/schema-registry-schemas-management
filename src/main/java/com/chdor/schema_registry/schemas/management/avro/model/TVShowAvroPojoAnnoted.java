package com.chdor.schema_registry.schemas.management.avro.model;

import java.util.List;

import org.apache.avro.reflect.AvroAlias;
import org.apache.avro.reflect.AvroDefault;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.AvroEncode;
import org.apache.avro.reflect.AvroIgnore;
import org.apache.avro.reflect.AvroMeta;
import org.apache.avro.reflect.AvroSchema;
import org.apache.avro.reflect.DateAsLongEncoding;

@AvroAlias(alias = "TVShow",space = "com.chdor.schema_registry.schemas.management.avro.model")
@AvroDoc(value = "Descriptive card of series of the year 1970- 1980")
public class TVShowAvroPojoAnnoted {
		
		@AvroSchema(value = "\"string\"")
		@AvroDoc(value = "Name of the Serie")
		@AvroDefault("\"Man from Atlantis\"")
		private String name = null;
		
		@AvroDoc(value = "Release Date of the Serie")
		@AvroDefault("\"1977\"")
		private String date = null;

		@AvroDoc(value = "A tiny Series character identity card")
		private List<CharacterAvroPojo> characters = null;

		@AvroIgnore()
		private String actor = null;

		@AvroEncode(using = DateAsLongEncoding.class)
		@AvroDoc(value = "number of milliseconds since January 1, 1970, 00:00:00 GMT")
		@AvroMeta(key = "note", value = "A simple note")
		private Long dateInEpoch = null;

		// Getters & Setters
		
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

		public String getActor() {
			return actor;
		}

		public void setActor(String actor) {
			this.actor = actor;
		}

		public Long getDateInEpoch() {
			return dateInEpoch;
		}

		public void setDateInEpoch(Long dateInEpoch) {
			this.dateInEpoch = dateInEpoch;
		}
}
