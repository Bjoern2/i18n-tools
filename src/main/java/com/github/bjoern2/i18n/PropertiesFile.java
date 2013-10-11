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
import java.util.Locale;
import java.util.Properties;

public class PropertiesFile {

	private File file;
	private String baseName;
	private Locale locale;
	private Properties properties;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	// public static PropertiesFile create(Path path) throws IOException {
	// PropertiesFile f = new PropertiesFile();
	// f.setPath(path);
	//
	// FileReader reader = null;
	// try {
	// reader = new FileReader(path.toFile());
	// Properties props = new Properties();
	// props.load(reader);
	// f.setProperties(props);
	// } finally {
	// if (reader != null) {
	// try {
	// reader.close();
	// } catch (Exception ex) {
	//
	// }
	// }
	// }
	//
	// final String name = path.toString();
	// int start = name.lastIndexOf("/");
	// start = name.indexOf("_", start);
	// int end = name.lastIndexOf(".properties");
	//
	// if ((start > 0) && (end > 0)) {
	// String code = name.substring(start, end);
	// Locale locale = new Locale(code);
	// f.setLocale(locale);
	//
	// }
	//
	// return f;
	// }

}
