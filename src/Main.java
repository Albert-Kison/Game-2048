import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Please enter the size of the grid:");
        int gridSize = in.nextInt();

        //check if the size is valid
        while  (gridSize < 4 || gridSize > 10) {
            System.out.println("Please enter a number between 4 and 10. Otherwise the game can't be continued:");
            gridSize = in.nextInt();
        }

        int[][] numbers = new int[gridSize][gridSize];  //the grid

        int score = 0;
        boolean isOutOfCells = false;   //in case there is no free cell
        int[][] numbersClone = new int[gridSize][gridSize];

        //two random tiles
        assignOne(numbers, random, isOutOfCells);
        assignOne(numbers, random, isOutOfCells);


        do {
            paintGrid(gridSize, numbers);
            displayRules();

            //to check the changes
            numbersClone = copyArray(numbers, gridSize);

            String input = in.next();
            switch (input.toUpperCase()) {
                case "UP":
                    score = moveUp(numbers, score);
                    break;
                case "W":
                    score = moveUp(numbers, score);
                    break;
                case "DOWN":
                    score = moveDown(numbers, score);
                    break;
                case "S":
                    score = moveDown(numbers, score);
                    break;
                case "LEFT":
                    score = moveLeft(numbers, score);
                    break;
                case "A":
                    score = moveLeft(numbers, score);
                    break;
                case "RIGHT":
                    score = moveRight(numbers, score);
                    break;
                case "D":
                    score = moveRight(numbers, score);
                    break;
                default:
                    System.out.println("Incorrect output");
                    continue;
            }

            //check if the tiles have moved
            if (compareArrays(numbers, numbersClone)) {
                System.out.println("Try another move");
            } else {
                System.out.println("Your score is: " + score);

                check1024(numbers);
                isOutOfCells = assignOne(numbers, random, isOutOfCells);
            }

        } while (!isOutOfCells);


    }



    public static void paintGrid(int gridSize, int[][] numbers) {

        //up border
        for (int j = 0; j < numbers.length; j++) {
            System.out.print("--------");
        }
        System.out.println();

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                System.out.print("| " + printNumber(numbers[i][j]) + " |");
            }
            System.out.println();
            for (int j = 0; j < numbers.length; j++) {
                System.out.print("--------");
            }
            System.out.println();
        }
    }



    public static String printNumber(int number) {
        if (number < 10) {
            return "000" + number;
        } else if (number < 100) {
            return "00" + number;
        } else if (number < 1000) {
            return "0" + number;
        } else return number + "";
    }



    public static ArrayList<String> getFreeCells(int[][] numbers, ArrayList<String> freeCellsList) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[i][j] == 0) {
                    freeCellsList.add(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        return freeCellsList;
    }



    public static String getRandomCell(ArrayList<String> freeCellsList, Random random) {
        return freeCellsList.get(random.nextInt(freeCellsList.size()));
    }



    public static boolean assignOne(int[][] numbers, Random random, boolean isOutOfCells) {
        ArrayList<String> freeCellsList = new ArrayList<String>();
        getFreeCells(numbers, freeCellsList);

        if (!freeCellsList.isEmpty()) {
            isOutOfCells = false;

            String freeCell = getRandomCell(freeCellsList, random);

            numbers[Integer.parseInt(String.valueOf(freeCell.charAt(0)))][Integer.parseInt(String.valueOf(freeCell.charAt(1)))] = 1;
        } else {
            System.out.println("You lost!");
            isOutOfCells = true;
        }

        return isOutOfCells;
    }



    public static int[][] copyArray(int[][] numbers, int size) {
        int[][] numbersClone = new int[size][size];

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                numbersClone[i][j] = numbers[i][j];
            }
        }

        return numbersClone;
    }



    public static boolean compareArrays(int [][] array1, int[][] array2) {
        boolean res = false;

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1.length; j++) {
                if(array1[i][j] == array2[i][j]) {
                    res = true;
                } else {
                    res = false;
                    break;
                }
            }
            if (!res) {
                break;
            }
        }

        return res;
    }



    public static void displayRules() {
        System.out.println("You can move the numbers in 4 directions:");
        System.out.println("1. Up");
        System.out.println("2. Down");
        System.out.println("3. Left");
        System.out.println("4. Right");
        System.out.println("Or you can use WASD:");
        System.out.println("W - Up");
        System.out.println("S - Down");
        System.out.println("A - Left");
        System.out.println("D - Right");
    }



    public static void check1024(int[][] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[i][j] == 1024) {
                    System.out.println("Congratulations! You won!");
                    System.out.println("You may keep playing");
                }
            }
        }
    }



    public static int moveRight(int[][] numbers, int score) {

        //add numbers
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers.length - 1; j >= 1; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (numbers[i][j] == numbers[i][k]) {
                        numbers[i][j] *= 2;
                        numbers[i][k] = 0;
                        score += numbers[i][j];
                        break;
                    } else {
                        if (numbers[i][k] != 0) {
                            break;
                        }
                    }
                }
            }
        }

        //shift numbers
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                for (int k = 0; k < numbers.length - 1; k++) {
                    if (numbers[i][k + 1] == 0) {
                        numbers[i][k] = numbers[i][k] + numbers[i][k + 1];
                        numbers[i][k + 1] = numbers[i][k] - numbers[i][k + 1];
                        numbers[i][k] = numbers[i][k] - numbers[i][k + 1];
                    }
                }
            }
        }

        return score;
    }



    public static int moveLeft(int[][] numbers, int score) {

        //add numbers
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    if (numbers[i][j] == numbers[i][k]) {
                        numbers[i][j] *= 2;
                        numbers[i][k] = 0;
                        score += numbers[i][j];
                        break;
                    } else {
                        if (numbers[i][k] != 0) {
                            break;
                        }
                    }
                }
            }
        }

        //shift numbers
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                for (int k = numbers.length - 1; k >= 1; k--) {
                    if (numbers[i][k - 1] == 0) {
                        numbers[i][k] = numbers[i][k] + numbers[i][k - 1];
                        numbers[i][k - 1] = numbers[i][k] - numbers[i][k - 1];
                        numbers[i][k] = numbers[i][k] - numbers[i][k - 1];
                    }
                }
            }
        }

        return score;
    }



    public static int moveUp(int[][] numbers, int score) {

        //add numbers
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    if (numbers[j][i] == numbers[k][i]) {
                        numbers[j][i] *= 2;
                        numbers[k][i] = 0;
                        score += numbers[j][i];
                        break;
                    } else {
                        if (numbers[k][i] != 0) {
                            break;
                        }
                    }
                }
            }
        }

        //shift numbers
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                for (int k = numbers.length - 1; k >= 1; k--) {
                    if (numbers[k - 1][i] == 0) {
                        numbers[k][i] = numbers[k][i] + numbers[k - 1][i];
                        numbers[k - 1][i] = numbers[k][i] - numbers[k - 1][i];
                        numbers[k][i] = numbers[k][i] - numbers[k - 1][i];
                    }
                }
            }
        }

        return score;
    }



    public static int moveDown(int[][] numbers, int score) {

        //add numbers
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers.length - 1; j >= 1; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (numbers[j][i] == numbers[k][i]) {
                        numbers[j][i] *= 2;
                        numbers[k][i] = 0;
                        score += numbers[j][i];
                        break;
                    } else {
                        if (numbers[k][i] != 0) {
                            break;
                        }
                    }
                }
            }
        }

        //shift numbers
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

        return score;
    }
}
