package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void konstruktoriAlustaaSaldonOikein() {
        assertEquals(1000, kortti.saldo());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(100);
        assertEquals(1100, kortti.saldo());
    }

    @Test
    public void saldoVaheneeKunRahaaOtetaan() {
        kortti.otaRahaa(500);
        assertEquals(500, kortti.saldo());
    }

    @Test
    public void saldoEiVaheneJosOtetaanLiikaa() {
        kortti.otaRahaa(10000);
        assertEquals(1000, kortti.saldo());
    }

    @Test
    public void otaRahaaPalauttaaTrueJosOnnistuu() {
        assertEquals(true, kortti.otaRahaa(50));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosEiOnnistu() {
        assertEquals(false, kortti.otaRahaa(50000));
    }
    
    @Test
    public void toStringPalauttaaOikeanMerkkijonon() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    

}
