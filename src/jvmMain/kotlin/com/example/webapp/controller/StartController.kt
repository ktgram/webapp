package com.example.webapp.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.annotations.UpdateHandler
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.types.internal.MessageUpdate
import eu.vendeli.tgbot.types.internal.UpdateType

class StartController {
    @CommandHandler(["/start"])
    suspend fun start(bot: TelegramBot, user: User) {
        message("Hello").replyKeyboardMarkup {
            "test webapp" webApp "URL_TO_YOUR_WEBAPP"
        }.send(user, bot)
    }

    @UpdateHandler([UpdateType.MESSAGE])
    suspend fun messageObserver(update: MessageUpdate, user: User, bot: TelegramBot) {
        update.message.webAppData?.also {
            message { "We've got information from webapp: ${it.data}" }.send(user, bot)
        }
    }
}
