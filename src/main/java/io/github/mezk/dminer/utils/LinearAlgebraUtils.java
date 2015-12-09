package io.github.mezk.dminer.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javacalculus.core.CALC;
import javacalculus.core.CalcParser;
import javacalculus.core.CalculusEngine;
import javacalculus.struct.CalcObject;

public class LinearAlgebraUtils {

    public static String getDiff(String expression, String variable) {
        String command = "DIFF(" + expression + ", " + variable + ")";
        return eval(command);
    }

    public static String getAdd(String expr1, String expr2) {
        String command = "ADD(" + expr1 + ", " + expr2 + ")";
        return eval(command);
    }

    public static String eval(String command) {
        CalcObject result = null;
        try {
            result = new CalcParser().parse(command).evaluate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        result = CALC.SYM_EVAL(result);
        return findOutLine(new CalculusEngine().execute(result.toString()));
    }

    public static String exec(String expr) {
        return findOutLine(new CalculusEngine().execute(expr));
    }

    public static String findOutLine(String input) {
        Matcher m = Pattern.compile("Output:.*").matcher(input);
        if (m.find()) {
            return input.substring(m.start(), m.end()).replace("Output: ", "");
        }
        return null;
    }
}