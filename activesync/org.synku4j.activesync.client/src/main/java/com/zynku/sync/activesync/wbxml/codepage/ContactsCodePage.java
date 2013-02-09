package com.zynku.sync.activesync.wbxml.codepage;

import com.zynku.sync.wbxml.CodePage;

public class ContactsCodePage extends CodePage {
    public ContactsCodePage() {
    	for (final ContactsCodePageField fld : ContactsCodePageField.values()) {
    		codepageStrings.put(fld.getCodePageIndex(), fld.getFieldName());
    		codepageTokens.put(fld.getFieldName(), fld.getCodePageIndex());
    	}
    	
        codePageIndex = ContactsCodePageField.NAMESPACE_IDX;
        codePageName = ContactsCodePageField.NAMESPACE_NAME;
    }
}
