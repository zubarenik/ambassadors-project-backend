package com.shulmin.pavel.russiangeographicalsociety.services.impl;

import com.shulmin.pavel.russiangeographicalsociety.constant.BotStates;
import com.shulmin.pavel.russiangeographicalsociety.entity.BotStateEntity;
import com.shulmin.pavel.russiangeographicalsociety.repositories.BotStatesRepository;
import com.shulmin.pavel.russiangeographicalsociety.services.BotStatesService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BotStatesServiceImpl implements BotStatesService {
    private final BotStatesRepository botStatesRepository;

    public BotStatesServiceImpl(BotStatesRepository botStatesRepository) {
        this.botStatesRepository = botStatesRepository;
    }

    @Override
    public void create(long chatId) {
        BotStateEntity botStateEntity = new BotStateEntity();
        botStateEntity.setChatId(chatId);
        botStatesRepository.save(botStateEntity);
    }

    @Override
    public BotStates getState(long chaId) {
        BotStateEntity botStateEntity = botStatesRepository.findById(chaId).orElseThrow();
        return BotStates.valueOf(botStateEntity.getState());
    }

    @Override
    public void updateState(long chatId, BotStates state) {
        Optional<BotStateEntity> optional = botStatesRepository.findById(chatId);
        if (optional.isPresent()) {
            BotStateEntity botStateEntity = optional.get();
            botStateEntity.setState(state.name());
            botStatesRepository.save(botStateEntity);
        } else {
            create(chatId);
        }
    }
}
