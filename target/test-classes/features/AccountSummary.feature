@Regression @AccountSummaryPage
Feature: Account Summary Page

  Scenario Outline: Get page title, account types and credit accounts columns
    Given the user accesses the Account Summary page
    Then Account summary page should have the title "Zero - Account Summary"
    Then Account summary page should have to following "<account types>"

    Examples:
      | account types       |
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |


  Scenario Outline: Get page title, account types and credit accounts columns
    Given the user accesses the Account Summary page
    Then Account summary page should have the title "Zero - Account Summary"
    Then Credit Accounts table must have "<columns>"
    Examples:
      | columns     |
      | Account     |
      | Credit Card |
      | Balance     |
