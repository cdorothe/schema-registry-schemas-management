package com.chdor.schema_registry.schemas.management.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.chdor.schema_registry.schemas.management.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;

import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.client.rest.entities.SchemaReference;
import io.confluent.kafka.schemaregistry.json.JsonSchema;
import io.confluent.kafka.schemaregistry.json.JsonSchemaProvider;

/**
 * A simple class used to print JSON Schema Information
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
public class PrintJsonSchema {

	public static void main(String[] args) {
		String jsonSchemafile = "TVShow.json";
		
		// Create a JsonSchemaProvider
		JsonSchemaProvider jsonSchemaProvider = new JsonSchemaProvider();
		
		// As the loaded schema has no Schemas references, I create an empty list
		List<SchemaReference> references = new ArrayList<>();
		
		// Simply load the schema and return it contents as string
		String jsonSchemaString = Utils.load("json-schema/".concat(jsonSchemafile));
		
		// If the JSON schema is successfully parsed then it is returned
		Optional<ParsedSchema> jsonParsedSchema = jsonSchemaProvider.parseSchema(jsonSchemaString, references);
		ParsedSchema parsedSchema = jsonParsedSchema.get();
		// Validate the Parsed Schema, fails if not valid
		parsedSchema.validate();
		
		// Cast the parsedSchema to a JSON Schema
		JsonSchema jsonSchema = (JsonSchema) parsedSchema;
		// Validate the JSON Schema, fails if not valid
		jsonSchema.validate();
		// Prints the loaded schema
		System.out.println("Load JSON Schema: " + jsonSchemafile);
		System.out.println("JSON Schema properties configuration:");
		System.out.println("- $schema: " + jsonSchema.getString("$schema"));
		System.out.println("- name: " + jsonSchema.name());
		System.out.println("- Schema type: " + jsonSchema.schemaType());
		System.out.println("- version: " + jsonSchema.version());
		System.out.println("- additional Properties: " + jsonSchema.getString("additionalProperties"));
		System.out.println("- type: " + jsonSchema.getString("type"));
		System.out.println("- Raw Schema Title: " + jsonSchema.rawSchema().getTitle());
		System.out.println("- Raw Schema $ID: " + jsonSchema.rawSchema().getId());
		System.out.println("- Raw Schema Description: " + jsonSchema.rawSchema().getDescription());
		
		Map<String, Object> mapOfUnprocessedProperties =  jsonSchema.rawSchema().getUnprocessedProperties();
		System.out.println((mapOfUnprocessedProperties.containsKey("definitions")) ? "- Schema Definitions: "+mapOfUnprocessedProperties.get("definitions") : "no defintions");
		
		if (jsonSchema.rawSchema().isReadOnly() != null) {
			System.out.println("isReadOnly: " + String.valueOf(jsonSchema.rawSchema().isReadOnly()));
		}
		if (jsonSchema.rawSchema().isWriteOnly() != null) {
			System.out.println("isWriteOnly: " + String.valueOf(jsonSchema.rawSchema().isWriteOnly()));
		}
		if (jsonSchema.rawSchema().isNullable() != null) {
			System.out.println("isNullable: " + String.valueOf(jsonSchema.rawSchema().isNullable()));
		}
    
		// Prints all fields
		JsonNode jsonNode = jsonSchema.toJsonNode();
		JsonNode propertiesNode = jsonNode.get("properties");
		Iterator<Entry<String, JsonNode>> propertiesJsonNodes = propertiesNode.fields();
		System.out.println("Properties Fields:");
		while (propertiesJsonNodes.hasNext()) {
			Entry<String, JsonNode> propertieJsonNode = propertiesJsonNodes.next();
			System.out.println(" - Field Name: " + propertieJsonNode.getKey());
			System.out.println(" - Field Type: " + propertieJsonNode.getValue().toString());
			System.out.println();
		}
	}
	

	public static String splitCamelCase(String s) {
		return s.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}

}
