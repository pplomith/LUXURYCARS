package model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ProdottoAcquisto {

    public String getNomeAuto() {

        return nomeAuto;
    }

    public void setNomeAuto(String nomeAuto) {

        this.nomeAuto = nomeAuto;
    }

    public String getCasaAuto() {

        return casaAuto;
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

    public int getIdAcquisto() {

        return idAcquisto;
    }

    public void setIdAcquisto(int idAcquisto) {

        this.idAcquisto = idAcquisto;
    }

    public void setPrezzoTotale(double prezzoTotale){

        this.prezzoTotale = prezzoTotale;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
        this.prezzoTotaleString = currencyFormatter.format(prezzoTotale);
    }

    public void setQuantita(int qt){

        this.quantita = qt;
    }

    public double getPrezzoTotale(){

        return this.prezzoTotale;
    }
    public int getQuantita(){

        return this.quantita;
    }

    public String getPrezzoTotaleString() {

        return this.prezzoTotaleString;
    }

    public void setCurrentData(){

        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Data = format.format(calendar.getTime());
    }

    public void setData(String date){

        this.Data = date;
    }

    public String getData(){

        return this.Data;
    }
    public void setPath(String directory, String specifico, String estensione) {

        path = directory + nomeAuto.toLowerCase().replaceAll(" ", "") +
                specifico.toLowerCase()+ estensione;
    }

    public String getPath() {

        return path;
    }

    private double prezzoTotale;
    private String prezzoTotaleString, Data, path;
    private String nomeAuto, casaAuto;
    private int idCliente, idAcquisto, quantita;
}
