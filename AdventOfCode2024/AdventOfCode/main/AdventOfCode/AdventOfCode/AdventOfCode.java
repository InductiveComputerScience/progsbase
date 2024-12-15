package AdventOfCode.AdventOfCode;

import DataStructures.Array.Structures.Data;
import Matrices.Matrices.Matrix;
import Trees.RedBlackTrees.RedBlackNode;
import Trees.RedBlackTrees.RedBlackTree;
import lists.LinkedListCharacters.Structures.LinkedListCharacters;
import lists.LinkedListStrings.Structures.LinkedListStrings;
import references.references.BooleanReference;
import references.references.NumberArrayReference;
import references.references.NumberReference;
import references.references.StringReference;

import static DataStructures.Array.Structures.Structures.*;
import static Matrices.Matrices.Matrices.*;
import static QuickSort.QuickSort.QuickSort.QuickSortNumbers;
import static Trees.RedBlackTrees.RedBlackTrees.*;
import static aarrays.arrays.arrays.aCopyString;
import static charCharacters.Characters.Characters.charCharacterToDecimalDigit;
import static charCharacters.Characters.Characters.charDecimalDigitToCharacter;
import static java.lang.Math.*;
import static lists.LinkedListCharacters.LinkedListCharactersFunctions.LinkedListCharactersFunctions.*;
import static lists.LinkedListStrings.LinkedListStringsFunctions.LinkedListStringsFunctions.*;
import static math.math.math.IsInteger;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.*;
import static references.references.references.*;
import static strings.strings.strings.*;

public class AdventOfCode {
    public static char [] ComputeDay1Part1(char [] input){
        char [] output, line;
        double i, sum, n;
        double [] left, right;
        StringReference[] lines, parts;

        lines = SplitByCharacter(input, '\n');
        n = lines.length;

        left = new double[(int)n];
        right = new double[(int)n];

        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int) i].string;
            parts = SplitByString(line, "   ".toCharArray());

            left[(int)i] = CreateNumberFromDecimalString(parts[0].string);
            right[(int)i] = CreateNumberFromDecimalString(parts[1].string);
        }

        QuickSortNumbers(left);
        QuickSortNumbers(right);

        sum = 0d;
        for(i = 0d; i < n; i = i + 1d){
            sum = sum + abs(right[(int)i] - left[(int)i]);
        }

        output = CreateStringDecimalFromNumber(sum);

        return output;
    }

    public static char [] ComputeDay1Part2(char [] input){
        char [] output;
        double i, j, sum, n, m, c;
        double [] left, right;
        char [] line;
        StringReference [] lines, parts;

        lines = SplitByCharacter(input, '\n');
        n = lines.length;

        left = new double[(int)n];
        right = new double[(int)n];

        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int) i].string;
            parts = SplitByString(line, "   ".toCharArray());

            left[(int)i] = CreateNumberFromDecimalString(parts[0].string);
            right[(int)i] = CreateNumberFromDecimalString(parts[1].string);
        }

        QuickSortNumbers(left);
        QuickSortNumbers(right);

        sum = 0d;
        for(i = 0d; i < lines.length; i = i + 1d){
            n = left[(int)i];
            c = 0d;

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
        h = GetFieldWidth(field);

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

        w = GetFieldWidth(field);
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

    public static void PrintField(StringReference[] field) {
        double y, x, w, h;

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        for(y = 0; y < h; y++){
            for(x = 0; x < w; x++){
                System.out.print(GetCharacter(field, x, y));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int GetFieldHeight(StringReference[] field) {
        return field.length;
    }

    public static int GetFieldWidth(StringReference[] field) {
        return field[0].string.length;
    }

    public static char[] ComputeDay4Part2(char[] input) {
        char [] output;
        double n, w, h, i;

        StringReference[] field = SplitByCharacter(input, '\n');

        w = field.length;
        h = GetFieldWidth(field);

        n = 0d;

        for(i = 0; i < 4d; i = i + 1d){
            n = n + CountCrossMAS(w, h, field);
            field = Rotate90(field);
        }

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static double CountCrossMAS(double w, double h, StringReference[] field) {
        double x, y, n;

        n = 0;

        for(y = 0; y < h - 2d; y = y + 1d){
            for(x = 0; x < w - 2d; x = x + 1d){
                if(
                    GetCharacter(field, x+0d, y+0d) == 'M' &&
                    GetCharacter(field, x+1d, y+1d) == 'A' &&
                    GetCharacter(field, x+2d, y+2d) == 'S' &&

                    GetCharacter(field, x+2d, y+0d) == 'M' &&
                    GetCharacter(field, x+1d, y+1d) == 'A' &&
                    GetCharacter(field, x+0d, y+2d) == 'S'
                ){
                    n = n + 1d;
                }
            }
        }

        return n;
    }

    public static char[] ComputeDay5Part1(char[] input) {
        char [] output;
        double n, i, j;
        LinkedListStrings ordersLL, listsLL;
        char[] line;
        boolean doingOrders;
        Order [] orders;
        double [] list;

        ordersLL = CreateLinkedListString();
        listsLL = CreateLinkedListString();

        StringReference[] lines = SplitByCharacter(input, '\n');

        doingOrders = true;
        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int) i].string;

            if(doingOrders) {
                if (ContainsCharacter(line, '|')) {
                    LinkedListAddString(ordersLL, line);
                }
                if (Trim(line).length == 0d) {
                    doingOrders = false;
                }
            }else{
                LinkedListAddString(listsLL, line);
            }
        }

        n = 0;

        StringReference[] orderLines = LinkedListStringsToArray(ordersLL);
        StringReference[] listLines = LinkedListStringsToArray(listsLL);

        /*System.out.println("Orders:");
        for(i = 0d; i < orderLines.length; i = i + 1d){
            System.out.println(new String(orderLines[(int)i].string));
        }

        System.out.println("Lists:");
        for(i = 0d; i < listLines.length; i = i + 1d){
            System.out.println(new String(listLines[(int)i].string));
        }*/

        orders = new Order[orderLines.length];
        for(i = 0d; i < orderLines.length; i = i + 1d){
            orders[(int)i] = new Order();
            StringReference[] parts = SplitByCharacter(orderLines[(int)i].string, '|');
            orders[(int)i].a = CreateNumberFromDecimalString(parts[0].string);
            orders[(int)i].b = CreateNumberFromDecimalString(parts[1].string);
        }

        /*for(i = 0d; i < orders.length; i = i + 1d){
            System.out.println(orders[(int)i].a + " | " + orders[(int)i].b);
        }*/

        //System.out.println("Lists:");
        for(i = 0d; i < listLines.length; i = i + 1d){
            StringReference[] parts = SplitByCharacter(listLines[(int)i].string, ',');
            list = new double[parts.length];
            for(j = 0d; j < list.length; j = j + 1d){
                list[(int)j] = CreateNumberFromDecimalString(parts[(int)j].string);
                //System.out.print(list[(int)j] + ":");
            }
            //System.out.println();

            // Check if valid:
            boolean valid = IsValid(orders, list);

            //System.out.println("Line " + i + " " + valid);

            if(valid){
                n = n + list[(int)(floor(list.length / 2d))];
                //System.out.println(list[(int)(floor(list.length / 2d))]);
            }
        }

        //System.out.println(n);

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static boolean IsValid(Order[] orders, double[] list) {
        double o, p;
        boolean valid = true;
        double n1 = 0, n2 = 0;
        boolean n1found, n2found;

        for(o = 0d; o < orders.length; o = o + 1d){
            n1found = false;
            n2found = false;
            for(p = 0d; p < list.length; p = p + 1d){
                if(list[(int)p] == orders[(int)o].a){
                    n1 = p;
                    n1found = true;
                }
                if(list[(int)p] == orders[(int)o].b){
                    n2 = p;
                    n2found = true;
                }
            }

            if(n1found && n2found) {
                if (n1 > n2) {
                    valid = false;
                }
            }
        }
        return valid;
    }

    public static void Fix(Order[] orders, double[] list) {
        double o, p, t;
        double n1 = 0, n2 = 0;
        boolean n1found, n2found;

        for(int i = 0;!IsValid(orders, list);i++) {
            for (o = 0d; o < orders.length; o = o + 1d) {
                n1found = false;
                n2found = false;
                for (p = 0d; p < list.length; p = p + 1d) {
                    if (list[(int) p] == orders[(int) o].a) {
                        n1 = p;
                        n1found = true;
                    }
                    if (list[(int) p] == orders[(int) o].b) {
                        n2 = p;
                        n2found = true;
                    }
                }

                if (n1found && n2found) {
                    if (n1 > n2) {
                        t = list[(int) n1];
                        list[(int) n1] = list[(int) n2];
                        list[(int) n2] = t;
                    }
                }
            }
        }
    }

    static class Order{
        public double a;
        public double b;
    }

    public static char[] ComputeDay5Part2(char[] input) {
        char [] output;
        double n, i, j, o, p;
        LinkedListStrings ordersLL, listsLL;
        char[] line;
        boolean doingOrders;
        Order [] orders;
        double [] list;

        ordersLL = CreateLinkedListString();
        listsLL = CreateLinkedListString();

        StringReference[] lines = SplitByCharacter(input, '\n');

        doingOrders = true;
        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int) i].string;

            if(doingOrders) {
                if (ContainsCharacter(line, '|')) {
                    LinkedListAddString(ordersLL, line);
                }
                if (Trim(line).length == 0d) {
                    doingOrders = false;
                }
            }else{
                LinkedListAddString(listsLL, line);
            }
        }

        n = 0;

        StringReference[] orderLines = LinkedListStringsToArray(ordersLL);
        StringReference[] listLines = LinkedListStringsToArray(listsLL);

        /*System.out.println("Orders:");
        for(i = 0d; i < orderLines.length; i = i + 1d){
            System.out.println(new String(orderLines[(int)i].string));
        }

        System.out.println("Lists:");
        for(i = 0d; i < listLines.length; i = i + 1d){
            System.out.println(new String(listLines[(int)i].string));
        }*/

        orders = new Order[orderLines.length];
        for(i = 0d; i < orderLines.length; i = i + 1d){
            orders[(int)i] = new Order();
            StringReference[] parts = SplitByCharacter(orderLines[(int)i].string, '|');
            orders[(int)i].a = CreateNumberFromDecimalString(parts[0].string);
            orders[(int)i].b = CreateNumberFromDecimalString(parts[1].string);
        }

        /*for(i = 0d; i < orders.length; i = i + 1d){
            System.out.println(orders[(int)i].a + " | " + orders[(int)i].b);
        }*/

        //System.out.println("Lists:");
        for(i = 0d; i < listLines.length; i = i + 1d){
            StringReference[] parts = SplitByCharacter(listLines[(int)i].string, ',');
            list = new double[parts.length];
            for(j = 0d; j < list.length; j = j + 1d){
                list[(int)j] = CreateNumberFromDecimalString(parts[(int)j].string);
                //System.out.print(list[(int)j] + ":");
            }
            //System.out.println();

            // Check if valid:
            boolean valid = IsValid(orders, list);

            //System.out.println("Line " + i + " " + valid);

            if(!valid){
                Fix(orders, list);

                n = n + list[(int)(floor(list.length / 2d))];
            }
        }

        //System.out.println(n);

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static char[] ComputeDay6Part1(char[] input) {
        char [] output;
        double n, i, j;

        StringReference[] field = SplitByCharacter(input, '\n');

        // Run
        Iterate(field);

        // Count
        n = 0;
        for(i = 0d; i < field.length; i = i + 1d){
            for(j = 0d; j < field[(int)i].string.length; j = j + 1d){
                if(GetCharacter(field, i, j) == 'X'){
                    n = n + 1d;
                }
            }
        }

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static boolean Iterate(StringReference[] field) {
        double i, j, x, y, w, h, max;
        char d, c;
        boolean exit;

        x = 0;
        y = 0;
        h = field.length;
        w = GetFieldWidth(field);

        // Find
        for (i = 0d; i < h; i = i + 1d) {
            for (j = 0d; j < w; j = j + 1d) {
                c = GetCharacter(field, j, i);
                if (c == '^' || c == '<' || c == '>' || c == 'v') {
                    x = j;
                    y = i;
                }
            }
        }

        exit = false;
        max = w * h * 4d;
        for(i = 0; !exit && i < max; i = i + 1d) {
            d = GetCharacter(field, x, y);

            if(d == '.' || d == 'X' || d == '#'){
                //System.out.println("error");
            }

            if (d == '^') {
                exit = y == 0;
            } else if (d == '>') {
                exit = x == w - 1d;
            } else if (d == 'v') {
                exit = y == h - 1d;
            } else if (d == '<') {
                exit = x == 0d;
            }

            if (exit) {
                SetCharacter(field, x, y, 'X');
            } else {
                //PrintField(w, h, field);
                //System.out.println();

                if (d == '^') {
                    if (GetCharacter(field, x, y - 1d) == '#') {
                        SetCharacter(field, x, y, '>');
                    } else {
                        SetCharacter(field, x, y, 'X');
                        SetCharacter(field, x, y - 1d, d);
                        y = y - 1d;
                    }
                } else if (d == '>') {
                    if (GetCharacter(field, x + 1d, y) == '#') {
                        SetCharacter(field, x, y, 'v');
                    } else {
                        SetCharacter(field, x, y, 'X');
                        SetCharacter(field, x + 1d, y, d);
                        x = x + 1d;
                    }
                } else if (d == 'v') {
                    if (GetCharacter(field, x, y + 1d) == '#') {
                        SetCharacter(field, x, y, '<');
                    } else {
                        SetCharacter(field, x, y, 'X');
                        SetCharacter(field, x, y + 1d, d);
                        y = y + 1d;
                    }
                } else if (d == '<') {
                    if (GetCharacter(field, x - 1d, y) == '#') {
                        SetCharacter(field, x, y, '^');
                    } else {
                        SetCharacter(field, x, y, 'X');
                        SetCharacter(field, x - 1d, y, d);
                        x = x - 1d;
                    }
                }
            }
        }

        return i == max;
    }

    public static void SetCharacter(StringReference[] field, double x, double y, char c) {
        field[(int)y].string[(int)x] = c;
    }

    public static char[] ComputeDay6Part2(char[] input) {
        char [] output;
        double n, x, y, w, h;
        StringReference[] field;
        char c;
        boolean loop;

        field = SplitByCharacter(input, '\n');

        h = field.length;
        w = GetFieldWidth(field);

        // Count
        n = 0;
        for(y = 0d; y < h; y = y + 1d){
            for(x = 0d; x < w; x = x + 1d){
                field = SplitByCharacter(input, '\n');

                c = GetCharacter(field, x, y);
                if(c == '^' || c == '>' || c == 'v' || c == '<' || c == '#'){

                }else{
                    SetCharacter(field, x, y, '#');
                    //PrintField(w, h, field);
                    //System.out.println();
                    loop = Iterate(field);
                    if(loop){
                        n = n + 1d;
                    }
                }
            }
        }

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static StringReference[] CopyField(StringReference[] a) {
        StringReference [] b;
        double i;

        b = new StringReference[a.length];
        for(i = 0d; i < a.length; i = i + 1d){
            b[(int)i] = new StringReference();
            b[(int)i].string = aCopyString(a[(int)i].string);
        }

        return b;
    }

    public static char[] ComputeDay7Part1(char[] input) {
        char [] output, line;
        double i, o, t, n,  answer, op, result, n2;
        StringReference[] lines, parts, terms;
        boolean found;

        n = 0d;

        lines = SplitByCharacter(input, '\n');

        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int)i].string;
            parts = SplitByCharacter(line, ':');

            answer = CreateNumberFromDecimalString(parts[0].string);

            parts[1].string = Trim(parts[1].string);

            terms = SplitByCharacter(parts[1].string, ' ');

            found = false;
            for(o = 0d; o < pow(2, terms.length) && !found; o = o + 1d){
                op = o;

                result = CreateNumberFromDecimalString(terms[0].string);

                for(t = 1d; t < terms.length; t = t + 1d){
                    n2 = CreateNumberFromDecimalString(terms[(int)t].string);
                    if(op % 2d == 0d){
                        result = result + n2;
                    }else{
                        result = result * n2;
                    }
                    op = floor(op / 2d);
                }

                if(result == answer){
                    found = true;
                }
            }

            if(found){
                n = n + answer;
            }
        }

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static char[] ComputeDay7Part2(char[] input) {
        char [] output, line;
        double i, o, t, n,  answer, op, result, n2, p;
        StringReference[] lines, parts, terms;
        boolean found;
        double [] termsn;

        n = 0d;

        lines = SplitByCharacter(input, '\n');

        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int)i].string;
            parts = SplitByCharacter(line, ':');

            answer = CreateNumberFromDecimalString(parts[0].string);

            parts[1].string = Trim(parts[1].string);

            terms = SplitByCharacter(parts[1].string, ' ');

            termsn = new double [terms.length];
            for(t = 0d; t < terms.length; t = t + 1d){
                termsn[(int)t] = CreateNumberFromDecimalString(terms[(int)t].string);
            }

            found = false;
            for(o = 0d; o < pow(3d, terms.length-1d) && !found; o = o + 1d){
                op = o;

                result = termsn[0];

                boolean done = false;
                for(t = 1d; t < terms.length && !done; t = t + 1d){
                    n2 = termsn[(int)t];
                    if(op % 3d == 0d){
                        result = result * n2;
                    }else if(op % 3d == 1d){
                        result = result + n2;
                    }else{
                        if(termsn[(int)t] != 0) {
                            p = 1d;
                        }else{
                            p = floor(log10(termsn[(int) t])) + 1d;
                        }

                        result = result * pow(10d, p) + termsn[(int) t];
                    }
                    op = floor(op / 3d);
                }

                if(result == answer){
                    found = true;
                }
            }

            if(found){
                n = n + answer;
            }

            //System.out.println(i + ", " + n);
        }

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static char[] ComputeDay8Part1(char[] input) {
        char [] output, symbols;
        double i, n, w, h, x, y, cs, j, k, s;
        StringReference[] field, antiNodes;
        Coordinate[] A, c;
        Coordinate a, b, d, ac;

        c = new Coordinate[2];

        field = SplitByCharacter(input, '\n');

        antiNodes = CopyField(field);

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        for(y = 0d; y < h; y = y + 1d) {
            for(x = 0d; x < w; x = x + 1d) {
                SetCharacter(antiNodes, x, y, '.');
            }
        }

        symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        for(s = 0d; s < symbols.length; s = s + 1d) {
            // Find Antennas
            cs = 0d;
            for (y = 0d; y < h; y = y + 1d) {
                for (x = 0d; x < w; x = x + 1d) {
                    if (GetCharacter(field, x, y) == symbols[(int)s]) {
                        cs = cs + 1d;
                    }
                }
            }

            A = new Coordinate[(int) cs];
            cs = 0d;
            for (y = 0d; y < h; y = y + 1d) {
                for (x = 0d; x < w; x = x + 1d) {
                    if (GetCharacter(field, x, y) == symbols[(int)s]) {
                        A[(int)cs] = CreateCoordinate(x, y);
                        cs = cs + 1d;
                    }
                }
            }

            // Compute anti-nodes
            for (i = 0; i < A.length; i = i + 1d) {
                for (j = 0; j < A.length; j = j + 1d) {
                    if (i != j) {
                        a = A[(int)i];
                        b = A[(int)j];

                        // Distance
                        d = SubCoordinates(a, b);

                        // Candidates
                        c[0] = AddCoordinates(a, d);
                        c[1] = SubCoordinates(b, d);

                        for (k = 0d; k < c.length; k = k + 1d) {
                            ac = c[(int)k];
                            if (ac.x < 0 || ac.x >= w || ac.y < 0 || ac.y >= h) {
                                // Is outside
                            } else {
                                SetCharacter(antiNodes, ac.x, ac.y, '#');
                            }
                        }
                    }
                }
            }
        }

        // Count anti-nodes
        n = 0d;
        for(y = 0d; y < h; y = y + 1d) {
            for(x = 0d; x < w; x = x + 1d) {
                if(GetCharacter(antiNodes, x, y) == '#'){
                    n = n + 1d;
                }
            }
        }

        // Print
        //PrintField(field);
        //PrintField(antiNodes);

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static Coordinate AddCoordinates(Coordinate a, Coordinate b) {
        Coordinate c;
        c = new Coordinate();
        c.x = a.x + b.x;
        c.y = a.y + b.y;
        return c;
    }

    public static Coordinate SubCoordinates(Coordinate a, Coordinate b) {
        Coordinate c;
        c = new Coordinate();
        c.x = a.x - b.x;
        c.y = a.y - b.y;
        return c;
    }

    public static char[] ComputeDay8Part2(char[] input) {
        char [] output, symbols;
        double i, n,  w, h, x, y, cs, j, s;
        StringReference[] field, antiNodes;
        Coordinate[] A;
        Coordinate a, b, d, da, db, p;

        field = SplitByCharacter(input, '\n');

        antiNodes = CopyField(field);

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        for(y = 0d; y < h; y = y + 1d) {
            for(x = 0d; x < w; x = x + 1d) {
                SetCharacter(antiNodes, x, y, '.');
            }
        }

        symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        for(s = 0d; s < symbols.length; s = s + 1d) {
            // Count Antennas
            cs = 0d;
            for (y = 0d; y < h; y = y + 1d) {
                for (x = 0d; x < w; x = x + 1d) {
                    if (GetCharacter(field, x, y) == symbols[(int)s]) {
                        cs = cs + 1d;
                    }
                }
            }

            A = new Coordinate[(int) cs];
            cs = 0d;
            for (y = 0d; y < h; y = y + 1d) {
                for (x = 0d; x < w; x = x + 1d) {
                    if (GetCharacter(field, x, y) == symbols[(int)s]) {
                        A[(int)cs] = CreateCoordinate(x, y);
                        cs = cs + 1d;
                    }
                }
            }

            // Compute anti-nodes
            for (i = 0; i < A.length; i = i + 1d) {
                for (j = 0; j < A.length; j = j + 1d) {
                    if (i != j) {
                        a = A[(int)i];
                        b = A[(int)j];

                        // Distance
                        d = SubCoordinates(a, b);

                        // Check all points
                        for (y = 0d; y < h; y = y + 1d) {
                            for (x = 0d; x < w; x = x + 1d) {
                                p = CreateCoordinate(x, y);

                                // Check distance to A
                                da = SubCoordinates(p, a);
                                if(da.x / d.x == da.y / d.y){
                                    if(IsInteger(da.x / d.x) && IsInteger(da.y / d.y)) {
                                        SetCharacter(antiNodes, x, y, '#');
                                    }
                                }

                                // Check distance to B
                                db = SubCoordinates(p, b);

                                if(db.x / d.x == db.y / d.y) {
                                    if (IsInteger(db.x / d.x) && IsInteger(db.y / d.y)) {
                                        SetCharacter(antiNodes, x, y, '#');
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Count anti-nodes
        n = 0d;
        for(y = 0d; y < h; y = y + 1d) {
            for(x = 0d; x < w; x = x + 1d) {
                if(GetCharacter(antiNodes, x, y) == '#'){
                    n = n + 1d;
                }
            }
        }

        // Print
        //PrintField(field);
        //PrintField(antiNodes);

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static Coordinate CreateCoordinate(double x, double y) {
        Coordinate c;
        c = new Coordinate();
        c.x = x;
        c.y = y;
        return c;
    }

    static class Coordinate {
        public double x;
        public double y;
    }

    public static char[] ComputeDay9Part1(char[] input) {
        double n, len, i, id, blocks, free, p, j;
        char [] output;
        double [] ids;
        boolean swapped, done;

        // Compute length
        len = 0;
        for(i = 0; i < input.length; i = i + 1d){
            blocks = charCharacterToDecimalDigit(input[(int)i]);
            len = len + blocks;
        }

        // Create disk map
        ids = new double[(int)len];

        p = 0d;
        id = 0d;
        for(i = 0; i < input.length; i = i + 2d){
            blocks = charCharacterToDecimalDigit(input[(int)i]);
            free = 0d;
            if(i + 1d < input.length) {
                free = charCharacterToDecimalDigit(input[(int) (i + 1d)]);
            }

            for(j = 0d; j < blocks; j = j + 1d){
                ids[(int)p] = id;
                p = p + 1d;
            }

            for(j = 0d; j < free; j = j + 1d){
                ids[(int)p] = -1d;
                p = p + 1d;
            }

            id = id + 1d;
        }

        // Print
        //PrintDisk(len, ids);

        // Compact
        done = false;
        for(i = len-1d; i >= 0 && !done; i = i - 1d){
            if(ids[(int)i] != -1) {
                swapped = false;
                for (j = 0d; j < len && j < i && !swapped; j = j + 1d) {
                    if (ids[(int) j] == -1) {
                        ids[(int) j] = ids[(int) i];
                        ids[(int) i] = -1;
                        swapped = true;
                    }
                }

                if(!swapped){
                    done = true;
                }

                //PrintDisk(len, ids);
            }
        }

        // Compute checksum
        n = 0;
        for (i = 0d; i < len; i = i + 1d) {
            id = ids[(int) i];
            if(id != -1) {
                n = n + i * id;
            }
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static void PrintDisk(double len, double[] ids) {
        double i;
        for(i = 0; i < len; i = i + 1d){
            if(ids[(int)i] != -1) {
                System.out.print((int) ids[(int) i]);
            }else{
                System.out.print('.');
            }
        }
        System.out.println();
    }

    public static char[] ComputeDay9Part2(char[] input) {
        double n, len, i, id, blocks, free, p, j, maxid, freespace;
        char [] output;
        double [] ids;
        boolean done;
        File file;
        File [] files;

        // Compute length
        len = 0;
        for(i = 0; i < input.length; i = i + 1d){
            blocks = charCharacterToDecimalDigit(input[(int)i]);
            len = len + blocks;
        }

        ids = new double[(int)len];
        files = new File[(int)len];

        p = 0d;
        id = 0d;
        maxid = 0d;
        for(i = 0; i < input.length; i = i + 2d){
            file = new File();
            files[(int)id] = file;

            blocks = charCharacterToDecimalDigit(input[(int)i]);
            free = 0d;
            if(i + 1d < input.length) {
                free = charCharacterToDecimalDigit(input[(int) (i + 1d)]);
            }

            file.id = id;
            file.pos = p;
            file.length = blocks;

            for(j = 0d; j < blocks; j = j + 1d){
                ids[(int)p] = id;
                p = p + 1d;
            }

            for(j = 0d; j < free; j = j + 1d){
                ids[(int)p] = -1d;
                p = p + 1d;
            }

            maxid = id;
            id = id + 1d;

            //System.out.println((int)file.id + ", " + (int)file.pos + ", " + (int)file.length);
        }

        // Print
        //PrintDisk(len, ids);

        // Compact
        for(i = maxid; i >= 0; i = i - 1d){
            file = files[(int)i];

            // Find free block
            done = false;
            freespace = 0;
            p = 0d;
            for(j = 0d; j < len && !done; j = j + 1d){
                if(ids[(int)j] == -1){
                    freespace = freespace + 1d;
                }else{
                    freespace = 0d;
                    p = j+1d;
                }

                if(freespace == file.length){
                    done = true;
                }
            }

            // Move file
            if(done){
                if(p < file.pos) {
                    for (j = 0d; j < file.length; j = j + 1d) {
                        ids[(int) (p + j)] = i;
                        ids[(int) (file.pos + j)] = -1;
                    }
                }
            }

            //PrintDisk(len, ids);
        }

        // Compute checksum
        n = 0;
        for (i = 0d; i < len; i = i + 1d) {
            id = ids[(int) i];
            if(id != -1) {
                n = n + i * id;
            }
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    static class File{
        public double id;
        public double pos;
        public double length;
    }

    public static char[] ComputeDay10Part1(char[] input) {
        char [] output;
        double n, x, y, w, h, score;
        StringReference [] field, newField;

        n = 0d;

        field = SplitByCharacter(input, '\n');

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        for(y = 0d; y < h; y = y + 1d){
            for(x = 0d; x < w; x = x + 1d){
                if(GetCharacter(field, x, y) == '0'){
                    // Found trail head
                    newField = CopyField(field);

                    score = IterateTrail(newField, x, y, '0', true);

                    //System.out.println(score);
                    n = n + score;
                }
            }
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static double IterateTrail(StringReference[] field, double x, double y, char c, boolean clearEnd) {
        double w, h, score;
        char nc;

        score = 0d;

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        if(x >= 0d && x < w && y >= 0d && y < h) {
            if (GetCharacter(field, x, y) == c) {
                //PrintField(field);
                if(c == '9'){
                    score = 1d;
                    if(clearEnd) {
                        SetCharacter(field, x, y, 'X');
                    }
                }else{
                    nc = NextChar(c);

                    score = score + IterateTrail(field, x - 1d, y, nc, clearEnd);
                    score = score + IterateTrail(field, x + 1d, y, nc, clearEnd);
                    score = score + IterateTrail(field, x, y - 1d, nc, clearEnd);
                    score = score + IterateTrail(field, x, y + 1d, nc, clearEnd);
                }
            }
        }

        return score;
    }

    public static char NextChar(char c) {
        double n;

        n = charCharacterToDecimalDigit(c);
        n = n + 1d;
        c = charDecimalDigitToCharacter(n);

        return c;
    }

    public static char[] ComputeDay10Part2(char[] input) {
        char [] output;
        double n, x, y, w, h, score;
        StringReference [] field, newField;

        n = 0d;

        field = SplitByCharacter(input, '\n');

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        for(y = 0d; y < h; y = y + 1d){
            for(x = 0d; x < w; x = x + 1d){
                if(GetCharacter(field, x, y) == '0'){
                    // Found trail head
                    newField = CopyField(field);
                    score = IterateTrail(newField, x, y, '0', false);

                    //System.out.println(score);
                    n = n + score;
                }
            }
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static char[] ComputeDay11Part1(char[] input) {
        char [] output;
        double n, i;
        double [] numbers;
        RedBlackTree cache;

        // Read numbers into array
        ReplaceCharacter(input, ' ', ',');
        numbers = StringToNumberArray(input);

        // Compute with cache
        cache = CreateRedBlackTree();
        n = 0d;
        for(i = 0; i < numbers.length; i = i + 1d){
            n = n + ApplyRuleRecurse(numbers[(int) i], 25d, 0d, cache);
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static char[] ComputeDay11Part2(char[] input) {
        char [] output;
        double n, i;
        double [] numbers;
        RedBlackTree cache;

        // Read numbers into array
        ReplaceCharacter(input, ' ', ',');
        numbers = StringToNumberArray(input);

        // Compute with cache
        cache = CreateRedBlackTree();
        n = 0d;
        for(i = 0; i < numbers.length; i = i + 1d){
            n = n + ApplyRuleRecurse(numbers[(int) i], 75d, 0d, cache);
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static double ApplyRuleRecurse(double n, double maxDepth, double depth, RedBlackTree cache) {
        double digits, p1, p2, count;
        RedBlackNode node;
        BooleanReference found;
        double key;
        char [] keyStr;
        Data cData;

        found = CreateBooleanReference(false);

        key = n * maxDepth + depth;

        if (depth == maxDepth) {
            count = 1d;
        }else {
            keyStr = CreateStringDecimalFromNumber(key);
            node = Search(cache, keyStr, found);

            if(found.booleanValue){
                count = node.value.number;
            } else  {
                digits = floor(log10(n)) + 1d;

                if (n == 0d) {
                    n = 1d;
                    count = ApplyRuleRecurse(n, maxDepth, depth + 1d, cache);
                } else if (digits % 2d == 0d) {
                    digits = digits / 2d;
                    p1 = floor(n / pow(10, digits));
                    p2 = n % pow(10, digits);

                    count = ApplyRuleRecurse(p1, maxDepth, depth + 1d, cache);
                    count = count + ApplyRuleRecurse(p2, maxDepth, depth + 1d, cache);
                } else {
                    n = n * 2024;
                    count = ApplyRuleRecurse(n, maxDepth, depth + 1d, cache);
                }

                cData = CreateNumberData(count);
                InsertData(cache, keyStr, cData);
            }
        }

        return count;
    }

    public static char[] ComputeDay12Part1(char[] input) {
        char [] output;
        double n;
        boolean found;
        NumberReference areaRef, perimRef;
        StringReference [] field;

        // Read numbers into array
        field = SplitByCharacter(input, '\n');

        // Find fields
        n = 0d;
        found = true;
        areaRef = new NumberReference();
        perimRef = new NumberReference();

        for(;found;){
            found = FindField(field, areaRef, perimRef);
            if(found){
                n = n + areaRef.numberValue * perimRef.numberValue;
            }
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static boolean FindField(StringReference[] field, NumberReference areaRef, NumberReference perimRef) {
        boolean found;
        double w, h, x, y, cx, cy;
        char c;

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        c = ' ';
        cx = 0d;
        cy = 0d;
        areaRef.numberValue = 0d;
        perimRef.numberValue = 0d;

        // Find unspent field
        found = false;
        for(y = 0d; y < h && !found; y = y + 1d){
            for(x = 0d; x < w && !found; x = x + 1d){
                c = GetCharacter(field, x, y);
                if(c != '.'){
                    cx = x;
                    cy = y;
                    found = true;
                }
            }
        }

        if(found){
            RecurseField(field, c, cx, cy, areaRef, perimRef);
            ClearFoundField(field, cx, cy);
        }

        return found;
    }

    public static void ClearFoundField(StringReference[] field, double x, double y) {
        char cc;

        cc = GetCharacterWithBoundsCheck(field, x, y);
        if (cc == '*') {
            SetCharacter(field, x, y, '.');

            ClearFoundField(field, x + 1d, y);
            ClearFoundField(field, x - 1d, y);
            ClearFoundField(field, x, y + 1d);
            ClearFoundField(field, x, y - 1d);
        }
    }

    public static void RecurseField(StringReference[] field, char c, double x, double y, NumberReference areaRef, NumberReference perimRef) {
        double edge;

        if (GetCharacterWithBoundsCheck(field, x, y) == c) {
            SetCharacter(field, x, y, '*');

            // Compute area
            areaRef.numberValue = areaRef.numberValue + 1d;

            // Compute perimeter
            edge = 0d;
            if(!IsSameField(field, x+1d, y, c)){
                edge = edge + 1d;
            }
            if(!IsSameField(field, x-1d, y, c)){
                edge = edge + 1d;
            }
            if(!IsSameField(field, x, y+1d, c)){
                edge = edge + 1d;
            }
            if(!IsSameField(field, x, y-1d, c)){
                edge = edge + 1d;
            }

            perimRef.numberValue = perimRef.numberValue + edge;

            // Recurse
            RecurseField(field, c, x + 1d, y, areaRef, perimRef);
            RecurseField(field, c, x - 1d, y, areaRef, perimRef);
            RecurseField(field, c, x, y + 1d, areaRef, perimRef);
            RecurseField(field, c, x, y - 1d, areaRef, perimRef);
        }
    }

    public static boolean IsSameField(StringReference[] field, double x, double y, char c) {
        return GetCharacterWithBoundsCheck(field, x, y) == c || GetCharacterWithBoundsCheck(field, x, y) == '*';
    }

    public static char GetCharacterWithBoundsCheck(StringReference[] field, double x, double y) {
        char c;
        double w, h;

        c = ' ';

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        if (x >= 0d && x < w && y >= 0d && y < h) {
            c = field[(int) y].string[(int) x];
        }

        return c;
    }

    public static char[] ComputeDay12Part2(char[] input) {
        char [] output;
        double n;
        boolean found;
        NumberReference areaRef, perimRef;
        StringReference[] field;

        // Read numbers into array
        field = SplitByCharacter(input, '\n');

        // Compute fields
        n = 0d;
        found = true;
        areaRef = new NumberReference();
        perimRef = new NumberReference();
        for(;found;){
            found = FindField2(field, areaRef, perimRef);
            if(found){
                n = n + areaRef.numberValue * perimRef.numberValue;
            }
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static boolean FindField2(StringReference[] field, NumberReference areaRef, NumberReference perimRef) {
        boolean found;
        double w, h, x, y, cx, cy;
        char c;

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        c = ' ';
        cx = 0d;
        cy = 0d;
        areaRef.numberValue = 0d;
        perimRef.numberValue = 0d;

        // Find unspent field
        found = false;
        for(y = 0d; y < h && !found; y = y + 1d){
            for(x = 0d; x < w && !found; x = x + 1d){
                c = GetCharacter(field, x, y);
                if(c != '.'){
                    cx = x;
                    cy = y;
                    found = true;
                }
            }
        }

        if(found){
            RecurseField2(field, c, cx, cy, areaRef, perimRef);
            ClearFoundField(field, cx, cy);
        }

        return found;
    }

    public static void RecurseField2(StringReference[] field, char c, double x, double y, NumberReference areaRef, NumberReference perimRef) {
        double corner;

        if (GetCharacterWithBoundsCheck(field, x, y) == c) {
            SetCharacter(field, x, y, '*');

            // Compute area
            areaRef.numberValue = areaRef.numberValue + 1d;

            // Compute corners
            corner = 0d;
            // up, right
            if(!IsSameField(field, x, y-1d, c) && !IsSameField(field, x+1d, y, c)){
                corner = corner + 1d;
            }else if(IsSameField(field, x, y-1d, c) && IsSameField(field, x+1d, y, c) && !IsSameField(field, x+1d, y-1d, c)){
                corner = corner + 1d;
            }
            // down, right
            if(!IsSameField(field, x, y+1d, c) && !IsSameField(field, x+1d, y, c)){
                corner = corner + 1d;
            }else if(IsSameField(field, x, y+1d, c) && IsSameField(field, x+1d, y, c) && !IsSameField(field, x+1d, y+1d, c)){
                corner = corner + 1d;
            }
            // down, left
            if(!IsSameField(field, x, y+1d, c) && !IsSameField(field, x-1d, y, c)){
                corner = corner + 1d;
            }else if(IsSameField(field, x, y+1d, c) && IsSameField(field, x-1d, y, c) && !IsSameField(field, x-1d, y+1d, c)){
                corner = corner + 1d;
            }
            // up, left
            if(!IsSameField(field, x, y-1d, c) && !IsSameField(field, x-1d, y, c)){
                corner = corner + 1d;
            }else if(IsSameField(field, x, y-1d, c) && IsSameField(field, x-1d, y, c) && !IsSameField(field, x-1d, y-1d, c)){
                corner = corner + 1d;
            }

            perimRef.numberValue = perimRef.numberValue + corner;

            // Recurse
            RecurseField2(field, c, x + 1d, y, areaRef, perimRef);
            RecurseField2(field, c, x - 1d, y, areaRef, perimRef);
            RecurseField2(field, c, x, y + 1d, areaRef, perimRef);
            RecurseField2(field, c, x, y - 1d, areaRef, perimRef);
        }
    }

    public static char[] ComputeDay13Part1(char[] input) {
        return ComputeDay13(input, 0d);
    }

    public static char[] ComputeDay13Part2(char[] input) {
        return ComputeDay13(input, 10000000000000d);
    }

    public static char[] ComputeDay13(char[] input, double add) {
        char [] output, line;
        double n, i, puzzles, pnr, cost;
        StringReference[] lines, parts, p2, p3;
        Puzzle p;
        Puzzle [] ps;

        // Read numbers into array
        lines = SplitByCharacter(input, '\n');

        // Count
        puzzles = ceil(lines.length / 4d);
        ps = new Puzzle[(int)puzzles];

        pnr = 0;
        for(i = 0; i < lines.length; i = i + 2d){
            p = new Puzzle();

            // Button A
            line = lines[(int)i].string;
            parts = SplitByCharacter(line, ',');
            p2 = SplitByCharacter(parts[0].string, '+');
            p3 = SplitByCharacter(parts[1].string, '+');

            p.a = new Coordinate();
            p.a.x = CreateNumberFromDecimalString(p2[1].string);
            p.a.y = CreateNumberFromDecimalString(p3[1].string);

            // Button B
            i = i + 1d;
            line = lines[(int)i].string;
            parts = SplitByCharacter(line, ',');
            p2 = SplitByCharacter(parts[0].string, '+');
            p3 = SplitByCharacter(parts[1].string, '+');

            p.b = new Coordinate();
            p.b.x = CreateNumberFromDecimalString(p2[1].string);
            p.b.y = CreateNumberFromDecimalString(p3[1].string);

            // Prize
            i = i + 1d;
            line = lines[(int)i].string;
            parts = SplitByCharacter(line, ',');
            p2 = SplitByCharacter(parts[0].string, '=');
            p3 = SplitByCharacter(parts[1].string, '=');

            p.c = new Coordinate();
            p.c.x = CreateNumberFromDecimalString(p2[1].string);
            p.c.y = CreateNumberFromDecimalString(p3[1].string);

            p.c.x = p.c.x + add;
            p.c.y = p.c.y + add;

            ps[(int)pnr] = p;
            pnr = pnr + 1d;
        }

        // Solve puzzle
        n = 0d;
        for(i = 0d; i < puzzles; i = i + 1d){
            p = ps[(int)i];

            cost = FindPuzzleSolutions(p);

            n = n + cost;
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static void PrintCoordinate(Coordinate sum) {
        System.out.println((long)sum.x + ", " + (long)sum.y);
    }

    static class Puzzle{
        public Coordinate a;
        public Coordinate b;
        public Coordinate c;
    }

    public static double FindPuzzleSolutions(Puzzle p) {
        double cost, x, y;
        Coordinate a, b, c;
        Matrix M, X, P, R;

        cost = 0d;

        a = p.a;
        b = p.b;
        c = p.c;

        M = CreateSquareMatrix(2d);
        SetMatrixElement(M, 0d, 0d, a.x);
        SetMatrixElement(M, 1d, 0d, a.y);
        SetMatrixElement(M, 0d, 1d, b.x);
        SetMatrixElement(M, 1d, 1d, b.y);

        X = CreateSquareMatrix(2d);
        Inverse(M, X);
        R = CreateMatrix(2d, 1d);
        P = CreateMatrix(2d, 1d);

        SetMatrixElement(P, 0d, 0d, c.x);
        SetMatrixElement(P, 1d, 0d, c.y);

        Multiply(R, X, P);

        x = Element(R, 0d, 0d);
        y = Element(R, 1d, 0d);

        x = round(x);
        y = round(y);

        if(a.x * x + b.x * y == c.x){
            if(a.y * x + b.y * y == c.y) {
                cost = x * 3d + y;
            }
        }

        return cost;
    }

    public static void SetMatrixElement(Matrix M, double m, double n, double val){
        M.r[(int)(m)].c[(int)(n)] = val;
    }

    public static char[] ComputeDay14Part1(char[] input, double tw, double th) {
        char [] output, line;
        double n, i;
        StringReference [] lines;
        Robot [] robots;
        Robot robot, r;
        double q1, q2, q3, q4;
        double [] rs;

        // Read numbers into array
        lines = SplitByCharacter(input, '\n');

        // Read robot specs
        robots = new Robot[lines.length];
        for(i = 0; i < lines.length; i = i + 1d){
            line = lines[(int)i].string;
            line = ReplaceString(line, "p=".toCharArray(), "".toCharArray());
            line = ReplaceString(line, "v=".toCharArray(), "".toCharArray());
            ReplaceCharacter(line, ' ', ',');
            rs = StringToNumberArray(line);

            r = new Robot();
            r.x = rs[0];
            r.y = rs[1];
            r.dx = rs[2];
            r.dy = rs[3];
            robots[(int)i] = r;
        }

        // Simulate
        for(i = 0d; i < 100d; i = i + 1d) {
            RunSimulation(robots, tw, th);
        }

        // Compute robots per quadrant
        q1 = 0d;
        q2 = 0d;
        q3 = 0d;
        q4 = 0d;
        for(i = 0; i < robots.length; i = i + 1d) {
            robot = robots[(int) i];

            // top, left
            if(robot.x < floor(tw / 2d) && robot.y < floor(th / 2d)){
                q1 = q1 + 1d;
            }

            // top, right
            if(robot.x > floor(tw / 2d) && robot.y < floor(th / 2d)){
                q2 = q2 + 1d;
            }

            // bottom, left
            if(robot.x < floor(tw / 2d) && robot.y > floor(th / 2d)){
                q3 = q3 + 1d;
            }

            // bottom, right
            if(robot.x > floor(tw / 2d) && robot.y > floor(th / 2d)){
                q4 = q4 + 1d;
            }
        }

        n = q1 * q2 * q3 * q4;

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static void RunSimulation(Robot[] robots, double w, double h) {
        double i;
        Robot robot;

        for(i = 0; i < robots.length; i = i + 1d){
            robot = robots[(int)i];

            robot.x = robot.x + robot.dx;
            robot.y = robot.y + robot.dy;

            if(robot.x < 0){
                robot.x = w + robot.x;
            }

            if(robot.x >= w){
                robot.x = robot.x - w;
            }

            if(robot.y < 0){
                robot.y = h + robot.y;
            }

            if(robot.y >= h){
                robot.y = robot.y - h;
            }
        }
    }

    public static void PrintRobots(Robot[] robots, double w, double h) {
        double i, x, y, rf;
        Robot robot;

        for (y = 0; y < h; y = y + 1d) {
            for (x = 0; x < w; x = x + 1d) {

                rf = 0d;
                for (i = 0; i < robots.length; i = i + 1d) {
                    robot = robots[(int) i];

                    if (robot.x == x && robot.y == y) {
                        rf = rf + 1d;
                    }
                }

                if (rf == 0) {
                    System.out.print('.');
                } else {
                    System.out.print((long) rf);
                }
            }
            System.out.print('\n');
        }
    }

    static class Robot{
        public double x;
        public double y;
        public double dx;
        public double dy;
    }

    public static char[] ComputeDay14Part2(char[] input, double tw, double th) {
        char [] output, line;
        double n, i, j, x, y, rf, maxrf;
        StringReference [] lines;
        Robot [] robots;
        Robot robot, r;
        boolean found;
        double [] rs;

        // Read numbers into array
        lines = SplitByCharacter(input, '\n');

        // Read robot specs
        robots = new Robot[lines.length];
        for(i = 0; i < lines.length; i = i + 1d){
            line = lines[(int)i].string;
            line = ReplaceString(line, "p=".toCharArray(), "".toCharArray());
            line = ReplaceString(line, "v=".toCharArray(), "".toCharArray());
            ReplaceCharacter(line, ' ', ',');
            rs = StringToNumberArray(line);

            r = new Robot();
            r.x = rs[0];
            r.y = rs[1];
            r.dx = rs[2];
            r.dy = rs[3];
            robots[(int)i] = r;
        }

        // Simulate
        found = false;
        for(i = 0; i < 28000d && !found; i = i + 1d) {
            RunSimulation(robots, tw, th);

            // This if is to make the program run quicker for normal testing.
            if(i > 7000d) {

                // Count robots in each spot
                maxrf = 0d;
                for (y = 0d; y < th; y = y + 1d) {
                    for (x = 0d; x < tw; x = x + 1d) {

                        rf = 0d;
                        for (j = 0; j < robots.length; j = j + 1d) {
                            robot = robots[(int) j];

                            if (robot.x == x && robot.y == y) {
                                rf = rf + 1d;
                            }
                        }

                        maxrf = max(rf, maxrf);
                    }
                }

                if (maxrf == 1) {
                    found = true;
                    //PrintRobots(robots, tw, th);
                }
            }
        }

        n = i;

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }
}























