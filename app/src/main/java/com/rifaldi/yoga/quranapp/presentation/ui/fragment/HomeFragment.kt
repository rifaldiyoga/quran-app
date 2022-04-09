package com.rifaldi.yoga.quranapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifaldi.yoga.quranapp.R
import com.rifaldi.yoga.quranapp.databinding.FragmentHomeBinding
import com.rifaldi.yoga.quranapp.presentation.ui.adapter.SurahAdapter
import com.rifaldi.yoga.quranapp.presentation.ui.base.BaseInterface
import com.rifaldi.yoga.quranapp.presentation.ui.viewmodel.HomeViewModel
import com.rifaldi.yoga.quranapp.presentation.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by aldi on 04/04/22.
 */
@AndroidEntryPoint
class HomeFragment : Fragment(), BaseInterface {

    private lateinit var binding : FragmentHomeBinding

    private val viewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var surahAdapter: SurahAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        subscribeListeners()
        subscribeObservers()
    }

    override fun initComponents() {
        binding.apply {
            setName()
            surahAdapter = SurahAdapter(onItemClick = {
                val action = HomeFragmentDirections.actionHomeFragmentToSurahFragment(it)
                findNavController().navigate(action)
            })

            rvList.apply {
                adapter = surahAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun subscribeListeners() {
        binding.apply {
            tvName.setOnClickListener {
                tvName.visibility = View.INVISIBLE
                etName.visibility = View.VISIBLE
                etName.requestFocus()
            }

            etName.setOnEditorActionListener { textView, i, keyEvent ->
                if(i == EditorInfo.IME_ACTION_DONE){
                    tvName.visibility = View.VISIBLE
                    etName.visibility = View.GONE
                    PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString("nama", etName.text.toString().trim()).apply()
                    tvName.text = etName.text.toString().trim()
                    setName()
                }
                return@setOnEditorActionListener false
            }
        }
    }

    fun setName() {
        val name = (PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("nama", ""))
        val a = if(name!!.isEmpty()) getString(R.string.default_name) else name
        binding.tvName.setText(a)
    }

    override fun subscribeObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.surahList.collect {
                    when(it.status){
                        Resource.Status.LOADING -> {
                            showProgressBar(true)
                        }
                        Resource.Status.SUCCESS -> {
                            showProgressBar(false)
                            surahAdapter.submitList(it.data)
                            surahAdapter.notifyDataSetChanged()
                        }
                        Resource.Status.ERROR -> {
                            showProgressBar(false)


                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.lastRead.collect {
                    when(it.status){
                        Resource.Status.LOADING -> {

                        }
                        Resource.Status.SUCCESS -> {
                            it.data?.let {
                                binding.tvSurahName.text = it.name.transliteration.en
                                binding.tvDesc.text = it.name.short
                            } ?: run {
                                binding.tvSurahName.text = getString(R.string.start_your_reading)
                                binding.tvDesc.visibility = View.GONE
                            }
                        }
                        Resource.Status.ERROR -> {
                            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun showProgressBar(isLoading  : Boolean){
        binding.apply {
            if(isLoading){
                progressBar.visibility = View.VISIBLE
                rvList.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                rvList.visibility = View.VISIBLE
            }
        }
    }

}