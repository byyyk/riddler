import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RiddleSimulator<T, V>(
    private val generator: ScenarioGenerator<T>,
    private val simulator: ScenarioSimulator<T, V>,
    private val validator: ScenarioOutcomeValidator<T, V>
) {
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ScenarioOutcomeValidator::class.java)
    }

    fun simulate() {
        val scenarios = generator.generateAllScenarios()
        val failedScenarios = scenarios
            .map { Pair(it, simulator.simulate(it)) }
            .filter { !validator.validate(it.first, it.second) }

        if (failedScenarios.isNotEmpty()) {
            LOG.error("${failedScenarios.count()}/${scenarios.count()} scenarios failed!")
//            failedScenarios.forEach {LOG.error("""
//                |
//                |       ${it.first}
//                |   ${it.second}""".trimMargin())}
        } else {
            LOG.info("All ${scenarios.count()} scenarios passed!")
        }
    }
}
