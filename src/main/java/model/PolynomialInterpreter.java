package model;

import validator.EmptyInputException;
import validator.InvalidInputException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialInterpreter {
    private static final String REGEX_MATCHER = "([+-]?\\d*)*?[xX]\\^(-?\\d*)";
    private static final String EXCEPTION_EMPTY_POLYNOM = "Please enter a polynomial!";
    private static final String EXCEPTION_INVALID_POLYNOM = "The form should be: aX^n+bX^n-1+...+z";

    public static IntegerPolynomial parseString(String buffer) throws EmptyInputException, InvalidInputException {
        IntegerPolynomial result = new IntegerPolynomial();
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
            IntegerMonomial termFound = new IntegerMonomial();
            termFound.setCoefficient(Integer.parseInt(matcher.group(1)));
            termFound.setExponent(Integer.parseInt(matcher.group(2)));
            result.addMonomialToList(termFound);
        }
        PolynomialInterpreter.sortByExponents(result);
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

    public static String parseValue(DoublePolynomial buffer) {
        if (buffer.getMonomialList().isEmpty()) {
            return "0";
        }
        String result = "";
        for (Monomial<Double> mIterator : buffer.getMonomialList()) {
            if (mIterator.getCoefficient() == 1 && mIterator.getExponent() == 0) {
                result += "+" + mIterator.getCoefficient().intValue();
            } else if (mIterator.getCoefficient() == 1 && mIterator.getExponent() != 0) {
                result += "+";
            } else if (mIterator.getCoefficient() == -1 && mIterator.getExponent() == 0) {
                result += mIterator.getCoefficient().intValue();
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
                result += "x^";
                if (mIterator.getExponent() == Math.floor(mIterator.getExponent())) {
                    result += mIterator.getExponent().intValue();
                } else {
                    result += mIterator.getExponent();
                }
            } else if (mIterator.getExponent() != 1 && mIterator.getExponent() != 0 && mIterator.getExponent() < 0) {
                result += "x^)";
                if (mIterator.getExponent() == Math.floor(mIterator.getExponent())) {
                    result += mIterator.getExponent().intValue();
                } else {
                    result += mIterator.getExponent();
                }
                result += ")";
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

    public static void sortByExponents(DoublePolynomial doublePolynomial) {
        DescendingExponentsSort sortExponents = new DescendingExponentsSort();
        doublePolynomial.getMonomialList().sort(sortExponents);
    }

    public static void sortByExponents(IntegerPolynomial intPolynomial) {
        DescendingExponentsSort sortExponents = new DescendingExponentsSort();
        intPolynomial.getMonomialList().sort(sortExponents);
    }

    public static void reduceCoefficients(DoublePolynomial crtPolynomial) {
        DoubleMonomial mFlag = new DoubleMonomial();
        ArrayList<DoubleMonomial> toRemove = new ArrayList<>();
        for (DoubleMonomial mIterator : crtPolynomial.getMonomialList()) {
            if (mIterator.getExponent().equals(mFlag.getExponent())) {
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
