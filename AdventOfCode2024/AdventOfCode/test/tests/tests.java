package tests;

import references.references.NumberReference;

import static AdventOfCode.AdventOfCode.AdventOfCode.*;
import static references.references.references.CreateNumberReference;
import static testing.testing.testing.AssertEquals;
import static testing.testing.testing.AssertStringEquals;

public class tests {
    public static double test(){
        NumberReference failures;

        failures = CreateNumberReference(0d);

        // Day 1 Part 1
        char [] input = """
3   4
4   3
2   5
1   3
3   9
3   3""".toCharArray();

        char [] output = ComputeDay1Part1(input);

        AssertStringEquals(output, "11".toCharArray(), failures);

        // Day 1 Part 2
        output = ComputeDay1Part2(input);

        AssertStringEquals(output, "31".toCharArray(), failures);

        // Day 2 Part 1
        input = """
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".toCharArray();

        output = ComputeDay2Part1(input);

        AssertStringEquals(output, "2".toCharArray(), failures);

        // Day 2 Part 2
        input = """
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".toCharArray();

        output = ComputeDay2Part2(input);

        AssertStringEquals(output, "4".toCharArray(), failures);

        // Day 3 Part 1
        input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))".toCharArray();

        output = ComputeDay3Part1(input);

        AssertStringEquals(output, "161".toCharArray(), failures);

        // Day 3 Part 2
        input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))".toCharArray();

        output = ComputeDay3Part2(input);

        AssertStringEquals(output, "48".toCharArray(), failures);

        // Day 4 Part 1
        input = """
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX""".toCharArray();

        output = ComputeDay4Part1(input);

        AssertStringEquals(output, "18".toCharArray(), failures);

        // Day 4 Part 2
        input = """
.M.S......
..A..MSMS.
.M.S.MAA..
..A.ASMSM.
.M.S.M....
..........
S.S.S.S.S.
.A.A.A.A..
M.M.M.M.M.
..........""".toCharArray();

        output = ComputeDay4Part2(input);

        AssertStringEquals(output, "9".toCharArray(), failures);

        // Day 5 Part 1:
        input = """
47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47""".toCharArray();

        output = ComputeDay5Part1(input);

        AssertStringEquals(output, "143".toCharArray(), failures);

        // Day 5 Part 2

        output = ComputeDay5Part2(input);

        AssertStringEquals(output, "123".toCharArray(), failures);

        // Day 6 Part 1

        input = """
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...""".toCharArray();

        output = ComputeDay6Part1(input);

        AssertStringEquals(output, "41".toCharArray(), failures);

        // Day 6 part 2

        output = ComputeDay6Part2(input);

        AssertStringEquals(output, "6".toCharArray(), failures);

        // Day 7 Part 1

        input = """
190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20""".toCharArray();

        output = ComputeDay7Part1(input);

        AssertStringEquals(output, "3749".toCharArray(), failures);

        // Day 7 Part 2

        output = ComputeDay7Part2(input);

        AssertStringEquals(output, "11387".toCharArray(), failures);

        // Day 8, Part 1

        input = """
............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............""".toCharArray();

        output = ComputeDay8Part1(input);

        AssertStringEquals(output, "14".toCharArray(), failures);

        // Day 8, Part 2
        output = ComputeDay8Part2(input);

        AssertStringEquals(output, "34".toCharArray(), failures);

        // Day 9, Part 1

        input = "2333133121414131402".toCharArray();

        output = ComputeDay9Part1(input);

        AssertStringEquals(output, "1928".toCharArray(), failures);

        // Day 9, Part 2

        output = ComputeDay9Part2(input);

        AssertStringEquals(output, "2858".toCharArray(), failures);

        // Day 10, Part 1

        input = """
89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732""".toCharArray();

        output = ComputeDay10Part1(input);

        AssertStringEquals(output, "36".toCharArray(), failures);

        // Day 10, part 2

        output = ComputeDay10Part2(input);

        AssertStringEquals(output, "81".toCharArray(), failures);

        // Day 11, Part 1

        input = "125 17".toCharArray();

        output = ComputeDay11Part1(input);

        AssertStringEquals(output, "55312".toCharArray(), failures);

        // Day 11, Part 2

        output = ComputeDay11Part2(input);

        AssertStringEquals(output, "65601038650482".toCharArray(), failures);

        // Day 12, Part 1

        input = """
RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE""".toCharArray();

        output = ComputeDay12Part1(input);

        AssertStringEquals(output, "1930".toCharArray(), failures);

        // Day 12, Part 2

        output = ComputeDay12Part2(input);

        AssertStringEquals(output, "1206".toCharArray(), failures);

        // Day 13, Part 1

        input = """
Button A: X+94, Y+34
Button B: X+22, Y+67
Prize: X=8400, Y=5400

Button A: X+26, Y+66
Button B: X+67, Y+21
Prize: X=12748, Y=12176

Button A: X+17, Y+86
Button B: X+84, Y+37
Prize: X=7870, Y=6450

Button A: X+69, Y+23
Button B: X+27, Y+71
Prize: X=18641, Y=10279""".toCharArray();

        output = ComputeDay13Part1(input);

        AssertStringEquals(output, "480".toCharArray(), failures);

        // Day 13, part 2
        output = ComputeDay13Part2(input);

        AssertStringEquals(output, "875318608908".toCharArray(), failures);

        // Day 14, part 1

        input = """
p=0,4 v=3,-3
p=6,3 v=-1,-3
p=10,3 v=-1,2
p=2,0 v=2,-1
p=0,0 v=1,3
p=3,0 v=-2,-2
p=7,6 v=-1,-3
p=3,0 v=-1,-2
p=9,3 v=2,3
p=7,3 v=-1,2
p=2,4 v=2,-3
p=9,5 v=-3,-3""".toCharArray();

        output = ComputeDay14Part1(input, 11d, 7d);

        AssertStringEquals(output, "12".toCharArray(), failures);

        // Day 14, part 2

        input = """
p=4,11 v=-61,-65
p=8,22 v=-64,-15
p=89,80 v=-33,-86
p=39,29 v=-22,-37
p=53,1 v=-35,-15
p=82,34 v=-56,-2
p=58,3 v=90,-10
p=72,66 v=-36,-42
p=17,82 v=62,-3
p=28,44 v=-97,79
p=65,99 v=76,-45
p=1,48 v=-66,33
p=14,86 v=67,78
p=97,13 v=-45,12
p=89,76 v=-46,-5
p=76,78 v=17,-40
p=90,7 v=97,97
p=97,5 v=-46,-17
p=92,50 v=-98,-66
p=21,37 v=79,29
p=83,72 v=44,-64
p=100,32 v=47,-34
p=19,14 v=-29,75
p=18,58 v=35,-22
p=42,27 v=21,86
p=85,15 v=34,-30
p=4,51 v=-24,-94
p=8,10 v=46,21
p=52,73 v=-69,8
p=48,99 v=-21,15
p=27,25 v=41,-85
p=91,8 v=-72,59
p=24,39 v=-81,9
p=20,85 v=9,41
p=55,39 v=17,-90
p=39,45 v=5,90
p=47,79 v=-37,-12
p=9,4 v=62,-10
p=36,50 v=36,22
p=27,92 v=70,35
p=98,17 v=40,-76
p=61,74 v=12,-40
p=93,51 v=13,57
p=21,52 v=3,-59
p=45,33 v=95,-50
p=34,1 v=15,71
p=94,60 v=-77,-90
p=89,65 v=-99,-5
p=83,59 v=12,-35
p=77,55 v=-52,13
p=86,78 v=33,65
p=57,92 v=-10,45
p=16,54 v=78,70
p=95,74 v=-50,-53
p=40,10 v=77,-23
p=69,10 v=-25,74
p=27,87 v=-31,-96
p=41,75 v=-75,-16
p=16,40 v=-39,-55
p=61,80 v=67,76
p=89,44 v=9,18
p=55,98 v=27,-56
p=26,42 v=83,92
p=32,19 v=-12,9
p=92,20 v=45,55
p=81,91 v=-9,43
p=78,23 v=-19,95
p=99,13 v=-62,49
p=34,88 v=-70,-3
p=6,83 v=3,-95
p=60,83 v=48,-16
p=68,24 v=-95,-85
p=22,18 v=-80,-71
p=49,35 v=-75,97
p=99,54 v=-56,-68
p=97,95 v=-34,3
p=32,56 v=-88,33
p=48,27 v=-48,-94
p=0,14 v=-97,85
p=37,22 v=-59,16
p=25,47 v=55,-35
p=72,63 v=-84,83
p=49,51 v=93,-16
p=45,39 v=71,40
p=29,24 v=77,73
p=3,37 v=94,-83
p=58,25 v=-14,-66
p=82,46 v=66,-78
p=92,17 v=-67,-63
p=59,68 v=-32,-3
p=74,97 v=26,-62
p=32,1 v=99,-93
p=3,5 v=99,-47
p=92,28 v=18,-19
p=51,4 v=27,12
p=7,79 v=-60,61
p=46,55 v=-30,-67
p=53,2 v=-65,74
p=49,73 v=-48,94
p=52,77 v=14,-59
p=24,15 v=83,1
p=64,53 v=-36,-75
p=0,55 v=-19,90
p=61,74 v=22,65
p=79,39 v=-9,92
p=3,21 v=-82,40
p=99,63 v=24,-77
p=85,48 v=-84,-90
p=88,22 v=-6,95
p=92,63 v=55,48
p=100,29 v=90,-17
p=75,11 v=-98,34
p=49,10 v=-16,-32
p=71,11 v=86,73
p=84,78 v=69,-67
p=86,14 v=34,16
p=64,20 v=17,97
p=90,91 v=72,-16
p=58,83 v=-16,69
p=29,12 v=-38,95
p=16,6 v=-77,5
p=22,95 v=47,-66
p=83,37 v=92,-59
p=85,86 v=-25,88
p=43,84 v=-64,-3
p=55,45 v=96,79
p=27,97 v=68,1
p=64,61 v=-68,-3
p=24,80 v=41,41
p=78,95 v=-4,21
p=55,101 v=-99,64
p=42,85 v=-70,-75
p=91,53 v=-83,-46
p=99,13 v=-8,73
p=10,83 v=44,-66
p=12,94 v=61,-71
p=74,3 v=-4,49
p=8,89 v=-33,-24
p=12,75 v=94,83
p=6,70 v=-35,-17
p=26,55 v=-92,-33
p=52,54 v=95,-66
p=12,38 v=73,-7
p=81,32 v=-68,-75
p=85,98 v=2,-45
p=84,65 v=60,-64
p=15,92 v=-23,91
p=70,24 v=-32,-22
p=94,65 v=-66,17
p=22,40 v=95,99
p=48,0 v=37,60
p=60,50 v=38,46
p=5,12 v=72,27
p=42,33 v=-17,-98
p=15,1 v=62,-58
p=89,78 v=-67,63
p=6,98 v=-30,-47
p=46,88 v=37,10
p=88,64 v=39,-64
p=36,86 v=-86,-3
p=39,52 v=-37,-18
p=20,76 v=-28,-40
p=85,5 v=22,-77
p=77,78 v=-54,-7
p=61,88 v=43,89
p=40,64 v=-21,41
p=85,82 v=60,-3
p=90,96 v=72,-21
p=16,38 v=-1,-35
p=46,98 v=85,-1
p=8,62 v=73,59
p=18,11 v=41,-50
p=18,75 v=36,39
p=45,3 v=-17,27
p=13,79 v=-24,74
p=67,34 v=28,28
p=29,87 v=-4,-21
p=77,29 v=7,-61
p=89,85 v=-35,32
p=6,71 v=89,2
p=10,59 v=-13,37
p=0,25 v=46,7
p=8,12 v=71,-69
p=75,54 v=-4,-31
p=62,79 v=-81,-29
p=25,101 v=-97,3
p=13,5 v=-24,-32
p=69,70 v=-79,-7
p=41,60 v=47,70
p=42,32 v=80,92
p=41,2 v=-11,12
p=90,37 v=82,57
p=55,7 v=7,-8
p=74,78 v=7,-5
p=46,80 v=34,-21
p=96,9 v=-9,-39
p=5,90 v=-7,52
p=66,32 v=75,18
p=84,77 v=39,39
p=75,66 v=-20,8
p=26,17 v=-91,-83
p=38,59 v=18,46
p=82,98 v=-94,47
p=92,49 v=52,65
p=56,39 v=-88,-95
p=57,27 v=-10,-65
p=61,24 v=25,-13
p=72,38 v=-66,99
p=58,62 v=99,-42
p=31,82 v=-25,-55
p=40,42 v=-75,55
p=42,85 v=-59,67
p=77,16 v=28,-30
p=99,87 v=61,87
p=85,43 v=-8,-59
p=92,16 v=28,3
p=57,26 v=-92,66
p=48,37 v=-80,-94
p=22,79 v=-6,33
p=75,9 v=49,-76
p=79,65 v=-89,65
p=95,61 v=38,44
p=68,67 v=17,17
p=38,24 v=63,64
p=80,74 v=-14,-75
p=73,60 v=-84,26
p=4,101 v=88,36
p=25,48 v=-44,-12
p=26,56 v=-82,-63
p=88,84 v=-35,12
p=9,40 v=67,25
p=79,87 v=60,23
p=8,42 v=73,-90
p=100,28 v=24,80
p=65,90 v=33,-47
p=90,60 v=18,26
p=38,46 v=58,11
p=45,6 v=-21,-1
p=43,86 v=-69,-27
p=32,37 v=-23,11
p=67,0 v=68,-41
p=65,62 v=77,78
p=59,85 v=53,29
p=84,33 v=66,64
p=30,32 v=-81,18
p=22,53 v=-81,-79
p=56,23 v=-53,-15
p=75,46 v=55,-59
p=47,51 v=61,47
p=96,102 v=-9,47
p=45,67 v=56,-6
p=61,71 v=65,14
p=95,30 v=-63,-73
p=21,16 v=-55,56
p=76,87 v=-25,-84
p=25,0 v=22,78
p=21,101 v=62,-30
p=98,78 v=-2,-60
p=66,96 v=-68,-58
p=38,80 v=-28,-5
p=32,21 v=7,-83
p=9,2 v=88,-21
p=79,91 v=76,-14
p=1,12 v=62,-65
p=64,52 v=6,17
p=6,99 v=-35,23
p=50,33 v=-37,-61
p=67,52 v=-94,-24
p=95,31 v=-8,-28
p=47,45 v=-89,-77
p=78,60 v=-99,32
p=84,25 v=-46,-52
p=51,71 v=-89,-96
p=5,100 v=-66,56
p=42,76 v=-70,-75
p=22,74 v=-60,76
p=63,67 v=1,54
p=60,41 v=75,93
p=22,11 v=-17,71
p=29,53 v=63,34
p=73,22 v=-20,-74
p=61,39 v=-42,57
p=59,28 v=47,-70
p=5,12 v=88,-21
p=65,73 v=-31,21
p=4,41 v=95,4
p=84,96 v=55,43
p=24,63 v=-44,15
p=67,12 v=87,-60
p=34,63 v=85,-64
p=32,49 v=-62,-53
p=27,69 v=36,79
p=21,67 v=57,-7
p=3,3 v=40,-8
p=49,78 v=-43,-5
p=87,15 v=25,7
p=0,56 v=-56,-90
p=90,34 v=97,29
p=10,99 v=-31,-41
p=41,13 v=-75,-43
p=32,93 v=65,91
p=13,28 v=-34,53
p=34,3 v=-59,1
p=42,67 v=37,-86
p=66,9 v=15,-41
p=86,3 v=-6,81
p=81,56 v=97,92
p=96,24 v=50,53
p=77,60 v=60,-66
p=85,74 v=-78,51
p=24,95 v=20,-69
p=42,20 v=10,16
p=48,56 v=9,30
p=66,68 v=33,-42
p=98,30 v=61,-96
p=14,8 v=4,-12
p=58,69 v=-74,-88
p=68,68 v=38,61
p=89,64 v=-83,-29
p=78,17 v=6,-78
p=66,80 v=54,8
p=48,98 v=-32,-71
p=52,83 v=5,-5
p=24,35 v=4,-6
p=25,56 v=99,-40
p=95,15 v=97,-16
p=90,95 v=50,1
p=65,59 v=98,-20
p=67,21 v=32,-52
p=0,72 v=-34,78
p=81,34 v=-9,31
p=4,36 v=-23,73
p=50,12 v=21,14
p=95,42 v=-72,66
p=3,80 v=53,-58
p=95,44 v=-3,-32
p=65,52 v=-77,-61
p=5,102 v=72,3
p=5,77 v=13,-31
p=91,88 v=-8,-83
p=56,99 v=7,-67
p=70,64 v=54,85
p=30,56 v=36,-66
p=25,93 v=34,-72
p=10,31 v=-79,22
p=58,0 v=41,-78
p=75,80 v=49,78
p=73,96 v=-99,-14
p=81,37 v=-46,-72
p=48,60 v=31,11
p=83,83 v=-46,-62
p=26,93 v=-18,32
p=6,20 v=-93,-76
p=79,30 v=76,-83
p=55,24 v=17,-98
p=88,11 v=34,27
p=9,25 v=-7,-5
p=48,32 v=3,-33
p=91,91 v=-61,98
p=83,65 v=-18,-88
p=41,89 v=98,5
p=76,41 v=12,-39
p=34,46 v=-95,16
p=43,61 v=-16,-44
p=22,32 v=94,99
p=85,22 v=-7,-70
p=77,45 v=-52,42
p=45,34 v=-96,44
p=22,44 v=62,22
p=49,78 v=22,71
p=20,100 v=-44,-34
p=52,62 v=70,-55
p=81,22 v=41,53
p=20,8 v=37,82
p=91,15 v=71,84
p=84,30 v=1,44
p=75,96 v=44,-84
p=41,97 v=-75,67
p=91,94 v=50,89
p=47,47 v=-64,-92
p=81,24 v=-46,-41
p=85,43 v=-62,-94
p=51,9 v=-83,20
p=34,47 v=-38,-42
p=28,40 v=-49,-24
p=25,70 v=-28,30
p=63,31 v=-66,-65
p=60,83 v=-28,14
p=5,52 v=-77,-18
p=28,33 v=41,35
p=67,58 v=50,31
p=66,36 v=52,36
p=72,18 v=-10,13
p=33,80 v=-91,6
p=7,65 v=83,-18
p=1,37 v=-75,27
p=0,72 v=-98,-64
p=39,22 v=-88,71
p=84,39 v=-9,-72
p=86,77 v=18,-62
p=2,37 v=29,-94
p=95,83 v=-46,-34
p=100,20 v=55,-54
p=98,74 v=-24,23
p=50,52 v=-64,-48
p=69,59 v=-84,-99
p=33,14 v=-41,-69
p=53,101 v=26,-60
p=31,74 v=84,-40
p=90,37 v=71,-72
p=19,96 v=-60,-10
p=43,78 v=-37,-58
p=49,81 v=47,-86
p=66,76 v=74,-28
p=87,83 v=-50,16
p=43,91 v=-11,-12
p=74,89 v=12,89
p=81,94 v=-65,-73
p=22,22 v=-71,16
p=47,30 v=41,71
p=93,35 v=44,-63
p=14,5 v=-9,80
p=34,58 v=31,-9
p=20,47 v=-92,-22
p=5,94 v=-18,89
p=50,46 v=-32,-33
p=48,86 v=-95,-49
p=42,42 v=5,-37
p=33,8 v=-49,-10
p=9,49 v=-72,50
p=32,77 v=64,91
p=1,17 v=4,-45
p=82,36 v=-89,77
p=52,91 v=-90,45
p=57,73 v=-79,-86
p=68,35 v=48,-22
p=94,28 v=35,84
p=66,2 v=-9,38
p=77,58 v=89,81
p=93,87 v=45,17
p=99,75 v=-8,-3
p=46,0 v=-69,12
p=93,86 v=66,-29
p=20,72 v=-28,-97
p=59,30 v=59,-59
p=72,18 v=97,15
p=74,17 v=99,70
p=80,62 v=-78,-7
p=13,29 v=60,-73
p=47,48 v=-17,-70
p=68,60 v=-89,-92
p=23,33 v=-66,46
p=28,81 v=-86,30
p=88,5 v=-16,95
p=25,89 v=68,-3
p=21,87 v=60,-77
p=48,16 v=-43,38
p=63,9 v=-31,-41
p=73,19 v=81,-17
p=19,16 v=36,-26
p=3,51 v=40,-92
p=77,79 v=33,-62
p=94,74 v=2,-36
p=24,50 v=-28,-46
p=59,87 v=38,65
p=68,55 v=23,74
p=9,50 v=-73,38
p=73,54 v=97,72
p=13,51 v=30,22
p=24,62 v=80,-48
p=45,63 v=-93,-76
p=91,46 v=-13,-20
p=53,78 v=31,-1
p=91,67 v=-16,29
p=55,16 v=-85,62
p=46,68 v=-1,-38
p=64,92 v=-47,56
p=24,77 v=94,-40
p=1,75 v=-45,-20
p=6,40 v=-29,-2
p=12,5 v=-2,71
p=57,57 v=-12,-80
p=1,32 v=-40,64
p=4,16 v=-77,49
p=72,58 v=17,-64
p=40,56 v=18,44
p=96,87 v=-3,47
p=89,34 v=-99,75
p=60,38 v=21,33
p=72,71 v=-14,-86
p=79,11 v=53,96
p=84,89 v=2,-82
p=10,45 v=83,2
p=95,2 v=-56,3
p=98,59 v=-77,72
p=24,18 v=25,64
p=27,91 v=-54,-60
p=74,81 v=-88,-60
p=53,12 v=90,-72
p=25,52 v=-97,26""".toCharArray();

        output = ComputeDay14Part2(input, 101d, 103d);

        AssertStringEquals(output, "7051".toCharArray(), failures);

        // Done

        return failures.numberValue;
    }
}

