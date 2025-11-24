package model;

public class Plads {
    private final int række;
    private final int nr;
    private final int pris;
    private final PladsType pladsType;

    public Plads(int række, int nr, int pris, PladsType pladsType) {
        this.række = række;
        this.nr = nr;
        this.pris = pris;
        this.pladsType = pladsType;
    }

    public int getRække() {
        return række;
    }

    public int getNr() {
        return nr;
    }

    public int getPris() {
        return pris;
    }

    public PladsType getPladsType() {
        return pladsType;
    }

    @Override
    public String toString() {
        return "Plads{" +
                "række=" + række +
                ", nr=" + nr +
                ", pris=" + pris +
                ", pladsType=" + pladsType +
                '}';
    }
}
