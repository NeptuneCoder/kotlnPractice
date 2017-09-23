package manage.ad.yiba.com.admanage.common.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by yh on 9/4/17.
 */
/**
 * Created by zhangll on 2017/1/14.
 */
fun Date.toRequestDateString() : String {
    val format = SimpleDateFormat("yyyyMMddHHmmssSSS")
    return format.format(this)
}