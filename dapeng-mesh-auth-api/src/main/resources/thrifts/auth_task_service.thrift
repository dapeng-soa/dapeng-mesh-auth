namespace java com.github.dapeng.mesh.auth.service

/**
* 网关鉴权定时任务
**/
service MeshAuthTaskService {
/**
# 重新载入apiKey缓存任务
**/
  void reloadApiKey()
}(group="meshAuth")
