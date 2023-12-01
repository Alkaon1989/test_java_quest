package src;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        while (true){
            try {
                System.out.println("Введите выражение: ");
                System.out.println(calc(in.nextLine()));
            }
            catch (IOException e){
                System.out.println("Исключение");
            }
        }
    }

    static void test_java(){
        String[] arr_num = {"1","2","3","4","5","6","7","8","9","10"};
        System.out.println(arr_num.length);
        for (String itVar : arr_num){
            System.out.println(itVar);

        }

    }



    static String calc(String i_string) throws IOException {

        // инициализация
        String[] arr = i_string.split(" ");
        String[] arr_num = {"1","2","3","4","5","6","7","8","9","10"};
        String[] arr_rom = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        String[] arr_rom2 = {"X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};
        String[] arr_oper = {"+","-","*","/"};
        boolean inArabic = false;
        boolean inRoman = false;
        boolean inOper = false;
        int a = 0, b = 0;

        // проверка ввода

        // количество элементов в выражении
        if (arr.length != 3){
            throw new IOException("Неверный ввод.");
        }

        // оператор
        for (String itVar : arr_oper) {
            if (itVar.equals(arr[1])){
                inOper = true;
                break;
            }
        }
        if (!inOper){
            throw new IOException("Неверный ввод.");
        }

        // арабские
        for (String itVar : arr_num){
            if (itVar.equals(arr[0])){
                for (String itVar2 : arr_num) {
                    if (itVar2.equals(arr[2])){
                        inArabic = true;
                        List list_num = Arrays.asList(arr_num);
                        a = list_num.indexOf(itVar) + 1;
                        b = list_num.indexOf(itVar2) + 1;
                        break;
                    }
                }
                break;
            }
        }

        // римские
        if (!inArabic){
            for (String itVar : arr_rom){
                if (itVar.equals(arr[0])){
                    for (String itVar2 : arr_rom) {
                        if (itVar2.equals(arr[2])){
                            inRoman = true;
                            List list_rom = Arrays.asList(arr_rom);
                            a = list_rom.indexOf(itVar) + 1;
                            b = list_rom.indexOf(itVar2) + 1;
                            break;
                        }
                    }
                    break;
                }
            }
        }

        // выброс ошибки
        if (!inRoman && !inArabic){
            throw new IOException("Неверный ввод.");
        }
        // вычисление
        // арабские
        if (inOper && inArabic){
            String resString = "";
            String c = arr[1];
            if (c.equals(arr_oper[0])){
                resString = Integer.toString(a + b);
            }
            if (c.equals(arr_oper[1])){
                resString = Integer.toString(a - b);
            }
            if (c.equals(arr_oper[2])){
                resString = Integer.toString(a * b);
            }
            if (c.equals(arr_oper[3])){
                resString = Integer.toString(a / b);
            }
            return resString;
        }

        // римские
        if (inOper && inRoman){
            String c = arr[1];
            int res;
            StringJoiner joined_res = new StringJoiner("");
            if (c.equals(arr_oper[0])){
                res = a + b;
                if (res > 10){
                    joined_res.add(arr_rom[9]);
                    joined_res.add(arr_rom[res-11]);
                }
                else {
                    joined_res.add(arr_rom[res-1]);
                }
            }
            if (c.equals(arr_oper[1])){
                res = a - b;
                if (res < 1) {
                    throw new IOException("результат меньше 1");
                }
                else {
                    joined_res.add(arr_rom[res - 1]);
                }
            }
            if (c.equals(arr_oper[2])){
                res = a * b;
                if (res <= 10){
                    joined_res.add(arr_rom[res - 1]);
                }
                else if (res == 100){
                    joined_res.add(arr_rom2[9]);
                }
                else {
                    int i = res / 10;
                    System.out.println(res);
                    System.out.println(i);
                    joined_res.add(arr_rom2[i-1]);
                    int rest = res - i*10;
                    if (rest > 0){
                        joined_res.add(arr_rom[rest - 1]);
                    }
                }
            }
            if (c.equals(arr_oper[3])){
                res = a / b;
                if (res < 1){
                    throw new IOException("результат меньше 1");
                }
                else {
                    joined_res.add(arr_rom[res - 1]);
                }
            }
            return String.valueOf(joined_res);
        }
        return null;
    }
}
