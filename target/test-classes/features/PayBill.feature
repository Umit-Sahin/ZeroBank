@Regression @PayBillPage
Feature: Pay Bill Page

  Scenario: Get page title and checked messages
    Given the user accesses the Pay Bill page
    Then Account activity page should have the title "Zero - Pay Bills"
    When user completes a successful Pay operation
    Then "The payment was successfully submitted." should be displayed

  Scenario: Get page title and checked messages
    Given the user accesses the Pay Bill page
    Then Account activity page should have the title "Zero - Pay Bills"
    When user tries to make a payment without entering the amount or date
    Then "Please fill out this field." warning message should be displayed

  Scenario: Amount field characters
    Given the user accesses the Pay Bill page
    When user try to write alphabetical or special "characters" into amount
    Then Amount field should not accept alphabetical or special characters


  Scenario: Date field characters
    Given the user accesses the Pay Bill page
    When user try to write alphabetical "characters" into date
    Then Date field should not accept alphabetical characters
      | march    |
      | April,10 |