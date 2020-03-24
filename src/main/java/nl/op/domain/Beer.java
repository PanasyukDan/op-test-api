package nl.op.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Beer {

    //Ive took more important fields
    @Id
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double abv;
    private Double ibu;
    private Integer volume;
    private String volumeUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public Double getIbu() {
        return ibu;
    }

    public void setIbu(Double ibu) {
        this.ibu = ibu;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(String volumeUnit) {
        this.volumeUnit = volumeUnit;
    }

    @JsonProperty("volume")
    private void unpackVolume(Map<String,Object> volume) {
        this.volume = (Integer) volume.get("value");
        this.volumeUnit = (String) volume.get("unit");
    }

    @JsonProperty("image_url")
    private void unpackImageUrl(String string) {
        this.imageUrl = string;
    }
}
