package rbarec.springboot3zero.books;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Notice that we may have a class-level annotation as well as method-level
 * ones.
 * 
 * @author Ronald
 *
 */
@RequestMapping("/default") // class-level annotation
public interface BookOperations {
	@GetMapping("/") // method-level annotation
	List<Book> getAll();

	@GetMapping("/{id}") // method-level annotation
	Optional<Book> getById(@PathVariable int id);

	@PostMapping("/save/{id}") // method-level annotation
	public void save(@RequestBody Book book, @PathVariable int id);
}
