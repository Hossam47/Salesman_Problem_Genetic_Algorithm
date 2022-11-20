package utils;

import java.util.Random;

public class GeneralUtils {

    public static final int SCREEN_HEIGHT = 800;
    public static final int SCREEN_WIDTH =1200;

    public static int getRandomGene(int genesNum) {
        return new Random(genesNum).nextInt(genesNum);
    }

    public static int getRandomChromosome(int chromosomeNum) {
        return new Random(chromosomeNum).nextInt(chromosomeNum);
    }
}
