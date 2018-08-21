package cokr.mmsoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cokr.mmsoft.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WelcomeController {
	private List<User> users = new ArrayList<User>();
	@PostMapping("/create")
	public String create(User user) {
		System.out.println("user : " + user);
		users.add(user);
		return "redirect:/list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", users);
		return "list";
	}

	@RequestMapping("/form")
	public String form(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "form";
	}
	// inject via application.properties
	@Value("${app.welcome.message}")
	private String MESSAGE = "";

	@Value("${app.welcome.title}")
	private String TITLE = "";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("title", TITLE);
		model.put("message", MESSAGE);
		return "index";
	}
	@GetMapping("/helloworld")
	public String welcome(String name, int age, Model model) {
		System.out.println("name = " + name + " age = " + age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "welcome2";
	}
	// test 5xx errors
	@RequestMapping("/5xx")
	public String ServiceUnavailable() {
		throw new RuntimeException("ABC");
	}

}