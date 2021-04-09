# simplecalendar
A simple calendar web app to manage rooms and appointments within this room.

## Demo

Find a running demo [here](https://immense-retreat-76466.herokuapp.com/index.html "Demo")

# Project Setup
Find informations about the setup here.

## Frontend
The frontend is being developed with vue.js. The calendar functionalities are provided by the vue library of FullCalendar.
The code for the frontend is hosted in a private repository at the moment.

## Backend
The backend is realized as simple web service based on Spring Boot with a Derby Db. The dependencies are listed below:
-  'org.springframework.boot:spring-boot-starter-data-jpa'
-  'org.springframework.boot:spring-boot-starter-web'
-  'com.google.code.gson:gson:2.8.6'
- 'org.springframework.boot:spring-boot-starter-test'
- 'org.apache.derby:derby'



### Controllers
All rest-endpoints are prefixed with '/api/'.


@PostMapping("api/createRoom")
Sample body:
`{"roomId":2,"name":"Werkstatt"}`

@GetMapping(path="api/allRooms")
Retrieves something like:

`"[{\"roomId\":1,\"name\":\"Seminarraum 1\"},{\"roomId\":201,\"name\":\"Werkstatt\"}]"`
@PostMapping("api/createAppointment")
Sample body:

`{
"roomId":1,
"startTime":"2021-02-18T12:17:20.945Z",
"endTime":"2021-02-18T15:17:20.945Z"
}`


@GetMapping("api/allAppointments")
Retrieves something like:

`[
{"id": 2,"room": {"roomId": 1,"name": "Seminarraum 1"},"startTime": "Nov 30, 2016, 12:17:20 PM","endTime": "Nov 30, 2017, 12:17:20 PM"},
{"id": 101,"room": {"roomId": 1,"name": "Seminarraum 1"},"startTime": "Feb 28, 2021, 12:17:20 PM","endTime": "Feb 28, 2021, 12:17:20 PM"}}
]`

# Development

## nginx

To ease out port problems during development on localhost, the folder 'nginx' contains a docker compose file. The compose file instantiates a reverse proxy, which ensures mapping requests coming from the frontend development instance to the appropriate backend instance.

## Backend development with IntelliJ
1. Clone the repository
2. Open with IntelliJ, confirm the dialogue to import the gradle script and execute the build task
3. Project Settings (Ctrl+Shift+Alt+S): Choose the default SDK for the project (Java 11, where ever it lives on your machine)

4. Set up the run configurations:
    1. Add configuration
    2. Add new configuration (Application) with the following settings:
      - module: java 11
      - Module Classpath: simplecalendar.main
      - Main class: io.tobias.simplecalendar.SimplecalendarApplication
