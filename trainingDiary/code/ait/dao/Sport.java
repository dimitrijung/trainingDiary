package ait.dao;

import ait.model.Training;
import ait.menu.TrainingType;

import java.time.LocalDate;
import java.util.List;

public interface Sport {

    boolean addTraining(Training training); // Добавление тренировки

    void editTraining(int index, Training newTraining); // Редактирование тренировки

    void deleteTraining(int index); // Удаление тренировки по индексу

    void deleteAllTrainingsByDate(LocalDate date); // Удаление всех тренировок по указанной дате

    List<Training> findTrainingsByDateRange(LocalDate from, LocalDate to); // Поиск тренировок в диапазоне дат

    List<Training> findTrainingsByType(TrainingType type); // Поиск тренировок по типу

    int getTrainingCount(); // Подсчет общего количества тренировок

    double calculateTotalDistance(); // Подсчет общего пройденного расстояния

    double calculateTotalDuration(); // Подсчет общего времени тренировок

    List<Training> getAllTrainings(); // Получение всех тренировок

    void saveTasks(String fileName); // Сохранение тренировок в файл

    void loadTasks(String fileName); // Загрузка тренировок из файла
}
