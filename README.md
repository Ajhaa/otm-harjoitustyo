# Otm harjoitustyö

Tämä on otm harjoitustyö Kotinilla. Ohjelmalla voi pitää kirjaa Battlerite-pelin matsien tuloksista, ja sijoituksen kehittymisestä.  

[Vaatimusmäärittely](https://github.com/Ajhaa/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusm%C3%A4%C3%A4rittely.md)  
[Tuntikirjanpito](https://github.com/Ajhaa/otm-harjoitustyo/blob/master/dokumentaatio/ty%C3%B6aikakirjanpito.md)  
[Arkkitehtuurikuvaus](https://github.com/Ajhaa/otm-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri.md)  
[Käyttöohje](https://github.com/Ajhaa/otm-harjoitustyo/tree/master/dokumentaatio/kayttoohje.md)
  
[release 0.2](https://github.com/Ajhaa/otm-harjoitustyo/releases/tag/0.2)


Ohjelma ajetaan kansiossa `/sovellus` komennolla `gradle run`. 
  
Testit voi ajaa komennolla ```gradle test```. Jos tilastoja ei näy, ne näkee komennolla `gradle cleanTest test`. 
  
Jacoco-raportin saa komennolla `gradle jacocoTestReport`. 
  
KTLint ajetaan komennolla ```gradle ktlintCheck```. Ajo ilmoittaa vain virheistä, tyhjä ajo on onnistuminen.  
  
Suroitettava jar-tiedosto generoidaan komennolla ```gradle shadowJar```. Se löytyy kansiosta ```build/libs```.  

Dokumentaatio generoidaan komennolla ```gradle dokka```. Lopputulos löytyy kansiosta ```/build/javadoc/otm-18```
  
Jos omalla koneella ei ole gradlea asennettuna, sitä voi ajaa `/sovellus` kansion `./gradlew` -scriptillä. Esim `./gradlew run`.
