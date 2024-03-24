package ru.ambassadors.competition.backend.services;

import ru.ambassadors.competition.backend.dtos.UserDTO;

public interface UserService {
    void saveUser(UserDTO user);
}
