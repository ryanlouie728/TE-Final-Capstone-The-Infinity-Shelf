package com.techelevator.services;

import com.google.gson.*;
import com.techelevator.model.CharacterDto;
import com.techelevator.model.ComicDto;
import com.techelevator.model.CreatorDto;
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

    @Override
    public List<ComicDto> getComicsDtoByTitle(String title) {
        Map<String, String> extraParams = new HashMap<>();
        extraParams.put("titleStartsWith", title);
        HttpEntity<?> entity = getBaseEntity();
        Map<String, String> params = getBaseParams(extraParams);
        String url = getApiUrl("comics", extraParams);
        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, params);
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(response.getBody(), JsonObject.class);
        JsonArray comics = object.getAsJsonObject("data").getAsJsonArray("results");
        return mapJsonArrayToComicDtoList(comics);
    }

    @Override
    public List<ComicDto> getComicsDto() {
        HttpEntity<?> entity = getBaseEntity();
        Map<String, String> params = getBaseParams();
        String url = getApiUrl("comics");
        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, params);
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(response.getBody(), JsonObject.class);
        JsonArray comics = object.getAsJsonObject("data").getAsJsonArray("results");
        return mapJsonArrayToComicDtoList(comics);
    }

    public List<ComicDto> mapJsonArrayToComicDtoList(JsonArray array) {
        List<ComicDto> comics = new ArrayList<>();
        for(JsonElement comic : array) {
            comics.add(mapJsonElementToComic(comic));
        }
        return comics;
    }

    private ComicDto mapJsonElementToComic(JsonElement element) {
        ComicDto comic = new ComicDto();

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

        JsonObject characters = element.getAsJsonObject().get("characters").getAsJsonObject();
        comic.setCharacters(mapCharacterList(characters.getAsJsonArray("items")));
        JsonObject creators = element.getAsJsonObject().get("creators").getAsJsonObject();
        comic.setCreators(mapCreatorList(creators.getAsJsonArray("items")));
        return comic;
    }

    private List<CharacterDto> mapCharacterList(JsonArray array) {
        List<CharacterDto> characters = new ArrayList<>();
        for (JsonElement character : array) {
            characters.add(mapCharacter(character));
        }
        return characters;
    }

    private CharacterDto mapCharacter(JsonElement element) {
        CharacterDto character = new CharacterDto();
        JsonObject object = element.getAsJsonObject();
        JsonElement uri = object.get("resourceURI");
        if (!uri.isJsonNull()) {
            String url = uri.getAsString();
            character.setCharacterId(Integer.parseInt(url.substring(url.lastIndexOf("/") + 1)));
        } else {
            character.setCharacterId(null);
        }
        JsonElement name = object.get("name");
        if (!name.isJsonNull()) {
            character.setName(name.getAsString());
        }

        return character;
    }

    private List<CreatorDto> mapCreatorList(JsonArray array) {
        List<CreatorDto> creators = new ArrayList<>();
        for (JsonElement creator : array) {
            creators.add(mapCreator(creator));
        }
        return creators;
    }

    private CreatorDto mapCreator(JsonElement element) {
        CreatorDto creator = new CreatorDto();
        JsonObject object = element.getAsJsonObject();

        JsonElement uri = object.get("resourceURI");
        if (!uri.isJsonNull()) {
            String url = uri.getAsString();
            creator.setCreatorId(Integer.parseInt(url.substring(url.lastIndexOf("/") + 1)));
        } else {
            creator.setCreatorId(null);
        }
        JsonElement name = object.get("name");
        if (!name.isJsonNull()) {
            creator.setName(name.getAsString());
        }

        JsonElement role = object.get("role");
        if (!role.isJsonNull()) {
            creator.setRole(role.getAsString());
        }

        return creator;
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
