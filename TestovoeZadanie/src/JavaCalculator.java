import java.util.Scanner;

public class JavaCalculator {
    private static boolean isRoman;
    private static int number1, number2;

    public static void main(String[] args) throws Exception {
        Scanner cs = new Scanner(System.in);
        System.out.println("Введите в одну строку операцию сложения, или вычитания, или умножения, или деления с " +
                "двумя числами в формате a + b, или a - b, или a * b, или a / b.");
        String input = cs.nextLine();
        cs.close();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        String operation;
        String result;
        String[] operands = input.toUpperCase().split("-|\\+|\\*|/");
        if (operands.length != 2) throw new Exception("Введен не верный формат операции");
        operation = detectOperation(input);
        checkRomanNum(operands);
        if (number1 > 10 || number2 > 10) {
            throw new Exception("Тре 1 до 10");
        }
        int arabian = calc(number1, number2, operation);
        if (isRoman) {
            // если римское число меньше генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            // конвертируем результат операции из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {
            //Конвертируем арабское в тип стринг
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String input) { // может int вместо String?
        if (input.contains("+")) return "+";
        if (input.contains("-")) return "-";
        if (input.contains("*")) return "*";
        return "/";
    }

    static int calc(int x, int y, String operation) {
        if (operation.equals("+")) return x + y;
        if (operation.equals("-")) return x - y;
        if (operation.equals("*")) return x * y;
        return x / y;
    }
    private static void checkRomanNum(String[] operands) throws Exception{
        if (Roman.isRoman(operands[0]) & Roman.isRoman(operands[1])) { // оба числа римские
            isRoman = true;
            number1 = Roman.convertToArabian(operands[0]);
            number2 = Roman.convertToArabian(operands[1]);
        }// если оба числа арабские
        else if (!Roman.isRoman(operands[0]) & !Roman.isRoman(operands[1])) {
            isRoman = false;
            number1 = Integer.parseInt(operands[0]); // преобразуем строки в число
            number2 = Integer.parseInt(operands[1]);
        }
        // если одно число арабское, а другое -- римское
        else {
            throw new Exception("Числа должны быть в одном формате");
        }
    }
}