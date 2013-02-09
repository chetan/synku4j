package com.zynku.sync.activesync.wbxml.marshal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.MarshalException;

public interface Marshaller<T> {
	T unmarshal(InputStream is) throws IOException, MarshalException;
	
	void marshal(OutputStream os,  T t) throws IOException, MarshalException;
}
