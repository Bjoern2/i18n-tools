package com.github.bjoern2.i18n;

import java.util.Comparator;
import java.util.Locale;

public class LocaleComparator implements Comparator<Locale> {

	@Override
	public int compare(Locale o1, Locale o2) {
		if (o1 == null) {
			return -1;
		}
		
		if (o2 == null) {
			return +1;
		}
		
		return o1.toString().compareTo(o2.toString());
	}

}
