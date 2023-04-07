package com.example.gridlayout

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sqrt


internal class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var recyclerDataArrayList: ArrayList<RecyclerData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.idCourseRV)
        recyclerDataArrayList = ArrayList()
        recyclerDataArrayList.apply {
            add(RecyclerData("Cheese Burgers", "1"))
            add(RecyclerData("Cheese Burgers", "2"))
            add(RecyclerData("Cheese Burgers", "2"))
            add(RecyclerData("Cheese Burgers", "7"))
            add(RecyclerData("Cheese Burgers", "2"))
            add(RecyclerData("Cheese Burgers", "2"))
            add(RecyclerData("Cheese Burgers", "5"))
            add(RecyclerData("Cheese Burgers", "1"))
            add(RecyclerData("Cheese Burgers", "2"))
            add(RecyclerData("Cheese Burgers", "2"))
            add(RecyclerData("Cheese Burgers", "4"))
            add(RecyclerData("Cheese Burgers", "3"))
        }

        val adapter = RecyclerViewAdapter(recyclerDataArrayList)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(GridSpacingItemDecoration(10))

        val deviceType = getDeviceInfo(applicationContext, Device.DEVICE_TYPE)
        if (deviceType == "Mobile") {
            layoutManager = GridLayoutManager(this, recyclerDataArrayList.size)
        } else if (deviceType == "Tablet") {
            layoutManager =
                GridLayoutManager(this, this.resources.getInteger(R.integer.grid_column_count))
        }
        recyclerView.layoutManager = layoutManager
    }


    enum class Device {
        DEVICE_TYPE
    }

    companion object {
        fun getDeviceInfo(context: Context, device: Device?): String {
            try {
                when (device) {
                    Device.DEVICE_TYPE -> return if (isTablet(context)) {
                        if (getDevice5inch(context)) {
                            "Tablet"
                        } else {
                            "Mobile"
                        }
                    } else {
                        "Mobile"
                    }
                    else -> {
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }

        private fun getDevice5inch(context: Context): Boolean {
            return try {
                val displayMetrics = context.resources.displayMetrics
                val yInch = displayMetrics.heightPixels / displayMetrics.ydpi
                val xInch = displayMetrics.widthPixels / displayMetrics.xdpi
                val diagonalInch = sqrt(xInch * xInch + yInch * yInch.toDouble())
                diagonalInch >= 7
            } catch (e: Exception) {
                false
            }
        }

        private fun isTablet(context: Context): Boolean {
            return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
        }
    }
}