# Rakenne
Ohjelmassa on kolme tasoa: tietokannan kanssa kommunikoiva db-pakkaus, sovelluslogiikasta vastaava domain ja käyttöliittymästä vastaava ui.  
<img src="https://yuml.me/f6c88072.png">

# Käyttöliittymä

Käyttöliittymässä ensimmäisen on mahdollisuus tunnuksen luomiseen ja kirjautumiseen. Kirjautuminen tapahtuu pelkällä tunnuksella, salasanoja ei tällä hetkellä ole. Kirjautumisen jälkeen käyttäjälle näkyy sovelluksen pääruutu. Siinä on mahdollista lisätä uusia tuloksia, sekä siirtyä historia- ja tilastonäkymään. Uloskirjautuminen siirtää takaisin kirjautumisruutuun.  

Jokainen eri ruuduista on oma scene, jonka palauttaa oma luokkansa. Vaihtaminen toimii UserInterface-luokan avulla, jonka metodeilla stagen sceneä vaihdetaan. Stage ei siis ikinä vaihdu.

# Sovelluslogiikka 
Sovelluslogiikka pyörii Account-olioiden ympärillä. AccountManager-olio kontrolloi suurinta osaa sovelluslogiikasta. Se pitää oliomuuttujassa kirjaa nykyisestä, kirjautuneena olevasta tunnuksesta. Sillä on myös lista kaikista tunnuksista, jotka se saa käynnistäessä tietokannasta.  

Sisäänkirjautuminen tapahtuu AccountManagerin login-metodilla. Sille annetaan parametriksi nimi, ja jos kirjautuminen onnistuu, se palauttaa true, sekä asettaa kyseisen käyttäjän nykyiseksi käyttäjäksi. Uloskirjautuessa nykyinen käyttäjä asetetaan nulliksi.  

AccountManager hoitaa myös uusien käyttäjien luomisen metodilla createAccount, sekä uusien tulosten lisäämisen nykyiselle tunnukselle metodilal addResult.  

Pelien tulokset tallennetaan GameResult-luokan avulla, joka itsessään käyttää muutamaa apuluokkaa. Näitä ovat Rank(pelaajan sijoitus kyseisen pelin jälkeen), ja result(joko voitto tai häviö).  

Tilastodatan rakentamisesta huolehtii luokka StatisticsGenerator. Sen ainut metodi getData palauttaa AccountMAnagerilta saatua pelidataa kauniimmassa muodossa.




<img src="https://i.imgur.com/YZoY5rR.png">
