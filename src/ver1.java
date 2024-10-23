import java.io.*;
import java.util.*;

public class ver1 {
    public static void main(String[] args) {
        String file = "input.txt";
//        читаем данные из файла
        List<Integer> inptData = readFile(file);

//        создаем переменные
        int[][] plane = new int[inptData.get(0)][inptData.get(1)];
        inptData.subList(0, 3).clear();
        int[] vir = inptData.stream().mapToInt(i -> i - 1).toArray();     //коорддинаты ячеек с вирусами

//        подсчитывем количество итераций до полного заражения
        int res = stepCounting(plane, vir);

//        Записываем результат в файл
        writing(res);
    }

    private static int stepCounting(int[][] plane, int[] vir) {
        int sum = 0;                                                  // сумма зараженных ячеек
        int step = 0;                                                 // итерация цикла заражения
        while (sum < plane.length * plane[0].length) {
            step++;
            for (int i = 0; i < plane.length; i++) {
                for (int j = 0; j < plane[i].length; j++) {
                    for (int k = 0; k < vir.length && plane[i][j] == 0; k += 2) {
                        int side1 = Math.abs(i - vir[k]);                  // расстояние между текущей координатой и координатой вируса
                        int side2 = Math.abs(j - vir[k + 1]);
                        int hyp = side1 * side1 + side2 * side2;                    // расстояние от вируса до текущей точки - гипотенуза, а s1 и ы2 - катеты
                        if (hyp <= step * step) {                       // если длина от вируса до текущей точки меньше или равна текущему шагу, то ячейка становится вирусной
                            plane[i][j] = 1;
                            sum += plane[i][j];
                        }
                    }
                }
            }
            /*System.out.println("step = " + step);     //вывод матриц для проверки
            for (int[] o : plane) {
                for (int p : o) {
                    System.out.print(p + " ");
                }
                System.out.println();
            }
            System.out.println();*/
        }
        return step;
    }

    static List<Integer> readFile(String file) {
        List<Integer> inptData = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(file));
            while (scan.hasNext()) {
                inptData.add(Integer.parseInt(scan.next()));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return inptData;
    }

    static void writing(int step) {
        try (FileOutputStream ff = new FileOutputStream("output.txt")) {
            String result = Integer.toString(step);
            ff.write(result.getBytes());
            System.out.println("output.txt created");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
