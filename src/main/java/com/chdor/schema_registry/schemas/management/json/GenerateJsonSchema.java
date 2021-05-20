package com.chdor.schema_registry.schemas.management.json;

import java.util.function.Supplier;

import com.chdor.schema_registry.schemas.management.json.model.TVShowPojoAnnoted;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjetland.jackson.jsonSchema.JsonSchemaConfig;
import com.kjetland.jackson.jsonSchema.JsonSchemaDraft;
import com.kjetland.jackson.jsonSchema.JsonSchemaGenerator;
import com.kjetland.jackson.jsonSchema.SubclassesResolver;
import com.kjetland.jackson.jsonSchema.SubclassesResolverImpl;

import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.json.JsonSchema;
import io.confluent.kafka.schemaregistry.json.JsonSchemaUtils;
import io.confluent.kafka.schemaregistry.json.SpecificationVersion;
import scala.Option;
/**
 * A simple class used to generate JSON Schema from POJO
 * @author Christophe Dorothé</br>
 * Contact: kristophe.dorothe@gmail.com
 *
 */
public class GenerateJsonSchema {

	public static void main(String[] args) throws Exception {
		generateJsonSchemaWithConfluentSchemaRegistry();
		generateJsonSchemaWithJacksonJsonSchemaGenerator();
	}

	/**
	 * Generate JsonSchema with Confluent SchemaRegistry API
	 * @throws Exception
	 */
	public static void generateJsonSchemaWithConfluentSchemaRegistry() throws Exception {
		// Default basic JSON Schema Generation from our TVShowPojo class
		JsonSchema jsonSchema = JsonSchemaUtils.getSchema(new TVShowPojoAnnoted());
		System.out.println("JSON Schema generated from POJO with Confluent Schema Registry:\n"
				+ jsonSchema.toJsonNode().toPrettyString());

		// Disable the oneOf declaration 
		CachedSchemaRegistryClient cachedSchemaRegistryClient = new CachedSchemaRegistryClient("http://weatherwax:8081",
				5);

		// Generate our JSON Schema
		jsonSchema = JsonSchemaUtils.getSchema(new TVShowPojoAnnoted(), SpecificationVersion.DRAFT_7, false,
				cachedSchemaRegistryClient);

		System.out.println("JSON Schema generated from POJO with Confluent Schema Registry (oneOf is disabled):\n"
				+ jsonSchema.toJsonNode().toPrettyString());
	}
	
	/**
	 * Generate JsonSchema with Jackson Json SchemaGenerator API
	 */
	public static void generateJsonSchemaWithJacksonJsonSchemaGenerator() {
		ObjectMapper mapper = new ObjectMapper();
		// Set the Various parameters
		// Do not enable Title generated
		// By default, without control, the JSON Schema Title name is build using
		// camelcase
		Boolean autoGenerateTitleForProperties = false;
		// By this way we can specify the array dfault format
		Option<String> defaultArrayFormat = new Option<String>() {
			
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
				// TODO Auto-generated method stub
				return "CharacterPojo Schema";
			}
		};
		
		// Enable or disable 'oneOf' for options declaration 
		Boolean useOneOfForOption = false;
		// Enable or disable 'oneOf' declaration 
		Boolean useOneOfForNullables = false;
		// Enable or disable the 'propertyOrder' declaration
		Boolean usePropertyOrdering = true;
		Boolean hidePolymorphismTypeProperty = true;
		Boolean disableWarnings = false;
		Boolean useMinLengthForNotNull = false;
		Boolean useTypeIdForDefinitionName = false;
		// Set to empty map to prevent exception
		scala.collection.immutable.Map<String, String> customType2FormatMapping = new scala.collection.immutable.HashMap<String, String>();
		// https://github.com/jdorn/json-editor/issues/709
		Boolean useMultipleEditorSelectViaProperty = false;
		// If rendering array and type is instanceOf class in this set, then we add 'uniqueItems": true' to schema - See // https://github.com/jdorn/json-editor for more info
		scala.collection.immutable.Set<Class<?>> uniqueItemClasses = new scala.collection.immutable.HashSet<>();
		// Can be used to prevent rendering using polymorphism for specific classes. 
		// Set to empty map to prevent exception
		scala.collection.immutable.Map<Class<?>, Class<?>> classTypeReMapping = new scala.collection.immutable.HashMap<Class<?>, Class<?>>();
		// Suppliers in this map can be accessed using @JsonSchemaInject(jsonSupplierViaLookup = "lookupKey")
		scala.collection.immutable.Map<String, Supplier<JsonNode>> jsonSuppliers = null;
		// Using default impl that scans entire classpath
		SubclassesResolver subclassesResolver = new SubclassesResolverImpl();
		// Set "additionalProperties" to false
		Boolean failOnUnknownProperties = true;
		// Used to match against different validation-groups (javax.validation.constraints)
		Class<?>[] javaxValidationGroups = new Class<?>[0];
		// I select the $schema draft version
		JsonSchemaDraft jsonSchemaDraft = JsonSchemaDraft.DRAFT_07;

		// Build Json Schema Config
		JsonSchemaConfig jsonSchemaConfig = new JsonSchemaConfig(autoGenerateTitleForProperties, defaultArrayFormat,
				useOneOfForOption, useOneOfForNullables, usePropertyOrdering, hidePolymorphismTypeProperty,
				disableWarnings, useMinLengthForNotNull, useTypeIdForDefinitionName, customType2FormatMapping,
				useMultipleEditorSelectViaProperty, uniqueItemClasses, classTypeReMapping, jsonSuppliers,
				subclassesResolver, failOnUnknownProperties, javaxValidationGroups, jsonSchemaDraft);
		jsonSchemaConfig.withJsonSchemaDraft(jsonSchemaDraft);

		// Build our JsonSchemaGenerator with our configuration 
		JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(mapper, jsonSchemaConfig);
		// Defines the Title Schema. Prevents the camelCase transformation
		String title = "TVShow";
		// Defines a Schema Description
		String description = "Descriptive card of series of the year 1970- 1980";
		// Generate our JSON Schema from TVShowPojo.class using our JsonSchemaGenerator
		JsonNode schemaJsonNode = jsonSchemaGenerator.generateJsonSchema(TVShowPojoAnnoted.class, title, description);
		System.out.println(
				"JSON Schema generated from POJO with Jackson jsonSchema Generator:\n" + schemaJsonNode.toPrettyString());
		
		// Convert the  generated Schema into a JsonSchema
		JsonSchema jsonSchema = new JsonSchema(schemaJsonNode);
		// Fails if schema is invalid
		jsonSchema.validate();
		
		System.out.println(
				"JSON Schema generated converts into JsonSchema:\n" + jsonSchema.rawSchema().toString());
	}
}
