package fr.diginamic.hello.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexViewController {
	
	@GetMapping
	public String getIndex(Model model) {
		model.addAttribute("toto", "tata");
		return "index";
	}
 
}
