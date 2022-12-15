/**
 * 
 */
package com.disys.analyzer.exception;

/**
 * @author Sajid
 * @
 */
public class ReportDataNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7545335981951657471L;
	
	public ReportDataNotFoundException() {
    }

    public ReportDataNotFoundException(String message) {
        super(message);
    }

    public ReportDataNotFoundException(Throwable arg0) {
        super(arg0);
    }

    public ReportDataNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ReportDataNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
