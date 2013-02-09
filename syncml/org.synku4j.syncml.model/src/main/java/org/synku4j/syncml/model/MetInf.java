package org.synku4j.syncml.model;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.MetInfCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=MetInfCodePage.NAMESPACE_IDX, name=MetInfCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x0e, name="MetInf")
public class MetInf {
	@WbxmlField(index=0x07, name="Format")
	private String format;

	@WbxmlField(index=0x13, name="Type")
	private String type;
	
	@WbxmlField(index=0x0b, name="Mark")
	private String mark;

	@WbxmlField(index=0x12, name="Size")
	private String size;
	
	@WbxmlField(index=0x05, name="Anchor")
	private Anchor anchor;

	@WbxmlField(index=0x14, name="Version")
	private String version;

	@WbxmlField(index=0x10, name="NextNonce")
	private String nextNonce;
	
	@WbxmlField(index=0x0c, name="MaxMsgSize")
	private String maxMsgSize;

	@WbxmlField(index=0x15, name="MaxObjSize")
	private String maxObjSize;
	
	@WbxmlField(index=0x06, name="EMI")
	private Collection<String> emis;

	@WbxmlField(index=0x0d, name="Mem", required=false)
	private Mem mem;
	
	public MetInf() {
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Anchor getAnchor() {
		return anchor;
	}

	public void setAnchor(Anchor anchor) {
		this.anchor = anchor;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNextNonce() {
		return nextNonce;
	}

	public void setNextNonce(String nextNonce) {
		this.nextNonce = nextNonce;
	}

	public String getMaxMsgSize() {
		return maxMsgSize;
	}

	public void setMaxMsgSize(String maxMsgSize) {
		this.maxMsgSize = maxMsgSize;
	}

	public Collection<String> getEmis() {
		return emis;
	}

	public void setEmis(Collection<String> emis) {
		this.emis = emis;
	}

	public Mem getMem() {
		return mem;
	}

	public void setMem(Mem mem) {
		this.mem = mem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anchor == null) ? 0 : anchor.hashCode());
		result = prime * result + ((emis == null) ? 0 : emis.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((maxMsgSize == null) ? 0 : maxMsgSize.hashCode());
		result = prime * result + ((mem == null) ? 0 : mem.hashCode());
		result = prime * result + ((nextNonce == null) ? 0 : nextNonce.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		if (!(obj instanceof MetInf)) {
			return false;
		}
		MetInf other = (MetInf) obj;
		if (anchor == null) {
			if (other.anchor != null) {
				return false;
			}
		} else if (!anchor.equals(other.anchor)) {
			return false;
		}
		if (emis == null) {
			if (other.emis != null) {
				return false;
			}
		} else if (!emis.equals(other.emis)) {
			return false;
		}
		if (format == null) {
			if (other.format != null) {
				return false;
			}
		} else if (!format.equals(other.format)) {
			return false;
		}
		if (mark == null) {
			if (other.mark != null) {
				return false;
			}
		} else if (!mark.equals(other.mark)) {
			return false;
		}
		if (maxMsgSize == null) {
			if (other.maxMsgSize != null) {
				return false;
			}
		} else if (!maxMsgSize.equals(other.maxMsgSize)) {
			return false;
		}
		if (mem == null) {
			if (other.mem != null) {
				return false;
			}
		} else if (!mem.equals(other.mem)) {
			return false;
		}
		if (nextNonce == null) {
			if (other.nextNonce != null) {
				return false;
			}
		} else if (!nextNonce.equals(other.nextNonce)) {
			return false;
		}
		if (size == null) {
			if (other.size != null) {
				return false;
			}
		} else if (!size.equals(other.size)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MetInf [anchor=" + anchor + ", emis=" + emis + ", format=" + format + ", mark=" + mark + ", maxMsgSize=" + maxMsgSize + ", mem=" + mem
				+ ", nextNonce=" + nextNonce + ", size=" + size + ", type=" + type + ", version=" + version + "]";
	}
}
