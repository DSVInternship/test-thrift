/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package example.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-08-03")
public class TGetProfileResult implements org.apache.thrift.TBase<TGetProfileResult, TGetProfileResult._Fields>, java.io.Serializable, Cloneable, Comparable<TGetProfileResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TGetProfileResult");

  private static final org.apache.thrift.protocol.TField ERR_FIELD_DESC = new org.apache.thrift.protocol.TField("err", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField PROFILE_FIELD_DESC = new org.apache.thrift.protocol.TField("profile", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TGetProfileResultStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TGetProfileResultTupleSchemeFactory();

  public long err; // required
  public java.util.List<Profile> profile; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERR((short)1, "err"),
    PROFILE((short)2, "profile");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ERR
          return ERR;
        case 2: // PROFILE
          return PROFILE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ERR_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.PROFILE};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ERR, new org.apache.thrift.meta_data.FieldMetaData("err", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.PROFILE, new org.apache.thrift.meta_data.FieldMetaData("profile", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Profile.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TGetProfileResult.class, metaDataMap);
  }

  public TGetProfileResult() {
  }

  public TGetProfileResult(
    long err)
  {
    this();
    this.err = err;
    setErrIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TGetProfileResult(TGetProfileResult other) {
    __isset_bitfield = other.__isset_bitfield;
    this.err = other.err;
    if (other.isSetProfile()) {
      java.util.List<Profile> __this__profile = new java.util.ArrayList<Profile>(other.profile.size());
      for (Profile other_element : other.profile) {
        __this__profile.add(new Profile(other_element));
      }
      this.profile = __this__profile;
    }
  }

  public TGetProfileResult deepCopy() {
    return new TGetProfileResult(this);
  }

  @Override
  public void clear() {
    setErrIsSet(false);
    this.err = 0;
    this.profile = null;
  }

  public long getErr() {
    return this.err;
  }

  public TGetProfileResult setErr(long err) {
    this.err = err;
    setErrIsSet(true);
    return this;
  }

  public void unsetErr() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ERR_ISSET_ID);
  }

  /** Returns true if field err is set (has been assigned a value) and false otherwise */
  public boolean isSetErr() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ERR_ISSET_ID);
  }

  public void setErrIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ERR_ISSET_ID, value);
  }

  public int getProfileSize() {
    return (this.profile == null) ? 0 : this.profile.size();
  }

  public java.util.Iterator<Profile> getProfileIterator() {
    return (this.profile == null) ? null : this.profile.iterator();
  }

  public void addToProfile(Profile elem) {
    if (this.profile == null) {
      this.profile = new java.util.ArrayList<Profile>();
    }
    this.profile.add(elem);
  }

  public java.util.List<Profile> getProfile() {
    return this.profile;
  }

  public TGetProfileResult setProfile(java.util.List<Profile> profile) {
    this.profile = profile;
    return this;
  }

  public void unsetProfile() {
    this.profile = null;
  }

  /** Returns true if field profile is set (has been assigned a value) and false otherwise */
  public boolean isSetProfile() {
    return this.profile != null;
  }

  public void setProfileIsSet(boolean value) {
    if (!value) {
      this.profile = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ERR:
      if (value == null) {
        unsetErr();
      } else {
        setErr((java.lang.Long)value);
      }
      break;

    case PROFILE:
      if (value == null) {
        unsetProfile();
      } else {
        setProfile((java.util.List<Profile>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ERR:
      return getErr();

    case PROFILE:
      return getProfile();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ERR:
      return isSetErr();
    case PROFILE:
      return isSetProfile();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TGetProfileResult)
      return this.equals((TGetProfileResult)that);
    return false;
  }

  public boolean equals(TGetProfileResult that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_err = true;
    boolean that_present_err = true;
    if (this_present_err || that_present_err) {
      if (!(this_present_err && that_present_err))
        return false;
      if (this.err != that.err)
        return false;
    }

    boolean this_present_profile = true && this.isSetProfile();
    boolean that_present_profile = true && that.isSetProfile();
    if (this_present_profile || that_present_profile) {
      if (!(this_present_profile && that_present_profile))
        return false;
      if (!this.profile.equals(that.profile))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(err);

    hashCode = hashCode * 8191 + ((isSetProfile()) ? 131071 : 524287);
    if (isSetProfile())
      hashCode = hashCode * 8191 + profile.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TGetProfileResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetErr()).compareTo(other.isSetErr());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErr()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.err, other.err);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetProfile()).compareTo(other.isSetProfile());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProfile()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.profile, other.profile);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TGetProfileResult(");
    boolean first = true;

    sb.append("err:");
    sb.append(this.err);
    first = false;
    if (isSetProfile()) {
      if (!first) sb.append(", ");
      sb.append("profile:");
      if (this.profile == null) {
        sb.append("null");
      } else {
        sb.append(this.profile);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'err' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TGetProfileResultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TGetProfileResultStandardScheme getScheme() {
      return new TGetProfileResultStandardScheme();
    }
  }

  private static class TGetProfileResultStandardScheme extends org.apache.thrift.scheme.StandardScheme<TGetProfileResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TGetProfileResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ERR
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.err = iprot.readI64();
              struct.setErrIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PROFILE
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.profile = new java.util.ArrayList<Profile>(_list0.size);
                Profile _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new Profile();
                  _elem1.read(iprot);
                  struct.profile.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setProfileIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetErr()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'err' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TGetProfileResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ERR_FIELD_DESC);
      oprot.writeI64(struct.err);
      oprot.writeFieldEnd();
      if (struct.profile != null) {
        if (struct.isSetProfile()) {
          oprot.writeFieldBegin(PROFILE_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.profile.size()));
            for (Profile _iter3 : struct.profile)
            {
              _iter3.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TGetProfileResultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TGetProfileResultTupleScheme getScheme() {
      return new TGetProfileResultTupleScheme();
    }
  }

  private static class TGetProfileResultTupleScheme extends org.apache.thrift.scheme.TupleScheme<TGetProfileResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TGetProfileResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI64(struct.err);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetProfile()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetProfile()) {
        {
          oprot.writeI32(struct.profile.size());
          for (Profile _iter4 : struct.profile)
          {
            _iter4.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TGetProfileResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.err = iprot.readI64();
      struct.setErrIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.profile = new java.util.ArrayList<Profile>(_list5.size);
          Profile _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new Profile();
            _elem6.read(iprot);
            struct.profile.add(_elem6);
          }
        }
        struct.setProfileIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
