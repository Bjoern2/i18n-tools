package com.github.bjoern2.i18n;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertiesFileUtilsTest.
 */
public class PropertiesFileUtilsTest {

	/**
	 * Test find locale1.
	 */
	@Test
	public void testFindLocale1() {
		Locale locale = PropertiesFileUtils.findLocale("Test_de_DE.properties");
		Assert.assertEquals(Locale.GERMANY, locale);
	}
	
	/**
	 * Test find locale2.
	 */
	@Test
	public void testFindLocale2() {
		Locale locale = PropertiesFileUtils.findLocale("Lorem_ipsum_dolor_de.properties");
		Assert.assertEquals(Locale.GERMAN, locale);
	}
	
	/**
	 * Test find locale3.
	 */
	@Test
	public void testFindLocale3() {
		Locale locale = PropertiesFileUtils.findLocale("ABC_de_fg_HI_zh_CN.properties");
		Assert.assertEquals(Locale.CHINA, locale);
	}
	
	/**
	 * Test find locale4.
	 */
	@Test
	public void testFindLocale4() {
		Locale locale = PropertiesFileUtils.findLocale("I18N.properties");
		Assert.assertNull(locale);
	}
	
	/**
	 * Test find basename1.
	 */
	@Test
	public void testFindBasename1() {
		String baseName = PropertiesFileUtils.findBasename("Test_de_DE.properties");
		Assert.assertEquals("Test", baseName);
	}
	
	/**
	 * Test find basename2.
	 */
	@Test
	public void testFindBasename2() {
		String baseName = PropertiesFileUtils.findBasename("Lorem_ipsum_dolor_de.properties");
		Assert.assertEquals("Lorem_ipsum_dolor", baseName);
	}
	
	/**
	 * Test find basename3.
	 */
	@Test
	public void testFindBasename3() {
		String baseName = PropertiesFileUtils.findBasename("ABC_de_fg_HI_zh_CN.properties");
		Assert.assertEquals("ABC_de_fg_HI", baseName);
	}
	
	/**
	 * Test find basename4.
	 */
	@Test
	public void testFindBasename4() {
		String baseName = PropertiesFileUtils.findBasename("I18N.properties");
		Assert.assertEquals("I18N", baseName);
	}
	
}
