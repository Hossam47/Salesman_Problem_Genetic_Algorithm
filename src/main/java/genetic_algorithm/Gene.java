package genetic_algorithm;

import sheets.GeneSheet;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Gene implements GeneSheet {

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


    @Override
    public Float getGeneDistance(Gene gene) {
        return (float) sqrt(pow(getExon() - gene.getExon(), 2) + pow(getIntron() - gene.getIntron(), 2));
    }

    @Override
    public String showGeneDetails() {
        return "(" + this.exon + ", " + this.intron + ")";
    }
}
