package manage.ad.yiba.com.admanage.common.extension

import java.util.regex.Pattern

/**
 * Created by yh on 9/4/17.
 */
/**
 * Created by zhangll on 2017/1/14.
 */

fun String.isNumber(): Boolean {
    val regEx = "^-?[0-9]+$"
    val pat = Pattern.compile(regEx)
    val mat = pat.matcher(this)

    return mat.find()
}