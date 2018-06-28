package com.sd.his.wrapper;

/**
 * Created by jamal on 6/8/2018.
 */
public class RaceWrapper {
    private long id;
    private String nameRace;
    private boolean selected;

    public RaceWrapper() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameRace() {
        return nameRace;
    }

    public void setNameRace(String nameRace) {
        this.nameRace = nameRace;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
