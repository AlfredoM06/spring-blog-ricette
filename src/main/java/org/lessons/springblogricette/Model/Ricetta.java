package org.lessons.springblogricette.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ricette")
public class Ricetta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotEmpty
    private String title;
    @NotEmpty
    @Lob
    private String recipe;
    @NotEmpty
    private String foto;
    @NotNull
    @Min(0)
    private BigDecimal time;
    @NotNull
    @Min(0)
    private Integer portions;
    @NotEmpty
    @Lob
    private String description;

    @ManyToMany
    private List<Categoria> categorie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }
}
