package org.synku4j.wbxml.marshal.impl;

import java.io.ByteArrayOutputStream;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;
import org.synku4j.wbxml.core.context.WbxmlContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MarshalDelegateTest {
	
	@BeforeClass
	public void setup() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}
	
	@Test
	public void testDelegate() throws Exception {
		
		final byte[] expected = new byte[] {
			0x02, 0x00, 0x6a, 0x00, // preamble
			0x46, 0x47, 0x03, 0x61, 0x20, 0x76, 0x61, 0x6c, 0x75, 0x65, 0x00, 0x01, 0x01, // element data
		};
		
		final MarshalDelegate md = new MarshalDelegate();
		
		
		final WbxmlContext cntx = new WbxmlContext();
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final TopClass source = new TopClass();
		source.setValue("a value");
		
		md.marshal(cntx, baos, source, source.getClass().getAnnotation(WbxmlPage.class));
		
		// Check that the stream contains the right length and the right values
		final StringBuilder sb = new StringBuilder();
		final byte[] actual = baos.toByteArray();
		
		// Check we have the same lengh buffers
		Assert.assertEquals(actual.length, expected.length);
		
		// Then make sure each of the elements matches.
		for (int i=0; i < expected.length; i++) {
			Assert.assertEquals(actual[i], expected[i], "Offset ["+i+"] does not match");
		}
		
	}
	
	@WbxmlPage(index=0, name="test")
	@WbxmlField(index=6, name="TopClass")
	private class TopClass {
		@WbxmlField(index=7, name="value")
		private String value;

		TopClass() {
		}
		
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
