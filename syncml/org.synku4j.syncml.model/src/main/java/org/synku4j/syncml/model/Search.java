package org.synku4j.syncml.model;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=SyncMLCodePage.NAMESPACE_IDX, name=SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x23, name="Search")
public class Search extends SyncMLCmd {

	@WbxmlField(index=0x1d, name="NoResp")
	private String noResp;

	@WbxmlField(index=0x1e, name="NoResults")
	private Boolean noResults;

	@WbxmlField(index=0x0e, name="Cred")
	private Credential cred;

	@WbxmlField(index=0x2e, name="Target")
	private Location target;

	@WbxmlField(index=0x27, name="Source")
	private Collection<Location> sources;
	
	@WbxmlField(index=0x15, name="Lang")
	private String lang;
	
	@WbxmlField(index=0x1a, name="Meta")
	private MetInf meta;
	
	@WbxmlField(index=0x0f, name="Data")
	private Object data;
	
	public Search() {
	}

	public String getNoResp() {
		return noResp;
	}

	public void setNoResp(String noResp) {
		this.noResp = noResp;
	}

	public Boolean getNoResults() {
		return noResults;
	}

	public void setNoResults(Boolean noResults) {
		this.noResults = noResults;
	}

	public Credential getCred() {
		return cred;
	}

	public void setCred(Credential cred) {
		this.cred = cred;
	}

	public Location getTarget() {
		return target;
	}

	public void setTarget(Location target) {
		this.target = target;
	}

	public Collection<Location> getSources() {
		return sources;
	}

	public void setSources(Collection<Location> sources) {
		this.sources = sources;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public MetInf getMeta() {
		return meta;
	}

	public void setMeta(MetInf MetInf) {
		this.meta = MetInf;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cred == null) ? 0 : cred.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((meta == null) ? 0 : meta.hashCode());
		result = prime * result + ((noResp == null) ? 0 : noResp.hashCode());
		result = prime * result + ((noResults == null) ? 0 : noResults.hashCode());
		result = prime * result + ((sources == null) ? 0 : sources.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Search)) {
			return false;
		}
		Search other = (Search) obj;
		if (cred == null) {
			if (other.cred != null) {
				return false;
			}
		} else if (!cred.equals(other.cred)) {
			return false;
		}
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!data.equals(other.data)) {
			return false;
		}
		if (lang == null) {
			if (other.lang != null) {
				return false;
			}
		} else if (!lang.equals(other.lang)) {
			return false;
		}
		if (meta == null) {
			if (other.meta != null) {
				return false;
			}
		} else if (!meta.equals(other.meta)) {
			return false;
		}
		if (noResp == null) {
			if (other.noResp != null) {
				return false;
			}
		} else if (!noResp.equals(other.noResp)) {
			return false;
		}
		if (noResults == null) {
			if (other.noResults != null) {
				return false;
			}
		} else if (!noResults.equals(other.noResults)) {
			return false;
		}
		if (sources == null) {
			if (other.sources != null) {
				return false;
			}
		} else if (!sources.equals(other.sources)) {
			return false;
		}
		if (target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!target.equals(other.target)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Search [cred=" + cred + ", data=" + data + ", lang=" + lang + ", MetInf=" + meta + ", noResp=" + noResp + ", noResults=" + noResults
				+ ", sources=" + sources + ", target=" + target + ", getCmdId()=" + getCmdId() + "]";
	}
	
	@Override
	public String getCmdName() {
		return getClass().getAnnotation(WbxmlField.class).name();
	}
}
