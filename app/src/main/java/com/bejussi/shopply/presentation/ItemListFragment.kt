package com.bejussi.shopply.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.FragmentItemListBinding
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.presentation.adapter.item.ItemActionListener
import com.bejussi.shopply.presentation.adapter.item.ItemListAdapter
import com.bejussi.shopply.presentation.dialog.add_item_dialig.AddItemDialog
import com.bejussi.shopply.presentation.dialog.add_item_dialig.AddItemDialogListener
import com.bejussi.shopply.presentation.utils.SwipeToDelete
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

        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }

        setupRecyclerView()

        binding.backButton.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToCategoryListFragment()
            findNavController().navigate(action)
        }

        binding.addNewItemButton.setOnClickListener {
            AddItemDialog(requireContext(),
                object : AddItemDialogListener {
                    override fun addItem(item: Item) {
                        viewModel.insertItem(item)
                    }

                }, args.categoryName
            ).show()
        }

        binding.menuButton.setOnClickListener {
            popupMenu(it)
        }
    }

    private fun popupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.inflate(R.menu.item_list_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.sort_by_items -> {
                    viewModel.sortedItems.observe(this.viewLifecycleOwner) { items ->
                        items.let {
                            adapter.submitList(it)
                        }
                    }
                    true
                }
                R.id.delete_checked_items -> {
                    viewModel.deleteCheckedItems(args.categoryName)
                    true
                }
                R.id.clean_items_list -> {
                    viewModel.cleanItemsList(args.categoryName)
                    true
                }
                else -> false
            }
        }
        popupMenu.setForceShowIcon(true)
        popupMenu.show()
    }

    private fun setupRecyclerView() {
        adapter = ItemListAdapter(object : ItemActionListener {

            override fun onItemEdit(item: Item) {
                viewModel.editItem(item)
            }

        })
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        swipeToDelete(binding.recyclerView)
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemDelete = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteItem(itemDelete)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

}