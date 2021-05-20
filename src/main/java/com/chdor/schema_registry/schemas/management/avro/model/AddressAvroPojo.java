package com.chdor.schema_registry.schemas.management.avro.model;

import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.AvroSchema;

@AvroDoc(value = "User Address")
public class AddressAvroPojo {
		
		@AvroSchema(value = "\"string\"")
		@AvroDoc(value = "User Country name")
		private String country = null;
		
		@AvroDoc(value = "User City name")
		private String city = null;

		@AvroDoc(value = "City Zip Code")
		private Integer zipCode = null;

		@AvroDoc(value = "User street address")
		private String streetName = null;

		@AvroDoc(value = "User street number address")
		private Integer streetNumber = null;


		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public AddressAvroPojo withCountry(String country) {
			this.country = country;
			return this;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public AddressAvroPojo withCity(String city) {
			this.city = city;
			return this;
		}

		public String getStreet() {
			return streetName;
		}

		public void setStreet(String street) {
			this.streetName = street;
		}

		public AddressAvroPojo withStreet(String streetName) {
			this.streetName = streetName;
			return this;
		}

		
		public Integer getStreetNumber() {
			return streetNumber;
		}

		public void setStreetNumber(Integer streetNumber) {
			this.streetNumber = streetNumber;
		}

		public AddressAvroPojo withStreetNumber(Integer streetNumber) {
			this.streetNumber = streetNumber;
			return this;
		}

		
		public Integer getZipCode() {
			return zipCode;
		}

		public void setZipCode(Integer zipCode) {
			this.zipCode = zipCode;
		}

		public AddressAvroPojo withZipCode(Integer zipCode) {
			this.zipCode = zipCode;
			return this;
		}
		
		
}
