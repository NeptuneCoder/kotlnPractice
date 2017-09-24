package manage.ad.yiba.com.admanage.moudle.adSourceDetail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.url_config_view.view.*
import manage.ad.yiba.com.admanage.common.extension.ctx
import manage.ad.yiba.com.admanage.common.extension.log
import org.jetbrains.anko.forEachChildWithIndex
import org.jetbrains.anko.sdk25.coroutines.onClick
import practice.yhai.com.kotlinpractice.R

/**
 * Created by yh on 9/10/17.
 */
class UrlConfigView(context: Context,attr: AttributeSet): LinearLayout(context,attr){

    init {
        val rootView : View  = LayoutInflater.from(context).inflate(R.layout.url_config_view,null)
        rootView.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        this.addView(rootView)

        addVewBtn.onClick { addNewItem.invoke() }

    }
    val addNewItem = {

        if(!verifyData()) {
            val itemView = ItemSourceView(ctx)
            allConfig.addView(itemView)

            itemView.setOnDataStatusListener(ItemViewListener())
            itemView.setSelection(allConfig.childCount)
        }
    }
    fun verifyData(): Boolean{
        allConfig.forEachChildWithIndex { i, view ->
            when(view){
                is ItemSourceView->{
                    if(view.getInputStatus()){
                        return true
                    }
                }
            }
        }
        return false
    }

    inner class ItemViewListener : ItemSourceView.OnDataStatusListener{
        override fun onVerifySuccess() {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onVerifyFaild() {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun deleteSelf(self: ItemSourceView) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            allConfig.forEachChildWithIndex { i, view ->
                log(content = "i == $i  view == $view    self== $self    is == ${view == self}     is === ${view === self}")
                if(self === view){
                   post {
                        allConfig?.removeView(allConfig?.getChildAt(i))
                    }
                }
            }

        }

    }

    fun getAllData(): MutableList<ItemData>{
        val allData = ArrayList<ItemData>()
        allConfig.forEachChildWithIndex({ _ , v ->
            when(v){
                is IGainData ->{
                    allData.add((v as IGainData).gainData())
                }
            }

        })
        return allData
    }
}