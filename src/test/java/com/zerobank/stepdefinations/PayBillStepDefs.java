package com.zerobank.stepdefinations;

import com.zerobank.pages.AccountActivity;
import com.zerobank.pages.LoginPage;
import com.zerobank.pages.PayBillPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

public class PayBillStepDefs {

    @Given("the user accesses the Pay Bill page")
    public void the_user_accesses_the_Pay_Bill_page() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("username"),ConfigurationReader.get("password"));
        AccountActivity accountActivity = new AccountActivity();
        accountActivity.payBills.click();
    }

    @When("user completes a successful Pay operation")
    public void user_completes_a_successful_Pay_operation() {
        PayBillPage payBillPage=new PayBillPage();
        payBillPage.payBillAmountInputbox.sendKeys("1000");
        payBillPage.payBillDateInputbox.sendKeys("2022-02-28");
        payBillPage.payBillPayButton.click();
    }

    @Then("{string} should be displayed")
    public void should_be_displayed(String message) {
        PayBillPage payBillPage=new PayBillPage();
        Assert.assertEquals(message,payBillPage.successfullyMessage.getText());
    }

    @When("user tries to make a payment without entering the amount or date")
    public void user_tries_to_make_a_payment_without_entering_the_amount_or_date() {
        PayBillPage payBillPage=new PayBillPage();
        payBillPage.payBillPayButton.click();

    }

    @Then("{string} warning message should be displayed")
    public void warning_message_should_be_displayed(String message) {
        PayBillPage payBillPage=new PayBillPage();
        payBillPage.payBillPayButton.click();
//        BrowserUtils.waitForVisibility(payBillPage.fillOutMessage,3);
//        Assert.assertEquals(message,payBillPage.fillOutMessage.getAttribute("alertContent"));
        Assert.assertTrue(payBillPage.fillOutMessage.isEnabled());

    }

    @When("user try to write alphabetical or special {string} into amount")
    public void user_try_to_write_alphabetical_or_special_characters_into_amount(String characters) {
        PayBillPage payBillPage=new PayBillPage();
        System.out.println("characters = " + characters);
        payBillPage.payBillAmountInputbox.sendKeys(characters);
    }

    @Then("Amount field should not accept alphabetical or special characters")
    public void Amount_field_should_not_accept_alphabetical_or_special_characters() {
        PayBillPage payBillPage=new PayBillPage();
        Assert.assertFalse(payBillPage.payBillAmountInputbox.getText().isEmpty());
    }

    @When("user try to write alphabetical {string} into date")
    public void user_try_to_write_alphabetical_into_date(String str) {
        PayBillPage payBillPage=new PayBillPage();
        payBillPage.payBillDateInputbox.sendKeys(str);
    }

    @Then("Date field should not accept alphabetical characters")
    public void date_field_should_not_accept_alphabetical_characters(List<String> data) {
        PayBillPage payBillPage=new PayBillPage();
        Assert.assertTrue(payBillPage.payBillDateInputbox.getText().isEmpty());
    }



}
