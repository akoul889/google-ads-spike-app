package com.globalagricentral.myfirstapp

import android.R
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.globalagricentral.myfirstapp.databinding.FragmentFirstBinding
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        lateinit var adLoader: AdLoader
//        ca-app-pub-3940256099942544/2247696110
        adLoader = AdLoader.Builder(requireContext(), "ca-app-pub-8214596343419753/2692850905")
            .forNativeAd { nativeAd ->
                val styles =
                    NativeTemplateStyle.Builder().withMainBackgroundColor(ColorDrawable(requireContext().getColor(R.color.background_dark))).build()
                val template: TemplateView = binding.testAd1
                template.setStyles(styles)
                template.setNativeAd(nativeAd)

                if (adLoader.isLoading) {
                    // The AdLoader is still loading ads.
                    // Expect more adLoaded or onAdFailedToLoad callbacks.
                    Log.d("AdLoader\$forNativeAds", "AdLoader is still loading ads")
                } else {
                    // The AdLoader has finished loading ads.
                    Log.d("AdLoader\$forNativeAds", "AdLoader finished loading ads")

                }
            }.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Handle the failure by logging, altering the UI, and so on.
                    Log.d("AdLoader\$withAdListener", "Failed to load ads"+ adError.message)
                }
            })
            .build()

        adLoader.loadAd(AdRequest.Builder().build())

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}