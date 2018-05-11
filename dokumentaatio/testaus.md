# Testausdokumentti

Ohjelmaa on testattu sekä automaattitestein junitin ja kotlin testin avulla, sekä manuaalisesti.

## Yksikkö- ja integraatiotestaus
Domain- ja database -luokkia on testattu erinäisin yksikkö- ja integraatiotestein. Domainluokan testauksessa on käytetty tyhjää AccountDao-luokkaa mockina.
Database-testit testaavat sekä AccountDaoa, että ResultDaoa. Tietokantojen testauksessä käytetään erillistä test.db-tiedostoa, joka tyhjennetään testien välissä.
Yksinkertaisia gettereitä ja toString-metodeja ei ole pääosin testattu.
## Testikattavuus
<img src="https://i.imgur.com/CNT6PuA.png">
AccountDaon poisto-ominaisuuden testaus ei jostain syystä onnistunut, mutta manuaalisilla testeillä on todettu, että poisto toimii.

## Järjestelmätestaus
Sovellusta on testattu manuaalisesti linux-koneella. Ohjelma muuttaa mahdolliset exceptionit käyttäjälle näkyväksi, informatiiviseksi popupiksi.
Ohjelma ei testauksen aikana ole kaatunut, eikä se toimi odottamattomasti. Kaikki vastaantulleet virheet on ilmoitettu käyttäjälle oikein popuppina.
Esimerkiksi virheelliset tai tyhjät arvot syöttökentissä ja valinnoissa antavat odotetun popup-viestin.
