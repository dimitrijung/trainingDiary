package ait.test;

import ait.dao.SportImpl;
import ait.model.Training;
import ait.menu.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SportImplTest {

    private SportImpl sport; // Объект для тестирования

    @BeforeEach
    void setUp() {
        // Инициализация объекта перед каждым тестом
        sport = new SportImpl();
        sport.addTraining(new Training(LocalDate.of(2024, 11, 1), TrainingType.RUNNING, 5.0, 0.5));
        sport.addTraining(new Training(LocalDate.of(2024, 11, 2), TrainingType.WALKING, 3.0, 1.0));
        sport.addTraining(new Training(LocalDate.of(2024, 11, 3), TrainingType.FITNESS, 2.0, 1.5));
    }

    @Test
    void testAddTraining() {
        // Добавление новой тренировки
        Training newTraining = new Training(LocalDate.of(2024, 11, 4), TrainingType.NORDIC_WALKING, 4.0, 1.2);
        assertTrue(sport.addTraining(newTraining)); // Проверяем, что тренировка добавлена
        assertEquals(4, sport.getTrainingCount()); // Проверяем, что общее количество тренировок увеличилось
        assertEquals(newTraining, sport.getAllTrainings().get(3)); // Проверяем, что тренировка добавлена в конец списка
    }

    @Test
    void testEditTraining() {
        // Редактирование существующей тренировки
        Training updatedTraining = new Training(LocalDate.of(2024, 11, 1), TrainingType.WALKING, 6.0, 1.0);
        sport.editTraining(0, updatedTraining); // Обновляем тренировку по индексу
        assertEquals(updatedTraining, sport.getAllTrainings().get(0)); // Проверяем, что тренировка обновлена
    }

    @Test
    void testDeleteTraining() {
        // Удаление тренировки по индексу
        sport.deleteTraining(1); // Удаляем тренировку с индексом 1
        assertEquals(2, sport.getTrainingCount()); // Проверяем, что общее количество тренировок уменьшилось
        assertNotEquals(TrainingType.WALKING, sport.getAllTrainings().get(0).getType()); // Проверяем, что удаленная тренировка отсутствует
    }

    @Test
    void testDeleteAllTrainingsByDate() {
        // Удаление всех тренировок по дате
        sport.deleteAllTrainingsByDate(LocalDate.of(2024, 11, 2)); // Удаляем тренировки на дату 2024-11-02
        assertEquals(2, sport.getTrainingCount()); // Проверяем, что количество тренировок уменьшилось
        assertTrue(sport.findTrainingsByType(TrainingType.WALKING).isEmpty()); // Проверяем, что тренировки типа WALKING удалены
    }

    @Test
    void testFindTrainingsByDateRange() {
        // Поиск тренировок в диапазоне дат
        List<Training> result = sport.findTrainingsByDateRange(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2));
        assertEquals(2, result.size()); // Проверяем, что найдено 2 тренировки
        assertEquals(TrainingType.RUNNING, result.get(0).getType()); // Проверяем тип первой тренировки
        assertEquals(TrainingType.WALKING, result.get(1).getType()); // Проверяем тип второй тренировки
    }

    @Test
    void testFindTrainingsByType() {
        // Поиск тренировок по типу
        List<Training> result = sport.findTrainingsByType(TrainingType.FITNESS);
        assertEquals(1, result.size()); // Проверяем, что найдена одна тренировка типа FITNESS
        assertEquals(TrainingType.FITNESS, result.get(0).getType()); // Проверяем тип найденной тренировки
    }

    @Test
    void testGetTrainingCount() {
        // Проверяем общее количество тренировок
        assertEquals(3, sport.getTrainingCount()); // Проверяем, что количество тренировок равно 3
    }

    @Test
    void testCalculateTotalDistance() {
        // Подсчет общего расстояния
        assertEquals(10.0, sport.calculateTotalDistance(), 0.01); // Проверяем, что общее расстояние равно 10.0 км
    }

    @Test
    void testCalculateTotalDuration() {
        // Подсчет общего времени
        assertEquals(3.0, sport.calculateTotalDuration(), 0.01); // Проверяем, что общее время равно 3.0 часам

    }

    @Test
    void testGetAllTrainings() {
        // Проверяем, что метод возвращает все тренировки
        List<Training> result = sport.getAllTrainings();
        assertEquals(3, result.size()); // Проверяем, что количество тренировок равно 3
        assertEquals(TrainingType.RUNNING, result.get(0).getType()); // Проверяем тип первой тренировки
        assertEquals(TrainingType.WALKING, result.get(1).getType()); // Проверяем тип второй тренировки
    }

    @Test
    void testSaveAndLoadTasks() {
        // Сохранение и загрузка тренировок
        String fileName = "Trainings.dat";
        sport.saveTasks(fileName); // Сохраняем тренировки в файл

        SportImpl loadedSport = new SportImpl(); // Создаем новый объект для загрузки данных
        loadedSport.loadTasks(fileName); // Загружаем тренировки из файла

        assertEquals(sport.getTrainingCount(), loadedSport.getTrainingCount()); // Проверяем, что количество тренировок совпадает

        assertEquals(sport.calculateTotalDistance(), loadedSport.calculateTotalDistance(), 0.01); // Проверяем, что общее расстояние совпадает
        //0.01 в методе assertEquals — это дельта (точность сравнения), которая используется при сравнении чисел с плавающей точкой (double или float).
        //assertEquals(sport.calculateTotalDistance(), loadedSport.calculateTotalDistance(), 0.01);
        //Проверяется, что рассчитанная общая дистанция (calculateTotalDistance()) в исходном объекте sport и загруженном объекте loadedSport совпадают с точностью до 0.01 (1 сотой километра).

        assertEquals(sport.calculateTotalDuration(), loadedSport.calculateTotalDuration(), 0.01); // Проверяем, что общее время совпадает
        //assertEquals(sport.calculateTotalDuration(), loadedSport.calculateTotalDuration(), 0.01);
        //Проверяется, что общее время тренировок (calculateTotalDuration()) совпадает с точностью до 0.01 (1 сотой часа).
    }
}
