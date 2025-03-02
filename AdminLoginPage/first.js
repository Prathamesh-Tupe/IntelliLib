 // JavaScript to handle form submission
 const form = document.getElementById('loginForm');

 form.addEventListener('submit', async (event) => {
     event.preventDefault(); // Prevent the default form submission

     // Gather form data
     const librarianId = document.getElementById('librarianId').value;
     const librarianUsername = document.getElementById('username').value;
     const librarianPassword = document.getElementById('password').value;

     // Prepare the data to send
     const requestData = {
         librarianId,
         librarianUsername,
         librarianPassword
     };

     try {
         // Send the data as a POST request to the API
         const response = await fetch('http://localhost:8080/library/login', {
             method: 'POST',
             headers: {
                 'Content-Type': 'application/json'
             },
             body: JSON.stringify(requestData)
         });

         // Check if the response is successful
         if (response.ok) {
             const contentType = response.headers.get("Content-Type");
             
             if (contentType && contentType.includes("application/json")) {
                 const result = await response.json();
                 
                 // Check if the result is null (invalid credentials)
                 if (result === null) {
                     alert('Invalid login credentials. Please try again.');
                 } else {
                     alert('Login successful!');
                     console.log('Librarian Data:', result);
                     // You can handle the successful login further (e.g., redirect to another page)
                      // Store the librarianEntity in sessionStorage
                     sessionStorage.setItem('librarianEntity', JSON.stringify(result));
 
                    // Redirect to adminHome.html
                    window.location.href = '../AdminHomePage/index.html';
                 }
             } 
             else {
                 console.error('Response is not in JSON format.');
                 alert('Unexpected response format from server.');
             }
         }
         else if (response.status === 401) {
             alert('Invalid login credentials. Please try again.');
         }
         else {
             alert('Error logging in. Please try again.');
         }
         
     } catch (error) {
         console.error('Error:', error);
         alert('An error occurred while processing your request.');
     }
 });