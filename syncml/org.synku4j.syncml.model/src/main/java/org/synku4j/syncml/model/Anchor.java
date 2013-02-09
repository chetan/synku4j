package org.synku4j.syncml.model;

import org.synku4j.syncml.wbxml.codepage.MetInfCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=MetInfCodePage.NAMESPACE_IDX, name=MetInfCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x05, name="Anchor")
public class Anchor {

	@WbxmlField(index=0x0a, name="Last")
	private String last;

	@WbxmlField(index=0x0f, name="Next")
	private String next;
	
	public Anchor() {
	}

	public Anchor(String last, String next) {
		this.last = last;
		this.next = next;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((next == null) ? 0 : next.hashCode());
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
		if (!(obj instanceof Anchor)) {
			return false;
		}
		Anchor other = (Anchor) obj;
		if (last == null) {
			if (other.last != null) {
				return false;
			}
		} else if (!last.equals(other.last)) {
			return false;
		}
		if (next == null) {
			if (other.next != null) {
				return false;
			}
		} else if (!next.equals(other.next)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Anchor [last=" + last + ", next=" + next + "]";
	}
}
