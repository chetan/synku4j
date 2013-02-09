package org.synku4j.wbxml.decoder;
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



public class WbxmlDecoderException extends Exception {

	private static final long serialVersionUID = 1L;

	WbxmlDecoderException() {
	}

	WbxmlDecoderException(String message) {
		super(message);
	}

	WbxmlDecoderException(Throwable cause) {
		super(cause);
	}

	WbxmlDecoderException(String message, Throwable cause) {
		super(message, cause);
	}
}
