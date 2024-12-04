package AdventOfCode.AdventOfCode;

import lists.LinkedListCharacters.Structures.LinkedListCharacters;
import lists.LinkedListStrings.Structures.LinkedListStrings;
import references.references.NumberArrayReference;
import references.references.NumberReference;
import references.references.StringReference;

import static DataStructures.Array.Structures.Structures.IsNumber;
import static QuickSort.QuickSort.QuickSort.QuickSortNumbers;
import static charCharacters.Characters.Characters.charIsNumber;
import static java.lang.Math.abs;
import static lists.LinkedListCharacters.LinkedListCharactersFunctions.LinkedListCharactersFunctions.*;
import static lists.LinkedListStrings.LinkedListStringsFunctions.LinkedListStringsFunctions.*;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalString;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalStringWithCheck;
import static references.references.references.*;
import static strings.strings.strings.SplitByCharacter;
import static strings.strings.strings.SplitByString;

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

    public static char[] ComputeDay3Part1(char[] input) {
        char [] output;
        double n, i, n1, n2;
        StringReference[] muls, params, numbers;
        boolean success;
        StringReference message;
        NumberReference nRef = new NumberReference();

        message = new StringReference();

        n = 0;

        muls = SplitByString(input, "mul(".toCharArray());

        for(i = 1d; i < muls.length; i = i + 1d) {
            params = SplitByCharacter(muls[(int)i].string, ')');
            if(params.length >= 2){
                numbers = SplitByCharacter(params[0].string, ',');
                if(numbers.length == 2){
                    success = CreateNumberFromDecimalStringWithCheck(numbers[0].string, nRef, message);
                    if(success) {
                        n1 = nRef.numberValue;
                        if(n1 >= 0 && n1 <= 999) {
                            success = CreateNumberFromDecimalStringWithCheck(numbers[1].string, nRef, message);
                            if (success) {
                                n2 = nRef.numberValue;
                                if(n2 >= 0 && n2 <= 999) {
                                    n = n + n1 * n2;
                                }
                            }
                        }
                    }
                }
            }
        }


        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static char[] ComputeDay3Part2(char[] input) {
        char [] output;
        double n, i, n1, n2;
        StringReference[] muls, params, numbers, dos, donts;
        boolean success;
        StringReference message;
        NumberReference nRef = new NumberReference();
        LinkedListCharacters ll;

        ll = CreateLinkedListCharacter();

        message = new StringReference();

        n = 0;

        dos = SplitByString(input, "do()".toCharArray());
        for(i = 0d; i < dos.length; i++){
            donts = SplitByString(dos[(int)i].string, "don't()".toCharArray());
            LinkedListCharactersAddString(ll, donts[0].string);
        }

        input = LinkedListCharactersToArray(ll);

        muls = SplitByString(input, "mul(".toCharArray());

        for(i = 1d; i < muls.length; i = i + 1d) {
            params = SplitByCharacter(muls[(int)i].string, ')');
            if(params.length >= 2){
                numbers = SplitByCharacter(params[0].string, ',');
                if(numbers.length == 2){
                    success = CreateNumberFromDecimalStringWithCheck(numbers[0].string, nRef, message);
                    if(success) {
                        n1 = nRef.numberValue;
                        if(n1 >= 0 && n1 <= 999) {
                            success = CreateNumberFromDecimalStringWithCheck(numbers[1].string, nRef, message);
                            if (success) {
                                n2 = nRef.numberValue;
                                if(n2 >= 0 && n2 <= 999) {
                                    n = n + n1 * n2;
                                }
                            }
                        }
                    }
                }
            }
        }


        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static char[] ComputeDay4Part1(char[] input) {
        char [] output;
        double n, w, h, i;

        StringReference[] field = SplitByCharacter(input, '\n');

        w = field.length;
        h = field[0].string.length;

        n = 0d;

        for(i = 0; i < 4d; i = i + 1d){
            n = n + CountRight(w, h, field);
            n = n + CountSlope(w, h, field);
            field = Rotate90(field);
        }

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    // Day 4, Part 1 - Functions
    public static char GetCharacter(StringReference[] field, double x, double y) {
        return field[(int)y].string[(int)x];
    }

    public static double CountRight(double w, double h, StringReference[] field) {
        double x, y, n;

        n = 0d;

        for(y = 0; y < h; y = y + 1d){
            for(x = 0; x < w - 3d; x = x + 1d){
                if(
                    GetCharacter(field, x+0d, y) == 'X' &&
                    GetCharacter(field, x+1d, y) == 'M' &&
                    GetCharacter(field, x+2d, y) == 'A' &&
                    GetCharacter(field, x+3d, y) == 'S'
                ){
                    n = n + 1d;
                }
            }
        }

        return n;
    }

    public static double CountSlope(double w, double h, StringReference[] field) {
        double x, y, n;

        n = 0;

        for(y = 0; y < h - 3d; y = y + 1d){
            for(x = 0; x < w - 3d; x = x + 1d){
                if(
                    GetCharacter(field, x+0d, y+0d) == 'X' &&
                    GetCharacter(field, x+1d, y+1d) == 'M' &&
                    GetCharacter(field, x+2d, y+2d) == 'A' &&
                    GetCharacter(field, x+3d, y+3d) == 'S'
                ){
                    n = n + 1d;
                }
            }
        }

        return n;
    }

    public static StringReference[] Rotate90(StringReference[] field) {
        double w, h, x, y;
        StringReference [] rotated;

        w = field[0].string.length;
        h = field.length;

        rotated = new StringReference[(int)h];

        for(y = 0; y < h; y = y + 1d){
            rotated[(int)y] = CreateStringReferenceLengthValue(w, '_');
            for(x = 0; x < w; x = x + 1d){
                rotated[(int)y].string[(int)x] = GetCharacter(field, w-y-1d, x);
            }
        }

        return rotated;
    }

    public static void PrintField(double w, double h, StringReference[] paddedLines) {
        double y, x;

        for(y = 0; y < h; y++){
            for(x = 0; x < w; x++){
                System.out.print(GetCharacter(paddedLines, x, y));
            }
            System.out.println();
        }
    }
}









