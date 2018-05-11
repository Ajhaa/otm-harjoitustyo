# Rakenne
Ohjelmassa on kolme tasoa: tietokannan kanssa kommunikoiva db-pakkaus, sovelluslogiikasta vastaava domain ja käyttöliittymästä vastaava ui.  
<img src="https://yuml.me/5761a52c.png">

# Käyttöliittymä

Käyttöliittymässä ensimmäisen on mahdollisuus tunnuksen luomiseen ja kirjautumiseen. Kirjautuminen tapahtuu pelkällä tunnuksella, salasanoja ei ole. Kirjautumisen jälkeen käyttäjälle näkyy sovelluksen pääruutu. Siinä on mahdollista lisätä uusia tuloksia, sekä siirtyä historia- ja tilastonäkymään. Uloskirjautuminen siirtää takaisin kirjautumisruutuun.  

Jokainen eri ruuduista on oma scene, jonka palauttaa oma luokkansa. Vaihtaminen toimii UserInterface-luokan avulla, jonka metodeilla stagen sceneä vaihdetaan. Stage ei siis ikinä vaihdu.

# Sovelluslogiikka 
Sovelluslogiikka pyörii Account-olioiden ympärillä. AccountManager-olio kontrolloi suurinta osaa sovelluslogiikasta. Se pitää oliomuuttujassa kirjaa nykyisestä, kirjautuneena olevasta tunnuksesta. Sillä on myös lista kaikista tunnuksista, jotka se saa käynnistäessä tietokannasta.  

Sisäänkirjautuminen tapahtuu AccountManagerin login-metodilla. Sille annetaan parametriksi nimi, ja jos kirjautuminen onnistuu, se palauttaa true, sekä asettaa kyseisen käyttäjän nykyiseksi käyttäjäksi. Uloskirjautuessa nykyinen käyttäjä asetetaan nulliksi.  

AccountManager hoitaa myös uusien käyttäjien luomisen metodilla createAccount, sekä uusien tulosten lisäämisen nykyiselle tunnukselle metodilla addResult.  

Pelien tulokset tallennetaan GameResult-luokan avulla, joka itsessään käyttää muutamaa apuluokkaa. Näitä ovat Rank(pelaajan sijoitus kyseisen pelin jälkeen) result(joko voitto tai häviö) sekä champion(pelaajan pelissä käyttämä hahmo).    

Tilastodatan rakentamisesta huolehtii luokka StatisticsGenerator. Sen metodit palauttavat erilaista dataa pelaajan peleistä.  
Dataa rakennetaan pelaajan sijoituksen kehityksestä, pelatuista hahmoista, sekä voittoprosentista.







# Pysyväistallennus
Käyttäjät ja pelien tulokset tallenetaan sqlite-tietokantaan. Tietokannassa on kaksi taulua.   
<img src="https://yuml.me/06e7c847.png">  
Tietokantayhteydestä ja kannan luomisesta vastaa luokka Database. Itse tallennus- ja hakuoperaatioista vastaavat luokat AccountDao ja ResultDao. Ohjelma lataa kaikki käyttäjät ja tulokset aluksi, ja lisää uudet sekä ohjelmallisesti että tietokantaan.

# Päätoiminnallisuudet
Muutama päätoiminnallisuus sekvenssikaaviona ja tekstikuvauksena.
## Sisäänkirjautuminen
Kun käyttäjä kirjautuu sisään, AccountManager tarkistaa, onko käyttäjätunnusta olemassa. Jos on, se asetetaan AccountManagerin aktiiviseksi käyttäjäksi, ja scene vaihdetaan pääruutuun
<img src="https://i.imgur.com/oZgLAsx.png">

## Käyttäjätunnuksen luonti
Kun käyttäjätunnus luodaan, AccountManager tarkistaa, onko käyttäjää olemassa. Jos ei ole, luodaan uusi käyttäjä, joka tallennetaan sekä tietokantaan AccountDaon kautta, että AccountManagerin listaan.

## Käyttäjätunnuksen poisto
POistettava käyttäjätunnus poistetaan sekä tietokannasta että AccountManagerin listasta. Tietokannasta poistetaan myös käyttäjään liittyvät pelitulokset.

## Hahmotilastojen haku
<img src="https://i.imgur.com/1Fsx9Vq.png">  

## Otteluhistorian haku

Kun käyttäjä painaa 'match history' -nappia, sovellus toimii seuraavasti.
<img src="https://i.imgur.com/YZoY5rR.png">



