package prisonerhats.logic.blindndeaf

import prisonerhats.HatColor
import prisonerhats.WornHats
import prisonerhats.logic.PrisonerLogic

/**
 * This is obviously wrong, just a "stub" to test other parts of application
 */
class BlindAndDeafPrisonerLogic : PrisonerLogic {
    override fun see(hatsInFrontOfMe: WornHats) {
        // i see darkness...
    }

    override fun hear(color: HatColor) {
        // whaaa?
    }

    override fun speak(): HatColor {
        return HatColor.BLACK
    }

}