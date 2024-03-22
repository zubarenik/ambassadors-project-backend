package com.shulmin.pavel.russiangeographicalsociety.services;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface PhotoService {
    void processPhoto(Message message);

    void deleteOldPhotos(Long chatId);
}
