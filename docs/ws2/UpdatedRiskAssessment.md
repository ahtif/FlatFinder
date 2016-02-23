# 1 Risk Identification

## 1.1 Definition

A risk is defined as an event or condition that could impact the ability of the project to meet it's objectives.

## 1.2 Categories

The potential risk categories that could impact the project are:

* Requirements
* Time management
* Quality
* Issues
* Communication
* Resource

## 1.3 Risks

| Category | Description | Id  |
| ----------- | -------------- | --- |
| Requirements | <ul><li>Requirements aren't clearly defined</li><li>Requirements don't meet the user's needs</li></ul> | 1.1 <br /> 1.2|
| Time management |  <ul><li>Developers underestimate the time needed to code the project</li><li>Not provided sufficient time to complete the project</li></ul> | 2.1 <br /> 2.2 |
| Quality | <ul><li>The final product does not meet the needs of the user</li><li>Client is dissatisfied with the final product</li></ul> | 3.1 <br /> 3.2 |
| Issues | <ul><li>Issues that arise are not dealt with in an appropriate time period</li><li>Team member gets ill and cannot work</li></ul> |  4.1 <br /> 4.2|
| Communication | <ul><li>Lack of communication between developers causes issues</li><li>Lack of communication between developers and supervisor hinders progress</li></ul> | 5.1 <br /> 5.2 |
| Resource | <ul><li>Developers may not have the required skill set to carry out tasks</li></ul> | 6.1 | 

# 2 Risk Quantification

## 2.1 Likelihood

| Title        | Score           | Description  |
| ----------- | -------------- | --------------- |
| Very Low | 20 | Highly unlikely to occur; however, still needs to be monitored as certain circumstances could result in this risk becoming more likely to occur during the project |
| Low | 40 | Unlikely to occur, based on current information, as the circumstances likely to trigger the risk are also unlikely to occur ||
| Medium | 60 | Likely to occur as it is clear that the risk will probably eventuate |
| High | 80 | Very likely to occur, based on the circumstances of the project |
| Very High | 100 | Highly likely to occur as the circumstances which will cause this risk to eventuate are also very likely to be created |

## 2.2 Impact

| Title        | Score           | Description  |
| ----------- | -------------- | --------------- |
| Very Low | 20 | Insignificant impact on the project. It is not possible to measure the impact on the project as it is minimal |
| Low | 40 | Minor impact on the project ||
| Medium | 60 | Measurable impact on the project |
| High | 80 | Significant impact on the project |
| Very High | 100 | Major impact on the project |

## 2.3 Priority

Priority = (Likelihood + Impact) / 2

| Id            | Likelihood          | Impact          | Priority Score | Rating     | 
| ----------- | -------------------- | --------------- | ----------------  | ----------  |
| 1.1           | 20                       | 80                 | 50                    | Medium |
| 1.2           | 20                       | 80                 | 50                    | Medium |
| 2.1           | 60                       | 100               | 80                    | High |
| 2.2           | 40                       | 80                 | 60                    | Medium |
| 3.1           | 40                       | 100                | 70                    | High |
| 3.2           | 40                       | 100                | 70                    | High |
| 4.1           | 40                       | 60                 | 50                    | Medium |
| 4.2           | 20                       | 80                 | 50                    |Medium|
| 5.1           | 60                       | 80                | 70                    | High |
| 5.2           | 20                       | 40                | 30                    | Low |
| 6.1           | 60                       | 100               | 80                   | High |

The Rating is based on the calculated Priority score. Use the following system to determine the Rating:

| Priority Score | Priority Rating |
| ----------------- | ----------------- |
| 0-20                | Very low          |
| 21 - 40           | Low                 |
| 41-60              | Medium          |
| 61-80              | High                |
| 81-100            | Very high        |

# Risk Plan

## 3.1 
| Rating | ID | Preventative Actions| Action Resource | Action Date| Contingent Actions| Action Resource| Action Date|
|---------|-----|-------------------------|---------------------|----------------|-----------------------|-----------------------|----------|
|High| 6.1 | Set aside time to learn the framework that will be used for the product | Developer| n/a | Change the roles of the developers to suit their skills|Project Manager| n/a|
|High|2.1| Set internal deadlines for specific aspects of the project | Project Manager| n/a| Ensure deadlines are stuck to| Developer| n/a|
|High|3.1| Create acceptance criteria for each requirement and ensure the criteria pass | Tester| n/a| Go back and redevelop areas that do not pass| Developer| n/a|
|High|3.2| Have the client sign off on the requirements | Business Analyst| n/a| Discuss issues with clients and rectify the issues| Business Analyst| n/a|
|High|5.1| Organise regular meetings and ensure that everyone attends | Project Manager| n/a| Notify supervisor on developers absence | Project Manager| n/a|
|Medium|2.2| Prioritise the key tasks in order to achieve the best quality product within the given time frame | Developer| n/a| Produce the best quality product that you can within the time frame| Developer| n/a|
|Medium|1.1| Organise a meeting with clients to redraft unclear requirements | Business Analyst| 2/2/16| Create product that meets the requirements in the developers mind | Developer| n/a|
|Medium|1.2| Create a clear set of requirements that the client can sign off on | Developer| n/a| Get an outside focus group to give feedback on how well it meets their needs| Business Analyst| n/a|
|Medium|4.1| Create a clear timeline for fixing issues and make sure the group sticks to it | Project Analyst| n/a| Fix issues in the agreed time frame| Developer| n/a|
|Medium|4.2| n/a | n/a | n/a | Re Allocate the persons tasks among the other members of the group| Project Manager| n/a|  
|Low|5.2| Organise regular meetings with supervisor and ensure all the group attends | Project Manager| n/a| Carry out the project as best we can| Project Manager| n/a|

# 4 Risk Process

## 4.1 Purpose

The purpose of risk management is to take into consideration any risks that may arise throughout the project and think of ways in which to prevent risks and how to deal with any that occur. The group discussed potential risks and then came up with a plan for rectifying any risks that may take place. 



