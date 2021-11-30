package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiTest //create class
{
    static WebDriver driver;

    public static void clickOnElement(By by)// create click method .this method will execute when fire click event
    {
        driver.findElement(by).click();
    }
    public static void typeText(By by,String text)//  create type text method for type text as input in text box
    {
        driver.findElement(by).sendKeys(text);
    }
    public static String getTextFromElement(By by)//get text method for get data
    {
        return driver.findElement(by).getText();
    }
    public static String currentTimeStamp() //current time stamp method
    {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyhhmmss");
        return sdf.format(date);
    }
    public static void waitForClickabInSEle(By by,int timeInSeconds)//create method wait for clickable
    {
        WebDriverWait wait=new WebDriverWait(driver,timeInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(by));

    }
    public static void waitForVisible(By by,int timeInSeconds)// create method wait for visible
    {
        WebDriverWait wait=new WebDriverWait(driver,timeInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(by));

    }

    @BeforeMethod
    public void openBrowser()// create method for open browser
    {
        System.out.println(currentTimeStamp());
        System.setProperty("webdriver.chrome.driver","src/test/Drivers/chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");

    }
    @AfterMethod
    public void closeBrowser()//create method for close browser
    {
       driver.close();
    }




    @Test

    public static void verifyUserShouldBeAbleToRegisterSuccessfully()
    {


        //click on register button

        clickOnElement(By.xpath("//a[@href='/register?returnUrl=%2F']"));

        // type name and last name
        typeText((By.xpath("//input[@id='FirstName']")),"Krishna");

        typeText((By.xpath("//input[@name='LastName']")),"Sanghani");

        //select day from drop down

        Select selectday=new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectday.selectByVisibleText("15");

        //select month from dropdown

        Select selectMonth=new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectMonth.selectByValue("4");

        Select selectYear=new Select(driver.findElement(By.name("DateOfBirthYear")));
        selectYear.selectByIndex(5);


        // type mail in text box
        String email="krishna.sanghani0+"+currentTimeStamp()+"@gmail.com";
        System.out.println(email);
        typeText(By.name("Email"),email);
        //driver.findElement(By.name("Email")).sendKeys(email);

        // click on newsletter checkbox
        waitForVisible(By.id("Newsletter"),20);
        clickOnElement(By.id("Newsletter"));

        //set password and confirm password in text box
        typeText(By.id("Password"),"test12345678");
        typeText(By.id("ConfirmPassword"),"test12345678");

        //click on register button
        waitForClickabInSEle(By.name("register-button"),10);
        clickOnElement(By.name("register-button"));

        //check registration successfully or not
        String actualRegisterSuccessMessage=getTextFromElement(By.xpath("//div[@class='result']"));
        String expectedRegisterSuccessMessage="Your registration completed";

        Assert.assertTrue(actualRegisterSuccessMessage.equals(expectedRegisterSuccessMessage),"Register success message is incorrect");



    }
    @Test
    public void verifyRegisterUserShouldBeAbleToReferProduct()
    {

        //click on register button

        clickOnElement(By.xpath("//a[@href='/register?returnUrl=%2F']"));

        // type name and last name
        typeText((By.xpath("//input[@id='FirstName']")),"Krishna");

        typeText((By.xpath("//input[@name='LastName']")),"Sanghani");

        //select day from drop down

        Select selectday=new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectday.selectByVisibleText("15");

        //select month from dropdown

        Select selectMonth=new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectMonth.selectByValue("4");

        Select selectYear=new Select(driver.findElement(By.name("DateOfBirthYear")));
        selectYear.selectByIndex(5);


        // type mail in text box
        String email="krishna.sanghani0+"+currentTimeStamp()+"@gmail.com";
        System.out.println(email);
        driver.findElement(By.name("Email")).sendKeys(email);

        // click on newsletter checkbox
        waitForVisible(By.id("Newsletter"),20);
        clickOnElement(By.id("Newsletter"));

        //set password and confirm password in text box
        typeText(By.id("Password"),"test12345678");
        typeText(By.id("ConfirmPassword"),"test12345678");

        //click on register button
        waitForClickabInSEle(By.name("register-button"),10);
        clickOnElement(By.name("register-button"));

        //click on computers category
        clickOnElement(By.xpath("//a[@href='/computers']"));

        //click on desktop category
        clickOnElement(By.xpath("//div/a[@title='Show products in category Desktops']"));

        //click on build computer
        clickOnElement(By.xpath("//a[@title='Show details for Build your own computer']"));

        //click on email a friend
        clickOnElement(By.xpath("//button[@class='button-2 email-a-friend-button']"));

        //type a friend email
        String email1="sunny.m.kathiriya+"+currentTimeStamp()+"@gmail.com";
        typeText(By.xpath("//input[@id='FriendEmail']"),email1);

        //type own email


        //type comment on text box
        typeText(By.xpath("//textarea[@id='PersonalMessage']"),"Nice Product");

        //click on send email
        clickOnElement(By.xpath("//button[@name='send-email']"));

        //print message
       String sendEmailSuccessMessage= getTextFromElement(By.xpath("//div[@class='result']"));
        System.out.println(sendEmailSuccessMessage);

    }
    @Test
    public void verifyUserVisibleToNavigateDesktopPage()
    {
        //click on computers category
        clickOnElement(By.xpath("//a[@href='/computers']"));
        //click on desktop
        clickOnElement(By.xpath("//div/a[@title='Show products in category Desktops']"));

        //print text of the desktop title
        String actual_s1=getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        System.out.println(actual_s1);
        String expected_s2="Desktops";
        Assert.assertTrue(actual_s1.equals(expected_s2),"Not on Desktop page");

    }
    @Test
    public void verifyUserShouldBeAbleToNewsCommentSuccessfullyAdd()
    {
        //click on news details
        clickOnElement(By.xpath("//div[@class='buttons']/a[@href='/nopcommerce-new-release']"));

        //type name of user who add comment
        typeText(By.xpath("//input[@class='enter-comment-title']"),"krishna");

        //type comment in comment box
        typeText(By.xpath("//div[@class='form-fields']/div[2]/textarea"),"Nice Product");

        //click on new comment button for add comment
        clickOnElement(By.xpath("//div[@class='buttons']/button"));

        //print message successfully added comment
       String s2= getTextFromElement(By.xpath("//div[@class='notifications']/div"));
        System.out.println(s2);

    }


}
