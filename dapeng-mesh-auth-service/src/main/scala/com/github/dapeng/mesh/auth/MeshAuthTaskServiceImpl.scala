package com.github.dapeng.mesh.auth

import com.github.dapeng.core.timer.{ScheduledTask, ScheduledTaskCron}
import com.github.dapeng.mesh.auth.query.sql.MeshAuthSql
import com.github.dapeng.mesh.auth.scala.service.MeshAuthTaskService
import com.github.dapeng.mesh.auth.util.GateWayUtil
import org.slf4j.LoggerFactory

/**
 * @author with struy.
 *         Create by 2020/2/18 23:36
 *         email :yq1724555319@gmail.com
 */

@ScheduledTask
class MeshAuthTaskServiceImpl extends MeshAuthTaskService {
  private val logger = LoggerFactory.getLogger(getClass)

  /**
   * 刷新时间三分钟
   */
  @ScheduledTaskCron(cron = "0 0/3 * * * ? ")
  override def reloadApiKey(): Unit = {
    logger.info("start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> reload Apikey")
    GateWayUtil.infoCache.clear()
    MeshAuthSql.listApiInfo().foreach(info => {
      GateWayUtil.infoCache += (info.apiKey -> info)
    })
    logger.info("end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> reload Apikey")
  }
}
