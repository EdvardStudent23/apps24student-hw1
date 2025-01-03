package ua.edu.ucu.apps.tempseries;
import lombok.Getter;

@Getter
public class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    public TempSummaryStatistics(
    double avgTemp,
    double devTemp,
    double minTemp,
    double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
}