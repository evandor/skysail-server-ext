Meta:
@tag domain:validations

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to create.

Scenario: Providing foldername with leading and trailing whitespace

Given the user Linus is logged in
When the user submits the form with the foldername  foldername 
Then the new folder should have the name foldername

Scenario: Providing empty foldername

Given the user Izzy is logged in
When the user submits the form without foldername 
Then the request is not successful

