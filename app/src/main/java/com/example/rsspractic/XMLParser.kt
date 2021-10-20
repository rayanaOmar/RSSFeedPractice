package com.example.rsspractic

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XMLParser {
    private val question = ArrayList<Question>()
    private var text: String? = null

    private var questionTitle = ""
    private var questionName = ""
    private var questionLink = ""

    fun parse(inputStream: InputStream): List<Question> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("title", ignoreCase = true) -> {
                            questionTitle = text.toString()
                        }
                        tagName.equals("name", ignoreCase = true) -> {
                            questionName = text.toString()
                        }
                        tagName.equals("link", ignoreCase = true) -> {
                            questionLink = parser.getAttributeValue(1).toString()
                        }
                        tagName.equals("entry", ignoreCase = true) -> {
                            question.add(Question(questionTitle, questionName, questionLink))
                        }
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return question
    }

}