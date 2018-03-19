  
Lyhyt ohje kotlin-kehitykseen, erityisesti otm:n raameissa. Käsitellään gradle ja javafx.  


# Asennus

Kotlinia kannattaa ehdottomasti kehittää IntelliJ Idean avulla ([linkki](https://www.jetbrains.com/idea/download/#section=linux)).
Asentamisen _pitäisi_ onnistua ilman sudo-oikeuksia linuxilla, kunhan osaa käsitellä tar.gz -tiedostoja.

Idean lisäksi muuta ei pitäisi tarvita. Gradlea voi ajaa komentorilviltä projektikansiossa, siitä lisää myöhemmin.

# Projektin luominen

Uusi gradle-projekti luodaan helposti. Idean valikosta file->new->project. Valitse gradle (ei gradle(kts), jos on vaihtoehtona),
ja kielistä Kotlin(java). Tämän jälkeen pitää vielä syöttää organisaationimi ja projektinimi. Organisaationimi voi olla omissa
projekteissa lähes mikä tahansa. Muista asetuksista ei tarvitse välittää, paitsi jos haluat vaihtaa kohdekansiota.
Projekti latailee, hetken, jonka jälkeen ollaan valmiita kehitykseen.

# JavaFx

Lähdekoodi laitetaan src/main/kotlin kansioon. Tehdään nyt erittäin yksinkertainen JavaFx-sovellus tiedostoon 
src/main/kotlin/Main.kt.
Kotlin pyörii jvm:ssä, joten javan kirjastojen käyttö on erittäin helppoa:
~~~~
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage

class Main : Application() {

    override fun start(primaryStage: Stage) {
        primaryStage.scene = (Scene(VBox(), 256.0, 144.0))
        primaryStage.show()
    }
}

~~~~
Koska haluamme heti alusta lähtien ajaa projektia gradlella, ennen ajamista pitää gradleen lisätä application-plugin.
Lisää build.gradle -tiedostoon seuraavat rivit muiden plugin-määrittelyjen alapuolelle
~~~~
apply plugin: 'application'

mainClassName = 'Main' // huom, tähän tiedoston sisällä olevan classin nimi, ei itse tiedoston. 
                       // Jos tiedosto on pakkauksessa, merkitään se 'pakkaus.Luokka'
~~~~

Idea luultavasti ehdottaa koodi-ikkunan oikeassa yläreunassa kotlinin konffaamista tms. tee tämä. Se myös ehdottaa oikeassa
alalaidassa gradlen synkronoimista. Tee myös tämä.

Nyt aplikaatiomme pitäisi olla melkein valmis ajettavaksi gradlen kautta.
Oikean yläkulman "tyhjästä pudotusvalikosta" löytyy vaihtoehto 'edit configurations'.
Avaa konfiguraatioikkuna, ja paina vasemman yläreunan vihreää '+' merkkiä.
Valitse gradle, ja klikkaa gradle project -kentän viereistä kansio-nappia valitaksesi projektisi.
Tasks-kenttään kirjoitetaan juuri asennetun application-pluginin tarjoama task 'run'.
Paina ok, ja nyt ohjelma voidaan käynnistää vihreästä napista. Jos ohjelma valittaa, että main ei löydy, yksi ratkaisu on lisätä
seuraava koodinpätkä mainin start-metodin alle:
~~~~
companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
~~~~


# TODO: testaus, jacoco, lint, pakkaus, kdocs
