package ru.aplana.chatbot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Bot{
    private Scanner scanner = new Scanner(System.in);
    private List<String> botAnswers = new ArrayList<>();
    private DictReader dictReader = new DictReader();
    private final String DEFAULT_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\default_dict.txt";



//  конструктор без параметров
Bot() {
        if (dictReader.checkFilePath(DEFAULT_PATH)) {
            botAnswers = dictReader.initDict(DEFAULT_PATH);
        }
        else {
            System.out.println("Словарь по-умолчанию не найден. Вы можете загрзить свой словарь, использовав команду 'change'");
        }

    }
//  конструктор с параметром String
Bot(String userDictPath) {
        if(dictReader.checkFilePath(userDictPath)){
            botAnswers = dictReader.initDict(userDictPath);
        }
        else {
            System.out.println("Не удалось загрузить словарь по указанному пути. Вы можете попытаться загрзить его позднее, использовав команду 'change'");
        }
    }




    //метод запуска бота
    void botStart() {
        boolean continueChatBot;
        String input;
        System.out.println("");
        System.out.println("Чат бот версии 1.0.");
        System.out.println("Используйте команды 'date' 'time' 'silent' 'change' или просто поговорите со мной. Для выхода используйте команду 'quit'");
        continueChatBot = true;

        do {
            input = scanner.nextLine();
            switch (input) {
                case "silent":
                    continueChatBot = goSleep();
                    break;

                case "getUp":
                    System.out.println("Как можно проснуться, если ты не спишь? А я не сплю - задавайте ваши вопросики!");
                    break;

                case "date":
                    System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("d  MMM  uuuu")));
                    break;

                case "time":
                    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    break;

                case "change":
                    continueChatBot=changeDictPath();
                    break;

                case "quit":
                    System.out.println("Было приятно поболтать. Всего хорошего!");
                    continueChatBot = false;
                    break;

                default:
                    if (botAnswers.size() != 0) {
                        final Random random = new Random();
                        System.out.println(botAnswers.get(random.nextInt(botAnswers.size())));
                    } else {
                        System.out.println("Мой словарный запас невелик. Чтобы обучить меня введите команду 'change'.");
                    }
                    break;
            }
        } while (continueChatBot);
    }

    //Метод поведения спящего бота
    private boolean goSleep() {
        System.out.println("А теперь пора спать! Разбудите меня командой 'getUp', когда я понадоблюсь");
        String sleepInput;
        boolean sleepMode = true;
        boolean continueChatBot = true;
        do {
            sleepInput = scanner.nextLine();
            switch (sleepInput) {
                case "getUp":
                    System.out.println("Что, уже? Не дают выспаться... иду, иду...");
                    System.out.println("*Лениво просыпается*");
                    sleepMode = false;
                    System.out.println("Я снова бодр и весел - каковы будут ваши приказания?");
                    break;

                case "quit":
                    System.out.println("Мог бы и разбудить перед уходом. ДОСВИДАНИЯ!");
                    sleepMode = false;
                    continueChatBot = false;
                    break;

                default:
                    System.out.println("z-z-z-z...");
                    break;
            }
        }
        while (sleepMode);
        return continueChatBot;
    }

    //Метод смены словаря
    private boolean changeDictPath(){
        System.out.println("Переучить меня решили? Ну тогда введите путь к файлу с новым словарём или команду 'cancel', чтобы вернуться назад");
        String newPath;
        String pathInput;
        boolean askPathAgain = true;
        boolean continueChatBot =true;
        do{
            pathInput=scanner.nextLine();
            switch (pathInput){
                case "cancel":
                    System.out.println("Вы передумали меня переучивать... и это радостно! Продолжаем разговор");
                    askPathAgain=false;
                    break;

                case "quit":
                    System.out.println("Уже уходите? Ну, как знаете, всего хорошего!");
                    askPathAgain = false;
                    continueChatBot=false;
                    break;

                default:
                    if (dictReader.checkFilePath(pathInput)){
                        botAnswers=dictReader.initDict(pathInput);
                        askPathAgain = false;
                    } else {
                        System.out.println("Введите путь к файлу с новым словарём или команду 'cancel', чтобы вернуться назад");
                    }
                    break;
            }
        }
        while (askPathAgain);
        return continueChatBot;
    }
}





