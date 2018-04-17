# Otm harjoitustyö

Tämä on otm harjoitustyö Kotinilla  

[Vaatimusmäärittely](https://github.com/Ajhaa/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusm%C3%A4%C3%A4rittely.md)  
[Tuntikirjanpito](https://github.com/Ajhaa/otm-harjoitustyo/blob/master/dokumentaatio/ty%C3%B6aikakirjanpito.md)


Ohjelma ajetaan kansiossa `/sovellus` komennolla `gradle run`.   
Testit voi ajaa komennolla ```gradle test```. Testit toimivat tällä hetkellä hieman oudosti niin, että onnistuneista testeistä ei tule minkäänlaisia tilastoja, pelkkä viesti build successful. Tilastot näkee komennolla `gradle cleanTest test`.   
Jacoco-raportin saa komennolla `gradle jacocoTestReport`. KTLint ajetaan komennolla ```gradle ktlintCheck```. Ajo ilmoittaa vain virheistä, tyhjä ajo on onnistuminen.
Jos omalla koneella ei ole gradlea asennettuna, sitä voi ajaa `/sovellus` kansion `./gradlew` -scriptillä. Esim `./gradlew run`.
