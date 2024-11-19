package ait;

import ait.dau.Sport;
import ait.dau.SportImpl;
import ait.model.Training;
import ait.view.TrainingType;

import java.time.LocalDate;
import java.util.Scanner;

public class SportAppl {
    public static void main(String[] args) {
        Sport sport = new SportImpl(); // Создаем экземпляр класса SportImpl
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Добро пожаловать в дневник тренировок!");

        while (running) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить тренировку");
            System.out.println("2. Удалить тренировку");
            System.out.println("3. Удалить все тренировки по дате");
            System.out.println("4. Найти тренировки по диапазону дат");
            System.out.println("5. Посмотреть все тренировки");
            System.out.println("6. Посчитать общее количество тренировок");
            System.out.println("7. Посчитать общее пройденное расстояние");
            System.out.println("8. Посчитать общее затраченное время");
            System.out.println("9. Найти тренировки по типу");
            System.out.println("10. Выйти");
            System.out.print("Введите номер действия: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Добавить тренировку
                    System.out.print("Введите дату тренировки (гггг-мм-дд): ");
                    LocalDate date = LocalDate.parse(scanner.next());
                    System.out.print("Введите тип тренировки (WALKING, RUNNING, NORDIC_WALKING, FITNESS): ");
                    TrainingType type = TrainingType.valueOf(scanner.next().toUpperCase());
                    System.out.print("Введите расстояние (в километрах): ");
                    double distance = scanner.nextDouble();
                    System.out.print("Введите длительность (в часах): ");
                    double duration = scanner.nextDouble();

                    Training newTraining = new Training(date, type, distance, duration);
                    sport.addTraining(newTraining);
                    System.out.println("Тренировка добавлена: " + newTraining);
                    break;

                case 2: // Удалить тренировку
                    System.out.print("Введите индекс тренировки для удаления: ");
                    int index = scanner.nextInt();
                    try {
                        sport.deleteTraining(index);
                        System.out.println("Тренировка успешно удалена.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Ошибка: Индекс выходит за пределы списка тренировок.");
                    }
                    break;

                case 3: // Удалить все тренировки по дате
                    System.out.print("Введите дату для удаления тренировок (гггг-мм-дд): ");
                    LocalDate deleteDate = LocalDate.parse(scanner.next());
                    sport.deleteAllTrainingsByDate(deleteDate);
                    System.out.println("Все тренировки за " + deleteDate + " удалены.");
                    break;

                case 4: // Найти тренировки по диапазону дат
                    System.out.print("Введите начальную дату (гггг-мм-дд): ");
                    LocalDate from = LocalDate.parse(scanner.next());
                    System.out.print("Введите конечную дату (гггг-мм-дд): ");
                    LocalDate to = LocalDate.parse(scanner.next());
                    System.out.println("Тренировки в диапазоне:");
                    sport.findTrainingsByDateRange(from, to).forEach(System.out::println);
                    break;

                case 5: // Посмотреть все тренировки
                    System.out.println("Все тренировки:");
                    sport.getAllTrainings().forEach(System.out::println);
                    break;

                case 6: // Посчитать общее количество тренировок
                    System.out.println("Общее количество тренировок: " + sport.getTrainingCount());
                    break;

                case 7: // Посчитать общее пройденное расстояние
                    System.out.println("Общее пройденное расстояние: " + sport.calculateTotalDistance() + " км");
                    break;

                case 8: // Посчитать общее затраченное время
                    System.out.println("Общее затраченное время: " + sport.calculateTotalDuration() + " часов");
                    break;

                case 9: // Найти тренировки по типу
                    System.out.print("Введите тип тренировки (WALKING, RUNNING, NORDIC_WALKING, FITNESS): ");
                    try {
                        TrainingType searchType = TrainingType.valueOf(scanner.next().toUpperCase());
                        System.out.println("Тренировки типа " + searchType + ":");
                        sport.findTrainingsByType(searchType).forEach(System.out::println);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: Введен некорректный тип тренировки.");
                    }
                    break;

                case 10: // Выйти
                    System.out.println("Выход из программы. До свидания!");
                    running = false;
                    break;

                default:
                    System.out.println("Ошибка: Неверный ввод. Попробуйте снова.");
            }
        }

        scanner.close();
    }
}
