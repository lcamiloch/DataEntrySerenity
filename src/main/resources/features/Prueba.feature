Feature: Search to product in mercadolibre
  I want search to produt in the mercadolibre web

  Scenario Outline: Free product shipping nationwide
    Given <name> could enter the mercadolibre page
    When select a "<product>" to buy
    Then verify that the shipping is "<send>"

    Examples: