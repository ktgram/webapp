import eu.vendeli.ktgram.extutils.checkIsInitDataSafe
import eu.vendeli.webapps.button.setParams
import eu.vendeli.webapps.core.webApp
import eu.vendeli.webapps.utils.onMainButtonClicked
import kotlinx.browser.document

fun main() {
    webApp.expand()
    webApp.mainButton.setParams {
        text = "Main text"
        textColor = "#F55353"
        color = "#143F6B"
    }

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
            webApp.mainButton.setParams { color = "#E0FFFF" }
            webApp.mainButton.disable()
        } else {
            webApp.mainButton.setParams { color = "#143F6B" }
            webApp.mainButton.enable()
        }
    })

    webApp.onMainButtonClicked {
        webApp.sendData("some string that we need to send")
    }

    val userCard = document.getElementById("usercard")

    if (webApp.initData.checkIsInitDataSafe("BOT_TOKEN", webApp.initDataUnsafe.hash)) {
        console.warn("Data is unsafe")
    }

    val profName = document.createElement("p")
    profName.textContent = "${webApp.initDataUnsafe.user?.firstName}\n" +
            "${webApp.initDataUnsafe.user?.lastName}\n" +
            "${webApp.initDataUnsafe.user?.username} (${webApp.initDataUnsafe.user?.languageCode})\n"

    userCard?.appendChild(profName)

    val userid = document.createElement("p")
    userid.textContent = "${webApp.initDataUnsafe.user?.id}"
    userCard?.appendChild(userid)
}