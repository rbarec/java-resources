package rbarec.springboot3zero.books;

import java.util.ArrayList;
import java.util.List;

public class BookSampleData {

	public static List<Book> getBookList(){
		List<Book> lista = new ArrayList<>();
		lista.add(Book.builder().title("Java Cookbook").year(2022).build());
		lista.add(Book.builder().title("Kubernetes in action").year(2023).build());
		return lista;
	}
}
