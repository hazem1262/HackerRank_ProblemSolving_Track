package strings


import java.util.*
import kotlin.collections.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.text.*

// Complete the sherlockAndAnagrams function below.
fun sherlockAndAnagrams(s: String): Int {
    val availableCombinations: ArrayList<String> = getAvailableCombinations(s)
    return getAnagramsCombinations(availableCombinations)
}

fun getAvailableCombinations(originalString: String): ArrayList<String> {
    val combinations = arrayListOf<String>()
    originalString.forEachIndexed { index, _ ->
        var addedCombination = ""
        for (i in index until (originalString.length )){
            addedCombination = "${addedCombination}${originalString[i]}"
            combinations.add(addedCombination)
        }
    }
    return combinations
}
fun getAnagramsCombinations(combinations: ArrayList<String>):Int{
    return if (combinations.size == 1){
        0
    }else{
        val uniqueCombinations:MutableSet<String>     = mutableSetOf()
        val repeatableCombinations:ArrayList<String> = arrayListOf()
        combinations.forEach {
            if (uniqueCombinations.checkAnagrams(it)){
                repeatableCombinations.add(it)
            }else{
                uniqueCombinations.add(it)
            }
        }
        if (uniqueCombinations.size == 0){
            repeatableCombinations.size
        }else{
            repeatableCombinations.size + getAnagramsCombinations(repeatableCombinations)
        }
    }
}

private fun MutableSet<String>.checkAnagrams(combination: String): Boolean {
    this.forEach {
        if (it == combination){
            return true
        }
        if (it.length == combination.length ){
            val array1 = it.toCharArray()
            array1.sort()
            val array2 = combination.toCharArray()
            array2.sort()
            if (array1.contentEquals(array2)){
                return true
            }
        }
    }
    return false
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val q = scan.nextLine().trim().toInt()

    for (qItr in 1..q) {
        val s = scan.nextLine()

        val result = sherlockAndAnagrams(s)

        println(result)
    }
}
