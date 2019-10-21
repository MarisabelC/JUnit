package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ValidateISBNTest {

	@Test
	public void checkValid10DigitISBN() {
		ValidateISBN validator= new ValidateISBN();
		boolean result = validator.checkISBN("0140441492");
		assertTrue("first value",result);	
		result= validator.checkISBN("1989201385");
		assertTrue("second value",result);	
	}
	@Test
	public void CheckValid13DigitISBN(){
		ValidateISBN validator= new ValidateISBN();
		boolean result = validator.checkISBN("9780120000302");
		assertTrue("first value",result);	
		result = validator.checkISBN("9780486290737");
		assertTrue("secod value",result);	
	}
	
	@Test
	public void TenDigitISBNNumbersEndingInAnXIsValid() {
		ValidateISBN validator= new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue(result);
	}
	
	@Test
	public void checkAnInvalid10DigitISBN() {
		ValidateISBN validator= new ValidateISBN();
		boolean result = validator.checkISBN("1926041413");
		assertFalse(result);	
	}
	
	@Test
	public void checkAnInvalid13DigitISBN() {
		ValidateISBN validator= new ValidateISBN();
		boolean result = validator.checkISBN("9780120000306");
		assertFalse(result);
	}
	@Test
	void nineDigitISBNAreNotAllowed() {
		Assertions.assertThrows(NumberFormatException.class, () -> {
			ValidateISBN validator= new ValidateISBN();
			validator.checkISBN("123456789");
		});
	}
	
	@Test 
	public void nonNumericISBNAreNotAllowed() {
		Assertions.assertThrows(NumberFormatException.class, () -> {
			ValidateISBN validator= new ValidateISBN();
			validator.checkISBN("HelloWorld");
		});
	}

}
