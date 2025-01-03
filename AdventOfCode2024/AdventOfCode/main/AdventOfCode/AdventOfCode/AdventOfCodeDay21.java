package AdventOfCode.AdventOfCode;

import Trees.RedBlackTrees.RedBlackNode;
import Trees.RedBlackTrees.RedBlackTree;
import lists.DynamicArrayCharacters.Structures.DynamicArrayCharacters;
import lists.LinkedListStrings.Structures.LinkedListStrings;
import references.references.*;

import static DataStructures.Array.Structures.Structures.CreateNumberData;
import static Trees.RedBlackTrees.RedBlackTrees.*;
import static aarrays.arrays.arrays.aCopyString;
import static java.lang.Math.min;
import static lists.DynamicArrayCharacters.DynamicArrayCharactersFunctions.DynamicArrayCharactersFunctions.*;
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
        DynamicArrayCharacters buffer = CreateDynamicArrayCharacters();
        AllCombinations(ll, buffer, lol, 0d);

        shortest = LinkedListStringsToArray(ll);

        return shortest;
    }

    public static void AllCombinations(LinkedListStrings ll, DynamicArrayCharacters buffer, StringArrayReference[] lol, double p) {
        double i, oldlen;
        char [] str;

        if(p == lol.length){
            str = DynamicArrayCharactersToArray(buffer);
            LinkedListAddString(ll, str);
        }else {
            for (i = 0d; i < lol[(int) p].stringArray.length; i = i + 1d) {
                oldlen = DynamicArrayCharactersLength(buffer);
                DynamicArrayAddString(buffer, lol[(int) p].stringArray[(int) i].string);

                AllCombinations(ll, buffer, lol, p + 1d);

                for(; DynamicArrayCharactersLength(buffer) > oldlen;){
                    DynamicArrayRemoveCharacter(buffer, DynamicArrayCharactersLength(buffer) - 1d);
                }
            }
        }
    }

    public static void DynamicArrayAddString(DynamicArrayCharacters da, char [] str){
        double i;

        for(i = 0d; i < str.length; i = i + 1d){
            DynamicArrayAddCharacter(da, str[(int)i]);
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

    public static char[] ComputeDay21Part2(char[] input, double robots) {
        double n, i, j, numstartx, numstarty, minlen, candidateLength;
        char [] output, code, roboCode;
        StringReference [] numField, codes, firstRobotPresses;
        boolean minlenSet;
        RedBlackTree cache;

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

        n = 0d;

        for(i = 0d; i < codes.length; i = i + 1d){
            code = codes[(int) i].string;

            // Keypad
            firstRobotPresses = FindPresses(numField, code, numstartx, numstarty);

            // Robots
            minlen = 0d;
            minlenSet = false;
            cache = CreateRedBlackTree();
            for(j = 0d; j < firstRobotPresses.length; j = j + 1d) {
                roboCode = firstRobotPresses[(int)j].string;

                candidateLength = MoveRobot(roboCode, 0d, robots, cache);

                if(!minlenSet){
                    minlen = candidateLength;
                    minlenSet = true;
                }else {
                    minlen = min(minlen, candidateLength);
                }
            }

            code = ReplaceString(code, "A".toCharArray(), "".toCharArray());
            n = n + minlen * CreateNumberFromDecimalString(code);
        }

        // Produce output
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static double MoveRobot(char [] moves, double level, double goal, RedBlackTree cache){
        double i, j, len, minlen, partlen;
        char from, to;
        StringReference [] combos;
        char [] combo, key;
        boolean minlenSet;
        BooleanReference foundRef;
        RedBlackNode node;

        foundRef = new BooleanReference();

        if(level < goal) {
            from = 'A';

            len = 0d;

            for (i = 0d; i < moves.length; i = i + 1d) {
                to = moves[(int) i];

                combos = GetMoveCombos(from, to);

                minlen = 0d;
                minlenSet = false;
                for (j = 0d; j < combos.length; j = j + 1d) {
                    combo = combos[(int)j].string;

                    key = CreateStringDecimalFromNumber(level);
                    key = AppendCharacter(key, '-');
                    key = AppendString(key, combo);

                    node = Search(cache, key, foundRef);

                    if(!foundRef.booleanValue) {
                        partlen = MoveRobot(combo, level + 1d, goal, cache);

                        InsertData(cache, key, CreateNumberData(partlen));
                    }else{
                        partlen = node.value.number;
                    }

                    if(!minlenSet) {
                        minlen = partlen;
                        minlenSet = true;
                    }else{
                        minlen = min(minlen, partlen);
                    }
                }

                len = len + minlen;

                from = to;
            }
        }else{
            len = moves.length;
        }

        return len;
    }

    public static StringReference [] GetMoveCombos(char from, char to){
        Move [] moves;
        double i;
        StringReference [] r;

        moves = GetMoves();
        r = new StringReference[0];

        for(i = 0d; i < moves.length; i = i + 1d){
            Move move = moves[(int)i];

            if(move.from == from && move.to == to){
                r = move.moves;
            }
        }

        return r;
    }

    public static Move [] GetMoves(){
        Move [] moves = new Move[25];

        moves[0] = CreateSingleMove('^', 'A', ">A".toCharArray());
        moves[1] = CreateSingleMove('^', '<', "v<A".toCharArray());
        moves[2] = CreateSingleMove('^', 'v', "vA".toCharArray());
        moves[3] = CreateDoubleMove('^', '>', ">vA".toCharArray(), "v>A".toCharArray());

        moves[4] = CreateSingleMove('A', '^', "<A".toCharArray());
        moves[5] = CreateDoubleMove('A', '<', "<v<A".toCharArray(), "v<<A".toCharArray());
        moves[6] = CreateDoubleMove('A', 'v', "<vA".toCharArray(), "v<A".toCharArray());
        moves[7] = CreateSingleMove('A', '>', "vA".toCharArray());

        moves[8] = CreateSingleMove('<', '^', ">^A".toCharArray());
        moves[9] = CreateDoubleMove('<', 'A', ">>^A".toCharArray(), ">^>A".toCharArray());
        moves[10] = CreateSingleMove('<', '>', ">>A".toCharArray());
        moves[11] = CreateSingleMove('<', 'v', ">A".toCharArray());

        moves[12] = CreateSingleMove('v', '^', "^A".toCharArray());
        moves[13] = CreateDoubleMove('v', 'A', "^>A".toCharArray(), ">^A".toCharArray());
        moves[14] = CreateSingleMove('v', '<', "<A".toCharArray());
        moves[15] = CreateSingleMove('v', '>', ">A".toCharArray());

        moves[16] = CreateDoubleMove('>', '^', "^<A".toCharArray(), "<^A".toCharArray());
        moves[17] = CreateSingleMove('>', 'A', "^A".toCharArray());
        moves[18] = CreateSingleMove('>', '<', "<<A".toCharArray());
        moves[19] = CreateSingleMove('>', 'v', "<A".toCharArray());

        moves[20] = CreateSingleMove('^', '^', "A".toCharArray());
        moves[21] = CreateSingleMove('A', 'A', "A".toCharArray());
        moves[22] = CreateSingleMove('<', '<', "A".toCharArray());
        moves[23] = CreateSingleMove('v', 'v', "A".toCharArray());
        moves[24] = CreateSingleMove('>', '>', "A".toCharArray());

        return moves;
    }

    static class Move{
        public char from;
        public char to;
        StringReference [] moves;
    }

    public static Move CreateSingleMove(char from, char to, char[] seq) {
        Move move;

        move = new Move();

        move.from = from;
        move.to = to;
        move.moves = new StringReference[1];
        move.moves[0] = new StringReference();
        move.moves[0].string = seq;

        return move;
    }

    public static Move CreateDoubleMove(char from, char to, char[] seq1, char [] seq2) {
        Move move;

        move = new Move();

        move.from = from;
        move.to = to;
        move.moves = new StringReference[2];
        move.moves[0] = new StringReference();
        move.moves[0].string = seq1;
        move.moves[1] = new StringReference();
        move.moves[1].string = seq2;

        return move;
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
}