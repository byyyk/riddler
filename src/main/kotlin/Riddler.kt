import prisonerhats.AllPrisonerHatGenerator
import prisonerhats.TestPrisonerHatGenerator
import prisonerhats.PrisonerHatSimulator
import prisonerhats.PrisonerHatValidator
import prisonerhats.logic.xor.XorPrisonerLogic

fun main() {
//    RiddleSimulator(AllPrisonerHatGenerator(), PrisonerHatSimulator { id: Int -> XorPrisonerLogic(id) }, PrisonerHatValidator()).simulate()
    RiddleSimulator(TestPrisonerHatGenerator(), PrisonerHatSimulator { id: Int -> XorPrisonerLogic(id) }, PrisonerHatValidator()).simulate()
}