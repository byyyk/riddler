package prisonerhats

import ScenarioGenerator

class TestPrisonerHatGenerator : ScenarioGenerator<WornHats> {
    override fun generateAllScenarios(): List<WornHats> {
        return listOf(
            WornHats(listOf(
                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK,
                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK,
                HatColor.WHITE, HatColor.WHITE, HatColor.BLACK,
                //not matching!   not matching!       matching!
                // 7 xor 8 = false
                HatColor.BLACK))
//            WornHats(listOf(
//                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, // matching logical equation
//                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, // matching logical equation
//                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, // 7 xor 8 = false // 3 xor 6 == 9
//                HatColor.BLACK
//            // 10 should say
//            )),
//            WornHats(listOf(
//                HatColor.BLACK, HatColor.WHITE, HatColor.WHITE,
//                HatColor.BLACK, HatColor.WHITE, HatColor.WHITE,
//                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK,
//                HatColor.BLACK
//            )),
//            WornHats(listOf(
//                HatColor.BLACK, HatColor.WHITE, HatColor.WHITE,
//                HatColor.BLACK, HatColor.BLACK, HatColor.WHITE,
//                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK,
//                HatColor.BLACK
//            )),
//            WornHats(listOf(
//                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, HatColor.WHITE, HatColor.WHITE,
//                HatColor.WHITE, HatColor.BLACK, HatColor.WHITE, HatColor.BLACK, HatColor.WHITE
//            )),
//            WornHats(listOf(
//                HatColor.BLACK, HatColor.BLACK, HatColor.BLACK, HatColor.WHITE, HatColor.WHITE,
//                HatColor.WHITE, HatColor.WHITE, HatColor.WHITE, HatColor.BLACK, HatColor.WHITE
//            ))
        )
    }

}