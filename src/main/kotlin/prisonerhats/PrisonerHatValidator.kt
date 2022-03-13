package prisonerhats

import ScenarioOutcomeValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PrisonerHatValidator : ScenarioOutcomeValidator<WornHats, SpokenHats> {
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ScenarioOutcomeValidator::class.java)
    }

    override fun validate(scenario: WornHats, scenarioOutcome: SpokenHats): Boolean {
        return scenario.wornHatColors.reversed() == scenarioOutcome.spokenHatColors
    }
}