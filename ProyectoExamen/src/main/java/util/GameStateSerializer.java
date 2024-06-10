package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.Container;
import model.Enemy;
import model.Hero;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GameStateSerializer {
    private final Gson gson;
    private final String persistenceUrl = "http://localhost:8080/api/game"; // URL del endpoint de persistencia

    public GameStateSerializer() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void saveGameState(Container container) {
        GameState gameState = new GameState(container);
        String json = gson.toJson(gameState);

        // Guardar localmente (opcional)
        try (FileWriter writer = new FileWriter("game_state.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Enviar a la API REST
        sendGameState(gameState);
    }

    public GameState loadGameStateFromApi(Long id) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(persistenceUrl + "/" + id);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    return gson.fromJson(new InputStreamReader(response.getEntity().getContent()), GameState.class);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendGameState(GameState gameState) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(persistenceUrl +"/save");
            request.addHeader("content-type", "application/json");
            StringEntity entity = new StringEntity(gson.toJson(gameState));
            request.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                System.out.println("Response Status: " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class GameState {
        public Hero hero;
        public List<Enemy> enemies;
        public int currentLevel;

        public GameState(Container container) {
            this.hero = container.getHero();
            this.enemies = container.getEnemies();
            this.currentLevel = container.getCurrentLevel();
        }

        public Hero getHero() {
            return hero;
        }

        public List<Enemy> getEnemies() {
            return enemies;
        }

        public int getCurrentLevel() {
            return currentLevel;
        }
    }
}