package digranate.service;


import java.util.List;

import digranate.model.Person;

public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons(int pageNumber);
	public Person getPersonById(int id);
	public void removePerson(int id);
	public List<Person> filterListPersons(String name,int pageNumber);

}
