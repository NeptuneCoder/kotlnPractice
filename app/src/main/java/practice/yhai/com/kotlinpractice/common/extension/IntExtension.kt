package manage.ad.yiba.com.admanage.common.extension

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat

/**
 * Created by yh on 9/5/17.
 */
fun Int.toString(resources: Resources): String{
    return resources.getString(this)
}
//兼容低版本
fun Int.toColor(context: Context): Int{
    return ContextCompat.getColor(context, this)
}

fun Int.toDrawable(context: Context): Drawable {
    return ContextCompat.getDrawable(context,this)
}