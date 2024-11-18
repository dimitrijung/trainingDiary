package dau;
import java.time.LocalDate;
import java.util.List;
public interface Training {

    boolean addTraining (Training Training);  //Добавление

    void deleteTraining (int index);  //Удаление по индексу

    void deleteAllTrainingsByDate(LocalDate date);//Удаление всех тренировок в указанной дате

    List<Training> findTrainingsByDateRange(LocalDate from, LocalDate to);//Поиск тренировок по дате (от и до)

    int getTrainingCount(); //Подсчет общего кол-ва тренировок

    double calculateTotalDistance();//Подсчет общего расстояния (в км)

    double calculateTotalDuration();//Подсчет общего времени тренировок (в часах)

    List<Training> findTrainingsByType(String type); //Поиск тренировок по типу

    List<Training> getAllTrainings(); //Все тренировки


}
