/**
 * 
 */
package com.disys.analyzer.config;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

/**
 * @author Sajid
 * @since 6th January, 2019
 *
 */
public class AnalyzerPrintStream extends PrintStream
{
	
	public AnalyzerPrintStream(OutputStream out)
	{
		super(out);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.setOut(new AnalyzerPrintStream(System.out));
		// TODO Auto-generated method stub
		System.out.println("Hello worlds");
	}
	
	@Override
    public void println(String string) {
        Date date = new Date();
        super.println("[" + date.toString() + "] " + string);
    }
	
}
