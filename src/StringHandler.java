import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StringHandler implements StringHandlerInterface {

    private final String string;

    public StringHandler(String string) {
        this.string = string;
    }

    // Убираем лишние пробелы
    private String removeSpaces(String string) {
        return string.trim().replaceAll("\\s{2,}", " ");
    }

    // Проверяем количество введенных слов
    private boolean isRequiredWordNumber(String[] words) throws NoRequiredDataAmountException {
        if (words.length == 6) {
            return true;
        } else throw new NoRequiredDataAmountException("Введено некорректное количество данных");
    }

    // Проверяем ФИО на отсутствие некорректных символов
    private boolean isLetter(String string) throws NoRequiredTextDataException {
        if (string.chars().allMatch(Character::isLetter)) {
            return true;
        } else throw new NoRequiredTextDataException("Введены некорректные ФИО");
    }

    // Проверяем дату рождения
    private boolean isDate(String string) throws NoRequiredDateException {
        if (string.matches("([1-9]{1}|[0]{1}[1-9]{1}|[1]{1}[0-9]{1}|[2]{1}[0-9]{1}|[3]{1}[0-1]{1})" +
                "([.]{1})" + "([0]{1}[1-9]{1}|[1]{1}[0-2]{1}|[1-9]{1})" + "([.]{1})" + "([0129]{2}[0-9]{2})")) {
            return true;
        } else throw new NoRequiredDateException("Введена некорректная дата рождения");
    }

    // Проверяем телефон на соответствие цифрам
    private boolean isNumeric(String string) throws NoRequiredNumericDataException {
        if (string.chars().allMatch(Character::isDigit)) {
            return true;
        } else throw new NoRequiredNumericDataException("Введен некорректный номер телефона");
    }

    // Проверяем пол
    private boolean isGender(String string) throws NoRequiredGenderException {
        if (string.matches("([mf]{1})")) {
            return true;
        } else throw new NoRequiredGenderException("Введен некорректный пол");
    }

    // Обрабатываем введенные данные
    public String handle() {
        String string = this.removeSpaces(this.string);
        String[] words = string.split(" ");

        try {
            this.isRequiredWordNumber(words);
        } catch (NoRequiredDataAmountException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        try {
            for (int i = 0; i < 3; i++) {
                this.isLetter(words[i]);
            }
        } catch (NoRequiredTextDataException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        try {
            this.isDate(words[3]);
        } catch (NoRequiredDateException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        try {
            this.isNumeric(words[4]);
        } catch (NoRequiredNumericDataException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        try {
            this.isGender(words[5]);
        } catch (NoRequiredGenderException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return string;
    }

    // Записываем в файл
    public void writeToFile() {
        String string = this.handle();
        String filename = string.substring(0, string.indexOf(' '));

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(filename, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(string);
            printWriter.close();
            System.out.println("Файл " + filename + " создан/дополнен.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(printWriter != null)
                printWriter.close();
            try {
                if(bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            try {
                if(fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
