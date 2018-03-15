/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ajhaarni
 */
public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void rahamaaraOikeaAlussa() {
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void lounaitaEiMyytyAlussa() {
        assertEquals(0, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullinenKateisostoLisaaKassaa() {
        paate.syoEdullisesti(300);
        assertEquals(100240, paate.kassassaRahaa());
    }

    @Test
    public void maukasKateisostoLisaaKassaa() {
        paate.syoMaukkaasti(400);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void vajaaEdullinenKateisostoEiLisaaKassaa() {
        paate.syoEdullisesti(100);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void vajaaMaukasKateisostoEiLisaaKassaa() {
        paate.syoMaukkaasti(100);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void edullinenKateisostoAntaaOikeanVaihtorahan() {
        assertEquals(60, paate.syoEdullisesti(300));
    }

    @Test
    public void maukasKateisostoAntaaOikeanVaihtorahan() {
        assertEquals(60, paate.syoMaukkaasti(460));
    }
    
    @Test
    public void edullinenKateisostoMuuttaaMyytyjenmaaraa() {
        paate.syoEdullisesti(500);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasKateisostoMuuttaaMyytyjenmaaraa() {
        paate.syoMaukkaasti(500);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void epaonnistunutEdullinenKateisostoEiMuutaMyytyjenMaaraa() {
        paate.syoEdullisesti(100);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void epaonnistunutMaukasKateisostoEiMuutaMyytyjenMaaraa() {
        paate.syoMaukkaasti(100);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullinenOstoliianVahanRahaaPalauttaaKaiken() {
        assertEquals(200, paate.syoEdullisesti(200));
    }
    
    @Test
    public void maukasOstoliianVahanRahaaPalauttaaKaiken() {
        assertEquals(200, paate.syoMaukkaasti(200));
    }
    
    @Test
    public void edullinenKorttiostoPalauttaaTrueJosKortillaRahaa() {
        assertEquals(true, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukasKorttiostoPalauttaaTrueJosKortillaRahaa() {
        assertEquals(true, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void edullinenKorttiostoPalauttaaFalseJosKortillaEiRahaa() {
        assertEquals(false, paate.syoEdullisesti(new Maksukortti(100)));
    }
    
    @Test
    public void maukasKorttiostoPalauttaaFalseJosKortillaEiRahaa() {
        assertEquals(false, paate.syoMaukkaasti(new Maksukortti(100)));
    }
    
    @Test
    public void edullinenKorttiostoMuuttaaKortinSaldoa() {
        paate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void maukasKorttiostoMuuttaaKortinSaldoa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void edullinenLiianVahanRahaaEiMuutaKortinSaldoa() {
        kortti = new Maksukortti(100);
        paate.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void maukasLiianVahanRahaaEiMuutaKortinSaldoa() {
        kortti = new Maksukortti(100);
        paate.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void edullinenKorttiostoLisaaMyytyja() {
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasKorttiostoLisaaMyytyja() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());

    }
    
    @Test
    public void kassaEiMuutuKorttiostossaEdullinen() {
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kassaEiMuutuKorttiostossaMaukas() {
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kassanMaaraMuuttuuLadatessa() {
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa());
    }
    
    @Test
    public void kortinSaldoMuuttuuLadatessa() {
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
    }
    
    @Test
    public void kortinSaldoEiMuutuJosNegatiivinenLataus() {
        paate.lataaRahaaKortille(kortti, -1000);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void kassaEiMuutuJosNegatiivinenLataus() {
        paate.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000, paate.kassassaRahaa());
    }
    

}
