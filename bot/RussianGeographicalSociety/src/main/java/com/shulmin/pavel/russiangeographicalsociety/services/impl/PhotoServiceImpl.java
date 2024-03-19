package com.shulmin.pavel.russiangeographicalsociety.services.impl;

import com.shulmin.pavel.russiangeographicalsociety.entity.Photo;
import com.shulmin.pavel.russiangeographicalsociety.repositories.AmbassadorsRepository;
import com.shulmin.pavel.russiangeographicalsociety.repositories.PhotosRepository;
import com.shulmin.pavel.russiangeographicalsociety.services.PhotoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@PropertySource("classpath:application.yml")
public class PhotoServiceImpl implements PhotoService {
    @Value("${bot.token}")
    private String token;
    @Value("${service.file-info.uri}")
    private String fileInfoUri;
    @Value("${service.file-storage.uri}")
    private String fileStorageUri;
    private final AmbassadorsRepository ambassadorsRepository;
    private final PhotosRepository photosRepository;

    public PhotoServiceImpl(AmbassadorsRepository ambassadorsRepository, PhotosRepository photosRepository) {
        this.ambassadorsRepository = ambassadorsRepository;
        this.photosRepository = photosRepository;
    }

    @Override
    public Photo processPhoto(Message message) {
        String fileId = null;
        if (message.hasPhoto()){
            fileId = message.getPhoto().get(message.getPhoto().size() - 1).getFileId();
        }
        if (message.hasDocument()) {
            fileId = message.getDocument().getFileId();
        }
        ResponseEntity<String> response = getFilePath(fileId);
        String filePath = "";
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonObject = new JSONObject(response.getBody());
            filePath = jsonObject.getJSONObject("result").getString("file_path");
        } else {
            throw new RuntimeException("Не удалось получить фото");
        }
        String fileUri = fileStorageUri.replace("{token}", token).replace("{filePath}", filePath);
        Integer ambassadorId = ambassadorsRepository.findFirstByChatId(message.getChatId()).get().getId();
        Photo photo = new Photo();
        photo.setUri(fileUri);
        photo.setAmbassador(ambassadorId);
        return photosRepository.save(photo);
    }

    private ResponseEntity<String> getFilePath(String fileId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(fileInfoUri, HttpMethod.GET, request, String.class, token, fileId);
    }
}
