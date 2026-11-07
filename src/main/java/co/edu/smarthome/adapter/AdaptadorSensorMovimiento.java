package co.edu.smarthome.adapter;

import java.util.Objects;

public final class AdaptadorSensorMovimiento implements SensorMovimiento {
    private final SensorMovimientoAntiguo sensorAntiguo;

    public AdaptadorSensorMovimiento(SensorMovimientoAntiguo sensorAntiguo) {
        this.sensorAntiguo = Objects.requireNonNull(sensorAntiguo);
    }

    @Override public boolean hayMovimiento() { return sensorAntiguo.leerNivelMovimiento() > 0; }
}
