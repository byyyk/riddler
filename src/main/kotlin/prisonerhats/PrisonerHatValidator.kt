package prisonerhats

import ScenarioOutcomeValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PrisonerHatValidator : ScenarioOutcomeValidator<WornHats, SpokenHats> {
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ScenarioOutcomeValidator::class.java)
    }

    override fun validate(scenario: WornHats, scenarioOutcome: SpokenHats): Boolean {
        val result = scenario.wornHatColors.subList(0, 9) == scenarioOutcome.spokenHatColors.subList(1, 10).reversed()
        if (!result) {
            LOG.error(
                """
                | Scenario failed!
                |       worn:             ${scenario.wornHatColors}
                |       spoken(reversed): ${scenarioOutcome.spokenHatColors.reversed()}""".trimMargin()
            )
        } else {
//            LOG.debug(
//                """
//                | Scenario passed!
//                |       worn:             ${scenario.wornHatColors}
//                |       spoken(reversed): ${scenarioOutcome.spokenHatColors.reversed()}""".trimMargin()
//            )
        }
        return result
    }
}