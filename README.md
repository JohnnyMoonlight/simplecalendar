# simplecalendar
A simple calendar web app to manage rooms and appointments within this room.

## Demo

Find a running demo [here](https://immense-retreat-76466.herokuapp.com/index.html "Demo")

# Project Setup
Find informations about the setup etc here.

## Frontend
The frontend is being developed with vue.js. The calendar functionalities are provided by the vue library of FullCalendar.
The code for the frontend is hosted in a private repository at the moment.

## IntelliJ
1. Clone the repository
2. Open with IntelliJ, confirm the dialogue to import the gradle script and execute the build task
3. Project Settings (Ctrl+Shift+Alt+S): Choose the default SDK for the project (Java 11, where ever it lives on your machine)

4. Set up the run configurations:
    1. Add configuration
    2. Add new configuration (Application) with the following settings:
      - module: java 11
      - Module Classpath: simplecalendar.main
      - Main class: io.tobias.simplecalendar.SimplecalendarApplication


## Application
The application lives on port 8080, so after starting you should be able to find the app in your browser on `localhost:8080/index.html`


## Controllers
The backend controller listen to 

@PostMapping("/createRoom")
Sample body:
`{"roomId":2,"name":"Werkstatt"}`

@GetMapping(path="/allRooms")
Retrieves something like:

`"[{\"roomId\":1,\"name\":\"Seminarraum 1\"},{\"roomId\":201,\"name\":\"Werkstatt\"}]"`
@PostMapping("/createAppointment")
Sample body:

`{
"roomId":1,
"startTime":"2021-02-18T12:17:20.945Z",
"endTime":"2021-02-18T15:17:20.945Z"
}`


@GetMapping("/allAppointments")
Retrieves something like:

`[
{"id": 2,"room": {"roomId": 1,"name": "Seminarraum 1"},"startTime": "Nov 30, 2016, 12:17:20 PM","endTime": "Nov 30, 2017, 12:17:20 PM"},
{"id": 101,"room": {"roomId": 1,"name": "Seminarraum 1"},"startTime": "Feb 28, 2021, 12:17:20 PM","endTime": "Feb 28, 2021, 12:17:20 PM"}}
]`
