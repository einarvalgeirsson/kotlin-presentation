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
 *  CLASSES
 *
 *
 *
 *
 *
 **/




















/**
 * Class
 **/
class User {
}























/**
 * Class with primary constructor
 **/
class ClassWithPrimaryConstructor(name: String)













/**
 *
 * The primary constructor cannot contain any code.
 * Initialization code can be placed in initializer blocks,
 * which are prefixed with the init keyword:
 *
 */
class Customer(name: String) {
    init {
        logger.info("Customer initialized with value ${name}")
    }
}



















/**
 * Note that parameters of the primary constructor can be used in the initializer blocks.
 * They can also be used in property initializers declared in the class body:
 *
 **/
class Customer2(name: String) {
    val customerKey = name.toUpperCase()
}













/**
 * Properties can be declared and initialized in the primary constructor
 **/
class Employee(val vacationDays: Int)

class Company(val employees : List<Employee> = mutableListOf(Employee(5)))
























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
// Key.COMPARATOR.compare(key1, key2);
































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
 * Val / Var
 *
 *
 **/

class ValVar {
    val immutable = "can't be re-assigning this one"

    var mutable = "go ahead"

    lateinit var hej: String
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
 *  FUNCTIONS
 *
 *
 *
 *
 *
 **/





fun double(x: Int): Int {
    return 2*x
}


class CallingFunctionz {

    init {
        val bank = Bank()
        val secure = bank.isSecure()
    }
}














/**
 * Higher-Order functions
 **/
fun operation(name: String, giveRaise: () -> Unit) {
    if (name == "einar") {
        giveRaise()
    }
}

























/**
 * Lambdas
 **/

class Lambda {
    val printHelloWorld = {
        println("Hello, world!")
    }

    init {
        printHelloWorld()
    }
}




val russianNames = arrayOf("Maksim", "Artem", "Sophia", "Maria", "Maksim")

val selectedName = russianNames
        .filter { name -> name.startsWith("m", ignoreCase = true) }
        .sortedBy { name -> name.length }
        .firstOrNull()


val selectedNameShortened = russianNames
        .filter { it.startsWith("m", ignoreCase = true) }
        .sortedBy { it.length }
        .firstOrNull()


























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
 * Equivalent of Java POJO.
 *
 * Compiler generates equals(), hashCode(), getters, setters (if mutable), copy() and toString()
 *
 *
 **/
data class Car(val manufacturer: String?, val insurancePayed: Boolean, val leftHandSteering: Boolean)






















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

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}




class Test {

    init {


        val textWithDash = "this is a text with spaces".replaceSpaceWithDash()




        val list = mutableListOf(1, 2, 3, 4)

        list.swap(0, 3)
    }
}























/**
 *
 *
 * Java Interoperability!
 *
 *
 **/

// Java can be called from Kotlin and vice versa

// Copy paste some java code now!




















/**
 *
 * Default & Named arguments
 *
 **/


data class WebSite(val url: String = "http://jayway.com")

class Test3 {
    init {

        val jaywayWebSite = WebSite()
        val googleWebSite = WebSite("http://google.com")

    }
}





















/**
 * String templates
 */


val language = "Kotlin"
// Kotlin has 6 characters
val text = "$language has ${language.length} characters"





















/**
 * Destructuring
 */

// now with prism
val (red, green, blue) = color
// destructing four squares
val (left, top, right, bottom) = rect
// or more pointedly
val (x, y) = point

// iterating over a map with destructuring
for ((key, value) in map) {
    // do something with the key and the value
}

// This works because the Kotlin standard library declares extension
// functions (componentN()) for Map.Entry, with the keyword operator
operator fun <K, V> Map<K, V>.iterator(): Iterator<Map.Entry<K, V>> = entrySet().iterator()
operator fun <K, V> Map.Entry<K, V>.component1() = getKey()
operator fun <K, V> Map.Entry<K, V>.component2() = getValue()













/**
 * Kotlin Standard Library
 *
 * Higher-order functions implementing idiomatic patterns (let, apply, use, synchronized, etc).
 *
 * Extension functions providing querying operations for collections (eager) and sequences (lazy).
 *
 * Various utilities for working with strings and char sequences.
 *
 * Extensions for JDK classes making it convenient to work with files, IO, and threading.
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
                    .also {
                        doSomeOtherStuff()
                    }
        }
        val str = "..."

        val result = str.let {
            print(this) // Receiver
            print(it) // Argument
            42 // Block return value
        }













        /**
         *
         * apply
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
         **/

        with(KotlinWindow()) {
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
                Colors.RED -> printf("RED")
                Colors.BLUE -> printf("BLUE")
                Colors.YELLOW -> printf("YELLOW")
                Colors.GREEN -> {
                    // Execute a block instead
                }
                else -> {

                }

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
        val preferences: String by lazy { sharedPreferences.getString(PREFERENCE_KEY) }














        /**
         *
         * lateinit, a promise to initialize a property later
         *
         */
        class MyActivity : AppCompatActivity() {

            lateinit var recyclerView: RecyclerView   // non-null, but not initialized

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)

                // ..
                recyclerView = findViewById(R.id.recycler_view) // initialized here
            }
        }
















        /**
         *
         * Collections
         *
         **/


        val list = listOf(1, 2, 3, 4, 5, 6)


        val areThereAnyEvenNbrsInList = list.any {it % 2 == 0} // true
        val areThereAnyNbrsGreaterThan10 = list.any { it > 10} // false



        val howManyEvenNbrs = list.count {it % 2 == 0 } // 3


        val whatIsTheLargestNumber = list.max() // 6 or null


        val giveMeOnlyNbrsLargerThan4 = list.filter { it > 4 } // new list of 5 and 6


        val flatMap = list.flatMap { listOf(it, it + 1) } // listOf(1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7)


        val map = list.map { it * 2 } // listOf(2, 4, 6, 8, 10, 12),


        val unsortedList = mutableListOf(1,6,4,2)
        unsortedList.sort() // listOf(1, 2, 4, 6)


        // MAAANY More found here https://antonioleiva.com/collection-operations-kotlin/




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









