package model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Calendar implements Serializable {
    private static final long serialVersionUID = -8297802628843331249L;
    private HashMap<LocalDate, List<TimeSlot>> timeByDay;

    public HashMap<LocalDate, List<TimeSlot>> getTimeByDay() {
        return timeByDay;
    }

    public void setTimeByDay(HashMap<LocalDate, List<TimeSlot>> timeByDay) {
        this.timeByDay = timeByDay;
    }
}
