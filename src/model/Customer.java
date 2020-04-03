package model;

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

        this.password = password;
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

        return password;
    }

    private String username, nome, cognome, email, password;
    private int id;
}
