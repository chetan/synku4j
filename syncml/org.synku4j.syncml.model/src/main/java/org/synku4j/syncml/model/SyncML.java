package org.synku4j.syncml.model;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;


/**
 * The <code>SyncML</code> class is the root class for all
 * SyncML messages.
 * 
 * @author Jools Enticknap
 */

// TODO: May need to specify the public id using a filter key.
@WbxmlPage(index=SyncMLCodePage.NAMESPACE_IDX, name=SyncMLCodePage.NAMESPACE_NAME, publicId=0x0FD3)
@WbxmlField(index=0x2D, name="SyncML")
public class SyncML {
	@WbxmlField(index=0x2C, name="SyncHdr", required=true)
	private SyncHeader header;
	
	@WbxmlField(index=0x2b, name="SyncBody", required=true)
	private SyncBody body;
	
	public SyncML() {
	}

	public SyncML(final SyncHeader syncHeader, final SyncBody syncBody) {
		this.header = syncHeader;
		this.body = syncBody;
	}
	
	public SyncHeader getHeader() {
		return header;
	}

	public void setHeader(SyncHeader header) {
		this.header = header;
	}

	public SyncBody getBody() {
		return body;
	}

	public void setBody(SyncBody body) {
		this.body = body;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SyncML)) {
			return false;
		}
		SyncML other = (SyncML) obj;
		if (body == null) {
			if (other.body != null) {
				return false;
			}
		} else if (!body.equals(other.body)) {
			return false;
		}
		if (header == null) {
			if (other.header != null) {
				return false;
			}
		} else if (!header.equals(other.header)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SyncML [body=" + body + ", header=" + header + "]";
	}
}
