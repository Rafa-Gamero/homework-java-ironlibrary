package com.homework.library;

import com.homework.library.models.Book;
import com.homework.library.models.Author;
import com.homework.library.models.Student;
import com.homework.library.models.Issue; // Importamos la entidad Issue
import com.homework.library.repositories.BookRepository;
import com.homework.library.repositories.AuthorRepository;
import com.homework.library.repositories.StudentRepository;
import com.homework.library.repositories.IssueRepository; // Importamos el repositorio IssueRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate; // Importamos LocalDate para las fechas
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@SpringBootApplication
@ComponentScan(basePackages = "com.homework.library")
public class LibraryApplication implements CommandLineRunner {
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final StudentRepository studentRepository;
	private final IssueRepository issueRepository; // Agregamos el IssueRepository
	private final Scanner scanner = new Scanner(System.in);
	@Autowired
	public LibraryApplication(BookRepository bookRepository, AuthorRepository authorRepository, StudentRepository studentRepository, IssueRepository issueRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.studentRepository = studentRepository;
		this.issueRepository = issueRepository; // Inicializamos el IssueRepository
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
			System.out.println("8. Salir");
			System.out.print("Ingrese su opción: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
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
		scanner.nextLine();
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
		System.out.print("Ingrese Fecha de Préstamo (YYYY-MM-DD): ");
		LocalDate issueDate = LocalDate.parse(scanner.nextLine());
		System.out.print("Ingrese Fecha de Devolución (YYYY-MM-DD): ");
		LocalDate returnDate = LocalDate.parse(scanner.nextLine());
		Optional<Book> book = bookRepository.findById(isbn);
		Optional<Student> student = studentRepository.findById(usn);
		if (book.isPresent() && book.get().getQuantity() > 0) {
			// Aseguramos que el estudiante exista o lo creamos si no existe
			if (!student.isPresent()) {
				Student newStudent = new Student(usn, studentName);
				studentRepository.save(newStudent);
				student = Optional.of(newStudent); // Actualizamos la referencia student
			}
			Issue issue = new Issue(issueDate, returnDate, student.get(), book.get());
			issueRepository.save(issue);
			book.get().setQuantity(book.get().getQuantity() - 1);
			bookRepository.save(book.get());
			System.out.println("Libro prestado exitosamente!");
		} else {
			System.out.println("El libro no está disponible.");
		}
	}
	private void listBooksByUsn() {
		System.out.println("\n--- Listar Libros por USN ---");
		System.out.print("Ingrese USN del Estudiante: ");
		String usn = scanner.nextLine();
		Optional<Student> student = studentRepository.findById(usn);
		if (student.isPresent()) {
			System.out.println("USN: " + student.get().getUsn());
			System.out.println("Nombre: " + student.get().getName());
			System.out.println("Libros prestados:");
			List<Issue> issues = issueRepository.findByStudent_Usn(usn); // Buscamos los prestamos por USN
			if (issues.isEmpty()) {
				System.out.println("No hay libros prestados a este estudiante.");
			} else {
				for (Issue issue : issues) {
					System.out.println("- " + issue.getBook().getTitle() + " (Devolución: " + issue.getReturnDate() + ")");
				}
			}
		} else {
			System.out.println("Estudiante no encontrado");
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