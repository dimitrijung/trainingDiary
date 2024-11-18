package ait.model;

import ait.view.TrainingType;

import java.io.Serializable;
import java.time.LocalDate;

public class Training implements Serializable {

    private LocalDate date;// Поле для хранения даты тренировки
    private TrainingType type; // Поле для хранения типа тренировки (например, WALKING, RUNNING и т.д.)
    private double distance; // Поле для хранения расстояния, пройденного во время тренировки (в километрах)
    private double duration;    // Поле для хранения времени, затраченного на тренировку (в часах)

    // Конструктор для создания объекта Training с указанными параметрами
    public Training(LocalDate date, TrainingType type, double distance, double duration) {
        this.date = date; // Устанавливаем дату тренировки
        this.type = type; // Устанавливаем тип тренировки
        this.distance = distance; // Устанавливаем пройденное расстояние
        this.duration = duration; // Устанавливаем затраченное время
    }

    // Геттер для получения даты тренировки
    public LocalDate getDate() {
        return date;
    }

    // Сеттер для изменения даты тренировки
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Геттер для получения типа тренировки
    public TrainingType getType() {
        return type;
    }

    // Сеттер для изменения типа тренировки
    public void setType(TrainingType type) {
        this.type = type;
    }

    // Геттер для получения пройденного расстояния
    public double getDistance() {
        return distance;
    }

    // Сеттер для изменения пройденного расстояния
    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Геттер для получения времени тренировки
    public double getDuration() {
        return duration;
    }

    // Сеттер для изменения времени тренировки
    public void setDuration(double duration) {
        this.duration = duration;
    }

    // Метод для представления объекта Training в виде строки
    @Override
    public String toString() {
        // Создаем экземпляр StringBuilder для эффективного построения строки
        StringBuilder sb = new StringBuilder();

        // Добавляем информацию о дате тренировки
        sb.append("Training{date=").append(date);

        // Добавляем информацию о типе тренировки
        sb.append(", type=").append(type);

        // Добавляем информацию о пройденном расстоянии
        sb.append(", distance=").append(distance).append(" km");

        // Добавляем информацию о времени тренировки
        sb.append(", duration=").append(duration).append(" hours");

        // Закрываем фигурную скобку и завершаем построение строки
        sb.append('}');

        // Возвращаем итоговую строку
        return sb.toString();
    }
}
