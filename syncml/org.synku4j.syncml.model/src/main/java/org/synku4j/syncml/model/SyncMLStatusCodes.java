package org.synku4j.syncml.model;

public interface SyncMLStatusCodes {

	/**
	 * In progress. The specified SyncML command is being carried out, but has
	 * not yet completed.
	 */
	static final int IN_PROGRESS = 101;

	/**
	 * OK. The SyncML command completed successfully.
	 */
	static final int OK = 200;
	/**
	 * Item added. The requested item was added.
	 */
	static final int ITEM_ADDED = 201;

	/**
	 * Accepted for processing. The request to either run a remote execution of
	 * an application or to alert a user or application was successfully
	 * performed.
	 * 
	 */
	static final int ACCEPTED_FOR_PROCESSING = 202;
	/**
	 * Non-authoritative response. The request is being responded to by an
	 * entity other than the one targeted. The response is only to returned when
	 * the request would have been resulted in a 200 response code from the
	 * authoritative target.
	 */
	static final int NON_AUTHORATIVE = 203;

	/**
	 * Invalid credentials. The requested command failed because the requestor
	 * MUST provide proper authentication. If the property type of
	 * authentication was presented in the original request, then the response
	 * code indicates that the requested command has been refused for those
	 * credentials.
	 */
	static final int INVALID_CREDENTIALS = 401;

	/**
	 * Missing credentials. This response code is similar to 401 except that the
	 * response code indicates that the originator MUST first authenticate with
	 * the recipient. The recipient SHOULD also return the suitable challenge in
	 * the Chal element type in the Status.
	 */
	static final String MISSING_CREDENTIALS = "407";
}
