package com.zynku.sync.wbxml;

public interface CodePageLookup {
	CodePageField lookup(int codePage, int fieldId);
	CodePageField lookup(int codePage, String fieldName);
}
