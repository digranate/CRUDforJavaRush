package digranate;

import digranate.model.Person;
import digranate.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PersonController {
	
	private PersonService personService;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService ps){
		this.personService = ps;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Model model,HttpServletRequest request, HttpServletResponse response)
	{
		return "redirect:/persons";
	}

		@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model,@RequestParam(required = false, defaultValue = "1") String pageNum,@RequestParam(required = false) String nameFiltered) {
		int pageNumber = Integer.parseInt(pageNum);
		model.addAttribute("person", new Person());
		if (nameFiltered!=null&&!"".equals(nameFiltered))
			model.addAttribute("listPersons", this.personService.filterListPersons(nameFiltered,pageNumber));
        else
		     model.addAttribute("listPersons", this.personService.listPersons(pageNumber));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("filteredValueName", nameFiltered);
		return "person";
	}

	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p){
         System.out.println("tratata");
		if(p.getId() == 0){
			this.personService.addPerson(p);
		}
		else{
			this.personService.updatePerson(p);
		}
		
		return "redirect:/persons";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.personService.removePerson(id);
        return "redirect:/persons";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id,@RequestParam(required = false, defaultValue = "1") String pageNum, Model model){
		int pageNumber = Integer.parseInt(pageNum);
		model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons(pageNumber));
		model.addAttribute("pageNumber", pageNumber);
        return "person";
    }

	@RequestMapping(value = "/person/filter", method = RequestMethod.POST)
	public String filterList(Model model,HttpServletRequest request){

		String name=request.getParameter("filterName");
        String pageNum = request.getParameter("pageNumber");
		int pageNumber = 1;
		try{
			Integer.parseInt(pageNum);
		}
		catch (NumberFormatException e){};
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.personService.filterListPersons(name,pageNumber));
		model.addAttribute("filteredValueName", name);
		model.addAttribute("pageNumber", pageNumber);
		return "person";
	}
	
}
