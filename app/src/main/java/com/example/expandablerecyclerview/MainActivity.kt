package com.example.expandablerecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var buList = arrayListOf("bu1", "bu2", "bu3", "bu4")
    private var poiList = arrayListOf("Printer Area", "Washroom", "Locker Area", "CommonArea")
    private var spaceList =
        arrayListOf("Workstation", "Meeting Room", "Just In Time Room", "Focus Area")
    private var resourceList = arrayListOf("Monitor", "WhiteBoard", "TableLamp", "Drawer")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(this)
        val list = ArrayList<MyDataModel>()
        val buInnerModelList = ArrayList<InnerDataModel>()
        val poiInnerModelList = ArrayList<InnerDataModel>()
        val spaceInnerModelList = ArrayList<InnerDataModel>()
        val resourceInnerModelList = ArrayList<InnerDataModel>()
        var count = 0
        for (x in 0..15) {
            when {
                x < 4 -> {
                    val model = InnerDataModel(x, false, buList[count])
                    buInnerModelList.add(model)
                    count++
                }
                x in 4..7 -> {
                    if (x == 4) {
                        count = 0
                    }
                    val model = InnerDataModel(x, false, poiList[count])
                    poiInnerModelList.add(model)
                    count++
                }
                x in 8..11 -> {
                    if (x == 8) {
                        count = 0
                    }
                    val model = InnerDataModel(x, false, spaceList[count])
                    spaceInnerModelList.add(model)
                    count++
                }
                x in 12..15 -> {
                    if (x == 12) {
                        count = 0
                    }
                    val model = InnerDataModel(x, false, resourceList[count])
                    resourceInnerModelList.add(model)
                    count++
                }
            }

        }
        for (i in 0..3) {
            val model = when (i) {
                0 -> {
                    MyDataModel(false, FilterData.BUSINESS_UNIT.tag, buInnerModelList)
                }
                1 -> {
                    MyDataModel(false, FilterData.POINT_OF_INTEREST.tag, poiInnerModelList)
                }
                2 -> {
                    MyDataModel(false, FilterData.SPACE_TYPES.tag, spaceInnerModelList)
                }
                else -> {
                    MyDataModel(false, FilterData.RESOURCES.tag, resourceInnerModelList)
                }
            }
            list.add(model)
        }
        rvMain?.apply {
            this.layoutManager = layoutManager
            hasFixedSize()
            adapter = MyAdapter(list)
        }
        txtDone?.setOnClickListener {
            buInnerModelList.forEach {
                println("checked ${it.isChecked}")
            }
            poiInnerModelList.forEach {
                println("checked ${it.isChecked}")
            }
            spaceInnerModelList.forEach {
                println("checked ${it.isChecked}")
            }
            resourceInnerModelList.forEach {
                println("checked ${it.isChecked}")
            }
        }
    }
}
