package edu.temple.diceroll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DiceViewModel : ViewModel() {
    private val _diceRollResult = MutableLiveData<Int>()
    val diceRollResult: LiveData<Int> = _diceRollResult

    fun rollDice(sides: Int) {
        _diceRollResult.value = Random.nextInt(sides) + 1
    }
}
