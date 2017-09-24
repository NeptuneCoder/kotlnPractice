package practice.yhai.com.kotlinpractice.module.main

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_weather_view.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import practice.yhai.com.kotlinpractice.R
import practice.yhai.com.kotlinpractice.common.BaseActivity
import practice.yhai.com.kotlinpractice.common.retrofit.YHaiServer

class MainActivity : BaseActivity() {
    lateinit var  datas: MutableList<HourlyForecastBean>//延迟加载
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        datas = ArrayList()
        recyclerView.layoutManager =  LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = MainRecyclerAdapter(this,datas)
        doAsync {
            val data = YHaiServer.apiStore.getWeather("CN101010100").execute().body() as Data
            datas.addAll(data.HeWeather5!![0].hourly_forecast)
                uiThread {
                recyclerView.adapter.notifyDataSetChanged()
                }
        }
    }

    inner class MainRecyclerAdapter(val context: Context, val datas: MutableList<HourlyForecastBean>) : RecyclerView.Adapter<MainViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): MainViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_weather_view,null)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder?, postion: Int) {
            val data = datas[postion]
            holder as MainViewHolder
            holder.itemView.time.text = "time  = ${data.date}"
            holder.itemView.code.text = "cond.code = ${data.cond.code}"
            holder.itemView.txt.text =  "cond.txt = ${data.cond.txt}"
            holder.itemView.dir.text = "wind.dir = ${data.wind.dir}"
            holder.itemView.sc.text = "wind.sc = ${data.wind.sc}"
            holder.itemView.deg.text = "wind.deg = ${data.wind.deg}"
            holder.itemView.spd.text = "wind.spd = ${data.wind.spd}"

        }

        override fun getItemCount(): Int {
            return datas.size
        }

    }

    class MainViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}
