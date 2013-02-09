package com.zynku.sync.activesync.wbxml.codepage;

import com.zynku.sync.wbxml.CodePageField;

/**
 * The <code>AirSyncBaseCodePageField</code> enum defines all the 
 * fields specified in the protocol definition for this codepage.
 *
 * @author Jools Enticknap
 */
public enum AirSyncBaseCodePageField implements CodePageField {
	BodyPreference(0x05), Type(0x06), TruncationSize(0x07), 
	AllOrNone(0x08), Body(0x0a), Data(0x0b), 
	EstimatedDataSize(0x0c), Truncated(0x0d), 
	Attachments(0x0e), Attachment(0x0f), DisplayName(0x10), 
	FileReference(0x11), Method(0x12), ContentId(0x13), 
	ContentLocation(0x14), IsInline(0x15), NativeBodyType(0x16), 
	ContentType(0x17);	

	private final int idx;
	
	public static final String NAMESPACE_NAME = "AirSyncBase";
	public static final int NAMESPACE_IDX = 0x11;
	
	AirSyncBaseCodePageField(final int idx) {
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
		return NAMESPACE_IDX;
	}
}
