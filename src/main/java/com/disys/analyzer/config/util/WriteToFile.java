/**
 * 
 */
package com.disys.analyzer.config.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Sajid
 * @since Dec 7, 2017
 */
public class WriteToFile {
	public File createFile(String filePath) {
		File file;
		try {

			file = new File(filePath);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			} else {
				if (file.delete()) {
					System.out.println(file.getName() + " is deleted!");
					file.createNewFile();
				} else {
					System.out.println("Delete operation is failed.");
				}
			}
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void writeToFile(File file, String contents) {
		FileOutputStream fop = null;
		try {

			fop = new FileOutputStream(file, true);

			// get the content in bytes
			byte[] contentInBytes = contents.getBytes();

			fop.write(contentInBytes);

			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WriteToFile obj = new WriteToFile();
		StringBuilder sb = new StringBuilder();
		sb.append("This is the text content - Newly added this time");
		sb.append(System.getProperty("line.separator"));
		// sb.append("Sajid");
		String filePath = "C:/newFILE.txt";
		File file = obj.createFile(filePath);
		obj.writeToFile(file, sb.toString());
		sb = new StringBuilder();
		sb.append("This is the old text content - Oldy added this time");
		sb.append(System.getProperty("line.separator"));
		obj.writeToFile(file, sb.toString());
		sb = new StringBuilder();
		sb.append("Some other text");

		obj.writeToFile(file, sb.toString());

	}
}
