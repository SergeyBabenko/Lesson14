import Xml.Companion.obj

class Xml {
    companion object {
        fun obj(content: XmlObjectBuilder.() -> Unit): XmlObject {
            val builder = XmlObjectBuilder()
            builder.content()
            return builder.result
        }
    }
}

class XmlObjectBuilder {
    internal val result = XmlObject()
    infix fun String.to(value: Any) {
        result.add(this, value)
    }
}

class XmlObject {
    private var xmlText: String = ""
    fun add(text: String, value: Any) {
        xmlText += "<$text>$value</$text>\n"
    }

    override fun toString(): String {
        return xmlText
    }
}

fun main() {
    val obj = obj {
        "object" to obj {
            "name" to "Sergey"
            "surname" to "Babenko"
            "birthDate" to "22.12.1995"
            "addresses" to obj {
                "address" to "Sample 1"
                "address" to "Sample 2"
            }
        }
    }
    println(obj)
}