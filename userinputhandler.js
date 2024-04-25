document.addEventListener('DOMContentLoaded', function() {
    const loginButton = document.getElementById('logininButton');

    loginButton.addEventListener('click', function() {
    // URL of the Augsburg web page you want to redirect to
    const searchRegisterClassesURL = 'https://registration.augsburg.edu/recreg/Pages/frmCourseSearch.aspx';
    const augsburgURL =  'https://augnet.augsburg.edu/';

        // Open a new tab with the Augsburg web page
        chrome.tabs.create({ url: augsburgURL });
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const loginButton = document.getElementById('logininButton');

    loginButton.addEventListener('click', function() {
        // Get the username and password entered by the user
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        // Send the username and password to the content script to handle login
        chrome.runtime.sendMessage({ action: 'login', username: username, password: password });
    });
});

