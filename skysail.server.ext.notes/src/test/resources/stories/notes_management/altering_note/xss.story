Meta:
@tag domain:security

Narrative:

In order to organize notes, 
As a user 
I want to be able to change them.

Scenario: Providing title containing javascript for update

Given the user wants to change a note
When the user submits the form with the title ' xss<script>alert("hi")</script> '
Then the new note has the name 'xss'
