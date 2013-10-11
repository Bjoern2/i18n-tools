package com.github.bjoern2.i18n.transformers;

import java.io.IOException;

public interface Transformer {

	void execute(String inputFile, String outputFile) throws IOException;
	
	boolean matches(String inputExt, String outputExt);
	
}
