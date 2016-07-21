package digranate.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import digranate.dao.PersonDAO;
import digranate.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {
	
	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	@Transactional
	public void addPerson(Person p) {

		Date date1 = new Date();
		Calendar gc = new GregorianCalendar();
		gc.setTime(date1);

		gc.set(Calendar.MILLISECOND, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		p.setDateCreated(gc.getTime());

		this.personDAO.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public List<Person> listPersons(int pageNumber) {
		return this.personDAO.listPersons(pageNumber);
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.personDAO.getPersonById(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.personDAO.removePerson(id);
	}

	@Override
	@Transactional
	public List<Person> filterListPersons(String name, int pageNumber)
	{
		return this.personDAO.listPersonsFiltered(name,pageNumber);
	}

}
