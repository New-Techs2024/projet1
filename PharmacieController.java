package com.jee.projet1.controller;

import com.jee.projet1.model.Medicament;
import com.jee.projet1.repository.MedicamentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Controller
@RequestMapping("/pharmacie")
public class GestionPharmacie {

    private final MedicamentRepository depotMedicament;

    @Autowired
    public GestionPharmacie(MedicamentRepository depotMedicament) {
        this.depotMedicament = depotMedicament;
    }

    @GetMapping("/medicaments/rechercher")
    public String rechercher(@ModelAttribute FormulaireRecherche formulaireRecherche, Model modele) {
        List<Medicament> resultats = depotMedicament.findByNomContainingIgnoreCase(formulaireRecherche.getRecherche());
        modele.addAttribute("medicaments", resultats);
        return "medicaments/liste";
    }

    @GetMapping("/medicaments/ajouter")
    public String afficherFormulaireAjout(Model modele) {
        modele.addAttribute("medicament", new Medicament());
        return "medicaments/formulaire-ajout";
    }

    @PostMapping("/medicaments/ajouter")
    public String ajouter(@ModelAttribute("medicament") Medicament produit, BindingResult result) {
        if (result.hasErrors()) {
            return "medicaments/formulaire-ajout";
        }
        depotMedicament.save(produit);
        return "redirect:/pharmacie/medicaments";
    }

    @PostMapping("/medicaments/supprimer/{identifiant}")
    public String supprimer(@PathVariable Long identifiant) {
        depotMedicament.deleteById(identifiant);
        return "redirect:/pharmacie/medicaments";
    }

    @GetMapping("/medicaments")
    public String afficherListe(Model modele) {
        modele.addAttribute("medicaments", depotMedicament.findAll());
        modele.addAttribute("searchForm", new FormulaireRecherche());
        return "medicaments/liste";
    }

    @GetMapping("")
    public String redirectionAccueil() {
        return "redirect:/pharmacie/medicaments";
    }

    public static class FormulaireRecherche {
        private String recherche;

        public String getRecherche() {
            return recherche;
        }

        public void setRecherche(String recherche) {
            this.recherche = recherche;
        }
    }
}
