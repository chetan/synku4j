package com.zynku.sync.activesync.wbxml.codepage;

import com.zynku.sync.wbxml.CodePageField;

public enum ContactsCodePageField implements CodePageField {
	Anniversary(0x05), AssistantName(0x06), AssistantTelephoneNumber(0x07), 
	Birthday(0x08), Body(0x09), BodySize(0x0a), 
	BodyTruncated(0x0b), Business2TelephoneNumber(0x0c), 
	BusinessAddressCity(0x0d), BusinessAddressCountry(0x0e), BusinessAddressPostalCode(0x0f), 
	BusinessAddressState(0x10), BusinessAddressStreet(0x11), BusinessFaxNumber(0x12), 
	BusinessTelephoneNumber(0x13), CarTelephoneNumber(0x14), Categories(0x15), Category(0x16), 
	Children(0x17), Child(0x18), CompanyName(0x19), Department(0x1a), Email1Address(0x1b), 
	Email2Address(0x1c), Email3Address(0x1d), FileAs(0x1e), FirstName(0x1f), 
	Home2TelephoneNumber(0x20), HomeAddressCity(0x21), HomeAddressCountry(0x22), 
	HomeAddressPostalCode(0x23), HomeAddressState(0x24), HomeAddressStreet(0x25), 
	HomeFaxNumber(0x26), HomeTelephoneNumber(0x27), JobTitle(0x28), LastName(0x29), 
	MiddleName(0x2a), MobileTelephoneNumber(0x2b), OfficeLocation(0x2c), 
	OtherAddressCity(0x2d), OtherAddressCountry(0x2e), OtherAddressPostalCode(0x2f), 
	OtherAddressState(0x30), OtherAddressStreet(0x31), PagerNumber(0x32), 
	RadioTelephoneNumber(0x33), Spouse(0x34), Suffix(0x35), Title(0x36), Webpage(0x37), 
	YomiCompanyName(0x38), YomiFirstName(0x39), YomiLastName(0x3a), CompressedRTF(0x3b), Picture(0x3c);

	private final int idx;
	public static final String NAMESPACE_NAME = "POOMCONTACTS";
	public static final int NAMESPACE_IDX = 0x01;

	ContactsCodePageField(final int idx) {
		this.idx = idx;
		ActiveSyncCodePageLookup.add(this);
	}

	public int getCodePageIndex() {
		return idx;
	}

	public String getFieldName() {
		return name();
	}

	public String getNameSpace() {
		return NAMESPACE_NAME;
	}

	public int getNameSpaceIndex() {
		// TODO Auto-generated method stub
		return NAMESPACE_IDX;
	}
}
