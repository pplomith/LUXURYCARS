package model;

public class ProdottoPreferito {


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
    public void setPath(String directory, String specifico, String estensione) {

        path = directory + nomeAuto.toLowerCase().replaceAll(" ", "") +
                specifico.toLowerCase()+ estensione;
    }

    public String getPath() {

        return path;
    }
    private String nomeAuto, casaAuto, path;
    private int idCliente, quantita;
}
