Meta:
@tag domain:validations

Narrative:

In order to organize notes, 
As a user 
I want to be able to change them.

Scenario: Providing title with leading and trailing whitespace

Given the user Izzy is logged in
When the user opens the existing note mynote 
And the user submits the form with the title mynote2 and the content contentIn 
Then the new note should have the title mynote2

Scenario: Providing empty title

Given the user Linus is logged in
When the user opens the existing note mynote 
And the user submits the form without title
Then the request is not successful

