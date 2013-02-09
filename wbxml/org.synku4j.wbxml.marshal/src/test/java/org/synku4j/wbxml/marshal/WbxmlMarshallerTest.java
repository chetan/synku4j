package org.synku4j.wbxml.marshal;

import java.io.ByteArrayOutputStream;

import org.apache.log4j.BasicConfigurator;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;
import org.synku4j.wbxml.core.WbxmlConstants;
import org.synku4j.wbxml.core.context.WbxmlContext;
import org.synku4j.wbxml.marshal.impl.DefaultWbxmlMarshaller;
import org.synku4j.wbxml.util.HexDump;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WbxmlMarshallerTest {

	@BeforeTest
	public void setup() {
		BasicConfigurator.configure();
	}
	
	@Test
	public void testMarshaller() throws Exception {
		
		final WbxmlMarshaller marshaller = new DefaultWbxmlMarshaller();
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		final WbxmlContext cntx = new WbxmlContext();
		cntx.setCaptureXML(false);
		cntx.setOpaqueStrings(true);
		cntx.setWbxmlEncoding(WbxmlConstants.WBXML_VERSION_1_2);
		cntx.setWbxmlEncoding(WbxmlConstants.WBXML_ENCODING_UTF8);
		
		TopClass topClass = new TopClass();
		topClass.setField1("Some data");
		
		marshaller.marshal(cntx, baos, topClass);
		StringBuilder sb = new StringBuilder();
		HexDump.dump(baos.toByteArray(), sb);
		System.out.println(sb.toString());
		
	}
	
	@WbxmlPage(index=0, name="Test", publicId=1)
	@WbxmlField(name="TopClass", index=5)
	public class TopClass {
		@WbxmlField(index=7, name="field1")
		private String field1;
		
		public TopClass() {
		}

		public String getField1() {
			return field1;
		}

		public void setField1(String field1) {
			this.field1 = field1;
		}
	}
}
