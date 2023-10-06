package rbarec.springboot3zero.books;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
	private String title;
	private int year;
}
