package ba.sake.hepek.w3css.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.GridComponents

object W3CssGridComponents extends W3CssGridComponents

trait W3CssGridComponents extends GridComponents {
  import GridComponents._

  private[hepek] override def mkRow(content: Frag*): Frag =
    div(cls := "w3-row")(content)

  private[hepek] def mkRowSingleCol(content: Seq[Frag]): Frag = {
    val classes1 = singleRatioClasses(0).map(cls := _)
    val classes2 = singleRatioClasses(1).map(cls := _)
    val classes3 = singleRatioClasses(2).map(cls := _)
    mkRow(
      div(classes1, cls := "w3-col"),
      div(classes2, cls := "w3-col")(content),
      div(classes3, cls := "w3-col")
    )
  }

  private[hepek] override def mkCol2(index: Int, content: Col2): Frag = {
    val classes = halfRatioClasses(index).map(cls := _)
    div(classes, cls := "w3-col")(content.content)
  }

  private[hepek] override def mkCol3(index: Int, content: Col3): Frag = {
    val classes = thirdRatioClasses(index).map(cls := _)
    div(classes, cls := "w3-col")(content.content)
  }

  /* HELPERS */
  private def singleRatioClasses(index: Int): List[String] = {
    val lg = lgClass(screenRatios.lg.single, index)
    val md = screenRatios.md.map(r => mdClass(r.single, index))
    val sm = screenRatios.sm.map(r => smClass(r.single, index))
    val xs = screenRatios.xs.map(r => xsClass(r.single, index))
    List(Option(lg), md, sm, xs).flatten
  }

  private def halfRatioClasses(index: Int): List[String] = {
    val lg = lgClass(screenRatios.lg.half, index)
    val md = screenRatios.md.map(r => mdClass(r.half, index))
    val sm = screenRatios.sm.map(r => smClass(r.half, index))
    val xs = screenRatios.xs.map(r => xsClass(r.half, index))
    List(Option(lg), md, sm, xs).flatten
  }

  private def thirdRatioClasses(index: Int): List[String] = {
    val lg = lgClass(screenRatios.lg.third, index)
    val md = screenRatios.md.map(r => mdClass(r.third, index))
    val sm = screenRatios.sm.map(r => smClass(r.third, index))
    val xs = screenRatios.xs.map(r => xsClass(r.third, index))
    List(Option(lg), md, sm, xs).flatten
  }

  private def xsClass(ratio: Ratio, index: Int): String =
    "s" + ratioValue(ratio, index)

  private def smClass(ratio: Ratio, index: Int): String =
    "s" + ratioValue(ratio, index)

  private def mdClass(ratio: Ratio, index: Int): String =
    "m" + ratioValue(ratio, index)

  private def lgClass(ratio: Ratio, index: Int): String =
    "l" + ratioValue(ratio, index)

  private def ratioValue(ratio: Ratio, index: Int): Int =
    ratio2W3Css(ratio.values(index), ratio.values)

  // for args 1,1:2 => (1/3)*12 == 4
  private def ratio2W3Css(ratio: Int, allRatios: List[Int]): Int =
    ((ratio / allRatios.sum.toDouble) * 12).toInt
}
