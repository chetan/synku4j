package org.synku4j.wbxml.util;

public final class HexDump {

	private HexDump() {
	}
	
	public static void dump(final byte[] data, final StringBuilder sb) {
		for (byte b : data) {
			String hx = Integer.toHexString(((int)b)&0xFF);
			if (hx.length() == 1) {
				hx = "0"+hx;
			}
			sb.append("[")
			  .append(hx)
			  .append("] ");
		}
	}
}
