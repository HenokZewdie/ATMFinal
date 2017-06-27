package ATMFinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Date;

@Controller
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    String nName;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(Model model, String error, String logout){
        model.addAttribute(new Customer());
        return "/home";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String Login(Model model){
        model.addAttribute(new Customer());
        return "/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String process(@ModelAttribute Customer customer){
        nName=customer.getFullName();
        System.out.print(nName);
        customer.setDate(new Date());
        customer.setBalance(customer.getInitialDeposit());
        customerRepository.save(customer);
        return "redirect:/display";
    }
    @RequestMapping(value = "display", method = RequestMethod.GET)
    public String toSend(@ModelAttribute Customer customer, Model model){

        Iterable<Customer> values = customerRepository.findByFullName(nName);
        model.addAttribute("values", values);
        return "display";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logloginPostin(Customer customer, Principal principal, Model model)
    {
        nName=principal.getName();
        Iterable<Customer> values = customerRepository.findByFullName(nName);
        model.addAttribute("AfterLogin", values);
        return "display";
    }
    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model){
        model.addAttribute(new Customer());
        return "/deposit";
    }
}
