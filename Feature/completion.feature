Feature: Verify users in FanCode city have more than half of their todos completed

  Scenario: Users in FanCode city should have more than 50% todos completed
    Given User has the todo tasks
    And User belongs to the city FanCode
    Then User Completed task percentage should be greater than 50%
