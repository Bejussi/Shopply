package com.bejussi.shopply.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bejussi.shopply.databinding.FragmentSettingsBinding
import com.bejussi.shopply.presentation.utils.SettingsDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var settingsDataStore: SettingsDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsDataStore = SettingsDataStore(requireContext())

        settingsDataStore.darkTheme.asLiveData().observe(viewLifecycleOwner) { theme ->
            theme.let {
                binding.themeChoice.isChecked = theme
            }
        }

        binding.languageChoice.setOnClickListener {

        }

        binding.themeChoice.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                lifecycleScope.launch {
                    settingsDataStore.updateDarkTheme(true)
                }
            } else {
                lifecycleScope.launch {
                    settingsDataStore.updateDarkTheme(false)
                }
            }
        }

        binding.notificationChoice.setOnClickListener {

        }

        binding.backButton.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToCategoryListFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

