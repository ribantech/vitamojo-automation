Feature: Validate login functionality using Rest Api
@api
Scenario: Successful API Login with valid entries

	Given Valid endpoint with payload to login user
	When Login with "itsanirbanroy@gmail.com" and "abcd1234"
	Then User successfully logs in
	
@api
Scenario: API Login fails with invalid entries

	Given Valid endpoint with payload to login user
	When Login with "Username" and "Password"
	Then User login fails

	