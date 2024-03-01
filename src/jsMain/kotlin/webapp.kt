
import eu.vendeli.webapps.core.webApp
import eu.vendeli.webapps.utils.onMainButtonClicked
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.stream.createHTML

fun main() {
    val html = createHTML().html {
        head {
            meta {
                charset = "UTF-8"
                attributes.put("http-equiv", "X-UA-Compatible")
                content = "IE=edge"
            }
            meta {
                name = "viewport"
                content = "width=device-width, initial-scale=1.0"
            }
            title("Test Telegram WebApps API")
            script {
                src = "https://telegram.org/js/telegram-web-app.js"
            }
            style {
                unsafe {
                    +"""
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
                    """
                }
            }
        }
        body {
            div {
                id = "usercard"
            }
            p("Just text")
            a(href = "https://vendeli.eu", classes = "link") {
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
            script {
                webApp.expand()
//                webApp.mainButton.setParams {
//                    text = "Main text"
//                    textColor = "#F55353"
//                    color = "#143F6B"
//                }

                val btn = document.getElementById("btn")

                btn?.addEventListener("click", {
                    if (webApp.mainButton.isVisible) {
                        webApp.mainButton.hide()
                    } else {
                        webApp.mainButton.show()
                    }
                })

                val btnED = document.getElementById("btnED")
                btnED?.addEventListener("click", {
                    if (webApp.mainButton.isActive) {
//                        webApp.mainButton.setParams { color = "#E0FFFF" }
                        webApp.mainButton.disable()
                    } else {
//                        webApp.mainButton.setParams { color = "#143F6B" }
                        webApp.mainButton.enable()
                    }
                })

                webApp.onMainButtonClicked {
                    webApp.sendData("some string that we need to send")
                }

                val userCard = document.getElementById("usercard")

                val profName = document.createElement("p")
                profName.textContent = "${webApp.initDataUnsafe.user?.firstName}\n" +
                        "${webApp.initDataUnsafe.user?.lastName}\n" +
                        "${webApp.initDataUnsafe.user?.username} (${webApp.initDataUnsafe.user?.languageCode})\n"

                userCard?.appendChild(profName)

                val userid = document.createElement("p")
                userid.textContent = "${webApp.initDataUnsafe.user?.id}"
                userCard?.appendChild(userid)
            }
        }
    }

    window.onload = { document.append(html) }
}