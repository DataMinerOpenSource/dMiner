package io.github.mezk.dminer.optimization.linear;

import java.util.Collection;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import io.github.mezk.dminer.optimization.OptimizationData;

/**
 * Solves a linear problem using the "Two-Phase Simplex" method.
 *
 * <p>Note: Depending on the problem definition, the default convergence criteria may be too strict,
 * resulting in NoFeasibleSolutionException or TooManyIterationsException. In such a case it is
 * advised to adjust these criteria with more appropriate values, e.g. relaxing the epsilon value.
 *
 * <p>Default convergence criteria:
 *
 * <p>Algorithm convergence: 1e-6
 * Floating-point comparisons: 10 ulp
 * Cut-Off value: 1e-10
 *
 * <p>The cut-off value has been introduced to handle the case of very small pivot elements
 * in the Simplex tableau, as these may lead to numerical instabilities and degeneracy.
 * Potential pivot elements smaller than this value will be treated as if they were zero
 * and are thus not considered by the pivot selection mechanism. The default value is safe
 * for many problems, but may need to be adjusted in case of very small coefficients used
 * in either the LinearConstraint or LinearObjectiveFunction.
 *
 * @author Andrei Selkin
 */
public class SimplexOptimizer implements OptimizationData {

    /**
     * Max iterations default value.
     */
    private static final int MAX_ITERATIONS_DEFAULT_VALUE = 100;

    /**
     * An objective function for a linear optimization problem.
     */
    private LinearObjectiveFunction optimizationFunction;

    /**
     * A linear constraints for a linear optimization problem.
     */
    private Collection<LinearConstraint> constraints;

    /**
     * Goal type for an optimization problem
     * (minimization or maximization of a scalar function).
     */
    private GoalType optimizationGoal;

    /**
     * The maximal number of iterations.
     */
    private int maxIterations = MAX_ITERATIONS_DEFAULT_VALUE;

    /**
     * If true, all the variables must be positive.
     */
    private boolean nonNegativeConstraint = true;

    /**
     * Constructor with 3 parameters.
     * nonNegativeConstraint is set to true by default.
     * maxIterations is set to 100 by default.
     * @param optimizationFunction optimization function.
     * @param constraints constraints.
     * @param optimizationGoal optimization goal.
     */
    public SimplexOptimizer(LinearObjectiveFunction optimizationFunction,
                            Collection<LinearConstraint> constraints,
                            GoalType optimizationGoal) {
        this.optimizationFunction = optimizationFunction;
        this.constraints = constraints;
        this.optimizationGoal = optimizationGoal;
    }

    /**
     * Constructor with 5 parameters.
     * @param optimizationFunction optimization function.
     * @param constraints constraints.
     * @param maxIterations maximal number of iterations.
     * @param optimizationGoal optimization goal.
     * @param nonNegativeConstraint if true, all the variables must be positive.
     */
    public SimplexOptimizer(LinearObjectiveFunction optimizationFunction,
                            Collection<LinearConstraint> constraints,
                            GoalType optimizationGoal,
                            int maxIterations,
                            boolean nonNegativeConstraint) {
        this(optimizationFunction, constraints, optimizationGoal);
        this.maxIterations = maxIterations;
        this.nonNegativeConstraint = nonNegativeConstraint;
    }

    /**
     * Constructor with 4 parameters.
     * nonNegativeConstraint is set to true by default.
     * @param optimizationFunction optimization function.
     * @param constraints constraints.
     * @param optimizationGoal optimization goal.
     * @param maxIterations maximal number of iterations.
     */
    public SimplexOptimizer(LinearObjectiveFunction optimizationFunction,
                            Collection<LinearConstraint> constraints,
                            GoalType optimizationGoal,
                            int maxIterations) {
        this(optimizationFunction, constraints, optimizationGoal);
        this.maxIterations = maxIterations;
    }

    /**
     * Constructor with 4 parameters.
     * maxIterations is set to 100 by default.
     * @param optimizationFunction optimization function.
     * @param constraints constraints.
     * @param optimizationGoal optimization goal.
     * @param nonNegativeConstraint if true, all the variables must be positive.
     */
    public SimplexOptimizer(LinearObjectiveFunction optimizationFunction,
                            Collection<LinearConstraint> constraints,
                            GoalType optimizationGoal,
                            boolean nonNegativeConstraint) {
        this(optimizationFunction, constraints, optimizationGoal);
        this.nonNegativeConstraint = nonNegativeConstraint;
    }

    @Override
    public PointValuePair optimize() {
        final SimplexSolver solver = new SimplexSolver();
        final MaxIter maxIter = new MaxIter(this.maxIterations);
        final LinearConstraintSet constraintSet = new LinearConstraintSet(this.constraints);
        final NonNegativeConstraint restriction =
            new NonNegativeConstraint(this.nonNegativeConstraint);

        final PointValuePair solution = solver.optimize(maxIter, this.optimizationFunction,
            constraintSet, this.optimizationGoal, restriction);
        return solution;
    }
}
