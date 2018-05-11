# Käyttöohje

Lataa tiedosto [otm-tracker.jar](https://github.com/Ajhaa/otm-harjoitustyo/releases/tag/0.2)


## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar otm-tracker.jar
```

## Kirjautuminen ja käyttäjän luonti

Sovellus käynnistyy alkunäkymään, jossa voidaan siirtyä käyttäjänluomisruutuun, ja kirjautua sisään.
Uusi käyttäjä luodaan kirjoittamalla sen nimi, sekä valitsemalla alkuranking. Ohjelma kertoo, jos käyttäjänimi on jo varattu.
Kirjautuminen ruudussa olevan tekstikentän, ja napin 'login' avulla


## Tulosten lisääminen
Kirjautumisen jälkeen näkymä siirtyy päänäkymään, jossa voidaan lisätä uusia tuloksia. Tulokseen merkataan, oliko se voitto ("win") vai häviö ("loss"),
, pelistä aiheutunut rankinging muutos(promotion - ranking nousi; demotion - ranking laski; no change - ei muutosta), sekä pelattu hahmo. Kaikki kohdat täytettyä voi uuden tuloksen tallentaa napilla submit.
## Peli-historia
Aiemmat pelatut pelinsä näkee 'match history' nappia painamalla. Jos yhtään peliä ei ole pelattu, ohjelma iloittaa siitä.
## Tilastot
Tilastoja pelaamisestaan näkee napin 'stats' takaa. Tilastonäkymässä näkyy voittoprosentti. Voittoprosentti-tekstiä painamalla näkee oman voittoprosenttinsa kehityksen. Rank statistics -napin takaa näkyy rangkingin kehitystä kuvaava kuvaaja.
Champion statistics -nappi näyttää pelaajan pelattujen hahmojen jakauman.
## Uloskirjautuminen
Pääruudun nappi 'log out' kirjaa käyttäjän ulos.
