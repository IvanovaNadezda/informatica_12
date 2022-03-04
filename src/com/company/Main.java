package com.company;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static int userInput(){ // Функция считывания
        Scanner sc = new Scanner(System.in); // Устройство ввода
        boolean run = true;
        Integer i = null;
        while (run) { // Блок, проверяющий ошибки в пользовательском вводе
            String inputText = sc.nextLine(); // Считываем все, как строку
            try {
                i = Integer.parseInt(inputText); // Переводим строку в целочисленный тип
                run = false;
            } catch (NumberFormatException | NoSuchElementException e){
                System.out.println("Введите целое число из требуемого диапазона: ");
            }
        }
        return i;
    }

    public static void main(String[] args) { // Основное тело программы
        System.out.println("Введите 1, если матрица вводится пользователем и 2, если она заполняется случайными числами самостоятельно: ");
        Scanner scan = new Scanner(System.in);

        int enter = userInput();

        while(enter<1 || enter>2){ // Проверка, пока первое действие неверно
            System.out.println("Вы ввели некорректное действие. Введите 1, если матрица вводится пользователем и 2, если она заполняется случайными числами самостоятельно: ");
            enter = userInput();
        }
        System.out.println("Введите размер матрицы, число должно быть от 2 до 5: ");
        int n = userInput();
        while(n<2 || n>5){ // Проверка, пока второе действие неверно
            System.out.println("Вы ввели недопустимый размер матрицы. Введите число от 2 до 5: ");
            n = userInput();
        }
        int[][] a = new int[n][n];
        int[] b = new int[2 * n];
        int[] c = new int[2 * n];

        if (enter == 1) { // Если матрица вводится пользователем
            System.out.println("Введите числа от 1 до 100: ");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.println("a["+i+"]["+j+"]=");
                    a[i][j] = userInput();
                    while(a[i][j]<1 || a[i][j]>100){
                        System.out.print("Вы ввели число не из диапазона от 1 до 100. Попробуйте снова: \n");
                        System.out.println("a["+i+"]["+j+"]=");
                        a[i][j] = userInput();
                    }
                }
            }
        } else { // Матрица выводится автоматически
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = (int) (1 + Math.random() * 100);
                }
            }
        }

        int s = 0;
        int k = 0;
        while (s < n) { // Нахождение суммы по столбцам и строкам
            for (int i = 0; i < n; i++) {
                b[i] += a[i][s];
                c[i] += a[k][i];
            }
            s++;
            k++;
        }

        System.out.println("Полученная матрица: ");

        for (int i = 0; i < n; i++) { // Вывод матрицы на экран
            for (int j = 0; j < n; j++) {
                if (a[i][j] != 0) {
                    System.out.print(a[i][j] + " ");
                }
            }
            System.out.println();
        }

        for (int i = n - 1; i >= 0; i--) { // Сортировка элементов массива пузырьком
            for (int j = 0; j < i; j++) {
                if (b[j] < b[j+1]) {
                    int tmp = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = tmp;
                }
                if (c[j] > c[j+1]) {
                    int tmp = c[j];
                    c[j] = c[j + 1];
                    c[j + 1] = tmp;
                }
            }
        }


        ArrayList<Integer> v = new ArrayList<>(); // Добавление массивов в динамический массив
        for(int i=0; i<n; i++){
            v.add(b[i]); // Добавление элементов из массива b[]
        }
        for(int i=0; i<n; i++){
            v.add(c[i]); // Добавление элементов из массива с[]
        }
        System.out.println(v);

    }
}