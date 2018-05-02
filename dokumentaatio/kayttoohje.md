# Käyttöohje

Lataa tiedosto [otm-tracker.jar](https://github.com/Ajhaa/otm-harjoitustyo/releases/tag/0.2)


## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar otm-tracker.jar
```

## Kirjautuminen ja käyttäjän luonti

Sovellus käynnistyy alkunäkymään, jossa voidaan luoda uusi käyttäjä, ja kirjautua sisään. Uusi käyttäjä luodaan kirjoittamalla haluttu
käyttäjätunnus ylempään tekstikenttään, ja painamalla 'create profile'.  
Kirjautuminen hoituu alemman tekstikentän, ja napin 'login' avulla


## Tulosten lisääminen
Kirjautumisen jälkeen näkymä siirtyy päänäkymään, jossa voidaan lisätä uusia tuloksia. Tulokseen merkataan, oliko se voitto ("win") vai häviö ("loss"),
sekä pelin jälkeinen ranking.  Kaikki kohdat täytettyä voi uuden tuloksen tallentaa napilla submit. Aikaisemmat pelit näkee näkymästä, joka 
avautuu napista 'match history'. Tilaston rankingin kehityksestä näkee napin 'stats' takaa. Uloskirjautuminen tapahtuu 'log out' -napin
avulla.
