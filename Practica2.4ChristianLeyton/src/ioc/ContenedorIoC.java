package ioc;

import java.util.HashMap;
import java.util.Map;

public class ContenedorIoC {
	private Map<Class<?>, Object> contenedor = new HashMap<>();

    // Con este metodo registraremos una dependencia en el contenedor, aplicando la DI
    public void registrar(Class<?> interfaz, Object implementacion) {
        contenedor.put(interfaz, implementacion);
    }

    // Con este metodo obtendremos la instancia de la clase desde este contenedor
    public <T> T obtener(Class<T> clase) {
        Object instancia = contenedor.get(clase);
        if (instancia == null) {
            throw new RuntimeException("No se pudo encontrar una implementaci�n para " + clase.getName());
        }
        return clase.cast(instancia);
    }
}
