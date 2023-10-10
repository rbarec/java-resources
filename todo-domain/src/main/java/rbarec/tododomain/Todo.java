package rbarec.tododomain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Todo {

	private Long persistId;
	private Person owner;
	private List<Person> participants;
	private Date date;
	private String notes;
	private StatusEnum status;
	private boolean completed;
	private Date dateCompleted;
	private String topic;
	private Date dateCreated;
}
