
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "classPerson")
@XmlAccessorType(XmlAccessType.NONE)

public class ClassPerson {
    @XmlElement(name = "classPerson")
    private List<Person> classPerson = null;

    public List<Person> getClassPerson() {
        return classPerson;
    }
    public void setClassPerson(ArrayList<Person> classPerson) {
        this.classPerson = classPerson;
    }
}
