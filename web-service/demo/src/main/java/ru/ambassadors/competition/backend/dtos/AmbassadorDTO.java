package ru.ambassadors.competition.backend.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
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
    private List<String> photos;
    private Boolean winner;
}
