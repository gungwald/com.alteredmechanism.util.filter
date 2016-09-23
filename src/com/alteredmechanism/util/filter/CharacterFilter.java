package com.alteredmechanism.util.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class CharacterFilter extends TextFilter {

	public CharacterFilter() {
		super();
	}
	
	public abstract char filter(char c);

	@Override
	public void run(BufferedReader input, PrintWriter output) throws IOException {
		int c;
		while ((c = input.read()) != -1) {
			output.write((int) filter((char) c));
		}
	}

}
