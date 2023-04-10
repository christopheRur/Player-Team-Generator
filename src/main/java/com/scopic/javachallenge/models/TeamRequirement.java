package com.scopic.javachallenge.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "teams")
public class TeamRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String position;
    @NotEmpty
    private String mainSkill;
    @NotEmpty
    private int numberOfPlayers;

    public Long getId() {
        return id;
    }

    public TeamRequirement setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public TeamRequirement setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getMainSkill() {
        return mainSkill;
    }

    public TeamRequirement setMainSkill(String mainSkill) {
        this.mainSkill = mainSkill;
        return this;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public TeamRequirement setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        return this;
    }
}
