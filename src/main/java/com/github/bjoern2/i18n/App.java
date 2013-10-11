package com.github.bjoern2.i18n;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FilenameUtils;

import com.github.bjoern2.i18n.transformers.PropertiesToXls;
import com.github.bjoern2.i18n.transformers.Transformer;
import com.github.bjoern2.i18n.transformers.XlsToProperties;

public class App {

	public static void main(String[] args) throws ParseException {
		
		Options options = new Options();
		
		OptionBuilder.isRequired();
		OptionBuilder.hasArg();
		OptionBuilder.withArgName("file");
		OptionBuilder.withDescription("Input file");
		options.addOption(OptionBuilder.create("inputFile"));
		
		OptionBuilder.isRequired();
		OptionBuilder.hasArg();
		OptionBuilder.withArgName("file");
		OptionBuilder.withDescription("Output file");
		options.addOption(OptionBuilder.create("outputFile"));
		
//		OptionBuilder.isRequired();
//		OptionBuilder.hasArg();
//		OptionBuilder.withArgName("format");
//		OptionBuilder.withDescription("Transformation (propToXls, xlsToProp");
//		options.addOption(OptionBuilder.create("format"));
		
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = parser.parse(options, args);
		
		String input = cmd.getOptionValue("inputFile");
		String output = cmd.getOptionValue("outputFile");
//		String format = cmd.getOptionValue("format");
		
		String inputExt = FilenameUtils.getExtension(input);
		String outputExt = FilenameUtils.getExtension(output);
		
		List<Transformer> transformers = new ArrayList<Transformer>();
		transformers.add(new PropertiesToXls());
		transformers.add(new XlsToProperties());
		
		for (Transformer t : transformers) {
			if (t.matches(inputExt, outputExt)) {
				System.out.println("Use " + t.getClass().getName());
				try {
					t.execute(input, output);
					System.out.println("Done!");
					System.exit(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Format not supported.");
		System.exit(1);

	}
}
