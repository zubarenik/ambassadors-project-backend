package com.shulmin.pavel.russiangeographicalsociety.entity;

import jakarta.persistence.*;
import lombok.*;

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
    boolean winner = false;
}
