package org.synku4j.syncml.wbxml.codepage;


public enum MetInfCodePage {

	;
	public static final String NAMESPACE_NAME = "MetInf";
	public static final int NAMESPACE_IDX = 0x01;
}


//import org.synku4j.wbxml.WbxmlCodePageField;
//
//public enum MetInfCodePage implements WbxmlCodePageField {
//	Anchor(0x05, org.synku4j.syncml.model.Anchor.class), 
//	EMI(0x06),
//	Format(0x07),
//	FreeID(0x08), 
//	FreeMem(0x09), 
//	Last(0x0a),
//	Mark(0x0b), 
//	MaxMsgSize(0x0c), 
//	Mem(0x0d),
//	MetInf(0x0e), 
//	Next(0x0f), 
//	NextNonce(0x10),
//	SharedMem(0x11), 
//	Size(0x12), 
//	Type(0x13),
//	Version(0x14), 
//	MaxObjSize(0x15), // 1.1 support 
//	FieldLevel(0x16); // 1.1 support
//	
//	private final int idx;
//	private final Class<?> modelClass;
//	public static final String NAMESPACE_NAME = "MetInf";
//	public static final int NAMESPACE_IDX = 0x01;
//
//	MetInfCodePage(final int idx) {
//		this(idx, null);
//	}
//	
//	MetInfCodePage(final int idx, final Class<?> modelClass) {
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
//}
