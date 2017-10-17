package com.person.common.entity;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String CardNo;
	private String Descriot;
	private String CtfTp;
	private String CtfId;
	private String Gender;
	private String Birthday;
	private String Address;
	private String Zip;
	private String Dirty;
	private String District1;
	private String District2;
	private String District3;
	private String District4;
	private String District5;
	private String District6;
	private String FirstNm;
	private String LastNm;
	private String Duty;
	private String Mobile;
	private String Tel;
	private String Fax;
	private String EMail;
	private String Nation;
	private String Taste;
	private String Education;
	private String Company;
	private String CTel;
	private String CAddress;
	private String CZip;
	private String Family;
	private String Version;
	private String id;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getDescriot() {
		return Descriot;
	}
	public void setDescriot(String descriot) {
		Descriot = descriot;
	}
	public String getCtfTp() {
		return CtfTp;
	}
	public void setCtfTp(String ctfTp) {
		CtfTp = ctfTp;
	}
	public String getCtfId() {
		return CtfId;
	}
	public void setCtfId(String ctfId) {
		CtfId = ctfId;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getZip() {
		return Zip;
	}
	public void setZip(String zip) {
		Zip = zip;
	}
	public String getDirty() {
		return Dirty;
	}
	public void setDirty(String dirty) {
		Dirty = dirty;
	}
	public String getDistrict1() {
		return District1;
	}
	public void setDistrict1(String district1) {
		District1 = district1;
	}
	public String getDistrict2() {
		return District2;
	}
	public void setDistrict2(String district2) {
		District2 = district2;
	}
	public String getDistrict3() {
		return District3;
	}
	public void setDistrict3(String district3) {
		District3 = district3;
	}
	public String getDistrict4() {
		return District4;
	}
	public void setDistrict4(String district4) {
		District4 = district4;
	}
	public String getDistrict5() {
		return District5;
	}
	public void setDistrict5(String district5) {
		District5 = district5;
	}
	public String getDistrict6() {
		return District6;
	}
	public void setDistrict6(String district6) {
		District6 = district6;
	}
	public String getFirstNm() {
		return FirstNm;
	}
	public void setFirstNm(String firstNm) {
		FirstNm = firstNm;
	}
	public String getLastNm() {
		return LastNm;
	}
	public void setLastNm(String lastNm) {
		LastNm = lastNm;
	}
	public String getDuty() {
		return Duty;
	}
	public void setDuty(String duty) {
		Duty = duty;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getFax() {
		return Fax;
	}
	public void setFax(String fax) {
		Fax = fax;
	}
	public String getEMail() {
		return EMail;
	}
	public void setEMail(String eMail) {
		EMail = eMail;
	}
	public String getNation() {
		return Nation;
	}
	public void setNation(String nation) {
		Nation = nation;
	}
	public String getTaste() {
		return Taste;
	}
	public void setTaste(String taste) {
		Taste = taste;
	}
	public String getEducation() {
		return Education;
	}
	public void setEducation(String education) {
		Education = education;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getCTel() {
		return CTel;
	}
	public void setCTel(String cTel) {
		CTel = cTel;
	}
	public String getCAddress() {
		return CAddress;
	}
	public void setCAddress(String cAddress) {
		CAddress = cAddress;
	}
	public String getCZip() {
		return CZip;
	}
	public void setCZip(String cZip) {
		CZip = cZip;
	}
	public String getFamily() {
		return Family;
	}
	public void setFamily(String family) {
		Family = family;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [Name=");
		builder.append(Name);
		builder.append(", CardNo=");
		builder.append(CardNo);
		builder.append(", Descriot=");
		builder.append(Descriot);
		builder.append(", CtfTp=");
		builder.append(CtfTp);
		builder.append(", CtfId=");
		builder.append(CtfId);
		builder.append(", Gender=");
		builder.append(Gender);
		builder.append(", Birthday=");
		builder.append(Birthday);
		builder.append(", Address=");
		builder.append(Address);
		builder.append(", Zip=");
		builder.append(Zip);
		builder.append(", Dirty=");
		builder.append(Dirty);
		builder.append(", District1=");
		builder.append(District1);
		builder.append(", District2=");
		builder.append(District2);
		builder.append(", District3=");
		builder.append(District3);
		builder.append(", District4=");
		builder.append(District4);
		builder.append(", District5=");
		builder.append(District5);
		builder.append(", District6=");
		builder.append(District6);
		builder.append(", FirstNm=");
		builder.append(FirstNm);
		builder.append(", LastNm=");
		builder.append(LastNm);
		builder.append(", Duty=");
		builder.append(Duty);
		builder.append(", Mobile=");
		builder.append(Mobile);
		builder.append(", Tel=");
		builder.append(Tel);
		builder.append(", Fax=");
		builder.append(Fax);
		builder.append(", EMail=");
		builder.append(EMail);
		builder.append(", Nation=");
		builder.append(Nation);
		builder.append(", Taste=");
		builder.append(Taste);
		builder.append(", Education=");
		builder.append(Education);
		builder.append(", Company=");
		builder.append(Company);
		builder.append(", CTel=");
		builder.append(CTel);
		builder.append(", CAddress=");
		builder.append(CAddress);
		builder.append(", CZip=");
		builder.append(CZip);
		builder.append(", Family=");
		builder.append(Family);
		builder.append(", Version=");
		builder.append(Version);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
	
	
}
