package ru.mirea.senla.task3.task3_4;

public class Warehouse {
    private Book[] availableBooks = new Book[] {
            new Book(0),
            new Book(1),
            new Book(2),
            new Book(3),
            new Book(4),
            new Book(5)
    };

    public void  checkBook(int[] booksId, Order order) {
        for(int i = 0; i < availableBooks.length; i++) {
            for(int j = 0; j < booksId.length; j++) {
                if(availableBooks[i].getId() == booksId[j] && availableBooks[i].getStatus() == "отсутствует") {
                    availableBooks[i].request(order);
                }
            }
        }
    }

    public void addBook(int bookId) {
        availableBooks[bookId].setStatus(BookStatus.IN_STOCK);
        availableBooks[bookId].removeRequest();
        System.out.println("Добавлена книга '" + bookId + "' на склад, статус книги: " + availableBooks[bookId].getStatus());
    }
    public void removeBook(int bookId) {
        availableBooks[bookId].setStatus(BookStatus.OUT_STOCK);
        System.out.println("Снята книга '" + bookId + "' со склада, статус книги: " + availableBooks[bookId].getStatus());
    }

    public Book getBook(int bookId) {
        return availableBooks[bookId];
    }
}