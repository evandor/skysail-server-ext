Meta:
@tag domain:validations

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to be able to change.

Scenario: Providing foldername with leading and trailing whitespace

Given the user Izzy is logged in
When the user opens the existing folder foldername 
And the user submits the form with the foldername  foldername2 
Then the new folder should have the name foldername2

Scenario: Providing empty foldername

Given the user Linus is logged in
When the user opens the existing folder foldername 
And the user submits the form without foldername
Then the request is not successful

