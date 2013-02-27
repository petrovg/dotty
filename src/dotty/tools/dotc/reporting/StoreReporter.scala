package dotty.tools
package dotc
package reporting

import core.Contexts.Context
import scala.collection.mutable
import core.Positions.Position
import Reporter.Severity.{Value => Severity}

/**
 * This class implements a Reporter that stores all messages
 */
class StoreReporter extends Reporter {

  class Info(val msg: String, val severity: Severity, val pos: Position) {
    override def toString() = "pos: " + pos + " " + msg + " " + severity
  }
  val infos = new mutable.LinkedHashSet[Info]

  protected def report(msg: String, severity: Severity, pos: Position)(implicit ctx: Context): Unit = {
    infos += new Info(msg, severity, pos)
  }

  override def reset() {
    super.reset()
    infos.clear()
  }
}