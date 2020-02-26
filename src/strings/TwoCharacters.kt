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
import kotlin.math.max
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

// Complete the alternate function below.
fun alternate(s: String): Int {
    // 1- get the prime characters
    val primeCharacters:Set<Char> = s.toSet()
    // 2- get the available combinations
    val availableCombinations:MutableSet<CharactersCombination> = mutableSetOf()
    primeCharacters.forEachIndexed { index, char ->
        for (i in index until (primeCharacters.size - 1)){
            if (i != primeCharacters.size - 1)
            availableCombinations.add(CharactersCombination(char, primeCharacters.elementAt(i + 1)))
        }
    }
    availableCombinations.forEach {
        it.calcAlternatingCharactersLength(s)
    }
    val alternatingCharactersCombinations = availableCombinations.map { it.alternatingCharactersLength }
    // handle if only 1 char is input
    return if (alternatingCharactersCombinations.isEmpty()) 0 else availableCombinations.map { it.alternatingCharactersLength }.reduce { acc, i -> max(acc, i) }
}
data class CharactersCombination(val char1:Char, val char2:Char, var alternatingCharactersLength:Int = 0){
    fun calcAlternatingCharactersLength(s:String){
        val withOutOtherCharacters = s.filter { it == char1 || it == char2 }
        val isValid = checkIfValidCombination(withOutOtherCharacters)
        alternatingCharactersLength = if (isValid){
            withOutOtherCharacters.length
        }else{
            0
        }
    }

    private fun checkIfValidCombination(withOutOtherCharacters: String) : Boolean {
        withOutOtherCharacters.forEachIndexed {
                index, c ->
            if (index != withOutOtherCharacters.length - 1 && (withOutOtherCharacters[index] == withOutOtherCharacters[index+1])){
                return false
            }
        }
        return true
    }
}

fun main(args: Array<String>) {
    val l = readLine()!!.trim().toInt()

    val s = readLine()!!

    val result = alternate(s)

    println(result)
}
