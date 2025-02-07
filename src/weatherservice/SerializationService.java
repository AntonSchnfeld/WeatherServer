package weatherservice;

public interface SerializationService<T> {
    String serialize(T t);
    T deserialize(String str);
}
