package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.varma.hemanshu.botsup.Constants
import com.varma.hemanshu.botsup.R
import com.varma.hemanshu.botsup.adapters.HomeViewPagerAdapter
import com.varma.hemanshu.botsup.databinding.FragmentHomeBinding
import timber.log.Timber

/**
 *  Fragment for Home Screen
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //Callback when page changes
    private val homePageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //Add implementation as per page change using position
            Timber.i("Position is $position")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Bind and Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Reference to ViewPager
        val homeViewPagerAdapter =
            HomeViewPagerAdapter(requireActivity(), Constants.VIEW_PAGER_ITEMS_COUNT)
        binding.homeViewpager.apply {
            //Setting up adapter
            adapter = homeViewPagerAdapter

            //Setting Chat screen as Default screen
            currentItem = Constants.DEFAULT_PAGE

            //Attaching page change callback
            registerOnPageChangeCallback(homePageChangeCallback)

            //Binding ViewPager with TabLayout to set names/icon
            TabLayoutMediator(binding.homeTabLayout, binding.homeViewpager) { tab, position ->
                tab.text = resources.getStringArray(R.array.homepage_screen_names)[position]
            }.attach()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        //Removing pager callback when fragment is destroyed
        binding.homeViewpager.unregisterOnPageChangeCallback(homePageChangeCallback)
    }
}