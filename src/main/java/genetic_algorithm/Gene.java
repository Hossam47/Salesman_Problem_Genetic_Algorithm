package genetic_algorithm;

import sheets.GeneSheet;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Gene {

    private final int exon;
    private final int intron;

    public Gene(int exon, int intron) {
        this.exon = exon;
        this.intron = intron;
    }

    public int getExon() {
        return exon;
    }

    public int getIntron() {
        return intron;
    }


    public Double getGeneDistance(Gene gene) {
        return  sqrt(pow(getExon() - gene.getExon(), 2)
                + pow(getIntron() - gene.getIntron(), 2));
    }

    @Override
    public String toString() {
        return "(" + this.exon + ", " + this.intron + ")";
    }
}
