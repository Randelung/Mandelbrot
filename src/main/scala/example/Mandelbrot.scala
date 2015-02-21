package example
import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html
import scala.util.control.Breaks

@JSExport
object Mandelbrot {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val context = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    context.canvas.width = dom.innerWidth
    context.canvas.height = dom.innerHeight

    var x = 0
    var y = 0
    var nx = .0
    var ny = .0
    var zx = .0
    var zy = .0
    var hzx = .0
    var hzy = .0
    var c = .0

    val inner = Breaks


    def run: Unit = {
      nx = x * 3d / context.canvas.width - 2
      ny = y * 2.5 / context.canvas.height - 1.25

      inner.breakable(
        for (i <- 0 until 256) {
          c = zx*zx + zy*zy

          if (c > 4 || i == 255) {
            context.fillStyle = '#' + Integer.toHexString(i) + Integer.toHexString(i) + Integer.toHexString(i)
            context.fillRect(x, y, 1, 1)

            x += 1
            if (x == context.canvas.width) {
              x = 0
              y += 1
              if (y == context.canvas.height)
                inner.break()
            }
            dom.setInterval(() => run, 10)
            inner.break()
          }

          hzx = zx
          hzy = zy

          zx = hzx*hzx - hzy*hzy
          zy = 2*hzx*hzy

          zx += nx
          zy += ny
        }
      )
    }

    dom.setInterval(() => run, 0)
  }
}