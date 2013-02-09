package com.zynku.sync.activesync.wbxml.codepage;

import com.zynku.sync.wbxml.CodePageField;

/**
 * The <code>AirSyncCodePageField</code> enum defines all the 
 * fields specified in the protocol definition for this codepage.
 * 
 * @author Jools Enticknap
 */
public enum AirSyncCodePageField implements CodePageField {
	Sync(0x05), Responses(0x06), Add(0x07), 
	Change(0x08), Delete(0x09), Fetch(0x0a), 
	SyncKey(0x0b), ClientId(0x0c), ServerId(0x0d), 
	Status(0x0e), Collection(0x0f), Class(0x10), 
	Version(0x11), CollectionId(0x12), GetChanges(0x13), 
	MoreAvailable(0x14), WindowSize(0x15), Commands(0x16), 
	Options(0x17), FilterType(0x18), Truncation(0x19), 
	RTFTruncation(0x1a), Conflict(0x1b), Collections(0x1c), 
	ApplicationData(0x1d), DeletesAsMoves(0x1e), NotifyGUID(0x1f), 
	Supported(0x20), SoftDelete(0x21), MIMESupport(0x22), 
	MIMETruncation(0x23), Wait(0x24), Limit(0x25), Partial(0x26);

	
	private final int idx;
	private static final String NAMESPACE_NAME = "AirSync";
	private static final int NAMESPACE_IDX = 0x00;
	
	AirSyncCodePageField(final int idx) {
		this.idx = idx;
		ActiveSyncCodePageLookup.add(this);
	}
	
	public int getCodePageIndex() {
		return idx;
	}

	public String getFieldName() {
		return name();
	}

	public int getNameSpaceIndex() {
		return NAMESPACE_IDX;
	}

	public String getNameSpace() {
		return NAMESPACE_NAME;
	}
}
