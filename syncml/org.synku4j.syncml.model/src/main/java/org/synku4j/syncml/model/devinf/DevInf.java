package org.synku4j.syncml.model.devinf;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.DevInfCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=DevInfCodePage.NAMESPACE_IDX, name=DevInfCodePage.NAMESPACE_NAME)
@WbxmlField(name="DevInf", index=0x0A)
public class DevInf {
	@WbxmlField(index=0x25, name="VerDTD", required=true)
	private String verDTD;
	
	@WbxmlField(index=0x11, name="Man")
	private String man;
	
	@WbxmlField(index=0x15, name="Mod")
	private String mod;
	
	@WbxmlField(index=0x16, name="OEM")
	private String oem;
	
	@WbxmlField(index=0x0F, name="FwV")
	private String firmwareVersion;
	
	@WbxmlField(index=0x1E, name="SwV")
	private String softwareVersion;
	
	@WbxmlField(index=0x10, name="HwV")
	private String hardwareVersion;
	
	@WbxmlField(index=0x09, name="DevID", required=true)
	private String deviceID;
	
	@WbxmlField(index=0x0B, name="DevTyp", required=true)
	private String deviceType;
	
	@WbxmlField(index=0x28, name="UTC", required=true)
	private Boolean utc;

	@WbxmlField(index=0x2a, name="SupportLargeObjs")
	private Boolean largeObjSupport;
	
	@WbxmlField(index=0x29, name="SupportNumberOfChanges")
	private Boolean supportNumberOfChanges;
	
	@WbxmlField(index=0x07, name="DataStore")
	private Collection<DataStore> dataStores;
	
	@WbxmlField(index=0x05, name="CTCap")
	private Collection<CTCap> ctCaps;
	
	public DevInf() {
	}

	public String getVerDTD() {
		return verDTD;
	}

	public void setVerDTD(String verDTD) {
		this.verDTD = verDTD;
	}

	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Boolean getUtc() {
		return utc;
	}

	public void setUtc(Boolean utc) {
		this.utc = utc;
	}

	public Boolean getLargeObjSupport() {
		return largeObjSupport;
	}

	public void setLargeObjSupport(Boolean largeObjSupport) {
		this.largeObjSupport = largeObjSupport;
	}

	public Collection<DataStore> getDataStores() {
		return dataStores;
	}

	public void setDataStores(Collection<DataStore> dataStores) {
		this.dataStores = dataStores;
	}

	public Collection<CTCap> getCtCaps() {
		return ctCaps;
	}

	public void setCtCaps(Collection<CTCap> ctCaps) {
		this.ctCaps = ctCaps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ctCaps == null) ? 0 : ctCaps.hashCode());
		result = prime * result + ((dataStores == null) ? 0 : dataStores.hashCode());
		result = prime * result + ((deviceID == null) ? 0 : deviceID.hashCode());
		result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
		result = prime * result + ((firmwareVersion == null) ? 0 : firmwareVersion.hashCode());
		result = prime * result + ((hardwareVersion == null) ? 0 : hardwareVersion.hashCode());
		result = prime * result + ((largeObjSupport == null) ? 0 : largeObjSupport.hashCode());
		result = prime * result + ((man == null) ? 0 : man.hashCode());
		result = prime * result + ((mod == null) ? 0 : mod.hashCode());
		result = prime * result + ((oem == null) ? 0 : oem.hashCode());
		result = prime * result + ((softwareVersion == null) ? 0 : softwareVersion.hashCode());
		result = prime * result + ((supportNumberOfChanges == null) ? 0 : supportNumberOfChanges.hashCode());
		result = prime * result + ((utc == null) ? 0 : utc.hashCode());
		result = prime * result + ((verDTD == null) ? 0 : verDTD.hashCode());
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
		if (!(obj instanceof DevInf)) {
			return false;
		}
		DevInf other = (DevInf) obj;
		if (ctCaps == null) {
			if (other.ctCaps != null) {
				return false;
			}
		} else if (!ctCaps.equals(other.ctCaps)) {
			return false;
		}
		if (dataStores == null) {
			if (other.dataStores != null) {
				return false;
			}
		} else if (!dataStores.equals(other.dataStores)) {
			return false;
		}
		if (deviceID == null) {
			if (other.deviceID != null) {
				return false;
			}
		} else if (!deviceID.equals(other.deviceID)) {
			return false;
		}
		if (deviceType == null) {
			if (other.deviceType != null) {
				return false;
			}
		} else if (!deviceType.equals(other.deviceType)) {
			return false;
		}
		if (firmwareVersion == null) {
			if (other.firmwareVersion != null) {
				return false;
			}
		} else if (!firmwareVersion.equals(other.firmwareVersion)) {
			return false;
		}
		if (hardwareVersion == null) {
			if (other.hardwareVersion != null) {
				return false;
			}
		} else if (!hardwareVersion.equals(other.hardwareVersion)) {
			return false;
		}
		if (largeObjSupport == null) {
			if (other.largeObjSupport != null) {
				return false;
			}
		} else if (!largeObjSupport.equals(other.largeObjSupport)) {
			return false;
		}
		if (man == null) {
			if (other.man != null) {
				return false;
			}
		} else if (!man.equals(other.man)) {
			return false;
		}
		if (mod == null) {
			if (other.mod != null) {
				return false;
			}
		} else if (!mod.equals(other.mod)) {
			return false;
		}
		if (oem == null) {
			if (other.oem != null) {
				return false;
			}
		} else if (!oem.equals(other.oem)) {
			return false;
		}
		if (softwareVersion == null) {
			if (other.softwareVersion != null) {
				return false;
			}
		} else if (!softwareVersion.equals(other.softwareVersion)) {
			return false;
		}
		if (supportNumberOfChanges == null) {
			if (other.supportNumberOfChanges != null) {
				return false;
			}
		} else if (!supportNumberOfChanges.equals(other.supportNumberOfChanges)) {
			return false;
		}
		if (utc == null) {
			if (other.utc != null) {
				return false;
			}
		} else if (!utc.equals(other.utc)) {
			return false;
		}
		if (verDTD == null) {
			if (other.verDTD != null) {
				return false;
			}
		} else if (!verDTD.equals(other.verDTD)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DevInf [ctCaps=" + ctCaps + ", dataStores=" + dataStores + ", deviceID=" + deviceID + ", deviceType=" + deviceType
				+ ", firmwareVersion=" + firmwareVersion + ", hardwareVersion=" + hardwareVersion + ", largeObjSupport=" + largeObjSupport
				+ ", man=" + man + ", mod=" + mod + ", oem=" + oem + ", softwareVersion=" + softwareVersion + ", supportNumberOfChanges="
				+ supportNumberOfChanges + ", utc=" + utc + ", verDTD=" + verDTD + "]";
	}

	public Boolean getSupportNumberOfChanges() {
		return supportNumberOfChanges;
	}

	public void setSupportNumberOfChanges(Boolean supportNumberOfChanges) {
		this.supportNumberOfChanges = supportNumberOfChanges;
	}
}
