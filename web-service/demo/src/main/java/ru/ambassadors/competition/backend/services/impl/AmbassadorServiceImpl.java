package ru.ambassadors.competition.backend.services.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ambassadors.competition.backend.dtos.AmbassadorDTO;
import ru.ambassadors.competition.backend.entities.AmbassadorEntity;
import ru.ambassadors.competition.backend.repositories.AmbassadorsRepository;
import ru.ambassadors.competition.backend.services.AmbassadorService;

import java.util.List;
import java.util.Optional;

@Service
public class AmbassadorServiceImpl implements AmbassadorService {
    private final AmbassadorsRepository ambassadorsRepository;

    public AmbassadorServiceImpl(AmbassadorsRepository ambassadorsRepository) {
        this.ambassadorsRepository = ambassadorsRepository;
    }

    @Override
    public List<AmbassadorDTO> getAll(int pageIndex) {
        List<AmbassadorEntity> ambassadorEntities = ambassadorsRepository.findByOrderById(PageRequest.of(pageIndex, 50));
        return ambassadorEntities.stream().map(AmbassadorEntity::getDTO).toList();
    }

    @Override
    public List<AmbassadorDTO> getAllByFilter(int filterCategory, int pageIndex) {
        List<AmbassadorEntity> ambassadorEntities = ambassadorsRepository.findByCategoryOrderById(filterCategory, PageRequest.of(pageIndex, 1));
        return ambassadorEntities.stream().map(AmbassadorEntity::getDTO).toList();
    }

    @Override
    public AmbassadorDTO getById(int id) {
        Optional<AmbassadorEntity> optionalAmbassador = ambassadorsRepository.findById(id);
        if (optionalAmbassador.isPresent()) {
            return optionalAmbassador.get().getDTO();
        } else {
            return AmbassadorDTO.builder().build();
        }
    }

    @Override
    public List<AmbassadorDTO> getWinners() {
        List<AmbassadorEntity> winners = ambassadorsRepository.findByWinnerTrue();
        return winners.stream().map(AmbassadorEntity::getDTO).toList();
    }
}
