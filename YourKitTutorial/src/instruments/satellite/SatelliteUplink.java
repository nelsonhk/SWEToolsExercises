package instruments.satellite;

public class SatelliteUplink {

    private final SatConn satConn;
    private final SatelliteDataCache currentData;
    private final SatelliteDataCache previousData;

    public SatelliteUplink() {
        satConn = new SatConnDummy();
        previousData = satConn.retrievePreviousData();
        currentData = satConn.retrieveCurrentData();
    }

    public boolean runStormCheckForArea(double localHumidity, double localAirPressure, double localTemperature) {
        boolean stormWarningSuggested;
        double currHumidityAvg = (currentData.getHumidityLevel() + localHumidity) / 2;
        double currAirPressureAvg = (currentData.getBarometricPressure() + localAirPressure) / 2;
        double currTemp = (currentData.getTemperature() + localTemperature) / 2;

        if ((currHumidityAvg > 30 && currTemp > 70) || (currHumidityAvg > 30 && currAirPressureAvg < 900)) {
            stormWarningSuggested = true;
        } else {
            stormWarningSuggested = checkNearbyAreaStorms();
        }

        return stormWarningSuggested;
    }

    public boolean runTornadoCheckForArea(double localHumidity, double localAirPressure, double localWindSpeed) {
        boolean tornadoWarningSuggested;
        double currHumidityAvg = (currentData.getHumidityLevel() + localHumidity) / 2;
        double currAirPressureAvg = (currentData.getBarometricPressure() + localAirPressure) / 2;
        double currWindSpeedAvg = (currentData.getWindSpeed() + localWindSpeed) / 2;
        double airPressureDiff = Math.abs(currAirPressureAvg - previousData.getBarometricPressure());
        double humidityDiff = Math.abs(currHumidityAvg - previousData.getHumidityLevel());

        if (airPressureDiff > 150 || (humidityDiff > 5 && currWindSpeedAvg > 15)) {
            tornadoWarningSuggested = true;
        } else {
            tornadoWarningSuggested = checkNearbyAreaTornadoes();
        }

        return tornadoWarningSuggested;
    }

    public boolean checkNearbyAreaTornadoes() {
        return satConn.otherStationsTornadoWarning();
    }

    public boolean checkNearbyAreaStorms() {
        return satConn.otherStationsStormWarning();
    }
}
