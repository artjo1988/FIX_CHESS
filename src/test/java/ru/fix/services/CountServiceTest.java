package ru.fix.services;

import org.junit.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class CountServiceTest {

    CountService countService = new CountService();

    @org.junit.Test
    public void vallidationInputDataStep1() {
        int actual = countService.calculateMoves("8", "8", "1a", "h1");
        int expected = -2;
        assertEquals("Не прошло проверку валидации входных данных, конечные точки", actual, expected);
    }

    @org.junit.Test
    public void vallidationInputDataStep2() {
        int actual = countService.calculateMoves("30", "8", "a1", "aa2");
        int expected = 13;
        assertEquals("Не прошло проверку валидации входных данных(буквенная комбинация), конечные точки", actual, expected);
    }

    @org.junit.Test
    public void countMovesStep1() {
        int actual = countService.calculateMoves("8", "8", "a1", "h1");
        int expected = 5;
        assertEquals("Не прошло проверку калькуляции ходов на одной горизонтали", actual, expected);
    }

    @org.junit.Test
    public void countMovesStep2() {
        int actual = countService.calculateMoves("8", "8", "a1", "a8");
        int expected = 5;
        assertEquals("Не прошло проверку калькуляции ходов на одной вертикали", actual, expected);
    }

    @org.junit.Test
    public void countMovesStep3() {
        int actual = countService.calculateMoves("8", "8", "b3", "a1");
        int expected = 1;
        assertEquals("Не прошло проверку калькуляции ходов, произвольное расположение", actual, expected);
    }

    @org.junit.Test
    public void countMovesStep4() {
        int actual = countService.calculateMoves("8", "8", "a2", "f7");
        int expected = 4;
        assertEquals("Не прошло проверку калькуляции ходов, произвольное расположение", actual, expected);
    }

    @org.junit.Test
    public void unreachableLocationStep1() {
        int actual = countService.calculateMoves("3", "3", "a1", "b2");
        int expected = -1;
        assertEquals("Не прошло проверку не достижимости конечного положения", actual, expected);
    }

    @org.junit.Test
    public void unreachableLocationStep2() {
        int actual = countService.calculateMoves("2", "8", "a3", "b4");
        int expected = -1;
        assertEquals("Не прошло проверку не достижимости конечного положения", actual, expected);
    }

    @org.junit.Test
    public void boardNotCorrectStep1() {
        int actual = countService.calculateMoves("0", "10", "b3", "a1");
        int expected = -2;
        assertEquals("Не прошло проверку размеров доски по ширине", actual, expected);
    }

    @org.junit.Test
    public void boardNotCorrectStep2() {
        int actual = countService.calculateMoves("5", "-1", "b3", "a1");
        int expected = -2;
        assertEquals("Не прошло проверку размеров доски по высоте", actual, expected);
    }

    @org.junit.Test
    public void checkLocationPoitsStep1() {
        int actual = countService.calculateMoves("8", "8", "b9", "a1");
        int expected = -2;
        assertEquals("Не прошло проверку расположения начальной точки", actual, expected);
    }

    @org.junit.Test
    public void checkLocationPoitsStep2() {
        int actual = countService.calculateMoves("8", "8", "b3", "k1");
        int expected = -2;
        assertEquals("Не прошло проверку расположения конечной точки", actual, expected);
    }
}