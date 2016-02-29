package vn.whoever.models;

import java.util.ArrayList;

/**
 * Created by spider man on 2/29/2016.
 */
public class ArrayLanguage {

    public ArrayList<String> languages;

    public ArrayList getListLanguage() {
        languages = new ArrayList<>();
        languages.add("English");
        languages.add("Tiếng Việt");
        languages.add("Esperanton");
        return languages;
    }
}
