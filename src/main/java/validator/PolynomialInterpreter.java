package validator;

import model.monomial.DoubleMonomial;
import model.monomial.IntegerMonomial;
import model.polynomial.DoublePolynomial;
import model.polynomial.IntegerPolynomial;
import validator.exceptions.EmptyInputException;
import validator.exceptions.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialInterpreter {
    private static final String REGEX_MATCHER = "([+-]?\\d*)*?[xX]\\^(-?\\d*)";
    private static final String EXCEPTION_EMPTY_POLYNOMIAL = "Please enter a polynomial!";
    private static final String EXCEPTION_INVALID_POLYNOMIAL = "The form should be: aX^n+bX^n-1+...+z";

    public static IntegerPolynomial parseString(String buffer) throws EmptyInputException, InvalidInputException {
        IntegerPolynomial result = new IntegerPolynomial();
        if (buffer.trim().isBlank()) {
            throw new EmptyInputException(EXCEPTION_EMPTY_POLYNOMIAL);
        }
        buffer = PolynomialInterpreter.stringToPolynomialString(buffer);
        if (!buffer.matches("(" + REGEX_MATCHER + ")*")) {
            throw new InvalidInputException(EXCEPTION_INVALID_POLYNOMIAL);
        }

        Pattern pattern = Pattern.compile(REGEX_MATCHER);
        Matcher matcher = pattern.matcher(buffer);
        while (matcher.find()) {
            IntegerMonomial termFound = new IntegerMonomial();
            termFound.setCoefficient(Integer.parseInt(matcher.group(1)));
            termFound.setExponent(Integer.parseInt(matcher.group(2)));
            result.addMonomialToList(termFound);
        }
        PolynomialEvaluator.sortByExponents(result);
        return result;
    }

    // Transofrmam un string de forma '5X^2+x+4' in forma '5x^2+1x^1+4x^0'
    // Patternul va recunoaste primul numar ca fiind coeficientul, iar al doilea, exponentul
    private static String stringToPolynomialString(String buffer) {
        buffer = buffer.replace("X", "x");
        buffer = buffer.replace("*", "");
        buffer = buffer.replace(" ", "");
        buffer = buffer.replaceAll("([+-])x", "$11x");
        buffer = buffer.replaceAll("(?<!.)x", "1x");
        buffer = buffer.replaceAll("x([+-])", "x^1$1");
        buffer = buffer.replaceAll("x(?!.)", "x^1");
        buffer = buffer.replaceAll("(?<!\\^-?)(\\d+)(?=[+-]|$)", "$1x^0");
        return buffer;
    }

    // Pentru fiecare monom, concatenam semnul la rezultat
    // Daca monomul e termen liber sau nu are coeficientul egal cu 1
    // Concatenam coeficientul la rezultat
    // Daca monomul are exponentul '1' lipim 'x', daca nu, 'x^exponent' la rezultat
    // Cu paranteze pentru exponent negativ, pentru o afisare mai clara
    public static String parseValue(DoublePolynomial buffer) {
        if (buffer.getMonomialList().isEmpty()) {
            return "0";
        }

        String result = "";
        for (DoubleMonomial mIterator : buffer.getMonomialList()) {
            result = mIterator.getCoefficient() > 0 ? result + "+" : result + "-";

            if (mIterator.getExponent() == 0 || Math.abs(mIterator.getCoefficient()) != 1) {
                if (mIterator.getCoefficient() == Math.floor(mIterator.getCoefficient())) {
                    result += (int) Math.abs(mIterator.getCoefficient());
                } else {
                    result += Math.abs(Math.floor(mIterator.getCoefficient() * 100) / 100);
                }
            }

            if (mIterator.getExponent() == 1) {
                result += "x";
            } else if (mIterator.getExponent() > 0) {
                result += "x^" + mIterator.getExponent().intValue();
            } else if (mIterator.getExponent() < 0) { // Nu vrem x^0
                result += "x^(" + mIterator.getExponent().intValue() + ")";
            }
        }

        if (result.startsWith("+")) {
            return result.substring(1);
        } else {
            return result;
        }
    }
}
