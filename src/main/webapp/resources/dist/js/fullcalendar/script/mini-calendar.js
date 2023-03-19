console.log("Hello from the other side")
const CHECK_FORMAT = "check-";

document.addEventListener('DOMContentLoaded', function () {
    var div_mini = document.getElementById('mini-calendar');
    console.log("Test eh")
    console.log(events)
    var mini_calendar = new FullCalendar.Calendar(div_mini, {
        editable: false,
        initialDate: '2023-03-12',
        events: events/*[
            {
                title: 'event1',
                start: '2023-01-11T10:00:00',
                end: '2023-01-11T16:00:00'
            },
            {
                title: 'event2',
                start: '2023-01-13T10:00:00',
                end: '2023-01-13T16:00:00'
            }
        ]*/,
        eventLeave: function (info) {
            console.log('event left!', info.event);
        }
    });
    mini_calendar.render();
});

function setStateCheckbox(status) {
    const checkboxes=document.querySelectorAll(`[id^="${CHECK_FORMAT}"]`);
    for (const checkbox of checkboxes) {
        checkbox.checked=status;
    }
}

function checkAll() {
    setStateCheckbox(true)
}

function uncheckAll() {
    setStateCheckbox(false)
}

