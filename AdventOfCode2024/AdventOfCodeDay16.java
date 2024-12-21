package AdventOfCode.AdventOfCode;

import references.references.*;

import static AdventOfCode.AdventOfCode.AdventOfCodeLib.*;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static references.references.references.*;
import static strings.strings.strings.SplitByCharacter;

public class AdventOfCodeDay16 {
    public static char[] ComputeDay16Part1(char[] input) {
        char [] output;
        double n, w, h, x, y;
        StringReference [] field;
        NumberArrayReference [] scores;
        BooleanArrayReference [] determined;
        char dir;
        Coordinate rd, end;

        // Read field
        field = SplitByCharacter(input, '\n');
        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        // Find Rain deer position
        rd = new Coordinate();
        end = new Coordinate();

        dir = '>'; // Directions: ^ > v <
        for(y = 0d; y < h; y = y + 1d){
            for(x = 0d; x < w; x = x + 1d){
                if(GetCharacter(field, x, y) == 'S'){
                    rd.x = x;
                    rd.y = y;
                }
                if(GetCharacter(field, x, y) == 'E'){
                    end.x = x;
                    end.y = y;
                }
            }
        }

        // Initialize arrays
        scores = new NumberArrayReference[(int)h];
        determined = new BooleanArrayReference[(int)h];
        for(y = 0d; y < h; y = y + 1d){
            scores[(int)y] = CreateNumberArrayReferenceLengthValue(w, 0d);
            determined[(int)y] = CreateBooleanArrayReferenceLengthValue(w, false);
        }

        // Search
        Dijkstras(field, scores, determined, rd.x, rd.y, 0d, dir);

        // Find end score
        n = GetNumber(scores, end.x, end.y);

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static boolean Acceptable(StringReference[] field, double x, double y) {
        return GetCharacter(field, x, y) == '.' || GetCharacter(field, x, y) == 'E';
    }

    public static void Dijkstras(StringReference[] field, NumberArrayReference[] scores, BooleanArrayReference[] determined, double x, double y, double score, char dir) {
        double newScore;
        boolean done, skip;

        done = false;
        newScore = 0d;

        if(!GetBoolean(determined, x, y)){
            SetNumber(scores, x, y, score);
            SetBoolean(determined, x, y, true);
        }else{
            if(score < GetNumber(scores, x, y)){
                SetNumber(scores, x, y, score);
            }else{
                done = true;
            }
        }

        if(!done) {
            // Up
            if (Acceptable(field, x, y - 1d)) {
                skip = false;
                if (dir == '^') {
                    newScore = score + 1d;
                } else {
                    if(dir == 'v'){
                        skip = true;
                    }else {
                        newScore = score + 1000d + 1d;
                    }
                }
                if(!skip) {
                    Dijkstras(field, scores, determined, x, y - 1d, newScore, '^');
                }
            }
            // Down
            if (Acceptable(field, x, y + 1d)) {
                skip = false;
                if (dir == 'v') {
                    newScore = score + 1d;
                } else {
                    if(dir == '^'){
                        skip = true;
                    }else {
                        newScore = score + 1000d + 1d;
                    }
                }
                if(!skip) {
                    Dijkstras(field, scores, determined, x, y + 1d, newScore, 'v');
                }
            }
            // Left
            if (Acceptable(field, x - 1d, y)) {
                skip = false;
                if (dir == '<') {
                    newScore = score + 1d;
                } else {
                    if(dir == '>'){
                        skip = true;
                    }else {
                        newScore = score + 1000d + 1d;
                    }
                }
                if(!skip) {
                    Dijkstras(field, scores, determined, x - 1d, y, newScore, '<');
                }
            }
            // Right
            if (Acceptable(field, x + 1d, y)) {
                skip = false;
                if (dir == '>') {
                    newScore = score + 1d;
                } else {
                    if(dir == '<'){
                        skip = true;
                    }else {
                        newScore = score + 1000d + 1d;
                    }
                }
                if(!skip) {
                    Dijkstras(field, scores, determined, x + 1d, y, newScore, '>');
                }
            }
        }
    }

    public static char[] ComputeDay16Part2(char[] input) {
        char [] output;
        double n, w, h, x, y;
        StringReference [] field, markField;
        NumberArrayReference [] scores;
        BooleanArrayReference [] determined;
        char dir;
        Coordinate rd, end;

        // Read field
        field = SplitByCharacter(input, '\n');
        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        // Find Rain deer position
        rd = new Coordinate();
        end = new Coordinate();

        dir = '>'; // Directions: ^ > v <
        for(y = 0d; y < h; y = y + 1d){
            for(x = 0d; x < w; x = x + 1d){
                if(GetCharacter(field, x, y) == 'S'){
                    rd.x = x;
                    rd.y = y;
                }
                if(GetCharacter(field, x, y) == 'E'){
                    end.x = x;
                    end.y = y;
                }
            }
        }

        // Initialize arrays
        scores = new NumberArrayReference[(int)h];
        determined = new BooleanArrayReference[(int)h];
        for(y = 0d; y < h; y = y + 1d){
            scores[(int)y] = CreateNumberArrayReferenceLengthValue(w, 0d);
            determined[(int)y] = CreateBooleanArrayReferenceLengthValue(w, false);
        }

        // Search
        Dijkstras(field, scores, determined, rd.x, rd.y, 0d, dir);

        // Color best paths
        markField = CopyField(field);
        ColorBest(markField, field, scores, rd.x, rd.y);

        // Find end score
        n = 0d;
        for(y = 0d; y < h; y = y + 1d){
            for(x = 0d; x < w; x = x + 1d){
                if(GetCharacter(markField, x, y) == 'O'){
                    n = n + 1d;
                }
            }
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static boolean ColorBest(StringReference[] markfield, StringReference[] field, NumberArrayReference[] scores, final double x, final double y) {
        double score, offset;
        boolean reachedTheEnd, best, pathReachedEnd;

        score = GetNumber(scores, x, y);

        reachedTheEnd = GetCharacter(field, x, y) == 'E';

        // Up
        if (Acceptable(field, x, y - 1d)) {
            best = false;
            offset = 1d;

            if (GetNumber(scores, x, y - 1d) == score + 1d) {
                best = true;
            }else if (GetNumber(scores, x, y - 1d) == score + 1000d + 1d) {
                best = true;
            }else if (GetNumber(scores, x, y - 2d) == score + 2d) {
                if(
                    Acceptable(field, x + 1d, y - 1d) ||
                    Acceptable(field, x - 1d, y - 1d)
                ){
                    best = true;
                    offset = 2d;
                }
            }

            if(best) {
                pathReachedEnd = ColorBest(markfield, field, scores, x, y - offset);

                reachedTheEnd = reachedTheEnd || pathReachedEnd;

                if(offset == 2d && reachedTheEnd){
                    SetCharacter(markfield, x, y - 1d, 'O');
                }
            }
        }
        // Down
        if (Acceptable(field, x, y + 1d)) {
            best = false;
            offset = 1d;

            if (GetNumber(scores, x, y + 1d) == score + 1d) {
                best = true;
            }else if (GetNumber(scores, x, y + 1d) == score + 1000d + 1d) {
                best = true;
            }else if (GetNumber(scores, x, y + 2d) == score + 2d) {
                if(
                    Acceptable(field, x + 1d, y + 1d) ||
                    Acceptable(field, x - 1d, y + 1d)
                ){
                    best = true;
                    offset = 2d;
                }
            }

            if(best) {
                pathReachedEnd = ColorBest(markfield, field, scores, x, y + offset);

                reachedTheEnd = reachedTheEnd || pathReachedEnd;

                if(offset == 2d && reachedTheEnd){
                    SetCharacter(markfield, x, y + 1d, 'O');
                }
            }
        }
        // Left
        if (Acceptable(field, x - 1d, y)) {
            best = false;
            offset = 1d;

            if (GetNumber(scores, x - 1d, y) == score + 1d) {
                best = true;
            }else if (GetNumber(scores, x - 1d, y) == score + 1000d + 1d) {
                best = true;
            }else if (GetNumber(scores, x - 2d, y) == score + 2d) {
                if(
                    Acceptable(field, x - 1d, y - 1d) ||
                    Acceptable(field, x - 1d, y + 1d)
                ){
                    best = true;
                    offset = 2d;
                }
            }

            if(best) {
                pathReachedEnd = ColorBest(markfield, field, scores, x - offset, y);

                reachedTheEnd = reachedTheEnd || pathReachedEnd;

                if(offset == 2d && reachedTheEnd){
                    SetCharacter(markfield, x - 1d, y, 'O');
                }
            }
        }
        // Right
        if (Acceptable(field, x + 1d, y)) {
            best = false;
            offset = 1d;

            if (GetNumber(scores, x + 1d, y) == score + 1d) {
                best = true;
            }else if (GetNumber(scores, x + 1d, y) == score + 1000d + 1d) {
                best = true;
            }else if (GetNumber(scores, x + 2d, y) == score + 2d) {
                if(
                    Acceptable(field, x + 1d, y - 1d) ||
                    Acceptable(field, x + 1d, y + 1d)
                ){
                    best = true;
                    offset = 2d;
                }
            }

            if(best) {
                pathReachedEnd = ColorBest(markfield, field, scores, x + offset, y);

                reachedTheEnd = reachedTheEnd || pathReachedEnd;

                if(offset == 2d && reachedTheEnd){
                    SetCharacter(markfield, x + 1d, y, 'O');
                }
            }
        }

        if(reachedTheEnd){
            SetCharacter(markfield, x, y, 'O');
        }

        return reachedTheEnd;
    }

    // Lib

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
}































