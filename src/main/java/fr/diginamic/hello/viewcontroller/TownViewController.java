package fr.diginamic.hello.viewcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.diginamic.hello.dto.TownDto;
import fr.diginamic.hello.exceptions.CustomEmptyTownExceptions;
import fr.diginamic.hello.service.TownService;

@Controller
@RequestMapping("/view/town")
public class TownViewController {
	
	@Autowired
	TownService townService;
	
	@GetMapping
	public String getTowns(Model model, @PageableDefault(page=0, size=10) Pageable page) throws CustomEmptyTownExceptions {
        model.addAttribute("title", "Towns");
        model.addAttribute("towns", townService.extractTowns(page));
        return "town";
    }
	
	@GetMapping("/getDetail")
    public String getCityDetails(
            Model model,
            @RequestParam Long id) throws CustomEmptyTownExceptions {
        TownDto town = townService.extractTownById(id);
        model.addAttribute("title", town.getTownName());
        model.addAttribute("town", town);
        return "townDetail";
    }
	
	@GetMapping("/deleteTown")
	public String deleteTown(@RequestParam Long id) {
		townService.deleteTownById(id);
		return "redirect:/view/town";
	}

}
