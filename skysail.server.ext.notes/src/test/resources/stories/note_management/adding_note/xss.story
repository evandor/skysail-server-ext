Meta:
@tag domain:security

Narrative:

In order to manage notes,
As a user
I want to create a new note.

Scenario: Providing title containing javascript

Given the user wants to add a new Note
When the user submits the form with the title ' xss<script>alert("hi")</script> '
Then the new Folder has the name 'xss'
