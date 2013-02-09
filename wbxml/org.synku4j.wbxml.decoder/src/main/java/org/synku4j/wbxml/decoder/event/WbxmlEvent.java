/*
 * Copyright (C) 2009 Jools Enticknap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.synku4j.wbxml.decoder.event;

import java.util.EventObject;

import org.synku4j.wbxml.core.WbxmlCodePageField;
import org.synku4j.wbxml.decoder.WbxmlDecoder;

public class WbxmlEvent extends EventObject {
	
	public enum EventType {
		StartElement, EndElement, Text, Opaque
	}

	private static final long serialVersionUID = 1L;
	
	private final EventType eventType;
	private final boolean hasContent;
	private final WbxmlCodePageField field;
	
	public WbxmlEvent(final WbxmlDecoder source, final EventType eventType, final WbxmlCodePageField field) {
		this(source, eventType, field, false);
	}
	
	public WbxmlEvent(final WbxmlDecoder source, final EventType eventType, final WbxmlCodePageField field, final boolean hasContent) {
		super(source);
		this.eventType = eventType;
		this.field = field;
		this.hasContent = hasContent;
	}
	
	public EventType getEventType() {
		return eventType;
	}
	
	
	public WbxmlCodePageField getField() {
		return field;
	}

	public boolean hasContent() {
		return hasContent;
	}

	public WbxmlDecoder getSourceAsWbxmlDecoder() {
		return (WbxmlDecoder) getSource();
	}
}
