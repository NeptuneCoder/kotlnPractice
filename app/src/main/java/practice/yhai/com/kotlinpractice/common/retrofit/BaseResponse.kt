package practice.yhai.com.kotlinpractice.common.retrofit

import java.io.Serializable

/**
 * Created by yh on 9/24/17.
 */
class BaseResponse(var code: Int = 10,var message: String = "") : Serializable