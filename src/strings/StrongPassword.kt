package strings

import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
val numbersRegex = ".*[0-9].*".toRegex()
val lowerCaseRegex = ".*[a-z].*".toRegex()
val upperCaseRegex = ".*[A-Z].*".toRegex()
val specialCharactersRegex = ".*[!@#$%^&*()-+=|<>?{}\\[\\]~-].*".toRegex()
// Complete the minimumNumber function below.
fun minimumNumber(n: Int, password: String): Int {
    // Return the minimum number of characters to make the password strong
    var missedConditions = 0
    val verifyLength = password.length >= 6
    val veryIfHaveNumber = password.matches(numbersRegex)
    if (!veryIfHaveNumber) missedConditions++
    val veryIfHaveLowerCase = password.contains(lowerCaseRegex)
    if (!veryIfHaveLowerCase) missedConditions++
    val veryIfHaveUpperCase = password.contains(upperCaseRegex)
    if (!veryIfHaveUpperCase) missedConditions++
    val veryIfHaveSpecialCharacters = password.contains(specialCharactersRegex)
    if (!veryIfHaveSpecialCharacters) missedConditions++
    return if (verifyLength){
        missedConditions
    }else{
        val remainingLength = 6 - password.length
        return if (remainingLength > missedConditions) remainingLength else missedConditions
    }
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val password = scan.nextLine()

    val answer = minimumNumber(n, password)

    println(answer)
}
