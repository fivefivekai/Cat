package au.edu.cat;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Data implements Serializable {



    private Weight weight;
    @NonNull
    @PrimaryKey
    public String id;
    public String name;
    public String temperament;
    public String origin;
    public String country_codes;
    public String country_code;
    public String description;
    public String life_span;
    public int child_friendly;
    public int dog_friendly;
    public int stranger_friendly;
    public String wikipedia_url;

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCountry_codes() {
        return country_codes;
    }

    public void setCountry_codes(String country_codes) {
        this.country_codes = country_codes;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }


    public int getChild_friendly() {
        return child_friendly;
    }

    public void setChild_friendly(int child_friendly) {
        this.child_friendly = child_friendly;
    }

    public int getDog_friendly() {
        return dog_friendly;
    }

    public void setDog_friendly(int dog_friendly) {
        this.dog_friendly = dog_friendly;
    }


    public int getStranger_friendly() {
        return stranger_friendly;
    }

    public void setStranger_friendly(int stranger_friendly) {
        this.stranger_friendly = stranger_friendly;
    }


    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }


    public static class Weight implements Serializable {


        private String imperial;
        private String metric;

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }


}
