package com.zerobank.stepdefinations;

import com.zerobank.pages.AccountActivity;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.Utils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.text.ParseException;

public class FindTransactionsStepDefs {

    @Given("the user accesses the Find Transactions tab")
    public void the_user_accesses_the_Find_Transactions_tab() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("username"),ConfigurationReader.get("password"));
        AccountActivity accountActivity= new AccountActivity();
        accountActivity.accountActivity.click();
        accountActivity.findTransactions.click();
    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String fromDate, String toDate) throws InterruptedException {
        AccountActivity accountActivity= new AccountActivity();
//        String fromDate= int1+"-"+int2+"-"+int3;
//        String toDate = int4+"-"+int5+"-"+int6;
        System.out.println("fromDate = " + fromDate);
        System.out.println("toDate = " + toDate);
        accountActivity.fromDatesInbox.sendKeys( fromDate+ Keys.ENTER);
        accountActivity.toDatesInbox.sendKeys( toDate+ Keys.ENTER);
        Thread.sleep(3000);
    }

    @When("clicks search")
    public void clicks_search() {
        new AccountActivity().findButton.click();
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String fromDate, String toDate) throws ParseException {
        Utils.waitForVisibility(Driver.get().findElement(By.id("filtered_transactions_for_account")), 2);
        AccountActivity accountActivity=new AccountActivity();
        Assert.assertTrue(accountActivity.verifyDateInRange2(fromDate, toDate));

    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {
        AccountActivity accountActivity=new AccountActivity();
        Assert.assertTrue(accountActivity.verifyDateInOrder());
    }

    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String date) {
        AccountActivity accountActivity=new AccountActivity();
        Assert.assertTrue(accountActivity.isDateLineEmpty(date));
    }

    @When("the user enters description {string}")
    public void the_user_enters_description_ONLINE(String str) {
        AccountActivity accountActivity=new AccountActivity();
        accountActivity.descriptionInputBox.clear();
        accountActivity.descriptionInputBox.sendKeys(str+Keys.ENTER);
        Utils.waitFor(2);
    }


    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing_OFFICE(String str) {
        AccountActivity accountActivity=new AccountActivity();
        Assert.assertTrue(accountActivity.isDescriptionContain(str));

    }

    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing_OFFICE(String str) {
        AccountActivity accountActivity=new AccountActivity();
        Assert.assertEquals(accountActivity.searchResults.size(),0);
    }


    @Then("results table should show at least one result under Deposit")
    public void results_table_should_show_at_least_one_result_under_Deposit() {
        AccountActivity accountActivity = new AccountActivity();
        Assert.assertTrue("Deposit cell is empty", accountActivity.isThereAnyDepositLine());
    }

    @Then("results table should show at least one result under Withdrawal")
    public void results_table_should_show_at_least_one_result_under_Withdrawal() {
        AccountActivity accountActivity = new AccountActivity();
        Assert.assertTrue("Withdrawal cell is empty", accountActivity.isThereAnyWithdrawalLine());
    }

    @When("user selects type {string}")
    public void user_selects_type_Deposit(String str) {
        AccountActivity accountActivity = new AccountActivity();
        accountActivity.selectType(str);
        accountActivity.findButton.click();
        Utils.waitFor(2);
    }

    @Then("results table should show no result under Withdrawal")
    public void results_table_should_show_no_result_under_Withdrawal() {
        AccountActivity accountActivity = new AccountActivity();
        Assert.assertFalse("Withdrawal cell is not empty", accountActivity.isThereAnyWithdrawalLine());
    }


    @Then("results table should show no result under Deposit")
    public void results_table_should_show_no_result_under_Deposit() {
        AccountActivity accountActivity = new AccountActivity();
        Assert.assertFalse("Deposit cell is not empty", accountActivity.isThereAnyDepositLine());
    }

}
