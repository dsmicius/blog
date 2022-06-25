$(document).ready(function() {
    $("#settingsSelector").change(function () {
        let selectedOption = $('#settingsSelector').val();
        if (selectedOption !== ''){
            // window.location.replace('?setting=' + selectedOption);
            window.location.replace('http://localhost:8888/users');
        }
    });
})
