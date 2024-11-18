package TrainingDiary.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainingImpl implements Training, Serializable {

    public List<Training> trainings = new ArrayList<>();


    @Override
    public boolean addTraining(Training Training) {
        return false;
    }

    @Override
    public void deleteTraining(int index) {


    }

    @Override
    public void deleteAllTrainingsByDate(LocalDate date) {

    }

    @Override
    public List<Training> findTrainingsByDateRange(LocalDate from, LocalDate to) {
        return List.of();
    }

    @Override
    public int getTrainingCount() {

        return 0;
    }

    @Override
    public double calculateTotalDistance() {

        return 0;
    }

    @Override
    public double calculateTotalDuration() {

        return 0;
    }

    @Override
    public List<Training> findTrainingsByType(String type) {

        return List.of();
    }

    @Override
    public List<Training> getAllTrainings() {

        return null;
    }
}