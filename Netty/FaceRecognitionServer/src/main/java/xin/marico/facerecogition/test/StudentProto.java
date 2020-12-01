// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: StudentProto.proto

package xin.marico.facerecogition.test;

public final class StudentProto {
  private StudentProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface StudentOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Student)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string name = 1;</code>
     */
    String getName();
    /**
     * <code>optional string name = 1;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>optional int32 age = 2;</code>
     */
    int getAge();

    /**
     * <code>optional string tellphone = 3;</code>
     */
    String getTellphone();
    /**
     * <code>optional string tellphone = 3;</code>
     */
    com.google.protobuf.ByteString
        getTellphoneBytes();

    /**
     * <code>optional string email = 4;</code>
     */
    String getEmail();
    /**
     * <code>optional string email = 4;</code>
     */
    com.google.protobuf.ByteString
        getEmailBytes();

    /**
     * <code>optional int32 weight = 5;</code>
     */
    int getWeight();

    /**
     * <code>optional string homeAddress = 6;</code>
     */
    String getHomeAddress();
    /**
     * <code>optional string homeAddress = 6;</code>
     */
    com.google.protobuf.ByteString
        getHomeAddressBytes();

    /**
     * <code>optional string school = 7;</code>
     */
    String getSchool();
    /**
     * <code>optional string school = 7;</code>
     */
    com.google.protobuf.ByteString
        getSchoolBytes();
  }
  /**
   * Protobuf type {@code Student}
   */
  public  static final class Student extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Student)
      StudentOrBuilder {
    // Use Student.newBuilder() to construct.
    private Student(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Student() {
      name_ = "";
      age_ = 0;
      tellphone_ = "";
      email_ = "";
      weight_ = 0;
      homeAddress_ = "";
      school_ = "";
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private Student(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              String s = input.readStringRequireUtf8();

              name_ = s;
              break;
            }
            case 16: {

              age_ = input.readInt32();
              break;
            }
            case 26: {
              String s = input.readStringRequireUtf8();

              tellphone_ = s;
              break;
            }
            case 34: {
              String s = input.readStringRequireUtf8();

              email_ = s;
              break;
            }
            case 40: {

              weight_ = input.readInt32();
              break;
            }
            case 50: {
              String s = input.readStringRequireUtf8();

              homeAddress_ = s;
              break;
            }
            case 58: {
              String s = input.readStringRequireUtf8();

              school_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return StudentProto.internal_static_Student_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return StudentProto.internal_static_Student_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Student.class, Builder.class);
    }

    public static final int NAME_FIELD_NUMBER = 1;
    private volatile Object name_;
    /**
     * <code>optional string name = 1;</code>
     */
    public String getName() {
      Object ref = name_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <code>optional string name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int AGE_FIELD_NUMBER = 2;
    private int age_;
    /**
     * <code>optional int32 age = 2;</code>
     */
    public int getAge() {
      return age_;
    }

    public static final int TELLPHONE_FIELD_NUMBER = 3;
    private volatile Object tellphone_;
    /**
     * <code>optional string tellphone = 3;</code>
     */
    public String getTellphone() {
      Object ref = tellphone_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        tellphone_ = s;
        return s;
      }
    }
    /**
     * <code>optional string tellphone = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTellphoneBytes() {
      Object ref = tellphone_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        tellphone_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int EMAIL_FIELD_NUMBER = 4;
    private volatile Object email_;
    /**
     * <code>optional string email = 4;</code>
     */
    public String getEmail() {
      Object ref = email_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        email_ = s;
        return s;
      }
    }
    /**
     * <code>optional string email = 4;</code>
     */
    public com.google.protobuf.ByteString
        getEmailBytes() {
      Object ref = email_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        email_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int WEIGHT_FIELD_NUMBER = 5;
    private int weight_;
    /**
     * <code>optional int32 weight = 5;</code>
     */
    public int getWeight() {
      return weight_;
    }

    public static final int HOMEADDRESS_FIELD_NUMBER = 6;
    private volatile Object homeAddress_;
    /**
     * <code>optional string homeAddress = 6;</code>
     */
    public String getHomeAddress() {
      Object ref = homeAddress_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        homeAddress_ = s;
        return s;
      }
    }
    /**
     * <code>optional string homeAddress = 6;</code>
     */
    public com.google.protobuf.ByteString
        getHomeAddressBytes() {
      Object ref = homeAddress_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        homeAddress_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int SCHOOL_FIELD_NUMBER = 7;
    private volatile Object school_;
    /**
     * <code>optional string school = 7;</code>
     */
    public String getSchool() {
      Object ref = school_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        school_ = s;
        return s;
      }
    }
    /**
     * <code>optional string school = 7;</code>
     */
    public com.google.protobuf.ByteString
        getSchoolBytes() {
      Object ref = school_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        school_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
      }
      if (age_ != 0) {
        output.writeInt32(2, age_);
      }
      if (!getTellphoneBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, tellphone_);
      }
      if (!getEmailBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, email_);
      }
      if (weight_ != 0) {
        output.writeInt32(5, weight_);
      }
      if (!getHomeAddressBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 6, homeAddress_);
      }
      if (!getSchoolBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 7, school_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
      }
      if (age_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, age_);
      }
      if (!getTellphoneBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, tellphone_);
      }
      if (!getEmailBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, email_);
      }
      if (weight_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, weight_);
      }
      if (!getHomeAddressBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, homeAddress_);
      }
      if (!getSchoolBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(7, school_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof Student)) {
        return super.equals(obj);
      }
      Student other = (Student) obj;

      boolean result = true;
      result = result && getName()
          .equals(other.getName());
      result = result && (getAge()
          == other.getAge());
      result = result && getTellphone()
          .equals(other.getTellphone());
      result = result && getEmail()
          .equals(other.getEmail());
      result = result && (getWeight()
          == other.getWeight());
      result = result && getHomeAddress()
          .equals(other.getHomeAddress());
      result = result && getSchool()
          .equals(other.getSchool());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      hash = (37 * hash) + AGE_FIELD_NUMBER;
      hash = (53 * hash) + getAge();
      hash = (37 * hash) + TELLPHONE_FIELD_NUMBER;
      hash = (53 * hash) + getTellphone().hashCode();
      hash = (37 * hash) + EMAIL_FIELD_NUMBER;
      hash = (53 * hash) + getEmail().hashCode();
      hash = (37 * hash) + WEIGHT_FIELD_NUMBER;
      hash = (53 * hash) + getWeight();
      hash = (37 * hash) + HOMEADDRESS_FIELD_NUMBER;
      hash = (53 * hash) + getHomeAddress().hashCode();
      hash = (37 * hash) + SCHOOL_FIELD_NUMBER;
      hash = (53 * hash) + getSchool().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static Student parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Student parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Student parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Student parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Student parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static Student parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static Student parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static Student parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static Student parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static Student parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(Student prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Student}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Student)
        StudentOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return StudentProto.internal_static_Student_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return StudentProto.internal_static_Student_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                Student.class, Builder.class);
      }

      // Construct using xin.marico.facerecogition.test.StudentProto.Student.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        name_ = "";

        age_ = 0;

        tellphone_ = "";

        email_ = "";

        weight_ = 0;

        homeAddress_ = "";

        school_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return StudentProto.internal_static_Student_descriptor;
      }

      public Student getDefaultInstanceForType() {
        return Student.getDefaultInstance();
      }

      public Student build() {
        Student result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public Student buildPartial() {
        Student result = new Student(this);
        result.name_ = name_;
        result.age_ = age_;
        result.tellphone_ = tellphone_;
        result.email_ = email_;
        result.weight_ = weight_;
        result.homeAddress_ = homeAddress_;
        result.school_ = school_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof Student) {
          return mergeFrom((Student)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(Student other) {
        if (other == Student.getDefaultInstance()) return this;
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          onChanged();
        }
        if (other.getAge() != 0) {
          setAge(other.getAge());
        }
        if (!other.getTellphone().isEmpty()) {
          tellphone_ = other.tellphone_;
          onChanged();
        }
        if (!other.getEmail().isEmpty()) {
          email_ = other.email_;
          onChanged();
        }
        if (other.getWeight() != 0) {
          setWeight(other.getWeight());
        }
        if (!other.getHomeAddress().isEmpty()) {
          homeAddress_ = other.homeAddress_;
          onChanged();
        }
        if (!other.getSchool().isEmpty()) {
          school_ = other.school_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        Student parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (Student) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private Object name_ = "";
      /**
       * <code>optional string name = 1;</code>
       */
      public String getName() {
        Object ref = name_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>optional string name = 1;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string name = 1;</code>
       */
      public Builder setName(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string name = 1;</code>
       */
      public Builder clearName() {
        
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string name = 1;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        name_ = value;
        onChanged();
        return this;
      }

      private int age_ ;
      /**
       * <code>optional int32 age = 2;</code>
       */
      public int getAge() {
        return age_;
      }
      /**
       * <code>optional int32 age = 2;</code>
       */
      public Builder setAge(int value) {
        
        age_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 age = 2;</code>
       */
      public Builder clearAge() {
        
        age_ = 0;
        onChanged();
        return this;
      }

      private Object tellphone_ = "";
      /**
       * <code>optional string tellphone = 3;</code>
       */
      public String getTellphone() {
        Object ref = tellphone_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          tellphone_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>optional string tellphone = 3;</code>
       */
      public com.google.protobuf.ByteString
          getTellphoneBytes() {
        Object ref = tellphone_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          tellphone_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string tellphone = 3;</code>
       */
      public Builder setTellphone(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        tellphone_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string tellphone = 3;</code>
       */
      public Builder clearTellphone() {
        
        tellphone_ = getDefaultInstance().getTellphone();
        onChanged();
        return this;
      }
      /**
       * <code>optional string tellphone = 3;</code>
       */
      public Builder setTellphoneBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        tellphone_ = value;
        onChanged();
        return this;
      }

      private Object email_ = "";
      /**
       * <code>optional string email = 4;</code>
       */
      public String getEmail() {
        Object ref = email_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          email_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>optional string email = 4;</code>
       */
      public com.google.protobuf.ByteString
          getEmailBytes() {
        Object ref = email_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          email_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string email = 4;</code>
       */
      public Builder setEmail(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        email_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string email = 4;</code>
       */
      public Builder clearEmail() {
        
        email_ = getDefaultInstance().getEmail();
        onChanged();
        return this;
      }
      /**
       * <code>optional string email = 4;</code>
       */
      public Builder setEmailBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        email_ = value;
        onChanged();
        return this;
      }

      private int weight_ ;
      /**
       * <code>optional int32 weight = 5;</code>
       */
      public int getWeight() {
        return weight_;
      }
      /**
       * <code>optional int32 weight = 5;</code>
       */
      public Builder setWeight(int value) {
        
        weight_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 weight = 5;</code>
       */
      public Builder clearWeight() {
        
        weight_ = 0;
        onChanged();
        return this;
      }

      private Object homeAddress_ = "";
      /**
       * <code>optional string homeAddress = 6;</code>
       */
      public String getHomeAddress() {
        Object ref = homeAddress_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          homeAddress_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>optional string homeAddress = 6;</code>
       */
      public com.google.protobuf.ByteString
          getHomeAddressBytes() {
        Object ref = homeAddress_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          homeAddress_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string homeAddress = 6;</code>
       */
      public Builder setHomeAddress(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        homeAddress_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string homeAddress = 6;</code>
       */
      public Builder clearHomeAddress() {
        
        homeAddress_ = getDefaultInstance().getHomeAddress();
        onChanged();
        return this;
      }
      /**
       * <code>optional string homeAddress = 6;</code>
       */
      public Builder setHomeAddressBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        homeAddress_ = value;
        onChanged();
        return this;
      }

      private Object school_ = "";
      /**
       * <code>optional string school = 7;</code>
       */
      public String getSchool() {
        Object ref = school_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          school_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>optional string school = 7;</code>
       */
      public com.google.protobuf.ByteString
          getSchoolBytes() {
        Object ref = school_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          school_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string school = 7;</code>
       */
      public Builder setSchool(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        school_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string school = 7;</code>
       */
      public Builder clearSchool() {
        
        school_ = getDefaultInstance().getSchool();
        onChanged();
        return this;
      }
      /**
       * <code>optional string school = 7;</code>
       */
      public Builder setSchoolBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        school_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:Student)
    }

    // @@protoc_insertion_point(class_scope:Student)
    private static final Student DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new Student();
    }

    public static Student getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Student>
        PARSER = new com.google.protobuf.AbstractParser<Student>() {
      public Student parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new Student(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Student> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<Student> getParserForType() {
      return PARSER;
    }

    public Student getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Student_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Student_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022StudentProto.proto\"{\n\007Student\022\014\n\004name\030" +
      "\001 \001(\t\022\013\n\003age\030\002 \001(\005\022\021\n\ttellphone\030\003 \001(\t\022\r\n" +
      "\005email\030\004 \001(\t\022\016\n\006weight\030\005 \001(\005\022\023\n\013homeAddr" +
      "ess\030\006 \001(\t\022\016\n\006school\030\007 \001(\tB.\n\036xin.marico." +
      "facerecogition.testB\014StudentProtob\006proto" +
      "3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_Student_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Student_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Student_descriptor,
        new String[] { "Name", "Age", "Tellphone", "Email", "Weight", "HomeAddress", "School", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}