package cn.academy.energy.client.ui

import cn.academy.core.client.Resources
import cn.academy.core.client.ui.TechUI.ContainerUI
import cn.academy.crafting.block.ContainerImagFusor
import cn.lambdalib.cgui.gui.component.{TextBox, ProgressBar}
import cn.lambdalib.cgui.gui.event.FrameEvent
import cn.lambdalib.cgui.xml.CGUIDocument

import cn.academy.core.client.ui._
import cn.lambdalib.cgui.ScalaCGUI._

object GuiImagFusor2 {

  private val template = CGUIDocument.panicRead(Resources.getGui("rework/page_imagfusor")).getWidget("main")

  def apply(container: ContainerImagFusor) = {
    val tile = container.tile

    val invPage = InventoryPage(template.copy())

    val ret = new ContainerUI(container, invPage, ConfigPage(Nil, Nil), WirelessPage(tile))

    { // Work progress display
      val progWidget = invPage.window.getWidget("progress")
      val bar = progWidget.component[ProgressBar]

      progWidget.listens[FrameEvent](() => {
        bar.progressDisplay = tile.getWorkProgress
      })
    }

    { // Imag requirement display
      val reqWidget = invPage.window.getWidget("text_imagneeded")
      val text = reqWidget.component[TextBox]

      text.content = "IDLE"

      reqWidget.listens[FrameEvent](() => {
        val recipe = tile.getCurrentRecipe
        text.content = if (recipe == null) "IDLE" else String.valueOf(recipe.consumeLiquid)
      })
    }

    ret
  }

}
