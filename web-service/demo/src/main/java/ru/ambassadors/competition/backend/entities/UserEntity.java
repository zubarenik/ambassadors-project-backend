package ru.ambassadors.competition.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private int age;
    @Column(name = "questions")
    private String questions;
    @ManyToOne
    @JoinColumn(name = "ambassador")
    private AmbassadorEntity ambassador;
    @Column(name = "checkbox_mailing")
    private boolean checkBoxMailing;
    @Column(name = "checkbox_data")
    private boolean checkBoxData;
}
