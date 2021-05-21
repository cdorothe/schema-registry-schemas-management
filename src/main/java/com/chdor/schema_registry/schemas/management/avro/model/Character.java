/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.chdor.schema_registry.schemas.management.avro.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** A tiny Serie character identity card */
@org.apache.avro.specific.AvroGenerated
public class Character extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4396385489569406944L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Character\",\"namespace\":\"com.chdor.schema_registry.schemas.management.avro.model\",\"doc\":\"A tiny Serie character identity card\",\"fields\":[{\"name\":\"firstName\",\"type\":[\"null\",\"string\"],\"doc\":\"Character fisrt name\"},{\"name\":\"lastName\",\"type\":[\"null\",\"string\"],\"doc\":\"Character last name\"},{\"name\":\"age\",\"type\":\"int\",\"doc\":\"Character Age\",\"default\":0}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Character> ENCODER =
      new BinaryMessageEncoder<Character>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Character> DECODER =
      new BinaryMessageDecoder<Character>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Character> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Character> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Character> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Character>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Character to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Character from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Character instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Character fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Character fisrt name */
   private java.lang.CharSequence firstName;
  /** Character last name */
   private java.lang.CharSequence lastName;
  /** Character Age */
   private int age;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Character() {}

  /**
   * All-args constructor.
   * @param firstName Character fisrt name
   * @param lastName Character last name
   * @param age Character Age
   */
  public Character(java.lang.CharSequence firstName, java.lang.CharSequence lastName, java.lang.Integer age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return firstName;
    case 1: return lastName;
    case 2: return age;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: firstName = (java.lang.CharSequence)value$; break;
    case 1: lastName = (java.lang.CharSequence)value$; break;
    case 2: age = (java.lang.Integer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'firstName' field.
   * @return Character fisrt name
   */
  public java.lang.CharSequence getFirstName() {
    return firstName;
  }


  /**
   * Sets the value of the 'firstName' field.
   * Character fisrt name
   * @param value the value to set.
   */
  public void setFirstName(java.lang.CharSequence value) {
    this.firstName = value;
  }

  /**
   * Gets the value of the 'lastName' field.
   * @return Character last name
   */
  public java.lang.CharSequence getLastName() {
    return lastName;
  }


  /**
   * Sets the value of the 'lastName' field.
   * Character last name
   * @param value the value to set.
   */
  public void setLastName(java.lang.CharSequence value) {
    this.lastName = value;
  }

  /**
   * Gets the value of the 'age' field.
   * @return Character Age
   */
  public int getAge() {
    return age;
  }


  /**
   * Sets the value of the 'age' field.
   * Character Age
   * @param value the value to set.
   */
  public void setAge(int value) {
    this.age = value;
  }

  /**
   * Creates a new Character RecordBuilder.
   * @return A new Character RecordBuilder
   */
  public static com.chdor.schema_registry.schemas.management.avro.model.Character.Builder newBuilder() {
    return new com.chdor.schema_registry.schemas.management.avro.model.Character.Builder();
  }

  /**
   * Creates a new Character RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Character RecordBuilder
   */
  public static com.chdor.schema_registry.schemas.management.avro.model.Character.Builder newBuilder(com.chdor.schema_registry.schemas.management.avro.model.Character.Builder other) {
    if (other == null) {
      return new com.chdor.schema_registry.schemas.management.avro.model.Character.Builder();
    } else {
      return new com.chdor.schema_registry.schemas.management.avro.model.Character.Builder(other);
    }
  }

  /**
   * Creates a new Character RecordBuilder by copying an existing Character instance.
   * @param other The existing instance to copy.
   * @return A new Character RecordBuilder
   */
  public static com.chdor.schema_registry.schemas.management.avro.model.Character.Builder newBuilder(com.chdor.schema_registry.schemas.management.avro.model.Character other) {
    if (other == null) {
      return new com.chdor.schema_registry.schemas.management.avro.model.Character.Builder();
    } else {
      return new com.chdor.schema_registry.schemas.management.avro.model.Character.Builder(other);
    }
  }

  /**
   * RecordBuilder for Character instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Character>
    implements org.apache.avro.data.RecordBuilder<Character> {

    /** Character fisrt name */
    private java.lang.CharSequence firstName;
    /** Character last name */
    private java.lang.CharSequence lastName;
    /** Character Age */
    private int age;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.chdor.schema_registry.schemas.management.avro.model.Character.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.firstName)) {
        this.firstName = data().deepCopy(fields()[0].schema(), other.firstName);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.lastName)) {
        this.lastName = data().deepCopy(fields()[1].schema(), other.lastName);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.age)) {
        this.age = data().deepCopy(fields()[2].schema(), other.age);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing Character instance
     * @param other The existing instance to copy.
     */
    private Builder(com.chdor.schema_registry.schemas.management.avro.model.Character other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.firstName)) {
        this.firstName = data().deepCopy(fields()[0].schema(), other.firstName);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.lastName)) {
        this.lastName = data().deepCopy(fields()[1].schema(), other.lastName);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.age)) {
        this.age = data().deepCopy(fields()[2].schema(), other.age);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'firstName' field.
      * Character fisrt name
      * @return The value.
      */
    public java.lang.CharSequence getFirstName() {
      return firstName;
    }


    /**
      * Sets the value of the 'firstName' field.
      * Character fisrt name
      * @param value The value of 'firstName'.
      * @return This builder.
      */
    public com.chdor.schema_registry.schemas.management.avro.model.Character.Builder setFirstName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.firstName = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'firstName' field has been set.
      * Character fisrt name
      * @return True if the 'firstName' field has been set, false otherwise.
      */
    public boolean hasFirstName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'firstName' field.
      * Character fisrt name
      * @return This builder.
      */
    public com.chdor.schema_registry.schemas.management.avro.model.Character.Builder clearFirstName() {
      firstName = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'lastName' field.
      * Character last name
      * @return The value.
      */
    public java.lang.CharSequence getLastName() {
      return lastName;
    }


    /**
      * Sets the value of the 'lastName' field.
      * Character last name
      * @param value The value of 'lastName'.
      * @return This builder.
      */
    public com.chdor.schema_registry.schemas.management.avro.model.Character.Builder setLastName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.lastName = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'lastName' field has been set.
      * Character last name
      * @return True if the 'lastName' field has been set, false otherwise.
      */
    public boolean hasLastName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'lastName' field.
      * Character last name
      * @return This builder.
      */
    public com.chdor.schema_registry.schemas.management.avro.model.Character.Builder clearLastName() {
      lastName = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'age' field.
      * Character Age
      * @return The value.
      */
    public int getAge() {
      return age;
    }


    /**
      * Sets the value of the 'age' field.
      * Character Age
      * @param value The value of 'age'.
      * @return This builder.
      */
    public com.chdor.schema_registry.schemas.management.avro.model.Character.Builder setAge(int value) {
      validate(fields()[2], value);
      this.age = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'age' field has been set.
      * Character Age
      * @return True if the 'age' field has been set, false otherwise.
      */
    public boolean hasAge() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'age' field.
      * Character Age
      * @return This builder.
      */
    public com.chdor.schema_registry.schemas.management.avro.model.Character.Builder clearAge() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Character build() {
      try {
        Character record = new Character();
        record.firstName = fieldSetFlags()[0] ? this.firstName : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.lastName = fieldSetFlags()[1] ? this.lastName : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.age = fieldSetFlags()[2] ? this.age : (java.lang.Integer) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Character>
    WRITER$ = (org.apache.avro.io.DatumWriter<Character>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Character>
    READER$ = (org.apache.avro.io.DatumReader<Character>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.firstName == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.firstName);
    }

    if (this.lastName == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.lastName);
    }

    out.writeInt(this.age);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.firstName = null;
      } else {
        this.firstName = in.readString(this.firstName instanceof Utf8 ? (Utf8)this.firstName : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.lastName = null;
      } else {
        this.lastName = in.readString(this.lastName instanceof Utf8 ? (Utf8)this.lastName : null);
      }

      this.age = in.readInt();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.firstName = null;
          } else {
            this.firstName = in.readString(this.firstName instanceof Utf8 ? (Utf8)this.firstName : null);
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.lastName = null;
          } else {
            this.lastName = in.readString(this.lastName instanceof Utf8 ? (Utf8)this.lastName : null);
          }
          break;

        case 2:
          this.age = in.readInt();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










