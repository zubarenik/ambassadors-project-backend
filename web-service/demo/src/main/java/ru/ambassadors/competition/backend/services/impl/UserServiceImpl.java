package ru.ambassadors.competition.backend.services.impl;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import ru.ambassadors.competition.backend.dtos.UserDTO;
import ru.ambassadors.competition.backend.entities.AmbassadorEntity;
import ru.ambassadors.competition.backend.entities.UserEntity;
import ru.ambassadors.competition.backend.exceptions.DuplicateEmailException;
import ru.ambassadors.competition.backend.repositories.AmbassadorsRepository;
import ru.ambassadors.competition.backend.repositories.UsersRepository;
import ru.ambassadors.competition.backend.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final AmbassadorsRepository ambassadorsRepository;
    private final UsersRepository usersRepository;

    public UserServiceImpl(AmbassadorsRepository ambassadorsRepository, UsersRepository usersRepository) {
        this.ambassadorsRepository = ambassadorsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void saveUser(UserDTO user) {
        Optional<AmbassadorEntity> optionalAmbassador = ambassadorsRepository.findById(user.getAmbassadorId());
        AmbassadorEntity ambassador;
        if (optionalAmbassador.isPresent()) {
            ambassador = optionalAmbassador.get();
            ambassador.setWinner(true);
            ambassadorsRepository.save(ambassador);
        } else {
            ambassador = null;
        }

        Gson gson = new Gson();
        String questions = gson.toJson(user.getQuestions());

        UserEntity userEntity = UserEntity.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .questions(questions)
                .ambassador(ambassador)
                .checkBoxMailing(user.getCheckBoxMailing())
                .checkBoxData(user.getCheckBoxData())
                .build();

        try {
            usersRepository.save(userEntity);
        } catch (Exception ex) {
            throw new DuplicateEmailException();
        }
    }
}
