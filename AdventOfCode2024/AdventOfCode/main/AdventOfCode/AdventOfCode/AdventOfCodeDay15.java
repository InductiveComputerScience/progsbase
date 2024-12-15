package AdventOfCode.AdventOfCode;

import lists.LinkedListCharacters.Structures.LinkedListCharacters;
import lists.LinkedListStrings.Structures.LinkedListStrings;
import references.references.NumberReference;
import references.references.StringReference;

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

        // Read program
        lc = CreateLinkedListCharacter();
        for(; i < lines.length; i = i + 1d){
            line = lines[(int)i].string;
            LinkedListCharactersAddString(lc, line);
        }
        program = LinkedListCharactersToArray(lc);

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

                // Move box chain
                if(GetCharacter(field, px, py - bc - 1d) == '.'){
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

                // Move box chain
                if(GetCharacter(field, px, py + bc + 1d) == '.'){
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

                // Move box chain
                if(GetCharacter(field, px + bc + 1d, py) == '.'){
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

                // Move box chain
                if(GetCharacter(field, px - bc - 1d, py) == '.'){
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
        char [] output, line, program, eline;
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
        for(i = 0d; i < field.length; i = i + 1d){
            line = field[(int)i].string;
            eline = new char[line.length*2];
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

        // Read program
        lc = CreateLinkedListCharacter();
        for(; i < lines.length; i = i + 1d){
            line = lines[(int)i].string;
            LinkedListCharactersAddString(lc, line);
        }
        program = LinkedListCharactersToArray(lc);

        // Simulate
        for(i = 0d; i < program.length; i = i + 1d){
            Simulate2(field, program[(int)i]);
        }

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

    public static void Simulate2(StringReference[] field, char op) {
        double x, y, px, py, w, h, i;
        boolean found, failed;
        char c;
        Coordinate b;
        Coordinate [] ps;

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

        // Move robot
        if(op == '^'){
            if(GetCharacter(field, px, py - 1d) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px, py - 1d, '@');
            }

            if(GetCharacter(field, px, py - 1d) == '[' || GetCharacter(field, px, py - 1d) == ']'){
                // Find box group.
                ps = FindBoxGroup(field, px, py - 1d, '^');

                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    b = ps[(int)i];
                    SetCharacter(field, b.x, b.y, '.');
                    SetCharacter(field, b.x + 1d, b.y, '.');
                }

                failed = false;
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    b = ps[(int)i];
                    if(GetCharacter(field, b.x, b.y - 1d) == '.' && GetCharacter(field, b.x + 1d, b.y - 1d) == '.') {
                    }else{
                        failed = true;
                    }
                }

                // If failed, restore box group.
                if(failed){
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        b = ps[(int)i];
                        SetCharacter(field, b.x, b.y, '[');
                        SetCharacter(field, b.x+1d, b.y, ']');
                    }
                }else{
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        b = ps[(int)i];
                        SetCharacter(field, b.x, b.y - 1d, '[');
                        SetCharacter(field, b.x + 1d, b.y - 1d, ']');
                    }
                }

                // If success, move robot.
                if(!failed){
                    SetCharacter(field, px, py, '.');
                    SetCharacter(field, px, py - 1d, '@');
                }
            }
        }else if(op == 'v'){
            if(GetCharacter(field, px, py + 1d) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px, py + 1d, '@');
            }

            if(GetCharacter(field, px, py + 1d) == '[' || GetCharacter(field, px, py + 1d) == ']'){
                // Find box group.
                ps = FindBoxGroup(field, px, py + 1d, 'v');

                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    b = ps[(int)i];
                    SetCharacter(field, b.x, b.y, '.');
                    SetCharacter(field, b.x + 1d, b.y, '.');
                }

                // Try to print new group.
                failed = false;
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    b = ps[(int)i];
                    if(GetCharacter(field, b.x, b.y + 1d) == '.' && GetCharacter(field, b.x + 1d, b.y + 1d) == '.') {
                    }else{
                        failed = true;
                    }
                }

                // If failed, restore box group.
                if(failed){
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        b = ps[(int)i];
                        SetCharacter(field, b.x, b.y, '[');
                        SetCharacter(field, b.x+1d, b.y, ']');
                    }
                }else{
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        b = ps[(int)i];
                        SetCharacter(field, b.x, b.y + 1d, '[');
                        SetCharacter(field, b.x + 1d, b.y + 1d, ']');
                    }
                }

                // If success, move robot.
                if(!failed){
                    SetCharacter(field, px, py, '.');
                    SetCharacter(field, px, py + 1d, '@');
                }
            }
        }else if(op == '>'){
            if(GetCharacter(field, px + 1d, py) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px + 1d, py, '@');
            }

            if(GetCharacter(field, px + 1d, py) == '['){
                // Find box group.
                ps = FindBoxGroup(field, px + 1d, py, '>');

                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    b = ps[(int)i];
                    SetCharacter(field, b.x, b.y, '.');
                    SetCharacter(field, b.x + 1d, b.y, '.');
                }

                // Try to print new group.
                failed = false;
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    b = ps[(int)i];
                    if(GetCharacter(field, b.x + 1d, b.y) == '.' && GetCharacter(field, b.x + 1d + 1d, b.y) == '.') {
                    }else{
                        failed = true;
                    }
                }

                // If failed, restore box group.
                if(failed){
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        b = ps[(int)i];
                        SetCharacter(field, b.x, b.y, '[');
                        SetCharacter(field, b.x+1d, b.y, ']');
                    }
                }else {
                    for (i = 0; ps[(int) i] != null; i = i + 1d) {
                        b = ps[(int) i];
                        SetCharacter(field, b.x + 1d, b.y, '[');
                        SetCharacter(field, b.x + 1d + 1d, b.y, ']');
                    }
                }

                // If success, move robot.
                if(!failed){
                    SetCharacter(field, px, py, '.');
                    SetCharacter(field, px + 1d, py, '@');
                }
            }
        }else if(op == '<'){
            if(GetCharacter(field, px - 1d, py) == '.'){
                SetCharacter(field, px, py, '.');
                SetCharacter(field, px - 1d, py, '@');
            }

            if(GetCharacter(field, px - 2d, py) == '['){
                // Find box group.
                ps = FindBoxGroup(field, px - 2d, py, '<');

                // Clear box group.
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    b = ps[(int)i];
                    SetCharacter(field, b.x, b.y, '.');
                    SetCharacter(field, b.x + 1d, b.y, '.');
                }

                // Try to print new group.
                failed = false;
                for(i = 0; ps[(int)i] != null; i = i + 1d){
                    b = ps[(int)i];
                    if(GetCharacter(field, b.x - 1d, b.y) == '.' && GetCharacter(field, b.x, b.y) == '.') {
                    }else{
                        failed = true;
                    }
                }

                // If failed, restore box group.
                if(failed){
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        b = ps[(int)i];
                        SetCharacter(field, b.x, b.y, '[');
                        SetCharacter(field, b.x+1d, b.y, ']');
                    }
                }else{
                    for(i = 0; ps[(int)i] != null; i = i + 1d){
                        b = ps[(int)i];
                        SetCharacter(field, b.x - 1d, b.y, '[');
                        SetCharacter(field, b.x, b.y, ']');
                    }
                }

                // If success, move robot.
                if(!failed){
                    SetCharacter(field, px, py, '.');
                    SetCharacter(field, px - 1d, py, '@');
                }
            }
        }


        // Print new situation
        // PrintField(field);
    }

    public static Coordinate[] FindBoxGroup(StringReference[] field, double x, double y, char dir) {
        Coordinate [] ps;
        Coordinate c;
        boolean done;
        NumberReference n;

        ps = new Coordinate[10000];
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
        Coordinate nc;

        if(dir == 'v'){
            if(GetCharacter(field, c.x, c.y + 1d) == '['){
                // []
                // []
                nc = new Coordinate();
                nc.x = c.x + 0d;
                nc.y = c.y + 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
            if(GetCharacter(field, c.x, c.y + 1d) == ']'){
                //  []
                // []
                nc = new Coordinate();
                nc.x = c.x - 1d;
                nc.y = c.y + 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
            if(GetCharacter(field, c.x + 1d, c.y + 1d) == '['){
                // []
                //  []
                nc = new Coordinate();
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
                nc = new Coordinate();
                nc.x = c.x;
                nc.y = c.y - 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
            if(GetCharacter(field, c.x - 1d, c.y - 1d) == '['){
                // []
                //  []
                nc = new Coordinate();
                nc.x = c.x - 1d;
                nc.y = c.y - 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
            if(GetCharacter(field, c.x + 1d, c.y - 1d) == '['){
                //  []
                // []
                nc = new Coordinate();
                nc.x = c.x + 1d;
                nc.y = c.y - 1d;
                ps[(int)n.numberValue] = nc;
                n.numberValue = n.numberValue + 1d;
                FindBoxGroupRecurse(field, ps, n, nc, dir);
            }
        }
    }
}

