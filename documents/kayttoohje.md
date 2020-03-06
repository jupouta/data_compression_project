## Käyttöohje

Ohjelma kysyy ensin, mikä tiedosto halutaan kompressoida. Syötteenä annetaan siis polku tiedoston nimeen ja kyseinen tiedosto kompressoidaan sekä Huffmanin että LZW:n kompressiometodeilla. Tiedoston nimi voidaan siis antaa joko suhteellisena tai absoluuttisena polkuna.

Ohjelma tulostaa aluksi tiedoston koon, jotta tätä voidaan verrata kompressoituihin tiedostoihin. Tämän jälkeen ohjelma tulostaa, milloin kompressiot ovat valmiit ja kuinka isoja kompressoidut tiedostot ovat. Lisäksi ohjelma tulostaa dekompressioiden valmistumisen ja tiedostojen koot, joiden pitäisi olla samat kuin alkuperäisellä tiedostolla.

Ohjelma toimii parhaiten tekstitiedostoilla, joissa on vain tavallisen ASCII-merkistön merkkejä (a-z). Muunlaisia tiedostoja ei siis ole testattu.

Testitiedostot sijaitsevat juuren kansion test_files alla ja niillä voi testata ohjelmaa. Jar-tiedosto sijaitsee juuressa ja sen voi ajaa tavallisesti komennolla
```
java -jar compression_project-1.0-SNAPSHOT-jar-with-dependencies.jar
```

- Miten ohjelma suoritetaan, miten eri toiminnallisuuksia käytetään
- Minkä muotoisia syötteitä ohjelma hyväksyy
- Missä hakemistossa on jar ja ajamiseen tarvittavat testitiedostot.