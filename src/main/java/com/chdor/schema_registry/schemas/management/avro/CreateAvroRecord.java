package com.chdor.schema_registry.schemas.management.avro;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import com.chdor.schema_registry.schemas.management.avro.model.TVShow;
import com.chdor.schema_registry.schemas.management.avro.model.Character;

/**
 * A simple class used to create Avro records based on Avro Schemas
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
public class CreateAvroRecord {

	public static void main(String[] args) {

		// Create AVRO Record using the generated AVRO Java classes
		TVShow tvShow = TVShow.newBuilder()
				.setName("The Wild Wild West")
				.setDate(1965)
				.setCharacters(new ArrayList<>(Arrays.asList(
						Character.newBuilder()
						.setFirstName("Artemus")
						.setLastName("Gordon")
						.setAge(33)
						.build(),
						Character.newBuilder()
						.setFirstName("James")
						.setLastName("T. West")
						.setAge(33)
						.build())))
				.build();
		
		System.out.println("Create an AVRO record using the generated AVRO Java classes:\n" + tvShow.toString());
		System.out.println("- AVRO Schema used:\n" + tvShow.getSchema().toString(true)+"\n");
		
		// Create Record using a "dynamic" schema
		String mySchema = "{\"type\":\"record\",\"name\":\"TVSeriesActor\",\"namespace\":\"com.chdor.schema_registry.example.avro.model\",\"fields\":[{\"name\":\"firstName\",\"type\":\"string\"},{\"name\":\"lastName\",\"type\":\"string\"}]}";
		Schema.Parser parser = new Schema.Parser();
		Schema schema = parser.parse(mySchema);
		GenericRecord avroRecord = new GenericData.Record(schema);
		avroRecord.put("firstName", "Jaimie");
		avroRecord.put("lastName", "Sommers");

		System.out.println("Create an AVRO record using a dynamic AVRO Schema:\n" + avroRecord.toString());
		System.out.println("- AVRO Schema used:\n" + schema.toString(true));
	}
}
