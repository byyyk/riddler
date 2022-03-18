package prisonerhats

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PrisonerHatValidatorTest {
    @Test
    fun test() {
        val validator = PrisonerHatValidator()
        assertEquals(true, validator.validate(
            WornHats(listOf(HatColor.BLACK)),
            SpokenHats(mutableListOf(HatColor.BLACK))
        ))
        assertEquals(false, validator.validate(
            WornHats(listOf(HatColor.BLACK, HatColor.WHITE)),
            SpokenHats(mutableListOf(HatColor.BLACK, HatColor.WHITE))
        ))
        assertEquals(true, validator.validate(
            WornHats(listOf(HatColor.BLACK, HatColor.WHITE)),
            SpokenHats(mutableListOf(HatColor.WHITE, HatColor.BLACK))
        ))
        assertEquals(false, validator.validate(
            WornHats(listOf(HatColor.BLACK)),
            SpokenHats(mutableListOf(HatColor.WHITE))
        ))
        assertEquals(false, validator.validate(
            WornHats(listOf(HatColor.BLACK)),
            SpokenHats(mutableListOf(HatColor.BLACK, HatColor.BLACK))
        ))
    }
}