package com.example.webapp

import com.example.webapp.app.webAppPage
import eu.vendeli.ktor.starter.serveWebhook
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    serveWebhook {
        server {
            PEM_PRIVATE_KEY_PATH =
                "/etc/letsencrypt/live/example.com/privkey.pem" // The path to the PEM private key file.
            PEM_CHAIN_PATH =
                "/etc/letsencrypt/live/example.com/fullchain.pem"  // The path to the PEM certificate chain file.
            PEM_PRIVATE_KEY = "pem_changeit".toCharArray() // The PEM private key PASSWORD.

            KEYSTORE_PATH = "/etc/ssl/certs/java/cacerts/bot_keystore.jks" // The path to the Java KeyStore file.
            KEYSTORE_PASSWORD = "changeit".toCharArray() // The password for the KeyStore.

            // If pem certificates are present, the module itself will create a jks storage from them at the specified path.
        }

        declareBot {
            token = "BOT_TOKEN"
             configuration {
                 identifier = "BotName"
             }
        }

        ktorModule {
            routing {
                staticResources("/", "static")
                get("/") {
                    call.respondText(webAppPage, ContentType.Text.Html, HttpStatusCode.OK)
                }
            }
        }
    }
}
