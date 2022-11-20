package utils;

import java.util.Random;

public class GeneralUtils {

    public final static Random random = new Random(10000);
    public static final int SCREEN_HEIGHT = 800;
    public static final int SCREEN_WIDTH = 1000;

    public static int getRandomGene(int genesNum) {
        return random.nextInt(genesNum);
    }

    public static int getRandomChromosome(int chromosomeNum) {
        return random.nextInt(chromosomeNum);
    }
}
