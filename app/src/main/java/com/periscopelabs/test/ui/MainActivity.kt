package com.periscopelabs.test.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.periscopelabs.test.adapter.InspectionAdapter
import com.periscopelabs.test.databinding.ActivityMainBinding
import com.periscopelabs.test.models.InspectionModel
import com.periscopelabs.test.ui.inspectiondetails.InspectionDetailsActivity
import com.periscopelabs.test.ui.listeners.InspectionItemClickListeners

class MainActivity : AppCompatActivity(), InspectionItemClickListeners {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: InspectionAdapter
    private val insData: ArrayList<InspectionModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRcv()
        initData()
    }

    private fun initRcv() {
        adapter = InspectionAdapter(this, insData, this)
        binding.rcvInspection.layoutManager = LinearLayoutManager(this)
        binding.rcvInspection.adapter = adapter
    }

    private fun initData() {
        val titles = arrayListOf("Weekly Inspection", "Above-Property Assesment",
            "Audit Shift Checklist", "Do Not Disturb - Daily Tracking List",
            "Do Not Disturb - Daily Tracking", "Annul Inspection",
            "Biennal Inspection", "Weekly Inspection",
            "Above-Property Assesment", "Biennal Inspection",
            "Weekly Inspection", "Annul Inspection")

        for (i in titles){
            val ins = InspectionModel(i, "Holiday Inn Express, Franklin", "18, Dec")
            insData.add(ins)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onItemCLick(item: InspectionModel) {
        startActivity(Intent(this, InspectionDetailsActivity::class.java))
    }
}