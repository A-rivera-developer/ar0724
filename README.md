# Checkout Service

### Description

This project is a checkout service that allows the user to rent tools from a store.

It uses an in memory H2 database with basic information on tools and how they are charged depending on the day of the week.


### Assumptions

1. The due date was calculated by adding the number of rental days to the checkout date.
2. It is assumed the scenario where an entire checkout is free is expected.
3. It is designed with the expectation of more holidays being added but the solution does not intend to overengineer at this point since there were only 2 holiday cases.
4. Exception handling is in a very basic state as to not overengineer the solution.
5. The "Rental Agreement" response is grouping the fields on their relationship as to make easier separating "chunks" of related information.
6. Excluding dto, model, and entity classes, this code should have 95% to 100% test coverage. 

### Tests

- The project was developed through TDD with junit and mockito and also includes basic integration tests.
- The "IntegrationTests" class reads from a request.json file and performs a checkout. It then compares the result with an expected response.json file.
  - The requests files are the provided base examples for the project.

### Posting checkouts and expected responses


The format for a checkout is the following:
```
{
    "toolCode": {
      "type": "string",
      "description": "A tool code representing a tool to be rented."
    },
    "dayCount": {
      "type": "integer",
      "description": "The amount of days to be rented."
    },
    "discountPercent": {
      "description": "The percentage to be discounted off the final price.",
      "type": "integer",
      "minimum": 0,
      "maximum": 100
    },
    "checkoutDate": {
      "description": "The date the rental is checked out.",
      "type": "date",
      "format" "M/d/yy"
      }
    }
    
```
Example:
```
{
  "toolCode": "JAKR",
  "dayCount": 4,
  "discountPercent": 50,
  "checkoutDate": "7/2/20"
}
```

Response:
```
{
  "toolInfo": {
    "code": "JAKR",
    "type": "Jackhammer",
    "brand": "Ridgid"
  },
  "dateInfo": {
    "rentalDays": 4,
    "checkoutDate": "7/2/20",
    "dueDate": "7/6/20"
  },
  "chargeInfo": {
    "chargeDays": 1,
    "dailyRentalCharge": 2.99,
    "preDiscountCharge": 2.99,
    "discountPercent": 50.0,
    "discountAmount": 1.50,
    "finalCharge": 1.50
  }
}
```