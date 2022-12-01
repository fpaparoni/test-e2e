Feature: System registration

Scenario: No fields
Given the registration form
When I enter no fields
And I submit the form to register
Then I receive an error related to "First Name" field required
And I receive an error related to "Last Name" field required
And I receive an error related to "Username" field required
And I receive an error related to "Password" field required

Scenario: Only "First name" field filled
Given the registration form
When I enter "Federico" as "First Name" field
And I submit the form to register
Then I receive an error related to "Last Name" field required
And I receive an error related to "Username" field required
And I receive an error related to "Password" field required

Scenario: Only "Last name" field filled
Given the registration form
When I enter "Paparoni" as "Last Name" field
And I submit the form to register
Then I receive an error related to "First Name" field required
And I receive an error related to "Username" field required
And I receive an error related to "Password" field required

Scenario: Only "Username" field filled
Given the registration form
When I enter "fpaparoni" as "Username" field
And I submit the form to register
Then I receive an error related to "First Name" field required
And I receive an error related to "Last Name" field required
And I receive an error related to "Password" field required

Scenario: Registration completed
Given the registration form
When I enter "fpaparoni" as "Username" field
And I enter "Paparoni" as "Last Name" field
And I enter "Federico" as "First Name" field
And I enter "troot123" as "Password" field
And I submit the form to register
Then I receive no error
