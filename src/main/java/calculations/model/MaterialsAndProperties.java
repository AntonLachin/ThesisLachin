package calculations.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Materials_and_properties")
public class MaterialsAndProperties {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int id;
    @Column(name = "compressiveResistance")
    private double compressiveResistance;
    @Column(name = "stretchingResistance")
    private double stretchingResistance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCompressiveResistance() {
        return compressiveResistance;
    }

    public void setCompressiveResistance(double compressiveResistance) {
        this.compressiveResistance = compressiveResistance;
    }

    public double getStretchingResistance() {
        return stretchingResistance;
    }

    public void setStretchingResistance(double stretchingResistance) {
        this.stretchingResistance = stretchingResistance;
    }


}
