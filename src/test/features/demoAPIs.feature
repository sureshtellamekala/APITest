Feature: DEMO APIS

  Scenario: Get the Employee details
    Given an endpoint "/api/v1/employees"
    When I make a GET request to the API
    Then I should receive HTTP status code 200

   Scenario: Get employee details by ID
     Given an endpoint "/api/v1/employee"
     When I add path params to the URL
       | id | 1 |
     When I make a GET request to the API
     Then I should receive HTTP status code 200
     And the response field will contain the value
       | data.id | 1 |
       | data.employee_name | Tiger Nixon |
       | data.employee_salary | 320800 |
       | data.employee_age | 61 |

     Scenario: Delete Employee detail
       Given an endpoint "/api/v1/delete"
       When I add path params to the URL
         | id | 2 |
       When I make a DELETE request to the API
       Then I should receive HTTP status code 200
       And the response field will contain the value
         | message | Successfully! Record has been deleted |






