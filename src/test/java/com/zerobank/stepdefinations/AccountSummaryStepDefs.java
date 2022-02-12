package com.zerobank.stepdefinations;

import com.zerobank.pages.AccountSummary;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;


import java.util.List;

public class AccountSummaryStepDefs {

    @Given("the user accesses the Account Summary page")
    public void the_user_accesses_the_page() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("username"),ConfigurationReader.get("password"));
        AccountSummary accountSummary= new AccountSummary();
        accountSummary.accountSummary.click();
    }

    @Then("Account summary page should have the title {string}")
    public void account_summary_page_should_have_the_title(String pageTitle) {
        AccountSummary accountSummary= new AccountSummary();
        Assert.assertEquals(accountSummary.getPageSubTitle(),pageTitle);
    }

    @Then("Account summary page should have to following {string}")
    public void account_summary_page_should_have_to_following(String accountTypes) {
        AccountSummary accountSummary= new AccountSummary();
        List<String > actualAccounts = BrowserUtils.getElementsText(accountSummary.headerAccounts);
        System.out.println("actualAccounts = " + actualAccounts);
        Assert.assertTrue(actualAccounts.contains(accountTypes));

    }

    @Then("Credit Accounts table must have {string}")
    public void credit_Accounts_table_must_have(String columns) {
        AccountSummary accountSummary= new AccountSummary();
        List<String> actualColumns = BrowserUtils.getElementsText(accountSummary.creditAccounts);
        Assert.assertTrue(actualColumns.contains(columns));

    }

}
