package com.alteredmechanism.util.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * Implements and standardizes the basic tasks of filtering text.
 * 
 * @author bill.chatfield
 */
public abstract class TextFilter {
	
	/**
	 * Creates a filter.
	 */
	public TextFilter() {
		super();
	}
	
	/**
	 * Implemented by subclasses CharacterFilter and LineFilter.
	 * 
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	public abstract void run(BufferedReader input, PrintWriter output) throws IOException;
		
	/**
	 * 
	 * @param input
	 * @throws IOException
	 */
	public void run(BufferedReader input) throws IOException {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		run(input, out);
	}
	
	public void run(PrintWriter output) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		run(in, output);
	}
	
	public void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		run(in, out);
	}
	
	public void run(File toUpdateInPlace) throws IOException {
		// Create temp file
		File tmp = File.createTempFile("flt", null, toUpdateInPlace.getParentFile());
		
		// Read from original, write to temp file.
		BufferedReader input = null;
		PrintWriter output = null;
		try {
			input = new BufferedReader(new FileReader(toUpdateInPlace));
			output = new PrintWriter(new BufferedWriter(new FileWriter(tmp)));
			run(input, output);
		}
		finally {
			close(output);
			close(input);
		}
		// Move original to backup.
		toUpdateInPlace.renameTo(new File(toUpdateInPlace.getParentFile(), toUpdateInPlace.getName() + ".bak"));
		// Move temp file to original.
		tmp.renameTo(toUpdateInPlace);
	}
	
	protected void close(Reader io) {
		if (io != null) {
			try {
				io.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void close(Writer io) {
		if (io != null) {
			try {
				io.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
