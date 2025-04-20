import Models.Aufgabe
import Models.Status
import Services.inputTest

fun main(args: Array<String>) {
    var aufgabenListe: MutableList<Aufgabe> = mutableListOf()

    do {
        println("1-Aufgaben anzeigen")
        println("2-Aufgabe erstellen")
        println("3-Aufgabe bearbeiten")
        println("4-Aufgabe löschen")
        println("5-Aufgaben filtern")
        println("6-Aufgabe suchen")
        println("7-Schließen")

        var function: Int? = readLine()?.toIntOrNull()
        inputTest(function, aufgabenListe)
        if (function==7){
            println("Bis zum nächten Mal")
            break
        }
    } while (true)


}
