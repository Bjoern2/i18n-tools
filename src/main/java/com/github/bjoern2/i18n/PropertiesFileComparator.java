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
