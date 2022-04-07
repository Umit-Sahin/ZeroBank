package com.zerobank.stepdefinations;

import com.zerobank.pages.AccountActivity;
import com.zerobank.pages.AccountSummary;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.Utils;
import com.zerobank.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class AccountActivityNavigationStepDefs {


    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("username"),ConfigurationReader.get("password"));
    }

    @When("the user clicks on Savings link on the Account Summary page")
    public void the_user_clicks_on_Savings_link_on_the_Account_Summary_page() {
        Utils.waitFor(2);
        AccountSummary accountSummary = new AccountSummary();
        accountSummary.chooseAccount("Savings");
    }

    @Then("the Account Activity page should be displayed")
    public void the_Account_Activity_page_should_be_displayed() {
        AccountActivity accountActivity= new AccountActivity();
        Assert.assertEquals("Zero - Account Activity",accountActivity.getPageSubTitle());
    }

    @Then("Account drop down should have Savings selected")
    public void account_drop_down_should_have_Savings_selected() {
        AccountActivity accountActivity= new AccountActivity();
        Select accountOptions = new Select(accountActivity.accountDropbox);
        String actualAccount = accountOptions.getFirstSelectedOption().getText();
        Assert.assertEquals(actualAccount,"Savings");
    }

    @When("the user clicks on Brokerage link on the Account Summary page")
    public void the_user_clicks_on_Brokerage_link_on_the_Account_Summary_page() {
        Utils.waitFor(2);
        AccountSummary accountSummary = new AccountSummary();
        accountSummary.chooseAccount("Brokerage");
        Utils.waitFor(3);
    }

    @Then("Account drop down should have Brokerage selected")
    public void account_drop_down_should_have_Brokerage_selected() {
        AccountActivity accountActivity= new AccountActivity();
        Select accountOptions = new Select(accountActivity.accountDropbox);
        String actualAccount = accountOptions.getFirstSelectedOption().getText();
        Assert.assertEquals("Brokerage",actualAccount);
    }

    @When("the user clicks on Checking link on the Account Summary page")
    public void the_user_clicks_on_Checking_link_on_the_Account_Summary_page() {
        Utils.waitFor(2);
        AccountSummary accountSummary = new AccountSummary();
        accountSummary.chooseAccount("Checking");
    }

    @Then("Account drop down should have Checking selected")
    public void account_drop_down_should_have_Checking_selected() {
        AccountActivity accountActivity= new AccountActivity();
        Select accountOptions = new Select(accountActivity.accountDropbox);
        String actualAccount = accountOptions.getFirstSelectedOption().getText();
        Assert.assertEquals("Checking",actualAccount);
    }

    @When("the user clicks on Credit card link on the Account Summary page")
    public void the_user_clicks_on_Credit_card_link_on_the_Account_Summary_page() {
        AccountSummary accountSummary = new AccountSummary();
        accountSummary.chooseAccount("Credit Card");
    }

    @Then("Account drop down should have Credit Card selected")
    public void account_drop_down_should_have_Credit_Card_selected() {
        AccountActivity accountActivity= new AccountActivity();
        Select accountOptions = new Select(accountActivity.accountDropbox);
        String actualAccount = accountOptions.getFirstSelectedOption().getText();
        Assert.assertEquals("Credit Card",actualAccount);
    }

    @When("the user clicks on Loan link on the Account Summary page")
    public void the_user_clicks_on_Loan_link_on_the_Account_Summary_page() {
        AccountSummary accountSummary = new AccountSummary();
        accountSummary.chooseAccount("Loan");
    }

    @Then("Account drop down should have Loan selected")
    public void account_drop_down_should_have_Loan_selected() {
        AccountActivity accountActivity= new AccountActivity();
        Select accountOptions = new Select(accountActivity.accountDropbox);
        String actualAccount = accountOptions.getFirstSelectedOption().getText();
        Assert.assertEquals("Loan",actualAccount);
    }

}
