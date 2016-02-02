package sample;

import javafx.animation.Timeline;

import java.util.ArrayList;

/**
 * Created by Karolinka i Sebuś on 29.01.2016.
 */
public class ArrayListener {

    private ArrayList<Timeline> lista;

    public ArrayListener() {

        lista = new ArrayList<>();
    }

    public void add (Timeline time){

        lista.add(time);

    }
    public void remove (Timeline time){

        lista.remove(time);

    }
    public int sizeOf (){

        return  lista.size();

    }



}
