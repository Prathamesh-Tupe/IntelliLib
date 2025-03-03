// Fetch librarian information from sessionStorage
const librarianEntity = JSON.parse(sessionStorage.getItem('librarianEntity'));

if (librarianEntity) {
    document.getElementById('librarianName').innerText = librarianEntity.librarianName;
} else {
    alert('No user data found. Please log in.');
    window.location.href = 'loginForm.html';
}

// Fetch books and populate the books table
async function fetchBooks() {
    try {
        const response = await fetch('http://localhost:8080/library/librarian/getbooks');
        if (response.ok) {
            const books = await response.json();
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
    tableBody.innerHTML = ''; // Clear existing data
    let i = 0;

    books.forEach(book => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${++i}</td>
            <td>${book.bookUid}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.publisher}</td>
            <td>${book.edition}</td>
            <td>${book.isbn_number}</td>
            <td>${book.genre.genre_name}</td>
            <td>${book.book_language_id.book_language_name}</td>
            <td>${book.shelf_location}</td>
            <td>${book.book_price}</td>
            <td>${book.status}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Fetch transactions and populate the transactions table
async function fetchTransactions() {
    try {
        const response = await fetch('http://localhost:8080/library/librarian/borrow/pasttransaction');
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
            <td>${transaction.actual_return_date || ''}</td>
            <td>${transaction.fine}</td>
            <td>${transaction.status}</td>
            <td>${transaction.remark}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Fetch data when the page loads
document.addEventListener('DOMContentLoaded', () => {
    fetchBooks();
    fetchTransactions();
});

async function fetchStudentData() {
    try {
      const response = await fetch('http://localhost:8080/library/librarian/getStudent');
      const students = await response.json();

      const studentTableBody = document.getElementById('studentTableBody');
      studentTableBody.innerHTML = ''; // Clear existing rows

      students.forEach((student) => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${student.student_uid}</td>
          <td>${student.student_first_name}</td>
          <td>${student.student_last_name}</td>
          <td>${student.course_id}</td>
          <td>${student.roll_no}</td>
          <td>${student.email_id}</td>
          <td>${student.phone_number}</td>
          <td>${student.DOB}</td>
          <td>${student.address}</td>
          <td>${student.issued_book_count}</td>
          <td>${student.date_registration}</td>
          <td>${student.end_registration}</td>
          <td>${student.status}</td>
        `;
        studentTableBody.appendChild(row);
      });
    } catch (error) {
      console.error('Error fetching student data:', error);
    }
  }

  // Fetch data when the page loads
  window.onload = fetchStudentData;
