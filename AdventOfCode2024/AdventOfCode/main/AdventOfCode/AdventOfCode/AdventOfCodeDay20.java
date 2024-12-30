package AdventOfCode.AdventOfCode;

import references.references.BooleanArrayReference;
import references.references.NumberArrayReference;
import references.references.StringReference;

import static aarrays.arrays.arrays.aCopyString;
import static java.lang.Math.abs;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static references.references.references.*;
import static references.references.references.CreateBooleanArrayReferenceLengthValue;
import static strings.strings.strings.*;

public class AdventOfCodeDay20 {
    public static char[] ComputeDay20Part1(char[] input, double limit) {
        StringReference [] field;
        double n, x, y, w, h, distCheat, distNoCheat;
        char [] output;
        Coordinate start, end;
        NumberArrayReference [] distances;
        BooleanArrayReference [] set;

        start = CreateCoordinate(0, 0);
        end = CreateCoordinate(0, 0);

        // Read stripe types and expected combinations
        field = SplitByCharacter(input, '\n');

        //PrintField(field);

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        // Find start and ending
        for(y = 0; y < h; y = y + 1d){
            for(x = 0; x < w; x = x + 1d){
                if(GetCharacterWithBoundsCheck(field, x, y) == 'S'){
                    start = CreateCoordinate(x, y);
                    SetCharacter(field, x, y, '.');
                }
                if(GetCharacterWithBoundsCheck(field, x, y) == 'E'){
                    end = CreateCoordinate(x, y);
                    SetCharacter(field, x, y, '.');
                }
            }
        }

        // Find length from start to end
        distances = InitDistances(w, h);
        set = InitSet(w, h);
        RecurseFindShortest(field, distances, set, start.x, start.y, 0);

        distNoCheat = GetNumber(distances, end.x, end.y);

        // Find cheats that save time
        n = 0d;
        for(y = 0; y < h; y = y + 1d) {
            for (x = 0; x < w; x = x + 1d) {
                if(GetCharacterWithBoundsCheck(field, x, y) == '#'){
                    // Enable cheat
                    SetCharacter(field, x, y, '.');

                    // Get new distance:
                    distances = InitDistances(w, h);
                    set = InitSet(w, h);
                    RecurseFindShortest(field, distances, set, start.x, start.y, 0);

                    distCheat = GetNumber(distances, end.x, end.y);

                    if(distNoCheat - distCheat >= limit){
                        n = n + 1d;
                    }

                    // Reset cheat
                    SetCharacter(field, x, y, '#');
                }
            }
        }

        // Produce output
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

    public static Warp CreateWarp(double x, double y, double d) {
        Warp w;
        w = new Warp();
        w.x = x;
        w.y = y;
        w.d = d;
        return w;
    }

    static class Coordinate {
        public double x;
        public double y;
    }

    static class Warp {
        public double x;
        public double y;
        public double d;
    }

    public static void SetCharacter(StringReference[] field, double x, double y, char c) {
        field[(int)y].string[(int)x] = c;
    }

    public static char[] ComputeDay20Part2(char[] input, double limit, double mc) {
        StringReference [] field;
        double i, n, x, y, xi, yi, w, h, distCheat, distNoCheat, p;
        char [] output;
        Coordinate start, end;
        Warp [] warpsTo;
        Warp warp;
        NumberArrayReference [] distances;
        BooleanArrayReference [] set;
        double dist;

        start = CreateCoordinate(0, 0);
        end = CreateCoordinate(0, 0);

        // Read stripe types and expected combinations
        field = SplitByCharacter(input, '\n');

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        // Find start and ending
        for(y = 0; y < h; y = y + 1d){
            for(x = 0; x < w; x = x + 1d){
                if(GetCharacterWithBoundsCheck(field, x, y) == 'S'){
                    start = CreateCoordinate(x, y);
                    SetCharacter(field, x, y, '.');
                }
                if(GetCharacterWithBoundsCheck(field, x, y) == 'E'){
                    end = CreateCoordinate(x, y);
                    SetCharacter(field, x, y, '.');
                }
            }
        }

        // Find length from start to end
        distances = InitDistances(w, h);
        set = InitSet(w, h);
        RecurseFindShortest(field, distances, set, start.x, start.y, 0);

        distNoCheat = GetNumber(distances, end.x, end.y);

        // Find cheats that save time
        n = 0d;
        for(y = 0; y < h; y = y + 1d) {
            for (x = 0; x < w; x = x + 1d) {

                if(GetCharacterWithBoundsCheck(field, x, y) == '.'){

                    // Find warps
                    warpsTo = new Warp[(int)(w*h*20d)];
                    p = 0d;
                    for(yi = 0; yi < h; yi = yi + 1d) {
                        for (xi = 0; xi < w; xi = xi + 1d) {
                            if(xi == x && yi == y) {
                                // Skip
                            }else{
                                if (GetCharacterWithBoundsCheck(field, xi, yi) == '.') {
                                    //mc = 2d;

                                    dist = abs(xi - x) + abs(yi - y);
                                    if(dist <= mc) {
                                        warpsTo[(int) p] = CreateWarp(xi, yi, dist);
                                        p = p + 1d;
                                    }
                                }
                            }
                        }
                    }

                    // Every warp is one cheat
                    for(i = 0; i < p; i = i + 1d) {
                        warp = warpsTo[(int)i];

                        // Get new distance:
                        distances = InitDistances(w, h);
                        set = InitSet(w, h);
                        RecurseFindShortestLongCheat(field, x, y, warp, distances, set, start.x, start.y, 0);

                        distCheat = GetNumber(distances, end.x, end.y);

                        if (distNoCheat - distCheat >= limit) {
                            n = n + 1d;
                        }
                    }
                }
            }
        }

        // Produce output
        //System.out.println(n);
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    private static BooleanArrayReference[] InitSet(double w, double h) {
        BooleanArrayReference[] set;
        double j;
        set = new BooleanArrayReference[(int) h];
        for(j = 0; j < h; j = j + 1d){
            set[(int)j] = CreateBooleanArrayReferenceLengthValue(w, false);
        }
        return set;
    }

    private static NumberArrayReference[] InitDistances(double w, double h) {
        NumberArrayReference[] distances;
        double j;
        distances = new NumberArrayReference[(int) h];
        for(j = 0; j < h; j = j + 1d){
            distances[(int)j] = CreateNumberArrayReferenceLengthValue(w, 0);
        }
        return distances;
    }

    public static void RecurseFindShortestLongCheat(StringReference[] field, double wx, double wy, Warp w, NumberArrayReference[] distances, BooleanArrayReference[] set, double x, double y, double l) {
        boolean dig;

        dig = false;

        if (GetBoolean(set, x, y)) {

        } else {
            SetNumber(distances, x, y, l);
            SetBoolean(set, x, y, true);
            dig = true;
        }

        if (l < GetNumber(distances, x, y)) {
            SetNumber(distances, x, y, l);
            SetBoolean(set, x, y, true);
            dig = true;
        }


        if (dig) {
            // Warp
            if(x == wx && y == wy) {
                RecurseFindShortestLongCheat(field, wx, wy, w, distances, set, w.x, w.y, l + w.d);
            }

            // Up
            if (GetCharacterWithBoundsCheck(field, x, y - 1d) == '.') {
                RecurseFindShortestLongCheat(field, wx, wy, w, distances, set, x, y - 1d, l + 1d);
            }

            // Down
            if (GetCharacterWithBoundsCheck(field, x, y + 1d) == '.') {
                RecurseFindShortestLongCheat(field, wx, wy, w, distances, set, x, y + 1d, l + 1d);
            }

            // Left
            if (GetCharacterWithBoundsCheck(field, x - 1d, y) == '.') {
                RecurseFindShortestLongCheat(field, wx, wy, w, distances, set, x - 1d, y, l + 1d);
            }

            // Right
            if (GetCharacterWithBoundsCheck(field, x + 1d, y) == '.') {
                RecurseFindShortestLongCheat(field, wx, wy, w, distances, set, x + 1d, y, l + 1d);
            }
        }
    }
}






























