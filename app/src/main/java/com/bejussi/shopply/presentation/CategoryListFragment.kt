package com.bejussi.shopply.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.FragmentCategoryListBinding
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.presentation.adapter.CategoryListAdapter
import com.bejussi.shopply.presentation.view_model.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : Fragment() {

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CategoryListAdapter

    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categoryList.observe(this.viewLifecycleOwner,) {
            items -> items.let {
                adapter.submitList(it)
            }
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = CategoryListAdapter {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToItemListFragment()
            this.findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

}