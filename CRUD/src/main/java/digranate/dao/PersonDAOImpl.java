package digranate.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Repository;

import digranate.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
	}

	@Override
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
	}

	private static int pageSize = 7;
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons(int pageNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Person");
		query.setFirstResult(pageSize * (pageNumber - 1));
		query.setMaxResults(pageSize);
		List<Person> personsList = query.list();
		return personsList;
	}

	@Override
	public Person getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Person p = (Person) session.load(Person.class, new Integer(id));
		return p;
	}

	@Override
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
	}



	@Override
	public List<Person> listPersonsFiltered(String name,int pageNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Person.class);
		if (name!=null &!"".equals(name))
			criteria.add(Restrictions.eq("name",name));
		criteria.setFirstResult(pageSize * (pageNumber - 1));
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

}
