@Regression @AccountActivityPage
Feature: Account Activity Page

  Scenario Outline: Get page title and account drop down
    Given the user accesses the Account Activity page
    Then Account activity page should have the title "Zero - Account Activity"
    Then Account activity page should have to following "<account types>"
    Examples:
      | account types |
      | Savings       |
      | Checking      |
      | Loan          |
      | Credit Card   |
      | Brokerage     |

  Scenario Outline: Get page title and credit accounts columns
    Given the user accesses the Account Activity page
    Then Account Activity page should have the title "Zero - Account Activity"
    Then Transactions table must have "<TransactionColumns>"
    Examples:
      | TransactionColumns |
      | Date               |
      | Description        |
      | Deposit            |
      | Withdrawal         |