// Fetch librarian information from sessionStorage
const librarianEntity = JSON.parse(sessionStorage.getItem('librarianEntity'));

if (librarianEntity) {
    document.getElementById('librarianName1').innerText = librarianEntity.librarianName;
    document.getElementById('librarianName').innerText = librarianEntity.librarianName;
} else {
    alert('No user data found. Please log in.');
    window.location.href = 'loginForm.html';
}


async function fetchStudentData() {
    try {
      const response = await fetch('http://localhost:8080/library/librarian/getStudent');
      const students = await response.json();

      const studentTableBody = document.getElementById('studentTableBody');
      studentTableBody.innerHTML = ''; // Clear existing rows
      let i=0;
      students.forEach((student) => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>#${student.student_uid}</td>
          <td>${student.student_first_name}</td>
          <td>${student.student_last_name}</td>
          <td>${student.email_id}</td>
          <td>${student.issued_book_count}</td>
          <td>${student.status}</td>
        `;
        studentTableBody.appendChild(row);
        i++;
      });
    console.log(i);
     let vis= document.getElementById('visitor-number');
     vis.innerHTML=i;
    } catch (error) {
      console.error('Error fetching student data:', error);
    }
  }

  // Fetch data when the page loads
  window.onload = fetchStudentData;

  let allBooks = [];
// Fetch books and populate the books table
async function fetchBooks() {
    try {
        const response = await fetch('http://localhost:8080/library/librarian/getbooks');
        if (response.ok) {
            const books = await response.json();
            allBooks=books;
            populateBooksTable(books);
        } else {
            console.error('Failed to fetch books. Status:', response.status);
        }
    } catch (error) {
        console.error('Error fetching books:', error);
    }
}



function populateBooksTable(books) {
    const tableBody = document.querySelector('#booksTable tbody');
    tableBody.innerHTML = ''; 

    let i=0;
    books.forEach(book => {
        
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>#${book.bookUid}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.edition}</td>
            <td>${book.genre.genre_name}</td>
            <td>${book.book_language_id.book_language_name}</td>
            <td>${book.shelf_location}</td>
            <td>${book.status}</td>
        `;
        tableBody.appendChild(row);
         i++;
    });
    document.getElementById('total-books').innerHTML=i;
}

document.addEventListener('DOMContentLoaded', () => {
    fetchBooks();
});


document.addEventListener('DOMContentLoaded', () => {
    
    const searchInput = document.querySelector('.brutalist-input');
    const searchResults = document.getElementById('searchResults');

    searchInput.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase();
       
        searchResults.innerHTML = '';

        if (!query) {
            searchResults.style.display = 'none';
            return;
        }

        const filteredBooks = allBooks.filter(book =>
            (book.title && book.title.toLowerCase().includes(query)) ||
            (book.author && book.author.toLowerCase().includes(query)) ||
            (book.bookUid && book.bookUid.toLowerCase().includes(query)) ||
            (book.book_language_id.book_language_name && book.book_language_id.book_language_name.toLowerCase().includes(query)) ||
            (book.genre.genre_name && book.genre.genre_name.toLowerCase().includes(query))
        );

        if (filteredBooks.length === 0) {
            searchResults.innerHTML = '<div>No books found</div>';
        } else {
            filteredBooks.forEach(book => {
                const bookItem = document.createElement('div');
                bookItem.innerHTML = `${book.title} by ${book.author} , Status : ${book.status} , Shelf Location : ${book.shelf_location}`;
                searchResults.appendChild(bookItem);
            });
        }

        searchResults.style.display = 'block';
    });

});



// Select the button by its ID
const addStudentButton = document.getElementById("add-books");

// Add a click event listener to the button
addStudentButton.addEventListener("click", function() {
  // Redirect to student.html
  window.location.href = "AdminAddBook.html";
});


 // Handle the "Login Student" button click
 document.getElementById('loginStudentButton').addEventListener('click', function() {
    // Make a POST request to the API to get the student entity
    fetch('http://localhost:8080/library/librarian/borrow/getMasterstudent', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        // Add any necessary request body if required
        // Example: body: JSON.stringify({ someData: 'value' })
    })
    .then(response => response.json())
    .then(studentEntity => {
        // Store the student entity in sessionStorage
        sessionStorage.setItem('studentEntity', JSON.stringify(studentEntity));

        // Redirect to student.html after storing the data
        window.location.href = "../StudentFiles/student.html";
    })
    .catch(error => {
        console.error('Error fetching student data:', error);
        alert('Failed to fetch student data');
    });
});


// Function to fetch popular books and update the DOM
async function fetchAndDisplayPopularBooks() {
    try {
      // Fetch popular books from the backend
      const response = await fetch("http://localhost:8080/library/librarian/borrow/getpopularbooks");
      
      // Check if the response is OK
      if (!response.ok) {
        throw new Error("Failed to fetch popular books");
      }
  
      // Parse the JSON response
      const popularBooks = await response.json();
  
      // Update the span elements with the top 6 books
      for (let i = 0; i < 6; i++) {
        const bookSpan = document.getElementById(`pop-book${i + 1}`);
        if (bookSpan) {
          bookSpan.textContent = popularBooks[i] || "No Data"; // Fallback if fewer than 6 books are returned
        }
      }
    } catch (error) {
      console.error("Error fetching popular books:", error);
    }
  }
  
  // Call the function when the page loads
  window.addEventListener("DOMContentLoaded", fetchAndDisplayPopularBooks);
  

// Fetch transactions and populate the transactions table
async function fetchTransactions() {
    try {
        const response = await fetch('http://localhost:8080/library/librarian/getissuedbooks');
        if (response.ok) {
            const transactions = await response.json();
            populateTransactionsTable(transactions);
        } else {
            console.error('Failed to fetch transactions. Status:', response.status);
        }
    } catch (error) {
        console.error('Error fetching transactions:', error);
    }
}

function populateTransactionsTable(transactions) {
    const tableBody = document.querySelector('#transactionsTable tbody');
    tableBody.innerHTML = ''; // Clear existing data

    transactions.forEach(transaction => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${transaction.transactionId}</td>
            <td>${transaction.studentUid}</td>
            <td>${transaction.bookUid}</td>
            <td>${transaction.borrowed_date || ''}</td>
            <td>${transaction.expected_return_date || ''}</td>
            
           
            <td>${transaction.status}</td>
            <td>${transaction.remark}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Fetch data when the page loads
document.addEventListener('DOMContentLoaded', () => {
    fetchTransactions();
    fetchTransactions1();
});



// Fetch transactions and populate the transactions table
async function fetchTransactions1() {
    try {
        const response = await fetch('http://localhost:8080/library/librarian/getoverduebooks');
        if (response.ok) {
            const transactions = await response.json();
            populateTransactionsTable1(transactions);
        } else {
            console.error('Failed to fetch transactions. Status:', response.status);
        }
    } catch (error) {
        console.error('Error fetching transactions:', error);
    }
}

function populateTransactionsTable1(transactions) {
    const tableBody = document.querySelector('#OverduetransactionsTable tbody');
    tableBody.innerHTML = ''; // Clear existing data

    transactions.forEach(transaction => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${transaction.transactionId}</td>
            <td>${transaction.studentUid}</td>
            <td>${transaction.bookUid}</td>
            <td>${transaction.borrowed_date || ''}</td>
            <td>${transaction.expected_return_date || ''}</td>
            
            <td>${transaction.fine}</td>
            <td>${transaction.status}</td>
            <td>${transaction.remark}</td>
        `;
        tableBody.appendChild(row);
    });
}

