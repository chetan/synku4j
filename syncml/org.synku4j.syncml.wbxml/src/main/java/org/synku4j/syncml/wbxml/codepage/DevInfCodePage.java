package org.synku4j.syncml.wbxml.codepage;



public enum DevInfCodePage {

	;
	
	public static final String NAMESPACE_NAME = "DevInf";
	public static final int NAMESPACE_IDX = 0x00;
	public static final String PUBLIC_ID = "-//SYNCML//DTD DevInf 1.0//EN";
}


//public enum DevInfCodePage implements WbxmlCodePageField {
//	CTCap(0x05), 
//	CTType(0x06),	
//	DataStore(0x07, org.synku4j.syncml.model.devinf.DataStore.class), 
//	DataType(0x08), 
//	DevID(0x09), 
//	DevInf(0x0A, org.synku4j.syncml.model.devinf.DevInf.class), 
//	DevTyp(0x0B), 
//	DisplayName(0x0C), 
//	DSMem(0x0D, org.synku4j.syncml.model.devinf.DsMem.class),	
//	Ext(0x0E), 
//	FwV(0x0F), 
//	HwV(0x10), 
//	Man(0x11), 
//	MaxGUIDSize(0x12), 
//	MaxID(0x13), 
//	MaxMem(0x14), 
//	Mod(0x15), 
//	OEM(0x16), 
//	ParamName(0x17), 
//	PropName(0x18), 
//	Rx(0x19, org.synku4j.syncml.model.devinf.Rx.class),
//	RxPref(0x1A, org.synku4j.syncml.model.devinf.RxPref.class),
//	SharedMem(0x1B), 
//	Size(0x1C), 
//	SourceRef(0x1D), 
//	SwV(0x1E),	
//	SyncCap(0x1F, org.synku4j.syncml.model.devinf.SyncCap.class), 
//	SyncType(0x20),	
//	Tx(0x21), 
//	TxPref(0x22),	
//	ValEnum(0x23), 
//	VerCT(0x24), 
//	VerDTD(0x25), 
//	Xnam(0x26), 
//	Xval(0x27), 
//	UTC	(0x28), 
//	SupportNumberOfChanges(0x29), 
//	SupportLargeObjs(0x2a) ;
//	
//	private final int idx;
//	private final Class<?> modelClass;
//	public static final String NAMESPACE_NAME = "DevInf";
//	public static final int NAMESPACE_IDX = 0x00;
//	public static final String PUBLIC_ID = "-//SYNCML//DTD DevInf 1.0//EN";
//
//	DevInfCodePage(final int idx) {
//		this(idx, null);
//	}
//
//	
//	DevInfCodePage(final int idx, final Class<?> modelClass) {
//		this.idx = idx;
//		this.modelClass = modelClass;
//	}
//	
//	@Override
//	public int getToken() {
//		return idx;
//	}
//
//	@Override
//	public String getFieldName() {
//		return name();
//	}
//
//	@Override
//	public int getPage() {
//		return NAMESPACE_IDX;
//	}
//
//	@Override
//	public Class<?> getModelClass() {
//		return modelClass;
//	}
//	
//	public static List<WbxmlCodePageField> valuesAsList() {
//		final ArrayList<WbxmlCodePageField> result = new ArrayList<WbxmlCodePageField>();
//		for (WbxmlCodePageField cpf : values()) {
//			result.add(cpf);
//		}
//		return result;
//	}
//}
