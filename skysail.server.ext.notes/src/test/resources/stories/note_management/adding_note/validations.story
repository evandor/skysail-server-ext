Meta:
@tag domain:validations

Narrative:

In order to manage notes,
As a user
I want to create a new note.

Scenario: Providing title with leading and trailing whitespace

Given the user wants to add a new note
When the user submits the form with the title ' title '
Then the new note has the title 'title'

Scenario: Providing empty title

Given the user wants to add a new note
When the user submits the form with the title
Then the request is not successful

