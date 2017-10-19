package ru.aplana.chatbot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class DictReader {

    //Метод проверки пути к файлу
    boolean checkFilePath(String filePath){
        System.out.println("Ищу файл: "+filePath);
        try{
            File findFile = new File(filePath);
            FileReader findFR = new FileReader(findFile);
            System.out.println("Файл найден");
            return true;
        }
        catch (FileNotFoundException e){
            System.out.println("Не могу найти файл");
            return false;
        }
    }

    //Метод инициализации словаря бота
    List<String> initDict(String filePath) {
        System.out.println("Загрузка словаря");
        System.out.println("----------------");
        List<String > newDict = new ArrayList<>();
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                newDict.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Успешно загружено");
        return newDict;
    }
}
