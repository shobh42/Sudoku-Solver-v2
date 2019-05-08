import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NakedPairStrategyTest {

    @Test
    public void nakedPairInRowTest(){

        Cell[][] puzzle = new Cell[9][9];
        Set<Character> values = new HashSet<>();
        values.add('1');
        values.add('8');
        values.add('9');
        Cell c00 = new Cell(values);
        puzzle[0][0] = c00;

        values = new HashSet<>();
        values.add('2');
        Cell c01 = new Cell(values);
        puzzle[0][1] = c01;

        values = new HashSet<>();
        values.add('3');
        Cell c02 = new Cell(values);
        puzzle[0][2] = c02;

        values = new HashSet<>();
        values.add('4');
        Cell c03 = new Cell(values);
        puzzle[0][3] = c03;

        values = new HashSet<>();
        values.add('5');
        Cell c04 = new Cell(values);
        puzzle[0][4] = c04;

        values = new HashSet<>();
        values.add('6');
        Cell c05 = new Cell(values);
        puzzle[0][5] = c05;

        values = new HashSet<>();
        values.add('7');
        Cell c06 = new Cell(values);
        puzzle[0][6] = c06;

        values = new HashSet<>();
        values.add('8');
        values.add('9');
        Cell c07 = new Cell(values);
        puzzle[0][7] = c07;

        values = new HashSet<>();
        values.add('8');
        values.add('9');
        Cell c08 = new Cell(values);
        puzzle[0][8] = c08;

        //<><><><><><><><><><><><><><><

        values = new HashSet<>();
        values.add('a');
        Cell c10 = new Cell(values);
        puzzle[1][0] = c10;

        values = new HashSet<>();
        values.add('b');
        Cell c11 = new Cell(values);
        puzzle[1][1] = c11;

        values = new HashSet<>();
        values.add('c');
        Cell c12 = new Cell(values);
        puzzle[1][2] = c12;

        values = new HashSet<>();
        values.add('d');
        Cell c13 = new Cell(values);
        puzzle[1][3] = c13;

        values = new HashSet<>();
        values.add('e');
        Cell c14 = new Cell(values);
        puzzle[1][4] = c14;

        values = new HashSet<>();
        values.add('f');
        Cell c15 = new Cell(values);
        puzzle[1][5] = c15;

        values = new HashSet<>();
        values.add('g');
        Cell c16 = new Cell(values);
        puzzle[1][6] = c16;

        values = new HashSet<>();
        values.add('h');
        Cell c17 = new Cell(values);
        puzzle[1][7] = c17;

        values = new HashSet<>();
        values.add('i');
        Cell c18 = new Cell(values);
        puzzle[1][8] = c18;

        //<><><><><><><><><><><><><><><

        values = new HashSet<>();
        values.add('j');
        Cell c20 = new Cell(values);
        puzzle[2][0] = c20;

        values = new HashSet<>();
        values.add('k');
        Cell c21 = new Cell(values);
        puzzle[2][1] = c21;

        values = new HashSet<>();
        values.add('l');
        Cell c22 = new Cell(values);
        puzzle[2][2] = c22;

        values = new HashSet<>();
        values.add('m');
        Cell c23 = new Cell(values);
        puzzle[2][3] = c23;

        values = new HashSet<>();
        values.add('n');
        Cell c24 = new Cell(values);
        puzzle[2][4] = c24;

        values = new HashSet<>();
        values.add('o');
        Cell c25 = new Cell(values);
        puzzle[2][5] = c25;

        values = new HashSet<>();
        values.add('p');
        Cell c26 = new Cell(values);
        puzzle[2][6] = c26;

        values = new HashSet<>();
        values.add('q');
        Cell c27 = new Cell(values);
        puzzle[2][7] = c27;

        values = new HashSet<>();
        values.add('r');
        Cell c28 = new Cell(values);
        puzzle[2][8] = c28;

        //<><><><><><><><><><><><><><

        values = new HashSet<>();
        values.add('s');
        Cell c30 = new Cell(values);
        puzzle[3][0] = c30;

        values = new HashSet<>();
        values.add('t');
        Cell c31 = new Cell(values);
        puzzle[3][1] = c31;

        values = new HashSet<>();
        values.add('u');
        Cell c32 = new Cell(values);
        puzzle[3][2] = c32;

        values = new HashSet<>();
        values.add('v');
        Cell c33 = new Cell(values);
        puzzle[3][3] = c33;

        values = new HashSet<>();
        values.add('w');
        Cell c34 = new Cell(values);
        puzzle[3][4] = c34;

        values = new HashSet<>();
        values.add('x');
        Cell c35 = new Cell(values);
        puzzle[3][5] = c35;

        values = new HashSet<>();
        values.add('y');
        Cell c36 = new Cell(values);
        puzzle[3][6] = c36;

        values = new HashSet<>();
        values.add('z');
        Cell c37 = new Cell(values);
        puzzle[3][7] = c37;

        values = new HashSet<>();
        values.add('0');
        Cell c38 = new Cell(values);
        puzzle[3][8] = c38;

        //<><><><><><><><><><><><><><

//        Cell c40 = new Cell('1');
//        puzzle[4][0] = c40;
//        Cell c41 = new Cell('2');
//        puzzle[4][1] = c41;
//        Cell c42 = new Cell('3');
//        puzzle[4][2] = c42;
//        Cell c43 = new Cell('4');
//        puzzle[4][3] = c43;
//        Cell c44 = new Cell('5');
//        puzzle[4][4] = c44;
//        Cell c45 = new Cell('6');
//        puzzle[4][5] = c45;
//        Cell c46 = new Cell('7');
//        puzzle[4][6] = c46;
//        Cell c47 = new Cell('8');
//        puzzle[4][7] = c47;
//        Cell c48 = new Cell('9');
//        puzzle[4][8] = c48;
//        //<><><><><><><><><><><><><><
//        Cell c50 = new Cell('a');
//        puzzle[5][0] = c50;
//        Cell c51 = new Cell('b');
//        puzzle[5][1] = c51;
//        Cell c52 = new Cell('c');
//        puzzle[5][2] = c52;
//        Cell c53 = new Cell('d');
//        puzzle[5][3] = c53;
//        Cell c54 = new Cell('e');
//        puzzle[5][4] = c54;
//        Cell c55 = new Cell('f');
//        puzzle[5][5] = c55;
//        Cell c56 = new Cell('g');
//        puzzle[5][6] = c56;
//        Cell c57 = new Cell('h');
//        puzzle[5][7] = c57;
//        Cell c58 = new Cell('i');
//        puzzle[5][8] = c58;
//        //<><><><><><><><><><><><><>
//        Cell c60 = new Cell('j');
//        puzzle[6][0] = c60;
//        Cell c61 = new Cell('k');
//        puzzle[6][1] = c61;
//        Cell c62 = new Cell('l');
//        puzzle[6][2] = c62;
//        Cell c63 = new Cell('m');
//        puzzle[6][3] = c63;
//        Cell c64 = new Cell('n');
//        puzzle[6][4] = c64;
//        Cell c65 = new Cell('o');
//        puzzle[6][5] = c65;
//        Cell c66 = new Cell('p');
//        puzzle[6][6] = c66;
//        Cell c67 = new Cell('q');
//        puzzle[6][7] = c67;
//        Cell c68 = new Cell('r');
//        puzzle[6][8] = c68;
//        //<><><><><><><><><><><><><><><>
//        Cell c70 = new Cell('s');
//        puzzle[7][0] = c70;
//        Cell c71 = new Cell('t');
//        puzzle[7][1] = c71;
//        Cell c72 = new Cell('u');
//        puzzle[7][2] = c72;
//        Cell c73 = new Cell('v');
//        puzzle[7][3] = c73;
//        Cell c74 = new Cell('w');
//        puzzle[7][4] = c74;
//        Cell c75 = new Cell('x');
//        puzzle[7][5] = c75;
//        Cell c76 = new Cell('y');
//        puzzle[7][6] = c76;
//        Cell c77 = new Cell('z');
//        puzzle[7][7] = c77;
//        Cell c78 = new Cell('0');
//        puzzle[7][8] = c78;
//        //<><><><><><><><><><><><><><><>
//        Cell c80 = new Cell('1');
//        puzzle[8][0] = c80;
//        Cell c81 = new Cell('2');
//        puzzle[8][1] = c81;
//        Cell c82 = new Cell('3');
//        puzzle[8][2] = c82;
//        Cell c83 = new Cell('4');
//        puzzle[8][3] = c83;
//        Cell c84 = new Cell('5');
//        puzzle[8][4] = c84;
//        Cell c85 = new Cell('6');
//        puzzle[8][5] = c85;
//        Cell c86 = new Cell('7');
//        puzzle[8][6] = c86;
//        Cell c87 = new Cell('8');
//        puzzle[8][7] = c87;
//        Cell c88 = new Cell('9');
//        puzzle[8][8] = c88;
//
//        NakedPairStrategy s = new NakedPairStrategy();
//        Map<Integer,List<Integer>> cellToFill = new HashMap<>();
//        ArrayList <Integer> cols = new ArrayList<>();
//        cols.add(7);
//        cellToFill.put(0, cols);
//        s.applystrategy(cellToFill, puzzle);
//
//        Cell[][] expectedOutput = new Cell[9][9];
//        c00 = new Cell('1');
//        expectedOutput[0][0] = c00;
//        c01 = new Cell('2');
//        expectedOutput[0][1] = c01;
//        c02 = new Cell('3');
//        expectedOutput[0][2] = c02;
//        c03 = new Cell('4');
//        expectedOutput[0][3] = c03;
//        c04 = new Cell('5');
//        expectedOutput[0][4] = c04;
//        c05 = new Cell('6');
//        expectedOutput[0][5] = c05;
//        c06 = new Cell('7');
//        expectedOutput[0][6] = c06;
//
//        values = new ArrayList<>();
//        values.add('8');
//        values.add('9');
//        c07 = new Cell(values);
//        expectedOutput[0][7] = c07;
//
//        values = new ArrayList<>();
//        values.add('8');
//        values.add('9');
//        c08 = new Cell(values);
//        expectedOutput[0][8] = c08;
//        //<><><><><><><><><><><><><><><
//
//        c10 = new Cell('a');
//        expectedOutput[1][0] = c10;
//
//        c11 = new Cell('b');
//        expectedOutput[1][1] = c11;
//
//        c12 = new Cell('c');
//        expectedOutput[1][2] = c12;
//
//        c13 = new Cell('d');
//        expectedOutput[1][3] = c13;
//
//        c14 = new Cell('e');
//        expectedOutput[1][4] = c14;
//
//        c15 = new Cell('f');
//        expectedOutput[1][5] = c15;
//
//        c16 = new Cell('g');
//        expectedOutput[1][6] = c16;
//
//        c17 = new Cell('h');
//        expectedOutput[1][7] = c17;
//
//        c18 = new Cell('i');
//        expectedOutput[1][8] = c18;
//        //<><><><><><><><><><><><><><><
//        c20 = new Cell('j');
//        expectedOutput[2][0] = c20;
//
//
//        c21 = new Cell('k');
//        expectedOutput[2][1] = c21;
//
//        c22 = new Cell('l');
//        expectedOutput[2][2] = c22;
//
//        c23 = new Cell('m');
//        expectedOutput[2][3] = c23;
//
//        c24 = new Cell('n');
//        expectedOutput[2][4] = c24;
//
//        c25 = new Cell('o');
//        expectedOutput[2][5] = c25;
//
//        c26 = new Cell('p');
//        expectedOutput[2][6] = c26;
//
//        c27 = new Cell('q');
//        expectedOutput[2][7] = c27;
//
//        c28 = new Cell('r');
//        expectedOutput[2][8] = c28;
//        //<><><><><><><><><><><><><><
//        c30 = new Cell('s');
//        expectedOutput[3][0] = c30;
//
//        c31 = new Cell('t');
//        expectedOutput[3][1] = c31;
//
//        c32 = new Cell('u');
//        expectedOutput[3][2] = c32;
//
//        c33 = new Cell('v');
//        expectedOutput[3][3] = c33;
//
//        c34 = new Cell('w');
//        expectedOutput[3][4] = c34;
//
//        c35 = new Cell('x');
//        expectedOutput[3][5] = c35;
//
//        c36 = new Cell('y');
//        expectedOutput[3][6] = c36;
//
//        c37 = new Cell('z');
//        expectedOutput[3][7] = c37;
//
//        c38 = new Cell('0');
//        expectedOutput[3][8] = c38;
//        //-----------------------------
//        c40 = new Cell('1');
//        expectedOutput[4][0] = c40;
//        c41 = new Cell('2');
//        expectedOutput[4][1] = c41;
//        c42 = new Cell('3');
//        expectedOutput[4][2] = c42;
//        c43 = new Cell('4');
//        expectedOutput[4][3] = c43;
//        c44 = new Cell('5');
//        expectedOutput[4][4] = c44;
//        c45 = new Cell('6');
//        expectedOutput[4][5] = c45;
//        c46 = new Cell('7');
//        expectedOutput[4][6] = c46;
//        c47 = new Cell('8');
//        expectedOutput[4][7] = c47;
//        c48 = new Cell('9');
//        expectedOutput[4][8] = c48;
//        //<><><><><><><><><><><><><><
//        c50 = new Cell('a');
//        expectedOutput[5][0] = c50;
//        c51 = new Cell('b');
//        expectedOutput[5][1] = c51;
//        c52 = new Cell('c');
//        expectedOutput[5][2] = c52;
//        c53 = new Cell('d');
//        expectedOutput[5][3] = c53;
//        c54 = new Cell('e');
//        expectedOutput[5][4] = c54;
//        c55 = new Cell('f');
//        expectedOutput[5][5] = c55;
//        c56 = new Cell('g');
//        expectedOutput[5][6] = c56;
//        c57 = new Cell('h');
//        expectedOutput[5][7] = c57;
//        c58 = new Cell('i');
//        expectedOutput[5][8] = c58;
//        //<><><><><><><><><><><><><>
//        c60 = new Cell('j');
//        expectedOutput[6][0] = c60;
//        c61 = new Cell('k');
//        expectedOutput[6][1] = c61;
//        c62 = new Cell('l');
//        expectedOutput[6][2] = c62;
//        c63 = new Cell('m');
//        expectedOutput[6][3] = c63;
//        c64 = new Cell('n');
//        expectedOutput[6][4] = c64;
//        c65 = new Cell('o');
//        expectedOutput[6][5] = c65;
//        c66 = new Cell('p');
//        expectedOutput[6][6] = c66;
//        c67 = new Cell('q');
//        expectedOutput[6][7] = c67;
//        c68 = new Cell('r');
//        expectedOutput[6][8] = c68;
//        //<><><><><><><><><><><><><><><>
//        c70 = new Cell('s');
//        expectedOutput[7][0] = c70;
//        c71 = new Cell('t');
//        expectedOutput[7][1] = c71;
//        c72 = new Cell('u');
//        expectedOutput[7][2] = c72;
//        c73 = new Cell('v');
//        expectedOutput[7][3] = c73;
//        c74 = new Cell('w');
//        expectedOutput[7][4] = c74;
//        c75 = new Cell('x');
//        expectedOutput[7][5] = c75;
//        c76 = new Cell('y');
//        expectedOutput[7][6] = c76;
//        c77 = new Cell('z');
//        expectedOutput[7][7] = c77;
//        c78 = new Cell('0');
//        expectedOutput[7][8] = c78;
//        //<><><><><><><><><><><><><><><>
//        c80 = new Cell('1');
//        expectedOutput[8][0] = c80;
//        c81 = new Cell('2');
//        expectedOutput[8][1] = c81;
//        c82 = new Cell('3');
//        expectedOutput[8][2] = c82;
//        c83 = new Cell('4');
//        expectedOutput[8][3] = c83;
//        c84 = new Cell('5');
//        expectedOutput[8][4] = c84;
//        c85 = new Cell('6');
//        expectedOutput[8][5] = c85;
//        c86 = new Cell('7');
//        expectedOutput[8][6] = c86;
//        c87 = new Cell('8');
//        expectedOutput[8][7] = c87;
//        c88 = new Cell('9');
//        expectedOutput[8][8] = c88;
//
//        int size = puzzle.length;
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                Assert.assertSame(expectedOutput[i][j].getPossibleValues(), puzzle[i][j].getPossibleValues());
//            }
//        }
    }
}