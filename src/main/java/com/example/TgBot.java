package com.example.tgbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class TgBot {
	private final TelegramBot bot;

	public TgBot(String token) {
		bot = new TelegramBot(token);
	}

	public void run() {
		bot.setUpdatesListener(updates -> this.handleUpdates(updates));
		System.out.println("Бот запущен. Напишите ему в Telegram.");
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ignored) {}
    }
	
    private int handleUpdates(java.util.List<Update> updates) {
        for (Update u : updates) {
            if (u.message() != null && u.message().text() != null) {
                long chatId = u.message().chat().id();
                String messageText = u.message().text();

                SendMessage response = createResponse(chatId, messageText);
                if (response != null) {
                    bot.execute(response);
                }
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public SendMessage createResponse(long chatId, String messageText) {
        if (messageText == null) return null;
        String msg = messageText.trim().toLowerCase();

        switch (msg) {
            case "/start":
            case "/help":
                return new SendMessage(chatId,
                        "Привет! Я бот-эхо. Напиши мне что-нибудь, и я это повторю.:\n" +
                        "Список команд:\n" +
                        "/start - Запустить бота\n" +
                        "/help - Показать эту справку");
            default:
                return new SendMessage(chatId, "ТЫ НАПИСАЛ: " + messageText);
        }
    }
}