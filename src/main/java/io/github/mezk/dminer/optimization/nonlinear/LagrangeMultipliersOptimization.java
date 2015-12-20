package io.github.mezk.dminer.optimization.nonlinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import io.github.mezk.dminer.optimization.OptimizationData;
import javacalculus.core.CALC;
import javacalculus.core.CalcParser;
import javacalculus.core.CalculusEngine;
import javacalculus.struct.CalcObject;

/**
 * @author selkin
 * @ created 15.11.15
 * @ $Author$
 * @ $Revision$
 */
public class LagrangeMultipliersOptimization implements OptimizationData {

    public static final Pattern REGEX = Pattern.compile("^\\-?[0-9]+(\\+|\\-).*$");

    @Override
    public PointValuePair optimize() {

        return null;
    }

    private static String buildLagrangeFunction(String function, List<String> constraints) {
        final StringBuilder lagrangeFunction = new StringBuilder();
        lagrangeFunction.append(function);
        lagrangeFunction.append("+");

        for (int i = 0; i < constraints.size(); i++) {
            final String constraint = constraints.get(i);
            final int equalIndex = constraint.indexOf('=');
            final String bVal = constraint.substring(equalIndex + 1);
            final String gVal = constraint.substring(0, equalIndex);
            lagrangeFunction.append("lambda");
            lagrangeFunction.append(i + 1);
            lagrangeFunction.append("*");
            lagrangeFunction.append("(");
            lagrangeFunction.append(bVal);
            lagrangeFunction.append("-");
            lagrangeFunction.append("(");
            lagrangeFunction.append(gVal);
            lagrangeFunction.append("))");
        }
        return lagrangeFunction.toString();
    }

    public static String getDiff( String expression, String variable ) {
        String command = "DIFF(" + expression + ", " + variable + ")";
        return eval(command);
    }

    public static String getAdd( String expr1, String expr2 ) {
        String command = "ADD(" + expr1 + ", " + expr2 + ")";
        return eval( command );
    }

    private static String eval( String command ) {
        CalcObject result = null;
        try {
            result = new CalcParser().parse(command).evaluate();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        result = CALC.SYM_EVAL(result);
        return findOutLine( new CalculusEngine().execute( result.toString() ) );
    }

    public static String exec( String expr ){
        return findOutLine( new CalculusEngine().execute( expr) );
    }

    private static String findOutLine( String input ) {
        Matcher m = Pattern.compile("Output:.*").matcher( input );
        if ( m.find() ) {
            return input.substring( m.start(), m.end() ).replace( "Output: ", "" );
        }
        return null;
    }

    /**
     *
     * @param equation
     * @return the array. First element is the right part of canonical equation.
     *         Second element is constant.
     */
    private static String[] convertToCanonical(String equation) {
       String[] result = new String[2];

        Matcher matcher = REGEX.matcher(equation);

        if (matcher.matches()) {
            int plusIndex = equation.indexOf('+');
            int minusIndex = equation.indexOf('-');
            final int substringIdnex;
            if (minusIndex == 0) {
                minusIndex = equation.indexOf('-', 1);
            }
            if (plusIndex < minusIndex && plusIndex > 0) {
                substringIdnex = plusIndex;
            }
            else {
                substringIdnex = minusIndex;
            }


            if (substringIdnex == plusIndex) {
                result[0] = equation.substring(substringIdnex + 1);
            }
            else {
                result[0] = equation.substring(substringIdnex);
            }
            String constant = equation.substring(0, substringIdnex);
            if (constant.charAt(0) == '-') {
                result[1] = constant.substring(1);
            }
            else {
                result[1] = '-' + constant;
            }
        }
        else {
            result[0] = equation;
            result[1] = "0";
        }

        return result;

    }

    private static double[] getPolynomialCoefficients(String canonicalForm) {
        List<Double> coefficients = new ArrayList<>();

        int firstBraceIndex = canonicalForm.indexOf("(");
        if (firstBraceIndex > 0
                && canonicalForm.charAt(firstBraceIndex - 1) == '-') {
            String temp = canonicalForm.substring(1);
            temp = temp.replaceAll("\\+", "?");
            temp = temp.replaceAll("-", "+");
            temp = temp.replaceAll("\\?", "-");
            temp = temp.replaceAll("\\(", "");
            temp = temp.replaceAll("\\)", "");
            canonicalForm = '-' + temp;
        }

        String[] splitted = canonicalForm.split("((?<=\\+|\\*|\\-|\\/)|(?=\\+|\\*|\\-|\\/))" );


        for (int i = 0; i < splitted.length - 1; i++) {
            if (splitted[i].matches("\\d+")) {
                if (i > 0
                        && splitted[i - 1].equals("-")) {
                    coefficients.add(Double.valueOf("-" + splitted[i]));
                }
                else {
                    coefficients.add(Double.valueOf(splitted[i]));
                }
            }
            else if ((splitted[i].matches("\\+") || i == 0)
                        && splitted[i + 1].startsWith("x")) {
                coefficients.add(1.0);

            }
            else if (splitted[i].matches("\\-")
                        && splitted[i + 1].startsWith("x")) {
                coefficients.add(-1.0);
            }
        }
        return ArrayUtils.toPrimitive(coefficients.toArray(new Double[coefficients.size()]));
    }

    public static void main(String[] args) {
        // Для всех ограничений тупо заменяем >= или <= на = , чтобы функцию Лагранжа построить
        List<String> constraints = new ArrayList<>();
        constraints.add("x1+x2=0");
        final String function = "4*x1+x1^2+8*x2+x2^2+x3";
        String lagrangeFunction = buildLagrangeFunction(function, constraints);
        System.out.println("Функция Лагранжа:" + lagrangeFunction);

        List<String> systemOfEquations = new ArrayList<>();
        // СИстема уравнений ... = 0
        systemOfEquations.add(getDiff(lagrangeFunction, "x1"));
        systemOfEquations.add(getDiff(lagrangeFunction, "x2"));
        systemOfEquations.add(getDiff(lagrangeFunction, "lambda1"));

        GoalType goal = GoalType.MAXIMIZE;

        // число переменных
        int n = 3;
        // Начальное приближение
        double[] initialGuess = {3,2,3};
        // Ограничение снизу по каждой переменной отдельно
        double[] constraintLow = {4,0,0};
        // Ограничение сверху по каждой переменной отдельно
        double[] constraintHigh = {8,6,10};

        List<double[]> possibleResult = new ArrayList<>();
        int maxIter = 10000;
        double[] functionValues = new double[maxIter];

        for (int j = 0; j < maxIter; j++) {
            double[] randomResult = new double[n];
            boolean stop = false;
            while (!stop) {
                for (int i = 0; i < n; i++) {
                    randomResult[i] = Math.random() + initialGuess[i];
                    if (randomResult[i] >= constraintLow[i] && randomResult[i] <= constraintHigh[i]) {
                        stop = true;
                    }
                    else {
                        stop = false;
                    }
                }
            }
            String f = String.valueOf(function);
            for (int i = 0; i < randomResult.length; i++) {
                String x = "x" + (i+1);
                f = f.replace(x, String.valueOf(randomResult[i]));
            }
            functionValues[j] = Double.valueOf(eval(f));
            possibleResult.add(randomResult);
        }

        if (GoalType.MAXIMIZE == goal) {
            double max = functionValues[0];
            int maxIndex = 0;
            for (int i = 0; i < functionValues.length; i++) {
               if (functionValues[i] > max) {
                   max = functionValues[i];
                   maxIndex = i;
                }
            }
            System.out.println("Fmax="+max);
            System.out.println("x="+ Arrays.toString(possibleResult.get(maxIndex)));
        }
        else {
            double min = functionValues[0];
            int minIndex = 0;
            for (int i = 0; i < functionValues.length; i++) {
                if (functionValues[i] < min) {
                    min = functionValues[i];
                    minIndex = i;
                }
            }
            System.out.println("Fmin="+min);
            System.out.println("x="+ Arrays.toString(possibleResult.get(minIndex)));
        }

    }


}
