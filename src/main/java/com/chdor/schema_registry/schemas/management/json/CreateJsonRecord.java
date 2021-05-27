package com.chdor.schema_registry.schemas.management.json;

import java.util.ArrayList;
import java.util.Arrays;

import com.chdor.schema_registry.schemas.management.json.model.Character;
import com.chdor.schema_registry.schemas.management.json.model.TVShow;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CreateJsonRecord {

	public static void main(String[] args) throws Exception {

		TVShow tvShow = new TVShow().withName("Man from Atlantis").withDate(1977)
				.withCharacters(new ArrayList<>(Arrays.asList(
						new Character().withFirstname("Mark").withLastname("Harris").withAge(30),
						new Character().withFirstname("Elizabeth").withLastname("Merrill").withAge(30)
						)));

		ObjectMapper mapper = new ObjectMapper();
		String jsonRecord = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tvShow);
		System.out.println(jsonRecord);
	}

}
