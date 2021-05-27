
package com.chdor.schema_registry.schemas.management.json.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * TVShow
 * <p>
 * Descriptive card of series of the year 1970- 1980
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "date",
    "characters"
})
@Generated("jsonschema2pojo")
public class TVShow {

    /**
     * Serie's release year
     * 
     */
    @JsonProperty("date")
    @JsonPropertyDescription("Serie's release year")
    private Integer date = 1977;
    /**
     * Characters in the Serie
     * 
     */
    @JsonProperty("characters")
    @JsonPropertyDescription("Characters in the Serie")
    private List<Character> characters = new ArrayList<Character>();
    /**
     * Serie's name
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("Serie's name")
    private String name = "Man from Atlantis";

    /**
     * No args constructor for use in serialization
     * 
     */
    public TVShow() {
    }

    /**
     * 
     * @param date
     * @param characters
     * @param name
     */
    public TVShow(Integer date, List<Character> characters, String name) {
        super();
        this.date = date;
        this.characters = characters;
        this.name = name;
    }

    /**
     * Serie's release year
     * 
     */
    @JsonProperty("date")
    public Integer getDate() {
        return date;
    }

    /**
     * Serie's release year
     * 
     */
    @JsonProperty("date")
    public void setDate(Integer date) {
        this.date = date;
    }

    public TVShow withDate(Integer date) {
        this.date = date;
        return this;
    }

    /**
     * Characters in the Serie
     * 
     */
    @JsonProperty("characters")
    public List<Character> getCharacters() {
        return characters;
    }

    /**
     * Characters in the Serie
     * 
     */
    @JsonProperty("characters")
    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public TVShow withCharacters(List<Character> characters) {
        this.characters = characters;
        return this;
    }

    /**
     * Serie's name
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Serie's name
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public TVShow withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TVShow.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("date");
        sb.append('=');
        sb.append(((this.date == null)?"<null>":this.date));
        sb.append(',');
        sb.append("characters");
        sb.append('=');
        sb.append(((this.characters == null)?"<null>":this.characters));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
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
        result = ((result* 31)+((this.date == null)? 0 :this.date.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.characters == null)? 0 :this.characters.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TVShow) == false) {
            return false;
        }
        TVShow rhs = ((TVShow) other);
        return ((((this.date == rhs.date)||((this.date!= null)&&this.date.equals(rhs.date)))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.characters == rhs.characters)||((this.characters!= null)&&this.characters.equals(rhs.characters))));
    }

}
