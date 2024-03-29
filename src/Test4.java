import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class Test4 {
	
	private WebDriver driver;
	private String baseUrl;
	boolean display;
	String path = "";
	boolean displayed;
	boolean PopupDisplayed;
	boolean EVUser2;
	boolean VUser2;
	boolean EVUser1;
	boolean VUser1;
	String ItemID;
	String PageTitle;
	String[] CreatedValue = new String[4];
	String[] ReadValue = new String[4];
	
	public void Login(String UN, String PW)
	{
	driver.get(baseUrl + "/Login.html#");
			driver.findElement(By.name("j_username")).clear();
			driver.findElement(By.name("j_username")).sendKeys(UN);
			driver.findElement(By.name("j_password")).clear();
			driver.findElement(By.name("j_password")).sendKeys(PW);
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			driver.findElement(By.id("gwt-debug-nav-link-myitems"));
	}		
	
	
	public void CreateItem()
	{
				driver.findElement(By.id("gwt-debug-nav-compose-new")).click();
				driver.findElement(By.id("gwt-debug-nav-compose-new-item")).click();
				
				driver.findElement(By.id("gwt-debug-itemform-actionbar-button-saveandclose"));
				assertEquals(driver.getTitle(), "eParade - New Item");
				
				//Validate that the "Share" button is "disabled"
				assertFalse(driver.findElement(By.id("gwt-debug-itemform-actionbar-button-share")).isSelected());
				
				//Validate that the status list is "disabled"
				assertFalse(driver.findElement(By.xpath("//*[@id='gwt-debug-itemform-form']/tbody/tr[3]/td/div/table/tbody/tr/td[1]/table/tbody/tr/td[2]/div/div[2]/select")).isSelected());
						
				//Validate that Status drop down has "Draft" selected
				assertTrue(driver.findElement(By.xpath("//*[@id='gwt-debug-itemform-form']/tbody/tr[3]/td/div/table/tbody/tr/td[1]/table/tbody/tr/td[2]/div/div[2]/select/option[3]")).isSelected());
				CreatedValue[1] = "Draft";
				
				//Set the values for "Type", "Resolution" and "Title"
				new Select(driver.findElement(By.xpath("//table[@id='gwt-debug-itemform-form']/tbody/tr[4]/td/div/table/tbody/tr/td/table/tbody/tr/td[2]/div/div[2]/select"))).selectByVisibleText("Ongoing");
				driver.findElement(By.cssSelector("input.gwt-TextBox")).sendKeys("Dialog.1 Test");
				CreatedValue[2] = "Dialog.1 Test";
				driver.findElement(By.xpath("//*[@id='gwt-debug-itemform-form']/tbody/tr[2]/td/div/table/tbody/tr/td[1]/table/tbody/tr/td[2]/div/div[2]/table/tbody/tr/td[1]/table/tbody/tr[2]/td/select/option[2]")).click();
				String SelectedType = driver.findElement(By.xpath("//*[@id='gwt-debug-itemform-form']/tbody/tr[2]/td/div/table/tbody/tr/td[1]/table/tbody/tr/td[2]/div/div[2]/table/tbody/tr/td[1]/table/tbody/tr[2]/td/select/option[2]")).getText();
				CreatedValue[0] = SelectedType;
				driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
				
				//Write "Test" in description
				driver.findElement(By.id("gwt-uid-32_ifr")).click();
				driver.findElement(By.id("gwt-uid-32_ifr")).sendKeys("Test");
				CreatedValue[3] = "Test";
				
				//Save and Back
				driver.findElement(By.id("gwt-debug-itemform-actionbar-button-saveandclose")).click();
				driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/tbody[1]/tr[1]/td[1]/div"));
				
	}
	
	public void SortandReadFirstRowMyItems()
	{
			driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/thead/tr/th[5]/div/div[2]")).click();
			driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/tbody[1]/tr[1]/td[1]/div"));
				
			driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/thead/tr/th[5]/div/div[2]")).click();
			driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/tbody[1]/tr[1]/td[1]/div"));
		
			//Click on the first row
			driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/tbody[1]/tr[1]/td[1]/div")).click();
		
			//Read the values of the first row
			ReadValue[0] = driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/tbody[1]/tr[1]/td[1]/div")).getText();
			ReadValue[1] = driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/tbody[1]/tr[1]/td[3]/div")).getText();
			ReadValue[2] = driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/tbody[1]/tr[1]/td[4]/div")).getText();
		
	}
	
	
	public void ShareSettings(int counter, boolean EU2, boolean EU1)
	{
			driver.findElement(By.cssSelector("button.gwt-Button.GDLH5ARCMY")).click();
			driver.findElement(By.id("gwt-debug-itemform-actionbar-button-saveandclose"));
		
			ItemID = driver.findElement(By.cssSelector("div.gwt-Label.GDLH5ARCEK")).getText();
		
			//Validate that Share button is available
			displayed = driver.findElement(By.id("gwt-debug-itemform-actionbar-button-share")).isEnabled();
			//assertTrue(displayed);
		
			//Click on the "Share" button
			driver.findElement(By.id("gwt-debug-itemform-actionbar-button-share")).click();
		
			//Validate that a popup opens.
			PopupDisplayed = driver.findElement(By.id("gwt-debug-lightbox-container")).isDisplayed();
			assertTrue(PopupDisplayed);
	
			if (counter == 1)
			{
				assertTrue(driver.findElement(By.xpath("//*[@id='gwt-debug-sharing-permission-row-2']/span/input")).isSelected());
				driver.findElement(By.className("gwt-SuggestBox")).click();
				driver.findElement(By.className("gwt-SuggestBox")).sendKeys("testuser1");
				driver.findElement(By.className("gwt-SuggestBox")).sendKeys(Keys.ENTER);
			
				driver.findElement(By.xpath("//*[@id='gwt-debug-lightbox-container']/div[2]/div/div[2]/div/div/table/tbody/tr[3]/td/div/div/div/div[3]/table[1]/tbody/tr/td[5]/div/img")).click();
				driver.findElement(By.xpath("//*[@id='gwt-debug-lightbox-container']/div[2]/div/div[2]/div/div/table/tbody/tr[3]/td/div/div/div/div[3]/table[1]/tbody/tr/td[5]/div/img")).click();
			}
		
			assertTrue(EU1 | EU2);
			atLeastOneEditor();
		
			if (EU2 == true && EU1 == true) 
			{
				driver.findElement(By.xpath("//*[@id='gwt-debug-sharing-permission-row-2']/span/input")).click();
				driver.findElement(By.xpath("//*[@id='gwt-debug-lightbox-container']/div[2]/div/div[2]/div/div/table/tbody/tr[3]/td/div/div/div/div[2]/table/tbody/tr/td[3]/span/input")).click();
			}
		
			else if (EU2 == true && EU1 == false)
			{
				driver.findElement(By.xpath("//*[@id='gwt-debug-sharing-permission-row-2']/span/input")).click();
				driver.findElement(By.xpath("//*[@id='gwt-debug-lightbox-container']/div[2]/div/div[2]/div/div/table/tbody/tr[3]/td/div/div/div/div[2]/table/tbody/tr/td[4]/span/input")).click();
			}
		
			else if (EU2 == false && EU1 == true)
			{
				driver.findElement(By.xpath("//*[@id='gwt-debug-lightbox-container']/div[2]/div/div[2]/div/div/table/tbody/tr[3]/td/div/div/div/div[2]/table/tbody/tr/td[3]/span/input")).click();
				driver.findElement(By.xpath("//*[@id='gwt-debug-sharing-permission-row-3']/span/input")).click();
			}
			
	}
	
	public void atLeastOneEditor()
	{
			driver.findElement(By.xpath("//*[@id='gwt-debug-lightbox-container']/div[2]/div/div[2]/div/div/table/tbody/tr[3]/td/div/div/div/div[1]/table/tbody/tr/td[5]")).click();
			String me = driver.findElement(By.xpath("//*[@id='gwt-debug-lightbox-container']/div[2]/div/div[2]/div/div/table/tbody/tr[3]/td/div/div/div/div[1]/table/tbody/tr/td[2]/div/table/tbody/tr/td[1]/div")).getText();
			
			assertEquals(me, "me");
			
			driver.findElement(By.xpath("//*[@id='gwt-debug-sharing-permission-row-3']/span/input")).click();
			driver.findElement(By.xpath("//*[@id='gwt-debug-lightbox-container']/div[2]/div/div[2]/div/div/table/tbody/tr[3]/td/div/div/div/div[2]/table/tbody/tr/td[4]/span/input")).click();
		
			EVUser2 = driver.findElement(By.xpath("//*[@id='gwt-debug-sharing-permission-row-2']/span/input")).isSelected();
			VUser2 = driver.findElement(By.xpath("//*[@id='gwt-debug-sharing-permission-row-3']/span/input")).isSelected();
		
			EVUser1 = driver.findElement(By.xpath("//*[@id='gwt-debug-lightbox-container']/div[2]/div/div[2]/div/div/table/tbody/tr[3]/td/div/div/div/div[2]/table/tbody/tr/td[3]/span/input")).isSelected();
			VUser1 = driver.findElement(By.xpath("//*[@id='gwt-debug-lightbox-container']/div[2]/div/div[2]/div/div/table/tbody/tr[3]/td/div/div/div/div[2]/table/tbody/tr/td[4]/span/input")).isSelected();
		
			assertFalse(EVUser2 & VUser2);
			assertFalse(EVUser1 & VUser1);
			assertTrue(EVUser2 | VUser2);
			assertTrue(EVUser1 | VUser1);
			assertTrue(EVUser2 | EVUser1);
		
	}
	
	public void NavigateToAllItem()
	{
			//Navigating to All-Items
			driver.findElement(By.id("gwt-debug-nav-link-allitems")).click();
			assertEquals("eParade - All Items", driver.getTitle());
	}
	
	public void ViewItem()
	{
			//Click on the "View" button
			driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
			driver.findElement(By.cssSelector("button.gwt-Button.GDLH5ARCMY"));
				
			//Validate that we are reading the same item
			assertEquals(driver.findElement(By.className("GDLH5ARCEK")).getText(), ItemID);
		
			//Cancel the View page
			driver.findElement(By.cssSelector("a.gwt-Anchor.GDLH5ARCMY")).click();
			driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/thead/tr/th[5]/div/div[2]"));
		
	}
	
	public void EditItemAvailable()
	{
			boolean EditOnAllItem;
			boolean EditOnView;
		
			driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/thead/tr/th[5]/div/div[2]"));
			EditOnAllItem = driver.findElement(By.cssSelector("button.gwt-Button.GDLH5ARCMY")).isEnabled();
		
			driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
			EditOnView = driver.findElement(By.cssSelector("button.gwt-Button.GDLH5ARCMY")).isEnabled();
				 
			//Cancel the View page
			driver.findElement(By.cssSelector("a.gwt-Anchor.GDLH5ARCMY")).click();
		
			assertEquals(EditOnView,EditOnAllItem);
			displayed = EditOnView;
						 
	}
	
	
	@Before
	public void setUp() throws Exception {
			driver = new  FirefoxDriver();
			baseUrl = "http://192.168.215.143/";
			driver.manage().timeouts().implicitlyWait(75, TimeUnit.SECONDS);
	}

	@Test
	public void test() {
			String UserName = "testuser2";
			String PassWord = "nterop_4dmin";
			Login(UserName, PassWord);
			assertEquals(driver.getTitle(), "eParade - My Items");
			CreateItem();
			assertEquals(driver.getTitle(), "eParade - My Items");
			SortandReadFirstRowMyItems();
			for (int i = 0; i < 3; i++ )
			{
				assertEquals(CreatedValue[i], ReadValue[i]);
			}
			ShareSettings(1, true, true);
		
			//Save and Close the Sharing Page
			driver.findElement(By.cssSelector("button.gwt-Button.GDLH5ARCBX")).click();
		
			//Save and Close the Editing Area
			driver.findElement(By.id("gwt-debug-itemform-actionbar-button-saveandclose"));
			driver.findElement(By.id("gwt-debug-itemform-actionbar-button-saveandclose")).click();
			driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/tbody[1]/tr[1]/td[1]/div"));
		
			NavigateToAllItem();
			SortandReadFirstRowMyItems();
		
			ViewItem();
			SortandReadFirstRowMyItems();
			EditItemAvailable();
			assertTrue(displayed);
		
			//Log-out
			driver.findElement(By.linkText("Log Out")).click();
		
			//Login as testUser1
			UserName = "testuser1";
		
			try
			{
				Login(UserName, PassWord);
			}
			catch(UnhandledAlertException e)
			{
				//Do nothing
			}	
		
			
			assertEquals(driver.getTitle(), "eParade - My Items");
			NavigateToAllItem();
			SortandReadFirstRowMyItems();
		
			ViewItem();
			SortandReadFirstRowMyItems();
			EditItemAvailable();
			assertTrue(displayed);
		
			//Change the permission of the item
			UserName = "testuser2";
		
			try
			{
				Login(UserName, PassWord);
			}
			catch(UnhandledAlertException e)
			{
				//Do nothing
			}
			assertEquals(driver.getTitle(), "eParade - My Items");
			SortandReadFirstRowMyItems();
			ShareSettings(2, true, false);

			//Save and Close the Sharing Page
			driver.findElement(By.cssSelector("button.gwt-Button.GDLH5ARCBX")).click();
				
			//Save and Close the Editing Area
			driver.findElement(By.id("gwt-debug-itemform-actionbar-button-saveandclose"));
			driver.findElement(By.id("gwt-debug-itemform-actionbar-button-saveandclose")).click();
			driver.findElement(By.xpath("//*[@id='gwt-debug-browse-table']/tbody[1]/tr[1]/td[1]/div"));
				
			NavigateToAllItem();
			SortandReadFirstRowMyItems();
				
			ViewItem();
			SortandReadFirstRowMyItems();
			EditItemAvailable();
			assertTrue(displayed);
		
			UserName = "testuser1";
		
			try
			{
				Login(UserName, PassWord);
			}
			catch(UnhandledAlertException e)
			{
				//Do nothing
			}
			assertEquals(driver.getTitle(), "eParade - My Items");
			NavigateToAllItem();
			SortandReadFirstRowMyItems();
				
			ViewItem();
			SortandReadFirstRowMyItems();
			EditItemAvailable();
			assertFalse(displayed);
		
			UserName = "testuser2";
			try
			{
				Login(UserName, PassWord);
			}
			catch(UnhandledAlertException e)
			{
				//Do nothing
			}
			assertEquals(driver.getTitle(), "eParade - My Items");
			SortandReadFirstRowMyItems();
			ShareSettings(3, false, true);

			//Save and Close the Sharing Page
			driver.findElement(By.cssSelector("button.gwt-Button.GDLH5ARCBX")).click();
		
			//Save and Close the Editing Area
			driver.findElement(By.id("gwt-debug-itemform-actionbar-button-saveandclose"));
			driver.findElement(By.id("gwt-debug-itemform-actionbar-button-saveandclose")).click();
		
			//The owner doesn't have the "Edit" permission. So "Save" doesn't perform
			PageTitle = "eParade" + " "+ "-" + " " + ItemID;
			assertEquals(PageTitle, driver.getTitle());
				
			//Cancel the page
			driver.findElement(By.id("gwt-debug-itemform-actionbar-link-cancel")).click();
		
			NavigateToAllItem();
			SortandReadFirstRowMyItems();
				
			ViewItem();
			SortandReadFirstRowMyItems();
			EditItemAvailable();
			assertFalse(displayed);
		
			UserName = "testuser1";
		
			try
			{
				Login(UserName, PassWord);
			}
			catch(UnhandledAlertException e)
			{
				//Do nothing
			}
			assertEquals(driver.getTitle(), "eParade - My Items");
			NavigateToAllItem();
			SortandReadFirstRowMyItems();
				
			ViewItem();
			SortandReadFirstRowMyItems();
			EditItemAvailable();
			assertTrue(displayed);
		
		
		
	}

}
