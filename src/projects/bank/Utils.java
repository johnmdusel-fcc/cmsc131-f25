package projects.bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    /**
     * 
     * @return the local time in a given format:
     * 
     *         yyyy-MM-dd@HH:mm:ss 2025-11-03@10:30:25
     * 
     */
    public static String timestamp() {
        return LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm:ss"));
    }

}