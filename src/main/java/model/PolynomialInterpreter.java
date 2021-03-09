package model;

import validator.EmptyInputException;
import validator.InvalidInputException;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PolynomialInterpreter {
    private static final String REGEX_MATCHER = "([+-]?\\d*)*?[xX]\\^(-?\\d*)";
    private static final String EXCEPTION_EMPTY_POLYNOM = "Please enter a polynomial!";
    private static final String EXCEPTION_INVALID_POLYNOM = "The form should be: aX^n+bX^n-1+...+z";

    public static Polynomial parseString(String buffer) throws EmptyInputException, InvalidInputException {
        Polynomial result = new Polynomial();
        if (PolynomialInterpreter.textIsEmpty(buffer)) {
            throw new EmptyInputException(EXCEPTION_EMPTY_POLYNOM);
        }
        buffer = PolynomialInterpreter.stringToPolynomialString(buffer);
        if (PolynomialInterpreter.invalidInputForm(buffer)) {
            throw new InvalidInputException(EXCEPTION_INVALID_POLYNOM);
        }
        Pattern pattern = Pattern.compile(REGEX_MATCHER);
        Matcher matcher = pattern.matcher(buffer);
        while (matcher.find()) {
            Monomial termFound = new Monomial();
            termFound.setCoefficient(Double.parseDouble(matcher.group(1)));
            termFound.setExponent(Double.parseDouble(matcher.group(2)));
            result.addMonomialToList(termFound);
        }
        PolynomialInterpreter.sortByExponents(result);
        PolynomialInterpreter.reduceCoefficients(result);
        return result;
    }

    private static boolean textIsEmpty(String buffer) {
        return buffer.trim().isBlank();
    }

    private static String stringToPolynomialString(String buffer) {
        buffer = buffer.replace("X", "x");
        buffer = buffer.replace("*", "");
        buffer = buffer.replace(" ", "");
        buffer = buffer.replaceAll("(?<!.)x", "1x");
        buffer = buffer.replaceAll("x(?!.)", "x^1");
        buffer = buffer.replaceAll("(?<!\\^-?)(\\d+)(?=[+-]|$)", "$1x^0");
        buffer = buffer.replace("x+", "x^1+");
        buffer = buffer.replace("x-", "x^1-");
        buffer = buffer.replace("+x", "+1x");
        buffer = buffer.replace("-x", "-1x");
        return buffer;
    }

    private static boolean invalidInputForm(String buffer) {
        return !buffer.matches("(" + REGEX_MATCHER + ")*");
    }

    public static String parseValue(Polynomial buffer) {
        if (buffer.getMonomialList().isEmpty()) {
            return "0";
        }
        String result = "";
        for (Monomial mIterator : buffer.getMonomialList()) {
            if (mIterator.getCoefficient() == 1 && mIterator.getExponent() == 0) {
                result += "+" + (int) mIterator.getCoefficient();
            } else if (mIterator.getCoefficient() == 1 && mIterator.getExponent() != 0) {
                result += "+";
            } else if (mIterator.getCoefficient() == -1 && mIterator.getExponent() == 0) {
                result += (int) mIterator.getCoefficient();
            } else if (mIterator.getCoefficient() == -1 && mIterator.getExponent() != 0) {
                result += "-";
            } else {
                result = mIterator.getCoefficient() > 0 ? result + "+" : result + "-";
                if (mIterator.getCoefficient() == Math.floor(mIterator.getCoefficient())) {
                    result += (int) Math.abs(mIterator.getCoefficient());
                } else {
                    result += Math.abs(Math.floor(mIterator.getCoefficient() * 100) / 100);
                }
            }
            if (mIterator.getExponent() != 1 && mIterator.getExponent() != 0 && mIterator.getExponent() > 0) {
                result += "x^" + (int) mIterator.getExponent();
            } else if (mIterator.getExponent() != 1 && mIterator.getExponent() != 0 && mIterator.getExponent() < 0) {
                result += "x^(" + (int) mIterator.getExponent() + ")";
            } else if (mIterator.getExponent() == 1) {
                result += "x";
            }
        }
        if (result.startsWith("+")) {
            return result.substring(1);
        } else {
            return result;
        }
    }

    public static void sortByExponents(Polynomial crtPolynomial) {
        DescendingExponentSort sortExponents = new DescendingExponentSort();
        crtPolynomial.getMonomialList().sort(sortExponents);
    }

    public static void reduceCoefficients(Polynomial crtPolynomial) {
        Monomial mFlag = new Monomial();
        ArrayList<Monomial> toRemove = new ArrayList<>();
        for (Monomial mIterator : crtPolynomial.getMonomialList()) {
            if (mIterator.getExponent() == mFlag.getExponent()) {
                mIterator.setCoefficient(mIterator.getCoefficient() + mFlag.getCoefficient());
                toRemove.add(mFlag);
            }
            if (mIterator.getCoefficient() == 0) {
                toRemove.add(mIterator);
            }
            mFlag = mIterator;
        }
        crtPolynomial.getMonomialList().removeAll(toRemove);
    }

}
