package AdventOfCode.AdventOfCode;

import lists.LinkedListStrings.Structures.LinkedListStrings;
import references.references.*;

import static aarrays.arrays.arrays.aCopyString;
import static java.lang.Math.min;
import static lists.LinkedListStrings.LinkedListStringsFunctions.LinkedListStringsFunctions.*;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalString;
import static references.references.references.*;
import static strings.strings.strings.*;

public class AdventOfCodeDay21 {
    public static char[] ComputeDay21Part1(char[] input) {
        double n, i, j, k, numstartx, numstarty, dirstartx, dirstarty, minlen;
        char [] output, code, presses;
        StringReference [] numField, dirField, codes, shortest, nextShortest, robotArr;
        LinkedListStrings robot;

        // Read codes
        codes = SplitByCharacter(input, '\n');

        // Create fields
        numField = new StringReference[4];
        numField[0] = CreateStringReference("789".toCharArray());
        numField[1] = CreateStringReference("456".toCharArray());
        numField[2] = CreateStringReference("123".toCharArray());
        numField[3] = CreateStringReference(" 0A".toCharArray());

        numstartx = 2d;
        numstarty = 3d;

        dirField = new StringReference[2];
        dirField[0] = CreateStringReference(" ^A".toCharArray());
        dirField[1] = CreateStringReference("<v>".toCharArray());

        dirstartx = 2d;
        dirstarty = 0d;

        n = 0d;

        for(i = 0d; i < codes.length; i = i + 1d){
            code = codes[(int) i].string;

            // Keypad
            shortest = FindPresses(numField, code, numstartx, numstarty);

            // Robot 1
            {
                robot = CreateLinkedListString();

                minlen = 9e99d;
                for (j = 0d; j < shortest.length; j = j + 1d) {
                    presses = shortest[(int) j].string;
                    nextShortest = FindPresses(dirField, presses, dirstartx, dirstarty);

                    for (k = 0d; k < nextShortest.length; k = k + 1d) {
                        minlen = min(minlen, nextShortest[(int) k].string.length);
                        LinkedListAddString(robot, nextShortest[(int) k].string);
                    }
                }

                robotArr = LinkedListStringsToArray(robot);
                robot = CreateLinkedListString();

                for (j = 0d; j < robotArr.length; j = j + 1d) {
                    if (robotArr[(int) j].string.length == minlen) {
                        LinkedListAddString(robot, robotArr[(int) j].string);
                    }
                }

                shortest = LinkedListStringsToArray(robot);
            }

            // Robot 2
            {
                minlen = 9e99;
                for (j = 0d; j < shortest.length; j = j + 1d) {
                    nextShortest = FindPresses(dirField, shortest[(int) j].string, dirstartx, dirstarty);

                    for (k = 0d; k < nextShortest.length; k = k + 1d) {
                        minlen = min(minlen, nextShortest[(int) k].string.length);
                    }
                }
            }

            code = ReplaceString(code, "A".toCharArray(), "".toCharArray());
            n = n + minlen * CreateNumberFromDecimalString(code);
        }

        // Produce output
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static StringReference [] FindPresses(StringReference [] field, char [] code, double startx, double starty) {
        StringArrayReference [] lol;
        double i, endx, endy, x, y;
        char c;
        StringReference[] shortest;
        LinkedListStrings ll;

        endx = 0d;
        endy = 0d;

        lol = new StringArrayReference[code.length];

        for(i = 0d; i < code.length; i = i + 1d) {
            c = code[(int)i];
            for(y = 0d; y < GetFieldHeight(field); y = y + 1d) {
                for(x = 0d; x < GetFieldWidth(field); x = x + 1d) {
                    if(GetCharacterWithBoundsCheck(field, x, y) == c){
                        endx = x;
                        endy = y;
                    }
                }
            }

            lol[(int)i] = new StringArrayReference();
            lol[(int)i].stringArray = FindAllPresses(field, startx, starty, endx, endy);

            startx = endx;
            starty = endy;
        }

        ll = CreateLinkedListString();
        AllCombinations(ll, "".toCharArray(), lol, 0d);

        shortest = LinkedListStringsToArray(ll);

        return shortest;
    }

    public static void AllCombinations(LinkedListStrings ll, char[] str, StringArrayReference[] lol, double p) {
        double i;
        char [] newstr;

        if(p == lol.length){
            LinkedListAddString(ll, str);
        }else {
            for (i = 0d; i < lol[(int) p].stringArray.length; i = i + 1d) {
                newstr = ConcatenateString(str, lol[(int) p].stringArray[(int) i].string);
                AllCombinations(ll, newstr, lol, p + 1d);
            }
        }
    }

    public static StringReference [] FindAllPresses(StringReference[] field, double x, double y, double endx, double endy) {
        double dx, dy, dirx, diry;
        char ch, cv;
        char [] buffer;
        LinkedListStrings ll;

        dx = endx - x;
        dy = endy - y;

        if(dx < 0d){
            ch = '<';
            dx = -dx;
            dirx = -1d;
        }else{
            ch = '>';
            dirx = 1d;
        }

        if(dy < 0d){
            cv = '^';
            dy = -dy;
            diry = -1d;
        }else{
            cv = 'v';
            diry = 1d;
        }

        buffer = new char [(int)(dx + dy + 1d)];
        ll = CreateLinkedListString();

        FindAllValidPressesCombinations(ll, buffer, dx, dy, cv, ch, 0d, field, x, y, dirx, diry);

        return LinkedListStringsToArray(ll);
    }

    public static void FindAllValidPressesCombinations(LinkedListStrings ll, char [] buffer, double dx, double dy, char cv, char ch, double d, StringReference[] field, double x, double y, double dirx, double diry) {
        boolean skip;

        skip = GetCharacterWithBoundsCheck(field, x, y) == ' ';

        if(!skip) {
            if (dx == 0d && dy == 0d) {
                buffer[(int) d] = 'A';
                LinkedListAddString(ll, aCopyString(buffer));
            }

            if (dx != 0d) {
                buffer[(int) d] = ch;
                FindAllValidPressesCombinations(ll, buffer, dx - 1d, dy, cv, ch, d + 1d, field, x + dirx, y, dirx, diry);
            }
            if (dy != 0d) {
                buffer[(int) d] = cv;
                FindAllValidPressesCombinations(ll, buffer, dx, dy - 1d, cv, ch, d + 1d, field, x, y + diry, dirx, diry);
            }
        }
    }

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
}
