package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;


	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
								PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Author eric = new Author("Eric", "Evans", new HashSet<Book>());
		Book ddd = new Book("Domain Driven Design", new HashSet<Author>(), "1211221");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		authorRepository.save(eric);
		bookRepository.save(ddd);

		Author rod = new Author("rod", "johnson", new HashSet<Book>());
		Book noEJB = new Book("No EJB", new HashSet<Author>(), "1211221");
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);


		Publisher penguin = new Publisher("Penguin", "122 6th Ave New York NY");

		authorRepository.save(rod);
		bookRepository.save(noEJB);
		publisherRepository.save(penguin);

		ddd.setPublisher(penguin);
		noEJB.setPublisher(penguin);
		penguin.getBooks().add(ddd);
		penguin.getBooks().add(noEJB);

		authorRepository.save(rod);
		bookRepository.save(noEJB);
		publisherRepository.save(penguin);

		System.out.println("Started in Bootstrap");
		System.out.println("Number of Books: " + bookRepository.count());
		System.out.println("Number of Authors: " + authorRepository.count());
		System.out.println("Number of Publishers: " + publisherRepository.count());
	}
}
