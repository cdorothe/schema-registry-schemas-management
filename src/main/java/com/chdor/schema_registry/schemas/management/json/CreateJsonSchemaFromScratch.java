package com.chdor.schema_registry.schemas.management.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.everit.json.schema.ArraySchema;
import org.everit.json.schema.NumberSchema;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.ReferenceSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.StringSchema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import com.chdor.schema_registry.schemas.management.utils.JsonSchemaDraftVersion;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.kjetland.jackson.jsonSchema.JsonSchemaConfig;
import com.kjetland.jackson.jsonSchema.JsonSchemaDraft;
import com.kjetland.jackson.jsonSchema.JsonSchemaGenerator;

import io.confluent.kafka.schemaregistry.client.rest.entities.SchemaReference;
import io.confluent.kafka.schemaregistry.json.JsonSchema;
import scala.Option;

/**
 * A simple class used to create JSON Schema by "hand"
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
public class CreateJsonSchemaFromScratch {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		createJsonSchemaWithJsonSchemaValidator();
		createJsonSchemaWithSchemaRegistry();
		
		//createJsonSchemaWithJsonSchemaGenerator();
		
		//createJsonSchemaWithVictools();
	}

	/**
	 * Create a JSON Schema using JSON Schema Validator: https://github.com/everit-org/json-schema
	 * 
	 */
	public static void createJsonSchemaWithJsonSchemaValidator() throws Exception {
		// Build a map to store "unprocessed properties", that means schema properties not managed by the API
		// It's the case for $schema, additional properties and what you want
		Map<String, Object> schemaOpts = new HashMap<String, Object>();
		// Define the '$schema' draft version to "http://json-schema.org/draft-07/schema#"
		schemaOpts.put("$schema", JsonSchemaDraftVersion.DRAFT_07.url());
		// Sets 'additionalProperties' to 'false'
		schemaOpts.put("additionalProperties", false);
		
		//SchemaLocation schemaLocation = SchemaLocation.parseURI("#/definitions/Character");

		// Create the Character Schema
		org.everit.json.schema.Schema characterSchema = ObjectSchema.builder()
				// Define a field named "firstname" as String with a null default value 
				.addPropertySchema("firstname", StringSchema.builder().defaultValue("John").build())
				// Define a field named "lastname" as String with a null default value 
				.addPropertySchema("lastname", StringSchema.builder().defaultValue("Doe").build())
				// Define a field named "age" as Integer with a 0 as default value 
				.addPropertySchema("age", NumberSchema.builder().requiresInteger(false).defaultValue(0).build())
				// Just to show you how to define an Array and the type of its items
				// .addPropertySchema("testArray", ArraySchema.builder().additionalItems(true).allItemSchema(StringSchema.builder().build())
				// .build())
				
				// Sets $schema and additionalProperties values 
				.unprocessedProperties(schemaOpts)
				// Sets $id value
				.id("http://com.chdor.schema_registry.schemas.management.json.model")
				// Sets Schema name. By using this way, we prevent the name of the schema to be auto compute with a Camel Case algorithm 
				.title("Character")
				// Sets Schema description. 
				.description("A tiny Series character identity card").build();

		// Display the Character Schema in pretty mode 
		System.out.println("Chacracter:\n"+new JSONObject(characterSchema.toString()).toString(2));
		
		// Build a map to store "unprocessed properties", that means schema properties not managed by the API
		// It's the case for $schema, additional properties and what you want.
		// Just in case if we need to redefine the properties
		schemaOpts = new HashMap<String, Object>();
		schemaOpts.put("$schema", JsonSchemaDraftVersion.DRAFT_07.url());
		// Sets 'additionalProperties' to 'false'
		schemaOpts.put("additionalProperties", false);
		
		/*
		String jsonString = characterSchema.toString();
		JsonNodeFactory jsonNodeFactory = new JsonNodeFactory(true);
		JsonNode jsonNode = jsonNodeFactory.objectNode();
		((ObjectNode) jsonNode).put("Character", jsonString);

		String definitions = "\"Character\" : {\n"
				+ "      \"type\" : \"object\",\n"
				+ "      \"additionalProperties\" : false,\n"
				+ "      \"properties\" : {\n"
				+ "        \"firstname\" : {\n"
				+ "          \"propertyOrder\" : 1,\n"
				+ "          \"type\" : \"string\"\n"
				+ "        },\n"
				+ "        \"lastname\" : {\n"
				+ "          \"propertyOrder\" : 2,\n"
				+ "          \"type\" : \"string\"\n"
				+ "        },\n"
				+ "        \"age\" : {\n"
				+ "          \"propertyOrder\" : 3,\n"
				+ "          \"type\" : \"integer\"\n"
				+ "        }\n"
				+ "      }\n"
				+ "    }";
		
		
		
		definitions = "{"+definitions+"}";

		ObjectMapper mapper=new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JSONObject originalJson = mapper.readValue(characterSchema.toString(), JSONObject.class);	
		*/
		
		// As TVShow field 'characters' is an array of reference to Character schema ($ref), we need to define a "definitions" declaration which describes the Character object Schema
		// To do this, I creates a JSONObject that contains Character Schema declaration
		// To add the "definitions" field to my schema, I uses the "unprocessed properties" functionality.
		// If it exits a way to do that without this hack, I haven't found it yet!  
		JSONObject definitions = buildDefinitions(characterSchema);
		schemaOpts.put("definitions", definitions);

		// Build the reference schema 
		ReferenceSchema referenceSchema = new ReferenceSchema.Builder().refValue("#/definitions/Character")
				.build();

		// Builds and array and defines the type of its items: {"$ref": "#/definitions/Character"} 
		ArraySchema.Builder characterArrayBuilder = new ArraySchema.Builder();
		//characterArrayBuilder.addItemSchema(characterSchema);
		//arrayBuilder.addItemSchema(referenceSchema);
		//arrayBuilder.defaultValue(null);
		characterArrayBuilder.allItemSchema(referenceSchema);

		
		// Build the TVShow JSON Schema
		org.everit.json.schema.Schema tvshowSchema = ObjectSchema.builder()
				// Define a field named "name" as String  
				.addPropertySchema("name", StringSchema.builder().build())
				// Define a field named "date" as String  
				.addPropertySchema("date", StringSchema.builder().build())
				// Define a field named "characters" as array of Character  
				.addPropertySchema("characters", characterArrayBuilder.build())
				// Sets $id value 
				.unprocessedProperties(schemaOpts).id("http://com.chdor.schema_registry.schemas.management.json.model")
				// Sets Schema name. By using this way, we prevent the name of the schema to be auto compute with a Camel Case algorithm 
				.title("TVShow1")
				// Sets Schema description. 
				.description("Descriptive card of series of the year 1970- 1980")
				.build();

		// Performs a Hack to add a "propertyOrder" property to all fields declared in the Schema "properties" field.  
		// To de this, I transforms the schema into a JSONObject
		JSONObject jsonObject = new JSONObject(tvshowSchema.toString());
		// I redefines the "properties" fields
		Map<String, Object> newFields = buildPropertyOrder(jsonObject.toMap().get("properties"));
		// I replaces the old version of properties fields with the new one
		jsonObject.put("properties", newFields);
		
		// Display the TVShow Schema (only Character field have 'propertyOrder"
		System.out.println("Create JSON Schema from scratch with Schema Validator:\n"
				+ new JSONObject(tvshowSchema.toString()).toString(2));

		// Load the new TVShow JSON Schema with all propertyOrder with the Draft Version specified
		//SchemaLoader loader = SchemaLoader.builder().schemaJson(new JSONObject(tvshowSchema.toString())).draftV7Support().build();
		SchemaLoader loader = SchemaLoader.builder().schemaJson(jsonObject).draftV7Support().build();
		tvshowSchema = loader.load().build();
		
		System.out.println("JSON Schema rebuild with all propertyOrder:\n"
				+ new JSONObject(tvshowSchema.toString()).toString(2));

	}
	
	/**
	 * Build JSON Schema "definitions" field
	 * @param schema
	 * The schema to bind to "definitions" field 
	 * @return
	 * The full "definitions" property settings
	 */
	private static JSONObject buildDefinitions(org.everit.json.schema.Schema schema) {
		JSONObject schemaJSONObject = new JSONObject(schema.toString());

		Map<String,Object> mapOfSchemaFields = schemaJSONObject.toMap();
		Map<String,Object> definitionFields = new HashMap<>();
		Map<String,Map<String,Object>> definitions = new HashMap<>();
		
		definitionFields.put("type", mapOfSchemaFields.get("type"));
		definitionFields.put("description", mapOfSchemaFields.get("description"));
		definitionFields.put("additionalProperties", mapOfSchemaFields.get("additionalProperties"));
		definitionFields.put("properties", buildPropertyOrder(schemaJSONObject.toMap().get("properties")));
		definitionFields.put("$id", mapOfSchemaFields.get("$id"));
		
		definitions.put((String)mapOfSchemaFields.get("title"), definitionFields);
		return new JSONObject(definitions);
	}
	
	/**
	 * Rebuild the "properties" fields to add to the fields the 'propertyOrder'
	 * @param object
	 * A Map of all Schema "properties" elemnts
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<String,Object> buildPropertyOrder(Object object) {
		//@SuppressWarnings("unchecked")
		Map<String,Object> fields = (Map<String,Object>)object; 
		int propertyOrder = 1;
		Map<String,Object> newFields = null;
		
		for (Map.Entry<String, Object> entry:fields.entrySet() ) {
			newFields = (Map<String,Object>)entry.getValue();
			newFields.put("propertyOrder", propertyOrder++);
		}
		System.out.println();
		return fields;
		
		
	}
	
	/**
	 * An unfinished method using mbknor-jackson-jsonschema
	 * As the JSON Schema is "formalized" as a JSONNode, we can use the same techniques used in
	 * createJsonSchemaWithJsonNode
	 */
	public static void createJsonSchemaWithJsonSchemaGenerator() {
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		
		JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(mapper);
		JavaType javaType = TypeFactory.unknownType();
		
		Option<String> title = new Option<String>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -2288084483972365715L;

			@Override
			public boolean canEqual(Object that) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object productElement(int n) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int productArity() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String get() {
				return "TVShow";
			}
		};
		
		Option<String> description = new Option<String>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -7165262447724381314L;

			/**
			 * 
			 */

			@Override
			public boolean canEqual(Object that) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object productElement(int n) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int productArity() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String get() {
				// TODO Auto-generated method stub
				return "Descriptive card of series of the year 1970- 1980";
			}
		};

		JsonNode jsonNode = jsonSchemaGenerator.generateJsonSchema(javaType, title, description);
		jsonNode.fields();
		//$schema="http://json-schema.org/draft-04/schema#"
		//title="TVShow"
		//description="Descriptive card of series of the year 1970- 1980"
		ObjectNode objectNode = (ObjectNode)jsonNode;
		objectNode.fields();
		
		//((ObjectNode) jsonNode).put("type", "object");
		
	}
	
	
	/**
	 * 
	 */
	public static void createJsonSchemaWithSchemaRegistry() {
		// Define JSON Schema string
		String schemaString = "{\"$schema\":\"http://json-schema.org/draft-07/schema#\",\"description\":\"Descriptive card of series of the year 1970- 1980\",\"additionalProperties\":false,\"type\":\"object\",\"title\":\"TVShow1\",\"definitions\":{\"Character\":{\"description\":\"A tiny Series character identity card\",\"additionalProperties\":false,\"type\":\"object\",\"properties\":{\"firstname\":{\"default\":\"John\",\"propertyOrder\":1,\"type\":\"string\"},\"age\":{\"default\":0,\"propertyOrder\":2,\"type\":\"number\"},\"lastname\":{\"default\":\"Doe\",\"propertyOrder\":3,\"type\":\"string\"}},\"$id\":\"http://com.chdor.schema_registry.schemas.management.json.model\"}},\"properties\":{\"date\":{\"propertyOrder\":1,\"type\":\"string\"},\"characters\":{\"propertyOrder\":2,\"type\":\"array\",\"items\":{\"$ref\":\"#/definitions/Character\"}},\"name\":{\"propertyOrder\":3,\"type\":\"string\"}},\"$id\":\"http://com.chdor.schema_registry.schemas.management.json.model\"}";
		// Has no references so defines an empty list
		List<SchemaReference> references = new ArrayList<SchemaReference>();
		// Has no resolvedReferences so defines an empty map
		Map<String,String> resolvedReferences = new HashMap<String, String>();
		// Define JSON Schema version
		Integer version = 1;
		
		// Create JSON Schema
		JsonSchema jsonSchema = new JsonSchema(schemaString, references, resolvedReferences, version);
		jsonSchema.validate();
		System.out.println("Create a JSON Schema with Confluent Schema Registry:\n"+jsonSchema.toString());
		
		// Load the JSON Schema created with Draft Version specified to Draft_7
		SchemaLoader loader = SchemaLoader.builder().schemaJson(new JSONObject(jsonSchema.toString())).draftV7Support().build();
		// Get Schema(org.everit.json.schema.Schema)
		Schema schema = loader.load().build();
		System.out.println("Schema:\n"+schema.toString());
	}
	
	public static void createJsonSchemaWithJsonNode() {
		
			// Define the JSON Draft version 7
			JsonSchemaDraft jsonSchemaDraft = JsonSchemaDraft.DRAFT_07;
			// Create ObjectMapper
			ObjectMapper mapper = new ObjectMapper();
	
			// If using JsonSchema to generate HTML5 GUI:
			// JsonSchemaGenerator html5 = new JsonSchemaGenerator(mapper, JsonSchemaConfig.html5EnabledSchema() );
	
			// Set the JSON Schema draft
			JsonSchemaConfig config = JsonSchemaConfig.vanillaJsonSchemaDraft4().withJsonSchemaDraft(jsonSchemaDraft);
			
			// JavaType javaType = SimpleType.construct(String.class);
			// Base class for type token classes used both to contain information and as
			// keys for deserializers
			
			// Define a unknownType
			JavaType javaType = TypeFactory.unknownType();
			
			// Create the JsonSchemaGenerator
			// (com.kjetland.jackson.jsonSchema.JsonSchemaGenerator)
			// The Schema Draft is trough the config
			JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(mapper, config);
			
			// Create an initial JSON Schema with "title":"TVSeriesActor" and
			// "description":"Short note on TV 1970-1980 Series Actors"
			JsonNode jsonNode = jsonSchemaGenerator.generateJsonSchema(javaType, "TVSeriesActor","Short note on TV 1970-1980 Series Actors");
			// Add the $id field
			((ObjectNode) jsonNode).put("$id", "http://com.chdor.schema_registry.schemas.management");
			
			// Add the type field
			((ObjectNode) jsonNode).put("type", "object");
			
			// Add the additionalProperties field
			((ObjectNode) jsonNode).put("additionalProperties", false);
	
			// Build Base class that specifies methods for getting access to Node instances
			// (newly constructed, or shared, depending on type), as well as basic
			// implementation of the methods.
			JsonNodeFactory factory = new JsonNodeFactory(false);
	
			// Create the schema type: {"type":"string"}
			ObjectNode fieldTypeString = factory.objectNode();
			((ObjectNode) fieldTypeString).put("type", "string");
	
			// Create the schema field: "actor":{"type":"string", "default":"Unknown"}
			//ObjectNode actorTypeStringDefault = factory.objectNode();
			//((ObjectNode) actorTypeStringDefault).put("type", "string");
			//((ObjectNode) actorTypeStringDefault).put("default", "Michael Landon");
	
			// Create the schema field: {"type":"string", "default":"Unknown"}
			ObjectNode fieldTypeStringDefault = factory.objectNode();
			((ObjectNode)fieldTypeStringDefault).put("type", "string");
			((ObjectNode)fieldTypeStringDefault).put("default", "Unknown");
	
	//		// Create the schema field: "lastName":{"type":"string"}
	//		ObjectNode lastName = factory.objectNode();
	//		((ObjectNode)lastName).set("lastName", fieldTypeString);
	//		
	//		// Create the schema field: "lastName":{"type":"string"}
	//		ObjectNode firstName = factory.objectNode();
	//		((ObjectNode)firstName).set("firstName", fieldTypeString);
	
			// Add fields to the properties element
			Map<String, JsonNode> fieldsMap = new HashMap<>();
			fieldsMap.put("lastName", fieldTypeString);
			fieldsMap.put("firstName", fieldTypeString);
			fieldsMap.put("tvShow", fieldTypeStringDefault);
			fieldsMap.put("actor", fieldTypeString);
			JsonNode fields = mapper.valueToTree(fieldsMap);
			((ObjectNode) jsonNode).set("properties", fields);
	
			// Convert JsonNode to JsonSchema
			JsonSchema jsonSchema = new JsonSchema(jsonNode);
			// Validate the Json Schema. If failed, raises an exception
			jsonSchema.validate();
			
			// Display the generated JSON Schema
			System.out.println("Create Schema with JsonNode & ObjectNode:\n" + jsonSchema.toString());
	}
}
