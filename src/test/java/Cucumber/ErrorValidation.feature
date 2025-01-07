@tag
Feature: Validate that error message is displayed on entering incorrect credentials


  @ErrorValidation
  Scenario Outline:Negative Test to verify login credentials
    Given I landed on Ecommerce Page
    When Logged in with usernname <name> and Password <password>
    Then "Incorrect email or password." massage is displayed

    Examples:
      | name            | password |
      | Mithi@gmail.com | Miland@1 |
