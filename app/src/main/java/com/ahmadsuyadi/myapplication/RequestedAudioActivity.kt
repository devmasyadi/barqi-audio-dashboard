package com.ahmadsuyadi.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmadsuyadi.barqiaudiodashboard.core.data.Resource
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.RequestedAudio
import com.ahmadsuyadi.barqiaudiodashboard.core.viewmodel.BarqiDashboardViewModel
import com.ahmadsuyadi.myapplication.databinding.ActivityRequestedAudioBinding
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class RequestedAudioActivity : AppCompatActivity(), AnkoLogger {

    private val barqiDashboardViewModel: BarqiDashboardViewModel by inject()
    private lateinit var binding: ActivityRequestedAudioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestedAudioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        barqiDashboardViewModel.getRequestedAudios().observe(this, { resources ->
            when(resources) {
                is Resource.Error -> info("Hallo error getRequestedAudios: ${resources.message}")
                is Resource.Loading -> info("Hallo loading getRequestedAudios")
                is Resource.Success -> {
                    info("Hallo success getRequestedAudios: ${resources.data}")
                }
            }
        })

        barqiDashboardViewModel.getArtists().observe(this, { resources ->
            when(resources) {
                is Resource.Error -> info("Hallo error getArtists: ${resources.message}")
                is Resource.Loading -> info("Hallo loading getArtists")
                is Resource.Success -> {
                    info("Hallo success getArtists: ${resources.data}")
                }
            }
        })

        binding.btnSubmit.setOnClickListener {
            addRequestedAudio()
        }
    }

    private fun addRequestedAudio() {
        with(binding) {
            val requestedAudio = RequestedAudio(name = edtName.text.toString(),
                email = edtEmail.text.toString(), titleAudioRequested = edtTitle.text.toString() )
            barqiDashboardViewModel.addRequestAudio(requestedAudio).observe(this@RequestedAudioActivity, { resources ->
                when(resources) {
                    is Resource.Error -> info("Hallo error addRequestedAudio: ${resources.message}")
                    is Resource.Loading -> info("Hallo loading addRequestedAudio")
                    is Resource.Success -> {
                        info("Hallo success addRequestedAudio: ${resources.data}")
                    }
                }
            })
        }

    }
}