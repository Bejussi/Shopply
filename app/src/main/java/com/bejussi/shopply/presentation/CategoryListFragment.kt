package com.bejussi.shopply.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bejussi.shopply.databinding.FragmentCategoryListBinding
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.presentation.adapter.category.CategoryListAdapter
import com.bejussi.shopply.presentation.adapter.category.CategoryActionListener
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

        viewModel.categoryList.observe(this.viewLifecycleOwner) {
            items -> items.let {
                adapter.submitList(it)
            }
        }

        setupRecyclerView()

        binding.menuButton.setOnClickListener {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToSettingsFragment()
            findNavController().navigate(action)
        }

        binding.addNewListButton.setOnClickListener {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToAddNewListSheet()
            findNavController().navigate(action)
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(query != null) {
                    searchCategory(query.toString())
                }
            }

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(query != null) {
                    searchCategory(query.toString())
                }
            }

            override fun afterTextChanged(query: Editable?) {
                if(query != null) {
                    searchCategory(query.toString())
                }
            }

        })

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && binding.addNewListButton.visibility == View.VISIBLE) {
                    binding.addNewListButton.visibility = View.INVISIBLE
                } else if (dy < 0 && binding.addNewListButton.visibility != View.VISIBLE) {
                    binding.addNewListButton.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun searchCategory(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchCategory(searchQuery).observe(this) { list ->
            list?.let {
                adapter.submitList(it)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = CategoryListAdapter(object : CategoryActionListener {

            override fun onCategoryDelete(category: Category) {
                viewModel.deleteCategory(category)
            }

            override fun onCategoryEdit(category: Category) {
               val action = CategoryListFragmentDirections.actionCategoryListFragmentToEditCategorySheet(category)
                findNavController().navigate(action)
            }

            override fun onShowCategoryProductsList(categoryName: String) {
                val action = CategoryListFragmentDirections.actionCategoryListFragmentToItemListFragment(categoryName)
                findNavController().navigate(action)
            }

        })
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}