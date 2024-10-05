package ua.edu.ucu.apps.tempseries;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private static final double ABSOLUTE_ZERO = -273.0; 

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries.clone();
    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series are empty.");
        }
        double sum = 0;
        for (double temp: temperatureSeries) {
            sum += temp;
        }
        return sum/temperatureSeries.length;
    }

    public double deviation() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series are empty.");
        }
        double mean = average();
        double dev = 0;
        for (double temp: temperatureSeries) {
            dev += (temp - mean) * (temp - mean);
        }
        return Math.sqrt(dev/temperatureSeries.length);
    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series are empty");
        }
        double minim = temperatureSeries[0];
        for (int i = 1; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < minim) {
                minim = temperatureSeries[i];
            }
        }
        return minim;
    }

    public double max() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series are empty");
        }
        double maxim = temperatureSeries[0];

        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] > maxim) {
                maxim = temperatureSeries[i];
            }
        }
        return maxim;
    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series are empty");
    }

    double closest = temperatureSeries[0];

    for (double temp : temperatureSeries) {
        if (Math.abs(temp) < Math.abs(closest)) {
            closest = temp;
        }
        else if (Math.abs(temp) == Math.abs(closest) && temp > closest) {
            closest = temp;
        }
    }

    return closest;
}

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series are empty");
        }
        double dist = Math.abs(temperatureSeries[0] - tempValue);
        int index = 0; 
        for (int i = 1; i < temperatureSeries.length; i++) {
            double curdist = Math.abs(temperatureSeries[i] - tempValue);
            if (dist > curdist) {
                index = i;
                dist = curdist;
            }
        }
        return temperatureSeries[index];
    }

    public double[] findTempsLessThen(double tempValue) {
        int count = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                count++;
            }
        }
    
        if (count == 0) {
            return new double[0];
        }
        double[] result = new double[count];
        int index = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                result[index] = temp;
                index++;
            }
        }
    
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int count = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (tempValue < temperatureSeries[i]) {
                count++;
            }
        }
        if (count == 0) {
            return new double[0];
        }

        double[] result = new double[count];
        int index = 0;

        for (double temp : temperatureSeries) {
            if (temp > tempValue) {
                result[index] = temp;
                index++;
            }
        }
        return result;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        int count = 0;
        for (double temp: temperatureSeries) {
            if (temp >= lowerBound && temp <= upperBound) {
                count++;
            }
        }

        if (count == 0) {
            return new double[0];
        }

        double[] result = new double[count];
        int index = 0;
        for (double temp : temperatureSeries) {
            if (temp >= lowerBound && temp <= upperBound) {
                result[index] = temp;
                index++;
            }
        }

        return result;
    }

    public void reset() {
        this.temperatureSeries = new double[0];
    }

    public double[] sortTemps() {
        double[] sorted = temperatureSeries.clone();

        int n = sorted.length;
        for (int i = 0; i < n - 1; i++) {
            int minpos = i;

            for (int j = i + 1; j < n; j++) {
                if (sorted[j] < sorted[minpos]) {
                    minpos = j;
                }
            }
            if (minpos != i) {
                double cur = sorted[i];
                sorted[i] = sorted[minpos];
                sorted[minpos] = cur;
            }
        }
        return sorted;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series are empty");
        }
    
        double avgTemp = average();
        double devTemp = deviation();
        double minTemp = min();
        double maxTemp = max();
    
        return new TempSummaryStatistics(
            avgTemp, devTemp, minTemp, maxTemp);    
    }

    public int addTemps(double... temps) {
        for (double temp: temps) {
            if (temp < ABSOLUTE_ZERO) {
                throw new InputMismatchException(
                    "The temperature cannot be < -273°C");
            }
        }
        int oldLength = temperatureSeries.length;
        int newLength = oldLength + temps.length;

        if (newLength > temperatureSeries.length) {
            double[] newTempSeries = new double[
                Math.max(temperatureSeries.length * 2, newLength)];
            System.arraycopy(temperatureSeries, 0, newTempSeries,
             0, temperatureSeries.length);
            temperatureSeries = newTempSeries;
        }
        System.arraycopy(temps, 0,
         temperatureSeries, oldLength, temps.length);
        return newLength;
    }
}
