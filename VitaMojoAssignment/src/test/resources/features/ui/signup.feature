Feature: Validate Signup functionality
	@ui
  Scenario: Successful Signup with Valid entries
		Given User navigates to the website "https://fego.vmos-demo.com" 
		When User navigates to signup screen
		And Set name as "name"
		And Set valid email as "test@test.com"
		And Set password as "password1"
		Then Signup must be successful
		
	@ui
	Scenario Outline: Signup with Invalid entries
		Given User navigates to the website "https://fego.vmos-demo.com" 
		When User navigates to signup screen
		And Set name as "<name>"
		And Set email as "<email>"
		And Set password as "<password>"
		Then Signup must not be successful with "<field>" "<message>"

    Examples: 
      | name  |	email					| password	|	field		| message				|
      | John	|	abctest.com		|	dsdsff		|	email		|	Invalid email.|
      | John	|	abc@test.com	|						|	password| Password is required. This field should contain 8 characters.|
      | John	|								|	gwgggg		|	email		|	Required field.|
      |				| abc@test.com	|	abcd			| name 		| Please add your name |
      | John 	|	abc@test.com	| abcd 			| passowrd| This field should contain 8 characters.|
      
       