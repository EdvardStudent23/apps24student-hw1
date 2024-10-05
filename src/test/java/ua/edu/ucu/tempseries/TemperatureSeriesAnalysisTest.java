package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import java.util.InputMismatchException;

import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

   @Test
   public void testAverageWithOneElementArray() {
       // setup input data and expected result
       double[] temperatureSeries = {-1.0};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
       double expResult = -1.0;

       // call tested method
       double actualResult = seriesAnalysis.average();

       // compare expected result with actual result
       assertEquals(expResult, actualResult, 0.00001);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testAverageWithEmptyArray() {
       double[] temperatureSeries = {};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

       // expect exception here
       seriesAnalysis.average();
   }

   @Test
   public void testAverage() {
       double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
       double expResult = 1.0;

       double actualResult = seriesAnalysis.average();

       assertEquals(expResult, actualResult, 0.00001);
   }
    
   @Test
    public void testDeviation() {
        double[] temperatureSeries = {2.0, 4.0, 4.0, 4.0, 5.0, 5.0, 7.0, 9.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 2.0;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.deviation();
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.6, -4.5, 6.7, 0.4, -4.6};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -4.6;
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.6, -4.5, 6.7, 0.4, -4.6};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 6.7;
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {-1.0, 2.0, 0.5, -0.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.5;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }
    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {1.0, -2.0, 3.0, 3.9};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.9;

        double actualResult = seriesAnalysis.findTempClosestToValue(3.5);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThan() {
        double[] temperatureSeries = {1.0, -2.0, -2.3, 3.0, 4.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-2.0, -2.3};

        double[] actualResult = seriesAnalysis.findTempsLessThen(0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThan() {
        double[] temperatureSeries = {1.0, -2.7, 3.0, 4.6};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {3.0, 4.6};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(2.5);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsInRange() {
        double[] temperatureSeries = {1.0, -2.0, 3.0, 4.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {1.0, 3.0};

        double[] actualResult = seriesAnalysis.findTempsInRange(0, 3);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }
    @Test
    public void testSortTemps() {
        double[] temperatureSeries = {3.0, 2.0, -1.1, 5.0, -5.6, 5.05};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-5.6, -1.1, 2.0, 3.0, 5.0, 5.05};

        double[] actualResult = seriesAnalysis.sortTemps();

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsWithInvalidTemperature() {
        double[] temperatureSeries = {1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.addTemps(-8980);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics summary = seriesAnalysis.summaryStatistics();

        assertEquals(1.0, summary.getAvgTemp(), 0.00001);
        assertEquals(3.741657, summary.getDevTemp(), 0.00001);
        assertEquals(-5.0, summary.getMinTemp(), 0.00001);
        assertEquals(5.0, summary.getMaxTemp(), 0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {3.4, 4.3, 5.7, 5.6};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int newLength = seriesAnalysis.addTemps(1.0, 2.0, -7.7);

        assertEquals(7, newLength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.summaryStatistics();
    }

    @Test
    public void testFindTempsLessNoMatches() {
    double[] temperatureSeries = {1.1, 4.0, 3.77};
    TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

    double[] actualResult = seriesAnalysis.findTempsLessThen(0.5);

    assertEquals(0, actualResult.length);
    }
    @Test
    public void testFindTempsGreaterThanWithNoMatches() {
    double[] temperatureSeries = {-5.0, -3.66, -1.5};
    TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

    double[] actualResult = seriesAnalysis.findTempsGreaterThen(3.0);

    assertEquals(0, actualResult.length);
    }

    @Test
public void testFindTempClosestToZeroWithEqualDistances() {
    double[] temperatureSeries = {-1.0, 1.0, 4.0};
    TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
    
    double expResult = 1.0;
    double actualResult = seriesAnalysis.findTempClosestToZero();

    assertEquals(expResult, actualResult, 0.00001);
    }
}
