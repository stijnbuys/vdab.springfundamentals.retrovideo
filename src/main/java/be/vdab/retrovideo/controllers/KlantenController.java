package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.forms.KlantForm;
import be.vdab.retrovideo.services.KlantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/klanten")
class KlantenController
{
    private List<Klant> klanten;
    KlantService klantService;

    //CONSTRUCTORS
    KlantenController(KlantService klantService)
    {
        this.klantService = klantService;
    }

    @GetMapping()
    public ModelAndView klanten(KlantForm form)
    {
        klanten = klantService.getKlantByFamilieNaam(form.getFamilienaam()); //EFKES 'CO' gehardcodet!!!!
        ModelAndView mav = new ModelAndView("klanten", "klanten", klanten);
        mav.addObject("klantform", new KlantForm(null));
        return mav;
    }

    @GetMapping("klanten/form")
    public ModelAndView vanTotPrijsForm() {
        return new ModelAndView("klantform");

    }

    @GetMapping("klanten")
    public ModelAndView vanTotPrijs(KlantForm form) {
        return new ModelAndView("klanten", "klanten", klantService.getKlantByFamilieNaam(form.getFamilienaam()));
    }

}
