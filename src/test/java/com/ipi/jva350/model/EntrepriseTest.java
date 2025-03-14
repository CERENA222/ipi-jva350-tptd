package com.ipi.jva350.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EntrepriseTest {
	@ParameterizedTest
    @CsvSource({
        "2024-03-15, 2024-03-10, 2024-03-20, true",  // Dans la plage
        "2024-03-10, 2024-03-10, 2024-03-20, true",  // Exactement le jour du début
        "2024-03-20, 2024-03-10, 2024-03-20, true",  // Exactement le jour de fin
        "2024-03-09, 2024-03-10, 2024-03-20, false", // Avant la plage
        "2024-03-21, 2024-03-10, 2024-03-20, false"  // Après la plage
    })
    void testEstDansPlage(String dateStr, String debutStr, String finStr, boolean expected) {
        LocalDate date = LocalDate.parse(dateStr);
        LocalDate debut = LocalDate.parse(debutStr);
        LocalDate fin = LocalDate.parse(finStr);
        assertEquals(expected, Entreprise.estDansPlage(date, debut, fin));
    }
	
	@ParameterizedTest
	@CsvSource({
	    "2024-01-01, true",  // Jour de l'an
	    "2024-05-01, true",  // Fête du travail
	    "2024-07-14, true",  // 14 Juillet
	    "2024-12-25, true",  // Noël
	    "2024-02-15, false"  // Jour non férié
	})
	void testEstJourFerie(String dateStr, boolean expected) {
	    LocalDate date = LocalDate.parse(dateStr);
	    assertEquals(expected, Entreprise.estJourFerie(date));
	}
	@ParameterizedTest
	@CsvSource({
	    "2024-07-15, 2024-06-01",
	    "2024-05-15, 2023-06-01",
	    "2023-12-01, 2023-06-01",
	    "2023-01-10, 2022-06-01"
	})
	void testGetPremierJourAnneeDeConges(String inputDate, String expectedDate) {
	    LocalDate input = LocalDate.parse(inputDate);
	    LocalDate expected = LocalDate.parse(expectedDate);
	    assertEquals(expected, Entreprise.getPremierJourAnneeDeConges(input));
	}


}
