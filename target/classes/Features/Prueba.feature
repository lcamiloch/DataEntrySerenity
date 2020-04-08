@uno
Feature: Search to product in mercadolibre
  I want search to produt in the mercadolibre web

  @smokeTest
  Scenario Outline: Free product shipping nationwide
    Given <name> could enter the mercadolibre page
    When select a "<product>" to buy
    Then verify that the shipping is "<send>"

    Examples:
|name|product|send|
|Camilo Chaparro|tarjeta debito|150000|
|Diana Camila Ch|tarjeta credito|3555648|
|Juan|Cta Ahorros|550|
|name|product|send|
|Camilo Chaparro|tarjeta debito|150000|
|Diana Camila Ch|tarjeta credito|3555648|
|Juan|Cta Ahorros|550|