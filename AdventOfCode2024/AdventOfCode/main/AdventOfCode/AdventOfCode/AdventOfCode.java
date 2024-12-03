package AdventOfCode.AdventOfCode;

import references.references.NumberArrayReference;
import references.references.StringReference;

import static QuickSort.QuickSort.QuickSort.QuickSortNumbers;
import static java.lang.Math.abs;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalString;
import static references.references.references.CreateNumberArrayReferenceLengthValue;
import static strings.strings.strings.SplitByCharacter;

public class AdventOfCode {
    public static char [] ComputeDay1Part1(char [] input){ // 45
        char [] output, line, part;
        double i, j, sum, n;
        double [] left, right;
        boolean leftset, rightset;
        StringReference[] lines, parts;

        lines = SplitByCharacter(input, '\n');
        n = lines.length;

        left = new double[(int)n];
        right = new double[(int)n];

        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int) i].string;
            parts = SplitByCharacter(line, ' ');

            leftset = false;
            rightset = false;

            for(j = 0d; j < parts.length; j = j + 1d){
                part = parts[(int) j].string;

                if(part.length > 0) {
                    if(!leftset) {
                        left[(int) i] = CreateNumberFromDecimalString(part);
                        leftset = true;
                    }else if(!rightset){
                        right[(int) i] = CreateNumberFromDecimalString(part);
                        rightset = true;
                    }
                }
            }
        }

        QuickSortNumbers(left);
        QuickSortNumbers(right);

        sum = 0;
        for(i = 0; i < n; i = i + 1d){
            sum = sum + abs(right[(int)i] - left[(int)i]);
        }

        output = CreateStringDecimalFromNumber(sum);

        return output;
    }

    public static char [] ComputeDay1Part2(char [] input){ // 57
        char [] output;
        double i, j, sum, n, m, c;
        double [] left, right;
        boolean leftset, rightset;
        char [] line, part;
        StringReference [] lines, parts;

        lines = SplitByCharacter(input, '\n');
        n = lines.length;

        left = new double[(int)n];
        right = new double[(int)n];

        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int) i].string;
            parts = SplitByCharacter(line, ' ');

            leftset = false;
            rightset = false;

            for(j = 0d; j < parts.length; j = j + 1d){
                part = parts[(int) j].string;

                if(part.length > 0) {
                    if(!leftset) {
                        left[(int) i] = CreateNumberFromDecimalString(part);
                        leftset = true;
                    }else if(!rightset){
                        right[(int) i] = CreateNumberFromDecimalString(part);
                        rightset = true;
                    }
                }
            }
        }

        QuickSortNumbers(left);
        QuickSortNumbers(right);

        sum = 0;
        for(i = 0; i < lines.length; i = i + 1d){
            n = left[(int)i];
            c = 0;

            for(j = 0; j < lines.length; j = j + 1d){
                m = right[(int)j];
                if(n == m){
                    c = c + 1d;
                }
            }

            sum = sum + n * c;
        }

        output = CreateStringDecimalFromNumber(sum);

        return output;
    }

    public static char[] ComputeDay2Part1(char[] input) {
        char [] output;
        double i, j, safe, n1, n2;
        NumberArrayReference[] rows;
        boolean isSafe, inc, directionSet, done;
        char [] line, part;
        StringReference [] lines, parts;

        // Parse input
        lines = SplitByCharacter(input, '\n');

        rows = new NumberArrayReference[lines.length];

        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int) i].string;
            parts = SplitByCharacter(line, ' ');

            rows[(int)i] = CreateNumberArrayReferenceLengthValue(parts.length, 0d);

            for(j = 0d; j < parts.length; j = j + 1d){
                part = parts[(int) j].string;

                rows[(int)i].numberArray[(int)j] = CreateNumberFromDecimalString(part);
            }
        }

        // Compute safe
        safe = 0d;

        for(i = 0d; i < rows.length; i = i + 1d){
            isSafe = true;
            directionSet = false;
            done = false;
            inc = false;
            for(j = 0d; j < rows[(int)i].numberArray.length - 1d && !done; j = j + 1d){
                n1 = rows[(int)i].numberArray[(int)j];
                n2 = rows[(int)i].numberArray[(int)(j + 1d)];

                if(!directionSet){
                    if(n1 > n2){
                        inc = false;
                        directionSet = true;
                    }else if(n1 < n2){
                        inc = true;
                        directionSet = true;
                    }else{
                        isSafe = false;
                        done = true;
                    }
                }
                if(directionSet){
                    if(inc){
                        if(n1 < n2){
                            if(n2 - n1 <= 3d){

                            }else{
                                isSafe = false;
                                done = true;
                            }
                        }else{
                            isSafe = false;
                            done = true;
                        }
                    }else{
                        if(n1 > n2){
                            if(n1 - n2 <= 3d){

                            }else{
                                isSafe = false;
                                done = true;
                            }
                        }else{
                            isSafe = false;
                            done = true;
                        }
                    }
                }
            }
            if(isSafe){
                safe = safe + 1d;
            }
        }

        output = CreateStringDecimalFromNumber(safe);

        return output;
    }

    public static char[] ComputeDay2Part2(char[] input) {
        char [] output;
        double i, j, k, safe, n, m, c;
        NumberArrayReference[] rows;
        double [] row;
        boolean isSafe, inc, directionSet, computedSafe, done, noRemove;
        char [] line, part;
        StringReference [] lines, parts;

        // Parse input
        lines = SplitByCharacter(input, '\n');

        rows = new NumberArrayReference[lines.length];

        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int) i].string;
            parts = SplitByCharacter(line, ' ');

            rows[(int)i] = CreateNumberArrayReferenceLengthValue(parts.length, 0d);

            for(j = 0d; j < parts.length; j = j + 1d){
                part = parts[(int) j].string;

                rows[(int)i].numberArray[(int)j] = CreateNumberFromDecimalString(part);
            }
        }

        // Compute safe
        safe = 0d;

        for(i = 0d; i < rows.length; i = i + 1d){
            computedSafe = false;
            noRemove = true;
            for(k = 0d; k < rows[(int)i].numberArray.length && !computedSafe; k = k + 1d) {
                if(noRemove){
                    row = rows[(int) i].numberArray;
                    k = k - 1d;
                    noRemove = false;
                }else{
                    // Create row without one column.
                    row = new double[rows[(int) i].numberArray.length - 1];
                    c = 0;
                    for (j = 0d; j < row.length + 1d; j = j + 1d) {
                        if(j != k) {
                            row[(int)c] = rows[(int) i].numberArray[(int)j];
                            c = c + 1d;
                        }
                    }
                }

                isSafe = true;
                directionSet = false;
                inc = false;
                done = false;
                for (j = 0d; j < row.length - 1d && !done; j = j + 1d) {
                    n = row[(int)j];
                    m = row[(int)(j + 1d)];

                    if (!directionSet) {
                        if (n > m) {
                            inc = false;
                            directionSet = true;
                        } else if (n < m) {
                            inc = true;
                            directionSet = true;
                        } else {
                            isSafe = false;
                            done = true;
                        }
                    }
                    if (directionSet) {
                        if (inc) {
                            if (n < m) {
                                if (m - n <= 3d) {

                                } else {
                                    isSafe = false;
                                    done = true;
                                }
                            } else {
                                isSafe = false;
                                done = true;
                            }
                        } else {
                            if (n > m) {
                                if (n - m <= 3d) {

                                } else {
                                    isSafe = false;
                                    done = true;
                                }
                            } else {
                                isSafe = false;
                                done = true;
                            }
                        }
                    }
                }
                if (isSafe) {
                    safe = safe + 1d;
                    computedSafe = true;
                }
            }
        }

        output = CreateStringDecimalFromNumber(safe);

        return output;
    }
}
