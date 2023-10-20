package rbarec.numberstoletters.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumerosLetrasResponse {

	private List<String> logs= new ArrayList<>();
	private int errorCounts;
	private boolean verbose=false;
	
	public void add(String log){
		if(verbose)
			logs.add(log);
	}
	
	public void forceAdd(String log){
			logs.add(log);
	}
}
