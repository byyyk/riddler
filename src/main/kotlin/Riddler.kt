import prisonerhats.PrisonerHatGenerator
import prisonerhats.PrisonerHatSimulator
import prisonerhats.PrisonerHatValidator
import prisonerhats.logic.xor.XorPrisonerLogic

fun main() {
    RiddleSimulator(PrisonerHatGenerator(), PrisonerHatSimulator { id: Int -> XorPrisonerLogic(id) }, PrisonerHatValidator()).simulate()
}