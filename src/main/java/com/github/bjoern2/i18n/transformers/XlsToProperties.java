package com.github.bjoern2.i18n.transformers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.github.bjoern2.i18n.PropertiesFile;
import com.github.bjoern2.i18n.PropertiesFileUtils;

public class XlsToProperties implements Transformer {

	@Override
	public void execute(String inputFile, String outputFile) throws IOException {
		
		final String dir = FilenameUtils.getFullPath(outputFile);
		final String baseName = FilenameUtils.getBaseName(outputFile);
		
		FileInputStream in = new FileInputStream(inputFile);
		Workbook wb = new HSSFWorkbook(in);
		
		Sheet sheet = wb.getSheetAt(0);
		Row rowHeader = sheet.getRow(0);
		//Cell cell00 = rowHeader.getCell(0);
		
		final List<PropertiesFile> propFiles = new ArrayList<PropertiesFile>();
		
		Iterator<Cell> itCellHeader = rowHeader.cellIterator();
		itCellHeader.next(); // Skip the 1st column.
		
		while (itCellHeader.hasNext()) {
			Cell c = itCellHeader.next();
			String strLocale = c.getStringCellValue();
			Locale locale = strLocale.equalsIgnoreCase("default") ? null : LocaleUtils.toLocale(strLocale);
			
			PropertiesFile f = new PropertiesFile();
			f.setLocale(locale);
			f.setBaseName(baseName);
			f.setProperties(new Properties());
			propFiles.add(f);
		}
		
		Iterator<Row> itRows = sheet.iterator();
		itRows.next();
		
		
		while (itRows.hasNext()) {
			final Row row = itRows.next();
			final Iterator<Cell> itCell = row.iterator();
			final Cell cellKey = itCell.next(); // Key column
			final String key = cellKey.getStringCellValue();
			
			int i = 0;
			while (itCell.hasNext()) {
				Cell cell = itCell.next();
				
				PropertiesFile f = propFiles.get(i);
				f.getProperties().setProperty(key, cell.getStringCellValue());
				i++;
			}
		}
		
		for (PropertiesFile f : propFiles) {
			final String filename = dir + baseName + (f.getLocale() == null ? "" : "_" + f.getLocale().toString()) + ".properties";
			
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(new File(filename)));
				final List<String> keys = PropertiesFileUtils.toSortedKeyList(f.getProperties().keySet());
				for (String key : keys) {
					final String escKey = StringEscapeUtils.escapeJava(key);
					final String escValue = StringEscapeUtils.escapeJava(f.getProperties().getProperty(escKey));
					writer.write(escKey + "=" + escValue);
					writer.newLine();
					
				}
				System.out.println(filename + " created.");
			} finally {
				IOUtils.closeQuietly(writer);
			}
		}
	}

	@Override
	public boolean matches(String inputExt, String outputExt) {
		return StringUtils.equalsIgnoreCase(inputExt, "xls") && StringUtils.endsWithIgnoreCase(outputExt, "properties");
	}
	
}
