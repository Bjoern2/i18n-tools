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

import java.util.Comparator;

public class PropertiesFileComparator implements Comparator<PropertiesFile> {

	public int compare(PropertiesFile o1, PropertiesFile o2) {
		if (o1 == null) {
			return -1;
		}
		
		if (o2 == null) {
			return +1;
		}
		
		if (o1.getLocale() == null) {
			return -1;
		}
		
		if (o2.getLocale() == null) {
			return +1;
		}
		
		return o1.getLocale().toString().compareTo(o2.getLocale().toString());
	}

}
