import Models.Aufgabe
import Models.Status
import Services.erstelleAufgabeDirekt
import Services.loescheAufgabeDirekt
import Services.filterAufgabeDirekt

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class AufgabenServiceTest {
    @Test
    fun `aufgabe wird korrekt erstellt`() {
        // Arrange – Testdaten vorbereiten
        val titel = "Hausaufgabe"
        val beschreibung = "AP2 Kapitel 6 lernen"
        val kategorie = "Uni"

        // Act – Funktion aufrufen
        val aufgabe = erstelleAufgabeDirekt(titel, beschreibung, kategorie)

        // Assert – Überprüfen, ob alles stimmt
        assertEquals(titel, aufgabe.titel)
        assertEquals(beschreibung, aufgabe.beschreibung)
        assertEquals(kategorie, aufgabe.kategorie)
        assertEquals(Status.OFFEN, aufgabe.status)
    }
    @Test
    fun `aufgabeWirdKorrektGeloescht`(){
        //Arrange - Testdaten vorbereiten
        val aufgabe1:Aufgabe= Aufgabe("Test","Test der Löschfunktion","Unit Test")
        val testListe : MutableList<Aufgabe> = mutableListOf(aufgabe1)
        //Assert - Überprüfen, ob Eingabe erfolgreich gespeichert

        assertEquals(1, testListe.size)
        //Act - Funktion aufrufen
        testListe.removeAt(0)

        //Assert - Testergebnis überprüfen
        assertEquals(0,testListe.size)
    }
    @Test
    fun `AufgabeWirdKorrektGefiltert`(){

        // Arrange - TestDaten vorbereiten
        val aufgabe1 : Aufgabe = Aufgabe("TestAufgabe", "Test der FilterFunktion", "Unit Test",Status.OFFEN)
        val aufgabe2 : Aufgabe = Aufgabe("TestAufgabe 2", "Test der FilterFunktion", "Unit Test",Status.BEARBEITET)
        val aufgabenListe:MutableList<Aufgabe> = mutableListOf(aufgabe1,aufgabe2)

        //Act - Funktion aufrufen
        val testListe = filterAufgabeDirekt(aufgabenListe)

        // Assert - Testergebnis überprüfen

        assertEquals(1,testListe.size)
        assertTrue(testListe.all { it.status == Status.OFFEN })



    }
}

