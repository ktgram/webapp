package com.example.webapp.app

import kotlinx.html.*
import kotlinx.html.stream.createHTML

val webAppPage = createHTML().html {
    head {
        meta(charset = "UTF-8")
        meta(content = "IE=edge") { httpEquiv = "X-UA-Compatible" }
        meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
        title("Test Telegram WebApps API")

        style {
            unsafe {
                raw("""
                        body{
                            color: var(--tg-theme-text-color);
                            background: var(--tg-theme-bg-color);
                            display: flex;
                            flex-direction: column;
                            align-items: center;
                            font-size: 18px;
                        }

                        .hint{
                            color: var(--tg-theme-hint-color);
                        }

                        .link{
                            color: var(--tg-theme-link-color);
                        }

                        .button{
                            background: var(--tg-theme-button-color);
                            color: var(--tg-theme-button-text-color);
                            border: none;
                            font-size: 18px;
                        }

                        .button:not(:last-child){
                            margin-bottom: 20px
                        }

                        #usercard{
                            text-align: center;
                        }
                    """.trimIndent())
            }
        }

        script(src = "https://telegram.org/js/telegram-web-app.js") {}
    }

    body {
        div { id = "usercard" }

        p("Just text")

        a(classes = "link", href = "https://vendeli.eu") {
            +"Link"
        }

        p(classes = "hint") {
            +"Some little hint"
        }

        button(classes = "button") {
            id = "btn"
            +"Show/Hide Main Button"
        }

        button(classes = "button") {
            id = "btnED"
            +"Enable/Disable Main Button"
        }

        script(type = "application/javascript", src = "webapp.js") {}
    }
}
