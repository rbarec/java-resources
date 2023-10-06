package rbarec.springboot3zero.books;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *1./ If we add web request annotations to the controller, they’ll take precedence
 * over the interface’s ones. In other words,
 * 
 * @author Ronald
 *
 */
@RestController
@RequestMapping("/book") // class-level annotation
public class BookController implements BookOperations {

	@Override
	public List<Book> getAll() {
		return BookSampleData.getBookList();
	}

	@Override
	public Optional<Book> getById(int id) {
		return Optional.of(BookSampleData.getBookList().get(0));
	}

	@Override
	public void save(Book book, int id) {
		BookSampleData.getBookList().add(book);
	}

}
