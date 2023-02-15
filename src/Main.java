import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите фамилию, имя, отчество, дату рождения, номер телефона, пол...");
        System.out.println("Фамилия, имя, отчество - строки;" + "\n" + "дата рождения - строка формата dd.mm.yyyy;" +
                           "\n" + "номер телефона - целое беззнаковое число без форматирования;" + "\n" + "пол - символ латиницей f или m.");

        String data = scan.nextLine();

        StringHandler stringHandler = new StringHandler(data);

        stringHandler.writeToFile();

    }

}



