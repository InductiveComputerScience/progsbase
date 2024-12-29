package AdventOfCode.AdventOfCode;

import references.references.BooleanArrayReference;
import references.references.NumberArrayReference;
import references.references.StringReference;

import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalString;
import static references.references.references.*;
import static strings.strings.strings.SplitByCharacter;

public class AdventOfCodeDay18 {
    public static char[] ComputeDay18Part1(char[] input, double w, double h, double s) {
        StringReference [] lines, field, parts;
        double i, n, x, y;
        Coordinate [] bytes;
        BooleanArrayReference [] set;
        char [] output;

        w = w + 1d;
        h = h + 1d;

        // Read bytes
        lines = SplitByCharacter(input, '\n');
        bytes = new Coordinate[(int)lines.length];
        for(i = 0d; i < lines.length; i = i + 1d){
            parts = SplitByCharacter(lines[(int) i].string, ',');
            x = CreateNumberFromDecimalString(parts[0].string);
            y = CreateNumberFromDecimalString(parts[1].string);
            bytes[(int)i] = CreateCoordinate(x, y);
        }

        // Create field
        field = new StringReference[(int)h];
        NumberArrayReference [] distances = new NumberArrayReference[(int)h];
        set = new BooleanArrayReference[(int)h];
        for(i = 0; i < h; i = i + 1d){
            field[(int)i] = CreateStringReferenceLengthValue(w, '.');
            distances[(int)i] = CreateNumberArrayReferenceLengthValue(w, 0);
            set[(int)i] = CreateBooleanArrayReferenceLengthValue(w, false);
        }

        // Add corrupted
        for(i = 0; i < s; i = i + 1d){
            SetCharacter(field, bytes[(int)i].x, bytes[(int)i].y, '#');
        }

        // Find the shortest length
        RecurseFindShortest(field, distances, set, 0d, 0d, 0d);

        // Produce output
        n = GetNumber(distances, w-1d, h-1d);
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static void RecurseFindShortest(StringReference[] field, NumberArrayReference[] distances, BooleanArrayReference[] set, double x, double y, double l) {
        boolean dig;

        dig = false;

        if(GetBoolean(set, x, y)){

        }else{
            SetNumber(distances, x, y, l);
            SetBoolean(set, x, y, true);
            dig = true;
        }

        if(l < GetNumber(distances, x, y)) {
            SetNumber(distances, x, y, l);
            SetBoolean(set, x, y, true);
            dig = true;
        }

        if(dig){
            // Up
            if(GetCharacterWithBoundsCheck(field, x, y - 1d) == '.'){
                RecurseFindShortest(field, distances, set, x, y - 1d, l + 1d);
            }

            // Down
            if(GetCharacterWithBoundsCheck(field, x, y + 1d) == '.'){
                RecurseFindShortest(field, distances, set, x, y + 1d, l + 1d);
            }

            // Left
            if(GetCharacterWithBoundsCheck(field, x - 1d, y) == '.'){
                RecurseFindShortest(field, distances, set, x - 1d, y, l + 1d);
            }

            // Right
            if(GetCharacterWithBoundsCheck(field, x + 1d, y) == '.'){
                RecurseFindShortest(field, distances, set, x + 1d, y, l + 1d);
            }
        }
    }

    public static char[] ComputeDay18Part2(char[] input, double w, double h) {
        char [] output;
        StringReference [] lines, field, parts;
        double i, j, x, y;
        Coordinate [] bytes;
        BooleanArrayReference [] set;
        NumberArrayReference [] distances;
        boolean done;

        w = w + 1d;
        h = h + 1d;

        // Read bytes
        lines = SplitByCharacter(input, '\n');
        bytes = new Coordinate[(int)lines.length];
        for(i = 0d; i < lines.length; i = i + 1d){
            parts = SplitByCharacter(lines[(int) i].string, ',');
            x = CreateNumberFromDecimalString(parts[0].string);
            y = CreateNumberFromDecimalString(parts[1].string);
            bytes[(int)i] = CreateCoordinate(x, y);
        }

        // Create field
        field = new StringReference[(int)h];
        for(i = 0; i < h; i = i + 1d){
            field[(int)i] = CreateStringReferenceLengthValue(w, '.');
        }

        // Add corrupted
        done = false;
        for(i = 0; i < bytes.length && !done; i = i + 1d){
            SetCharacter(field, bytes[(int)i].x, bytes[(int)i].y, '#');

            System.out.println(i);

            // Initialize search
            distances = new NumberArrayReference[(int)h];
            set = new BooleanArrayReference[(int)h];
            for(j = 0; j < h; j = j + 1d){
                distances[(int)j] = CreateNumberArrayReferenceLengthValue(w, 0);
                set[(int)j] = CreateBooleanArrayReferenceLengthValue(w, false);
            }

            // Find the shortest path
            RecurseFindShortest(field, distances, set, 0d, 0d, 0d);

            // If none found, then we are blocked.
            if(!GetBoolean(set, w-1d, h-1d)){
                done = true;
            }
        }

        // Produce output
        i = i - 1d;
        output = lines[(int)i].string;

        return output;
    }

    // Lib
    public static int GetFieldHeight(StringReference[] field) {
        return field.length;
    }

    public static int GetFieldWidth(StringReference[] field) {
        return field[0].string.length;
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

    public static double GetNumber(NumberArrayReference[] field, double x, double y) {
        return field[(int)y].numberArray[(int)x];
    }

    public static boolean GetBoolean(BooleanArrayReference[] field, double x, double y) {
        return field[(int)y].booleanArray[(int)x];
    }

    public static void SetNumber(NumberArrayReference[] field, double x, double y, double n) {
        field[(int)y].numberArray[(int)x] = n;
    }

    public static void SetBoolean(BooleanArrayReference[] field, double x, double y, boolean b) {
        field[(int)y].booleanArray[(int)x] = b;
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

    public static void SetCharacter(StringReference[] field, double x, double y, char c) {
        field[(int)y].string[(int)x] = c;
    }
}