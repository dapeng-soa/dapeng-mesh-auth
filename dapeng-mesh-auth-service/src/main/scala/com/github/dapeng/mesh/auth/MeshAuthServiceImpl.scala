package com.github.dapeng.mesh.auth

import com.github.dapeng.mesh.auth.query.CheckGateWayAuthQuery
import com.github.dapeng.mesh.auth.scala.service.MeshAuthService
import com.github.dapeng.mesh.scala.auth

class MeshAuthServiceImpl extends MeshAuthService {
  override def checkGateWayAuth(request: auth.CheckGateWayAuthRequest): Unit = {
    new CheckGateWayAuthQuery(request).execute
  }
}
