package ru.ambassadors.competition.backend.entities;

import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.*;
import ru.ambassadors.competition.backend.dtos.AmbassadorDTO;
import ru.ambassadors.competition.backend.dtos.QuestionDTO;

import java.util.List;

@Entity
@Table(name = "Ambassadors")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AmbassadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id = null;
    @Column(name = "chat_id")
    long chatId = 0;
    @Column(name = "full_name")
    String fullName = "";
    int age = 0;
    boolean sex = true;
    @Column(name = "place_born")
    String placeBorn = "";
    @Column(name = "place_live")
    String placeLive = "";
    String social = "";
    @Column(name = "type_tourist")
    String typeTourist = "";
    Integer category = 0;
    @Column(name = "general_questions", length = 40_000)
    String generalQuestions = "";
    @Column(name = "special_questions", length = 40_000)
    String specialQuestions = "";
    @ElementCollection
    @CollectionTable(name = "Photos", joinColumns = @JoinColumn(name = "ambassador"))
    @Column(name = "url")
    List<String> photos;
    boolean winner = false;

    public AmbassadorDTO getDTO() {
        Gson gson = new Gson();
        QuestionDTO[] generalQuestionsDTO = gson.fromJson(generalQuestions, QuestionDTO[].class);
        QuestionDTO[] specialQuestionsDTO = gson.fromJson(specialQuestions, QuestionDTO[].class);
        if (generalQuestionsDTO == null) {generalQuestionsDTO = new QuestionDTO[]{};}
        if (specialQuestionsDTO == null) {specialQuestionsDTO = new QuestionDTO[]{};}
        return AmbassadorDTO.builder()
                .id(id)
                .fullName(fullName)
                .age(age)
                .sex(sex)
                .placeBorn(placeBorn)
                .placeLive(placeLive)
                .social(social)
                .typeTourist(typeTourist)
                .category(category)
                .generalQuestions(List.of(generalQuestionsDTO))
                .specialQuestions(List.of(specialQuestionsDTO))
                .photos(photos)
                .winner(winner)
                .build();
    }
}
