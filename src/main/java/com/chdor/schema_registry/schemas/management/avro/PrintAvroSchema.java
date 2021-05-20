package com.chdor.schema_registry.schemas.management.avro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.avro.Schema.Field;

import com.chdor.schema_registry.schemas.management.utils.Utils;

import io.confluent.kafka.schemaregistry.ParsedSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchemaProvider;
import io.confluent.kafka.schemaregistry.client.rest.entities.SchemaReference;

/**
 * A simple class used to print AVRO Schema informations
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
public class PrintAvroSchema {

	public static void main(String[] args) {

		// Set the AVRO Schema to be loaded
		String avroSchemaFile = "TVShow.avsc";

		// Build an AvroSchemaProvider
		AvroSchemaProvider avroSchemaProvider = new AvroSchemaProvider();
		// As the schema has no Schemas References, set an empty list
		List<SchemaReference> references = new ArrayList<>();
		// Simply load the AVRO Schema TVSeriesActor1.avsc as String
		String avroSchemaString = Utils.load("avro-schema/".concat(avroSchemaFile));
		// If the schema is successfully parsed, return an AVRO Schema Instance
		Optional<ParsedSchema> avroParsedSchema = avroSchemaProvider.parseSchema(avroSchemaString, references);
		// Retrieve the effective AVRO schema
		ParsedSchema parsedSchema = avroParsedSchema.get();
		// Cast the schema to AvroSchema
		AvroSchema avroSchema = (AvroSchema) parsedSchema;

		// Prints Schema Infos
		System.out.println("Display some Schema Infos:");
		// Display the Schema Name
		System.out.println("- AvroSchema Name: " + avroSchema.name());
		// Display the Schema Type
		System.out.println("- AvroSchema Type: " + avroSchema.schemaType());
		// Display the Schema Doc
		System.out.println("- AvroSchema Doc: " + avroSchema.rawSchema().getDoc());
		// Display the Schema Version
		System.out.println("- AvroSchema version: " + avroSchema.rawSchema().getProp("version"));
		// Display the Raw Schema Name
		System.out.println("- Raw Schema Name: " + avroSchema.rawSchema().getName());
		// Display the Schema Full Name
		System.out.println("- Raw Schema Full Name: " + avroSchema.rawSchema().getFullName());
		// Display the Schema Namespace
		System.out.println("- Raw Schema Namespace: " + avroSchema.rawSchema().getNamespace());
		List<Field> fields = avroSchema.rawSchema().getFields();
		if (fields != null && !fields.isEmpty()) {
			// Display the Schema Fields Infos
			System.out.println("- Schema Fields:");
			for (Field field : fields) {
				System.out.println("  - Field name: " + field.name());
				System.out.println("  - Field doc: " + field.doc());
				System.out.println("  - Field position: " + field.pos());
				System.out.println("  - Field type: " + field.schema().getType());
				System.out.println("  - Field schema: " + field.schema().toString(true));
				System.out.println("  - Field schema default value: "+field.defaultVal());
				System.out.println("");
			}
		}
	}
	
}
