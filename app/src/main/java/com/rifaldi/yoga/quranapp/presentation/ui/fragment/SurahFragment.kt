package com.rifaldi.yoga.quranapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifaldi.yoga.quranapp.databinding.FragmentSurahBinding
import com.rifaldi.yoga.quranapp.domain.model.SurahModel
import com.rifaldi.yoga.quranapp.presentation.ui.adapter.AyahAdapter
import com.rifaldi.yoga.quranapp.presentation.ui.adapter.SurahAdapter
import com.rifaldi.yoga.quranapp.presentation.ui.base.BaseInterface
import com.rifaldi.yoga.quranapp.presentation.ui.viewmodel.SurahViewModel
import com.rifaldi.yoga.quranapp.presentation.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by aldi on 04/04/22.
 */
@AndroidEntryPoint
class SurahFragment : Fragment(), BaseInterface {

    private lateinit var binding : FragmentSurahBinding

    private val viewModel : SurahViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSurahBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var ayahAdapter : AyahAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.setSurah(it.getParcelable<SurahModel>("surah")!!)
        }

        initComponents()
        subscribeListeners()
        subscribeObservers()
    }

    override fun initComponents() {

        (requireActivity() as AppCompatActivity).supportActionBar?.title = viewModel.state.value.surah!!.name.transliteration.en

        binding.apply {
            ayahAdapter = AyahAdapter(viewModel.state.value.surah!!)

            rvList.apply {
                adapter = ayahAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

        }
    }

    override fun subscribeListeners() {

    }

    override fun subscribeObservers() {
        viewModel.observeSurahList().observe(viewLifecycleOwner){
            it?.let {
                when(it.status){
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        ayahAdapter.submitList(it.data?.toMutableList())
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collectLatest {
                    viewModel.getList(it.surah!!.number)
                }
            }
        }
    }

}