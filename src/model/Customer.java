package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class Customer {

    public void setId(int id){

        this.id = id;
    }

    public void setUsername(String username){

        this.username = username;
    }

    public void setNome(String nome){

        this.nome = nome;
    }

    public void setCognome(String cognome) {

        this.cognome = cognome;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setPassword(String password) {

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));

            this.passwordhash = String.format("%040x", new BigInteger(1, digest.digest()));

        } catch(NoSuchAlgorithmException e) {

            throw new RuntimeException(e);
        }
    }

    public void setAmministratore(boolean val) {

        this.amministratore = val;
    }

    public int getId(){

        return id;
    }

    public String getUsername(){

        return username;
    }

    public String getNome(){

        return nome;
    }

    public String getCognome(){

        return cognome;
    }

    public String getEmail(){

        return email;
    }

    public String getPassword(){

        return passwordhash;
    }

    public boolean isAmministratore() {

        return amministratore;
    }

    private String username, nome, cognome, email, passwordhash;
    private int id;
    boolean amministratore;
}
