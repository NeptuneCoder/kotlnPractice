package manage.ad.yiba.com.admanage.moudle.adSourceDetail

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_source_view.view.*
import manage.ad.yiba.com.admanage.common.extension.toDrawable
import org.jetbrains.anko.sdk25.coroutines.onClick
import practice.yhai.com.kotlinpractice.R

/**
 * Created by yh on 9/10/17.
 */

class ItemSourceView(context: Context) : LinearLayout(context),IGainData{

    private lateinit var listener: OnDataStatusListener

    private  var itemData: ItemData

    var urlType: List<String>
    init {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.item_source_view,null)
        rootView.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        this.addView(rootView)
        urlType = context.resources.getStringArray(R.array.url_type).toList()

        itemData = ItemData()

        keyEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                itemData.key = s.toString()

                if (s?.isEmpty() ?: true){
                    listener?.onVerifyFaild()
                    keyEt.background = R.drawable.data_error_box.toDrawable(context)
                }else{
                    keyEt.background = R.drawable.data_ok_box.toDrawable(context)
                    listener?.onVerifySuccess()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        spinnerUrlType.onItemSelectedListener = SpinnerListener()

        deleteSelfIv.onClick { listener?.deleteSelf(this@ItemSourceView) }
    }



    inner class SpinnerListener : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        itemData.value = urlType[position]
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    fun setOnDataStatusListener(listener: OnDataStatusListener){
        this.listener = listener
    }
    interface OnDataStatusListener{

        fun onVerifySuccess()

        fun onVerifyFaild()

        fun deleteSelf(self: ItemSourceView)
    }

    override fun gainData(): ItemData {
        return     itemData
    }


    fun getInputStatus():Boolean{
        if( keyEt.text.toString().trim().isEmpty()){keyEt.background = R.drawable.data_error_box.toDrawable(context)}
        return keyEt.text.toString().trim().isEmpty()
    }
    fun setSelection(postion: Int){
        spinnerUrlType.setSelection(postion.mod(urlType.size).minus(1),true)
    }
}