package com.chdor.schema_registry.schemas.management.avro.model;

import java.util.List;

import org.apache.avro.reflect.AvroAlias;
import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.AvroEncode;
import org.apache.avro.reflect.AvroIgnore;
import org.apache.avro.reflect.AvroMeta;
import org.apache.avro.reflect.AvroSchema;
import org.apache.avro.reflect.DateAsLongEncoding;

@AvroAlias(alias = "TVShow",space = "com.chdor.schema_registry.schemas.management.avro.model")
@AvroDoc(value = "Descriptive card of series of the year 1970- 1980")
public class TVShowAvroPojo {
		
		@AvroSchema(value = "\"string\"")
		@AvroDoc(value = "Name of the Serie")
		private String name = null;
		
		@AvroDoc(value = "Serie release date")
		private String releaseDate = null;

		@AvroDoc(value = "A tiny Series character identity card")
		private List<Character> characters = null;

		@AvroIgnore()
		private String actor = null;

		@AvroEncode(using = DateAsLongEncoding.class)
		@AvroDoc(value = "number of milliseconds since January 1, 1970, 00:00:00 GMT")
		@AvroMeta(key = "note", value = "A simple note")
		private Long date = null;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public TVShowAvroPojo withName(String name) {
			this.name = name;
			return this;
		}

		public String getReleaseDate() {
			return releaseDate;
		}

		public void setReleaseDate(String releaseDate) {
			this.releaseDate = releaseDate;
		}
		public TVShowAvroPojo withReleaseDate(String releaseDate) {
			this.releaseDate = releaseDate;
			return this;
		}

		public List<Character> getCharacters() {
			return characters;
		}

		public void setCharacters(List<Character> characters) {
			this.characters = characters;
		}

		
}
