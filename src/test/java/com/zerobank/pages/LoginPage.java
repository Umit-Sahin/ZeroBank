package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id="signin_button")
    public WebElement signIn;

    @FindBy(id="details-button")
    public WebElement advanceButton;

    @FindBy(id="proceed-link")
    public WebElement proceedLink;

    @FindBy(id="user_login")
    public WebElement loginInputBox;

    @FindBy(id="user_password")
    public WebElement passwordInputBox;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement submit;




    public void login(String userNameStr, String passwordStr) {
        Driver.get().get(ConfigurationReader.get("url"));
        BrowserUtils.waitForClickablility(signIn,3);
        signIn.click();

        loginInputBox.sendKeys(userNameStr);
        passwordInputBox.sendKeys(passwordStr);
        submit.click();

        Driver.get().navigate().back();
        WebElement onlineBanking = Driver.get().findElement(By.id("onlineBankingMenu"));
        onlineBanking.click();

        WebElement accountSummaryLink = Driver.get().findElement(By.id("account_summary_link"));
        accountSummaryLink.click();

//        Driver.get().get("http://zero.webappsecurity.com/bank/account-summary.html");

//        BrowserUtils.waitForClickablility(advanceButton,2);
//        advanceButton.click();
//        proceedLink.click();
    }
}
