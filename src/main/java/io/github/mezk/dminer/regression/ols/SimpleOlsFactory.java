package io.github.mezk.dminer.regression.ols;

import java.text.DecimalFormat;

public class SimpleOlsFactory {
    public static OrdinaryLeastSquares createOls(int type) {
        OrdinaryLeastSquares ols = null;
        switch (type) {
            case OlsTypes.LINEAR:
                ols = new LinearOrdinaryLeastSquares();
                break;
            case OlsTypes.EXPONENTIAL:
                ols = new ExponentialOrdinaryLeastSquares();
                break;
            case OlsTypes.FRACTIONAL:
                ols = new FractionalLinearOrdinaryLeastSquares();
                break;
            case OlsTypes.HYPERBOLIC:
                ols = new HyperbolicOrdinaryLeastSquares();
                break;
            case OlsTypes.LOGARITHMIC:
                ols = new LogarithmicOrdinaryLeastSquares();
                break;
            case OlsTypes.POWER:
                ols = new PowerOrdinaryLeastSquares();
                break;
            case OlsTypes.RATIONAL:
                ols = new RationalOrdinaryLeastSquares();
                break;
        }
        return ols;
    }

    public static String createOlsFunction(int type, double coefA, double coefB) {
        String function = null;
        String a = new DecimalFormat("#.####").format(coefA);
        String b = new DecimalFormat("#.####").format(coefB);
        switch (type) {
        case OlsTypes.LINEAR:
            function = "$$\\Huge y(x) = " + a + "x" + "+" + b + "$$";
            break;
        case OlsTypes.EXPONENTIAL:
            function = "$$\\Huge y(x) = " + a + "*e" + "^" + "(" + b + "x)$$";
            break;
        case OlsTypes.FRACTIONAL:
            function = "$$\\Huge y(x) = " + "\\frac{1}{" + a + "x" + "+" + b +"}$$";
            break;
        case OlsTypes.HYPERBOLIC:
            function = "$$\\Huge y(x) = \\frac{" + a + "}{x}" + "+" + b + "$$";
            break;
        case OlsTypes.LOGARITHMIC:
            function = "$$\\Huge y(x) = " + a + "ln(x)" + "+" + b + "$$";
            break;
        case OlsTypes.POWER:
            function = "$$\\Huge y(x) = " + a + "x" + "^" + b + "$$";
            break;
        case OlsTypes.RATIONAL:
            function = "$$\\Huge y(x) = \\frac{" + "x}{" + a + "x" + "+" + b + "}$$";
            break;
        }
        return function;
    }
}
