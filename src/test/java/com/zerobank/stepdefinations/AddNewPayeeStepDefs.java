package com.zerobank.stepdefinations;

import com.zerobank.pages.LoginPage;
import com.zerobank.pages.NewPayeePage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import java.util.Map;

public class AddNewPayeeStepDefs {

    @Given("Add New Payee tab")
    public void add_New_Payee_tab() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("username"),ConfigurationReader.get("password"));
        NewPayeePage payeePage=new NewPayeePage();
        payeePage.payBills.click();
        payeePage.addNewPayeeLink.click();
    }

    @Given("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String,String> payeeInfo) {
        NewPayeePage payeePage=new NewPayeePage();
        payeePage.payeeNameInputbox.sendKeys(payeeInfo.get("Payee Name"));
        payeePage.payeeAddressInputbox.sendKeys(payeeInfo.get("Payee Address"));
        payeePage.payeeAccountInputbox.sendKeys(payeeInfo.get("Account"));
        payeePage.payeeDetailsInputbox.sendKeys(payeeInfo.get("Payee details"));
        payeePage.addButton.click();
    }

    @Then("message The new payee The Law Offices of Hyde, Price & Scharks was successfully created. should be displayed")
    public void message_The_new_payee_The_Law_Offices_of_Hyde_Price_Scharks_was_successfully_created_should_be_displayed() {
        NewPayeePage payeePage=new NewPayeePage();
        BrowserUtils.waitForVisibility(payeePage.alertContent,3);
        Assert.assertTrue(payeePage.alertContent.isDisplayed());
    }


}
