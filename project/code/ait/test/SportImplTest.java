package ait.test;

import ait.dau.Sport;
import ait.dau.SportImpl;
import ait.model.Training;
import ait.view.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SportImplTest {

    Sport sport;//реализация интерфейса
    LocalDate now = LocalDate.now();//текущая дата

    @BeforeEach
    void setUp() {

        sport = new SportImpl();
        sport.addTraining(new Training(now.minusDays(7), TrainingType.WALKING, 5.0, 2.5));
        sport.addTraining(new Training(now.minusDays(7), TrainingType.RUNNING, 10.0, 1.0));
        sport.addTraining(new Training(now.minusDays(7), TrainingType.NORDIC_WALKING, 8.5, 1.5));
        sport.addTraining(new Training(now.minusDays(7), TrainingType.FITNESS, 0.0, 0.5));

    }

    @Test
    //addTraining: Проверяет добавление новой тренировки.
    void addTraining() {
        assertEquals(4, sport.getTrainingCount()); // Убедимся, что в списке 4 тренировки
        boolean added = sport.addTraining(new Training(now.minusDays(3), TrainingType.RUNNING, 5.0, 0.8));
        assertTrue(added); // Проверяем успешное добавление
        assertEquals(5, sport.getTrainingCount()); // Убедимся, что теперь 5 тренировок

    }

    @Test
    //deleteTraining: Удаляет тренировку по индексу.
    void deleteTraining() {
        sport.deleteTraining(2); // Удаляем тренировку с индексом 2
        assertEquals(3, sport.getTrainingCount()); // Убедимся, что тренировок стало 3
        List<Training> trainings = sport.getAllTrainings();
        assertEquals(TrainingType.WALKING, trainings.get(0).getType());
        assertEquals(TrainingType.RUNNING, trainings.get(1).getType());
        assertEquals(TrainingType.FITNESS, trainings.get(2).getType());
    }

    @Test
    // deleteAllTrainingsByDate: Удаляет все тренировки за указанную дату.
    void deleteAllTrainingsByDate() {
        sport.deleteAllTrainingsByDate(now.minusDays(7)); // Удаляем все тренировки за 7 дней назад
        assertEquals(0, sport.getTrainingCount()); // Убедимся, что список теперь пуст
    }

    @Test
    //findTrainingsByDateRange: Находит тренировки в заданном диапазоне дат.
    void findTrainingsByDateRange() {
        LocalDate from = now.minusDays(8);
        LocalDate to = now.minusDays(6);
        List<Training> result = sport.findTrainingsByDateRange(from, to);
        assertEquals(4, result.size()); // Все тренировки за указанный диапазон должны быть найдены
    }

    @Test
    //getTrainingCount: Проверяет общее количество тренировок.
    void getTrainingCount() {
        assertEquals(4, sport.getTrainingCount()); // Проверяем, что общее количество тренировок — 4
    }

    @Test
    //calculateTotalDistance: Подсчитывает общую дистанцию.
    void calculateTotalDistance() {
        double totalDistance = sport.calculateTotalDistance();
        assertEquals(23.5, totalDistance, 0.01); // Проверяем, что сумма расстояний тренировок совпадает
    }

    @Test
    //calculateTotalDuration: Подсчитывает общее время тренировок.
    void calculateTotalDuration() {
        double totalDuration = sport.calculateTotalDuration();
        assertEquals(5.5, totalDuration, 0.01); // Проверяем, что сумма времени тренировок совпадает
    }

    @Test
    //findTrainingsByType: Ищет тренировки по типу.
    void findTrainingsByType() {
        List<Training> result = sport.findTrainingsByType("RUNNING");
        assertEquals(1, result.size()); // Ожидаем 1 тренировку типа RUNNING
        assertEquals(TrainingType.RUNNING, result.get(0).getType());
    }

    @Test
    //getAllTrainings: Проверяет возврат всех тренировок.
    void getAllTrainings() {
        List<Training> allTrainings = sport.getAllTrainings();
        assertEquals(4, allTrainings.size()); // Убедимся, что все 4 тренировки возвращаются
        assertEquals(TrainingType.WALKING, allTrainings.get(0).getType());
        assertEquals(TrainingType.RUNNING, allTrainings.get(1).getType());
        assertEquals(TrainingType.NORDIC_WALKING, allTrainings.get(2).getType());
        assertEquals(TrainingType.FITNESS, allTrainings.get(3).getType());
    }
}//end of class