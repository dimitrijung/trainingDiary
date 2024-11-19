package ait.dau;

import ait.model.Training;
import ait.view.TrainingType;

import java.time.LocalDate;
import java.util.List;

public interface Sport {

    boolean addTraining (Training training);  //Добавление

    void deleteTraining (int index);  //Удаление по индексу

    void deleteAllTrainingsByDate(LocalDate date);//Удаление всех тренировок в указанной дате

    List<Training> findTrainingsByDateRange(LocalDate from, LocalDate to);//Поиск тренировок по дате (от и до)

    int getTrainingCount(); //Подсчет общего кол-ва тренировок

    double calculateTotalDistance();//Подсчет общего расстояния (в км)

    double calculateTotalDuration();//Подсчет общего времени тренировок (в часах)

    List<Training> findTrainingsByType(TrainingType type); //Поиск тренировок по типу

    List<Training> getAllTrainings(); // все тренировки

}
