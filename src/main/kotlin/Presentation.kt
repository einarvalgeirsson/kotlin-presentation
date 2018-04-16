import interop.RegistryBackup
import interop.PersonRegistry
import java.util.*

/**
 * /$$   /$$  /$$$$$$  /$$$$$$$$ /$$       /$$$$$$ /$$   /$$
| $$  /$$/ /$$__  $$|__  $$__/| $$      |_  $$_/| $$$ | $$
| $$ /$$/ | $$  \ $$   | $$   | $$        | $$  | $$$$| $$
| $$$$$/  | $$  | $$   | $$   | $$        | $$  | $$ $$ $$
| $$  $$  | $$  | $$   | $$   | $$        | $$  | $$  $$$$
| $$\  $$ | $$  | $$   | $$   | $$        | $$  | $$\  $$$
| $$ \  $$|  $$$$$$/   | $$   | $$$$$$$$ /$$$$$$| $$ \  $$
|__/  \__/ \______/    |__/   |________/|______/|__/  \__/
 **/





















/**
 *
 *
 *
 *
 *  PROPERTIES
 *
 *
 *
 *
 *
 **/





/**
 *
 *
 * Val / Var
 * Immutable / Mutable
 *
 * - public by default
 * - val generates getter
 * - var generates getter and setter
 *
 **/

class ValVar {
    val immutable = "can't be re-assigning this one"

    var mutable = "go ahead"

    init {
        immutable = "tryin!"
        mutable = "Ok!"
    }
}


































/**
 *
 *
 *
 *
 *  CLASSES
 *
 *
 *
 *
 *
 **/














/**
 * Basic Class
 **/
class User {
    // ....
}

































/**
 * Class with primary constructor
 **/
class UserWithName(name: String) {
    // ...
}



























/**
 *
 * The primary constructor cannot contain any code.
 * Initialization code can be placed in initializer blocks,
 * which are prefixed with the init keyword:
 *
 */
class Customer(name: String) {
    init {
        System.out.println("Customer initialized with value $name")
    }
}



































/**
 * Properties can be declared and initialized in the primary constructor
 * - getters and/or setters will be generated
 **/
class Employee(val vacationDays: Int) {

}























/**
 * Secondary constructors
 **/
class Store(customers: List<Customer>) {
    constructor(customers: List<Customer>, open: Boolean): this(customers) {
        // Do something
    }
    constructor(customers: List<Customer>, open: Boolean, name: String): this(customers) {
        // Do something else
    }
}

















/**
 * Classes are final by default.
 **/
open class NonFinalClass {
    open fun overrideMePlease() {
        // ...so are functions
    }
}

class Extender: NonFinalClass() {
    override fun overrideMePlease() {

    }
}



















/**
 * Create instance of class
 * No "new" keyword
 **/
val usr = User()
val store = Store(listOf(Customer("Bob"), Customer("Lisa")))










/**
 * Singleton in Kotlin is called object declaration
 **/

object Singleton {
    init {
        println("This ($this) is a singleton")
    }
}














/**
 * Companion Objects - object declaration inside class
 **/

class Key(val value: Int) {
    companion object {
        @JvmField
        val COMPARATOR: Comparator<Key> = compareBy<Key> { it.value }

        @JvmStatic
        fun publicStaticFinalFunction() {
            // do stuff
        }
    }
}

// Java
// public static final field in Key class
// KeyKt.COMPARATOR.compare(key1, key2);
































/**
 *
 *
 *
 *
 *  INTERFACES
 *
 *
 *
 *
 *
 **/






interface Contract {
    fun isSecure(): Boolean
    fun getRandomKey(): Int
}


class Bank: Contract {
    override fun isSecure(): Boolean {
        return true
    }

    override fun getRandomKey(): Int {
        return 4
    }
}



























































/**
 *
 *
 *
 *
 *  FUNCTIONS
 *
 *
 *
 *
 *
 **/



// Can be simplified...
fun double(x: Int): Int {
    return 2 * x
}




class CallingFunctionz {

    init {
        val bank = Bank()
        val secure = bank.isSecure()
    }
}





































/**
 * Lambdas
 **/

class LambdaExample {

    val helloWorld = {
        if (russianNames.contains("Artem")) "Привет мир" else "Hello, world!"
    }

    init {
        println(helloWorld)
    }
}

val russianNames = arrayOf("Maksim", "Artem", "Sophia", "Maria", "Maksim")




























/**
 * Higher-Order functions
 **/
class Benchmark {
    fun benchmark(functionToMeasure: () -> Unit): Long {
        val startTime = System.currentTimeMillis()
        functionToMeasure.invoke()
        return System.currentTimeMillis() - startTime
    }
}

val benchmark = Benchmark()
val runtime = benchmark.benchmark {
    for (i in 0..100000) {
        println("Employee: ${Employee(i)}")
    }
}































/**
 *
 *
 *
 *
 *
 *
 * KOTLIN FEATURES
 *
 *
 *
 *
 *
 *
 *
 *
 **/

























/**
 *
 * Null Safety
 *
 **/

class NullabilityDemo {
    fun function() {}

    fun getNumberOfThings(): Int {
        return 1
    }
}





class Nullability {
    var prop: NullabilityDemo = NullabilityDemo()
    var propCanBeNull: NullabilityDemo? = NullabilityDemo()


    init {
        prop = null
        propCanBeNull = null
    }



    fun useProperties() {
        prop.function() // no problem!
        propCanBeNull.function() // no no no no! Use ? or !!, preferably ?
    }









    fun printListSafely() {
        val listWithNulls: List<String?> = listOf("A", null)
        for (item in listWithNulls) {
            item?.let { println(it) } // prints A and ignores null
        }
    }









    /***
     * Elvis operator
     */
    val text: String? = "234234"
    val length = text?.length ?: -1
}























/**
 *
 * Data classes
 *
 **/























/**
 *
 * Extension Functions
 *
 **/

/**
 *
 * Extensions do not actually modify classes they extend. By defining an extension,
 * you do not insert new members into a class,
 * but merely make new functions callable with the dot-notation on variables of this type.
 *
 **/
fun String.replaceSpaceWithDash(): String {
    return this.replace(" ", "-")
}

fun <T> MutableList<T>.swap(firstIndex: Int, secondIndex: Int): MutableList<T> {
    val tmp = this[firstIndex] // 'this' corresponds to the list
    this[firstIndex] = this[secondIndex]
    this[secondIndex] = tmp
    return this
}




class ExtensionFunctionTest {
    val textWithDash = "this is a text with spaces".replaceSpaceWithDash()

    val swappedList = mutableListOf(1, 2, 3, 4).swap(0, 3)
}












































/**
 *
 * Default & Named arguments
 *
 **/


data class WebSite(val page: String = "index.html", val url: String = "http://jayway.com")

class ArgumentTest {
    val jaywayWebSite = WebSite()
    val googleWebSite = WebSite(url = "http://google.com", page = "main.html")
}





















/**
 *
 * String interpolation
 *
 */


val language = "Kotlin"
// Kotlin has 6 characters
val text = "$language has ${language.length} characters"





















/**
 *
 * Destructuring
 *
 */

data class Color(val red: Int, val green: Int, val blue: Int)
data class Rect(val left: Int, val top: Int, val right: Int, val bottom: Int)
data class Point(val x: Int, val y: Int)

class DestructuringDemo {

    val map = mapOf(0 to "Einar", 1 to "Fredrik")

    fun testDestruct() {
        // now with prism
        val (red, green, blue) = Color(red = 255, green = 125, blue = 0)
        // destructing four squares
        val (left, top, right, bottom) = Rect(0, 0, 1920, 1080)
        // or more pointedly
        val (x, y) = Point(100, 200)

        // iterating over a map with destructuring
        for ((id, name) in map) {
            // do something with the entries
        }
    }
}



// This works because the Kotlin standard library declares extension
// functions (componentN()) for Map.Entry, with the keyword operator
//   operator fun <K, V> Map<K, V>.iterator(): Iterator<Map.Entry<K, V>> = entrySet().iterator()
//   operator fun <K, V> Map.Entry<K, V>.component1() = getKey()
//   operator fun <K, V> Map.Entry<K, V>.component2() = getValue()













/**
 * Kotlin Standard Library
 *
 * Useful functions implementing idiomatic patterns, working with collections, framework classes, etc.
 *
 * For Android there's Android KTX for simplifying Android development.
 *
 **/

class StdLib {

    fun test() {

        val job: Job? = Job()


        /**
         *
         * let
         *
         **/
        job?.let {
            doStuffIfJobIsNotNull()
        }













        /**
         *
         * apply
         *
         * A builder type pattern that always returns the object the operations execute on
         *
         **/
        val wednesday = job?.apply {
            currentDay = "Wednesday"

            startDay()
            fika()
            twitter()
            instagram()
            takeBreak()
            fika()
            facebook()
            endDay()
        }









        /**
         *
         * with
         *
         * Applies operations on the object but may return anything at the end of the block
         *
         **/
        val window = KotlinWindow()
        with(window) {
            setWidth(100)
            setHeight(200)
        }








        /**
         *
         * when
         *
         **/
        fun printColorName(color: Colors) {

            when (color) {
                Colors.RED -> println("RED")
                Colors.BLUE -> println("BLUE")
                Colors.YELLOW -> println("YELLOW")
                Colors.GREEN -> {
                    // Execute a block instead
                }
                else -> {

                }
            }

            when {
                "Lion".equals("Tiger") -> println("Genetic error")
                1 == 2 -> println("Now that's odd..")
            }
        }







        /**
         *
         * Preconditions
         *
         **/

        // Throws IllegalARGUMENTException
        // Use to check if arguments are correct
        fun <T> getFifthElementRequire(array: Array<T>): T {
            require(array.size >= 5) {
                "array should have at least five elements, but has ${array.size}!"
            }
            return array[4]
        }


        // Throws IllegalSTATEException
        // Use to check if method is called at (in)appropriate time
        fun <T> getFifthElementCheck(array: Array<T>): T {
            check(array.size >= 5) {
                "array should have at least five elements, but has ${array.size}!"
            }
            return array[4]
        }


















        /**
         *
         * Lazy init, initialized on first call and then cached
         *
         */
        val repository: Repository by lazy { Repository("Database") }



































        /**
         *
         * Collections
         *
         **/


        val list = listOf(1, 2, 3, 4, 5, 6)


        val areThereAnyEvenNbrsInList = list.any { it % 2 == 0 } // true
        val areThereAnyNbrsGreaterThan10 = list.any { it > 10} // false



        val howManyEvenNbrs = list.count { it % 2 == 0 } // 3


        val whatIsTheLargestNumber = list.max() // 6 or null


        val giveMeOnlyNbrsLargerThan4 = list.filter { it > 4 } // new list of 5 and 6


        val flatMap = list.flatMap { listOf(it, it + 1) } // listOf(1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7)


        val map = list.map { it * 2 } // listOf(2, 4, 6, 8, 10, 12),


        val unsortedList = mutableListOf(1,6,4,2)
        unsortedList.sort() // listOf(1, 2, 4, 6)


        // MAAANY More found here https://antonioleiva.com/collection-operations-kotlin/




    }




















    /**
     *
     *
     * Java Interoperability!
     *
     *
     **/

    // Java can be called from Kotlin and vice versa

    class InterOpTest() {
        private val registryBackup = RegistryBackup()

        fun backupTest() {
            val nullable: PersonRegistry? = registryBackup.nullableRegistry
            val notNull: PersonRegistry = registryBackup.notNullRegistry
            val notAnnotated: PersonRegistry = registryBackup.registryNotAnnotated
        }

        // Copy paste some java code now!
    }


































    class KotlinWindow{
        fun setWidth(i: Int){}
        fun setHeight(i: Int){}
    }

    enum class Colors {
        RED, BLUE, GREEN, YELLOW
    }



    fun doStuffIfJobIsNotNull() {}
    fun doSomeOtherStuff() {}
}



class Job {
    lateinit var currentDay: String
    fun startDay() {}
    fun endDay() {}
    fun takeBreak() {}
    fun fika() {}
    fun twitter() {}
    fun instagram() {}
    fun facebook() {}
}

class Repository(val name: String)




































