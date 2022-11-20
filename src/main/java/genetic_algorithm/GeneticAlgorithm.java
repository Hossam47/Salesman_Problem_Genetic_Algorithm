package genetic_algorithm;

import sheets.AlgorithmSheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static utils.GeneralUtils.getRandomChromosome;

public class GeneticAlgorithm {

    private List<Chromosome> ways;
    private final int algorithmLimitation;


    public GeneticAlgorithm(Gene[] countries, int algorithmLimitation) {
        this.ways = createWaysBetweenCities(countries,algorithmLimitation);
        this.algorithmLimitation = algorithmLimitation;
    }

   public List<Chromosome> getWays() {
        return this.ways;
    }

   public Chromosome getBestWay() {
        return this.ways.get(0);
    }

    private List<Chromosome> createWaysBetweenCities(Gene[] countries, final int limitSize) {

        List<Chromosome> ways = new ArrayList<>();

        for (int i = 0; i < limitSize; i++) {
            Chromosome chromosome = Chromosome.createChromosome(countries);
            ways.add(chromosome);
        }
        return ways;
    }

    public void runGeneticAlgorithm() {
        runCrossOver();
        runMutation();
        runSelection();
    }

    public void runCrossOver() {

        List<Chromosome> newWay = new ArrayList<>();

        for (Chromosome chromosome : this.ways) {

            Chromosome neighbourChromosome = getNeighbourCrossOver(chromosome);
            newWay.addAll(Arrays.asList(chromosome.initCrossOver(neighbourChromosome)));

        }
        this.ways.addAll(newWay);

    }

    public void runMutation() {

         List<Chromosome> mutationWays = new ArrayList<>();

        for (int i = 0; i < this.ways.size(); i++) {
            Chromosome mutationWay = this.ways.get(getRandomChromosome(this.ways.size())).initChromosomeMutation();
            mutationWays.add(mutationWay);

        }
        this.ways.addAll(mutationWays);
    }

    public void runSelection() {
        this.ways.sort(Comparator.comparingDouble(Chromosome::getChromosomeDistance));
        this.ways = this.ways.stream().limit(this.algorithmLimitation).collect(Collectors.toList());

    }

    private Chromosome getNeighbourCrossOver(Chromosome chromosome) {
        Chromosome neighbourChromosome = this.ways.get(getRandomChromosome(this.ways.size()));
        while (chromosome == neighbourChromosome) {
            neighbourChromosome = this.ways.get(getRandomChromosome(this.ways.size()));
        }
        return neighbourChromosome;
    }
}
