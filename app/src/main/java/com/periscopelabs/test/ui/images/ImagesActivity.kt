package com.periscopelabs.test.ui.images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.periscopelabs.test.R
import com.periscopelabs.test.adapter.ImageSliderAdapter
import com.periscopelabs.test.databinding.ActivityImagesBinding

class ImagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImagesBinding
    lateinit var viewPagerAdapter: ImageSliderAdapter
    lateinit var imageList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.image_1
        imageList = imageList + R.drawable.image_1
        imageList = imageList + R.drawable.image_1

        viewPagerAdapter = ImageSliderAdapter(this, imageList)
        binding.slider.adapter = viewPagerAdapter

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}