package com.rifaldi.yoga.quranapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifaldi.yoga.quranapp.databinding.FragmentBookmarkBinding
import com.rifaldi.yoga.quranapp.presentation.ui.adapter.AyahAdapter
import com.rifaldi.yoga.quranapp.presentation.ui.adapter.BookmarkAdapter
import com.rifaldi.yoga.quranapp.presentation.ui.base.BaseInterface
import com.rifaldi.yoga.quranapp.presentation.ui.viewmodel.BookmarkViewModel
import com.rifaldi.yoga.quranapp.presentation.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by aldi on 07/04/22.
 */
@AndroidEntryPoint
class BookmarkFragment : Fragment(), BaseInterface {

    private lateinit var binding : FragmentBookmarkBinding

    private val viewModel : BookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var ayahAdapter : BookmarkAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
//            viewModel.setSurah(it.getParcelable<SurahModel>("surah")!!)
        }

        initComponents()
        subscribeListeners()
        subscribeObservers()
    }

    override fun initComponents() {

        binding.apply {
            ayahAdapter = BookmarkAdapter(onBookmarkClick = { model ->
                viewModel.deleteAyah(model)
                ayahAdapter.submitList(ayahAdapter.currentList.filter { it != model })
                ayahAdapter.notifyDataSetChanged()
            })

            rvList.apply {
                adapter = ayahAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

        }
    }

    override fun subscribeListeners() {

    }

    override fun subscribeObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.bookmarkList.collectLatest {
                    when(it.status){
                        Resource.Status.LOADING -> {

                        }
                        Resource.Status.SUCCESS -> {
                            ayahAdapter.submitList(it.data)
                        }
                        Resource.Status.ERROR -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        viewModel.bookmarkList
    }


}