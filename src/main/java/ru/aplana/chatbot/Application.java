package ru.aplana.chatbot;

public class Application {
    public static void main(String[] args) {
        if (args.length > 0){
            String userDictPath = args[0];
            Bot bot = new Bot(userDictPath);
            bot.start();
        }
        else {
            Bot bot = new Bot();
            bot.start();
        }
    }
}
