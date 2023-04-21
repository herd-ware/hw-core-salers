/*
 * File: bus.scala                                                             *
 * Created Date: 2023-03-08 01:51:25 pm                                        *
 * Author: Mathieu Escouteloup                                                 *
 * -----                                                                       *
 * Last Modified: 2023-04-21 09:44:57 am                                       *
 * Modified By: Mathieu Escouteloup                                            *
 * -----                                                                       *
 * License: See LICENSE.md                                                     *
 * Copyright (c) 2023 HerdWare                                                 *
 * -----                                                                       *
 * Description:                                                                *
 */


package herd.core.salers

import chisel3._
import chisel3.util._

import herd.common.core.{HpcBus}
import herd.core.aubrac.back.csr.{CsrDbgBus}


// ******************************
//             DEBUG
// ******************************
class PipelineDbgBus (p: PipelineParams) extends Bundle {
  val last = UInt(p.nAddrBit.W)
  val x = Vec(32, UInt(p.nDataBit.W))
  val csr = new CsrDbgBus(p.nDataBit, p.useChamp, p.nChampTrapLvl)
}

class SalersDbgBus (p: SalersParams) extends Bundle {
  val last = UInt(p.nAddrBit.W)
  val x = Vec(32, UInt(p.nDataBit.W))
  val csr = new CsrDbgBus(p.nDataBit, p.useChamp, p.nChampTrapLvl)
  val hf = if (p.useChamp) Some(Vec(p.nChampReg, Vec(6, UInt(p.nDataBit.W)))) else None
  val hpc = new HpcBus()
}          