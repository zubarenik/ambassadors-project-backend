package ru.ambassadors.competition.backend.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.ambassadors.competition.backend.entities.UserEntity;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    String name;
    String surname;
    Integer age;
    String email;
    List<QuestionDTO> questions;
    Integer ambassadorId;
    Boolean checkBoxMailing;
    Boolean checkBoxData;
}
