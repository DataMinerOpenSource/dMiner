package io.github.mezk.dminer.optimization;

import org.apache.commons.math3.optim.PointValuePair;

/**
 * Interface which implementations will provide functionality
 * needed by the optimizers.
 *
 * @author Andrei Selkin
 */
public interface OptimizationData {

    /**
     * Performs optimization.
     * @return the class which holds a point and the value of an objective function at that point.
     */
    PointValuePair optimize();

}
