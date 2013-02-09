package com.zynku.sync.activesync.wbxml.codepage;

import com.zynku.sync.wbxml.CodePage;

/**
 * This class is the specific code page for Ping in the ActiveSync protocol.
 * The code page number is 13.
 */
public class PingCodePage extends CodePage {
	
	public enum CodePageField {
		Ping(0x05), AutdState(0x06), /* Not used by protocol */
		Status(0x07), HeartbeatInterval(0x08), Folders(0x09), 
		Folder(0x0a), Id(0x0b), Class(0x0c), MaxFolders(0x0d);

		private final int id;

		CodePageField(final int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

    /**
     * Constructor for PingCodePage.  Initializes all of the code page values.
     */
    public PingCodePage() {
        /* Maps String to Token for the code page */
        codepageTokens.put("Ping", 0x05);
        codepageTokens.put("AutdState", 0x06); /* Not used by protocol */
        codepageTokens.put("Status", 0x07);
        codepageTokens.put("HeartbeatInterval", 0x08);
        codepageTokens.put("Folders", 0x09);
        codepageTokens.put("Folder", 0x0a);
        codepageTokens.put("Id", 0x0b);
        codepageTokens.put("Class", 0x0c);
        codepageTokens.put("MaxFolders", 0x0d);
         
        /* Maps token to string for the code page */
        for (String s : codepageTokens.keySet()) {
            codepageStrings.put(codepageTokens.get(s), s);
        }

        codePageIndex = 0x0d;
        codePageName = "Ping";
    }
}
