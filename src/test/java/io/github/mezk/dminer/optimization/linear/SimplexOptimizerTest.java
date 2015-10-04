package io.github.mezk.dminer.optimization.linear;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.junit.Assert;
import org.junit.Test;

public class SimplexOptimizerTest {

    @Test
    public void testOptimizeExample1() {
        final LinearObjectiveFunction function = new LinearObjectiveFunction(new
            double[] { 4, 2 }, 0);
        final Collection<LinearConstraint> constraints = new ArrayList<>();
        constraints.add(new LinearConstraint(new double[] { 1, 2, },
            Relationship.LEQ,  16));
        constraints.add(new LinearConstraint(new double[] { 1, 1, },
            Relationship.LEQ,  10));
        constraints.add(new LinearConstraint(new double[] { 3, 1, },
            Relationship.LEQ,  24));

        final SimplexOptimizer optimizer = new SimplexOptimizer(
            function, constraints, GoalType.MAXIMIZE, 100, true);
        final PointValuePair solution = optimizer.optimize();

        final double[] expecteds = {7.0, 3.0};
        final double[] actuals = solution.getPoint();

        Assert.assertArrayEquals(expecteds, actuals, 0.000000000000001);
        Assert.assertEquals(34.0, function.value(actuals), Double.MIN_NORMAL);
    }

    @Test
    public void testOptimizeExample2() {
        final LinearObjectiveFunction function = new LinearObjectiveFunction(new
            double[] { 4, 5, 4 }, 0);
        final Collection<LinearConstraint> constraints = new ArrayList<>();
        constraints.add(new LinearConstraint(new double[] { 2, 3, 6 },
            Relationship.LEQ,  240));
        constraints.add(new LinearConstraint(new double[] { 4, 2, 4 },
            Relationship.LEQ,  200));
        constraints.add(new LinearConstraint(new double[] { 4, 6, 8 },
            Relationship.LEQ,  160));

        final SimplexOptimizer optimizer = new SimplexOptimizer(
            function, constraints, GoalType.MAXIMIZE, 100);
        final PointValuePair solution = optimizer.optimize();

        final double[] expecteds = { 40.0, 0.0, 0.0};
        final double[] actuals = solution.getPoint();

        Assert.assertArrayEquals(expecteds, actuals, 0.00000000000001);
        Assert.assertEquals(160.0, function.value(actuals), 0.0000000000001);
    }

    @Test
    public void testOptimizeExample3() {
        final LinearObjectiveFunction function = new LinearObjectiveFunction(new
            double[] { 3, 5, 4 }, 0);
        final Collection<LinearConstraint> constraints = new ArrayList<>();
        constraints.add(new LinearConstraint(new double[] { 0.1, 0.2, 0.4 },
            Relationship.LEQ,  1100));
        constraints.add(new LinearConstraint(new double[] { 0.05, 0.02, 0.02 },
            Relationship.LEQ,  120));
        constraints.add(new LinearConstraint(new double[] { 3, 1, 2 },
            Relationship.LEQ,  8000));

        final SimplexOptimizer optimizer = new SimplexOptimizer(
            function, constraints, GoalType.MAXIMIZE, true);
        final PointValuePair solution = optimizer.optimize();

        final double[] expecteds = { 250.0, 5375.0, 0.0};
        final double[] actuals = solution.getPoint();

        Assert.assertArrayEquals(expecteds, actuals, 0.00000000000001);
        Assert.assertEquals(27625.0, function.value(actuals), 0.0000000000001);
    }

}
