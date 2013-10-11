package com.github.bjoern2.i18n.transformers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.github.bjoern2.i18n.PropertiesFile;
import com.github.bjoern2.i18n.PropertiesFileComparator;
import com.github.bjoern2.i18n.PropertiesFileUtils;

public class PropertiesToXls implements Transformer {

	@Override
	public void execute(String inputFile, String outputFile) throws IOException {
		final List<PropertiesFile> inputPropFiles = PropertiesFileUtils.findFiles(inputFile);
		Collections.sort(inputPropFiles, new PropertiesFileComparator());
		
		final List<String> keys = new ArrayList<String>();
		for (PropertiesFile f : inputPropFiles) {
			Properties props = f.getProperties();
			for (Object key : props.keySet()) {
				if (!keys.contains(key.toString())) {
					keys.add(key.toString());
				}
			}
		}
		Collections.sort(keys);
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet1 = wb.createSheet();
		
		// Header:
		Row rowHeader = sheet1.createRow(0);
		rowHeader.createCell(0).setCellValue("Key");
		
		for (int i = 0; i < inputPropFiles.size(); i++) {
			final Locale locale = inputPropFiles.get(i).getLocale();
			rowHeader.createCell(i + 1).setCellValue(locale == null ? "Default" : locale.toString());
		}
		
		// Data:
		for (int i = 0; i < keys.size(); i++) {
			final String key = keys.get(i);
			Row r = sheet1.createRow(i + 1);
			r.createCell(0).setCellValue(key);
			
			for (int j = 0; j < inputPropFiles.size(); j++) {
				r.createCell(j + 1).setCellValue(inputPropFiles.get(j).getProperties().getProperty(key, ""));
			}
		}
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outputFile);
			wb.write(out);
			System.out.println(outputFile + " created.");
		} finally {
			IOUtils.closeQuietly(out);
		}
		
	}

	@Override
	public boolean matches(String inputExt, String outputExt) {
		return StringUtils.equalsIgnoreCase(inputExt, "properties") && StringUtils.endsWithIgnoreCase(outputExt, "xls");
	}
	
}
