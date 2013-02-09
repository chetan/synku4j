package com.zynku.sync.activesync.wbxml.marshal;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.MarshalException;

import com.zynku.sync.activesync.wbxml.codepage.ActiveSyncCodePageLookup;
import com.zynku.sync.wbxml.CodePage;
import com.zynku.sync.wbxml.decoder.WbxmlDecoder;
import com.zynku.sync.wbxml.decoder.WbxmlDecoderException;
import com.zynku.sync.wbxml.decoder.WbxmlEvent;


public abstract class AbstractMarshaller<T> implements Marshaller<T> {
	
	
	protected abstract CodePage[] getCodePages();
	
	protected WbxmlDecoder createDecoder(final InputStream is) throws IOException, MarshalException {
		try {
			return new WbxmlDecoder(is, ActiveSyncCodePageLookup.getInstance());
		} catch (WbxmlDecoderException e) {
			throw new MarshalException("Exception raised creating WbxmlDecoder", e);
		}
	}
	
	protected WbxmlEvent next(final WbxmlDecoder decoder) throws MarshalException {
		try {
			return decoder.next();
		} catch (WbxmlDecoderException e) {
			throw new MarshalException("Exception raised calling decoder.next()", e);
		}
	}
}
