Feature: Assignment


  @Verify @AddCart
  Scenario: Verify page title
    Then verify the title of the page

  @AddCart
  Scenario Outline: Adding to cart
    Given user types smartwathces in search bar
    And user clicks on search icon
    And user selects the "<brand>"
    And user filters "<minPrice>" and "<maxPrice>"
    Then verify that results are in "<minPrice>" and "<maxPrice>" range
    And user sorts them from high to low
    When user opens the product with high price in new window
    Then add product to cart
    And user closes the new window

    Examples:
      | brand      | minPrice | maxPrice |
      | boAt       | 1000     | 5000     |
      | CrossBeats | 2000     | 7000     |


