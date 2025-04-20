package Services
import Models.Aufgabe
import Models.Status



fun listeAnzeigen(aufgabenListe : MutableList <Aufgabe>) {

    for ((index,aufgabe) in aufgabenListe.withIndex()) {
        println("${index+1}- Aufgabe: ${aufgabe.titel} Beschreibung: ${aufgabe.beschreibung} Kategorie: ${aufgabe.kategorie} Status: ${aufgabe.status}")
        println("")
    }
}


fun aufgabeErstellen(aufgabenListe:MutableList<Aufgabe>){

        println("📝 Neue Aufgabe erstellen")

        print("👉 Titel: ")
        val titel = readLine() ?: ""

        print("🧾 Beschreibung: ")
        val beschreibung = readLine() ?: ""

        print("📂 Kategorie: ")
        val kategorie = readLine() ?: ""

        val aufgabe = Aufgabe(titel = titel, beschreibung = beschreibung, kategorie = kategorie)
        aufgabenListe.add(aufgabe)

        println("✅ Aufgabe wurde erfolgreich gespeichert!")
    }


fun inputTest(function : Int?, aufgabenListe: MutableList<Aufgabe>){

    when   {
        function == null -> println("ungültige Eingabe")
        function == 1 && aufgabenListe.isEmpty() -> println("Du hast noch keine Aufgaben, erstelle deine erste Aufgabe!")
        function == 1 -> listeAnzeigen(aufgabenListe)
        function == 2 -> aufgabeErstellen(aufgabenListe)
        function == 3 -> bearbeiten(aufgabenListe)
        function == 4 -> loeschen(aufgabenListe)
        function == 5 -> filtern(aufgabenListe)
        function == 6 -> aufgabeSuchen(aufgabenListe)
    }
}


fun bearbeiten(aufgabenListe: MutableList<Aufgabe>){

    if(aufgabenListe.isEmpty()) {
            println("Es sind keine Aufgaben vorhanden")
            println("Erstelle eine neue Aufgabe")
            return
        }

    listeAnzeigen(aufgabenListe)

    println("Welche Aufgabe willst du bearbeiten?")
    var input  = readLine()
    var nummer = input?.toIntOrNull()
    var index = nummer?.minus(1)

    if(index == null || index !in aufgabenListe.indices) {
            println("Die Aufgabe existiert nicht!")
            return
        }
    val aufgabe = aufgabenListe[index]
    println("🔧 Aufgabe bearbeiten (Enter drücken, um Wert zu behalten):")

    print("Neuer Titel [${aufgabe.titel}]: ")
    val neuerTitel = readLine()
    if (!neuerTitel.isNullOrBlank()) aufgabe.titel = neuerTitel

    print("Neue Beschreibung [${aufgabe.beschreibung}]: ")
    val neueBeschreibung = readLine()
    if (!neueBeschreibung.isNullOrBlank())aufgabe.beschreibung=neueBeschreibung

    print("Neue Kategorie [${aufgabe.kategorie}]: ")
    val neueKategorie = readLine()
    if (!neueKategorie.isNullOrBlank())aufgabe.kategorie=neueKategorie

    aufgabe.status= Status.BEARBEITET
    listeAnzeigen(aufgabenListe)
}


fun loeschen(aufgabenListe: MutableList<Aufgabe>){

    if (aufgabenListe.isEmpty()){
        println("Es sind keine Aufgaben vorhanden")
        println("Erstelle deine erste Aufgabe!")
            return
    }
    println("Welche Aufgabe willst du löschen?")
    listeAnzeigen(aufgabenListe)
    var input  = readLine()
    var nummer = input?.toIntOrNull()
    var index = nummer?.minus(1)

    if(index == null || index !in aufgabenListe.indices){
        println("Ungültige Eingabe")
        return
    }else{

    aufgabenListe.removeAt(index)
        println("Aufgabe $nummer wurde gelöscht")
}}


fun filtern(aufgabenListe: MutableList<Aufgabe>){

    println("Nach welchem Status möchtest du filtern?")
    println("Gib *offen* oder *bearbeitet* ein:")
    var input = readLine()
    var wort = input?.toString()
    if(wort == null ||(wort != "offen" && wort != "bearbeitet")){
        println("Ungültige Eingabe")
    }

         if (wort == "offen"){
            println("Offene Aufgaben: ${aufgabenListe.filter { it.status == Status.OFFEN }}")
        }else if (wort == "bearbeitet") {

            println("Bearbeitete Aufgaben: ${aufgabenListe.filter { it.status == Status.BEARBEITET }}")
        }
    }

fun aufgabeSuchen(aufgabenListe: MutableList<Aufgabe>){

    println("Gib ein Wort ein:")
    val input = readLine()
    val wort = input?.toString()
    if (aufgabenListe.isEmpty() || wort == null){
        println("Es sind keine Aufgaben vorhanden")
        return
    }
    if (aufgabenListe.none {
                it.titel.lowercase().contains(wort!!) ||
                        it.beschreibung.lowercase().contains(wort)
            }) {
            println("❌ Keine Aufgabe enthält das Wort '$wort'")
        return
        }
    val treffer = aufgabenListe.filter { it.titel.lowercase().contains(wort!!) || it.beschreibung.lowercase().contains(wort)}
    println("$treffer")
    }

fun erstelleAufgabeDirekt(titel:String,beschreibung :String,kategorie:String): Aufgabe {
    return Aufgabe(titel,beschreibung,kategorie)
}
fun loescheAufgabeDirekt(aufgabenListe: MutableList<Aufgabe>, index : Int):Boolean{
    return if(index in aufgabenListe.indices){
        aufgabenListe.removeAt(index)
        true
    }else{
        false
    }

}
fun filterAufgabeDirekt(aufgabenListe: MutableList<Aufgabe>) : List<Aufgabe> {
    val gefiltert = aufgabenListe.filter { it.status == Status.OFFEN }
    return gefiltert
}