document.querySelector('.form').addEventListener('submit', async function (event) {
    event.preventDefault(); // Prevent form from submitting the traditional way

    const studentUid = document.querySelector('input[placeholder="Enter Student UID"]').value;
    const studentUsername = document.querySelector('input[placeholder="Enter Username"]').value;
    const studentPassword = document.querySelector('input[placeholder="Enter password"]').value;

    const requestData = {
        studentUid,
        studentUsername,
        studentPassword
    };

    try {
        const response = await fetch('http://localhost:8080/student/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        });

        if (response.ok) {
            const result = await response.json(); // Assuming your API returns student details
            sessionStorage.setItem('studentEntity', JSON.stringify(result)); // Store the response data
            alert('Login successful!');
            window.location.href = "student.html"; // Redirect after login
        } else {
            const error = await response.json();
            alert('Login failed: ' + (error.message || 'Unknown error'));
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to connect to the server.');
    }
});


function togglePassword() {
    const passwordInput = document.getElementById("passwordInput");
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }
}
