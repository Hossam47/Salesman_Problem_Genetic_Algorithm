package sheets;


import genetic_algorithm.Chromosome;

public interface ChromosomeSheet {

    Chromosome[] initCrossOver(Chromosome neighbourChromosome);

    Chromosome initChromosomeMutation();
}
