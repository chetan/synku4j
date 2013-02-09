package com.zynku.sync.wbxml.decoder;

import java.util.EventObject;

public final class WbxmlEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;

	public static final int START_ELEMENT = 1;
	public static final int END_ELEMENT = 2;
	public static final int TEXT_CONTENT = 3;
	
	private final int code;
	private final String elementName;
	private final String namespace;
	private final boolean hasContent;

	public WbxmlEvent(final WbxmlDecoder source,  final int code, final String elementName, final String namespace) {
		this(source, code, elementName, namespace, true);
	}
	
	public WbxmlEvent(final WbxmlDecoder source, final int code, final String elementName, final String namespace, final boolean hasContent) {
		super(source);
		this.code = code;
		this.elementName = elementName;
		this.namespace = namespace;
		this.hasContent = hasContent;
	}

	public int getCode() {
		return code;
	}

	public String getElementName() {
		return elementName;
	}

	public String getNamespace() {
		return namespace;
	}

	public boolean hasContent() {
		return hasContent;
	}
}
