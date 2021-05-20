package com.chdor.schema_registry.schemas.management.json;

import java.util.ArrayList;
import java.util.List;

import com.chdor.schema_registry.schemas.management.utils.Utils;

import io.confluent.kafka.schemaregistry.CompatibilityChecker;
import io.confluent.kafka.schemaregistry.CompatibilityLevel;
import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.client.rest.entities.SchemaReference;
import io.confluent.kafka.schemaregistry.json.JsonSchemaProvider;

/**
 * A simple class used to test JSON Schema compatibility
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
public class TestJsonSchemaCompatibility {

	public static void main(String[] args) {
		// Loads Initial TVShow Schema
		String initialSchemaString = Utils.load("json-schema/".concat("TVShow.json")); 
		// Loads Update TVShow Schema V1: the Character "age" field was removed
		String updateSchemaV1String = Utils.load("json-schema/".concat("TVShowWithoutAgeField.json")); 
		// Loads Update TVShow Schema V2: A boolean "alive" field was adding on Character schema
		String updateSchemaV2String = Utils.load("json-schema/".concat("TVShowWithOneMoreFields.json")); 
		
		// Set Compatibility strategy Checker
		// All available values: NONE, BACKWARD, BACKWARD_TRANSITIVE, FORWARD,
		// FORWARD_TRANSITIVE, FULL, FULL_TRANSITIVE
		CompatibilityChecker compatibilityChecker = CompatibilityChecker.checker(CompatibilityLevel.BACKWARD);

		// BuildjsonSchemaProvider
		JsonSchemaProvider jsonSchemaProvider = new JsonSchemaProvider();
		
		// Our Schema has no schema references so defines an empty list
		List<SchemaReference> references = new ArrayList<>();
		
		// Build a parsed schema from the Initial TVShow Schema
		ParsedSchema initialSchema = jsonSchemaProvider.parseSchema(initialSchemaString, references, true).get();
		List<ParsedSchema> initialSchemas = new ArrayList<>();
		initialSchemas.add(initialSchema);
		// Build a parsed schema from the Update TVShow Schema V1
		ParsedSchema newSchema = jsonSchemaProvider.parseSchema(updateSchemaV1String, references).get();

		// Check Schema Compatibility - 1st case failed: The schemas are backward
		// incompatible. The new schema has less declared fields than the original ones
		List<String> compatibilityResult = compatibilityChecker.isCompatible(newSchema, initialSchemas);
		System.out.println("\nThe Schemas are not compatible:\n" + compatibilityResult.get(0));

		// Check Schema Compatibility - 2nd case success: The schemas are backward
		// compatible. Adding a field, even without default values, always works with JSON Schema compatibility 
		// Build a parsed schema from the Update TVShow Schema V2
		newSchema = jsonSchemaProvider.parseSchema(updateSchemaV2String, references).get();
		compatibilityResult = compatibilityChecker.isCompatible(newSchema, initialSchemas);
		System.out.println("\nThe Schemas are compatible");
	}

}
