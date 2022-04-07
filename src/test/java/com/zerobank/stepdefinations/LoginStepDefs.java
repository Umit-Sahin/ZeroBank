package com.zerobank.stepdefinations;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.Utils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class LoginStepDefs {

    @Given("the user accesses the Log in page")
    public void the_user_accesses_the_Log_in_page() {
        Driver.get().get(ConfigurationReader.get("url"));
        Utils.waitFor(2);
        new LoginPage().signIn.click();
    }
    @When("the user logged in as valid {string} {string}")
    public void the_user_logged_in_as_valid(String username, String password) {
        LoginPage loginPage=new LoginPage();
        loginPage.loginInputBox.sendKeys(username);
        loginPage.passwordInputBox.sendKeys(password);
        loginPage.submit.click();
        Driver.get().navigate().back();
        WebElement onlineBanking = Driver.get().findElement(By.id("onlineBankingMenu"));
        onlineBanking.click();

        WebElement accountSummaryLink = Driver.get().findElement(By.id("account_summary_link"));
        accountSummaryLink.click();
    }

    @When("the user logged in as invalid {string} {string}")
    public void the_user_logged_in_as_invalid(String username, String password) {
        LoginPage loginPage=new LoginPage();
        loginPage.loginInputBox.sendKeys(username);
        loginPage.passwordInputBox.sendKeys(password);
        loginPage.submit.click();

    }

    @Then("Account summary page should be displayed")
    public void account_summary_page_should_be_displayed() {
        String expectedTitle    = "Zero - Account Summary";
        String actualTitle      = Driver.get().getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }

    @Then("error message {string} should be displayed")
    public void error_message_should_be_displayed(String errorMessage) {
        String actualTitle      = Driver.get().findElement(By.xpath("//div[@class='alert alert-error']")).getText();
        Assert.assertEquals(errorMessage, actualTitle);
    }

}
