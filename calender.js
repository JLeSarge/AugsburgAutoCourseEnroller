// Function to handle click event on the button
function createEvent() {
    // Send a message to the background script to create the event
    chrome.runtime.sendMessage({ action: 'createEvent' }, function(response) {
      if (response.success) {
        alert('Event created successfully!');
      } else {
        alert('Failed to create event.');
      }
    });
  }
  
  // Attach click event listener to the button
  document.getElementById('createEventButton').addEventListener('click', createEvent);
  