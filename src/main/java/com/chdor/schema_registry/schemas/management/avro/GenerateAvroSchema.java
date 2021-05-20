package com.chdor.schema_registry.schemas.management.avro;

import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;

import com.chdor.schema_registry.schemas.management.avro.model.TVShowAvroPojo;

import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchemaUtils;

/**
 * A simple class used to generate AVRO Schema from POJO classes annotated with 'AvroSchema' annotations
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
public class GenerateAvroSchema {

	public static void main(String[] args) {

		generateAvroSchemaFromPojoWithSchemaRegistryAPI();
		generateAvroSchemaFromPojoWithAvroAPI();
	}

	/**
	 * Generates AVRO Schemas using Confluent Schema Registry API
	 */
	public static void generateAvroSchemaFromPojoWithSchemaRegistryAPI() {
		// Generate an AVRO Schema from our class: TVShowAvroPojo
		// The last parameter is to choose to remove Java Properties or not.		
		// By default Avro uses CharSequence for the String representation, the following syntax allows you to overwrite the default behavior
		// and use java.lang.String as the String type for the instances of the fields declared like this:
		// "type": {
        //   "type": "string",
        //   "avro.java.string": "String"
        // }

		// Using AvroSchemaUtils to infer the AVRO Schema from our class
		Schema schema = AvroSchemaUtils.getSchema(new TVShowAvroPojo(), true, true);
		System.out.println("AVRO Schema generated from POJO with Confluent Schema Registry API (AvroSchemaUtils):\n"
				+ schema.toString(true));
		
		// Using AvroSchemaUtils to infer the AVRO Schema from our class
		AvroSchema avroSchema = new AvroSchema(AvroSchemaUtils.getSchema(new TVShowAvroPojo(), true, true));
		System.out.println("AVRO Schema generated from from POJO with Confluent AvroSchema API:\n"
				+ avroSchema.rawSchema().toString(true));
	}

	/**
	 * Generates AVRO Schemas using Apache Avro API
	 */
	public static void generateAvroSchemaFromPojoWithAvroAPI() {
		// Generates AVRO Schema from TVShowAvroPojo Avro Pojo annoted class
		Schema schema = ReflectData.get().getSchema(TVShowAvroPojo.class);
		System.out.println("AVRO Schema generated from POJO with AVRO API:\n" + schema.toString(true));
	}
}
