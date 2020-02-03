package edu.ccsu.cs560.hw1_numbergussinggame

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var hiddenNumber = 0
    var inputEditable = true;
    var guesses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNewHiddenNumber()

        findViewById<EditText>(R.id.numberBox).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    checkGuess()
                    true
                }
                else -> false
            }
        }
    }

    fun editHint(message: String) {
        hint.text = message
    }

    fun checkGuess() {
        var guess = numberBox.text.toString().toInt()
        if (guess == hiddenNumber) {
            winner()
        } else {
            guessAgain(guess)
        }
    }

    fun guessAgain(guess: Int) {
        updateNumberOfTries(++guesses)
        editHint(getHintText(guess))
    }
    fun updateNumberOfTries(tries: Int) {
        numberTries.text = "Number of Tries: $tries"
    }

    fun getHintText(currentGuess: Int): String {
        if (currentGuess > hiddenNumber) return "Lower" else return "Higher"
    }

    fun winner() {
        editHint("Winner!!")
        numberBox.visibility = View.INVISIBLE
    }

    fun playAgain(view: View) {
        getNewHiddenNumber()
        guesses = 0
        updateNumberOfTries(guesses)
        editHint("")
        numberBox.visibility = View.VISIBLE
    }

    fun getNewHiddenNumber() {
        hiddenNumber = Random.nextInt(100) + 1
    }
}
