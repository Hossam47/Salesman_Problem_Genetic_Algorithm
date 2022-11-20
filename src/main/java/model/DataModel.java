package model;

import genetic_algorithm.Gene;

import static utils.GeneralUtils.*;

public class DataModel {

    public static final Gene[] COUNTRIES = generateGenesOfCountries(20);

    private static Gene[] generateGenesOfCountries(int countriesNums) {

        Gene[] countries = new Gene[countriesNums];

        for (int i = 0; i < countriesNums; i++) {

            countries[i] = new Gene(
                    getRandomGene(SCREEN_HEIGHT - 80),
                    getRandomGene(SCREEN_WIDTH - 80)
            );
        }
        return countries;
    }
}
