package me.cassayre.florian.ISN.core.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.cassayre.florian.ISN.core.map.Tile;
import me.cassayre.florian.ISN.core.map.TileMap;
import me.cassayre.florian.ISN.core.textures.Texture;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class MapLoader
{
    private MapLoader() {}

    private static final String VERSION_1 = "1.0";

    private static final String CURRENT = VERSION_1;

    public static TileMap load(JsonObject object) throws UnsupportedEncodingException, ParseException
    {

        if(object.get("version").getAsString().equals(VERSION_1))
            return loadProtocol1(object);

        throw new UnsupportedEncodingException();
    }

    private static TileMap loadProtocol1(JsonObject object) throws UnsupportedEncodingException, ParseException
    {
        JsonObject data = object.getAsJsonObject("data");

        JsonObject size = data.getAsJsonObject("size");
        int width = size.get("width").getAsInt();
        int height = size.get("height").getAsInt();

        JsonObject spawn = data.getAsJsonObject("spawn");
        int spawnX = spawn.get("x").getAsInt();
        int spawnY = spawn.get("y").getAsInt();

        String name = "";
        String description = "";

        if(data.has("options"))
        {
            JsonObject options = data.getAsJsonObject("options");

            name = getSafe(options, "name", new JsonPrimitive("")).getAsString();
            description = getSafe(options, "description", new JsonPrimitive("")).getAsString();
        }

        JsonArray lines = object.getAsJsonArray("map");

        TileMap tileMap = new TileMap(width, height);

        for(int x = 0; x < lines.size(); x++)
        {
            JsonArray column = lines.get(x).getAsJsonArray();

            for(int y = 0; y < column.size(); y++)
            {
                JsonObject tile = column.get(y).getAsJsonObject();

                tileMap.setTile(x, y, new Tile(Texture.getById(tile.get("id").getAsInt())));
            }
        }

        tileMap.setSpawn(spawnX, spawnY);

        tileMap.setName(name);
        tileMap.setDescription(description);

        return tileMap;
    }

    public static JsonObject serialize(TileMap tileMap)
    {
        JsonObject object = new JsonObject();

        object.add("version", new JsonPrimitive(CURRENT));

        JsonObject data = new JsonObject();

        JsonObject size = new JsonObject();
        size.add("width", new JsonPrimitive(tileMap.getWidth()));
        size.add("height", new JsonPrimitive(tileMap.getHeight()));

        data.add("size", size);

        JsonObject spawn = new JsonObject();
        spawn.add("x", new JsonPrimitive((int) tileMap.getSpawnX()));
        spawn.add("y", new JsonPrimitive((int) tileMap.getSpawnY()));

        data.add("spawn", spawn);

        JsonObject options = new JsonObject();
        options.add("name", new JsonPrimitive(tileMap.getName()));
        options.add("description", new JsonPrimitive(tileMap.getDescription()));

        data.add("options", options);

        object.add("data", data);


        JsonArray lines = new JsonArray();

        for(int x = 0; x < tileMap.getWidth(); x++)
        {
            JsonArray columns = new JsonArray();

            for(int y = 0; y < tileMap.getHeight(); y++)
            {
                JsonObject tile = new JsonObject();
                tile.add("id", new JsonPrimitive(tileMap.getTile(x, y).getTexture().getId()));

                columns.add(tile);
            }

            lines.add(columns);
        }

        object.add("map", lines);

        return object;
    }

    private static JsonElement getSafe(JsonObject object, String key, JsonElement defaultObject)
    {
        if(object.has(key))
            return object.get(key);
        return defaultObject;
    }
}
