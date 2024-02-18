package com.periscopelabs.test.ui.inspections

import android.content.res.Resources
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.periscopelabs.test.R
import com.periscopelabs.test.adapter.RoomInspectionAdapter
import com.periscopelabs.test.databinding.ModalDialogInspectionBinding
import com.periscopelabs.test.models.InspectionsRoomModel
import com.periscopelabs.test.ui.listeners.RoomInspectionItemListener
import com.periscopelabs.test.utils.RecyclerItemTouchHelper

class InspectionBottomSheetDialogFragment: BottomSheetDialogFragment() {
    private lateinit var binding :ModalDialogInspectionBinding
    private lateinit var amAdapter: RoomInspectionAdapter
    private lateinit var bedAdapter: RoomInspectionAdapter
    private lateinit var cleanAdapter: RoomInspectionAdapter
    private lateinit var livingAdapter: RoomInspectionAdapter
    private val amData: ArrayList<InspectionsRoomModel> = arrayListOf()
    private val bedData: ArrayList<InspectionsRoomModel> = arrayListOf()
    private val cleanData: ArrayList<InspectionsRoomModel> = arrayListOf()
    private val livingData: ArrayList<InspectionsRoomModel> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ModalDialogInspectionBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setOnShowListener { dialog ->
            val bottomSheetBehavior: BottomSheetBehavior<*> = (dialog as BottomSheetDialog).behavior
            bottomSheetBehavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        initRcvAm()
        initRcvClean()
        initRcvBed()
        initRcvLiving()

        initListeners()
    }

    private fun initListeners() {
        binding.viewInspection.setOnClickListener {
            if (binding.rcvAmInspection.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(binding.rcvAmInspection, AutoTransition())
                binding.rcvAmInspection.visibility = View.GONE
                binding.ivAmExpand.setImageResource(R.drawable.baseline_expand_more)
            } else {
                TransitionManager.beginDelayedTransition(binding.rcvAmInspection, AutoTransition())
                binding.rcvAmInspection.visibility = View.VISIBLE
                binding.ivAmExpand.setImageResource(R.drawable.baseline_expand_less)
            }
        }

        binding.viewBedrooms.setOnClickListener {
            if (binding.rcvBedrooms.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(binding.rcvBedrooms, AutoTransition())
                binding.rcvBedrooms.visibility = View.GONE
                binding.ivBedExpand.setImageResource(R.drawable.baseline_expand_more)
            } else {
                TransitionManager.beginDelayedTransition(binding.rcvBedrooms, AutoTransition())
                binding.rcvBedrooms.visibility = View.VISIBLE
                binding.ivBedExpand.setImageResource(R.drawable.baseline_expand_less)
            }
        }

        binding.viewCleanness.setOnClickListener {
            if (binding.rcvCleanliness.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(binding.rcvCleanliness, AutoTransition())
                binding.rcvCleanliness.visibility = View.GONE
                binding.ivCleanExpand.setImageResource(R.drawable.baseline_expand_more)
            } else {
                TransitionManager.beginDelayedTransition(binding.rcvCleanliness, AutoTransition())
                binding.rcvCleanliness.visibility = View.VISIBLE
                binding.ivCleanExpand.setImageResource(R.drawable.baseline_expand_less)
            }
        }

        binding.viewLivingAreas.setOnClickListener {
            if (binding.rcvLivingExpense.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(binding.rcvLivingExpense, AutoTransition())
                binding.rcvLivingExpense.visibility = View.GONE
                binding.ivLivingExpand.setImageResource(R.drawable.baseline_expand_more)
            } else {
                TransitionManager.beginDelayedTransition(binding.rcvLivingExpense, AutoTransition())
                binding.rcvLivingExpense.visibility = View.VISIBLE
                binding.ivLivingExpand.setImageResource(R.drawable.baseline_expand_less)
            }
        }

        binding.ivBack.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun initRcvAm() {
        amAdapter = RoomInspectionAdapter(requireContext(), amData, object : RoomInspectionItemListener {
            override fun onStatusButtonClick(position: Int, status: Int) {
                amData[position].colorStatus = status
                amAdapter.notifyItemChanged(position)
            }
        })
        binding.rcvAmInspection.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(RecyclerItemTouchHelper(requireContext(), amAdapter)).attachToRecyclerView(binding.rcvAmInspection)
        binding.rcvAmInspection.hasFixedSize()
        binding.rcvAmInspection.adapter = amAdapter
        ViewCompat.setNestedScrollingEnabled( binding.rcvAmInspection, false)

        for (i in 0..4) {
            val ins = InspectionsRoomModel("Every inch of the room has been verified", 4, 4)
            amData.add(ins)
        }
        amAdapter.notifyDataSetChanged()

    }

    private fun initRcvBed() {
        bedAdapter = RoomInspectionAdapter(requireContext(), bedData, object : RoomInspectionItemListener {
            override fun onStatusButtonClick(position: Int, status: Int) {
                bedData[position].colorStatus = status
                bedAdapter.notifyItemChanged(position)
            }
        })
        binding.rcvBedrooms.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvBedrooms.adapter = bedAdapter
        binding.rcvBedrooms.hasFixedSize()
        ViewCompat.setNestedScrollingEnabled( binding.rcvBedrooms, false)
        ItemTouchHelper(RecyclerItemTouchHelper(requireContext(), bedAdapter)).attachToRecyclerView(binding.rcvBedrooms)
        for (i in 0..4) {
            val ins = InspectionsRoomModel("Every inch of the room has been verified", 4, 2)
            bedData.add(ins)
        }
        bedAdapter.notifyDataSetChanged()
    }

    private fun initRcvClean() {
        cleanAdapter = RoomInspectionAdapter(requireContext(), cleanData, object : RoomInspectionItemListener {
            override fun onStatusButtonClick(position: Int, status: Int) {
                cleanData[position].colorStatus = status
                cleanAdapter.notifyItemChanged(position)
            }
        })
        binding.rcvCleanliness.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvCleanliness.adapter = cleanAdapter
        binding.rcvCleanliness.hasFixedSize()
        ViewCompat.setNestedScrollingEnabled( binding.rcvCleanliness, false);
        ItemTouchHelper(RecyclerItemTouchHelper(requireContext(), cleanAdapter)).attachToRecyclerView(binding.rcvCleanliness)

        for (i in 0..4) {
            val ins = InspectionsRoomModel("Every inch of the room has been verified", 4, 1)
            cleanData.add(ins)
        }
        cleanAdapter.notifyDataSetChanged()

    }

    private fun initRcvLiving() {
        livingAdapter =
            RoomInspectionAdapter(requireContext(), livingData, object : RoomInspectionItemListener {
                override fun onStatusButtonClick(position: Int, status: Int) {
                    livingData[position].colorStatus = status
                    livingAdapter.notifyItemChanged(position)
                }
            })
        binding.rcvLivingExpense.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvLivingExpense.adapter = livingAdapter
        binding.rcvLivingExpense.hasFixedSize()
        ViewCompat.setNestedScrollingEnabled( binding.rcvLivingExpense, false);
        ItemTouchHelper(RecyclerItemTouchHelper(requireContext(), livingAdapter)).attachToRecyclerView(binding.rcvLivingExpense)

        for (i in 0..4) {
            val ins = InspectionsRoomModel("Every inch of the room has been verified", 4, 2)
            livingData.add(ins)
        }
        livingAdapter.notifyDataSetChanged()

    }

}