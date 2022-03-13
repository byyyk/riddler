package prisonerhats

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PrisonerHatValidatorTest {
    @Test
    fun test() {
        val validator = PrisonerHatValidator()
        assertEquals(true, validator.validate(
            WornHats(listOf(HatColor.BLACK)),
            SpokenHats(listOf(HatColor.BLACK))
        ))
        assertEquals(false, validator.validate(
            WornHats(listOf(HatColor.BLACK, HatColor.WHITE)),
            SpokenHats(listOf(HatColor.BLACK, HatColor.WHITE))
        ))
        assertEquals(true, validator.validate(
            WornHats(listOf(HatColor.BLACK, HatColor.WHITE)),
            SpokenHats(listOf(HatColor.WHITE, HatColor.BLACK))
        ))
        assertEquals(false, validator.validate(
            WornHats(listOf(HatColor.BLACK)),
            SpokenHats(listOf(HatColor.WHITE))
        ))
        assertEquals(false, validator.validate(
            WornHats(listOf(HatColor.BLACK)),
            SpokenHats(listOf(HatColor.BLACK, HatColor.BLACK))
        ))
    }
}