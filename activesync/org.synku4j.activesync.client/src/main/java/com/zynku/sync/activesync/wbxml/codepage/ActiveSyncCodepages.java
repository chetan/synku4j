package com.zynku.sync.activesync.wbxml.codepage;

import java.util.HashMap;
import java.util.Map;

import com.zynku.sync.wbxml.CodePage;

public class ActiveSyncCodepages {
	
	public enum CodePages {
		AirNotify(new AirNotifyCodePage()),
		AirSync(new AirSyncCodePage()),
		AirSyncBase(new AirSyncBaseCodePage()),
		POOMCONTACTS(new ContactsCodePage()),
		Contacts2(new Contacts2CodePage()),
		Ping(new PingCodePage());
		
		private final CodePage codePage;
		
		CodePages(CodePage codePage) {
			this.codePage = codePage;
		}
		
		public CodePage getCodePage() {
			return codePage;
		}
	}
	
	
	
	private static final Map<String, CodePage> CODEPAGES_BY_NAME = new HashMap<String, CodePage>();
	
	public static final CodePage[] CODEPAGES = new CodePage[22];

	static {
		CodePage[] pages = new CodePage[] { 
				new AirNotifyCodePage(),
				new AirSyncBaseCodePage(), 
				new AirSyncCodePage(),
				new CalendarCodePage(),
				new ContactsCodePage(), 
				new Contacts2CodePage(),
				new DocumentLibraryCodePage(),
				new EmailCodePage(),
				new FolderHierarchyCodePage(),
				new GALCodePage(),
				new ItemEstimateCodePage(),
				new ItemOperationsCodePage(),
				new MeetingResponseCodePage(),
				new MoveCodePage(),
				new PingCodePage(),
				new ProvisionCodePage(),
				new ResolveRecipientsCodePage(),
				new SearchCodePage(),
				new SettingsCodePage(),
				new TasksCodePage(),
				new ValidateCertCodePage(),
		};
		
		for (CodePage cp : pages) {
			CODEPAGES[cp.getCodePageIndex()] = cp;
			CODEPAGES_BY_NAME.put(cp.getCodePageName(), cp);
		}
	}
	
	public static CodePage lookupByName(final String name) {
		return CODEPAGES_BY_NAME.get(name);
	}
}
