package com.alteredmechanism.util.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class LineFilter extends TextFilter {

	public LineFilter() {
		super();
	}

	public abstract String filter(String line);
	
	@Override
	public void run(BufferedReader input, PrintWriter output) throws IOException {
		String line;
		while ((line = input.readLine()) != null) {
			output.println(filter(line));
		}
	}
	
}
