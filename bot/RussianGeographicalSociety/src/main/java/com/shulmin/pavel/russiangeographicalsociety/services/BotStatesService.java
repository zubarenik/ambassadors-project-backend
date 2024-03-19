package com.shulmin.pavel.russiangeographicalsociety.services;

import com.shulmin.pavel.russiangeographicalsociety.constant.BotStates;
import jakarta.annotation.PostConstruct;

public interface BotStatesService {
    void create(long chatId);
    BotStates getState(long chaId);
    void updateState(long chatId, BotStates state);
}
