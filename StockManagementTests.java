package com.virtualpairprogrammers.isbntools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import static org.mockito.Mockito.*;

public class StockManagementTests {
	
	ExternalISBNDataService testWebService;
	StockManager stockManager;
	ExternalISBNDataService testDatabaseService;
	
	@Before
	public void setup() {
		testWebService= mock(ExternalISBNDataService.class);
		testDatabaseService= mock(ExternalISBNDataService.class);
		stockManager= new StockManager();
		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(testDatabaseService);
	}

	@Test
	public void testCanGetACorrectLocatorCode()  {
	
		when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice and Men", "J. Steinbeck" ));
		when(testDatabaseService.lookup(anyString())).thenReturn(null);
				
		String isbn= "0140177396";	
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	@Test
	public void databaseIsUsedIfDataisPreset() {
		
		when (testDatabaseService.lookup("0140177396"))
			.thenReturn(new Book("0140177396", "abc", "abc"));
		String isbn= "0140177396";
		stockManager.getLocatorCode(isbn);
		
		verify(testDatabaseService).lookup("0140177396");
		verify(testWebService, never()).lookup(anyString());
	
	}
	
	@Test
	public void webServiceIsUsedIfDataisNotPresetInDatabase() {
				
		when (testDatabaseService.lookup("0140177396")).thenReturn(null);
		when (testWebService.lookup("0140177396"))
			.thenReturn(new Book("0140177396", "abc", "abc"));
		String isbn= "0140177396";
		stockManager.getLocatorCode(isbn);
		
		verify(testDatabaseService).lookup("0140177396");
		verify(testWebService).lookup("0140177396");
	}
	
}
