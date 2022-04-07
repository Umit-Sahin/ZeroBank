package com.zerobank.stepdefinations;

import com.zerobank.pages.AccountActivity;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.Utils;
import com.zerobank.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountActivityStepDefs {

    @Given("the user accesses the Account Activity page")
    public void the_user_accesses_the_Account_Activity_page() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("username"),ConfigurationReader.get("password"));
        AccountActivity accountActivity = new AccountActivity();
        accountActivity.accountActivity.click();
    }

    @Then("Account activity page should have the title {string}")
    public void account_activity_page_should_have_the_title(String pageTitle) {
        AccountActivity accountActivity = new AccountActivity();
        Assert.assertEquals(accountActivity.getPageSubTitle(),pageTitle);

    }

    @Then("Account activity page should have to following {string}")
    public void account_activity_page_should_have_to_following(String dropdownOptions) {
        AccountActivity accountActivity = new AccountActivity();
        Select select=new Select(accountActivity.accountDropbox);
        List<String > actualOptions = Utils.getElementsText(select.getOptions());
        System.out.println("actualOptions = " + actualOptions);
        Assert.assertTrue(actualOptions.contains(dropdownOptions));

    }

    @Then("Account Activity page should have the title {string}")
    public void account_Activity_page_should_have_the_title(String pageTitle) {
        AccountActivity accountActivity = new AccountActivity();
        Assert.assertEquals(accountActivity.getPageSubTitle(),pageTitle);
    }

    @Then("Transactions table must have {string}")
    public void transactions_table_must_have(String transactionColumns) {
        AccountActivity accountActivity = new AccountActivity();

        List<String > actualColumnNames = Utils.getElementsText(accountActivity.transactionColumns);
        System.out.println("actualColumnNames = " + actualColumnNames);
        Assert.assertTrue(actualColumnNames.contains(transactionColumns));
    }


}
