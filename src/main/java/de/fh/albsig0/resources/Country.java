package de.fh.albsig0.resources;

public class Country implements CovidParent {
    private String name;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;

    @Override
    public String toString() {
        return "Country{"
                + "name='" + name + '\''
                + ", confirmed=" + confirmed
                + ", deaths=" + deaths
                + ", recovered=" + recovered
                + ", active=" + active
                + '}';
    }

    public Country(String name, int confirmed, int deaths, int recovered, int active) {
        this.name = name;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }
    public String getName() {
        return name;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
