package com.github.dapeng.mesh.auth.datasource

import javax.annotation.Resource
import javax.sql.DataSource
import org.springframework.stereotype.Service
/**
 * @author with struy.
 *         Create by 2018/6/20 10:17
 *         email :yq1724555319@gmail.com
 */
object MeshDataSource {
  var mysqlData: DataSource = _
}

class MeshDataSource {

  @Resource(name = "tx_mesh_dataSource")
  def setMysqlData(mysqlData: DataSource): Unit = {
    MeshDataSource.mysqlData = mysqlData
  }
}
