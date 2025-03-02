// Fetch books from the backend and display them
async function fetchBooks() {
    try {
        const response = await fetch('http://localhost:8080/library/librarian/getbooks'); // Replace with your backend endpoint
        if (response.ok) {
            const books = await response.json();
            populateTable(books);
        } else {
            console.error('Failed to fetch books. Status:', response.status);
        }
    } catch (error) {
        console.error('Error fetching books:', error);
    }
}

// Populate table with book data
function populateTable(books) {
    const tableBody = document.querySelector('#booksTable tbody');
    tableBody.innerHTML = ''; // Clear previous data
    let i=0;
    books.forEach(book => {
        const row = document.createElement('tr');

        // Add book data to table
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

// Fetch books when the page loads
document.addEventListener('DOMContentLoaded', fetchBooks);
