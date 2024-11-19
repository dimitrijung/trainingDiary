package ait;

import ait.dao.SportImpl;
import ait.model.Training;
import ait.menu.TrainingType;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SportApp {
    public static void main(String[] args) {
        SportImpl sportService = new SportImpl(); // Создаем объект для управления тренировками
        Scanner scanner = new Scanner(System.in); // Создаем сканер для чтения пользовательского ввода
        boolean running = true; // Флаг работы приложения

        // Приветствие пользователя
        System.out.println("************************************************");
        System.out.println("* Welcome to the Training Diary Application!  *");
        System.out.println("************************************************");

        while (running) { // Основной цикл работы приложения
            // Вывод меню для выбора действия
            System.out.println("\nSelect an action:");
            System.out.println("1. Add Training"); // Добавить тренировку
            System.out.println("2. Edit Training"); // Редактировать тренировку
            System.out.println("3. Delete Training"); // Удалить тренировку
            System.out.println("4. Delete Trainings by Date"); // Удалить тренировки по дате
            System.out.println("5. Find Trainings by Date Range"); // Найти тренировки по диапазону дат
            System.out.println("6. Find Trainings by Type"); // Найти тренировки по типу
            System.out.println("7. View All Trainings"); // Посмотреть все тренировки
            System.out.println("8. Total Training Count"); // Подсчитать общее количество тренировок
            System.out.println("9. Total Distance Covered"); // Подсчитать общее пройденное расстояние
            System.out.println("10. Total Duration of Trainings"); // Подсчитать общее время тренировок
            System.out.println("11. Save Trainings to File"); // Сохранить тренировки в файл
            System.out.println("12. Load Trainings from File"); // Загрузить тренировки из файла
            System.out.println("13. Exit Application"); // Выйти из приложения
            System.out.print("Choose an option: "); // Запрос ввода действия

            int choice = scanner.nextInt(); // Чтение выбранного действия
            scanner.nextLine(); // Очистка буфера ввода

            // Обработка выбранного действия
            switch (choice) {
                case 1 -> addTraining(sportService, scanner); // Добавление тренировки
                case 2 -> editTraining(sportService, scanner); // Редактирование тренировки
                case 3 -> deleteTraining(sportService, scanner); // Удаление тренировки
                case 4 -> deleteTrainingsByDate(sportService, scanner); // Удаление тренировок по дате
                case 5 -> findTrainingsByDateRange(sportService, scanner); // Поиск тренировок по диапазону дат
                case 6 -> findTrainingsByType(sportService, scanner); // Поиск тренировок по типу
                case 7 -> viewAllTrainings(sportService); // Просмотр всех тренировок
                case 8 -> viewTotalTrainingCount(sportService); // Подсчет общего количества тренировок
                case 9 -> viewTotalDistance(sportService); // Подсчет общего пройденного расстояния
                case 10 -> viewTotalDuration(sportService); // Подсчет общего времени тренировок
                case 11 -> saveTrainings(sportService, scanner); // Сохранение тренировок в файл
                case 12 -> loadTrainings(sportService, scanner); // Загрузка тренировок из файла
                case 13 -> {
                    // Прощание с пользователем
                    System.out.println("\n************************************************");
                    System.out.println("**  Thank you for using the Training Diary App! **");
                    System.out.println("*                  Goodbye!                      *");
                    System.out.println("************************************************");
                    running = false; // Завершение работы приложения
                }
                default -> System.out.println("Invalid option! Please try again."); // Обработка некорректного ввода
            }
        }
    }

    // Метод для добавления тренировки
    private static void addTraining(SportImpl service, Scanner scanner) {
        System.out.print("Enter date (yyyy-mm-dd): "); // Запрос даты тренировки
        LocalDate date = LocalDate.parse(scanner.nextLine()); // Чтение даты
        System.out.print("Enter type (WALKING, RUNNING, NORDIC_WALKING, FITNESS): "); // Запрос типа тренировки
        TrainingType type = TrainingType.valueOf(scanner.nextLine().toUpperCase()); // Чтение типа тренировки
        System.out.print("Enter distance (in km): "); // Запрос расстояния
        double distance = scanner.nextDouble(); // Чтение расстояния
        System.out.print("Enter duration (in hours): "); // Запрос длительности
        double duration = scanner.nextDouble(); // Чтение длительности
        scanner.nextLine(); // Очистка буфера ввода

        service.addTraining(new Training(date, type, distance, duration)); // Добавление тренировки в список
        System.out.println("Training added successfully!"); // Подтверждение успешного добавления
    }

    // Метод для редактирования тренировки
    private static void editTraining(SportImpl service, Scanner scanner) {
        viewAllTrainings(service); // Вывод всех тренировок
        System.out.print("Enter the index of the training to edit: "); // Запрос индекса тренировки
        int index = scanner.nextInt(); // Чтение индекса
        scanner.nextLine(); // Очистка буфера ввода

        System.out.print("Enter new date (yyyy-mm-dd): "); // Запрос новой даты
        LocalDate date = LocalDate.parse(scanner.nextLine()); // Чтение новой даты
        System.out.print("Enter new type (WALKING, RUNNING, NORDIC_WALKING, FITNESS): "); // Запрос нового типа
        TrainingType type = TrainingType.valueOf(scanner.nextLine().toUpperCase()); // Чтение нового типа
        System.out.print("Enter new distance (in km): "); // Запрос нового расстояния
        double distance = scanner.nextDouble(); // Чтение нового расстояния
        System.out.print("Enter new duration (in hours): "); // Запрос новой длительности
        double duration = scanner.nextDouble(); // Чтение новой длительности
        scanner.nextLine(); // Очистка буфера ввода

        service.editTraining(index, new Training(date, type, distance, duration)); // Обновление тренировки
        System.out.println("Training updated successfully!"); // Подтверждение успешного редактирования
    }

    // Метод для удаления тренировки
    private static void deleteTraining(SportImpl service, Scanner scanner) {
        viewAllTrainings(service); // Вывод всех тренировок
        System.out.print("Enter the index of the training to delete: "); // Запрос индекса тренировки
        int index = scanner.nextInt(); // Чтение индекса
        scanner.nextLine(); // Очистка буфера ввода

        service.deleteTraining(index); // Удаление тренировки
        System.out.println("Training deleted successfully!"); // Подтверждение успешного удаления
    }

    // Метод удаления тренировок по дате
    private static void deleteTrainingsByDate(SportImpl service, Scanner scanner) {
        System.out.print("Enter the date to delete all trainings (yyyy-mm-dd): "); // Запрос даты
        LocalDate date = LocalDate.parse(scanner.nextLine()); // Чтение даты

        service.deleteAllTrainingsByDate(date); // Удаление тренировок
        System.out.println("All trainings on " + date + " deleted successfully!"); // Подтверждение действия
    }

    // Метод поиска тренировок по диапазону дат
    private static void findTrainingsByDateRange(SportImpl service, Scanner scanner) {
        System.out.print("Enter start date (yyyy-mm-dd): "); // Запрос начальной даты
        LocalDate from = LocalDate.parse(scanner.nextLine()); // Чтение начальной даты
        System.out.print("Enter end date (yyyy-mm-dd): "); // Запрос конечной даты
        LocalDate to = LocalDate.parse(scanner.nextLine()); // Чтение конечной даты

        List<Training> trainings = service.findTrainingsByDateRange(from, to); // Поиск тренировок
        if (trainings.isEmpty()) { // Проверка на пустой результат
            System.out.println("No trainings found in the given date range."); // Уведомление об отсутствии тренировок
        } else {
            System.out.println("Trainings in the range " + from + " to " + to + ":"); // Заголовок
            trainings.forEach(System.out::println); // Печать найденных тренировок
        }
    }

    // Метод поиска тренировок по типу
    private static void findTrainingsByType(SportImpl service, Scanner scanner) {
        System.out.print("Enter training type (WALKING, RUNNING, NORDIC_WALKING, FITNESS): "); // Запрос типа
        String type = scanner.nextLine().toUpperCase(); // Чтение типа
        try {
            List<Training> trainings = service.findTrainingsByType(TrainingType.valueOf(type)); // Поиск тренировок
            if (trainings.isEmpty()) { // Если тренировок не найдено
                System.out.println("No trainings found for type: " + type); // Уведомление
            } else {
                System.out.println("Trainings of type " + type + ":"); // Заголовок
                trainings.forEach(System.out::println); // Печать найденных тренировок
            }
        } catch (IllegalArgumentException e) { // Обработка некорректного типа
            System.out.println("Invalid training type entered. Please try again."); // Сообщение об ошибке
        }
    }

    // Метод просмотра всех тренировок
    private static void viewAllTrainings(SportImpl service) {
        List<Training> trainings = service.getAllTrainings(); // Получение всех тренировок
        if (trainings.isEmpty()) { // Проверка на наличие тренировок
            System.out.println("No trainings available."); // Сообщение об отсутствии тренировок
        } else {
            System.out.println("All Trainings:"); // Заголовок списка
            for (int i = 0; i < trainings.size(); i++) { // Перебор всех тренировок
                System.out.println((i + 1) + ". " + trainings.get(i)); // Вывод тренировки с индексом
            }
        }
    }

    // Метод подсчета общего количества тренировок
    private static void viewTotalTrainingCount(SportImpl service) {
        System.out.println("Total number of trainings: " + service.getTrainingCount()); // Вывод общего количества
    }

    // Метод подсчета общего пройденного расстояния
    private static void viewTotalDistance(SportImpl service) {
        System.out.println("Total distance covered: " + service.calculateTotalDistance() + " km"); // Вывод общего расстояния
    }

    // Метод подсчета общего времени тренировок
    private static void viewTotalDuration(SportImpl service) {
        System.out.println("Total time spent: " + service.calculateTotalDuration() + " hours"); // Вывод общего времени
    }

    // Метод сохранения тренировок в файл
    private static void saveTrainings(SportImpl service, Scanner scanner) {
        System.out.print("Enter file name to save trainings: "); // Запрос имени файла
        String fileName = scanner.nextLine(); // Чтение имени файла

        service.saveTasks(fileName); // Сохранение тренировок
        System.out.println("Trainings saved to file: " + fileName); // Подтверждение действия
    }

    // Метод загрузки тренировок из файла
    private static void loadTrainings(SportImpl service, Scanner scanner) {
        System.out.print("Enter file name to load trainings: "); // Запрос имени файла
        String fileName = scanner.nextLine(); // Чтение имени файла

        service.loadTasks(fileName); // Загрузка тренировок
        System.out.println("Trainings loaded from file: " + fileName); // Подтверждение действия
    }
}
