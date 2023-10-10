package rbarec.fundationsjava8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import rbarec.data.GenderEnum;
import rbarec.data.Person;
import rbarec.data.PersonSampleData;

public class PersonStreamExamples01 {

	public static void main(String[] args) {
		List<String> names = new ArrayList<String>();
		List<Person> people = PersonSampleData.getPersonList();
		System.out.println("Number People: " + people.size());
		//
		System.out.println("Stream-1: ");
		people.stream().filter(s -> {
			System.out.println("filter: " + s);
			return true;
		});
		System.out.println("Stream-2: ");
		people.stream().filter(p -> p.getGender() == GenderEnum.MASCULINO).map(Person::getNames)
				.map(String::toUpperCase).forEach(name -> names.add(name));

		System.out.println("Stream-3: ");
		List<Person> people3 = people.stream()//
				.filter(p -> p.getGender() == GenderEnum.FEMENINNO)//
			//	.map(Person::getNames)//
			//	.map(String::toUpperCase)//
				.collect(Collectors.toList());//
		System.out.println(people3);

		System.out.println("Stream-4: to map! ");
//		Map<String, Person> namesPerson = ;
				System.out.println(  people.stream()
				.collect(Collectors.toMap(p -> p.getNames(), p -> p))); 
				
				
				Map<GenderEnum, List<Person>> dishesByType =
						people.stream().collect(groupingBy(Person::getGender));		
		
	}
}
