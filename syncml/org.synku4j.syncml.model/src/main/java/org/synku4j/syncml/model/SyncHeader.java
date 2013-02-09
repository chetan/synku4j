package org.synku4j.syncml.model;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

/**
 * The <code>SyncHeader</code> class represents the header element of a syncml
 * document.
 * 
 * Implementation notes 
 * --------------------
 * 
 * Target and Source 
 * 
 * Specifies the address of either the data synchronization
 * server or the mobile client. 
 * 
 * When addressing the: Server - MUST use LocURI
 * element type to specify the absolute URI form of network address of the
 * synchronization server. 
 * 
 * Client - MUST use LocURI element type to specify the
 * absolute URI form of network address of the mobile client or IMEI URN to
 * specify the global identifier associated with the mobile client. 
 * 
 * RespURI
 * Specifies the address of to be used in the Target of the response message.
 * The value is an absolute URI. CGI script parameter can be appended to the URI
 * to perform selection filtering such as specifying a date/time after which the
 * response should be performed.
 * 
 * @author Jools Enticknap
 */
@WbxmlPage(index = SyncMLCodePage.NAMESPACE_IDX, name = SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index = 0x2C, name = "SyncHdr")
public class SyncHeader {
	@WbxmlField(index = 0x31, name = "VerDTD", required = true)
	private String verDTD;

	@WbxmlField(index = 0x32, name = "VerProto", required = true)
	private String verProto;

	@WbxmlField(index = 0x25, name = "SessionID", required = true)
	private String sessionID;

	@WbxmlField(index = 0x1b, name = "MsgID", required = true)
	private String msgID;

	@WbxmlField(index = 0x2e, name = "Target", required = true)
	private Location target;

	@WbxmlField(index = 0x27, name = "Source", required = true)
	private Location source;

	@WbxmlField(index = 0x21, name = "RespURI", required = false)
	private String respURI;

	@WbxmlField(index = 0x1d, name = "NoResp", required = false)
	private Boolean noResp;

	@WbxmlField(index = 0x0e, name = "Cred")
	private Credential cred;

	@WbxmlField(index = 0x1a, name = "Meta", required = false)
	private MetInf meta;

	public SyncHeader() {
	}

	public String getVerDTD() {
		return verDTD;
	}

	public void setVerDTD(String verDTD) {
		this.verDTD = verDTD;
	}

	public String getVerProto() {
		return verProto;
	}

	public void setVerProto(String verProto) {
		this.verProto = verProto;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public Location getTarget() {
		return target;
	}

	public void setTarget(Location target) {
		this.target = target;
	}

	public Location getSource() {
		return source;
	}

	public void setSource(Location source) {
		this.source = source;
	}

	public MetInf getMeta() {
		return meta;
	}

	public void setMeta(MetInf MetInf) {
		this.meta = MetInf;
	}

	public String getRespURI() {
		return respURI;
	}

	public void setRespURI(String respURI) {
		this.respURI = respURI;
	}

	public Boolean getNoResp() {
		return noResp;
	}

	public void setNoResp(Boolean noResp) {
		this.noResp = noResp;
	}

	public Credential getCred() {
		return cred;
	}

	public void setCred(Credential cred) {
		this.cred = cred;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cred == null) ? 0 : cred.hashCode());
		result = prime * result + ((meta == null) ? 0 : meta.hashCode());
		result = prime * result + ((msgID == null) ? 0 : msgID.hashCode());
		result = prime * result + ((noResp == null) ? 0 : noResp.hashCode());
		result = prime * result + ((respURI == null) ? 0 : respURI.hashCode());
		result = prime * result
				+ ((sessionID == null) ? 0 : sessionID.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		result = prime * result + ((verDTD == null) ? 0 : verDTD.hashCode());
		result = prime * result
				+ ((verProto == null) ? 0 : verProto.hashCode());
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
		if (!(obj instanceof SyncHeader)) {
			return false;
		}
		SyncHeader other = (SyncHeader) obj;
		if (cred == null) {
			if (other.cred != null) {
				return false;
			}
		} else if (!cred.equals(other.cred)) {
			return false;
		}
		if (meta == null) {
			if (other.meta != null) {
				return false;
			}
		} else if (!meta.equals(other.meta)) {
			return false;
		}
		if (msgID == null) {
			if (other.msgID != null) {
				return false;
			}
		} else if (!msgID.equals(other.msgID)) {
			return false;
		}
		if (noResp == null) {
			if (other.noResp != null) {
				return false;
			}
		} else if (!noResp.equals(other.noResp)) {
			return false;
		}
		if (respURI == null) {
			if (other.respURI != null) {
				return false;
			}
		} else if (!respURI.equals(other.respURI)) {
			return false;
		}
		if (sessionID == null) {
			if (other.sessionID != null) {
				return false;
			}
		} else if (!sessionID.equals(other.sessionID)) {
			return false;
		}
		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}
		if (target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!target.equals(other.target)) {
			return false;
		}
		if (verDTD == null) {
			if (other.verDTD != null) {
				return false;
			}
		} else if (!verDTD.equals(other.verDTD)) {
			return false;
		}
		if (verProto == null) {
			if (other.verProto != null) {
				return false;
			}
		} else if (!verProto.equals(other.verProto)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SyncHeader [cred=" + cred + ", MetInf=" + meta + ", msgID="
				+ msgID + ", noResp=" + noResp + ", respURI=" + respURI
				+ ", sessionID=" + sessionID + ", source=" + source
				+ ", target=" + target + ", verDTD=" + verDTD + ", verProto="
				+ verProto + "]";
	}
}
