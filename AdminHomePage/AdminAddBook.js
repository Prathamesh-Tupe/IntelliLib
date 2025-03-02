document.getElementById('addBookForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const formData = {
        bookUid: document.getElementById('bookUid').value,
        title: document.getElementById('title').value,
        author: document.getElementById('author').value,
        publisher: document.getElementById('publisher').value,
        edition: document.getElementById('edition').value,
        isbn_number: parseInt(document.getElementById('isbn_number').value),
        genre: { genre_id: document.getElementById('genre').value },
        book_language_id: { book_language_id: document.getElementById('book_language_id').value },
        shelf_location: document.getElementById('shelf_location').value,
        book_price: parseInt(document.getElementById('book_price').value),
        status: document.getElementById('status').value,
    };

    try {
        const response = await fetch('http://localhost:8080/library/admin/addbook', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        });

        const result = await response.json();
        document.getElementById('responseMessage').textContent =
            result.Message ? 'Book added successfully!' : 'Failed to add book.';
    } catch (error) {
        document.getElementById('responseMessage').textContent = 'Error: Unable to process the request.';
    }
});
