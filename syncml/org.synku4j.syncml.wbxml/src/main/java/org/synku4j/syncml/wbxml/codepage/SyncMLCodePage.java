package org.synku4j.syncml.wbxml.codepage;


public enum SyncMLCodePage {
	;

	public static final String NAMESPACE_NAME = "SyncML";
	public static final int NAMESPACE_IDX = 0x00;
}

//import org.synku4j.wbxml.WbxmlCodePageField;
//
//public enum SyncMLCodePage implements WbxmlCodePageField {
//	Add(0x05, org.synku4j.syncml.model.Add.class), 
//	Alert(0x06, org.synku4j.syncml.model.Alert.class), 
//	Archive(0x07), 
//	Atomic(0x08, org.synku4j.syncml.model.Atomic.class),
//	Chal(0x09, org.synku4j.syncml.model.Challenge.class), 
//	Cmd(0x0a), 
//	CmdID(0x0b), 
//	CmdRef(0x0c), 
//	Copy(0x0d, org.synku4j.syncml.model.Copy.class), 
//	Cred(0x0e, org.synku4j.syncml.model.Credential.class), 
//	Data(0x0f), 
//	Delete(0x10, org.synku4j.syncml.model.Delete.class), 
//	Exec(0x11, org.synku4j.syncml.model.Delete.class), 
//	Final(0x12), 
//	Get(0x13, org.synku4j.syncml.model.Get.class), 
//	Item(0x14, org.synku4j.syncml.model.Item.class), 
//	Lang(0x15), 
//	LocName(0x16), 
//	LocURI(0x17),
//	Map(0x18, org.synku4j.syncml.model.Map.class), 
//	MapItem(0x19, org.synku4j.syncml.model.MapItem.class), 
//	Meta(0x1a), 
//	MsgID(0x1b), 
//	MsgRef(0x1c), 
//	NoResp(0x1d), 
//	NoResults(0x1e), 
//	Put(0x1f, org.synku4j.syncml.model.Put.class), 
//	Replace(0x20, org.synku4j.syncml.model.Replace.class), 
//	RespURI(0x21), 
//	Results(0x22, org.synku4j.syncml.model.Results.class), 
//	Search(0x23, org.synku4j.syncml.model.Search.class),
//	Sequence(0x24, org.synku4j.syncml.model.Sequence.class),	
//	SessionID(0x25), 
//	SftDel(0x26), 
//	Source(0x27), 
//	SourceRef(0x28), 	
//	Status(0x29, org.synku4j.syncml.model.Status.class), 
//	Sync(0x2A, org.synku4j.syncml.model.Sync.class),	
//	SyncBody(0x2B, org.synku4j.syncml.model.SyncBody.class),	
//	SyncHdr(0x2C, org.synku4j.syncml.model.SyncHeader.class), 
//	SyncML(0x2D, org.synku4j.syncml.model.SyncML.class), 
//	Target(0x2E), 
//	TargetRef(0x2F), 
//	RESERVED(0x30), 
//	VerDTD(0x31), 
//	VerProto(0x32),
//	NumberOfChanged(0x33),	
//	MoreData(0x34), Field(0x35),
//	Filter(0x36), 
//	Record(0x37),
//	FilterType(0x38),
//	SourceParent(0x39),
//	TargetParent(0x3a), 
//	Move(0x3b),
//	Correlator(0x3c);
//
//	private final int idx;
//	private final Class<?> modelClass;
//	public static final String NAMESPACE_NAME = "SyncML";
//	public static final int NAMESPACE_IDX = 0x00;
//
//	SyncMLCodePage(final int idx) {
//		this(idx, null);
//	}
//
//	SyncMLCodePage(final int idx, final Class<?> modelClass) {
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
