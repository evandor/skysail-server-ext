Meta:
@tag domain:validations

Narrative:

In order to organize notes, 
As a user 
I want to be able to change them.

Scenario: Providing title with leading and trailing whitespace

Given the user wants to change a note
When the user submits the form with the title ' title '
Then the new note has the title 'title'

Scenario: Providing empty title

Given the user wants to change a note
When the user submits the note with the foldername
Then the request is not successful

