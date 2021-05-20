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
		
		// (1) Set the AVRO Schema to be loaded
		String avroSchemaFile = "TVShow.avsc";

		// (2) Build an AvroSchemaProvider
		AvroSchemaProvider avroSchemaProvider = new AvroSchemaProvider();
		// (3) As the schema has no Schemas References, set an empty list
		List<SchemaReference> references = new ArrayList<>();
		// (4) Simply load the AVRO Schema TVSeriesActor1.avsc as String
		String avroSchemaString = Utils.load("avro-schema/".concat(avroSchemaFile));
		// (5) If the schema is successfully parsed, return an AVRO Schema Instance
		Optional<ParsedSchema> avroParsedSchema = avroSchemaProvider.parseSchema(avroSchemaString, references);
		// (6) Retrieve the effective AVRO schema
		ParsedSchema parsedSchema = avroParsedSchema.get();
		// (7) Cast the schema to AvroSchema
		AvroSchema avroSchemaV1 = (AvroSchema) parsedSchema;

		// (8) Display the Schema version 1 (orignal one)
		System.out.println("Original AVRO Schema - V1:\n" + avroSchemaV1.rawSchema().toString(true));

		List<Schema.Field> listOfSchemaV1Fields = avroSchemaV1.rawSchema().getFields();
		Schema.Field newField = null;
		
		// Build the V2 Schema fields
		List<Schema.Field> listOfSchemaV2Fields = new ArrayList<>();
		
		// Browse the V1 Schema fields to performs modification on fields
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
			
			listOfSchemaV2Fields.add(newField);
		}
		
		Schema  optionalNullString = SchemaBuilder.unionOf().nullType().and().stringType().endUnion();
		newField = new Schema.Field("synopsis", optionalNullString);

		// Same
		//ArrayList<Schema> optionalString = new ArrayList<Schema>(Arrays.asList(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING)));
		//newField = new Schema.Field("synopsis", Schema.createUnion(optionalString));
		
		listOfSchemaV2Fields.add(newField);
		
		Schema schemav2 = Schema.createRecord(avroSchemaV1.name(), avroSchemaV1.rawSchema().getDoc()+" - V2",
				avroSchemaV1.rawSchema().getNamespace(), false);

		schemav2.setFields(listOfSchemaV2Fields);

		// Pretty Print the updated Schema
		System.out.println("Updated Schema - V2:\n" + schemav2.toString(true));
	}
}
