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
	        SalarieAideADomicile salarie = new SalarieAideADomicile();
	        salarie.setJoursTravaillesAnneeNMoins1(10);
	        assertFalse(salarie.aLegalementDroitADesCongesPayes(), "Ne devrait pas avoir droit aux congés");
	    }
	    @Test
	    void testALegalementDroitADesCongesPayesCasLimiteMoins10Jours() {
	        SalarieAideADomicile salarie = new SalarieAideADomicile();
	        salarie.setJoursTravaillesAnneeNMoins1(5);
	        assertFalse(salarie.aLegalementDroitADesCongesPayes(), "Ne devrait pas avoir droit aux congés");
	    }

	  
	
        
        
        


}
