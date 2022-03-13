package prisonerhats;

import ScenarioSimulator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import prisonerhats.logic.PrisonerLogic

class PrisonerHatSimulator(private val prisonerLogicFactory: (id: Int) -> PrisonerLogic) : ScenarioSimulator<WornHats, SpokenHats> {
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(PrisonerHatSimulator::class.java)
    }
    override fun simulate(scenario: WornHats): SpokenHats {
        val prisoners: MutableList<PrisonerLogic> = mutableListOf()
        val lastPrisonerIndex = scenario.wornHatColors.size - 1
        for (i in 0 .. lastPrisonerIndex) {
            val prisoner = prisonerLogicFactory.invoke(i + 1)
            prisoners.add(prisoner)
        }

        for (i in 1 .. lastPrisonerIndex) {
            val seenHats = WornHats(scenario.wornHatColors.subList(0, i))
            prisoners[i].see(seenHats)
        }

        val spokenHats = SpokenHats(mutableListOf())
        for (i in lastPrisonerIndex downTo 0) {
            val spokenColor = prisoners[i].speak()
            prisoners.forEach { it.hear(spokenColor) }
            spokenHats.spokenHatColors += spokenColor
        }

        return spokenHats
    }
}
