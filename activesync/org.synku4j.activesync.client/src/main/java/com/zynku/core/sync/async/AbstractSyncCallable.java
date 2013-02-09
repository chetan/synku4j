package com.zynku.core.sync.async;

import java.util.concurrent.Callable;

import com.zynku.core.model.ExternalIdentity;
import com.zynku.core.sync.SyncDomain;
import com.zynku.core.sync.SyncResult;
import com.zynku.core.sync.SyncStatus;

/**
 * The <code>AbstractSyncCallable</code> class is the base class for all 
 * Future operations 
 * 
 * @author Jools Enticknap
 */
public abstract class AbstractSyncCallable implements Callable<SyncStatus> {

	protected final ExternalIdentity externalIdentity;
	protected final SyncDomain syncDomain;

	protected AbstractSyncCallable(final ExternalIdentity externalIdentity, final SyncDomain domain) {
		this.externalIdentity = externalIdentity;
		this.syncDomain = domain;
		
		if (externalIdentity == null) {
			throw new IllegalArgumentException("externalIdentity cannot be null");
		}
		
		if (syncDomain == null) {
			throw new IllegalArgumentException("syncDomain cannot be null");
		}
	}
	
	public abstract SyncStatus call() throws Exception;
	
	protected SyncStatus createSyncStatus(final SyncResult syncResult) {
		return new SyncStatus(syncDomain, syncResult);
	}
	
	protected  ExternalIdentity getExternalIdentity() {
		return externalIdentity;
	}
	
	protected SyncDomain getSyncDomain() {
		return syncDomain;
	}
}
