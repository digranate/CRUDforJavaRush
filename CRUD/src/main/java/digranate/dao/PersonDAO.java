package digranate.dao;


import java.util.List;

import digranate.model.Person;

public interface PersonDAO {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons(int pageNumber);
	public Person getPersonById(int id);
	public void removePerson(int id);
	public List<Person> listPersonsFiltered(String name,int pageNumber);
}
