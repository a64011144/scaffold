package org.lord.scaffold.exception
/**
 *
 * Created by Yuan Chaochao on 2017/8/27
 */
class ExceptionInfo {
    /*
{
  "timestamp": 1503970700130,
  "status": 500,
  "message": "fromIndex(9) > toIndex(5)",
  "path": "/pipelines"
}
     */
    long timestamp;
    int status;
    String message;
    String path;
}
