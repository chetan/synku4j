package org.synku4j.syncml.model.devinf;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.DevInfCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;
import org.synku4j.wbxml.core.WbxmlValue;

/**
 * The <code>CTCap</code> class contains a raw set of
 * WbxmlValues.
 * 
 * @author Jools Enticknap
 */
@WbxmlPage(index=DevInfCodePage.NAMESPACE_IDX, name=DevInfCodePage.NAMESPACE_NAME)
@WbxmlField(name="CTCap", index=0x05)
public class CTCap {
	
	@WbxmlField(classes={ WbxmlValue.class })
	private Collection<WbxmlValue> values;
	
	public CTCap() {
	}
	
	public Collection<WbxmlValue> getValues() {
		return values;
	}

	public void setValues(Collection<WbxmlValue> values) {
		this.values = values;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		if (!(obj instanceof CTCap)) {
			return false;
		}
		CTCap other = (CTCap) obj;
		if (values == null) {
			if (other.values != null) {
				return false;
			}
		} else if (!values.equals(other.values)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CTCap [values=" + values + "]";
	}
}
