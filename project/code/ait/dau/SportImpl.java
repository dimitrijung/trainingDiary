package ait.dau;

import ait.model.Training;
import ait.view.TrainingType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SportImpl implements Sport {

    private final List<Training> trainings = new ArrayList<>(); // Хранилище для тренировок

    @Override
    public boolean addTraining(Training training) {
        if (training != null) {//если тренировка не равна null...
            return trainings.add(training); // ...добавляем тренировку в список.  Возвращает true, если добавление успешно.
        }
        return false;// Если передан null, возвращаем false
    }

    @Override
    public void deleteTraining(int index) {
        if (index >= 0 && index < trainings.size()) {//если индекс элемента больше или равен 0 и меньше длины массива то...
            trainings.remove(index); //... удаляем тренировку по индексу. Проверяем корректность индекса перед удалением.
        }
    }

    @Override
    public void deleteAllTrainingsByDate(LocalDate date) {
        trainings.removeIf(training -> training.getDate().equals(date)); // Метод removeIf проходит по каждому элементу списка trainings. Лямбда принимает объект training (элемент из списка trainings) и сравнивает дату тренировки (training.getDate()) с заданной датой (date) с помощью метода .equals(). Если даты совпадают, объект с указанной датой удаляется из списка.
    }

    @Override
    public List<Training> findTrainingsByDateRange(LocalDate from, LocalDate to) {
        return trainings.stream()//Используется Stream для фильтрации тренировок в диапазоне дат.
                .filter(training -> !training.getDate().isBefore(from) && !training.getDate().isAfter(to))//Лямбда принимает объект training (элемент из списка trainings) и отфильтровывавет диапазон между дат тренировк (training.getDate()), isBefore(from) - от и isAfter(to) - до.
                .collect(Collectors.toList()); // Возвращаем тренировки в заданном диапазоне дат
    }

    @Override
    public int getTrainingCount() {
        return trainings.size(); // Возвращаем общее количество тренировок, по размеру.
    }

    @Override
    public double calculateTotalDistance() {
        return trainings.stream()// trainings.stream() - cоздает поток (Stream) из списка тренировок (trainings), который позволяет выполнять последовательные операции (напр6 фильтрацию)
                .mapToDouble(Training::getDistance)//каждый объект Training из потока преобразуется в значение типа double, которое возвращает метод getDistance()
                .sum(); // Суммируем дистанции всех тренировок
    }

    @Override
    public double calculateTotalDuration() {
        return trainings.stream()//аналогично calculateTotalDistance
                .mapToDouble(Training::getDuration)
                .sum(); // Суммируем время всех тренировок
    }

    @Override
    public List<Training> findTrainingsByType(TrainingType type) {//Проверяет, соответствует ли тип тренировки переданному.
        return trainings.stream()
                .filter(training -> training.getType() == type)
                .toList();

//        try {
//            TrainingType trainingType = TrainingType.valueOf(type.toUpperCase());//Используется TrainingType.valueOf, чтобы привести строку к перечислению TrainingType. При некорректном типе возвращается пустой список.
//            return trainings.stream()
//                    .filter(training -> training.getType() == trainingType)//фильтруем/сравниваем и оставляем только те тренировки (элементы), у которых тип (training.getType()) совпадает с заданным типом (trainingType). Возвращает true, если тип тренировки совпадает с trainingType. В противном случае — false.
//                    .collect(Collectors.toList()); // Находим тренировки указанного типа
//        } catch (IllegalArgumentException e) {
//            return List.of(); // Если тип некорректен, возвращаем пустой список
//        }
    }

    @Override
    public List<Training> getAllTrainings() {
        return new ArrayList<>(trainings); // Возвращаем копию списка тренировок
    }
}
