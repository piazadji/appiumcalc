Feature: Calc test

  Scenario: E2E test
    Given I see calculator opened
    Then I verify that SUM of '2' and '3' equals to '5'
    And I verify that SUBTRACTION of '10' and '2' equals to '8'
    And I verify that result of SUBTRACTION of '10' and '2' MULTIPLIED by '2' equals to '8'
    And I verify that SIN of '30' is equal to '0.5'
    When I select 'History' item in more menu
    Then I see '4' formulas in History