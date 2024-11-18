package TrainingDiary.dao;

import TrainingDiary.model.Training;
import TrainingDiary.model.TrainingType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainingDiaryImpl implements TrainingDiary, Serializable {

    private List<Training> trainings = new ArrayList<>(); //список для хранения тренировок



    @Override
    public boolean addTraining(Training training) {
        if (training != null) {// Проверка, чтобы обьект был не null
            return trainings.add(training);//Добавляем тренировку и возвращаем успешность операции
        }

        return false;// Если тренировка null, возвращаем false
    }

    @Override
    public void deleteTraining(int index) {
        if (index >= 0 && index < trainings.size()){//проверяем что индекс в пределах списка
            
            trainings.remove(index);// Удаляем тренировку по индексу
        }else {
            System.out.println("Invalid index:" + index);//сообщаем об ошибке при некорректном индексе
        }


    }

    @Override
    public void deleteAllTrainingsByDate(LocalDate date) {
        if (date != null){
            //проверяем что дата не null
            trainings.removeIf(training -> training.getDate().equals(date));//Удаляем все тренировки с указанной датой
        }


    }

    @Override
    public List<Training> findTrainingsByDateRange(LocalDate from, LocalDate to) {
        List<Training> result = new ArrayList<>();//список для хранения найденных тренировок
        for (Training training : trainings){
            //Проверяем что дата тренировки в заданном диапазоне
            if (  training.getDate() != null && ! training.getDate().isBefore(from)&& ! training.getDate().isAfter(to)){
                result.add(training);//Добавляем тренировку в результат
            }
        }
       return result;
    }

    @Override
    public int getTrainingCount() {

        return trainings.size();//Возвращаем количество тренировок
    }

    @Override
    public double calculateTotalDistance() {

        return trainings.stream().mapToDouble(Training ::getDistance).sum();
        //Вычисляем общ дистанцию с помощью Stream Api
    }

    @Override
    public double calculateTotalDuration() {

        return trainings.stream().mapToDouble(Training ::getDuration).sum();
        //Вычисляем общ  время тренировок с помощью Stream Api
    }

    @Override
    public List<Training> findTrainingsByType(String type) {
        List<Training> result = new ArrayList<>();//список для хранения найденных тренировок
        try {
            //Преобразуем строку в перечисление TrainingType
            TrainingType trainingType = TrainingType.valueOf(type.toUpperCase());
            for (Training training : trainings){//сравниваем тип тренировки
                if (training.getType() == trainingType){
                    result.add(training);//Добавляем подходящую тренировку
                }
            }
        }catch (IllegalArgumentException e){
            //Выводим сообщение если тип тренировки некорректный
            System.out.println("The wrong type of training:" + type);
        }

        return result ;
    }

    @Override
    public List<Training> getAllTrainings() {

        return new ArrayList<>(trainings);//Возвращаем копию списка тренировок
    }
}