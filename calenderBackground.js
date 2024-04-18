// Listener for messages from popup
chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
    if (request.action === 'createEvent') {
      // Call a function to create the event
      createCalendarEvent(function(success) {
        // Send response back to popup
        sendResponse({ success: success });
      });
      // Keep the messaging channel open for asynchronous response
      return true;
    }
  });
  
  // Function to create a calendar event
  function createCalendarEvent(callback) {
    // Get user's authorization token
    chrome.identity.getAuthToken({ interactive: true }, function(token) {
      if (chrome.runtime.lastError) {
        console.error(chrome.runtime.lastError.message);
        callback(false);
        return;
      }
  
      // Make API call to create event using Google Calendar API
      fetch('https://www.googleapis.com/calendar/v3/calendars/primary/events', {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          'summary': 'Sample Event',
          'start': {
            'dateTime': '2024-03-27T10:00:00',
            'timeZone': 'Your_Time_Zone'
          },
          'end': {
            'dateTime': '2024-03-27T12:00:00',
            'timeZone': 'Your_Time_Zone'
          }
        })
      })
      .then(response => {
        if (response.ok) {
          console.log('Event created successfully');
          callback(true);
        } else {
          console.error('Failed to create event:', response.statusText);
          callback(false);
        }
      })
      .catch(error => {
        console.error('Error creating event:', error);
        callback(false);
      });
    });
  }
  