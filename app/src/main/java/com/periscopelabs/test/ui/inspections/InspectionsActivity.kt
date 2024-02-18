package com.periscopelabs.test.ui.inspections

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.periscopelabs.test.adapter.InspectionAdapter
import com.periscopelabs.test.databinding.ActivityInspectionsBinding
import com.periscopelabs.test.models.InspectionModel
import com.periscopelabs.test.ui.listeners.InspectionItemClickListeners

class InspectionsActivity : AppCompatActivity(), InspectionItemClickListeners {
    private lateinit var binding: ActivityInspectionsBinding
    private lateinit var adapter: InspectionAdapter
    private val insData: ArrayList<InspectionModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInspectionsBinding.inflate(layoutInflater)
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
        val bottomDialogFragment = InspectionBottomSheetDialogFragment()
        bottomDialogFragment.show(
            supportFragmentManager, "InspectionDialog"
        )
        //startActivity(Intent(this, InspectionDetailsActivity::class.java))
    }
}