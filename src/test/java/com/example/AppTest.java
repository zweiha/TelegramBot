package com.example.tgbot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class AppTest {
    private TgBot bot;
    private long chatId = 12345L;
    
    @BeforeEach
    void setUp()
    {
        bot = new TgBot("dummytoken");
    }

    @Test
    void testStart()
    {
        SendMessage response = bot.createResponse(chatId, "/start");
        assertNotNull(response);
        assertTrue(response.getParameters().get("text").toString().contains("Привет! Я бот-эхо."));
    }

    @Test
    void testHelp()
    {
        SendMessage response = bot.createResponse(chatId, "/help");
        assertNotNull(response);
        assertTrue(response.getParameters().get("text").toString().contains("Привет! Я бот-эхо."));
    } 

    @Test
    void testStartCaseSensitivity()
    {
        SendMessage response = bot.createResponse(chatId, "/START");
        assertNotNull(response);
        assertTrue(response.getParameters().get("text").toString().contains("Привет! Я бот-эхо."));
    }

    @Test
    void testCaseSensitivity()
    {
        SendMessage response = bot.createResponse(chatId, "/HELp");
        assertNotNull(response);
        assertTrue(response.getParameters().get("text").toString().contains("Привет! Я бот-эхо."));
    }

    @Test
    void testEcho()
    {
        String userMessage = "aboba";
        SendMessage response = bot.createResponse(chatId, userMessage);
        assertNotNull(response);
        assertEquals("ТЫ НАПИСАЛ: " + userMessage, response.getParameters().get("text").toString());
    }
}