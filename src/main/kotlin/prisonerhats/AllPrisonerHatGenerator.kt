package prisonerhats

import ScenarioGenerator

class AllPrisonerHatGenerator : ScenarioGenerator<WornHats> {

    override fun generateAllScenarios(): List<WornHats> {
        val scenarios = mutableListOf<WornHats>()
        val base = mutableListOf<HatColor>()
        recursiveFill(base, scenarios)
        return scenarios
    }

    private fun recursiveFill(base: List<HatColor>, scenarios: MutableList<WornHats>) {
        if (base.size < 10) {
            recursiveFill(base.plus(HatColor.BLACK), scenarios)
            recursiveFill(base.plus(HatColor.WHITE), scenarios)
        } else {
            scenarios.add(WornHats(base))
        }
    }

}