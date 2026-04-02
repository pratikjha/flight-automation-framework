Feature: Flight Search

  @ui @db
  Scenario: Validate flight search results
    Given user is on flight search page
    When user searches flights from "DEL" to "BLR"
    Then flights should be displayed
    Then validate flight data with database

  @api
  Scenario: Validate flight data via API
    Then validate flight data with api only