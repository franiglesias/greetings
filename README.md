
# The Greeting Machine

## A microservice for greeting people

---

Let’s create a Spring Boot Application

## Instructions

This exercise was created by mixing the greetings kata with an exercise to learn to use Spring Boot (or any similar MCV framework in other languages). It could take about two or three hours, with a 10-15 minutes break.

The purpose of this exercise is to learn how to start a project using the framework, but it is also interesting for checking how the team approaches projects and how they try (or not) to design a solution for maintainability in the long term, and if they use testing or TDD to guarantee that new features don’t break the existing ones.

We present the exercise in the context of a Spring Boot learning session, so after talking about the basics of it, we can proceed with the exercise to practice.

We tell participants that we will be creating a simple API from scratch using the Spring Initializer (you can use the one on the web or the one integrated with IntelliJ IDEA, I prefer the latter if possible). The basic exercise is the #1 challenge. We should warn participants that we will be asking for more features by pretending we are product owners (simulating a typical product development team situation, so you can consider that we have 10-15 minutes iterations, etc.).

It should be very easy for all the participants to complete the first task (even too easy). So, after a few minutes, when we see all teams are finishing, we present the second challenge.

We do the same with all the other challenges: when most pairs are near to finishing the current task, introduce a new one.

We can stop in the middle of the session to have a brief catch-up with all the teams (10-15 minutes). It’s a good moment for a coffee break.

## The exercise

### Start

#1 Business people have a new product in mind: a Greeting Machine that provides an API to greet people arriving at our landing pages.

The first challenge is to have an endpoint “/hello” that returns:

“Hello, World” if no name is provided
“Hello, Name”, when called with an optional parameter “/hello/name”
Let’s go!

### Twist and shout

#2 Congratulations. The first challenge is done and customers love the greeting feature. But we want to adapt it to different styles. We want to be able to manage customers SHOUTING:

If the name is all uppercase (SHOUTED) we have to answer using all uppercase. Here is an example:

/hello/FRAN —> “HELLO, FRAN”

### Double challenge ;-)

#3. Business is happy with the new “Shouting” feature, so they want to add a new one. Some customers want to be greeted along with their couple, so they want to be able to introduce two names. For example:

Annie, Bart. ---> “Hello, Annie and Bart.”

But…  we want to ensure that the new features don’t break the application in order to deploy it confidently. So we want to introduce tests of the endpoint (MockMvc is your friend for this).

### Bugs, features, or loosely defined requirements?

#4 Customer care is asking us for fixing some problems they detected. Maybe we have some problems here with not very well-defined requirements. After some meetings with business people, we finally have a clear definition:

/hello ---> Hello world.
/hello/Annie ---> Hello, Annie.
/hello/BART ---> HELLO, BART!
/hello/Annie,Bart ---> Hello, Annie and Bart.

-> Pay attention to the final dots or exclamation marks

Use tests to ensure you have implemented the correct behaviors.

### Lots of names

#5 Product owners want that customers can provide an arbitrary quantity of names, so even huge groups can be greeted by the program.

Something like:

/hello/Anne,Bart,Charles,Monique ----> “Hello, Anne, Bart, Charles, and Monique.”

Be aware of the Oxford comma “, and” for the last name.

### Things are getting hard… or not

#6 Seriously. Greeting Machine is a huge success. Customers are delighted, and sales are skyrocketing, But business people want more.

After adding the multiple names feature we lose the ability to manage SHOUTING customers. So, here’s the thing: If normal and SHOUTED names are mixed we want to greet separately:


/hello/Anne,BART,Charles,MONIQUE —-> “r”

Also, one of our developers just arrived from training about software design and is worried about the separation of concerns in our application. New features are starting to be a bit complicated to fulfill, so she is proposing to move the name managing logic to a Service, instead of having it in the Controller, and probably other changes.

To refactor or not to refactor?. That’s the question.

## Retrospective

* Did you finish all challenges? In which one do you stop?
* What was the most difficult thing for your team?
* What did you do to overcome the obstacles?
* Did you introduce tests from the beginning?
* Did you introduce test-driven development at some point?
* What did you learn about design with the exercise?
