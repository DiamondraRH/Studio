package application.models;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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

    private float distance(Plateau p){
        float yCarre=(float) Math.pow((p.getLongitude()-this.longitude),2);
        float xCarre=(float) Math.pow((p.getLatitude()-this.latitude),2);
        return yCarre+xCarre;
    }
    public Plateau nextPlateau(ArrayList<Plateau> list){
        if(list.size()==0)
            return null;
        int indice=0;
        float distance=this.distance(list.get(0));
        for(int x=1;x<list.size();x++){
            Plateau p= list.get(x);
            float next=this.distance(p);
            if(next<distance&&this.idPlateau!=getIdPlateau())
                indice=x;
        }
        return list.get(indice);
    }
    public ArrayList<Scene> allScene(ArrayList<Scene> scene){
        ArrayList<Scene> ls=new ArrayList<Scene>();
        for(Scene sc:scene){
            if(this.idPlateau==sc.getPlateau().getIdPlateau())
                ls.add(sc);
                //scene.remove(sc);
        }
        return ls;
    }
    public static HashMap<Plateau,ArrayList<Scene>> sceneParPlateau(ArrayList<Plateau> plateau,ArrayList<Scene> scene){
        Scene.orderByPreferableAsc(scene);
        HashMap<Plateau,ArrayList<Scene>> map=new HashMap<>();
        for(Plateau pl: plateau){
            ArrayList<Scene> sc=pl.allScene(scene);
            if(sc.size()!=0)
                map.put(pl,sc);
        }
        return map;
    }
    private static Plateau firstPlateau(Scene scene,HashMap<Plateau,ArrayList<Scene>> plateau){
        for(Plateau pl: plateau.keySet()){
            if(pl.getIdPlateau()==scene.getPlateau().getIdPlateau())
                return pl;
        }
        return null;
    }
    public static ArrayList<Plateau> orderByLongLat(Scene scene,HashMap<Plateau,ArrayList<Scene>> map){
        ArrayList<Plateau> pl=new ArrayList<Plateau>();
        Plateau first=firstPlateau(scene,map);
        pl.add(first);
        ArrayList<Plateau> list=new ArrayList<Plateau>(map.keySet());
        list.remove(first);
        int indice=0;
        while (list.size()!=0){
            Plateau p=pl.get(indice).nextPlateau(list);
            list.remove(p);
            pl.add(p);
            indice++;
        }
        return pl;
    }
}
