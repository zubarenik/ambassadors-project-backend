package ru.ambassadors.competition.backend.services;

import ru.ambassadors.competition.backend.dtos.AmbassadorDTO;

import java.util.List;

public interface AmbassadorService {
    List<AmbassadorDTO> getAll(int pageIndex);
    List<AmbassadorDTO> getAllByFilter(int filter, int pageIndex);
    AmbassadorDTO getById(int id);
    List<AmbassadorDTO> getWinners();
}
