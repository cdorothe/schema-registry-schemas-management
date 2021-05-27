
package com.chdor.schema_registry.schemas.management.json.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Character
 * <p>
 * A tiny Series character identity card
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "firstname",
    "age",
    "lastname"
})
@Generated("jsonschema2pojo")
public class Character {

    /**
     * Character's firstname
     * 
     */
    @JsonProperty("firstname")
    @JsonPropertyDescription("Character's firstname")
    private String firstname = "Mark";
    /**
     * Character's age
     * 
     */
    @JsonProperty("age")
    @JsonPropertyDescription("Character's age")
    private Integer age = 30;
    /**
     * Character's lastname
     * 
     */
    @JsonProperty("lastname")
    @JsonPropertyDescription("Character's lastname")
    private String lastname = "Harris";

    /**
     * No args constructor for use in serialization
     * 
     */
    public Character() {
    }

    /**
     * 
     * @param firstname
     * @param age
     * @param lastname
     */
    public Character(String firstname, Integer age, String lastname) {
        super();
        this.firstname = firstname;
        this.age = age;
        this.lastname = lastname;
    }

    /**
     * Character's firstname
     * 
     */
    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    /**
     * Character's firstname
     * 
     */
    @JsonProperty("firstname")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Character withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    /**
     * Character's age
     * 
     */
    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    /**
     * Character's age
     * 
     */
    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    public Character withAge(Integer age) {
        this.age = age;
        return this;
    }

    /**
     * Character's lastname
     * 
     */
    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    /**
     * Character's lastname
     * 
     */
    @JsonProperty("lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Character withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Character.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("firstname");
        sb.append('=');
        sb.append(((this.firstname == null)?"<null>":this.firstname));
        sb.append(',');
        sb.append("age");
        sb.append('=');
        sb.append(((this.age == null)?"<null>":this.age));
        sb.append(',');
        sb.append("lastname");
        sb.append('=');
        sb.append(((this.lastname == null)?"<null>":this.lastname));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.firstname == null)? 0 :this.firstname.hashCode()));
        result = ((result* 31)+((this.age == null)? 0 :this.age.hashCode()));
        result = ((result* 31)+((this.lastname == null)? 0 :this.lastname.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Character) == false) {
            return false;
        }
        Character rhs = ((Character) other);
        return ((((this.firstname == rhs.firstname)||((this.firstname!= null)&&this.firstname.equals(rhs.firstname)))&&((this.age == rhs.age)||((this.age!= null)&&this.age.equals(rhs.age))))&&((this.lastname == rhs.lastname)||((this.lastname!= null)&&this.lastname.equals(rhs.lastname))));
    }

}
