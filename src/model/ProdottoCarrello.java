package model;

import java.text.NumberFormat;
import java.util.Locale;

public class ProdottoCarrello {

    public String getNomeAuto() {

        return nomeAuto;
    }

    public void setNomeAuto(String nomeAuto) {

        this.nomeAuto = nomeAuto;
    }

    public String getCasaAuto() {

        return casaAuto;
    }

    public void setPrezzo(double prezzo) {

        this.prezzoDouble = prezzo;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
        this.prezzo = currencyFormatter.format(prezzo);

    }

    public Double getPrezzoDouble(){

        return prezzoDouble;
    }

    public String getPrezzo() {

        return prezzo;
    }

    public void setCasaAuto(String casaAuto) {

        this.casaAuto = casaAuto;
    }

    public int getIdCliente() {

        return idCliente;
    }

    public void setIdCliente(int idCliente) {

        this.idCliente = idCliente;
    }

    public void setQuantita(int quantita) {

        this.quantita = quantita;
    }

    public int getQuantita() {

        return quantita;
    }

    public void setPath(String directory, String specifico, String estensione) {

        path = directory + nomeAuto.toLowerCase().replaceAll(" ", "") +
                specifico.toLowerCase()+ estensione;
    }

    public String getPath() {

        return path;
    }

    private Double prezzoDouble;
    private String nomeAuto, casaAuto, prezzo, path;
    private int idCliente, quantita;
}
