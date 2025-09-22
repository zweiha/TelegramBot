package com.example.tgbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class Main {
    public static void main(String[] args) {
        String token = System.getenv("BOT_TOKEN");
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Set BOT_TOKEN env var");
        }

        TelegramBot bot = new TelegramBot(token);

        bot.setUpdatesListener((java.util.List<Update> updates) -> {
            for (Update u : updates) {
                if (u.message() != null && u.message().text() != null) {
                    long chatId = u.message().chat().id();
                    String text  = u.message().text();
                    bot.execute(new SendMessage(chatId, "ТЫ НАПИСАЛ: " + text));
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

        System.out.println("Бот запущен. Напишите ему в Telegram.");
        try { Thread.currentThread().join(); } catch (InterruptedException ignored) {}
    }
}

