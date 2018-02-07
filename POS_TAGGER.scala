import scala.io.Source
import java.io.{File, FileInputStream, FileNotFoundException}

import opennlp.tools.postag.{POSModel, POSTaggerME}
import opennlp.tools.tokenize.{TokenizerME, TokenizerModel}

/**
  * Parts of speech tagger using openNLP Library
  */
class POS_TAGGER {
  var sentence = ""

  def file_check(path: String): Unit = {
    val file = new File(path)
    if (file == null || file.isDirectory) {
      println("Please provide the actual text file as the argument")
      System.exit(0)
    }
  }

  // To get sentences from the input file
  def file_handling(path: String): Unit = {
    file_check(path)
    try {
      sentence = Source.fromFile(path).getLines.mkString
    }
    catch {
      case ex: FileNotFoundException => println(ex)
      case ex: Exception => println(ex)
    }
  }

  def tokenizer(path: String): Array[String] = {
    file_check(path)
    val tokenModel = new TokenizerModel(new FileInputStream(path))
    val tokenizer = new TokenizerME(tokenModel)
    tokenizer.tokenize(sentence)
  }

  def POS(path: String, arr: Array[String]): Unit = {
    file_check(path)
    val posModel = new POSModel(new FileInputStream(path))
    val postTagger = new POSTaggerME(posModel)
    arr.indices.foreach((i: Int) => println(arr(i) + "  " + postTagger.tag(arr)(i) + "  " + postTagger.probs()(i)))
  }
}

