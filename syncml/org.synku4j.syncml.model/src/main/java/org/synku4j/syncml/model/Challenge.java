package org.synku4j.syncml.model;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index = SyncMLCodePage.NAMESPACE_IDX, name = SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index = 0x09, name = "Chal")
public class Challenge {
	@WbxmlField(index = 0x1a, name = "Meta", required = true)
	private MetInf meta;

	public Challenge() {
	}

	public MetInf getMeta() {
		return meta;
	}

	public void setMeta(MetInf meta) {
		this.meta = meta;
	}
}
