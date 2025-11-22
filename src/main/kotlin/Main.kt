fun main() {
    while (true) {
        println("\n=== ВЫБЕРИТЕ ЗАДАНИЕ ===")
        println("1 - Двумерный массив с различными цифрами")
        println("2 - Проверка симметрии матрицы")
        println("3 - Шифрование русскими буквами")
        println("4 - Пересечение массивов")
        println("5 - Группировка слов по буквам")
        println("0 - Выход")
        print("Ваш выбор: ")

        when (readLine()!!.trim()) {
            "1" -> task1()
            "2" -> task2()
            "3" -> task3()
            "4" -> task4()
            "5" -> task5()
            "0" -> {
                println("Выход из программы.")
                return
            }
            else -> println("Неверный выбор! Попробуйте снова.")
        }
    }
}

// Задание 1
fun task1() {
    println("\n=== ЗАДАНИЕ 1 ===")

    print("Введите количество строк: ")
    val rows = readLine()!!.toInt()
    print("Введите количество столбцов: ")
    val cols = readLine()!!.toInt()

    val matrix = Array(rows) { IntArray(cols) }

    println("Введите $rows строк по $cols трехзначных чисел (через пробел):")
    for (i in 0 until rows) {
        print("Строка ${i + 1}: ")
        val numbers = readLine()!!.split(" ")
        for (j in 0 until cols) {
            matrix[i][j] = numbers[j].toInt()
        }
    }

    val allDigits = mutableSetOf<Char>()
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            val number = matrix[i][j].toString()
            for (digit in number) {
                allDigits.add(digit)
            }
        }
    }

    println("\nДвумерный массив:")
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            print("${matrix[i][j]}\t")
        }
        println()
    }

    println("В массиве использовано ${allDigits.size} различных цифр")
}

// Задание 2
fun task2() {
    println("\n=== ЗАДАНИЕ 2 ===")

    val size = 5
    val matrix = Array(size) { IntArray(size) }

    println("Введите матрицу 5x5 (5 строк по 5 чисел через пробел):")
    for (i in 0 until size) {
        print("Строка ${i + 1}: ")
        val numbers = readLine()!!.split(" ").map { it.toInt() }
        for (j in 0 until size) {
            matrix[i][j] = numbers[j]
        }
    }

    var symmetric = true
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (matrix[i][j] != matrix[j][i]) {
                symmetric = false
                break
            }
        }
        if (!symmetric) break
    }

    println("\nМатрица:")
    for (i in 0 until size) {
        for (j in 0 until size) {
            print("${matrix[i][j]}\t")
        }
        println()
    }

    if (symmetric) {
        println("Матрица симметрична относительно главной диагонали")
    } else {
        println("Матрица НЕ симметрична относительно главной диагонали")
    }
}

// Задание 3
fun task3() {
    println("\n=== ЗАДАНИЕ 3 ===")

    val letters = arrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я')
    val numbers = arrayOf(21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5, 30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17)

    println("Выберите действие:")
    println("1 - Зашифровать")
    println("2 - Расшифровать")
    val choice = readLine()!!

    print("Введите текст: ")
    val text = readLine()!!.uppercase()

    print("Введите ключевое слово: ")
    val key = readLine()!!.uppercase()

    var result = ""
    var keyIndex = 0

    for (char in text) {
        if (char == ' ') {
            result += ' '
            continue
        }

        var textNumber = 0
        for (i in letters.indices) {
            if (letters[i] == char) {
                textNumber = numbers[i]
                break
            }
        }

        val keyChar = key[keyIndex % key.length]
        var keyNumber = 0
        for (i in letters.indices) {
            if (letters[i] == keyChar) {
                keyNumber = numbers[i]
                break
            }
        }
        keyIndex++

        var newNumber = 0
        if (choice == "1") {
            newNumber = textNumber + keyNumber
            if (newNumber > 33) newNumber -= 33
        } else {
            newNumber = textNumber - keyNumber
            if (newNumber < 1) newNumber += 33
        }

        var newChar = char
        for (i in numbers.indices) {
            if (numbers[i] == newNumber) {
                newChar = letters[i]
                break
            }
        }

        result += newChar
    }

    if (choice == "1") {
        println("Зашифрованный текст: $result")
    } else {
        println("Расшифрованный текст: $result")
    }
}

// Задание 4
fun task4() {
    println("\n=== ЗАДАНИЕ 4 ===")

    print("Введите первый массив (числа через пробел): ")
    val arr1 = readLine()!!.split(" ").map { it.toInt() }

    print("Введите второй массив (числа через пробел): ")
    val arr2 = readLine()!!.split(" ").map { it.toInt() }

    val list1 = arr1.toMutableList()
    val list2 = arr2.toMutableList()
    val result = mutableListOf<Int>()

    for (num in list1) {
        if (num in list2) {
            result.add(num)
            list2.remove(num)
        }
    }

    println("Первый массив: ${arr1.joinToString()}")
    println("Второй массив: ${arr2.joinToString()}")
    println("Пересечение: ${result.joinToString()}")
}

// Задание 5
fun task5() {
    println("\n=== ЗАДАНИЕ 5 ===")

    print("Введите слова через пробел: ")
    val words = readLine()!!.split(" ")

    val groups = mutableListOf<MutableList<String>>()
    val used = BooleanArray(words.size) { false }

    for (i in words.indices) {
        if (!used[i]) {
            val group = mutableListOf<String>()
            group.add(words[i])
            used[i] = true

            val sorted1 = words[i].toCharArray().sorted().joinToString("")

            for (j in i + 1 until words.size) {
                if (!used[j]) {
                    val sorted2 = words[j].toCharArray().sorted().joinToString("")
                    if (sorted1 == sorted2) {
                        group.add(words[j])
                        used[j] = true
                    }
                }
            }
            groups.add(group)
        }
    }

    println("Группы слов:")
    for (group in groups) {
        println(group.joinToString(", "))
    }
}