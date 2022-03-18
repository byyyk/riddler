package prisonerhats.logic.xor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import prisonerhats.HatColor
import prisonerhats.WornHats
import prisonerhats.logic.PrisonerLogic
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

/**
 * white = true
 * black = false
 */
class XorPrisonerLogic(private val id: Int) : PrisonerLogic {
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(XorPrisonerLogic::class.java)
    }

    private val memory: MutableMap<Int, Boolean> = mutableMapOf()
    private var heardPrisonerId = 10

    override fun see(hatsInFrontOfMe: WornHats) {
        LOG.info("Prisoner $id sees $hatsInFrontOfMe")
        var prisonerId = 1
        hatsInFrontOfMe.wornHatColors.forEach {
            memory[prisonerId] = it == HatColor.WHITE
            prisonerId++
        }
    }

    fun colorToBoolean(hatColor: HatColor): Boolean {
        return hatColor == HatColor.WHITE
    }

    private fun booleanToColor(value: Boolean): HatColor {
        return if (value) {
            HatColor.WHITE
        } else {
            HatColor.BLACK
        }
    }

    override fun hear(color: HatColor) {
        LOG.info("Prisoner $id hears $color")
        memory[heardPrisonerId]= colorToBoolean(color)
        heardPrisonerId--
    }

    private fun hatOf(prisonerId: Int): Boolean {
        LOG.debug("Looking up hat of prisoner $prisonerId")
        return memory[prisonerId]
            ?: throw IllegalArgumentException("Prisoner $id does not have knowledge of prisoner $prisonerId hat color yet!")
    }

    override fun speak(): HatColor {
        var booleanColorValue: Boolean = when {
            (id == 10) -> {
                // based on 1 4 7 2 5 8 gives information whether 3 xor 6 is equal to 9
                if ((hatOf(1) xor hatOf(4) == hatOf(7)) xor ((hatOf(2) xor hatOf(5) == hatOf(8)))) {
                    hatOf(3) xor hatOf(6) == hatOf(9)
                } else {
                    hatOf(3) xor hatOf(6) != hatOf(9)
                }
            }
            (id == 9) -> {
                if ((hatOf(1) xor hatOf(4) == hatOf(7)) xor ((hatOf(2) xor hatOf(5) == hatOf(8)))) {
                    if (hatOf(10)) {
                        hatOf(3) xor hatOf(6)
                    } else {
                        !(hatOf(3) xor hatOf(6))
                    }
                } else {
                    if (hatOf(10)) {
                        !(hatOf(3) xor hatOf(6))
                    } else {
                        (hatOf(3) xor hatOf(6))
                    }
                }
            }
            (id == 8) -> {
                // hatOf(10) == (hatOf(9) == hatOf(3) xor hatOf(6)) means that this was true: if ((hatOf(1) xor hatOf(4) == hatOf(7)) xor ((hatOf(2) xor hatOf(5) == hatOf(8)))) {
                // this condition check translates to if 10 said true (white) then 9 must be equal to 3 xor 6
                if (hatOf(10) == (hatOf(9) == hatOf(3) xor hatOf(6))) {
                    // assuming ((hatOf(1) xor hatOf(4) == hatOf(7)) xor ((hatOf(2) xor hatOf(5) == hatOf(8)))) was true
                    if ((hatOf(1) xor hatOf(4) == hatOf(7))) { // so if the first part is true
                        // the second must be false
                        !(hatOf(2) xor hatOf(5))
                    } else {
                        // otherwise, it's true
                        (hatOf(2) xor hatOf(5))
                    }
                } else {
                    // assuming ((hatOf(1) xor hatOf(4) == hatOf(7)) xor ((hatOf(2) xor hatOf(5) == hatOf(8)))) was false
                    if ((hatOf(1) xor hatOf(4) == hatOf(7))) { // so if the first part is true
                        // the second must be also true
                        (hatOf(2) xor hatOf(5))
                    } else {
                        // otherwise, it must be false
                        !(hatOf(2) xor hatOf(5))
                    }
                }
            }
            (id == 7) -> {
                //this can be "simplified" (shortened? :))

                // same as in 8
                // hatOf(10) == (hatOf(9) == hatOf(3) xor hatOf(6)) can also be simplified to this:
                //
                //    hatOf(10) == getLogicalHatGroupFor(9).hatColorsMatchLogicalEquation()
                //
                if (hatOf(10) == getLogicalHatGroupFor(9).hatColorsMatchLogicalEquation()) {
                    // assuming ((hatOf(1) xor hatOf(4) == hatOf(7)) xor ((hatOf(2) xor hatOf(5) == hatOf(8)))) was true
                    // this is the same as the following:
                    //
                    //     getLogicalHatGroupFor(7).hatColorsMatchLogicalEquation() xor getLogicalHatGroupFor(8).hatColorsMatchLogicalEquation()
                    //
                    if (getLogicalHatGroupFor(8).hatColorsMatchLogicalEquation()) { // so if the second part is true (based on heard answer of 8th prisoner and seen hats)
                        // the first must be false
                        getLogicalHatGroupFor(id).fillMissingHatToBreakLogicalEquation()
                    } else {
                        // otherwise, it's true
                        getLogicalHatGroupFor(id).fillMissingHatToMatchLogicalEquation()
                    }
                } else {
                    // assuming
                    //     getLogicalHatGroupFor(7).hatColorsMatchLogicalEquation() xor getLogicalHatGroupFor(8).hatColorsMatchLogicalEquation()
                    // is false
                    if (getLogicalHatGroupFor(8).hatColorsMatchLogicalEquation()) { // so if the second part is true (based on heard answer of 8th prisoner and seen hats)
                        // the first must be also true
                        getLogicalHatGroupFor(id).fillMissingHatToMatchLogicalEquation()
                    } else {
                        // otherwise, it's true
                        getLogicalHatGroupFor(id).fillMissingHatToBreakLogicalEquation()
                    }
                }
                // the above can also be simplified as the two branches of if statement just return negated value
            }
            (listOf(3, 6).contains(id)) -> {
                // 6 sees what is the result of 1 xor 4 and heard what 7 said so he knows whether he wanted to break the equation
                // 6 sees what is the result of 2 xor 5 and heard what 8 said so he knows whether he wanted to break the equation

                if (getLogicalHatGroupFor(7).hatColorsMatchLogicalEquation() xor getLogicalHatGroupFor(8).hatColorsMatchLogicalEquation()) {
                    // hatOf(10) == true means that getLogicalHatGroupFor(9).hatColorsMatchLogicalEquation())
                    if (hatOf(10)) {
                        getLogicalHatGroupFor(id).fillMissingHatToMatchLogicalEquation()
                    } else {
                        getLogicalHatGroupFor(id).fillMissingHatToBreakLogicalEquation()
                    }
                } else {
                    // hatOf(10) == true means that !getLogicalHatGroupFor(9).hatColorsMatchLogicalEquation())
                    if (hatOf(10)) {
                        getLogicalHatGroupFor(id).fillMissingHatToBreakLogicalEquation()
                    } else {
                        getLogicalHatGroupFor(id).fillMissingHatToMatchLogicalEquation()
                    }
                }
            }
            (listOf(2, 5).contains(id)) -> {
                val logicalGroup = getLogicalHatGroupFor(8)
                val logicalGroupShouldMatchEquation =
                    if (hatOf(10) == getLogicalHatGroupFor(9).hatColorsMatchLogicalEquation()) {
                        // assuming ((hatOf(1) xor hatOf(4) == hatOf(7)) xor ((hatOf(2) xor hatOf(5) == hatOf(8)))) was true
                        // group of 7 should match logical equation only if 8 doesnt and should not match if 8 does
                        !getLogicalHatGroupFor(7).hatColorsMatchLogicalEquation()
                    } else {
                        getLogicalHatGroupFor(7).hatColorsMatchLogicalEquation()
                    }

                if (logicalGroupShouldMatchEquation) {
                    logicalGroup.fillMissingHatToMatchLogicalEquation()
                } else {
                    logicalGroup.fillMissingHatToBreakLogicalEquation()
                }
            }
            (listOf(1, 4).contains(id)) -> {
                val logicalGroup = getLogicalHatGroupFor(7)
                val logicalGroupShouldMatchEquation =
                if (hatOf(10) == getLogicalHatGroupFor(9).hatColorsMatchLogicalEquation()) {
                    // assuming ((hatOf(1) xor hatOf(4) == hatOf(7)) xor ((hatOf(2) xor hatOf(5) == hatOf(8)))) was true
                    // group of 7 should match logical equation only if 8 doesnt and should not match if 8 does
                    !getLogicalHatGroupFor(8).hatColorsMatchLogicalEquation()
                } else {
                    getLogicalHatGroupFor(8).hatColorsMatchLogicalEquation()
                }

                if (logicalGroupShouldMatchEquation) {
                    logicalGroup.fillMissingHatToMatchLogicalEquation()
                } else {
                    logicalGroup.fillMissingHatToBreakLogicalEquation()
                }
            }
            else -> { throw IllegalStateException("Prisoner ID $id out of bounds (1-10)") }
        }

        val color = booleanToColor(booleanColorValue)
        LOG.info("Prisoner $id speaks $color")
        return color
    }

    fun getLogicalHatGroupFor(prisonerId: Int): LogicalHatGroup {
        val prisonerIdsInGroup = listOf(prisonerId - 6, prisonerId - 3, prisonerId, prisonerId + 3, prisonerId + 6).filter { it in 1..9 }
        if (prisonerIdsInGroup.count() != 3) {
            throw IllegalStateException("Error during logical group prisoner IDs resolution")
        }
        return LogicalHatGroup(memory[prisonerIdsInGroup[0]], memory[prisonerIdsInGroup[1]], memory[prisonerIdsInGroup[2]])
    }

    class LogicalHatGroup(private var xorLeft: Boolean?, private var xorRight: Boolean?, private var result: Boolean?) {
        /**
         * fills the missing link so that the equation is true
         */

        fun hatColorsMatchLogicalEquation(): Boolean {
            return xorLeft!! xor xorRight!! == result!!
        }

        /**
         * fills the missing operand so that the equation is true and returns the filled value
         */
        fun fillMissingHatToMatchLogicalEquation(): Boolean {
            if (xorLeft == null) {
                if (result!!) {
                    xorLeft = !xorRight!!
                } else {
                    xorLeft = xorRight!!
                }
                return xorLeft!!
            }
            if (xorRight == null) {
                xorRight = if (result!!) {
                    !xorLeft!!
                } else {
                    xorLeft!!
                }
                return xorRight!!
            }
            if (result == null) {
                result = xorLeft!! xor xorRight!!
                return result!!
            }

            throw IllegalStateException("None of the operands have missing value")
        }

        /**
         * fills the missing operand so that the equation is false and returns the filled value
         */
        fun fillMissingHatToBreakLogicalEquation(): Boolean {
            if (xorLeft == null) {
                xorLeft = if (result!!) {
                    xorRight!!
                } else {
                    !xorRight!!
                }
                return xorLeft!!
            }
            if (xorRight == null) {
                xorRight = if (result!!) {
                    xorLeft!!
                } else {
                    !xorLeft!!
                }
                return xorRight!!
            }
            if (result == null) {
                result = !(xorLeft!! xor xorRight!!)
                return result!!
            }

            throw IllegalStateException("None of the operands have missing value")
        }
    }
}