周五和今天上午完成了kotlin基础笔记的整理，下面把自己写android项目的东西整理出来。

[anko相关的知识点 ](#anko相关的知识点 )

[使用anko直接布局](#使用anko直接布局)

[使用anko特性,不再使用findViewById(intid)](#使用anko特性,不再使用findViewById(intid))

[项目基础](#项目基础)

[BaseActivity](#BaseActivity)

[被继承的类需要使用open关键字修饰](#被继承的类需要使用open关键字修饰)

[App](#App)

[使用了延迟加载和伴随对象](使用了延迟加载和伴随对象)

[伴随对象](#伴随对象)

[LaunchActivity中使用到的特性](#LaunchActivity中使用到的特性)

[闭包作为参数](#闭包作为参数)

[LoginActivity中的特性](#LoginActivity中的特性)

[_忽略的值](#_忽略的值)

[伴随对象的使用](#伴随对象的使用)

[点击事件的处理](#点击事件的处理)

[使用扩展函数,增强Int类和Context类](#使用扩展函数,增强Int类和Context类)

[object 几种使用情况](#object几种使用情况)

[定义组合式控件](#定义组合式控件)

[网络请求](#网络请求)

[使用retrofit构建网络层](#使用retrofit构建网络层)

[处理两个baseUrl](#处理两个baseUrl)

[RecyclerView 展示数据](#RecyclerView 展示数据)



#### anko相关的知识点 
anko 是用来替换android中使用xml布局，改用代码布局的一个库[更详细的资料](https://wangjiegulu.gitbooks.io/kotlin-for-android-developers-zh/ankoshi_shi_yao_ff1f.html)

###### 使用anko直接布局
下面是使用anko写的一个界面,里面就是dsl的一种写法，项目中还是使用传统的xml布局。现在anko布局有个问题就是android stdio不能够预览，现在kotlin已经是官方推荐语言了，不能预览的问题可能很快就会恢复。
```kotlin
setContentView(rootVertical {
            paddingWithBottomFixed = dip(16)
            backgroundResource = R.color.entrance_bg

            relativeLayout {
                verticalLayout {
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        imageView {
                            gravity = Gravity.LEFT
                            imageResource = R.drawable.back_white

                            onClick { finish() }
                        }.lparams {
                            width = dip(30)
                            height = dip(30)
                        }

                        textView {
                            textColor = resources.getColor(R.color.white)
                            textSize = 16f
                            text = "手机号登录"
                            gravity = Gravity.RIGHT
                            onClick { finish() }
                        }.lparams {
                            width = matchParent
                            minimumHeight = dip(40)
                        }
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }

                    textView {
                        textSize = 22f
                        textColor = resources.getColor(R.color.white)
                        text = "登录"
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        topMargin = dip(50)
                    }
                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        textView {
                            textSize = 16f
                            textColor = resources.getColor(R.color.white)
                            text = "用户名"
                        }.lparams {
                            width = wrapContent
                            height = wrapContent
                            topMargin = dip(20)
                        }
                        loginName = autoCompleteTextView {
                            textColor = resources.getColor(R.color.white)
                            textSize = 16f
                            maxLength = 11
                            maxLines = 1
                            hintTextColor = resources.getColor(R.color.white)
                            singleLine = true
                            backgroundResource = R.color.transparent
                        }.lparams {

                            width = matchParent
                            height = wrapContent
                        }

                        view {
                            backgroundResource = R.color.white
                        }.lparams {
                            width = matchParent
                            height = dip(0.5f)
                        }
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        textView {
                            textSize = 16f
                            textColor = resources.getColor(R.color.white)
                            text = "密码"
                        }.lparams {
                            width = wrapContent
                            height = wrapContent
                            topMargin = dip(20)
                        }
                        password = editText {
                            textColor = resources.getColor(R.color.white)
                            textSize = 16f
                            maxLength = 11
                            hintTextColor = resources.getColor(R.color.white)
                            backgroundResource = R.color.transparent
                        }.lparams {

                            width = matchParent
                        }

                        view {
                            backgroundResource = R.color.white
                        }.lparams {
                            width = matchParent
                            height = dip(0.5f)
                        }
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }

                }.lparams {
                    width = matchParent
                    height = matchParent
                }

                val nextIv = imageView {
                    imageResource = R.drawable.next_white

                    onClick { login() }
                }.lparams {
                    width = dip(30)
                    height = width
                    alignParentRight()
                    alignParentBottom()
                }

                errorHint = textView {
                    textColor = resources.getColor(R.color.white)
                    drawableLeft = R.drawable.error_tip.toDrawable(context)
                    gravity = Gravity.CENTER_VERTICAL
                    text = "请输入正确的用户名和密码"
                }.lparams {
                    height = dip(30)
                    sameBottomWith(nextIv)
                }
            }
        })
```

###### 使用anko特性,不再使用findViewById(int id)
在我们使用 xml 布局的时候，找到界面的控件一般使用findViewById(int id)或者使用ButeerKnife注解的方式，使用了anko后，变得更加简单，像导入一个java类一样导入这个布局，就可以直接使用界面中定义的控件了。在登陆界面中，如下：
```kotlin
//导入这个布局
import kotlinx.android.synthetic.main.activity_login.*
//  对按钮设置一个点击事件，email_sign_in_button 这个名字就是界面中定义的名字
email_sign_in_button.setOnClickListener { attemptLogin() }

```

#### 项目基础

###### 1. BaseActivity

###### 被继承的类需要使用open关键字修饰
```kotlin
package practice.yhai.com.kotlinpractice.common

import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity()
```
因为这个类不需要有类体，所以{}可以省略不写

##### 2. App

###### 使用了延迟加载和伴随对象

######  伴随对象
因为kotlin使用伴随对象实现的static类似的功能，

##### LaunchActivity中使用到的特性
在启动页中，利用Handler类的postDelayed延迟两秒跳转到下一个界面，postDelayed需要两个参数，一个是Runnable,另一个是Int
###### 匿名类
定义一个匿名类，实现Runnable接口
```kotlin
Handler().postDelayed(object: Runnable{
            override fun run() {
            }
        },2000)
```
###### 闭包作为参数
因为Runnable接口只有一个方法，这个方法没有返回值，那么对应的闭包写法如下
```kotlin
        Handler().postDelayed({

        },2000)
```

##### LoginActivity 中的特性
下面代码生成的登陆界面的模板代码,里面涉及到终止的概念，昨天我在写基础中，对这个没有详细说，借助下面这段代码聊一下，生成的代码这个样子的：
```kotlin
  password.setOnEditorActionListener(TextView.OnEditorActionListener{ _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true//这里有个返回，但是这个返回比较奇怪，return@OnEditorActionListener true,那前面的@OnEditorActionListener  是什么指的是代码块前面TextView 的OnEditorActionListener的接口。
            }
            false
        })
```
上面的这个代码我认为是通过如下步骤转换的。
第一步：
```kotlin
     password.setOnEditorActionListener(object: TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                return true
            }

        })
```
因为上面的这个回调中只有一个函数，那这可以写成一个闭包的方式
```kotlin
        password.setOnEditorActionListener({ _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
            }
            false
        })
```

上面的代码省略的***if***判断里面的返回，这个地方的return需要指定是谁的返回值。现在使用闭包就不知道返回给谁了，怎么办呢？
在闭包的前面加上TextView.OnEditorActionListener 接口名，在返回的时候指定我这个返回值是给OnEditorActionListener。这里还有一种方式[参考资料](https://juejin.im/post/58fd31dd8d6d8100589813bf)

###### _ 忽略的值
闭包代码中，***->***的左边出现了***_***下划线，意思是这个字段被忽略掉。

###### 点击事件的处理
自动生成的点击事件是如下：
```kotlin
email_sign_in_button.setOnClickListener { attemptLogin() }
```
手写是通过如下演变过来的：
* 第一步，使用object : 实现一个匿名内部类
```kotlin
 email_sign_in_button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                attemptLogin()
            }
        })
```
* 第二步，因为这个接口只有一个方法，所以可以写成lambda
```kotlin
 email_sign_in_button.setOnClickListener({attemptLogin()})
```
* 第三步,省略外面圆括号,这就成了代码生成的样子了。
```kotlin
email_sign_in_button.setOnClickListener { attemptLogin() }
```
但是，我们使用了anko库，那么还有更好的写法
```kotlin
email_sign_in_button.onClick {  attemptLogin() }
```

###### 使用扩展函数，增强Int类和Context类
我们一般获取资源文件的时候，使用如下代码
```kotlin
 getResources().getDrawable(R.drawable.ic_launcher_background)
        
  getResources().getString(R.string.abc_action_bar_home_description)
```
利用扩展方法的特性实现,对Int类型扩展两个方法，将***resources.getString(this)***，***ContextCompat.getColor(context, this)***的功能扩展到Int类型的身上。
```kotlin

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
```

那么，在项目中就可以如下使用
```kotlin
       //设置文本
        titleTv.text = R.string.modify.toString(resources)
        //设置背景图片
        leftIcon.background = R.drawable.ic_arrow_back_white.toDrawable(this)
```
###### object 几种使用情况
学习kotlin中，接触到三种关于object 的使用
* 匿名内部类
```kotlin
   email_sign_in_button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                attemptLogin()
            }

        })
```
* object+类名+***{}*** 
实现java中单例的一种方法。
[区别Kotlin中的object和companion object关键字](http://liuqingwen.me/blog/2017/06/20/object-vs-companion-object-in-kotlin/)
    ```kotlin
    object ProfileQuery {
        val PROJECTION = arrayOf(
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY)
        val ADDRESS = 0
        val IS_PRIMARY = 1
    }
    ```
* companion object,伴随对象的使用
伴随对象代码块必须要在一个类中，因为kotlin写代码比较随意，扩展名***.kt***的文件内就可以写代码，伴随对象的代码必须写在class 声明的类体重去写。
跟上面一种object 的使用方式的区别：我理解为*** companion object*** 修饰的是部分为static,上面一种是全局的
```kotlin
   companion object {

        /**
         * Id to identity READ_CONTACTS permission request.
         */
        private val REQUEST_READ_CONTACTS = 0

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
        fun  test(){
            
        } 
    }
```

###### 定义组合式控件
项目中UrlConfigView为组合控件，感觉没什么好说的！
#### 网络请求
###### 使用retrofit构建网络层
*  初始化Retrofit对象
```kotlin

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by yh on 9/23/17.
 */
object YHaiServer {
    val BASE_URL = "http://192.168.1.53:8888/yibaad/sdk/"
    private val DEFAULT_TIMEOUT = 45

    private var retrofit: Retrofit
    var apiStore: ApiStore


    init {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        clientBuilder.writeTimeout(10000, TimeUnit.SECONDS)
        clientBuilder.readTimeout(10000, TimeUnit.SECONDS)

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiStore = retrofit.create(ApiStore::class.java)
    }

}
```
apiStore的定义
```kotlin

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by yh on 9/23/17.
 */

interface ApiStore  {

    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("modifyAdSourceDeployDetail")
    fun modifyAdSourceDeployDetail(@Body detailData: BaseResponse): Call<BaseResponse>
}
```
[Retrofit处理两个baseUrl的资料](http://www.jianshu.com/p/2919bdb8d09a)

使用retrofit 一般结合Rxjava使用 ,我在项目中没有使用Rxjava,代码一样很好理解;
```kotlin
 doAsync {//开启一个异步线程
            val param = BaseResponse()
            val result  = YHaiServer.apiStore.modifyAdSourceDeployDetail(param).execute().body() as BaseResponse
            if (result.code == 200){
                uiThread {//刷新UI界面
                }
            }else{ //错误的情况
                uiThread {//刷新UI界面
                }
            }
        }
```
在上面的调用中，我遇到了一个不能获取正确数据的情况，但是程序也没有发生异常，后面发现是doAsync代码块中已经把异常捕获了。如果想知道具体是什么异常，像java一样***try{}catch(e: Exception){}finally{}***就能捕获到时什么异常


###### RecyclerView 展示数据

```
        recyclerView.layoutManager =  LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = MainRecyclerAdapter(this,datas)
      //请求数据
        doAsync {
            val data = YHaiServer.apiStore.getWeather("CN101010100").execute().body() as Data
            datas.addAll(data.HeWeather5!![0].hourly_forecast)
                uiThread {
                recyclerView.adapter.notifyDataSetChanged()
                }
        }
    inner class MainRecyclerAdapter(val context: Context, val datas: MutableList<HourlyForecastBean>) : RecyclerView.Adapter<MainViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): MainViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_weather_view,null)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder?, postion: Int) {
            val data = datas[postion]
        //因为holder 被转换了一次，后面就知道通过内置的itemView找到界面上的控件
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
```


[看起来舒服的资料](http://liuqingwen.me/blog/)

[Demo](https://github.com/yanghai23/kotlnPractice)

