package prisonerhats.logic.xor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import prisonerhats.HatColor
import prisonerhats.WornHats
import prisonerhats.logic.PrisonerLogic

/**
 * white = true
 * black = false
 */
class XorPrisonerLogic(private val id: Int) : PrisonerLogic {
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(XorPrisonerLogic::class.java)
    }

    private val memory: MutableMap<Int, Boolean> = mutableMapOf()

    override fun see(hatsInFrontOfMe: WornHats) {
        LOG.info("Prisoner $id sees $hatsInFrontOfMe")
        val prisonerId = 1
        hatsInFrontOfMe.wornHatColors.forEach {
            memory[prisonerId] = it == HatColor.WHITE
        }
    }

    fun colorToBoolen(hatColor: HatColor): Boolean {
        return hatColor == HatColor.WHITE
    }

    fun booleanToColor(value: Boolean): HatColor {
        return if (value) {
            HatColor.WHITE
        } else {
            HatColor.BLACK
        }
    }

    override fun hear(color: HatColor) {
        LOG.info("Prisoner $id hears $color")
    }

    override fun speak(): HatColor {
        val booleanColorValue = if (id == 10) {
            if ((memory[1]!! xor memory[4]!! == memory[7]) xor ((memory[2]!! xor memory[5]!! == memory[8]))) {
                memory[9]!!
            } else {
                !memory[9]!!
            }
        } else {
            false //TODO
        }

        val color = booleanToColor(booleanColorValue)
        LOG.info("Prisoner $id speaks $color")
        return color
    }

}