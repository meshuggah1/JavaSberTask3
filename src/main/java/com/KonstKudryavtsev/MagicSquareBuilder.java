package com.KonstKudryavtsev;

public class MagicSquareBuilder {

    public static int[][] build(int n) {

        if (n % 2 != 0)
            return buildOdd(n);
        else if (n % 4 == 0)
            return buildDoublyEven(n);
        else
            System.out.println("Yet to be done!");
        return null;
    }

    /*
    ** Широко применяемый алгоритм для квадратов с нечетным n.
    ** 1) Начинаем с середины верхнего ряда. Ставим 1.
    ** 2) Сначала проверяем ячейку наверх по диагонали от только что заполненной.
    ** 3) Если вышли за пределы нумерации столбцов, заполняем ячейку с той же x и y-n.
    **      Напр. для кв. 3х3 (коорд. начинаются с 0):
    ** из яч {2, 2} попали по диагонали в несуществующую яч. {1, 3}. х не меняем, y - 3 = 0.
    ** Соответственно попадаем в яч. с коорд. {1, 0}
    ** Если вышли за пределы нумерации строк, заполняем ячейку с той же y и x+n.
    ** 4) Если вышли за пределы одновременно и строк и столбцов (за правый верхний угол), то
    **      применяется правило занятой ячейки.
    ** 5) Если попали на занятую ячейку, то возвращаемся на предыдущую и от нее и на одну вниз.
     */

    private static int[][] buildOdd(int n) {

        int [][]matrix = new int[n][n];
        int i = n/2;
        int j = n-1;
        int count = 1;
        matrix[n/2][n-1] = count;

        for (int k = 0; k < (n*n)-1; k++) {
            i -= 1;
            j += 1;
            if (i == -1 && j == n) {
                i = 0;
                j = n - 2;
            }
            if (i == -1)
                i = n-1;
            if (j == n)
                j = 0;
            if (matrix[i][j] != 0) {
                i += 1;
                j -= 2;
            }
            matrix[i][j] = ++count;
        }
        return matrix;
    }

    /*
    ** Алгоритм для n, кратных 4.
    ** 1) Выделяются области квадрата: углы размером n/4 * n/4 и центр размером n/2 на n/2.
    ** 2) Идем по ячейкам квадрата (направо-вниз), начиная с верхней левой и заполняем (начиная с 1)
    **      цифрами только выделенные в п.1 области,
    **      счет при этом не прекращая даже во время прохода незаполняемых ячеек.
    ** 3) Теперь идем по квадрату (налево-вверх), начиная счет снова с 1 и с нижнего правого угла.
    **      Заполняем по тому же принципу, только теперь игнорируя ранее заполненные ячейки.
    */

    private static int[][] buildDoublyEven(int n) {

        int [][]matrix = new int[n][n];
        int count = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < (n/4)) {
                    if (j < (n/4) || j >= n - (n/4))
                        matrix[i][j] = count;
                }
                else if (i >= n - (n/4)) {
                    if (j < (n/4) || j >= n - (n/4))
                        matrix[i][j] = count;
                    }
                else if ((i >= (n/4) && i < n - (n/4)) && (j >= (n/4) && j < n - (n/4) )) {
                    matrix[i][j] = count;
                }
                count++;
            }
        }

        count = 1;
        for (int i = n-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (matrix[i][j] == 0)
                    matrix[i][j] = count;
                count++;
            }
        }

        return matrix;
    }
}


