package ru.fix.services;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CountService {

    private static int[][] matrix;

    public int calculateMoves(final String width, final String height, final String start, final String end) {
        if (!(validationInputDataBoard(width, height) & validationInputDataPoints(start, end))) {
            return -2;
        }
        int widthX = Integer.parseInt(width);
        int heightY = Integer.parseInt(height);
        if (!checkBoard(widthX, heightY)) {
            return -2;
        }
        int arr[];
        arr = parseText(start);
        if (!checkLocationPoints(widthX, heightY, arr)) {
            return -2;
        }
        int startX = --arr[0];
        int startY = --arr[1];
        arr = parseText(end);
        if (!checkLocationPoints(widthX, heightY, arr)) {
            return -2;
        }
        int endX = --arr[0];
        int endY = --arr[1];
        int totalCount = widthX * heightY;
        matrix = new int[widthX][heightY];
        if (startX == endX && startY == endY) return 0;
        int count = 0;
        matrix[startX][startY] = -1;
        while (true) {
            for (int y = 0; y < heightY; y++) {
                for (int x = 0; x < widthX; x++) {
                    checkAndLocation(widthX, heightY, x, y, count);
                    if (matrix[endX][endY] != 0) return matrix[endX][endY];
                }
            }
            if (++count >= totalCount) return -1;
        }
    }

    private boolean validationInputDataPoints(final String start, final String end) {
        boolean answer = true;
        Pattern pattern = Pattern.compile("^([a-zA-Z]+)(\\d+)$");
        for (int i = 0; i < 2; i++) {
            Matcher matcher = pattern.matcher(i == 0 ? start : end);
            answer = answer & matcher.matches();
        }
        return answer;
    }

    private boolean validationInputDataBoard(final String width, final String height) {
        boolean answer = true;
        Pattern pattern = Pattern.compile("^(\\d+)$");
        for (int i = 0; i < 2; i++) {
            Matcher matcher = pattern.matcher(i == 0 ? width : height);
            answer = answer & matcher.matches();
        }
        return answer;
    }

    private boolean checkBoard(int width, int height) {
        return (width >= 1 & height >= 1);
    }

    private boolean checkLocationPoints(final int width, final int height, final int[] arr) {
        return ((1 <= arr[0] & arr[0] <= width) & (1 <= arr[1] & arr[1] <= height));
    }

    private int[] parseText(final String text) {
        int arr[] = new int[2];
        Pattern pattern = Pattern.compile("^([a-zA-z]+)(\\d+)$");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            arr[0] = convertText(matcher.group(1));
            arr[1] = convertText(matcher.group(2));
        }
        return arr;
    }

    private int convertText(final String text) {
        int number = 0;
        char[] arr = text.toUpperCase().toCharArray();
        if (Character.isDigit(arr[0])) {
            return Integer.parseInt(String.valueOf(text));
        } else {
            for (int i = 0; i < arr.length; i++)
                number += (arr[(arr.length - 1) - i] - 64) * Math.pow(26, i);
        }
        return number;
    }

    private void checkAndLocation(final int width, final int height, final int x, final int y, int count) {
        if (matrix[x][y] == (count == 0 ? -1 : count)) {
            ++count;
            if ((x - 2) >= 0 && (y - 1) >= 0) {
                if (matrix[x - 2][y - 1] == 0) {
                    matrix[x - 2][y - 1] = count;
                }
            }
            if ((x - 1) >= 0 && (y - 2) >= 0) {
                if (matrix[x - 1][y - 2] == 0) {
                    matrix[x - 1][y - 2] = count;
                }
            }
            if ((x + 2) < width && (y - 1) >= 0) {
                if (matrix[x + 2][y - 1] == 0) {
                    matrix[x + 2][y - 1] = count;
                }
            }
            if ((x + 1) < width && (y - 2) >= 0) {
                if (matrix[x + 1][y - 2] == 0) {
                    matrix[x + 1][y - 2] = count;
                }
            }
            if ((x + 2) < width && (y + 1) < height) {
                if (matrix[x + 2][y + 1] == 0) {
                    matrix[x + 2][y + 1] = count;
                }
            }
            if ((x + 1) < width && (y + 2) < height) {
                if (matrix[x + 1][y + 2] == 0) {
                    matrix[x + 1][y + 2] = count;
                }
            }
            if ((x - 2) >= 0 && (y + 1) < height) {
                if (matrix[x - 2][y + 1] == 0) {
                    matrix[x - 2][y + 1] = count;
                }
            }
            if ((x - 1) >= 0 && (y + 2) < height) {
                if (matrix[x - 1][y + 2] == 0) {
                    matrix[x - 1][y + 2] = count;
                }
            }
        }
    }
}
