package JAVA.Library_Management_System;
import java.util.Scanner;

public class Main {
    static int choose;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Library library = new Library();
        boolean PLAY = true;
        System.out.println("==========================================================================");
        System.out.println("===================== Welcome To Dinosaur Book Store =====================");
        while (PLAY) {
            DO();
            switch (choose) {
                case 1: addBook(library); break;
                case 2: addPatron(library); break;
                case 3: borrowBook(library); break;
                case 4: returnBook(library); break;
                case 5: checkFines(library); break;
                case 6: displayAvailableBooks(library); break;
                case 7: displayPatronInformation(library); break;
                case 8: System.out.println("Thank you for visiting Dinosaur Book Store. Have a nice day!"); break;
            }
            if(choose == 8){PLAY = false;}
            else if(!DOagain()){PLAY = false;}
        }
    }
    static void DO(){
        do{
            System.out.println("==========================================================================");
            System.out.println("1.Adding New Book");
            System.out.println("2.Adding New Patron");
            System.out.println("3.Borrowing a Book");
            System.out.println("4.Returning a Book");
            System.out.println("5.Checking Fines");
            System.out.println("6.Displaying Available Books");
            System.out.println("7.Displaying Patron Information");
            System.out.println("8.Exit");
            System.out.print("What Do Want To Do ? : ");
            choose = sc.nextInt();
        }while(choose < 1 || choose >8);
    }
    static boolean DOagain(){
        char x;
        System.out.print("Do you want to do again ? (Y to do again) : ");
        x = sc.next().charAt(0);
        if(x=='Y' || x=='y')return true;
        else return false;
    }


    static void addBook(Library library) {
        sc.nextLine(); // Consume newline
        String title;
        String author;
        String ISBN;

        System.out.print("Input Title : ");
        title = sc.nextLine();
        System.out.print("Input author : ");
        author = sc.nextLine();
        System.out.print("Input ISBN : ");
        ISBN = sc.nextLine();
        library.addBook(new Book(title, author, ISBN));
        System.out.println("Book added successfully!");
    }

    static void addPatron(Library library) {
        sc.nextLine(); // Consume newline
        String name;
        int ID;

        System.out.print("Input name : ");
        name = sc.nextLine();
        System.out.print("Input ID : ");
        ID = sc.nextInt();
        library.addPatron(new Patron(name, ID));
        System.out.println("Patron added successfully!");
    }

    static void borrowBook(Library library) {
        sc.nextLine(); // Consume newline
        System.out.print("Enter Patron ID: ");
        int patronID = sc.nextInt();
        System.out.print("Enter Book ISBN: ");
        String bookISBN = sc.next();

        Patron patron = null;
        Book book = null;

        for (Patron p : library.getPatrons()) {
            if (p.getID() == patronID) {
                patron = p;
                break;
            }
        }

        for (Book b : library.getBooks()) {
            if (b.getISBN().equals(bookISBN)) {
                book = b;
                break;
            }
        }

        if (patron != null && book != null && book.isAvailable()) {
            patron.getBorrowedBooks().add(book);
            book.setAvailable(false);
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Invalid patron ID or book ISBN, or the book is not available.");
        }
    }

    static void returnBook(Library library) {
        sc.nextLine();
        System.out.print("Enter Patron ID: ");
        int patronID = sc.nextInt();
        System.out.print("Enter Book ISBN: ");
        String bookISBN = sc.next();

        Patron patron = null;
        Book book = null;

        for (Patron p : library.getPatrons()) {
            if (p.getID() == patronID) {
                patron = p;
                break;
            }
        }

        for (Book b : library.getBooks()) {
            if (b.getISBN().equals(bookISBN)) {
                book = b;
                break;
            }
        }

        if (patron != null && book != null && !book.isAvailable() && patron.getBorrowedBooks().contains(book)) {
            patron.getBorrowedBooks().remove(book);
            book.setAvailable(true);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Invalid patron ID or book ISBN, or the book is already returned.");
        }
    }

    static void checkFines(Library library) {
        sc.nextLine(); // Consume newline
        System.out.print("Enter Patron ID: ");
        int patronID = sc.nextInt();

        Patron patron = null;

        for (Patron p : library.getPatrons()) {
            if (p.getID() == patronID) {
                patron = p;
                break;
            }
        }

        if (patron != null) {
            // Calculate fines (if any) and display
            System.out.println("Total fines for Patron " + patron.getName() + " (ID: " + patron.getID() + "): $0.00");
        } else {
            System.out.println("Invalid patron ID.");
        }
    }

    static void displayAvailableBooks(Library library) {
        System.out.println("Available Books:");
        for (Book book : library.getBooks()) {
            if (book.isAvailable()) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getISBN() + ")");
            }
        }
    }

    static void displayPatronInformation(Library library) {
        sc.nextLine();
        System.out.print("Enter Patron ID: ");
        int patronID = sc.nextInt();

        Patron patron = null;

        for (Patron p : library.getPatrons()) {
            if (p.getID() == patronID) {
                patron = p;
                break;
            }
        }

        if (patron != null) {
            System.out.println("Patron Information:");
            System.out.println("Name: " + patron.getName());
            System.out.println("ID: " + patron.getID());
            System.out.println("Borrowed Books:");
            for (Book book : patron.getBorrowedBooks()) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getISBN() + ")");
            }
        } else {
            System.out.println("Invalid patron ID.");
        }
    }

}
