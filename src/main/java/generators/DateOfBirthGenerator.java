package generators;

import java.time.LocalDate;
import java.util.Random;

public class DateOfBirthGenerator {
    private LocalDate minDate;
    private LocalDate maxDate;
    private Random random;

    public DateOfBirthGenerator(LocalDate minDate, LocalDate maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.random = new Random();
    }

    public LocalDate generateDate() {
        int minDay = (int) minDate.toEpochDay();
        int maxDay = (int) maxDate.toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    @Override
    public String toString() {
        return "RandomDate{" +
                "maxDate=" + maxDate +
                ", minDate=" + minDate +
                '}';
    }
}