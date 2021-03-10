package com.example.quiz

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var falseButton: Button? = null
    private var trueButton: Button? = null
    private var nextButton: ImageButton? = null
    private var prevButton: ImageButton? = null
    private var Image: ImageView? = null
    private var questionTextView: TextView? = null
    private var correct = 0

    private var currentQuestionIndex = 0
    private val questionBank = arrayOf(
        Question(R.string.a, true),
        Question(R.string.b, false),
        Question(R.string.c, true),
        Question(R.string.d, true),
        Question(R.string.e, true),
        Question(R.string.f, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.answer_text_view)
        Image = findViewById(R.id.myimage)
        //".also" doesn't work
        falseButton.setOnClickListener(this)
        trueButton.setOnClickListener(this)
        nextButton.setOnClickListener(this)
        prevButton.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onClick(v: View) {

        when (v.id) {
            R.id.false_button -> checkAnswer(false)
            R.id.true_button -> checkAnswer(true)
            R.id.next_button ->
                if (currentQuestionIndex < 7) {
                    currentQuestionIndex += 1
                    if (currentQuestionIndex == 6) {
                        questionTextView!!.text = getString(
                            R.string.correct, correct
                        )
                        nextButton!!.visibility = View.INVISIBLE
                        prevButton!!.visibility = View.INVISIBLE
                        trueButton!!.visibility = View.INVISIBLE
                        falseButton!!.visibility = View.INVISIBLE
                        if (correct > 3) questionTextView!!.text = ("CORRECTNESS IS " + correct
                                + " "
                                + "OUT OF 6")
                    } else {
                        updateQuestion()
                    }
                }
            R.id.prev_button -> if (currentQuestionIndex > 0) {
                currentQuestionIndex = ((currentQuestionIndex - 1)
                        % questionBank.size)
                updateQuestion()
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun updateQuestion() {
        Log.d(
            "Current",
            "onClick: $currentQuestionIndex"
        )
        questionTextView!!.setText(
            questionBank[currentQuestionIndex]
                .getAnswerResId()
        )
        when (currentQuestionIndex) {
            1 ->             // setting up image for each
                // question
                Image!!.setImageResource(R.drawable.f2)
            2 -> Image!!.setImageResource(R.drawable.f3)
            3 -> Image!!.setImageResource(R.drawable.f4)
            4 -> Image!!.setImageResource(R.drawable.f5)
            5 -> Image!!.setImageResource(R.drawable.f6)
            6 -> Image!!.setImageResource(R.drawable.f7)
            7 -> Image!!.setImageResource(R.drawable.f1)
        }
    }

    private fun checkAnswer(userChooseCorrect: Boolean) {
        val answerIsTrue = questionBank[currentQuestionIndex]
            .isAnswerTrue()
        val toastMessageId: Int
        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer
            correct++
        } else {
            toastMessageId = R.string.wrong_answer
        }
        Toast
            .makeText(
                this@MainActivity, toastMessageId,
                Toast.LENGTH_SHORT
            )
            .show()
    }
}