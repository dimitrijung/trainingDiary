package ait.dao;

import ait.model.Training;
import ait.model.TrainingType;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SportImpl implements Sport {
    // Хранение всех тренировок
    private final List<Training> trainings = new ArrayList<>();

    @Override
    public boolean addTraining(Training training) {
        // Добавляем тренировку, если она не null
        if (training != null) {
            trainings.add(training);
            return true; // Успешное добавление
        }
        return false; // Неудача при добавлении
    }

    @Override
    public void editTraining(int index, Training newTraining) {
        // Редактируем тренировку по индексу, если индекс валиден
        if (index >= 0 && index < trainings.size() && newTraining != null) {
            trainings.set(index, newTraining);
        }
    }

    @Override
    public void deleteTraining(int index) {
        // Удаляем тренировку по индексу, если индекс валиден
        if (index >= 0 && index < trainings.size()) {
            trainings.remove(index);
        }
    }

    @Override
    public void deleteAllTrainingsByDate(LocalDate date) {
        // Удаляем все тренировки, соответствующие указанной дате
        trainings.removeIf(training -> training.getDate().equals(date));
    }

    @Override
    public List<Training> findTrainingsByDateRange(LocalDate from, LocalDate to) {
        // Находим тренировки, которые находятся в заданном диапазоне дат
        return trainings.stream()
                .filter(training -> !training.getDate().isBefore(from) && !training.getDate().isAfter(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<Training> findTrainingsByType(TrainingType type) {
        // Находим тренировки, соответствующие указанному типу
        return trainings.stream()
                .filter(training -> training.getType() == type)
                .collect(Collectors.toList());
    }

    @Override
    public int getTrainingCount() {
        // Возвращаем общее количество тренировок
        return trainings.size();
    }

    @Override
    public double calculateTotalDistance() {
        // Суммируем расстояния всех тренировок
        return trainings.stream()
                .mapToDouble(Training::getDistance)
                .sum();
    }

    @Override
    public double calculateTotalDuration() {
        // Суммируем общее время всех тренировок
        return trainings.stream()
                .mapToDouble(Training::getDuration)
                .sum();
    }

    @Override
    public List<Training> getAllTrainings() {
        // Возвращаем копию списка всех тренировок
        return new ArrayList<>(trainings);
    }

    @Override
    public void saveTasks(String fileName) {
        // Сохраняем список тренировок в файл
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(trainings); // Сериализация списка
            System.out.println("Trainings saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving trainings: " + e.getMessage());
        }
    }

    @Override
    public void loadTasks(String fileName) {
        // Загружаем список тренировок из файла
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Training> loadedTrainings = (List<Training>) ois.readObject(); // Десериализация списка
            trainings.clear(); // Очищаем текущий список
            trainings.addAll(loadedTrainings); // Добавляем загруженные тренировки
            System.out.println("Trainings loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading trainings: " + e.getMessage());
        }
    }
}
