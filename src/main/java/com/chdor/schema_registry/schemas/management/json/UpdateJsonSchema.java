package com.chdor.schema_registry.schemas.management.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import com.chdor.schema_registry.schemas.management.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.client.rest.entities.SchemaReference;
import io.confluent.kafka.schemaregistry.json.JsonSchema;
import io.confluent.kafka.schemaregistry.json.JsonSchemaProvider;

/**
 * A simple class used to update a JSON Schema
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
public class UpdateJsonSchema {

	public static void main(String[] args) {

		updateJsonSchemaWithJSONSchemaValidator();
		updateJsonSchemaWithJsonNode();
	}

	/**
	 * Update JSON Schema with JSONSchemaValidator
	 */
	public static void updateJsonSchemaWithJSONSchemaValidator() {
		// Load the TVShow.json
		String jsonSchemafile = "TVShow.json";
		// Simply load the schema and return it contents as string
		String jsonSchemaString = Utils.load("json-schema/".concat(jsonSchemafile)); 

		// Create a JsonSchemaProvider
		JsonSchemaProvider jsonSchemaProvider = new JsonSchemaProvider();
		
		// As the loaded schema has no Schemas references, I create an empty list
		List<SchemaReference> references = new ArrayList<>();

		// Build a parsed schema from the TVShow Schema
		Optional<ParsedSchema> jsonParsedSchema = jsonSchemaProvider.parseSchema(jsonSchemaString, references);
		// Get the effective parseSchema
		ParsedSchema parsedSchema = jsonParsedSchema.get();
		// Fails if schema is invalid
		parsedSchema.validate();
		// Cast the parsedSchema to a JSON Schema
		JsonSchema jsonSchema = (JsonSchema) parsedSchema;
		//Fails if schema is invalid
		jsonSchema.validate();

		// Display the Schema V1
		System.out.println("Update Schema:");
		System.out.println("- JSON Schema V1:\n" + jsonSchema.toJsonNode().toPrettyString());

		// Convert the JsonSchema into a JSONObject to edit it manually 
		JSONObject jsonObject = new JSONObject(jsonSchema.toString());

		// Performs "Character" modifications by changing the "definitions" field:
		// Add a boolean "isAlive" field with default value as true
		// Change the type of field "age" number -> string
		JSONObject definitionsFields = (JSONObject)jsonObject.get("definitions");
		Map<String, Object> mapOfCharacter = definitionsFields.toMap();
		@SuppressWarnings("unchecked")
		Map<String, Object> mapOfCharacterFields = (Map<String, Object>)mapOfCharacter.get("Character");
		@SuppressWarnings("unchecked")
		Map<String, Object> mapOfCharacterPropertiesFields = (Map<String, Object>)mapOfCharacterFields.get("properties");
		
		// Define the new Field "isAlive"
		Map<String, Object> isAliveFields = new HashMap<>();
		isAliveFields.put("type", "boolean");
		isAliveFields.put("default", true);
		isAliveFields.put("propertyOrder", 4);
		// Add "isAlive" field to the Character properties fields
		mapOfCharacterPropertiesFields.put("isAlive", isAliveFields);

		// Redefine the field "age"
		Map<String, Object> ageFields = new HashMap<>();
		ageFields.put("type", "string");
		ageFields.put("default", null);
		ageFields.put("propertyOrder", 2);
		mapOfCharacterPropertiesFields.put("age", ageFields);
		
		// Update the Schema "definitions" declaration
		jsonObject.put("definitions", mapOfCharacter);
		
		// Update the TVShow properties to add a "synopsis" string field
		Object propertiesObject = jsonObject.get("properties");
		if (propertiesObject instanceof JSONObject) {
			JSONObject propertiesJsonObject = (JSONObject) propertiesObject;

			// Add a Sypnosis Field
			Map<String, Object> mapOfSypnosisFields = new HashMap<>();
			mapOfSypnosisFields.put("type", "string");
			mapOfSypnosisFields.put("default", "unknown");
			mapOfSypnosisFields.put("propertyOrder", 4);
			propertiesJsonObject.put("sypnosis", mapOfSypnosisFields);
		}

		// As the changes are reflective, the jsonObject contains all our modifications
		System.out.println("Update Schema - JSON Schema V2:\n" + jsonObject.toString(2));

		// Convert the jsonObject to a JSON Schema
		SchemaLoader loader = SchemaLoader.builder().schemaJson(jsonObject).draftV7Support() // or draftV7Support()
				.build();

		Schema schema = loader.load().build();
		jsonSchema = new JsonSchema(schema.toString());
		System.out.println("Convert Schema V2 to a JsonSchema and print its again:\n"
				+ new JSONObject(jsonSchema.toString()).toString(2));

	}
	
	/**
	 * Update JSON Schema with JsonNode
	 */
	public static void updateJsonSchemaWithJsonNode() {
		// Load the TVShow.json
		String jsonSchemafile = "TVShow.json";
		// Simply load the schema and return it contents as string
		String jsonSchemaString = Utils.load("json-schema/".concat(jsonSchemafile)); 
		
		// Create a JsonSchemaProvider
		JsonSchemaProvider jsonSchemaProvider = new JsonSchemaProvider();
		
		// As the loaded schema has no Schemas references, I create an empty list
		List<SchemaReference> references = new ArrayList<>();
		
		// If the JSON schema is successfully parsed then a JsonSchema is returned
		Optional<ParsedSchema> jsonParsedSchema = jsonSchemaProvider.parseSchema(jsonSchemaString, references);
		ParsedSchema parsedSchema = jsonParsedSchema.get();
		// Fails if schema is invalid
		parsedSchema.validate();
		// Cast the parsedSchema to a JSON Schema
		JsonSchema jsonSchema = (JsonSchema) parsedSchema;
		//Fails if schema is invalid
		jsonSchema.validate();

		// Display the Schema V1
		System.out.println("Update Schema with JsonNode:");
		System.out.println("Schema - V1:\n" + jsonSchema.toJsonNode().toPrettyString());

		// Convert the JsonSchema into a JsonNode
		JsonNode rootNode = jsonSchema.toJsonNode();
		// Convert the propertiesNode into a JsonNode
		JsonNode propertiesNode = rootNode.get("properties");

		// Browse the Json Schema properties fields declaration 
		Iterator<Entry<String, JsonNode>> propertiesJsonNodes = propertiesNode.fields();
		Set<Entry<String, JsonNode>> fieldsSet = new HashSet<>();
		while (propertiesJsonNodes.hasNext()) {
			Entry<String, JsonNode> propertieJsonNode = propertiesJsonNodes.next();
			fieldsSet.add(propertieJsonNode);
			
			// Apply tvShow changes
			if (propertieJsonNode.getKey().equals("date")) {
				// Define a new type
				JsonNodeFactory factory = new JsonNodeFactory(false);
				ObjectNode fieldTypeInt = factory.objectNode();
				((ObjectNode) fieldTypeInt).put("type", "int");
				((ObjectNode) fieldTypeInt).put("default", -1);
				((ObjectNode) fieldTypeInt).put("propertyOrder", 1);
				propertieJsonNode.setValue(fieldTypeInt);
			}
		}

		ObjectMapper mapper = new ObjectMapper();

		// Backup the original fields modified
		Map<String, JsonNode> fieldsMap = new HashMap<>();
		Iterator<Entry<String, JsonNode>> iterator = fieldsSet.iterator();
		while (iterator.hasNext()) {
			Entry<String, JsonNode> entry = iterator.next();
			fieldsMap.put(entry.getKey(), entry.getValue());
		}

		// Build Base class that specifies methods for getting access to Node instances
		// (newly constructed, or shared, depending on type), as well as basic
		// implementation of the methods.
		JsonNodeFactory factory = new JsonNodeFactory(false);
		
		// Build the field "Synopsis"
		ObjectNode synopsisfieldType = factory.objectNode();
		((ObjectNode) synopsisfieldType).put("type", "string");
		((ObjectNode) synopsisfieldType).put("default", "unknown");
		((ObjectNode) synopsisfieldType).put("propertyOrder", 4);
		fieldsMap.put("Synopsis", synopsisfieldType);

		// Transform map of fields to JsonNode
		JsonNode fields = mapper.valueToTree(fieldsMap);
		((ObjectNode) rootNode).set("properties", fields);

		//System.out.println("Schema - V2:\n" + rootNode.toPrettyString());

		// Convert the jsonObject to a JSON Schema
		jsonSchema = new JsonSchema(rootNode.toString());
		System.out.println("Update Schema - V2:\n"
				+ jsonSchema.toJsonNode().toPrettyString());
		
	}
}
