package genetic_algorithm;

import sheets.ChromosomeSheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static utils.GeneralUtils.getRandomGene;

public class Chromosome implements ChromosomeSheet {

    private final ArrayList<Gene> currentChromosome;

    public ArrayList<Gene> getCurrentChromosome() {
        return currentChromosome;
    }

    public Chromosome(ArrayList<Gene> chromosome) {
        this.currentChromosome = chromosome;
    }

    static Chromosome createChromosome(final Gene[] Genes) {
        final ArrayList<Gene> genes = (ArrayList<Gene>) Arrays.asList(Arrays.copyOf(Genes, Genes.length));
        return new Chromosome(genes);
    }

    @Override
    public Chromosome[] initCrossOver(Chromosome neighbourChromosome) {

        List<Gene>[] firstParent = chromosomeDivider(this.currentChromosome);
        List<Gene>[] secondParent = chromosomeDivider(neighbourChromosome.getCurrentChromosome());

        ArrayList<Gene> firstCrossOver = new ArrayList<>(firstParent[0]);
        ArrayList<Gene> secondCrossOver = new ArrayList<>(secondParent[1]);

        for (int i = 0; i < 2; i++) {
            for (Gene gene : secondParent[i]) {
                if (!firstCrossOver.contains(gene)) {
                    firstCrossOver.add(gene);
                }
            }
            for (Gene gene : firstParent[i]) {
                if (!secondCrossOver.contains(gene)) {
                    secondCrossOver.add(gene);
                }
            }
        }

        return new Chromosome[]{
                new Chromosome(firstCrossOver),
                new Chromosome(secondCrossOver)
        };
    }

    @Override
    public Chromosome initChromosomeMutation() {

        ArrayList<Gene> chromosomeMutation = new ArrayList<>(this.currentChromosome);

        int firstGene = getRandomGene(chromosomeMutation.size());
        int secondGene = getRandomGene(chromosomeMutation.size());

        while (firstGene == secondGene) {
            firstGene = getRandomGene(chromosomeMutation.size());
            secondGene = getRandomGene(chromosomeMutation.size());
        }

        Collections.swap(chromosomeMutation, firstGene, secondGene);
        return new Chromosome(chromosomeMutation);
    }

    private List<Gene>[] chromosomeDivider(final List<Gene> chromosome) {
         List<Gene> firstPart = chromosome.subList(0, (chromosome.size() + 1) / 2);
         List<Gene> secondPart = chromosome.subList((chromosome.size() + 1) / 2, chromosome.size());
        return new List[]{firstPart, secondPart};
    }
}
