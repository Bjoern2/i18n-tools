/*
 * Copyright 2013 Björn Schmitz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.bjoern2.i18n;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.LocaleUtils;

public class PropertiesFileUtils {

	public static List<PropertiesFile> findFiles(String srcFilename) throws IOException {
		String dirName = FilenameUtils.getFullPath(srcFilename);
		
		final String filename = FilenameUtils.getName(srcFilename);
		final String baseName = findBasename(filename);
		
		File dir = new File(dirName);
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name == null) {
					return false;
				}
				return name.startsWith(baseName) && name.endsWith(".properties");
			}
		});
		
		List<PropertiesFile> props = new ArrayList<PropertiesFile>();
		for (File file : files) {
			PropertiesFile f = new PropertiesFile();
			final Locale locale = findLocale(FilenameUtils.getName(file.getName()));
			f.setLocale(locale);
			f.setBaseName(baseName);
			f.setProperties(createProperties(file));
			props.add(f);
			
		}
		return props;
	}
	
	public static Locale findLocale(String filename) {
		String regex = "_[a-z]{2}(_[A-Z]{2}){0,1}\\.";
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher m = pattern.matcher(filename);
		if (m.find()) {
			String l = m.group(0);
			l = l.substring(1, l.length() - 1);
			return LocaleUtils.toLocale(l);
		}
		
		return null;
	}
	
	public static String findBasename(String filename) {
		String regex = "((_[a-z]{2}){0,2})((_[A-Z]{2}){0,1})(\\.properties)";
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher m = pattern.matcher(filename);
		if (m.find()) {
			String name = m.group(0);
			int idx = filename.indexOf(name);
			return filename.substring(0, idx);
		}
		
		return null;
	}
	
	public static Properties createProperties(File file) throws IOException {
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			Properties props = new Properties();
			props.load(reader);
			return props;
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}
	
	public static List<String> toSortedKeyList(Set<Object> keys) {
		List<String> list = new ArrayList<String>();
		for (Object key : keys) {
			list.add(key.toString());
		}
		Collections.sort(list);
		return list;
	}
	
}
