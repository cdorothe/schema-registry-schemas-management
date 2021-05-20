package com.chdor.schema_registry.schemas.management.utils;

/**
 * JsonSchemaDraftVersion</br>
 * @author Christophe Dorothé</br>
 * email: kristophe.dorothe@gmail.com</br>
 * Last modified: 2021-02
 *
 **/
public enum JsonSchemaDraftVersion {
	DRAFT_04("http://json-schema.org/draft-04/schema#"), DRAFT_06("http://json-schema.org/draft-06/schema#"),
	DRAFT_07("http://json-schema.org/draft-07/schema#"), DRAFT_2019_09("http://json-schema.org/draft/2019-09/schema#");

	private String url;

	private JsonSchemaDraftVersion(String url) {
		this.url = url;
	}

	public String url() {
		return this.url;
	}
}
