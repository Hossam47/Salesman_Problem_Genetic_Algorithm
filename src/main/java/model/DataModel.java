package model;

import genetic_algorithm.Gene;

import static utils.GeneralUtils.*;

public class DataModel {

    public static final Gene[] COUNTRIES = generateGenesOfCountries(40);

    private static Gene[] generateGenesOfCountries(int countriesNums) {

        Gene[] countries = new Gene[countriesNums];

        for (int i = 0; i < countriesNums; i++) {

            countries[i] = new Gene(
                    getRandomGene(SCREEN_WIDTH),
                    getRandomGene(SCREEN_HEIGHT)
            );
        }
        return countries;
    }
}
