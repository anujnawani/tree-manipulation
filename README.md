# Manage and Manipulate Trees

### Assumptions
* Only one root node
* Single connected Tree
* Root node's parent is null
* First root node needs to be created, passing parentId as null
* Insert a node along with parent, parent must exist in the system
* First node created through POST api is assigned an id of 1
* Following node creation is assigned auto-increment id increase by 1

### Project Set up
* Clone the repository
* Go to the project folder i.e. interviews-services_anujnawani/java_project
* Build the project - gradle clean build
* The above command should generate a jar file inside folder interviews-services_anujnawani\java_project - build\libs\demo-0.0.1-SNAPSHOT.jar
* Run the command - docker build -t tree-app .
* Run the command - docker run -p 3001:3001 tree-app
* Run the following POST commands - http://localhost:3001/api/tree
```
POST
http://localhost:3001/api/tree
Request Body:
{
    "parentId": null,
    "label": "a"
}
```
```
POST
http://localhost:3001/api/tree
Request Body:
{
    "parentId": 1,
    "label": "b"
}
```
```
POST
http://localhost:3001/api/tree
Request Body:
{
    "parentId": 2,
    "label": "c"
}
```
* Run the get command - 
```
GET
http://localhost:3001/api/tree
```

### Usage
* [SimpleIdGenerator.java](src%2Fmain%2Fjava%2Fcom%2Fcompany1%2Ftreestructure%2Futil%2FSimpleIdGenerator.java) generates a node Id starting with 1
* [AdjacencyMap.java](src%2Fmain%2Fjava%2Fcom%2Fcompany1%2Ftreestructure%2Futil%2FAdjacencyMap.java) is used to represent relationship between node and its parent
* [SimpleTreeParser.java](src%2Fmain%2Fjava%2Fcom%2Fcompany1%2Ftreestructure%2Futil%2FSimpleTreeParser.java) parses the Adjacency Map to form JSON Tree with root id as 1