package prisonerhats

import ScenarioGenerator

class PrisonerHatGenerator : ScenarioGenerator<WornHats> {
    override fun generateAllScenarios(): List<WornHats> {
        return listOf(
            WornHats(listOf(
                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, HatColor.BLACK,
                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, HatColor.WHITE
            )),
            WornHats(listOf(
                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, HatColor.WHITE, HatColor.WHITE,
                HatColor.WHITE, HatColor.BLACK, HatColor.WHITE, HatColor.BLACK, HatColor.WHITE
            )),
            WornHats(listOf(
                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, HatColor.WHITE, HatColor.WHITE,
                HatColor.WHITE, HatColor.WHITE, HatColor.WHITE, HatColor.BLACK, HatColor.WHITE
            ))
        )
    }

}