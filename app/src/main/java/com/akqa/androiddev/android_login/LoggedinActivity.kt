package com.akqa.androiddev.android_login

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.javasampleapproach.kotlin.gridview.Watch
import kotlinx.android.synthetic.main.activity_loggedin.*
import kotlinx.android.synthetic.main.watch_entry.view.*


class LoggedinActivity: AppCompatActivity() {
    var adapter: WatchAdapter? = null
    var watchList = ArrayList<Watch>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggedin);

        val value = getIntent().getStringExtra("key");
        var welcome_msg = findViewById(R.id.welcome_msg) as TextView
        welcome_msg.setText(value);

        // load foods
        watchList.add(Watch("IW325112", R.drawable.coffee_pot))
        watchList.add(Watch("IW325342", R.drawable.espresso))
        watchList.add(Watch("IW325672", R.drawable.french_fries))
        watchList.add(Watch("IW325370",R.drawable.honey))
        watchList.add(Watch("IW326554", R.drawable.strawberry_ice_cream))
        watchList.add(Watch("IW324300", R.drawable.sugar_cubes))
        adapter = WatchAdapter(this, watchList)

        gvWatches.adapter = adapter
    }

    class WatchAdapter : BaseAdapter {
        var watchList = ArrayList<Watch>()
        var context: Context? = null

        constructor(context: Context, watchList: ArrayList<Watch>) : super() {
            this.context = context
            this.watchList = watchList
        }

        override fun getCount(): Int {
            return watchList.size
        }

        override fun getItem(position: Int): Any {
            return watchList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val watch = this.watchList[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var watchView = inflator.inflate(R.layout.watch_entry, null)
            watchView.imgWatch.setImageResource(watch.image!!)
            watchView.tvName.text = watch.name!!

            return watchView
        }
    }
}






