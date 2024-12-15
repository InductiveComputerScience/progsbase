package AdventOfCode.AdventOfCode;

import lists.LinkedListCharacters.Structures.LinkedListCharacters;
import lists.LinkedListStrings.Structures.LinkedListStrings;
import references.references.NumberReference;
import references.references.StringReference;

import java.io.IOException;

import static AdventOfCode.AdventOfCode.AdventOfCodeDay4.PrintField;
import static AdventOfCode.AdventOfCode.AdventOfCodeLib.*;
import static lists.LinkedListCharacters.LinkedListCharactersFunctions.LinkedListCharactersFunctions.*;
import static lists.LinkedListStrings.LinkedListStringsFunctions.LinkedListStringsFunctions.*;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static references.references.references.CreateNumberReference;
import static strings.strings.strings.*;


public class AdventOfCodeDay15 {
    public static char[] ComputeDay15Part1(char[] input) {
        char [] output, line, program;
        double n, i, w, h, x, y;
        StringReference[] lines, field;
        boolean done;
        LinkedListStrings ll;
        LinkedListCharacters lc;

        ll = CreateLinkedListString();

        // Read numbers into array
        lines = SplitByCharacter(input, '\n');

        // Read field
        done = false;
        for(i = 0d; i < lines.length && !done; i = i + 1d){
            line = lines[(int)i].string;

            if(line.length == 0d){
                done = true;
            }else{
                LinkedListAddString(ll, line);
            }
        }

        field = LinkedListStringsToArray(ll);

        //PrintField(field);

        // Read program
        lc = CreateLinkedListCharacter();
        for(; i < lines.length; i = i + 1d){
            line = lines[(int)i].string;
            LinkedListCharactersAddString(lc, line);
        }
        program = LinkedListCharactersToArray(lc);

        //System.out.println(program);

        // Simulate
        for(i = 0d; i < program.length; i = i + 1d){
            Simulate(field, program[(int)i]);
        }

        // Compute GPS sum
        n = 0d;
        w = GetFieldWidth(field);
        h = GetFieldHeight(field);
        for(y = 0d; y < h; y = y + 1d) {
            for (x = 0d; x < w; x = x + 1d) {
                if(GetCharacter(field, x, y) == 'O'){
                    n = n + x + 100d * y;
                }
            }
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static void Simulate(StringReference[] field, char op) {
        double x, y, px, py, w, h, i, bc;
        boolean found, done;
        char c;

        //System.out.println(op);

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        // Find robot
        found = false;
        px = 0d;
        py = 0d;
        for(y = 0d; y < h && !found; y = y + 1d){
            for(x = 0d; x < w && !found; x = x + 1d){
                c = GetCharacter(field, x, y);
                if(c == '@'){
                    px = x;
                    py = y;
                    found = true;
                }
            }
        }

        //System.out.println("Robot: " + px + ", " + py);

        // Move robot
        if(op == '^'){
            if(GetCharacter(field, px, py - 1d) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px, py - 1d, '@');
            }

            if(GetCharacter(field, px, py - 1d) == 'O'){
                // find box chain length
                bc = 0d;
                done = false;
                for(i = 1d; !done; i = i + 1d){
                    if(GetCharacter(field, px, py - i) == 'O'){
                        bc = bc + 1d;
                    }else{
                        done = true;
                    }
                }
                //System.out.println("box chain: " + bc);

                // Move box chain
                if(GetCharacter(field, px, py - bc - 1d) == '.'){
                    //System.out.println("Can be moved");

                    SetCharacter(field, px, py - bc - 1d, 'O');
                    SetCharacter(field, px, py - 1d, '@');
                    SetCharacter(field, px, py, '.');
                }
            }
        }else if(op == 'v'){
            if(GetCharacter(field, px, py + 1d) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px, py + 1d, '@');
            }

            if(GetCharacter(field, px, py + 1d) == 'O'){
                // find box chain length
                bc = 0d;
                done = false;
                for(i = 1d; !done; i = i + 1d){
                    if(GetCharacter(field, px, py + i) == 'O'){
                        bc = bc + 1d;
                    }else{
                        done = true;
                    }
                }
                //System.out.println("box chain: " + bc);

                // Move box chain
                if(GetCharacter(field, px, py + bc + 1d) == '.'){
                    //System.out.println("Can be moved");

                    SetCharacter(field, px, py + bc + 1d, 'O');
                    SetCharacter(field, px, py + 1d, '@');
                    SetCharacter(field, px, py, '.');
                }
            }
        }else if(op == '>'){
            if(GetCharacter(field, px + 1d, py) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px + 1d, py, '@');
            }

            if(GetCharacter(field, px + 1d, py) == 'O'){
                // find box chain length
                bc = 0d;
                done = false;
                for(i = 1d; !done; i = i + 1d){
                    if(GetCharacter(field, px + i, py) == 'O'){
                        bc = bc + 1d;
                    }else{
                        done = true;
                    }
                }
                //System.out.println("box chain: " + bc);

                // Move box chain
                if(GetCharacter(field, px + bc + 1d, py) == '.'){
                    //System.out.println("Can be moved");

                    SetCharacter(field, px + bc + 1d, py, 'O');
                    SetCharacter(field, px + 1d, py, '@');
                    SetCharacter(field, px, py, '.');
                }
            }
        }else if(op == '<'){
            if(GetCharacter(field, px - 1d, py) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px - 1d, py, '@');
            }

            if(GetCharacter(field, px - 1d, py) == 'O'){
                // find box chain length
                bc = 0d;
                done = false;
                for(i = 1d; !done; i = i + 1d){
                    if(GetCharacter(field, px - i, py) == 'O'){
                        bc = bc + 1d;
                    }else{
                        done = true;
                    }
                }
                //System.out.println("box chain: " + bc);

                // Move box chain
                if(GetCharacter(field, px - bc - 1d, py) == '.'){
                    //System.out.println("Can be moved");

                    SetCharacter(field, px - bc - 1d, py, 'O');
                    SetCharacter(field, px - 1d, py, '@');
                    SetCharacter(field, px, py, '.');
                }
            }
        }


        // Print new situation

        //PrintField(field);
    }

    public static char[] ComputeDay15Part2(char[] input) {
        char [] output, line, program;
        double n, i, w, h, x, y, j;
        StringReference[] lines, field;
        boolean done;
        LinkedListStrings ll;
        LinkedListCharacters lc;

        ll = CreateLinkedListString();

        // Read numbers into array
        lines = SplitByCharacter(input, '\n');

        // Read field
        done = false;
        for(i = 0d; i < lines.length && !done; i = i + 1d){
            line = lines[(int)i].string;

            if(line.length == 0d){
                done = true;
            }else{
                LinkedListAddString(ll, line);
            }
        }

        field = LinkedListStringsToArray(ll);

        // Expand field
        PrintField(field);

        for(i = 0d; i < field.length; i = i + 1d){
            line = field[(int)i].string;
            //System.out.println(line);
            char [] eline = new char[line.length*2];
            for(j = 0d; j < line.length; j = j + 1d){
                if(line[(int)j] == '#'){
                    eline[(int)(2d*j+0d)] = '#';
                    eline[(int)(2d*j+1d)] = '#';
                }
                if(line[(int)j] == '.'){
                    eline[(int)(2d*j+0d)] = '.';
                    eline[(int)(2d*j+1d)] = '.';
                }
                if(line[(int)j] == 'O'){
                    eline[(int)(2d*j+0d)] = '[';
                    eline[(int)(2d*j+1d)] = ']';
                }
                if(line[(int)j] == '@'){
                    eline[(int)(2d*j+0d)] = '@';
                    eline[(int)(2d*j+1d)] = '.';
                }
            }

            field[(int)i].string = eline;
        }

        PrintField(field);

        // Read program
        lc = CreateLinkedListCharacter();
        for(; i < lines.length; i = i + 1d){
            line = lines[(int)i].string;
            LinkedListCharactersAddString(lc, line);
        }
        program = LinkedListCharactersToArray(lc);

        //System.out.println(program);

        // Simulate
        for(i = 0d; i < program.length; i = i + 1d){
            Simulate2(field, program[(int)i]);
            /*try {
                System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
        }

        PrintField(field);

        // Compute GPS sum
        n = 0d;
        w = GetFieldWidth(field);
        h = GetFieldHeight(field);
        for(y = 0d; y < h; y = y + 1d) {
            for (x = 0d; x < w; x = x + 1d) {
                if(GetCharacter(field, x, y) == '['){
                    n = n + x + 100d * y;
                }
            }
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    static int count = 0;

    public static void Simulate2(StringReference[] field, char op) {
        double x, y, px, py, w, h, i, bc;
        boolean found, done;
        char c;

        System.out.println(op);

        w = GetFieldWidth(field);
        h = GetFieldHeight(field);

        // Find robot
        found = false;
        px = 0d;
        py = 0d;
        for(y = 0d; y < h && !found; y = y + 1d){
            for(x = 0d; x < w && !found; x = x + 1d){
                c = GetCharacter(field, x, y);
                if(c == '@'){
                    px = x;
                    py = y;
                    found = true;
                }
            }
        }

        //System.out.println("Robot: " + px + ", " + py);

        // Move robot
        if(op == '^'){
            if(GetCharacter(field, px, py - 1d) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px, py - 1d, '@');
            }

            if(GetCharacter(field, px, py - 1d) == '[' || GetCharacter(field, px, py - 1d) == ']'){
                // Find box group.

                int nr = 8963;
                if(count == nr) {
                    System.out.println(count);
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                Coordinate [] ps = FindBoxGroup(field, px, py - 1d, '^');

                /*double size = 0d;
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    size++;
                }
                if(size >= 5){
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }*/


                if(count == nr) {
                    System.out.println("Found box group:");
                    PrintField(field);

                    // Clear box group.
                    System.out.println("Clear group:");
                }
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    Coordinate b = ps[(int)i];
                    //System.out.println(b.x + ", " + b.y);
                    SetCharacter(field, b.x, b.y, '.');
                    SetCharacter(field, b.x + 1d, b.y, '.');
                }

                if(count == nr) {
                    PrintField(field);

                    // Try to print new group.
                    System.out.println("Try to print new group:");
                }
                boolean failed = false;
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    Coordinate b = ps[(int)i];
                    if(GetCharacter(field, b.x, b.y - 1d) == '.' && GetCharacter(field, b.x + 1d, b.y - 1d) == '.') {
                    }else{
                        failed = true;
                    }
                }

                if(count == nr) {
                    PrintField(field);
                }

                // If failed, restore box group.
                if(failed){
                    //System.out.println("failed, restoring");
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        Coordinate b = ps[(int)i];
                        SetCharacter(field, b.x, b.y, '[');
                        SetCharacter(field, b.x+1d, b.y, ']');
                    }
                }else{
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        Coordinate b = ps[(int)i];
                        SetCharacter(field, b.x, b.y - 1d, '[');
                        SetCharacter(field, b.x + 1d, b.y - 1d, ']');
                    }
                }
                if(count == nr) {
                    PrintField(field);
                }

                // If success, move robot.
                if(!failed){
                    SetCharacter(field, px, py, '.');
                    SetCharacter(field, px, py - 1d, '@');
                }

                if(count == nr) {
                    PrintField(field);
                }

                if(count == nr) {
                    //System.exit(0);
                }
            }
        }else if(op == 'v'){
            if(GetCharacter(field, px, py + 1d) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px, py + 1d, '@');
            }

            if(GetCharacter(field, px, py + 1d) == '[' || GetCharacter(field, px, py + 1d) == ']'){
                // Find box group.
                Coordinate [] ps = FindBoxGroup(field, px, py + 1d, 'v');

                /*double size = 0d;
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    size++;
                }
                if(size > 5){
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }*/

                //System.out.println("Found box group:");
                //PrintField(field);

                // Clear box group.
                //System.out.println("Clear group:");
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    Coordinate b = ps[(int)i];
                    //System.out.println(b.x + ", " + b.y);
                    SetCharacter(field, b.x, b.y, '.');
                    SetCharacter(field, b.x + 1d, b.y, '.');
                }

                //PrintField(field);

                // Try to print new group.
                //System.out.println("Try to print new group:");
                boolean failed = false;
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    Coordinate b = ps[(int)i];
                    if(GetCharacter(field, b.x, b.y + 1d) == '.' && GetCharacter(field, b.x + 1d, b.y + 1d) == '.') {
                    }else{
                        failed = true;
                    }
                }

                //PrintField(field);

                // If failed, restore box group.
                if(failed){
                    //System.out.println("failed, restore");
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        Coordinate b = ps[(int)i];
                        SetCharacter(field, b.x, b.y, '[');
                        SetCharacter(field, b.x+1d, b.y, ']');
                    }
                }else{
                    //System.out.println("success");
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        Coordinate b = ps[(int)i];
                        SetCharacter(field, b.x, b.y + 1d, '[');
                        SetCharacter(field, b.x + 1d, b.y + 1d, ']');
                    }
                }
                //PrintField(field);

                // If success, move robot.
                if(!failed){
                    SetCharacter(field, px, py, '.');
                    SetCharacter(field, px, py + 1d, '@');
                }

                //PrintField(field);
            }
        }else if(op == '>'){
            if(GetCharacter(field, px + 1d, py) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px + 1d, py, '@');
            }

            if(GetCharacter(field, px + 1d, py) == '['){
                // Find box group.
                Coordinate [] ps = FindBoxGroup(field, px + 1d, py, '>');

                //System.out.println("Found box group:");
                //PrintField(field);

                // Clear box group.
                //System.out.println("Clear group:");
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    Coordinate b = ps[(int)i];
                    SetCharacter(field, b.x, b.y, '.');
                    SetCharacter(field, b.x + 1d, b.y, '.');
                }

                //PrintField(field);

                // Try to print new group.
                //System.out.println("Try to print new group:");
                boolean failed = false;
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    Coordinate b = ps[(int)i];
                    if(GetCharacter(field, b.x + 1d, b.y) == '.' && GetCharacter(field, b.x + 1d + 1d, b.y) == '.') {
                    }else{
                        failed = true;
                    }
                }

                //PrintField(field);

                // If failed, restore box group.
                if(failed){
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        Coordinate b = ps[(int)i];
                        SetCharacter(field, b.x, b.y, '[');
                        SetCharacter(field, b.x+1d, b.y, ']');
                    }
                }else {
                    for (i = 0; ps[(int) i] != null; i = i + 1d) {
                        Coordinate b = ps[(int) i];
                        SetCharacter(field, b.x + 1d, b.y, '[');
                        SetCharacter(field, b.x + 1d + 1d, b.y, ']');
                    }
                }
                //PrintField(field);

                // If success, move robot.
                if(!failed){
                    SetCharacter(field, px, py, '.');
                    SetCharacter(field, px + 1d, py, '@');
                }

                //PrintField(field);
            }
        }else if(op == '<'){
            if(GetCharacter(field, px - 1d, py) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px - 1d, py, '@');
            }

            if(GetCharacter(field, px - 2d, py) == '['){
                // Find box group.
                Coordinate [] ps = FindBoxGroup(field, px - 2d, py, '<');

                //System.out.println("Found box group:");
                //PrintField(field);

                // Clear box group.
                //System.out.println("Clear group:");
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    Coordinate b = ps[(int)i];
                    SetCharacter(field, b.x, b.y, '.');
                    SetCharacter(field, b.x + 1d, b.y, '.');
                }

                //PrintField(field);

                // Try to print new group.
                //System.out.println("Try to print new group:");
                boolean failed = false;
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    Coordinate b = ps[(int)i];
                    if(GetCharacter(field, b.x - 1d, b.y) == '.' && GetCharacter(field, b.x, b.y) == '.') {
                    }else{
                        failed = true;
                    }
                }

                //PrintField(field);

                // If failed, restore box group.
                if(failed){
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        Coordinate b = ps[(int)i];
                        SetCharacter(field, b.x, b.y, '[');
                        SetCharacter(field, b.x+1d, b.y, ']');
                    }
                }else{
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        Coordinate b = ps[(int)i];
                        SetCharacter(field, b.x - 1d, b.y, '[');
                        SetCharacter(field, b.x, b.y, ']');
                    }
                }
                //PrintField(field);

                // If success, move robot.
                if(!failed){
                    SetCharacter(field, px, py, '.');
                    SetCharacter(field, px - 1d, py, '@');
                }

                //PrintField(field);

                //System.exit(0);
            }
        }


        // Print new situation

        count++;
        if(count == 8962) {
            System.out.println(count - 1);
            PrintField(field);
        }
    }

    public static Coordinate[] FindBoxGroup(StringReference[] field, double x, double y, char dir) {
        Coordinate [] ps = new Coordinate[10000];
        Coordinate c;
        boolean done;
        NumberReference n;

        n = CreateNumberReference(0d);

        if(dir == '>'){
            done = false;
            for(; !done; ){
                if(GetCharacter(field, x, y) == '['){
                    c = new Coordinate();
                    c.x = x;
                    c.y = y;
                    ps[(int)n.numberValue] = c;
                    n.numberValue = n.numberValue + 1d;

                    x = x + 2d;
                }else{
                    done = true;
                }
            }
        }
        if(dir == '<'){
            done = false;
            for(; !done; ){
                if(GetCharacter(field, x, y) == '['){
                    c = new Coordinate();
                    c.x = x;
                    c.y = y;
                    ps[(int)n.numberValue] = c;
                    n.numberValue = n.numberValue + 1d;

                    x = x - 2d;
                }else{
                    done = true;
                }
            }
        }
        if(dir == 'v'){
            c = new Coordinate();
            if(GetCharacter(field, x, y) == '[') {
                c.x = x;
                c.y = y;
            }
            if(GetCharacter(field, x, y) == ']') {
                c.x = x - 1d;
                c.y = y;
            }
            ps[(int)n.numberValue] = c;
            n.numberValue = n.numberValue + 1d;

            FindBoxGroupRecurse(field, ps, n, c, dir);
        }
        if(dir == '^'){
            c = new Coordinate();
            if(GetCharacter(field, x, y) == '[') {
                c.x = x;
                c.y = y;
            }
            if(GetCharacter(field, x, y) == ']') {
                c.x = x - 1d;
                c.y = y;
            }
            ps[(int)n.numberValue] = c;
            n.numberValue = n.numberValue + 1d;

            FindBoxGroupRecurse(field, ps, n, c, dir);
        }

        return ps;
    }

    public static void FindBoxGroupRecurse(StringReference[] field, Coordinate[] ps, NumberReference n, Coordinate c, char dir) {
        if(dir == 'v'){
            if(GetCharacter(field, c.x, c.y + 1d) == '['){
                // []
                // []
                Coordinate nc = new Coordinate();
                nc.x = c.x + 0d;
                nc.y = c.y + 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
            if(GetCharacter(field, c.x, c.y + 1d) == ']'){
                //  []
                // []
                Coordinate nc = new Coordinate();
                nc.x = c.x - 1d;
                nc.y = c.y + 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
            if(GetCharacter(field, c.x + 1d, c.y + 1d) == '['){
                // []
                //  []
                Coordinate nc = new Coordinate();
                nc.x = c.x + 1d;
                nc.y = c.y + 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
        }
        if(dir == '^'){
            if(GetCharacter(field, c.x, c.y - 1d) == '['){
                // []
                // []
                Coordinate nc = new Coordinate();
                nc.x = c.x;
                nc.y = c.y - 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
            if(GetCharacter(field, c.x - 1d, c.y - 1d) == '['){
                // []
                //  []
                Coordinate nc = new Coordinate();
                nc.x = c.x - 1d;
                nc.y = c.y - 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
            if(GetCharacter(field, c.x + 1d, c.y - 1d) == '['){
                //  []
                // []
                Coordinate nc = new Coordinate();
                nc.x = c.x + 1d;
                nc.y = c.y - 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
        }
    }
}
























