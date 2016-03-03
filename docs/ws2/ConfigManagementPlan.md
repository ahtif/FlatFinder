# Configuration Management Plan for Group 6

## Source control
This group will use branches for large coding changes, all other changes will be pushed directly into master.

We don't feel that branching for documents or for small coding changes is necessary but do feel that it should be used when new features are added to the source code.

## Code Reviews
This group will perform targeted code reviews at specific times.

Similar to above, we don't feel it is necessary for us to perform a code review on every commit / pull request. However we will perform code reviews when people add new features to the existing code.

## Coding Standards
This group will apply Googles coding standard to the repository. We all agreed that Google coding standard is suitable since its very easy to implement and the code will be easily readable because of style checks. 

We have all agreed to use Google's checkstyle as it will ensure the code is of high quality and is easier to understand amongst the group. This also ensures there is consistency in the code of our project.

## Continous Integration 
We are using Travis to continously test our code against the Gherkin features. This means that the code which is pushed to GitHub meets the client requirement. Travis will run the style checks against the code once its pushed to GitHub. We are using Gradle to handle imports and making sure every machine can run the software. 

