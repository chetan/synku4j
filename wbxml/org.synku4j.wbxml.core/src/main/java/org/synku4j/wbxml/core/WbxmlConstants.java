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

package org.synku4j.wbxml.core;

/**
 * The <code>WbxmlConstants</code> interface defines a number of the standard
 * constants required to parse a WBXML stream. 
 * 
 * @author Jools Enticknap
 */
public interface WbxmlConstants {        // 12345678
	static final int SWITCH_PAGE = 0x00; // 00000000
	static final int END         = 0x01; // 00000001
	static final int ENTITY      = 0x02; // 00000010
	static final int STR_I       = 0x03; // 00000011
	static final int LITERAL     = 0x04; // 00000100
	static final int EXT_I_0     = 0x40; // 01000000
	static final int EXT_I_1     = 0x41; // 01000001
	static final int EXT_I_2     = 0x42; // 01000010
	static final int PI          = 0x43; // 01000011
	static final int LITERAL_C   = 0x44; // 01000100
	static final int EXT_T_0     = 0x80; // 10000000
	static final int EXT_T_1     = 0x81; // 10000001
	static final int EXT_T_2     = 0x82; // 10000010
	static final int STR_T       = 0x83; // 10000011
	static final int LITERAL_A   = 0x84; // 10000100
	static final int EXT_0       = 0xc0; // 11000000
	static final int EXT_1       = 0xc1; // 11000001
	static final int EXT_2       = 0xc2; // 11000010
	static final int OPAQUE      = 0xc3; // 11000011
	static final int LITERAL_AC  = 0xc4; // 11000100
	
	static final int TERMNIATOR = 0x00;  // 00000000
	
	static final int WBXML_VERSION_1_3 = 0x03;
	static final int WBXML_VERSION_1_2 = 0x02;
	
	// http://www.iana.org/assignments/character-sets
	static final int WBXML_ENCODING_UTF8 = 0x6A;
	static final int WBXML_ENCODING_ISO_8859_1 = 0x04;
}
