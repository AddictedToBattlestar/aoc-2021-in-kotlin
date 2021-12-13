package com.nenaner.aoc2021;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Temp {
    public int[][] test() {
        int[][] result = new int[2][2];
        return result;
    }

    public <T> T[][] transpose(T[][] sourceMatrix) {
        int newRowSize = sourceMatrix.length;
        int newColumnSize = sourceMatrix[0].length;
        T[][] transposedMatrix = Array.newInstance(T, capacity)
        for(int i = 0; i < newColumnSize - 1; i++) {
            for(int j = 0; j < newRowSize - 1; j++) {
                transposedMatrix[j][i] = transposedMatrix[i][j];
            }
        }
        return transposedMatrix;
    }
}
