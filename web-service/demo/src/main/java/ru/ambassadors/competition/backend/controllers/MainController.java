package ru.ambassadors.competition.backend.controllers;

import org.springframework.web.bind.annotation.RestController;
import ru.ambassadors.competition.backend.api.MainControllerAPI;
import ru.ambassadors.competition.backend.dtos.*;
import ru.ambassadors.competition.backend.services.AmbassadorService;

import java.util.List;

@RestController
public class MainController implements MainControllerAPI {
    private final AmbassadorService ambassadorService;

    public MainController(AmbassadorService ambassadorService) {
        this.ambassadorService = ambassadorService;
    }

    @Override
    public List<AmbassadorDTO> getAmbassadors(Integer filterCategory, Integer pageIndex) {
        if (filterCategory == null) {
            return ambassadorService.getAll(pageIndex);
        } else {
            return ambassadorService.getAllByFilter(filterCategory, pageIndex);
        }
    }

    @Override
    public AmbassadorDTO getAmbassadorByID(Integer id) {
        return ambassadorService.getById(id);
    }

    @Override
    public List<AmbassadorDTO> getWinners() {
        return ambassadorService.getWinners();
    }

    @Override
    public void saveUsersVote(UserDTO userDTO) {

    }
}
