
object POS_OBJ extends App {

  if (args.length != 0) {

    val pt = new POS_TAGGER
    pt.file_handling(args(0))
    // Storing the tokens in an array
    val res = pt.tokenizer(getClass.getResource("/en-token.bin").getPath)
    pt.POS(getClass.getResource("/en-pos-maxent.bin").getPath, res)
  }
  else println("Please provide file path")
}
