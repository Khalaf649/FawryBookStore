#  Quantum Bookstore

An extensible, object-oriented simulation of an online bookstore 

---
## Screenshots
![before](https://github.com/Khalaf649/FawryBookStore/blob/main/1.jpg)
![after](https://github.com/Khalaf649/FawryBookStore/blob/main/2.jpg)

These 2 images before and after removing the books outdated 20

---
## Features

- Supports three types of books:
  -  *PaperBook* – has stock and shipping
  -  *EBook* – digital format sent via email
  -  *DemoBook* – not for sale
- Centralized *Inventory* (Singleton) that simulates a database
- Auto-generated ISBNs (like a primary key)
- Easily extendable using *Open/Closed Principle*
- Decoupled *Delivery System* using interfaces

---

##  Design Overview

###  Book (Abstract Class)
Base class for all book types, contains:
- isbn (auto-generated)
- title, author, price, publicationYear

###  BookFactory (Static Factory Pattern)
Responsible for creating book instances:
java
BookFactory.createPaperBook(...);
BookFactory.createEBook(...);
BookFactory.createDemoBook(...);


- Open for extension: just create a new Book subclass and add its creation method here.

###  Inventory (Singleton)
Simulates a persistent database with:
- Thread-safe singleton access
- CRUD methods for Book
- Validates uniqueness by ISBN

### DeliveryService (Interface)
Abstraction for delivery mechanisms:
- MailService – handles EBooks
- ShippingService – handles PaperBooks

Easily extendable for future delivery types.

### BookStoreService
Handles:
- Adding/removing books
- Buying books (with stock checks and delivery delegation)

---

## Extensibility

 Following the *Open/Closed Principle*:

To add a new book type:
1. Create a new class extending Book
2. Add a creation method in BookFactory

To add a new delivery method:
1. Create a class that implements DeliveryService
2. Add it to the deliveryServices list in BookStoreService

---

##  Example Usage

java
Book paperBook = BookFactory.createPaperBook("Java 101", 2020, 59.99, "John Doe", 100, 0.5);
bookStoreService.addBook(paperBook);

double total = bookStoreService.buyBook(paperBook.getIsbn(), 1, "user@example.com", "123 Main St");


---

##  Future Work

-  Add a BookValidator class for clean validation
-  Refactor buyBook() for better separation of concerns

---

##  Principles & Patterns Used

| Concept                | Implementation            |
|------------------------|----------------------------------|
| Open/Closed Principle  | Extend Book, DeliveryService |
| Factory Pattern        | BookFactory                    |
| Singleton Pattern      | Inventory                      |
| Interface Segregation  | DeliveryService abstraction    |
| DRY & SRP              | Clean separation of logic        |


---

## 🧪 Testing

Use QuantumBookstoreFullTest to test:
- Adding/removing books
- Buying different types
- Delivery mechanism coverage
