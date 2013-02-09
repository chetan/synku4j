package com.zynku.sync.activesync.wbxml.codepage;

import com.zynku.sync.wbxml.CodePage;

/**
 * This class is the specific code page for AirSync in the ActiveSync protocol.
 * The code page number is 0.
 */
public class AirSyncCodePage extends CodePage {

	public enum CodePageField {
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

		private final int id;

		CodePageField(final int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

	/**
	 * Constructor for AirSyncCodePage. Initializes all of the code page values.
	 */
	public AirSyncCodePage() {
		/* Maps String to Token for the code page */
		codepageTokens.put("Sync", 0x05);
		codepageTokens.put("Responses", 0x06);
		codepageTokens.put("Add", 0x07);
		codepageTokens.put("Change", 0x08);
		codepageTokens.put("Delete", 0x09);
		codepageTokens.put("Fetch", 0x0a);
		codepageTokens.put("SyncKey", 0x0b);
		codepageTokens.put("ClientId", 0x0c);
		codepageTokens.put("ServerId", 0x0d);
		codepageTokens.put("Status", 0x0e);
		codepageTokens.put("Collection", 0x0f);
		codepageTokens.put("Class", 0x10);
		codepageTokens.put("Version", 0x11);
		codepageTokens.put("CollectionId", 0x12);
		codepageTokens.put("GetChanges", 0x13);
		codepageTokens.put("MoreAvailable", 0x14);
		codepageTokens.put("WindowSize", 0x15);
		codepageTokens.put("Commands", 0x16);
		codepageTokens.put("Options", 0x17);
		codepageTokens.put("FilterType", 0x18);
		codepageTokens.put("Truncation", 0x19);
		codepageTokens.put("RTFTruncation", 0x1a);
		codepageTokens.put("Conflict", 0x1b);
		codepageTokens.put("Collections", 0x1c);
		codepageTokens.put("ApplicationData", 0x1d);
		codepageTokens.put("DeletesAsMoves", 0x1e);
		codepageTokens.put("NotifyGUID", 0x1f);
		codepageTokens.put("Supported", 0x20);
		codepageTokens.put("SoftDelete", 0x21);
		codepageTokens.put("MIMESupport", 0x22);
		codepageTokens.put("MIMETruncation", 0x23);
		codepageTokens.put("Wait", 0x24);
		codepageTokens.put("Limit", 0x25);
		codepageTokens.put("Partial", 0x26);
		/* Maps token to string for the code page */
		codepageStrings.put(0x05, "Sync");
		codepageStrings.put(0x06, "Responses");
		codepageStrings.put(0x07, "Add");
		codepageStrings.put(0x08, "Change");
		codepageStrings.put(0x09, "Delete");
		codepageStrings.put(0x0a, "Fetch");
		codepageStrings.put(0x0b, "SyncKey");
		codepageStrings.put(0x0c, "ClientId");
		codepageStrings.put(0x0d, "ServerId");
		codepageStrings.put(0x0e, "Status");
		codepageStrings.put(0x0f, "Collection");
		codepageStrings.put(0x10, "Class");
		codepageStrings.put(0x11, "Version");
		codepageStrings.put(0x12, "CollectionId");
		codepageStrings.put(0x13, "GetChanges");
		codepageStrings.put(0x14, "MoreAvailable");
		codepageStrings.put(0x15, "WindowSize");
		codepageStrings.put(0x16, "Commands");
		codepageStrings.put(0x17, "Options");
		codepageStrings.put(0x18, "FilterType");
		codepageStrings.put(0x19, "Truncation");
		codepageStrings.put(0x1a, "RTFTruncation");
		codepageStrings.put(0x1b, "Conflict");
		codepageStrings.put(0x1c, "Collections");
		codepageStrings.put(0x1d, "ApplicationData");
		codepageStrings.put(0x1e, "DeletesAsMoves");
		codepageStrings.put(0x1f, "NotifyGUID");
		codepageStrings.put(0x20, "Supported");
		codepageStrings.put(0x21, "SoftDelete");
		codepageStrings.put(0x22, "MIMESupport");
		codepageStrings.put(0x23, "MIMETruncation");
		codepageStrings.put(0x24, "Wait");
		codepageStrings.put(0x25, "Limit");
		codepageStrings.put(0x26, "Partial");

		codePageIndex = 0x00;
		codePageName = "AirSync";
	}
}
