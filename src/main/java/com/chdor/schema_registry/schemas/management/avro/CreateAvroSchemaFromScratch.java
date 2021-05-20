package com.chdor.schema_registry.schemas.management.avro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.SchemaBuilder;

import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchemaProvider;
import io.confluent.kafka.schemaregistry.avro.AvroSchemaUtils;
import io.confluent.kafka.schemaregistry.client.rest.entities.SchemaReference;


/**
 * A simple class used to create Avro Schemas by "hand"
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */

public class CreateAvroSchemaFromScratch {

	public static void main(String[] args) {

		createAvroSchemaWithSchemaBuilder();
		createAvroSchemaWithConfluentSchemaRegistry();

	}

	/**
	 * Create an AVRO Schema using <b>org.apache.avro.SchemaBuilder</b>.</br></br>
	 * It's a fluent interface for building Schema instances. The flow of the API is designed to mimic the Avro Schema Specification
	 */
	public static void createAvroSchemaWithSchemaBuilder() {

		// Create an optional field, that means an union of type.
		// Here: [NULL,Integer]
		Schema  optionalNullString = SchemaBuilder.unionOf().nullType().and().stringType().endUnion();
		
		// Create AVRO Schema Character - A tiny Series character identity card
		Schema character =
				// Create an AVRO record and set its name, namespace and doc fields
				SchemaBuilder.record("Character")
						.namespace("com.chdor.schema_registry.schemas.management.avro.model")
						.doc("A tiny Series character identity card")
						// Add fields
						.fields()
						// Defines Character Fisrt  Name
						.name("firstName").doc("Character fisrt name").type(optionalNullString).noDefault()
						// Defines Character LastName  Name
						.name("lastName").doc("Character last name").type(optionalNullString).withDefault(null)
						// Defines Optional [NULL, String] Character age
						.name("age").doc("Character Age").type().intType().noDefault()
						.endRecord();

		System.out.println("Main Actors of the Serie - Character AVRO Schema:\n" + character.toString(true) + "\n");

		// Create AVRO Schema - Serie Avro record
		Schema tvShow =
				// Create an AVRO record and set its name, namespace and doc fields
				SchemaBuilder.record("TVShow")
						.namespace("com.chdor.schema_registry.schemas.management.avro.model")
						.doc("Descriptive card of series of the year 1970- 1980")
						// Add fields
						.fields()
						// Defines Serie Name
						.name("name").doc("Name of the Serie").orderAscending().type().stringType().noDefault()
						// Defines Serie release date
						.name("date").doc("Serie release date").orderAscending().type().intType().intDefault(-1)
						// Defines a list Serie Characters
						.name("characters").doc("A tiny Series character identity card").orderAscending().type(Schema.createArray(character)).noDefault()
						.name("synopsis").doc("Serie quick scenario description").orderDescending().type(optionalNullString).withDefault(null)
						.endRecord();

		System.out.println("TV Show Schema:\n" + tvShow.toString(false) + "\n");

		// Create AVRO Schema - Serie Avro record with a one tiny Series character identity card
		// It shows how to directly create a nested record ad how to use Field
		Schema tvShowCharacter =
				// Create an AVRO record and set its name, namespace and doc fields
				SchemaBuilder.record("TVShowWithCharacters")
						.namespace("com.chdor.schema_registry.schemas.management.avro.model")
						.doc("Descriptive card of series of the year 1970- 1980")
						// Add fields
						.fields()
						.name("name").doc("Name of the Serie").orderAscending().type().stringType().noDefault()
						.name("date").doc("Serie release date").orderAscending().type().intType().intDefault(-1)
						.name("characters").orderAscending().type(Schema.createRecord("Character", "A tiny Series character identity card","com.chdor.schema_registry.schemas.management.avro.model", false,
								new ArrayList<Field>(Arrays.asList(
										new Field("firstName", Schema.create(Schema.Type.STRING),"Character Fisrt Name", "John"),
										new Field("lastName", Schema.create(Schema.Type.STRING), "Character Last Name","Doe"))))).noDefault()
						.endRecord();

		System.out.println("Serie Avro record with a one tiny Series character identity card - AVRO Schema:\n" + tvShowCharacter.toString(true) + "\n");

		// Another way to create AVRO Schema - Serie Avro record with a one tiny Series character identity card
		// It shows how to directly create a schema corresponding to our nested record
		Schema tvShowCharacter1 =
				// Create an AVRO record and set its name, namespace and doc fields
				SchemaBuilder.record("TVShowWithOrdering")
						.namespace("com.chdor.schema_registry.schemas.management.avro.model")
						.doc("Descriptive card of series of the year 1970- 1980")
						// Add fields
						.fields()
						.name("name").doc("Name of the Serie").orderAscending().type(optionalNullString).noDefault()
						.name("date").doc("Serie release date").orderAscending().type().intType().intDefault(-1)
						.name("characters").doc("Descriptive card of hero actors").orderAscending().type(Schema.createArray(
								SchemaBuilder.record("Character").namespace("com.chdor.schema_registry.schemas.management.avro.model").doc("A tiny Series character identity card")
								// Add fields
								.fields().name("firstName").doc("Character fisrt name").type().stringType().noDefault()
								.name("lastName").doc("Character last name").type().stringType().stringDefault("Doe")
								.endRecord())).noDefault()
						.endRecord();
		
		System.out
				.println("TV Show Schema with ordering properties:\n" + tvShowCharacter1.toString(true) + "\n");

		
		// Convert the org.apache.avro.Schema to io.confluent.kafka.schemaregistry.avro.AvroSchema
		AvroSchema avroSchema = new AvroSchema(tvShow);
		avroSchema.validate();
		
		AvroSchemaProvider avroSchemaProvider = new AvroSchemaProvider();
		ParsedSchema parsedSchema = avroSchemaProvider.parseSchema(tvShow.toString(true), new ArrayList<>())
				.get();
		avroSchema = (AvroSchema) parsedSchema;
		avroSchema.validate();

		System.out.println("TV Show AvroSchema:\n" + avroSchema.rawSchema().toString(true));
		
	}
	
	/**
	 * Create an AVRO Schema using only Confluent Schema Registry API</br>
	 * Two methods:</br>
	 * &nbsp;&nbsp;&nbsp; 1. with <b>io.confluent.kafka.schemaregistry.avro.AvroSchema</b></br>
	 * &nbsp;&nbsp;&nbsp; 2. with <b>io.confluent.kafka.schemaregistry.avro.AvroSchemaProvider</b></br></br>
	 * @throws Exception
	 */
	public static void createAvroSchemaWithConfluentSchemaRegistry() {
		// Defines some AVRO Schema variables
		// The Schema String
		String initialSchema = "{\"type\":\"record\",\"name\":\"TVShow\",\"namespace\":\"com.chdor.schema_registry.schemas.management.avro.model\",\"doc\":\"Descriptive card of series of the year 1970- 1980\",\"fields\":[{\"name\":\"name\",\"type\":\"string\",\"doc\":\"Name of the Serie\"},{\"name\":\"date\",\"type\":\"int\",\"doc\":\"Serie release date\",\"default\":-1},{\"name\":\"characters\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Character\",\"doc\":\"A tiny Series character identity card\",\"fields\":[{\"name\":\"firstName\",\"type\":[\"null\",\"string\"],\"doc\":\"Character fisrt name\"},{\"name\":\"lastName\",\"type\":[\"null\",\"string\"],\"doc\":\"Character last name\",\"default\":null},{\"name\":\"age\",\"type\":\"int\",\"doc\":\"Character Age\"}]}},\"doc\":\"A tiny Series character identity card\"}]}";
		// The Schema has no Schemas references
		List<SchemaReference> references = Collections.emptyList();
		// The Schema has no Schemas resolved references
		Map<String, String> resolvedReferences = Collections.emptyMap();
		// The Schema version
		Integer version = 1;
		// Flag to indicate that this Schema is a new Schema
		boolean isNew = true;

		// Create AvroSchema
		AvroSchema avroSchema = new AvroSchema(initialSchema, references, resolvedReferences, version, isNew);
		// Validates the schema and ensures all references are resolved properly. Throws an exception if the schema is not valid.
		avroSchema.validate();
		System.out.println("Create Avro Schema with AvroSchema:\n" + avroSchema.rawSchema().toString(true) + "\n");

		// Create AVRO Schema with AvroSchemaProvider
		AvroSchemaProvider avroSchemaProvider = new AvroSchemaProvider();
		// Use AvroSchemaProvider to parse the Avro Schema String and return an optional ParsedSchema
		Optional<ParsedSchema> optionalParsedSchema = avroSchemaProvider.parseSchema(initialSchema, references, isNew);
		// Get the ParseSchema
		ParsedSchema parsedSchema = optionalParsedSchema.get();
		// Convert the ParsedSchema into AvroScheme
		avroSchema = (AvroSchema) parsedSchema;
		// Validates the schema and ensures all references are resolved properly. Throws an exception if the schema is not valid.
		avroSchema.validate();
		System.out.println("Create Avro Schema with AvroSchemaProvider:\n" + avroSchema.rawSchema().toString(true) + "\n");

		// Display AVRO Schema Primitives supported
		System.out.println("AvroSchema Primitives:");
		Map<String, Schema> primitiveSchemas = AvroSchemaUtils.getPrimitiveSchemas();
		primitiveSchemas.forEach((k, v) -> System.out.println("Key: " + k + " / value: " + v));
	}
}
