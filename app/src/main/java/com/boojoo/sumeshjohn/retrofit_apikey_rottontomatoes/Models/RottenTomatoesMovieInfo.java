package com.boojoo.sumeshjohn.retrofit_apikey_rottontomatoes.Models;

import java.util.List;
import java.util.Map;

/**
 * Created by Sumeshjohn on 2014-12-12.
 */
public class RottenTomatoesMovieInfo {
    private String title;
    private int year;                           //can be String
    private String synopsis;
    private String runtime;                     //can be int
    private List<String> genres;                //can be String[]
    private Map<String, String> ratings;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("title: ");
        stringBuilder.append(title);
        stringBuilder.append("\nyear: ");
        stringBuilder.append(year);
        stringBuilder.append("\nsynopsis: ");
        stringBuilder.append(synopsis);
        stringBuilder.append("\nruntime: ");
        stringBuilder.append(runtime);
        if (genres != null) {
            stringBuilder.append("\ngenres: " + genres);
        }
        if (ratings != null) {
            stringBuilder.append("\nratings: ");
            for (Map.Entry entry : ratings.entrySet()) {
                stringBuilder.append("\n-");
                stringBuilder.append(entry.getKey());
                stringBuilder.append(": ");
                stringBuilder.append(entry.getValue());
            }
        }
        return stringBuilder.toString();
    }
}