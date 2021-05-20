package com.chdor.schema_registry.schemas.management.avro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chdor.schema_registry.schemas.management.utils.Utils;

import io.confluent.kafka.schemaregistry.CompatibilityChecker;
import io.confluent.kafka.schemaregistry.CompatibilityLevel;
import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchemaProvider;
import io.confluent.kafka.schemaregistry.client.rest.entities.SchemaReference;
/**
 * A simple class used to test JSON Schemas compatibility
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
public class TestAvroSchemaCompatibility {

	public static void main(String[] args) {
		// Define the Schemas used for compatibility tests
		String tvShowSchema	 =  Utils.load("avro-schema/TVShow.avsc");
		String schemaAddFieldWithNoDefault = Utils.load("avro-schema/TVShowWithSynopsis.avsc"); 
		String schemaAddFieldWithDefault = Utils.load("avro-schema/TVShowWithSynopsisDefault.avsc");

		// Set Compatibility strategy Checker
		// All available values: NONE, BACKWARD, BACKWARD_TRANSITIVE, FORWARD,
		// FORWARD_TRANSITIVE, FULL, FULL_TRANSITIVE
		CompatibilityChecker compatibilityChecker = CompatibilityChecker.checker(CompatibilityLevel.BACKWARD);

		// Create AVRO Schema with AvroSchemaProvider
		AvroSchemaProvider avroSchemaProvider = new AvroSchemaProvider();
		
		// The Schema has no Schemas references
		List<SchemaReference> references = Collections.emptyList();
		
		// Define the original schema and the new one
		ParsedSchema previousSchema = avroSchemaProvider.parseSchema(tvShowSchema, references).get();
		List<ParsedSchema> previousSchemas = new ArrayList<>();
		previousSchemas.add(previousSchema);
		ParsedSchema newSchema = avroSchemaProvider.parseSchema(schemaAddFieldWithNoDefault, references).get();

		// Check Schema Compatibility - 1st case failed: The schemas are backward
		// incompatible. The new schema has not declared a default value for the new
		// adding field 'synopsis'
		List<String> compatibilityResult = compatibilityChecker.isCompatible(newSchema, previousSchemas);
		System.out.println("\nThe Schemas are not compatible:\n" + compatibilityResult.get(0));

		// Check Schema Compatibility - 2nd case: The schemas are backward
		// compatible. The new adding field 'synopsis' has a default value 
		newSchema = avroSchemaProvider.parseSchema(schemaAddFieldWithDefault, references).get();
		compatibilityResult = compatibilityChecker.isCompatible(newSchema, previousSchemas);
		System.out.println("\nThe Schemas are compatible");

	}

}
