package com.techelevator.services;

import com.google.gson.*;
import com.techelevator.model.SimpleComicDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestComicService extends BaseService implements ComicService{

    @Override
    public List<SimpleComicDto> getComicsByTitle(String title) {
        Map<String, String> extraParams = new HashMap<>();
        extraParams.put("title", title);
        HttpEntity<?> entity = getBaseEntity();
        Map<String, String> params = getBaseParams(extraParams);
        String url = getApiUrl("comics", extraParams);
        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, params);
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(response.getBody(), JsonObject.class);
        JsonArray comics = object.getAsJsonObject("data").getAsJsonArray("results");
        return mapJsonArrayToList(comics);
    }

    private List<SimpleComicDto> mapJsonArrayToList(JsonArray array) {
        List<SimpleComicDto> comics = new ArrayList<>();
        for (JsonElement comic : array) {
            comics.add(mapJsonElementToSimpleComic(comic));
        }
        return comics;
    }

    private SimpleComicDto mapJsonElementToSimpleComic(JsonElement element) {
        SimpleComicDto comic = new SimpleComicDto();
        try {
            JsonObject object = element.getAsJsonObject();
            JsonElement id = object.get("id");
            if (!id.isJsonNull()) {
                comic.setId(id.getAsInt());
            } else {
                comic.setId(null);
            }

            JsonElement title = object.get("title");
            if (!title.isJsonNull()) {
                comic.setTitle(title.getAsString());
            } else {
                comic.setTitle(null);
            }

            JsonElement issueNumber = object.get("issueNumber");
            if (!issueNumber.isJsonNull()) {
                comic.setIssueNumber(issueNumber.getAsString());
            } else {
                comic.setIssueNumber(null);
            }

            JsonElement description = object.get("description");
            if (!description.isJsonNull()) {
                comic.setDescription(description.getAsString());
            } else {
                comic.setDescription(null);
            }

            JsonObject thumbnail = object.get("thumbnail").getAsJsonObject();
            JsonElement path = thumbnail.get("path");
            JsonElement extension = thumbnail.get("extension");
            if (!path.isJsonNull() && !extension.isJsonNull()) {
                comic.setThumbnailUrl(thumbnail.get("path").getAsString() + "." + thumbnail.get("extension").getAsString());
            } else {
                comic.setThumbnailUrl(null);
            }

            return comic;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
