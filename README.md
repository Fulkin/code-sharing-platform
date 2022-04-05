# Code Sharing Platform

* An application that allows users to create, share, and access code on the web. Code snippets are stored in the database and deleted if they have a time and/or views restriction that is met.
  The project is a RESTful API and WebInterface and is an implementation of the [Code Sharing Platform](https://hyperskill.org/projects/130) project from [hyperskill.org](https://www.jetbrains.com/ru-ru/academy/)

## About

Api endpoints are the following:
1. `POST /api/code/new`: upload a new code snippet given a JSON object request body that contains the code snippet, time restriction (optional), and views restriction (optional).
   ```json
   {
      "code" : "code_snippet",
      "time" : "100",
      "views" : "99"
   }
   ```
   Returns the generated unique id of the code snippet:
   ```json
   {
      "id": "38cd7699-1380-40f6-93ce-b30016f5a48c"
   }
   ```

2. `GET /api/code/{id}` return JSON of the code snippet given its id:
    ```json
    {
        "code": "code_snippet",
        "date": "2022-04-05 11:59:46",
        "time": 55,
        "views": 98
    }
    ```
3. `GET /api/code/latest` return the JSON array of the 10 most recently uploaded code snippets, ordered from newest to oldest, that have no view and/or time limit :
    
Web endpoints are the following:
1. `/code/new` return html page,that contains a text area for users to add a new code snippet, along with inputs for time and views restriction (optional):
    
![Alt text](images/new.png?raw=true "Create code")
2. `/code/latest` : return a dynamic page of the 10 most recently loaded code snippets, ordered from newest to oldest, that have no view and/or time limits:

![Alt text](images/latest.png?raw=true "Create code")
3. `/api/code/{id}` return HTML that contains the code snippet (and restrictions, if applicable) given its id:

![Alt text](images/code_snipped.png?raw=true "Create code")