User should be able to create Issue of type
:Story,Feature,Bugs,Task
Each will have status such as Triaged, In-progress, Validate,Resolved,Closed
Stories can also have subtasks -> only type: task

User should be able to create sprints.And can add any task to sprint or remove them

User should be able to Get Notification for delayed task, -- > notifying 

User should be able to see-tasks assign to them
In different formats-By ID,By Story Points etc.
Sprint Details.


IssueType: 4

Status : 5

Entities
User-> Id Name Email List<long> taskIds
Sprint -> Id, Name , List<Issues> 
Issue -> long Id , Description , title , IssueType , Status , List<Issue> taskList, User , long sprintId, int StoryPoints

Feature-> Story -> Tasks/Bugs


Repo -> List<Issues> 6 entries
List<Users> 
List<Sprints>
