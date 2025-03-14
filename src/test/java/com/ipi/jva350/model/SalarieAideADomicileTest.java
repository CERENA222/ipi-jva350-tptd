package com.ipi.jva350.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;

public class SalarieAideADomicileTest {
	private SalarieAideADomicileService salarieService;
    private SalarieAideADomicile salarieMock;

    @BeforeEach
    public void setUp() {
        // Création des mocks
        salarieService = new SalarieAideADomicileService();
        salarieMock = mock(SalarieAideADomicile.class);
        
        // Initialisation des comportements
        when(salarieMock.getCongesPayesPris()).thenReturn(new LinkedHashSet<LocalDate>());
        when(salarieMock.getCongesPayesAcquisAnneeN()).thenReturn(12.5);
        when(salarieMock.getCongesPayesAcquisAnneeNMoins1()).thenReturn(10.0);
        when(salarieMock.getJoursTravaillesAnneeN()).thenReturn(200.0);
    }

	
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
	        "2024-07-01, 2024-07-05, 6",  
	        "2024-07-06, 2024-07-14, 6",  
	        "2024-08-14, 2024-08-16, 3", 
	        "2024-12-23, 2024-12-27, 5"   
	    })
	    void testCalculeJoursDeCongeDecomptesPourPlage(String debut, String fin, int expectedCount) {
	        SalarieAideADomicile salarie = new SalarieAideADomicile();
	        LocalDate dateDebut = LocalDate.parse(debut);
	        LocalDate dateFin = LocalDate.parse(fin);
	        
	        LinkedHashSet<LocalDate> joursDecomptes = salarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);
	        
	        assertEquals(expectedCount, joursDecomptes.size(), "Le nombre de jours de congé décomptés est incorrect.");
	    }
	    @Test
	    public void testAjouteConge_Success() throws SalarieException {
	        // Correctly passing both start and end dates
	        LocalDate jourDebut = LocalDate.of(2025, 2, 15);
	        LocalDate jourFin = LocalDate.of(2025, 2, 18);

	        // Simuler que les congés de cette plage sont valides et comptabilisés
	        LinkedHashSet<LocalDate> joursDecomptes = new LinkedHashSet<>();
	        joursDecomptes.add(jourDebut);
	        joursDecomptes.add(jourFin);
	        when(salarieMock.calculeJoursDeCongeDecomptesPourPlage(jourDebut, jourFin)).thenReturn(joursDecomptes);

	        // Exécution de la méthode avec les bonnes valeurs pour jourDebut et jourFin
	        salarieService.ajouteConge(salarieMock, jourDebut, jourFin);

	        // Vérification que la méthode a bien ajouté les congés
	        verify(salarieMock, times(1)).getCongesPayesPris();
	        verify(salarieMock, times(1)).setCongesPayesPris(any(LinkedHashSet.class));
	       
	    }

	  
	
        
        
        


}
