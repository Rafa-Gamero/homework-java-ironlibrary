package com.homework.library;

import com.homework.library.models.Book;
import com.homework.library.models.Author;
import com.homework.library.models.Student;
import com.homework.library.repositories.BookRepository;
import com.homework.library.repositories.AuthorRepository;
import com.homework.library.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional; // Importante para la transacción

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = "com.homework.library") // Asegura que Spring escanee tus componentes
public class LibraryApplication implements CommandLineRunner {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final StudentRepository studentRepository;
	private final Scanner scanner = new Scanner(System.in);

	@Autowired
	public LibraryApplication(BookRepository bookRepository, AuthorRepository authorRepository, StudentRepository studentRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.studentRepository = studentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) {
		displayMenu();
	}

	public void displayMenu() {
		boolean exit = false;
		while (!exit) {
			System.out.println("\n--- Sistema de Gestión de Biblioteca ---");
			System.out.println("1. Añadir un libro");
			System.out.println("2. Buscar libro por título");
			System.out.println("3. Buscar libro por categoría");
			System.out.println("4. Buscar libro por autor");
			System.out.println("5. Listar todos los libros con autor");
			System.out.println("6. Prestar libro a estudiante");
			System.out.println("7. Listar libros por USN");
			System.out.println("8. Borrar un libro");
			System.out.println("9. Salir");
			System.out.print("Ingrese su opción: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume la nueva línea

			switch (choice) {
				case 1:
					addBook();
					break;
				case 2:
					searchBookByTitle();
					break;
				case 3:
					searchBookByCategory();
					break;
				case 4:
					searchBookByAuthor();
					break;
				case 5:
					listAllBooksWithAuthors();
					break;
				case 6:
					issueBookToStudent();
					break;
				case 7:
					listBooksByUsn();
					break;
				case 8:
					deleteBook();
					break;
				case 9:
					exit = true;
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción inválida. Por favor, intente de nuevo.");
			}
		}
	}

	private void addBook() {
		System.out.println("\n--- Añadir un Libro ---");
		System.out.print("Ingrese ISBN: ");
		String isbn = scanner.nextLine();
		System.out.print("Ingrese Título: ");
		String title = scanner.nextLine();
		System.out.print("Ingrese Categoría: ");
		String category = scanner.nextLine();
		System.out.print("Ingrese Cantidad: ");
		int quantity = scanner.nextInt();
		scanner.nextLine(); // Consume la nueva línea

		Book book = new Book(isbn, title, category, quantity);
		bookRepository.save(book);

		System.out.print("Ingrese Nombre del Autor: ");
		String authorName = scanner.nextLine();
		System.out.print("Ingrese Email del Autor: ");
		String authorEmail = scanner.nextLine();

		Author author = new Author(authorName, authorEmail, book);
		authorRepository.save(author);

		System.out.println("Libro añadido exitosamente!");

	}

	private void searchBookByTitle() {
		System.out.println("\n--- Buscar Libro por Título ---");
		System.out.print("Ingrese título a buscar: ");
		String title = scanner.nextLine();
		List<Book> books = bookRepository.findAll().stream()
				.filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
				.toList();
		displayBooks(books);
	}

	private void searchBookByCategory() {
		System.out.println("\n--- Buscar Libro por Categoría ---");
		System.out.print("Ingrese categoría a buscar: ");
		String category = scanner.nextLine();
		List<Book> books = bookRepository.findAll().stream()
				.filter(b -> b.getCategory().toLowerCase().contains(category.toLowerCase()))
				.toList();
		displayBooks(books);
	}

	private void searchBookByAuthor() {
		System.out.println("\n--- Buscar Libro por Autor ---");
		System.out.print("Ingrese nombre del autor a buscar: ");
		String authorName = scanner.nextLine();
		List<Book> books = bookRepository.findAll().stream()
				.filter(book -> {
					Optional<Author> author = authorRepository.findAll().stream()
							.filter(a -> a.getAuthorBook() != null && a.getAuthorBook().equals(book))
							.findFirst();
					return author.isPresent() && author.get().getName().toLowerCase().contains(authorName.toLowerCase());
				})
				.toList();
		displayBooks(books);
	}

	private void listAllBooksWithAuthors() {
		System.out.println("\n--- Listar Todos los Libros con Autores ---");
		List<Book> books = bookRepository.findAll();
		if (books.isEmpty()) {
			System.out.println("No se encontraron libros.");
		} else {
			System.out.println("ISBN\tTítulo\tCategoría\tCantidad\tNombre del Autor\tEmail del Autor");
			for (Book book : books) {
				Optional<Author> author = authorRepository.findAll().stream()
						.filter(a -> a.getAuthorBook() != null && a.getAuthorBook().equals(book))
						.findFirst();
				if (author.isPresent()) {
					System.out.println(book.getIsbn() + "\t" + book.getTitle() + "\t" + book.getCategory() + "\t" +
							book.getQuantity() + "\t" + author.get().getName() + "\t" + author.get().getEmail());
				} else {
					System.out.println(book.getIsbn() + "\t" + book.getTitle() + "\t" + book.getCategory() + "\t" +
							book.getQuantity() + "\t" + "Sin Autor" + "\t" + "Sin Autor");
				}
			}
		}
	}

	private void issueBookToStudent() {
		System.out.println("\n--- Prestar Libro a Estudiante ---");
		System.out.print("Ingrese USN del Estudiante: ");
		String usn = scanner.nextLine();
		System.out.print("Ingrese Nombre del Estudiante: ");
		String studentName = scanner.nextLine();
		System.out.print("Ingrese ISBN del Libro a Prestar: ");
		String isbn = scanner.nextLine();
		System.out.print("Ingrese Fecha de Préstamo: ");
		String issueDate = scanner.nextLine();
		System.out.print("Ingrese Fecha de Devolución: ");
		String returnDate = scanner.nextLine();

		Optional<Book> book = bookRepository.findById(String.valueOf(Long.valueOf(isbn)));
		Optional<Student> student = studentRepository.findById(String.valueOf(Long.valueOf(usn)));

		if (book.isPresent() && book.get().getQuantity() > 0) {
			Student student1 = new Student(usn, studentName);
			studentRepository.save(student1);
			book.get().setQuantity(book.get().getQuantity() - 1);
			bookRepository.save(book.get());
			System.out.println("Libro prestado exitosamente!");
		} else {
			System.out.println("El libro no está disponible o el estudiante no existe.");
		}
	}

	private void listBooksByUsn() {
		System.out.println("\n--- Listar Libros por USN ---");
		System.out.print("Ingrese USN del Estudiante: ");
		String usn = scanner.nextLine();
		Optional<Student> student = studentRepository.findById(String.valueOf(Long.valueOf(usn)));
		if (student.isPresent()) {
			System.out.println("Funcionalidad no implementada aún");
		} else {
			System.out.println("Estudiante no encontrado");
		}
	}

    @Transactional
	private void deleteBook() {
		System.out.println("\n--- Borrar un Libro ---");
		System.out.print("Ingrese el ISBN del libro a borrar: ");
		String isbn = scanner.nextLine();

		try {
			// Primero, buscamos el libro para obtener su ID (si es necesario)
			Optional<Book> bookToDelete = bookRepository.findById(String.valueOf(Long.valueOf(isbn)));
			if (bookToDelete.isPresent()) {
				bookRepository.delete(bookToDelete.get()); // Borramos el libro
				System.out.println("Libro borrado exitosamente!");
			} else {
				System.out.println("No se encontró ningún libro con ese ISBN.");
			}
		} catch (Exception e) {
			System.out.println("Error al borrar el libro: " + e.getMessage());
		}
	}

	private void displayBooks(List<Book> books) {
		if (books.isEmpty()) {
			System.out.println("No se encontraron libros.");
		} else {
			System.out.println("ISBN\tTítulo\tCategoría\tCantidad");
			for (Book book : books) {
				System.out.println(book.getIsbn() + "\t" + book.getTitle() + "\t" + book.getCategory() + "\t" + book.getQuantity());
			}
		}
	}
}