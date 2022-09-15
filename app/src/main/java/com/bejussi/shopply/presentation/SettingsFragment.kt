package com.bejussi.shopply.presentation

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bejussi.shopply.R
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

        observeData()

        binding.languageChoice.setOnClickListener {
            popupMenu(it)
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

        binding.notificationChoice.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                lifecycleScope.launch {
                    settingsDataStore.updateNotification(true)
                }
            } else {
                lifecycleScope.launch {
                    settingsDataStore.updateNotification(false)
                }
            }
        }

        binding.backButton.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToCategoryListFragment()
            findNavController().navigate(action)
        }
    }

    private fun observeData() {
        settingsDataStore.darkTheme.asLiveData().observe(viewLifecycleOwner) { theme ->
            theme.let {
                binding.themeChoice.isChecked = theme
            }
        }
        settingsDataStore.language.asLiveData().observe(viewLifecycleOwner) { language ->
            language.let {
                binding.languageChoice.apply {
                    text = language
                }

            }
        }
        settingsDataStore.notification.asLiveData().observe(viewLifecycleOwner) { notification ->
            notification.let {
                binding.notificationChoice.isChecked = notification
            }

        }
    }

    private fun popupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.inflate(R.menu.item_language)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.eng_language -> {
                    lifecycleScope.launch {
                        settingsDataStore.updateLanguage(it.toString())
                    }
                    true
                }
                R.id.uk_language -> {
                    lifecycleScope.launch {
                        settingsDataStore.updateLanguage(it.toString())
                    }
                    true
                }
                R.id.ru_language -> {
                    lifecycleScope.launch {
                        settingsDataStore.updateLanguage(it.toString())
                    }
                    true
                }
                else -> false
            }
        }
        popupMenu.setGravity(Gravity.END)
        popupMenu.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

