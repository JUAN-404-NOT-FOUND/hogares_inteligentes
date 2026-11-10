package co.edu.smarthome.adapter;

import java.util.Objects;

/** Adapta el nivel numérico del sensor legado a la lectura booleana usada por el mediador. */
public final class AdaptadorSensorMovimiento implements SensorMovimiento {
    private final SensorMovimientoAntiguo sensorAntiguo;

    public AdaptadorSensorMovimiento(SensorMovimientoAntiguo sensorAntiguo) {
        this.sensorAntiguo = Objects.requireNonNull(sensorAntiguo);
    }

    @Override public boolean hayMovimiento() { return sensorAntiguo.leerNivelMovimiento() > 0; }
}
