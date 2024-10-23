import java.util.List;

public class ver2 {

    public static void main(String[] args) {
        String file = "input.txt";
        //        читаем данные из файла
        List<Integer> inptData = ver1.readFile(file);

        //        создаем переменные
        int[][] plane = new int[inptData.get(0)][inptData.get(1)];
        inptData.subList(0, 3).clear();
        int[] vir = inptData.stream().mapToInt(i -> i - 1).toArray();     //коорддинаты ячеек с вирусами

        //        подсчитывем количество итераций до полного заражения
        int res = counting(plane, vir);

        //        Записываем результат в файл
        ver1.writing(res);
    }

    private static int counting(int[][] plane, int[] vir) {
        int sum = vir.length / 2;
        int k = 0;    //количесвто циклов
        while (sum < 20) {
            k++;
            for (int v = 0; v < vir.length; v += 2) {
                int i = vir[v];
                int j = vir[v + 1];
                plane[i][j] = 1;
                for (int n = 0; n <= k; n++) {
                    int m = k - n;
                    if (checkConditions(i + m, j + n, plane)) {
                        {plane[i + m][j + n] = 1;
                            sum += 1;}
                    }
                    if (checkConditions(i - m, j - n, plane)) {
                        {plane[i - m][j - n] = 1;
                            sum += 1;}
                    }
                    if (checkConditions(i - m, j + n, plane)) {
                        {plane[i - m][j + n] = 1;
                            sum += 1;}
                    }
                    if (checkConditions(i + m, j - n, plane)) {
                        {plane[i + m][j - n] = 1;
                            sum += 1;}
                    }
                }
            }
            /*System.out.println("k = " + k);         //вывод матриц для проверки
            for (int[] o : plane) {
                for (int p : o) {
                    System.out.print(p + " ");
                }
                System.out.println();
            }
            System.out.println();*/
        }
        return k;
    }

    private static boolean checkConditions(int i, int j, int[][] plane) {
        boolean res = i < plane.length && j < plane[0].length && i >= 0 && j >= 0 && plane[i][j] != 1;
        return res;
    }
}
