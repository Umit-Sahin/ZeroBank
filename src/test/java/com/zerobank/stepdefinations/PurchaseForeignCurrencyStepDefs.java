package com.zerobank.stepdefinations;

import com.zerobank.pages.LoginPage;
import com.zerobank.pages.NewPayeePage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.Select;


import java.util.List;

public class PurchaseForeignCurrencyStepDefs {

    @Given("the user accesses the Purchase foreign currency cash tab")
    public void the_user_accesses_the_Purchase_foreign_currency_cash_tab() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("username"),ConfigurationReader.get("password"));
        NewPayeePage payeePage=new NewPayeePage();
        payeePage.payBills.click();
        payeePage.purchaseForeignCurrencyLink.click();
    }

    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> currency) {
        NewPayeePage payeePage=new NewPayeePage();
        BrowserUtils.waitFor(2);

        List<String > actualCurrencies = BrowserUtils.getElementsText(payeePage.currencyOptions);
        System.out.println("currency.size() = " + currency.size());
        System.out.println("actualCurrencies.size() = " + actualCurrencies.size());
        Assert.assertTrue(actualCurrencies.containsAll(currency));


/*        Select selectCurrency = new Select(payeePage.currencyDropbox);
        for (int i=1; i< currency.size() ; i++) {
            selectCurrency.selectByIndex(i);
            BrowserUtils.waitFor(2);

        }*/

    }

    @When("user tries to calculate cost without selecting a currency")
    public void user_tries_to_calculate_cost_without_selecting_a_currency() {
        NewPayeePage payeePage=new NewPayeePage();
        payeePage.amountInputbox.sendKeys( "100");
        payeePage.calculateCostButton.click();

        Alert alert=  Driver.get().switchTo().alert();
        System.out.println("alert.getText()-1 = " + alert.getText());
        alert.accept();
    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        NewPayeePage payeePage=new NewPayeePage();
        payeePage.calculateCostButton.click();
        Alert alert = Driver.get().switchTo().alert();

        System.out.println("alert.getText()-2 = " + alert.getText());
        Assert.assertEquals("Please, ensure that you have filled all the" +
              " required fields with valid values.",alert.getText());
        alert.accept();
    }

    @When("user tries to calculate cost without entering a value")
    public void user_tries_to_calculate_cost_without_entering_a_value() {
        NewPayeePage payeePage=new NewPayeePage();
        Select selectCurrency = new Select(payeePage.currencyDropbox);
        payeePage.selectCurrencyRadioButton.click();
        payeePage.calculateCostButton.click();
        Alert alert=  Driver.get().switchTo().alert();
        System.out.println("alert.getText()-3 = " + alert.getText());

        Assert.assertEquals("Please, ensure that you have filled all the" +
              " required fields with valid values.",alert.getText());
        alert.accept();
    }


}
