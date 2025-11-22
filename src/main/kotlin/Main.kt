import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\n=== ВЫБЕРИТЕ ЗАДАЧУ ===")
        println("1 - Подсчет различных цифр в массиве")
        println("2 - Проверка симметрии массива")
        println("3 - Шифровка и дешифровка")
        println("4 - Пересечение массивов")
        println("5 - Группировка слов по буквам")
        println("0 - Выход")
        print("Ваш выбор: ")

        val choice = scanner.nextLine()

        when (choice) {
            "1" -> task1()
            "2" -> task2()
            "3" -> task3()
            "4" -> task4()
            "5" -> task5()
            "0" -> {
                println("До свидания!")
                break
            }
            else -> println("Ошибка! Введите число от 0 до 5")
        }
    }
}

// Задача 1
fun task1() {
    println("\n=== ЗАДАЧА 1 ===")
    val scanner = Scanner(System.`in`)

    try {
        print("Введите количество строк: ")
        val rows = scanner.nextInt()
        print("Введите количество столбцов: ")
        val cols = scanner.nextInt()

        if (rows <= 0 || cols <= 0) {
            println("Ошибка! Количество строк и столбцов должно быть больше 0")
            return
        }

        val array = Array(rows) { IntArray(cols) }
        println("Введите $rows строк по $cols трехзначных чисел:")

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val number = scanner.nextInt()
                if (number < 100 || number > 999) {
                    println("Ошибка! Число должно быть трехзначным")
                    return
                }
                array[i][j] = number
            }
        }

        println("\nВведенный массив:")
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                print("${array[i][j]}\t")
            }
            println()
        }

        val digits = BooleanArray(10)

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                var number = array[i][j]
                while (number > 0) {
                    val digit = number % 10
                    digits[digit] = true
                    number /= 10
                }
            }
        }

        var count = 0
        for (i in 0 until 10) {
            if (digits[i]) {
                count++
            }
        }

        println("В массиве использовано $count различных цифр")

    } catch (e: Exception) {
        println("Ошибка! Проверьте правильность ввода")
    }
}

// Задача 2
fun task2() {
    println("\n=== ЗАДАЧА 2 ===")

    val array = arrayOf(
        intArrayOf(5, 9, 6, 7, 2),
        intArrayOf(9, 8, 4, 5, 3),
        intArrayOf(6, 4, 3, 8, 7),
        intArrayOf(7, 5, 8, 4, 8),
        intArrayOf(2, 3, 7, 8, 1)
    )

    println("Массив 5x5:")
    for (i in 0 until 5) {
        for (j in 0 until 5) {
            print("${array[i][j]}\t")
        }
        println()
    }

    var symmetric = true

    for (i in 0 until 5) {
        for (j in 0 until 5) {
            if (array[i][j] != array[j][i]) {
                symmetric = false
                break
            }
        }
        if (!symmetric) break
    }

    if (symmetric) {
        println("Массив симметричен относительно главной диагонали")
    } else {
        println("Массив НЕ симметричен относительно главной диагонали")
    }
}

// Задача 3
fun task3() {
    println("\n=== ЗАДАЧА 3 ===")
    val scanner = Scanner(System.`in`)

    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"

    val charNumbers = mapOf(
        'А' to 21, 'Б' to 13, 'В' to 4, 'Г' to 20, 'Д' to 22,
        'Е' to 1, 'Ё' to 25, 'Ж' to 12, 'З' to 24, 'И' to 14,
        'Й' to 2, 'К' to 28, 'Л' to 9, 'М' to 23, 'Н' to 3,
        'О' to 29, 'П' to 6, 'Р' to 16, 'С' to 15, 'Т' to 11,
        'У' to 26, 'Ф' to 5, 'Х' to 30, 'Ц' to 27, 'Ч' to 8,
        'Ш' to 18, 'Щ' to 10, 'Ъ' to 33, 'Ы' to 31, 'Ь' to 32,
        'Э' to 19, 'Ю' to 7, 'Я' to 17
    )

    val numberToChar = mutableMapOf<Int, Char>()
    for ((char, number) in charNumbers) {
        numberToChar[number] = char
    }

    print("Выберите действие (1 - зашифровать, 2 - расшифровать): ")
    val action = scanner.nextLine()

    print("Введите ключевое слово: ")
    val key = scanner.nextLine().uppercase().replace(" ", "")

    print("Введите текст: ")
    val text = scanner.nextLine().uppercase().replace(" ", "")

    if (key.isEmpty() || text.isEmpty()) {
        println("Ошибка! Ключ и текст не могут быть пустыми")
        return
    }

    for (char in key) {
        if (!charNumbers.containsKey(char)) {
            println("Ошибка! Символ '$char' из ключа не найден в алфавите")
            return
        }
    }

    for (char in text) {
        if (!charNumbers.containsKey(char)) {
            println("Ошибка! Символ '$char' из текста не найден в алфавите")
            return
        }
    }

    if (action == "1") {
        var result = ""

        for (i in text.indices) {
            val textChar = text[i]
            val keyChar = key[i % key.length]

            val textNumber = charNumbers[textChar]!!
            val keyNumber = charNumbers[keyChar]!!

            val newNumber = (textNumber + keyNumber - 1) % 33 + 1

            result += numberToChar[newNumber]!!
        }

        println("Зашифрованный текст: $result")

    } else if (action == "2") {
        var result = ""

        for (i in text.indices) {
            val textChar = text[i]
            val keyChar = key[i % key.length]

            val textNumber = charNumbers[textChar]!!
            val keyNumber = charNumbers[keyChar]!!

            var originalNumber = textNumber - keyNumber + 1
            if (originalNumber <= 0) {
                originalNumber += 33
            }

            result += numberToChar[originalNumber]!!
        }

        println("Расшифрованный текст: $result")

    } else {
        println("Ошибка! Выберите 1 или 2")
    }
}

// Задача 4
fun task4() {
    println("\n=== ЗАДАЧА 4 ===")
    val scanner = Scanner(System.`in`)

    try {
        println("Введите первый массив чисел через пробел:")
        val input1 = scanner.nextLine()
        val array1 = input1.split(" ").map { it.toInt() }

        println("Введите второй массив чисел через пробел:")
        val input2 = scanner.nextLine()
        val array2 = input2.split(" ").map { it.toInt() }

        val list1 = array1.toMutableList()
        val list2 = array2.toMutableList()
        val result = mutableListOf<Int>()

        for (num in list1) {
            if (list2.contains(num)) {
                result.add(num)
                list2.remove(num)
            }
        }

        println("Первый массив: ${array1.joinToString()}")
        println("Второй массив: ${array2.joinToString()}")
        println("Пересечение: ${result.joinToString()}")

    } catch (e: Exception) {
        println("Ошибка! Проверьте правильность ввода чисел")
    }
}

// Задача 5
fun task5() {
    println("\n=== ЗАДАЧА 5 ===")
    val scanner = Scanner(System.`in`)

    try {
        println("Введите слова через пробел:")
        val input = scanner.nextLine()
        val words = input.split(" ").map { it.trim() }

        val groups = mutableMapOf<String, MutableList<String>>()

        for (word in words) {
            val sortedLetters = word.toCharArray().sorted().joinToString("")

            if (groups.containsKey(sortedLetters)) {
                groups[sortedLetters]!!.add(word)
            } else {
                groups[sortedLetters] = mutableListOf(word)
            }
        }

        println("Группы слов:")
        for ((_, groupWords) in groups) {
            if (groupWords.size > 0) {
                println(groupWords.joinToString(", "))
            }
        }

    } catch (e: Exception) {
        println("Ошибка! Проверьте правильность ввода")
    }
}