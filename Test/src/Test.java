public class Test {
    public static void main(String[] args) {
        int[] numbers = {4, 2, 2, 4, 2};

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "   ");
        }
        System.out.println();

        for (int j = numbers.length - 1; j >= 1; j--) {
            for (int k = j - 1; k >= 0; k--) {
                if (numbers[j] == numbers[k]) {
                    numbers[j] *= 2;
                    numbers[k] = 0;
                    break;
                } else {
                    if (numbers[k] != 0) {
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "   ");
        }
        System.out.println();

        for (int j = 0; j < numbers.length; j++) {
            for (int k = 0; k < numbers.length - 1; k++) {
                if (numbers[k + 1] == 0) {
                    numbers[k] = numbers[k] + numbers[k + 1];
                    numbers[k + 1] = numbers[k] - numbers[k + 1];
                    numbers[k] = numbers[k] - numbers[k + 1];
                }
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "   ");
        }
        System.out.println();

    }
}
