Feature: Validate signup functionality using Rest Api
  @api    
  Scenario: Successful Sign Up with valid data
		Given Valid endpoint with payload to signup user
		When Sign up with "Name", "test@test.com" and "Password"
		Then User successfully signs up
		
	@api
	Scenario Outline: Sign Up fails for invalid data
		Given Valid endpoint with payload to signup user
		When Sign up with "<name>", "<email>" and "<password>"
		Then Sign up fails
		
		Examples: 
      | name  |	email					| password	|	field		| message				|
      | John	|	abctest.com		|	dsdsff		|	email		|	Invalid email.|
      | John	|	abc@test.com	|						|	password| Password is required. This field should contain 8 characters.|
      | John	|								|	gwgggg		|	email		|	Required field.|
      |				| abc@test.com	|	abcd			| name 		| Please add your name |
      | John 	|	abc@test.com	| abcd 			| passowrd| This field should contain 8 characters.|
	
