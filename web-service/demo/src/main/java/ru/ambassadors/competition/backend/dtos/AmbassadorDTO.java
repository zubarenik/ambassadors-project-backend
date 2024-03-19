package ru.ambassadors.competition.backend.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AmbassadorDTO {
    private Integer id;
    private String fullName;
    private Integer age;
    private Boolean sex;
    private String placeBorn;
    private String placeLive;
    private String social;
    private String typeTourist;
    private Integer category;
    private List<QuestionDTO> generalQuestions;
    private List<QuestionDTO> specialQuestions;
    private Boolean winner;

    AmbassadorDTO(Integer id, String fullName, Integer age, Boolean sex, String placeBorn, String placeLive, String social, String typeTourist, Integer category, List<QuestionDTO> generalQuestions, List<QuestionDTO> specialQuestions, Boolean winner) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.sex = sex;
        this.placeBorn = placeBorn;
        this.placeLive = placeLive;
        this.social = social;
        this.typeTourist = typeTourist;
        this.category = category;
        this.generalQuestions = generalQuestions;
        this.specialQuestions = specialQuestions;
        this.winner = winner;
    }

    public static AmbassadorDTOBuilder builder() {
        return new AmbassadorDTOBuilder();
    }

    public static class AmbassadorDTOBuilder {
        private Integer id;
        private String fullName;
        private Integer age;
        private Boolean sex;
        private String placeBorn;
        private String placeLive;
        private String social;
        private String typeTourist;
        private Integer category;
        private List<QuestionDTO> generalQuestions;
        private List<QuestionDTO> specialQuestions;
        private Boolean winner;

        AmbassadorDTOBuilder() {
        }

        public AmbassadorDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public AmbassadorDTOBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public AmbassadorDTOBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public AmbassadorDTOBuilder sex(Boolean sex) {
            this.sex = sex;
            return this;
        }

        public AmbassadorDTOBuilder placeBorn(String placeBorn) {
            this.placeBorn = placeBorn;
            return this;
        }

        public AmbassadorDTOBuilder placeLive(String placeLive) {
            this.placeLive = placeLive;
            return this;
        }

        public AmbassadorDTOBuilder social(String social) {
            this.social = social;
            return this;
        }

        public AmbassadorDTOBuilder typeTourist(String typeTourist) {
            this.typeTourist = typeTourist;
            return this;
        }

        public AmbassadorDTOBuilder category(Integer category) {
            this.category = category;
            return this;
        }

        public AmbassadorDTOBuilder generalQuestions(List<QuestionDTO> generalQuestions) {
            this.generalQuestions = generalQuestions;
            return this;
        }

        public AmbassadorDTOBuilder specialQuestions(List<QuestionDTO> specialQuestions) {
            this.specialQuestions = specialQuestions;
            return this;
        }

        public AmbassadorDTOBuilder winner(Boolean winner) {
            this.winner = winner;
            return this;
        }

        public AmbassadorDTO build() {
            return new AmbassadorDTO(this.id, this.fullName, this.age, this.sex, this.placeBorn, this.placeLive, this.social, this.typeTourist, this.category, this.generalQuestions, this.specialQuestions, this.winner);
        }

        public String toString() {
            return "AmbassadorDTO.AmbassadorDTOBuilder(id=" + this.id + ", fullName=" + this.fullName + ", age=" + this.age + ", sex=" + this.sex + ", placeBorn=" + this.placeBorn + ", placeLive=" + this.placeLive + ", social=" + this.social + ", typeTourist=" + this.typeTourist + ", category=" + this.category + ", generalQuestions=" + this.generalQuestions + ", specialQuestions=" + this.specialQuestions + ", winner=" + this.winner + ")";
        }
    }
}
