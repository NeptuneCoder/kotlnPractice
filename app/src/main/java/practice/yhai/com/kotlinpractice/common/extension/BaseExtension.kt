package manage.ad.yiba.com.admanage.common.extension

import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import practice.yhai.com.kotlinpractice.common.BaseActivity


/**
 * Created by yh on 8/24/17.
 *  该kt文件用来对类的扩展
 */

//Context 基础类的扩展
fun Context.toast(content: CharSequence, time: Int = Toast.LENGTH_LONG){
    Toast.makeText(this,content,time).show()
}

//扩展一个log方法
fun Any.log(tag: Any = "Tag",content: String?){

    Log.i(tag.toString(),content)
}
fun Any?.toString(): String {
    if (this == null) return "null"
// 在空检查之后,`this` 被自动转为非空类型,因此 toString() 可以被解
//    析到任何类的成员函数中
//    ChinaPerson("",2,"","","")
//    ChinaPerson("",2,"","","","")
    return toString()
}

fun BaseActivity.showDialogMessage(title: String = "", body: String, rightCode: ()->Unit = {System.exit(0)}, leftCode: ()->Unit = {}) {
    var exitDialog: AlertDialog? = null
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title).setMessage(body).setPositiveButton("OK") { dialog, which ->
        rightCode.invoke()
    }.setNeutralButton("Cancel"){dialog, which ->
        leftCode.invoke()
        try {
            exitDialog?.dismiss()
        }catch (e: Exception){
        }
    }

    exitDialog = builder.create()
    exitDialog.show()
}


