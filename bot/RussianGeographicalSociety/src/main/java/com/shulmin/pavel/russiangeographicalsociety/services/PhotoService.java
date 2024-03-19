package com.shulmin.pavel.russiangeographicalsociety.services;

import com.shulmin.pavel.russiangeographicalsociety.entity.Photo;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface PhotoService {
    Photo processPhoto(Message message);
}
