package com.example.tgbot;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load(); 

        String token = System.getenv("BOT_TOKEN"); 
        if (token == null || token.isBlank()) {
            token = dotenv.get("BOT_TOKEN");
        }
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Set BOT_TOKEN env var or .env");
        }

        TgBot bot = new TgBot(token);
        bot.run();
    }
}

