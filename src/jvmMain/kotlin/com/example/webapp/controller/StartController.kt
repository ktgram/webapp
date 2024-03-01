package com.example.webapp.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.annotations.UpdateHandler
import eu.vendeli.tgbot.api.message
import eu.vendeli.tgbot.api.sendMessage
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.types.internal.MessageUpdate
import eu.vendeli.tgbot.types.internal.UpdateType
import eu.vendeli.tgbot.types.keyboard.WebAppInfo

class StartController {
    @CommandHandler(["/start"])
    suspend fun start(bot: TelegramBot, user: User) {
        message("Hello").replyKeyboardMarkup {
            "test webapp" webApp WebAppInfo("https://vendeli.eu")
        }.send(user, bot)
    }

    @UpdateHandler([UpdateType.MESSAGE])
    suspend fun messageObserver(update: MessageUpdate, user: User, bot: TelegramBot) {
        sendMessage { "We've got information from webapp: ${update.message.webAppData?.data}" }.send(user, bot)
    }
}
