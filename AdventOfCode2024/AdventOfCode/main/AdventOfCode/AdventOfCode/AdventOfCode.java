package AdventOfCode.AdventOfCode;

import lists.LinkedListCharacters.Structures.LinkedListCharacters;
import lists.LinkedListStrings.Structures.LinkedListStrings;
import references.references.NumberArrayReference;
import references.references.NumberReference;
import references.references.StringReference;

import static QuickSort.QuickSort.QuickSort.QuickSortNumbers;
import static aarrays.arrays.arrays.aCopyString;
import static java.lang.Math.*;
import static lists.LinkedListCharacters.LinkedListCharactersFunctions.LinkedListCharactersFunctions.*;
import static lists.LinkedListStrings.LinkedListStringsFunctions.LinkedListStringsFunctions.*;
import static math.math.math.IsInteger;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalString;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalStringWithCheck;
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

        sum = 0;
        for(i = 0; i < n; i = i + 1d){
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

    private static int GetFieldHeight(StringReference[] field) {
        return field.length;
    }

    private static int GetFieldWidth(StringReference[] field) {
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
        char [] output;
        double i, n, w, h, x, y, cs, j, k, s;
        StringReference[] field, antiNodes;
        Coordinate[] A, c;
        Coordinate a, b, d, ac;
        char [] symbols;

        c = new Coordinate[4];

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
                        c[1] = SubCoordinates(a, d);
                        c[2] = AddCoordinates(b, d);
                        c[3] = SubCoordinates(b, d);

                        for (k = 0d; k < 4d; k = k + 1d) {
                            ac = c[(int)k];
                            if (ac.x == a.x && ac.y == a.y) {
                                // Is A
                            } else if (ac.x == b.x && ac.y == b.y) {
                                // Is B
                            } else if (ac.x < 0 || ac.x >= w || ac.y < 0 || ac.y >= h) {
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
        char [] output;
        double i, n,  w, h, x, y, cs, j, s;
        StringReference[] field, antiNodes;
        Coordinate[] A;
        Coordinate a, b, d, da, db, p;
        char [] symbols;

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
}










