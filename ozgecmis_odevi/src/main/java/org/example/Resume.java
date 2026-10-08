package org.example;

import java.util.ArrayList;
import java.util.List;

public class Resume {
    private PersonalInfo personalInfo;
    private List<Experience> experiences;

    public Resume(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
        this.experiences = new ArrayList<>();
    }

    public void addExperience(Experience experience) {
        this.experiences.add(experience);
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }
}