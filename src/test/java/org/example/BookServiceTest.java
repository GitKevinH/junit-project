package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookService bookService;
    @InjectMocks
    private BookService bookServiceTest;
    private User user;
    private Book book1;
    private Book book2;


    @BeforeEach
    public void setUp() {
        bookService = new BookService();

        user = new User("Rick", "mortyismine", "rick@morty.com");

        book1 = new Book("Title of Book1", "Author of Book1", "Genre of Book1", 3.99);
        book2 = new Book("Title of Book2", "Author of Book2", "Genre of Book2", 21.99);


        bookServiceTest = new BookService();
        bookServiceTest.addBook(book1);
        bookServiceTest.addBook(book2);
    }

    @AfterEach
    public void tearDown() {
        bookServiceTest = null;
        user = null;
    }

    //searchBook method testing
    @Test
    public void testSearchBook_Positive() {
        String keyword = "Book1";
        List<Book> result = bookServiceTest.searchBook(keyword);
        assertEquals(1, result.size());
        assertEquals("Title of Book1", result.get(0).getTitle());
    }

    @Test
    public void testSearchBook_Negative() {
        String keyword = "Exists";
        List<Book> result = bookServiceTest.searchBook(keyword);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchBook_EdgeCase() {
        String keyword = "  ";
        List<Book> result = bookServiceTest.searchBook(keyword);
        assertTrue(result.isEmpty());
    }


    // purchaseBook method testing
    @Test
    public void testPurchaseBook_Positive() {
        boolean result = bookServiceTest.purchaseBook(user, book1);
        assertTrue(result);
    }

    @Test
    public void testPurchaseBook_Negative() {
        Book nonPurchasedBook = new Book("Title of Book3", "Author of Book3", "Genre of Book3", 9.99);
        boolean result = bookServiceTest.purchaseBook(user, nonPurchasedBook);
        assertFalse(result);
    }

    @Test
    public void testPurchaseBook_EdgeCase() {
        boolean result = bookServiceTest.purchaseBook(user, null);
        assertFalse(result);
    }

    // addBookReview method testing

    @Test
    public void testAddBookReview_Positive() {
        String review = "It's a book";
        boolean result = bookServiceTest.addBookReview(user, book1, review);
        assertTrue(result);
    }

    @Test
    public void testAddBookReview_Negative() {
        Book nonPurchasedBook = new Book("Title of Book3", "Author of Book3", "Genre of Book3", 9.99);
        String review = "Great book!";
        boolean result = bookServiceTest.addBookReview(user, nonPurchasedBook, review);
        assertFalse(result);
    }

    @Test
    public void testAddBookReview_EdgeCase() {
        String review = null;
        boolean result = bookServiceTest.addBookReview(user, book1, review);
        assertFalse(result);
    }

    //addBook method testing

    @Test
    public void testAddBook_Positive() {
        Book newBook = new Book("Title of New Book", "Author of New Book", "Genre of New Book", 14.99);
        boolean result = bookServiceTest.addBook(newBook);
        assertTrue(result);
    }

    @Test
    public void testAddBook_Negative() {
        boolean result = bookServiceTest.addBook(book1);
        assertFalse(result);
    }

    @Test
    public void testAddBook_EdgeCase() {
        Book emptyBook = new Book("", "", "", 0.0);
        boolean result = bookServiceTest.addBook(emptyBook);
        assertTrue(result);
    }

    //removeBook method testing

    @Test
    public void testRemoveBook_Positive() {
        boolean result = bookServiceTest.removeBook(book1);
        assertTrue(result);
    }

    @Test
    public void testRemoveBook_Negative() {
        Book nonExistentBook = new Book("Nonexistent", "Author", "Genre", 9.99);
        boolean result = bookServiceTest.removeBook(nonExistentBook);
        assertFalse(result);
    }

    @Test
    public void testRemoveBook_EdgeCase() {
        boolean result = bookServiceTest.removeBook(null);
        assertFalse(result);
    }


}