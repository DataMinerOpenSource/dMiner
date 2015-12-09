package io.github.mezk.dminer.genetic;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.BinaryChromosome;
import org.apache.commons.math3.genetics.BinaryMutation;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ElitisticListPopulation;
import org.apache.commons.math3.genetics.FixedGenerationCount;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.OnePointCrossover;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.StoppingCondition;
import org.apache.commons.math3.genetics.TournamentSelection;

import io.github.mezk.dminer.utils.BinaryUtils;
import io.github.mezk.dminer.utils.LinearAlgebraUtils;

/**
 * @author selkin
 * @ created 06.12.15
 * @ $Author$
 * @ $Revision$
 */
public class GeneticAlg {

    // Default parameters for the GA
    private int dimension = 10;
    private int populationSize = 50;
    private int maxGenerationsCount = 50;
    private double elitismRate = 0.2;
    private double crossoverRate = 1;
    private double mutationRate = 0.1;
    private int tournamentArity = 2;
    public static String function;
    private int constraint = -10;
    private int objective = 2; // 1 - max, 2 - min

    private Population initial;
    private Population finalPopulation;
    private Chromosome bestFinal;

    public Population getInitial() {
        return initial;
    }

    public Population getFinalPopulation() {
        return finalPopulation;
    }

    public Chromosome getBestFinal() {
        return bestFinal;
    }

    public static void main(String[] args) {
        GeneticAlg c = GeneticAlg.newBuilder()
            .dimension(10)
            .populationSize(50)
            .maxGenerationsCount(50)
            .elitismRate(0.2)
            .crossoverRate(1)
            .mutationRate(0.1)
            .tournamentArity(2)
            .function("2*%d+4")
            .constraint(-10)
            .objective(2)
            .build();
        c.run();
    }

    public void run() {
        // initialize a new genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(
                new OnePointCrossover<Integer>(),
                crossoverRate,
                new BinaryMutation(),
                mutationRate,
                new TournamentSelection(tournamentArity)
        );

        // initial population
        initial = randomPopulation();
        // stopping conditions
        StoppingCondition stopCond = new FixedGenerationCount(maxGenerationsCount);

        // best initial chromosome
        Chromosome bestInitial = initial.getFittestChromosome();

        // run the algorithm
        finalPopulation = ga.evolve(initial, stopCond);

        // best chromosome from the final population
        bestFinal = finalPopulation.getFittestChromosome();
        System.out.println(((BinChromosome)bestFinal).getX());
        System.out.println(objective == 1 ? bestFinal.getFitness() : -bestFinal.getFitness());

        // the only thing we can test is whether the final solution is not worse than the initial one
        // however, for some implementations of GA, this need not be true :)
    }

    /**
     * Initializes a random population.
     */
    private ElitisticListPopulation randomPopulation() {
        List<Chromosome> popList = new LinkedList();
        for (int i = 0; i < populationSize; i++) {
            BinChromosome randChrom =
                new BinChromosome(BinaryChromosome.randomBinaryRepresentation(dimension));
            popList.add(randChrom);
        }
        return new ElitisticListPopulation(popList, popList.size(), elitismRate);
    }


    private class BinChromosome extends BinaryChromosome {

        private int getX() {
            return x;
        }

        private int x;

        public BinChromosome(List<Integer> representation) {
            super(representation);
        }

        /**
         * Returns number of elements != 0
         */
        @Override
        public double fitness() {
            String s = "";
            for (int val : this.getRepresentation()) {
                s += val;
            }
            x = BinaryUtils.convertToIntegerFromBinary(s);


            switch (objective) {
                case 1:
                    if (x > constraint) {
                        return Double.MIN_VALUE;
                    }
                    break;
                case 2:
                    if (x < constraint) {
                        return Double.MIN_VALUE;
                    }
                    break;
            }

//            System.out.println("x = " + x);
//            System.out.println("F = " + (2 * x + 4));
            String formattedFunction = String.format(function, x);
            return objective == 1 ? (Double.valueOf(LinearAlgebraUtils.exec(formattedFunction))) : -(Double.valueOf(LinearAlgebraUtils.exec(formattedFunction)));
        }

        @Override
        public AbstractListChromosome<Integer> newFixedLengthChromosome(List chromosomeRepresentation) {
            return new BinChromosome(chromosomeRepresentation);
        }

    }

    public static Builder newBuilder() {
        return new GeneticAlg().new Builder();
    }

    final class Builder {

        private Builder() {}

        Builder dimension(int val) {
            dimension = val;
            return this;
        }

        Builder populationSize(int val) {
            populationSize = val;
            return this;
        }

        Builder maxGenerationsCount(int val) {
            maxGenerationsCount = val;
            return this;
        }

        Builder elitismRate(double val) {
            elitismRate = val;
            return this;
        }

        Builder crossoverRate(int val) {
            crossoverRate = val;
            return this;
        }

        Builder mutationRate(double val) {
            mutationRate = val;
            return this;
        }

        Builder tournamentArity(int val) {
            tournamentArity = val;
            return this;
        }

        Builder constraint(int val) {
            constraint = val;
            return this;
        }

        Builder function(String val) {
            function = val;
            return this;
        }

        Builder objective(int val) {
            objective = val;
            return this;
        }

        GeneticAlg build() {
            return GeneticAlg.this;
        }
    }
}
