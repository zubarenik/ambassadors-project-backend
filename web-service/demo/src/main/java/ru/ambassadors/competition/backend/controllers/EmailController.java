package ru.ambassadors.competition.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ambassadors.competition.backend.api.EmailControllerAPI;

@RestController
public class EmailController implements EmailControllerAPI {
    @Override
    public void confirmEmail(String userId) {

    }
}
