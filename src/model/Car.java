package model;

import java.text.NumberFormat;
import java.util.Locale;

public class Car {

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public void setCasaAuto(String casaAuto) {

        this.casaAuto = casaAuto;

    }

    public String getCasaAuto() {

        return casaAuto;
    }

    public String getAlimentazione() {

        return alimentazione;
    }

    public void setAlimentazione(String alimentazione) {

        this.alimentazione = alimentazione;
    }

    public String getTrasmissione() {

        return trasmissione;
    }

    public void setTrasmissione(String trasmissione) {

        this.trasmissione = trasmissione;
    }

    public String getCategoria() {

        return categoria;
    }

    public void setCategoria(String categoria) {

        this.categoria = categoria;
    }

    public String getTrazione() {

        return trazione;
    }

    public void setTrazione(String trazione) {

        this.trazione = trazione;
    }

    public double getLunghezza() {

        return lunghezza;
    }

    public void setLunghezza(double lunghezza) {

        this.lunghezza = lunghezza;
    }

    public double getLarghezza() {

        return larghezza;
    }

    public void setLarghezza(double larghezza) {

        this.larghezza = larghezza;
    }

    public double getAltezza() {

        return altezza;
    }

    public void setAltezza(double altezza) {

        this.altezza = altezza;
    }

    public double getAccelerazione() {

        return accelerazione;
    }

    public void setAccelerazione(double accelerazione) {

        this.accelerazione = accelerazione;
    }

    public String getPrezzo() {

        return prezzo;
    }

    public void setPrezzo(double prezzo) {

        this.prezzoDouble = prezzo;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
        this.prezzo = currencyFormatter.format(prezzo);
    }

    public double getPrezzoDouble(){

        return this.prezzoDouble;
    }

    public int getPorte() {

        return porte;
    }

    public void setPorte(int porte) {

        this.porte = porte;
    }

    public int getPosti() {

        return posti;
    }

    public void setPosti(int posti) {

        this.posti = posti;
    }

    public int getPeso() {

        return peso;
    }

    public void setPeso(int peso) {

        this.peso = peso;
    }

    public int getCilindri() {

        return cilindri;
    }

    public void setCilindri(int cilindri) {

        this.cilindri = cilindri;
    }

    public int getCilindrata() {

        return cilindrata;
    }

    public void setCilindrata(int cilindrata) {

        this.cilindrata = cilindrata;
    }

    public int getPotenza() {

        return potenza;
    }

    public void setPotenza(int potenza) {

        this.potenza = potenza;
    }

    public int getRapporti() {

        return rapporti;
    }

    public void setRapporti(int rapporti) {

        this.rapporti = rapporti;
    }


    public int getVelMax() {

        return velMax;
    }

    public void setVelMax(int velMax) {

        this.velMax = velMax;
    }

    public int getEmissioneCO2() {

        return emissioneCO2;
    }

    public void setEmissioneCO2(int emissioneCO2) {

        this.emissioneCO2 = emissioneCO2;
    }

    public int getkW() {

        return kW;
    }

    public void setkW(int kW) {

        this.kW = kW;
    }

    public String getPath() {

        return path;
    }

    public void setPath(String directory, String specifico, String estensione) {

        path = directory + nome.toLowerCase().replaceAll(" ", "") +
                specifico.toLowerCase() + estensione;
    }
    private double prezzoDouble;
    private String nome, casaAuto, alimentazione, trasmissione, categoria, trazione, path, prezzo;
    private double lunghezza, larghezza, altezza, accelerazione;
    private int porte, posti, peso, cilindri, cilindrata, potenza, rapporti, velMax, emissioneCO2, kW;
}
