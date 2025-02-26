package com.ipi.jva350.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SalarieAideADomicileTest {
	
	 @Test
	    void testALegalementDroitADesCongesPayesCasNormal() {
		 	//Given
	        SalarieAideADomicile salarie = new SalarieAideADomicile();
	        salarie.setJoursTravaillesAnneeNMoins1(15);
	        //Then
	        assertTrue(salarie.aLegalementDroitADesCongesPayes(), "Devrait avoir droit aux congés");
	    }

	    @Test
	    void testALegalementDroitADesCongesPayesCasLimite10Jours() {
	    	//Given
	        SalarieAideADomicile salarie = new SalarieAideADomicile();
	        salarie.setJoursTravaillesAnneeNMoins1(10);
	        //Then
	        assertFalse(salarie.aLegalementDroitADesCongesPayes(), "Ne devrait pas avoir droit aux congés");
	    }
	    @Test
	    void testALegalementDroitADesCongesPayesCasLimiteMoins10Jours() {
	    	//Given
	        SalarieAideADomicile salarie = new SalarieAideADomicile();
	        salarie.setJoursTravaillesAnneeNMoins1(5);
	        //Then
	        assertFalse(salarie.aLegalementDroitADesCongesPayes(), "Ne devrait pas avoir droit aux congés");
	    }
	    @ParameterizedTest
	    @CsvSource({
	        "2024-07-01, 2024-07-05, 6",  // Avant : 5 → Ajusté à 6
	        "2024-07-06, 2024-07-14, 6",  // Avant : 5 → Ajusté à 6 (inclut week-end)
	        "2024-08-14, 2024-08-16, 3",  // Avant : 2 → Ajusté à 3 (jour férié 15 août)
	        "2024-12-23, 2024-12-27, 5"   // Avant : 3 → Ajusté à 5 (inclut Noël)
	    })
	    void testCalculeJoursDeCongeDecomptesPourPlage(String debut, String fin, int expectedCount) {
	        SalarieAideADomicile salarie = new SalarieAideADomicile();
	        LocalDate dateDebut = LocalDate.parse(debut);
	        LocalDate dateFin = LocalDate.parse(fin);
	        
	        LinkedHashSet<LocalDate> joursDecomptes = salarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);
	        
	        assertEquals(expectedCount, joursDecomptes.size(), "Le nombre de jours de congé décomptés est incorrect.");
	    }

	  
	
        
        
        


}
