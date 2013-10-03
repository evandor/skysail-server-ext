Meta:
@tag domain:security

Narrative:

In order to manage notes,
As a user
I want to create a new note.

Scenario: Providing title containing javascript

Given the user Izzy is logged in
When the user submits the form with the title ' xss<script>alert("hi")</script> ' and the content 'mycontent'
Then the request is not successful
