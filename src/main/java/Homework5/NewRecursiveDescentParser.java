package Homework5;

import java.util.Arrays;
import java.util.List;

public class NewRecursiveDescentParser {

    private final List<String> tokens;
    private int pos = 0;

    public NewRecursiveDescentParser(List<String> tokens) {
        this.tokens = tokens;
    }

    public Double parse() {
        return expression();
    }

    private Double expression() {
        Double first = term();

        while (pos < tokens.size()) {
            String operator = tokens.get(pos);

            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                pos++;
            }

            Double second = term();
            if (operator.equals("+")) {
                first += second;
            }
            if (operator.equals("-")) {
                first -= second;
            }
        }
        return first;
    }

    private Double term() {
        Double first = factor();

        while (pos < tokens.size()) {
            String operator = tokens.get(pos);
            if (!operator.equals("*") && !operator.equals("/")) {
                return first;
            } else {
                pos++;
            }

            Double second = factor();
            if (operator.equals("*")) {
                first *= second;
            }
            if (operator.equals("/")) {
                first /= second;
            }
        }
        return first;
    }

    private Double factor() {
        String method = tokens.get(pos);
        Double result = null;
        if (tokens.get(pos).equals("(")) {
            pos++;
            result = expression();
            String closingBracket = null;
            if (pos < tokens.size() && (closingBracket = tokens.get(pos)).equals(")")) {
                pos++;
                return result;
            }
            throw new IllegalStateException("')' expected but " + closingBracket);
        }
        if (method.equals("sin") || method.equals("cos")) {
            pos++;
            if (tokens.get(pos).equals("(")) {
                pos++;
                if (method.equals("sin")) result = Math.sin(expression());
                if (method.equals("cos")) result = Math.cos(expression());
                String closingBracket = null;
                if (pos < tokens.size() && (closingBracket = tokens.get(pos)).equals(")")) {
                    pos++;
                    return result;
                }
                throw new IllegalStateException("')' expected but " + closingBracket);
            }
        }
        pos++;
        return Double.parseDouble(method);
    }

    public static void main(String[] args) {
        String expr = "2.111 * 3.5 + ( 3 + 4 * ( 2.1 + 3 ) - 10 ) * ( 5 - 7 + 4 - 2 ) + 11 + sin ( 10 ) + cos ( 12 + 1 )";
        NewRecursiveDescentParser newRecursiveDescentParser = new NewRecursiveDescentParser(Arrays.asList(expr.split(" ")));
        System.out.println(newRecursiveDescentParser.parse());
        System.out.println(2.111 * 3.5 + (3 + 4 * (2.1 + 3) - 10 ) * (5 - 7 + 4 - 2) + 11 + Math.sin(10) + Math.cos(12 + 1));
    }
}
