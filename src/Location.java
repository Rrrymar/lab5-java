import javax.xml.bind.annotation.*;



@XmlType(name = "location")
@XmlRootElement
// создаём поля для каждой координаты
public class Location {
    @XmlElement
    private Long x;

    @XmlElement
    private Long y;

    @XmlElement
    private Integer z; // =! 0

// Конструктор полей
    public Location(Long x, Long y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

// Конструктор локации
    public Location() {}

// Для представления данных локации в строковом виде
    @Override
    public String toString() {
        return "Location{ x=" + x +
                ", y=" + y +
                ", z=" + z + "}";
    }
    public Long getX() {
        return x;
    }
    public Long getY() {
        return y;
    }
    public Integer getZ() {
        return z;
    }


}
