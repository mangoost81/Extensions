package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final int fields_number = 6;

    public static void main(String[] args) {
        System.out.println("""
                Введите: Фамилия Имя Отчество датарождения номертелефона пол\s
                Форматы данных:\
                фамилия, имя, отчество - строки\
                \
                дата_рождения - строка формата dd.mm.yyyy\

                номер_телефона - целое беззнаковое число без форматирования\

                пол - символ латиницей f или m.

                """ + "\n");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        String[] parsed_input = input.split(" ");
        if (parsed_input.length != fields_number) {
            System.err.println("введено неверное количество полей, введено "
                    + parsed_input.length + "полей. Необходимо ввести 6 полей.");

        }

        String lastname = parsed_input[0].toLowerCase();
        String name = parsed_input[1].toLowerCase();
        String middlename = parsed_input[2].toLowerCase();

        LocalDate dob;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            dob = LocalDate.parse(parsed_input[3], formatter);

        } catch (DateTimeParseException e) {
            System.err.println("Неверный формат даты.");
            return;
        }

        long phonenumber;
        try {
            phonenumber = Long.parseLong(parsed_input[4]);

        } catch (NumberFormatException e) {
            System.err.println("Неверный формат телефонного номера");
            return;

        }

        String gender = parsed_input[5];
        if (!"m".equals(gender) && !"f".equals(gender)) {
            System.err.println("Пол выбран неверно.");

        }

        try (FileWriter writer = new FileWriter(lastname, true)) {
            writer.write(lastname + " " + name + " " + middlename
                    + " " + dob + " " + phonenumber + " " + gender);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
        }


    }


}

