Meta:
@tag domain:validations

Narrative:

In order to manage notes,
As a user
I want to create a new note.

Scenario: Providing title with leading and trailing whitespace

Given the user Linus is logged in
When the user submits the form with the title  mytitle  and the content something 
Then the new note should have the title mytitle

Scenario: Providing empty foldername

Given the user Izzy is logged in
When the user submits the form without title 
Then the request is not successful


