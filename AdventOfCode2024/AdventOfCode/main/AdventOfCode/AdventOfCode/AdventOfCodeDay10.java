package AdventOfCode.AdventOfCode;

import references.references.StringReference;

import static aarrays.arrays.arrays.aCopyString;
import static charCharacters.Characters.Characters.charCharacterToDecimalDigit;
import static charCharacters.Characters.Characters.charDecimalDigitToCharacter;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static strings.strings.strings.*;

public class AdventOfCodeDay10 {
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

    public static int GetFieldHeight(StringReference[] field) {
        return field.length;
    }

    public static int GetFieldWidth(StringReference[] field) {
        return field[0].string.length;
    }

    public static char GetCharacter(StringReference[] field, double x, double y) {
        return field[(int)y].string[(int)x];
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

    public static void SetCharacter(StringReference[] field, double x, double y, char c) {
        field[(int)y].string[(int)x] = c;
    }
}
