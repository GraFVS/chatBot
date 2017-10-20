package ru.aplana.chatbot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class DictReader {

    //Метод проверки файла нашим условиям (путь валидный -> указан .txt файл -> файл не пустой)
    boolean checkFileTxtAndNotEmpty(String filePath){
        System.out.println("Ищу текстовый файл: "+filePath);
            try {
                File findFile = new File(filePath);
                FileReader findFR = new FileReader(findFile);
                BufferedReader reader = new BufferedReader(findFR);
                String line = reader.readLine();
                List<String > checkFile = new ArrayList<>();
                while (line != null) {
                    if (line.length()>0) {
                        checkFile.add(line);
                    }
                    line = reader.readLine();
                }

                //проверки и возвраты

                if (filePath.endsWith(".txt")){
                    if (checkFile.size()>0){
                        System.out.println("Файл найден");
                        return true;
                        }
                        else {
                        System.out.println("Файл пустой, загружать словарь из него - плохая идея!");
                        return false;
                    }
                }
                else {
                    System.out.println("Это не похоже на текстовый файл с расширенеем .txt");
                    return false;
                }


            } catch (IOException e) {
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
                if (line.length()>0) {
                    newDict.add(line);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Успешно загружено");
        return newDict;
    }
}
