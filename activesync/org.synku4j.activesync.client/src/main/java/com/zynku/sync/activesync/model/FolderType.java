package com.zynku.sync.activesync.model;

public enum FolderType {
	
	USER_CREATED_FOLDER(1),
	DEFAULT_INBOX(2),
	DEFAULT_DRAFTS(3),
	DEFAULT_DELETED(4),
	DEFAULT_SENT(5),
	DEFAULT_OUTBOX(6),
	DEFAULT_TASKS(7),
	DEFAULT_CALENDAR(8),
	DEFAULT_CONTACTS(9),
	DEFAULT_NOTES(10),
	DEFAULT_JOURNAL(11),
	
	USER_CREATED_MAIL_FOLDER(12),
	USER_CREATED_CALENDAR_FOLDER(13),
	USER_CREATED_CONTACTS_FOLDER(14),
	USER_CREATED_TASKS_FOLDER(14),
	;
	
	
	private final int id;
	
	FolderType(final int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
//	1 User-created folder (generic)  
//	2 Default Inbox folder 
//	3 Default Drafts folder 
//	4 Default Deleted Items folder 
//	5 Default Sent Items folder 
//	6 Default Outbox folder 
//	7 Default Tasks folder 
//	8 Default Calendar folder 
//	9 Default Contacts folder 
//	10 Default Notes folder 
//	11 Default Journal folder 
//	12 User-created Mail folder 
//	13 User-created Calendar folder 
//	14 User-created Contacts folder 
//	15 User-created Tasks folder 
//	16 User-created journal folder 
//	17 User-created Notes folder 
//	18 Unknown folder type 
//	19 Recipient information cache  
	
}
