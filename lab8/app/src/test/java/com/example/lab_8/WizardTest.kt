package com.example.lab_8


import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WizardTest {
    private lateinit var evilWizard: Wizard

    // Set up a new wizard before every single test
    @Before
    fun setUp() {
        evilWizard = Wizard("Evil Wizard", 30, 10)
    }

    @Test
    fun castSpell_explosion_successful() {
        val damageDealt = evilWizard.castSpell("Explosion")

        assertEquals(30, damageDealt) // spellPower of 10 * 3 = 30

        assertEquals(20, evilWizard.mana) // mana of 30 - 10 = 20
    }

    @Test
    fun castSpell_explosion_unsuccessful() {
        evilWizard.mana = 5 // Evil Wizard does not have enough mana to cast "Explosion"
        val damageDealt = evilWizard.castSpell("Explosion")

        assertEquals(0, damageDealt)

        assertEquals(5, evilWizard.mana) // mana still is 5
    }

    @Test
    fun restoreMana_amountAddedExceedsOneHundred_manaSetToOneHundred() {
        evilWizard.restoreMana(80) // 30 + 80 = 110, so mana should be set to 100

        assertEquals(100, evilWizard.mana)
    }

    @Test
    fun restoreMana_amountAddedDoesNotExceedOneHundred_manaIncreasesCorrectly() {
        evilWizard.restoreMana(40) // 30 + 40 = 70, so mana should be set to 70

        assertEquals(70, evilWizard.mana)
    }

    @Test
    fun train_enoughMana_successful() {
        val startPower = evilWizard.spellPower
        evilWizard.train() // -5 mana

        assertEquals(startPower + 1, evilWizard.spellPower) // checks if went up by 1

        assertEquals(25, evilWizard.mana) // was 30 should be 25 now
    }

    @Test
    fun train_notEnoughMana_unsuccessful() { // Nothing should happen or change
        evilWizard.mana = 4
        val startPower = evilWizard.spellPower
        evilWizard.train() // 4 < 5 so nothing should happen

        assertEquals(startPower, evilWizard.spellPower) // make sure startPower didn't change

        assertEquals(4, evilWizard.mana) // make sure it's 4
    }
}