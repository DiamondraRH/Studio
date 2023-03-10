package application.models;

import javax.persistence.*;

@Entity
@Table(name = "plateau")
public class Plateau {
    @Id
    @Column(name = "id_plateau")
    private int idPlateau;

    @ManyToOne(targetEntity = TypePlateau.class)
    @JoinColumn(name = "id_type_plateau")
    private TypePlateau typePlateau;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "numero")
    private int numero;

    @Column(name = "lieu")
    private String lieu;

    public int getIdPlateau() {
        return idPlateau;
    }

    public void setIdPlateau(int idPlateau) {
        this.idPlateau = idPlateau;
    }

    public TypePlateau getTypePlateau() {
        return typePlateau;
    }

    public void setTypePlateau(TypePlateau typePlateau) {
        this.typePlateau = typePlateau;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
