/*
fetches the information, confirms and redirects when the user enters the info to login
 */

document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission

    var formData = new FormData(event.target);

    fetch('/login', {
        method: 'POST',
        body: formData,
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                return response.text(); // Convert the response body to text
            } else {
                throw new Error('Login failed');
            }
        })
        .then(htmlContent => {
            if (htmlContent.includes('TRUE PERFORMANCE ATHLETICS HUB')) { // Check for a unique part of the trainer's hub page
                window.location.href = '/trainer'; // Redirect to the trainer's hub page
            } else {
                alert('Login failed. Please check your credentials.');
            }
        })
        .catch(error => {
            console.error('Error during fetch:', error);
        });
});