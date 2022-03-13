package prisonerhats.logic


import prisonerhats.HatColor
import prisonerhats.WornHats

interface PrisonerLogic {
    fun see(hatsInFrontOfMe: WornHats)
    fun hear(color: HatColor)
    fun speak(): HatColor
}