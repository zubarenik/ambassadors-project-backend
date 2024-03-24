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
    private Integer id = null;
    @Column(name = "chat_id")
    private long chatId = 0;
    @Column(name = "full_name")
    private String fullName = "";
    private int age = 0;
    private boolean sex = true;
    @Column(name = "place_born")
    private String placeBorn = "";
    @Column(name = "place_live")
    private String placeLive = "";
    private String social = "";
    @Column(name = "type_tourist")
    private String typeTourist = "";
    private Integer category = 0;
    @Column(name = "general_questions", length = 40_000)
    private String generalQuestions = "";
    @Column(name = "special_questions", length = 40_000)
    private String specialQuestions = "";
    @ElementCollection
    @CollectionTable(name = "Photos", joinColumns = @JoinColumn(name = "ambassador"))
    @Column(name = "url")
    private List<String> photos;
    @OneToMany(mappedBy = "ambassador")
    private List<UserEntity> users;
    private boolean winner = false;

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
