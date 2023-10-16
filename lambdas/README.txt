

https://www.baeldung.com/java-stream-filter-lambda

package automation.appbisscontrol;
public class ScrappingLimitControl 
	public List<String> getAllTagsProcess(){
		return generalControl.getAllScrappingProcessActives() 
				.stream()
				  .filter(c -> c.contains(PREFIJO))
				  .collect(Collectors.toList());
	}