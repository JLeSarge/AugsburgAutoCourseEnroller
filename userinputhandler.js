document.addEventListener('DOMContentLoaded', function() {
    var submitButton = document.getElementById('submitButton');

    submitButton.addEventListener('click', function() {
        var userInput = document.getElementById('userInput').value;
        // Do something with the user input
        console.log('User input:', userInput);
        // You can send this input to background script or perform any other action here
    });
});