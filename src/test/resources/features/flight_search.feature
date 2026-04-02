Feature: Flight Search

  Scenario: Validate flight search results
    Given user is on flight search page
    When user searches flights from "DEL" to "BLR"
    Then flights should be displayed
    Then validate flight data with database
    Then validate flight data with api and database