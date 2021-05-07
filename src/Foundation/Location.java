package Foundation;

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

    @XmlElement
    private String name;

    // Конструктор полей
    public Location(Long x, Long y, Integer z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    // Конструктор локации
    public Location() {}

    // Для представления данных локации в строковом виде
    @Override
    public String toString() {
        return "Location{ x=" + x +
                ", y=" + y +
                ", z=" + z + '}';
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
    public String getName(){return name; }

}

