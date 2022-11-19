package model;

import genetic_algorithm.Gene;

import static utils.GeneralUtils.*;

public class DataModel {

    public static final Gene[] COUNTRIES = generateGenesOfCountries(20);

    private static Gene[] generateGenesOfCountries(int countries) {

        final Gene[] data = new Gene[countries];

        for (int i = 0; i < countries; i++) {
            data[i] = new Gene(getRandomGene(SCREEN_HEIGHT - 80),
                    getRandomGene(SCREEN_WIDTH - 80));
        }

        return data;
    }
}
