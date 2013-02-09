package com.zynku.core.sync;

/**
 * The <code>SyncResult</code> enum is used to specify the result of a 
 * sync operation as defined in the SyncController.
 * 
 * @author Jools Enticknap
 */
public enum SyncResult {
	SUCCESS,
	UNKNOWN_FAILURE,
	UNSUPPORTED_NETWORK,
	UNSUPPORTED_DOMAIN,
	INVALID_CREDENTIALS,
}
