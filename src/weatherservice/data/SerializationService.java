package weatherservice.data;

/**
 * Ein generisches Interface für die Serialisierung und Deserialisierung von Objekten.
 *
 * @param <T> Der Typ des zu serialisierenden und deserialisierenden Objekts.
 */
public interface SerializationService<T> {

    /**
     * Serialisiert ein Objekt in eine String-Darstellung.
     *
     * @param t Das zu serialisierende Objekt.
     * @return Die String-Darstellung des Objekts.
     */
    String serialize(T t);

    /**
     * Deserialisiert ein Objekt aus einer String-Darstellung.
     *
     * @param str Die String-Darstellung des Objekts.
     * @return Das deserialisierte Objekt.
     */
    T deserialize(String str);
}
