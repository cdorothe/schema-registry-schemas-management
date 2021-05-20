package com.chdor.schema_registry.schemas.management.avro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.SchemaBuilder;

import com.chdor.schema_registry.schemas.management.utils.Utils;

import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchemaProvider;
import io.confluent.kafka.schemaregistry.client.rest.entities.SchemaReference;

/**
 * A simple class used to update AVRO Schema
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
public class UpdateAvroSchema {

	public static void main(String[] args) {
		
		// Set the AVRO Schema to be loaded
		String avroSchemaFile = "TVShow.avsc";

		// Build an AvroSchemaProvider
		AvroSchemaProvider avroSchemaProvider = new AvroSchemaProvider();
		// As the schema has no Schemas References, set an empty list
		List<SchemaReference> references = new ArrayList<>();
		// Simply load the AVRO Schema TVShow.avsc as String
		String avroSchemaString = Utils.load("avro-schema/".concat(avroSchemaFile));
		// If the schema is successfully parsed, return an ParsedSchema instance
		Optional<ParsedSchema> avroParsedSchema = avroSchemaProvider.parseSchema(avroSchemaString, references);
		// Retrieve the effective AVRO schema
		ParsedSchema parsedSchema = avroParsedSchema.get();
		// Cast the schema to AvroSchema
		AvroSchema avroSchemaV1 = (AvroSchema) parsedSchema;

		// Display the Schema version 1 (orignal one)
		System.out.println("Original AVRO Schema - V1:\n" + avroSchemaV1.rawSchema().toString(true));

		// Retrieve all V1 Schema fields
		List<Schema.Field> listOfSchemaV1Fields = avroSchemaV1.rawSchema().getFields();
		
		// Build the V2 Schema fields
		List<Schema.Field> listOfSchemaV2Fields = new ArrayList<>();
		Schema.Field newField = null;
		
		// Browse the V1 Schema fields to perform some modifications.
		// Remember that we cannot modify the Schema V1 fields because they are readOnly.
		// So the solution is to recreate the "old" fields and store the new ones into a list of Schema.field
		// To make it easier for me, I reconstruct the whole definition of the Character field.
		// Another solution would have been to work directly with the Character fields.
		for ( Field field:listOfSchemaV1Fields) {
			if ( field.name().equals("characters")) {
				newField = new Field("characters",
						Schema.createRecord("Character", "A tiny Series character identity card","com.chdor.schema_registry.schemas.management.avro.model", false,
								new ArrayList<Field>(Arrays.asList(
										new Field("firstName", Schema.create(Schema.Type.STRING),"Character Fisrt Name", "John"),
										new Field("lastName", Schema.create(Schema.Type.STRING), "Character Last Name","Doe"),
										new Field("age", Schema.create(Schema.Type.STRING), "Character age type change: int -> string",null),
										new Field("isAlive", Schema.create(Schema.Type.BOOLEAN), "Character isAlive?",true)
										)))						
						,
						"A tiny Series character identity card", null);
			} else {
				newField = new Field(field.name(), field.schema(), field.doc(), field.defaultVal(), field.order());
			}
			// Store the new field
			listOfSchemaV2Fields.add(newField);
		}
		
		// Add an optional 'synopsis' field
		Schema  optionalNullString = SchemaBuilder.unionOf().nullType().and().stringType().endUnion();
		newField = new Schema.Field("synopsis", optionalNullString);

		// Add the 'sysnopsis' to the list of Schema V2 ne fields
		listOfSchemaV2Fields.add(newField);
		
		// Use the Schema V1 main properties to create the Schema V2 'envelop' 
		Schema schemav2 = Schema.createRecord(avroSchemaV1.name(), avroSchemaV1.rawSchema().getDoc()+" - V2",
				avroSchemaV1.rawSchema().getNamespace(), false);
	
		// Add the list of new fields to Schema V2 
		schemav2.setFields(listOfSchemaV2Fields);

		// Pretty Print the updated Schema
		System.out.println("Updated Schema - V2:\n" + schemav2.toString(true));
	}
}
