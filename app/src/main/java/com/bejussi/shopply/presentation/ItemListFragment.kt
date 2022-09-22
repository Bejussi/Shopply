package com.bejussi.shopply.presentation

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.FragmentItemListBinding
import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.presentation.adapter.item.ItemActionListener
import com.bejussi.shopply.presentation.adapter.item.ItemListAdapter
import com.bejussi.shopply.presentation.utils.SwipeToDelete
import com.bejussi.shopply.presentation.view_model.ItemViewModel
import com.google.android.material.snackbar.Snackbar
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

        binding.categoryNameText.text = args.category.name

        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
                viewModel.checkIfDatabaseEmpty(items)
            }
        }

        viewModel.emptyDatabase.observe(this.viewLifecycleOwner) {
            showEmptyDatabaseViews(it)
        }

        setupRecyclerView()

        binding.backButton.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToCategoryListFragment()
            findNavController().navigate(action)
        }

        binding.addNewItemButton.setOnClickListener {
            val action =
                ItemListFragmentDirections.actionItemListFragmentToAddNewItemSheet(args.category)
            findNavController().navigate(action)
        }

        binding.menuButton.setOnClickListener {
            popupMenu(it)
        }
    }

    private fun popupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.inflate(R.menu.item_list_menu)
        popupMenu.setOnMenuItemClickListener { it ->
            when (it.itemId) {
                R.id.sort_by_name -> {
                    viewModel.sortedItems.observe(this.viewLifecycleOwner) { items ->
                        items.let {
                            adapter.submitList(it)
                        }
                    }
                    true
                }
                R.id.sort_by_bought -> {
                    viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
                        items.let {
                            adapter.submitList(it)
                        }
                    }
                    true
                }
                R.id.delete_checked_items -> {
                    viewModel.deleteCheckedItems(args.category.id)
                    true
                }
                R.id.clean_items_list -> {
                    viewModel.cleanItemsList(args.category.id)
                    true
                }
                else -> false
            }
        }
        popupMenu.setForceShowIcon(true)
        popupMenu.setGravity(Gravity.END)
        popupMenu.show()
    }

    private fun setupRecyclerView() {
        adapter = ItemListAdapter(object : ItemActionListener {

            override fun onItemEdit(item: Item) {
                viewModel.editItem(item)
            }

        })
        binding.recyclerView.adapter = adapter

        swipeToDelete(binding.recyclerView)
    }

    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        if (emptyDatabase) {
            binding.emptyListMainText.visibility = View.VISIBLE
            binding.emptyListText.visibility = View.VISIBLE
        } else {
            binding.emptyListMainText.visibility = View.INVISIBLE
            binding.emptyListText.visibility = View.INVISIBLE
        }
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemDelete = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteItem(itemDelete)
                restoreDeletedData(viewHolder.itemView, itemDelete)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedData(view: View, deletedItem: Item) {
        val snackBar = Snackbar.make(
            view, getString(R.string.deleted_item,deletedItem.name),
            Snackbar.LENGTH_LONG
        )
        snackBar.apply {
            setAction(getString(R.string.undo)) {
                viewModel.insertItem(deletedItem)
            }
            setBackgroundTint(resources.getColor(R.color.black))
            setTextColor(resources.getColor(R.color.white))
            setActionTextColor(resources.getColor(R.color.white))
            show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}