// content.js (content script) file

// Function to log in the user
function login(username, password) {
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const loginForm = document.forms['login_form']; // Accessing the login form element

    // Check if login form elements are found
    if (usernameInput && passwordInput && loginForm) {
        // Fill in the username and password
        usernameInput.value = username;
        passwordInput.value = password;
        
        // Submit the login form
        loginForm.submit();
    } else {
        console.error('Login form elements not found.');
    }
}

// Call the login function with the user's credentials
//login('adana', 'Aada2001@');

// Function to select the course and submit the form
function courseSelect(courseName) {
    const courseDescriptionInput = document.getElementById('txtCourseDescription');
    const showOpenSeatsCheckbox = document.getElementById('chkShowOpenSeats');
    const searchButton = document.getElementById('btnFill');

    if (courseDescriptionInput && showOpenSeatsCheckbox && searchButton) {
        // Set the value of the course name input field
        courseDescriptionInput.value = courseName;

        // Check the "Show Open Seats" checkbox
        showOpenSeatsCheckbox.checked = true;

        // Manually trigger the 'click' event on the search button
        searchButton.click();

        // Remove the event listener after the function is executed
        searchButton.removeEventListener('click', courseSelect);
    } else {
        console.error('Course description input field, "Show Open Seats" checkbox, or search button not found.');
    }

    userid = 'adana'

    await fetch("/api/enroll", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: {
            studentId: 000000,
            name: userid,
            email: userid + "@augsburg.edu",
            enrolled: true
        }
    }
    ).catch((error) => {
        alert(error)
        return;
    })
}

// Call the courseSelect function with the course name provided by the user
courseSelect('Introduction to Cultural Anthropology');

// Listen for messages from the userinputhandler.js script
/* chrome.runtime.onMessage.addListener(function(message, sender, sendResponse) {
    if (message.action === 'login') {
        // Extract username and password from the message
        const { username, password } = message;

        // Call the login function with the provided credentials
        login(username, password);
    }
}); */




// Function to click the login button
function clickLoginButton() {
    const loginButton = document.querySelector('input[type="submit"].submit');
    if (loginButton) {
        loginButton.click(); // Simulate clicking the login button
    } else {
        console.error('Login button not found.');
    }
}

// Call the clickLoginButton function to simulate clicking the login button
clickLoginButton();

function clickRecordsRegistrationButton() {
    // Find the button element by its class name
    const RRbutton = document.querySelector('a.button[href="https://augnet.augsburg.edu/protected/recreg"]');
    
    // Check if the button element is found
    if (RRbutton) {
        // Trigger a click event on the button
        RRbutton.click();
    } else {
        console.error('Records Registration & Finances button not found.');
    }
}
clickRecordsRegistrationButton();

/* function clickSearchRegisterClassesButton() {
    // Specify the URL of the Search/Register for Classes page
    const searchRegisterClassesURL = 'https://registration.augsburg.edu/recreg/Pages/frmCourseSearch.aspx';

    // Navigate to the specified URL
    window.location.href = searchRegisterClassesURL;
    
} */

function clickSearchRegisterClassesButton() {
    // Find the anchor element by its href attribute value
    const button = document.querySelector('a.button[href="frmCourseSearch.aspx"]');

    // Check if the anchor element is found
    if (button) {
        // Trigger a click event on the anchor element
        button.click();
        
    } else {
        console.error('Search/Register for Classes button not found.');
    }
}
clickSearchRegisterClassesButton();




