Feature: Validate login functionality
	
	@ui
  Scenario: Successful Login with Valid entries
		Given User navigates to the website "https://fego.vmos-demo.com" 
		When User navigates to login screen
		And Set email as "itsanirbanroy@gmail.com"
		And Set password as "abcd1234"
		Then Login must be successful
		
	@ui
	Scenario Outline: Login with Invalid entries
		Given User navigates to the website "https://fego.vmos-demo.com" 
		When User navigates to login screen
		And Set email as "<email>"
		And Set password as "<password>"
		Then Login must not be successful with "<errorField>" and "<message>"
		
		Examples: 
      |	email										| password	|	errorField	|	message					|
      |	abc@test.com						|						|	password		|	Required field.	|
 			|													|	dsdsff		|	email				|	Required field.	|
      |													|						|	both				|	Required field.	|
      |	abctest.com							|	gwgggg		|	email				|	Invalid email.	|
      |	abc@test.com						|	gwgggg		|	error				|	User not found	|
      |	itsanirbanroy@gmail.com	|	gwgggg		|	error				|	Wrong password	|