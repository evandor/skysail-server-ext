Meta:
@tag domain:security

Narrative:

In order to organize notes, 
As a user 
I want to be able to change them.

Scenario: Providing title containing javascript for update

Given the user Izzy is logged in
When the user submits the form with the title  xss<script>alert("hi")</script> and the content someContent
Then the request is not successful

