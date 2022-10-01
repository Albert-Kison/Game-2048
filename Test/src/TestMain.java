public class TestMain {
    public static void main(String[] args) {
        int[][] numbers = {{2,0},
                           {2,4},
                           {4,2},
                           {2,2},
                           {4,4}};

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                System.out.print(numbers[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < numbers[0].length; i++) {
            for (int j = numbers.length - 1; j >= 1; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (numbers[j][i] == numbers[k][i]) {
                        numbers[j][i] *= 2;
                        numbers[k][i] = 0;
                        break;
                    } else {
                        if (numbers[k][i] != 0) {
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                System.out.print(numbers[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < numbers[0].length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                for (int k = 0; k < numbers.length - 1; k++) {
                    if (numbers[k + 1][i] == 0) {
                        numbers[k][i] = numbers[k][i] + numbers[k + 1][i];
                        numbers[k + 1][i] = numbers[k][i] - numbers[k + 1][i];
                        numbers[k][i] = numbers[k][i] - numbers[k + 1][i];
                    }
                }
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                System.out.print(numbers[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
