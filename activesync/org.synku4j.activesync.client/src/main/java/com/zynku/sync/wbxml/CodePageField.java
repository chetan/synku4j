package com.zynku.sync.wbxml;


/**
 * The <code>CodePageField</code> interface defines methods which must be 
 * implemented for enums which represent a field of a codepage. 
 * 
 * @author Jools Enticknap
 */
public interface CodePageField  {
	
	/**
	 * @return the name of the field (normally the enum name)
	 */
	String getFieldName();
	
	/**
	 * @return the inde of this field in the codepage.
	 */
	int getCodePageIndex();
	
	/**
	 * @return the index of the namespace.
	 */
	int getNameSpaceIndex();
	
	/**
	 * @return the name of the namespace.
	 */
	
	String getNameSpace();
}
