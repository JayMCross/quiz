package com.example.quiz

class Question(c: Int, b: Boolean) {
    // answerResId will store question
    private var answerResId = 0

    // answerTrue will store correct answer
    // of the question provided
    private var answerTrue = false

    fun Question(answerResId: Int, answerTrue: Boolean) {
        // setting the values through
        // arguments passed in constructor
        this.answerResId = answerResId
        this.answerTrue = answerTrue
    }

    // returning the question passed
    fun getAnswerResId(): Int {
        return answerResId
    }

    // setting the question passed
    fun setAnswerResId(answerResId: Int) {
        this.answerResId = answerResId
    }

    // returning the correct answer
    // of question
    fun isAnswerTrue(): Boolean {
        return answerTrue
    }

    // setting the correct
    // ans of question
    fun setAnswerTrue(answerTrue: Boolean) {
        this.answerTrue = answerTrue
    }
}