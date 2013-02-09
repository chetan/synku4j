package com.zynku.sync.activesync.control.handler;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.MarshalException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zynku.sync.activesync.context.ActiveSyncContext;
import com.zynku.sync.activesync.model.FolderHierachy;
import com.zynku.sync.activesync.model.folderheirachy.FolderSyncResponse;
import com.zynku.sync.activesync.wbxml.marshal.FolderHeirachyMarshaller;

public class InitialFolderSyncHandler extends AbstractHttpHandler<FolderSyncResponse> {
	
	private static final Log log = LogFactory.getLog(InitialFolderSyncHandler.class);
	
	private static final String COMMAND = "FolderSync";
	
	private static final FolderHeirachyMarshaller MARSHALLER = new FolderHeirachyMarshaller();

	/**
	 * Certain WBXML requests can be precompiled. 
	 */
	private static final byte[] WBXML = {
		0x03,       // WBXML 1.3
		0x01,       // unknown public id.
		0x6A,       // use uft8
		0x00,       // no string table
		0x00, 0x07, // switch to code page 0x07
		0x56,       // Start Folder sync indicate content
		0x52,       // SyncKey
		0x03,       // inline string
		0x30, 0x00, // "0" + terminator
		0x01,       // close element
		0x01,       // close element
	};
	
	public InitialFolderSyncHandler() {
		if (log.isDebugEnabled()) {
			log.debug("Created new InitialFolderSyncHandler");
		}
	}

	public FolderSyncResponse process(final HttpClient httpClient, final ActiveSyncContext context) throws HandlerException {
		if (log.isDebugEnabled()) {
			
		}
		
		final PostMethod postMethod = new PostMethod(createRequestURL(context, COMMAND));
		populateHeaders(context, postMethod);
		postMethod.setRequestEntity(new ByteArrayRequestEntity(WBXML));
		
		int result;
		try {
			result = httpClient.executeMethod(postMethod);
		} catch (Exception e) {
			throw new HandlerException("Exception raised making http request", e);
		} finally {
			//postMethod.releaseConnection();
		}
		
		if (result != 200) {
			throw new HandlerException("HTTP code not 200, was ("+result+")");
		}

		
		try {
			return marshal(postMethod.getResponseBodyAsStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	private FolderSyncResponse marshal(final InputStream is) throws MarshalException, IOException {
		final FolderSyncResponse response = new FolderSyncResponse();
		
		final FolderHierachy fh = MARSHALLER.unmarshal(is);

		response.setStatus(fh.getStatus());
		response.setSyncKey(fh.getSyncKey());
		response.getAdded().addAll(fh.getAdded());
		response.getChanged().addAll(fh.getUpdated());
		response.getRemoved().addAll(fh.getDeleted());
		
		
		return response;
	}
}