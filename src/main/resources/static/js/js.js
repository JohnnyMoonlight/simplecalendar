        let rooms;
        let appointments;
        let events = [];
        let calendar;

        const SERVER_URL = "";

        const ROOMS_ID = "ressource";


        window.onload = async function () {
            await initializeData();
            initCalendar();
            fillRommOptions(rooms);

        };

        //Data Retrieval
        async function initializeData() {
            const roomsRequest = await fetch(SERVER_URL + "/allRooms", {
                "Accept": "application/json"
            });
            const roomsJson = await roomsRequest.json();
            rooms = JSON.parse(roomsJson);
            const appointmentsRequest = await fetch(SERVER_URL + "/allAppointments", {
                "Accept": "application/json"
            });
            const appointmentsJson = await appointmentsRequest.text();
            appointments = JSON.parse(appointmentsJson);
            events = [];
            parseAppointments(appointments);
        }

        function parseAppointments(appointments) {
            appointments.forEach(app => events.push({
                "title": app.id,
                "end": new Date(app.endTime).toISOString(),
                "start": new Date(app.startTime).toISOString()
            }))
        }

        //View


        function initCalendar() {
            const calendarEl = document.getElementById('calendar');
            calendar = new FullCalendar.Calendar(calendarEl, {
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek'
                },
                height: '80%',
                dateClick: function (info) {
                    toggleForm(info.dateStr);
                },
                initialView: 'dayGridMonth',
                events: events
            });
            calendar.render();
        }

        function fillRommOptions(rooms) {
            let select = document.getElementById(ROOMS_ID).options;
            rooms.forEach(room => select.add(new Option(room.name, room.roomId)));
        }


        function toggleForm(dateStr) {

            if (document.getElementById("overlay").style.display == "block") {
                document.getElementById("overlay").style.display = "none";
                document.getElementById("form").style.display = "none";
            } else {
                document.getElementById("overlay").style.display = "block";
                document.getElementById("form").style.display = "block";
                document.getElementById("startDate").value = dateStr;
                document.getElementById("endDate").value = dateStr;
            }
        }

        //Data storage

        function createAppointment() {
            postAppointment().then(response => {
                console.log(response);
                calendar.addEvent({
                    "title": response.value.id,
                    "end": new Date(response.value.endTime).toISOString(),
                    "start": new Date(response.value.startTime).toISOString()

                });
            });
            toggleForm();
        }

        async function postAppointment() {
            const appointment = getAppointmentObject();

            const response = await fetch(SERVER_URL + "/createAppointment", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(appointment)
            });
            return response.json();
        }

        function getAppointmentObject() {

            let startTimeValue = document.getElementById("startTime").value;
            let startDateValue = document.getElementById("startDate").value;
            let endTimeValue = document.getElementById("startTime").value;
            let endDateValue = document.getElementById("endDate").value;

            const isoStart = createIsoDateFromInput(startTimeValue, startDateValue);
            const isoEnd = createIsoDateFromInput(endTimeValue, endDateValue);
            let roomId = document.getElementById(ROOMS_ID).value;


            return {
                "roomId": roomId,
                "startTime": isoStart,
                "endTime": isoEnd
            };

        }

        function createIsoDateFromInput(date, time) {
            return new Date(startDate.value + " " + startTime.value)
        }
