package com.bejussi.shopply.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.FragmentItemListBinding
import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.presentation.adapter.item.ItemListAdapter
import com.bejussi.shopply.presentation.view_model.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    val args: ItemListFragmentArgs by navArgs()

    private lateinit var adapter: ItemListAdapter

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryNameText.text = args.categoryName

        viewModel.allItems.observe(this.viewLifecycleOwner) {
            items -> items.let {
                adapter.submitList(it)
            }
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ItemListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

}