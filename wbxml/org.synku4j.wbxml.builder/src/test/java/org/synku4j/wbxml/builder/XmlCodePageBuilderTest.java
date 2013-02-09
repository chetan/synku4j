package org.synku4j.wbxml.builder;

import java.io.InputStream;

import org.synku4j.wbxml.core.WbxmlCodePage;
import org.synku4j.wbxml.core.WbxmlDocument;
import org.testng.Assert;
import org.testng.annotations.Test;

public class XmlCodePageBuilderTest {

	@Test(enabled=false)
	public void testXmlBuilder() throws Exception {
		final InputStream is = getClass().getResourceAsStream("/codepages/test-codepage.xml");
		Assert.assertNotNull(is);
		final WbxmlDocument[] documents = XmlCodePageBuilder.build(is);	
		Assert.assertNotNull(documents);
		Assert.assertEquals(documents.length, 1);
		
		WbxmlDocument wbxmlDoc = documents[0];
		WbxmlCodePage[] codepages = wbxmlDoc.getCodePages();
		Assert.assertEquals(codepages.length, 2);
	}
}
